package com.oulumo.ngrok.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * @author Zsolt Homorodi.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TunnelListResponse {
    private List<Tunnel> tunnels;

    public List<Tunnel> getTunnels() {
        return tunnels;
    }

    public void setTunnels(List<Tunnel> tunnels) {
        this.tunnels = tunnels;
    }
}
