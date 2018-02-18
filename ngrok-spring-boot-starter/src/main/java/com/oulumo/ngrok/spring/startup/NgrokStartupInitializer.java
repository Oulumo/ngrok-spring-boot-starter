package com.oulumo.ngrok.spring.startup;

import com.oulumo.ngrok.spring.NgrokTunnelManager;
import com.oulumo.ngrok.spring.mapping.IPortMappingDefinition;
import com.oulumo.ngrok.spring.mapping.SimplePortMappingDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author Zsolt Homorodi.
 */
public class NgrokStartupInitializer implements DisposableBean, ApplicationListener<EmbeddedServletContainerInitializedEvent> {
    private static final Logger log = LoggerFactory.getLogger(NgrokStartupInitializer.class);
    private static final String EMBEDDED_SERVLET_TUNNEL_NAME = "embeddedservlet";

    private NgrokTunnelManager ngrokTunnelManager;

    public NgrokStartupInitializer(NgrokTunnelManager ngrokTunnelManager) {
        this.ngrokTunnelManager = ngrokTunnelManager;
    }

    @Override
    public void destroy() throws Exception {
        ngrokTunnelManager.deleteTunnelWithName(EMBEDDED_SERVLET_TUNNEL_NAME, false);
    }

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
        int serverPort = event.getEmbeddedServletContainer().getPort();
        log.debug("Server initialized, port is {}", serverPort);

        ngrokTunnelManager.createTunnelFor(new SimplePortMappingDefinition(
                EMBEDDED_SERVLET_TUNNEL_NAME,
                serverPort,
                IPortMappingDefinition.PortType.Http
        ));
    }
}
