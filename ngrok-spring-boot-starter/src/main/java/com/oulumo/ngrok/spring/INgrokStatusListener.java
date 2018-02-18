package com.oulumo.ngrok.spring;

import com.oulumo.ngrok.client.dto.Tunnel;

/**
 * @author Zsolt Homorodi.
 */
public interface INgrokStatusListener {
    void ngrokTunnelCreated(Tunnel tunnel);

    void ngrokTunnelDeleted(Tunnel tunnel);
}
