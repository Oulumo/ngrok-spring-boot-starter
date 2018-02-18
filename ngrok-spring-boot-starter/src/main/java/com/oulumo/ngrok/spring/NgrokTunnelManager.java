package com.oulumo.ngrok.spring;

import com.oulumo.ngrok.client.NgrokClient;
import com.oulumo.ngrok.client.dto.Tunnel;
import com.oulumo.ngrok.spring.config.NgrokProperties;
import com.oulumo.ngrok.spring.mapping.IPortMappingDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class NgrokTunnelManager {
    private static final Logger log = LoggerFactory.getLogger(NgrokTunnelManager.class);

    private NgrokClient ngrokClient;
    private NgrokProperties ngrokProperties;
    private List<INgrokStatusListener> statusListeners;

    private Map<String, Tunnel> definedTunnels;
    private ReentrantLock tunnelCreationLock = new ReentrantLock();


    public NgrokTunnelManager(NgrokClient ngrokClient, NgrokProperties ngrokProperties, List<INgrokStatusListener> statusListeners) {
        this.ngrokClient = ngrokClient;
        this.ngrokProperties = ngrokProperties;

        this.definedTunnels = new HashMap<>();
        this.statusListeners = statusListeners == null ? new ArrayList<>() : new ArrayList<>(statusListeners);
    }

    public <T extends IPortMappingDefinition> Tunnel createTunnelFor(T portMappingDefinition) {
        log.info("Requesting Ngrok tunnel with parameters: {}", portMappingDefinition);

        tunnelCreationLock.lock();

        try {
            Tunnel tunnel = definedTunnels.get(portMappingDefinition.getName());

            if (tunnel == null) {
                if (ngrokProperties.isEnabled()) {
                    if (portMappingDefinition.getPortType().equals(IPortMappingDefinition.PortType.Http)) {
                        if (ngrokProperties.isSecureHttpWithTls()) {
                            tunnel = ngrokClient.createHttpsTunnelFor(portMappingDefinition.getName(), portMappingDefinition.getPort());
                        }
                        else {
                            tunnel = ngrokClient.createHttpTunnelFor(portMappingDefinition.getName(), portMappingDefinition.getPort());
                        }
                    }

                    if (tunnel != null) {
                        log.info("Tunnel successfully created.");
                        definedTunnels.put(portMappingDefinition.getName(), tunnel);
                        Tunnel finalTunnel = tunnel;
                        statusListeners.forEach(iNgrokStatusListener -> iNgrokStatusListener.ngrokTunnelCreated(finalTunnel));
                    }
                    else {
                        log.warn("Tunnel creation unsuccessful.");
                    }
                }
                else {
                    log.warn("Ngrok usage is disabled, tunnel will NOT be created.");
                }
            }
            else {
                log.info("Tunnel already exists.");
            }

            return tunnel;
        }
        finally {
            tunnelCreationLock.unlock();
        }
    }

    public <T extends IPortMappingDefinition> void deleteTunnelOf(T portMappingDefinition) {
        deleteTunnelWithName(portMappingDefinition.getName(), true);
    }

    public void deleteTunnelWithName(String tunnelName) {
        deleteTunnelWithName(tunnelName, true);
    }

    public void deleteTunnelWithName(String tunnelName, boolean notifyListeners) {
        tunnelCreationLock.lock();

        try {
            Tunnel tunnel = definedTunnels.get(tunnelName);

            if (tunnel != null) {
                if (ngrokClient.deleteTunnel(tunnelName)) {
                    log.info("Tunnel '{}' successfully deleted.");

                    if (notifyListeners) {
                        statusListeners.forEach(iNgrokStatusListener -> iNgrokStatusListener.ngrokTunnelDeleted(tunnel));
                    }
                }
                else {
                    log.warn("Tunnel deletion for '{}' failed.");
                }
            }
            else {
                log.warn("Tunnel with name '{}' was not created by this manager - will not try to delete it.");
            }
        }
        finally {
            tunnelCreationLock.unlock();
        }
    }
}
