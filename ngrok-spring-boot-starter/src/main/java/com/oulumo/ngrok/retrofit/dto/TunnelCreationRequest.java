package com.oulumo.ngrok.retrofit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Zsolt Homorodi.
 */
public class TunnelCreationRequest {
    private String name;
    @JsonProperty("proto")
    private TunnelProtocol protocol;
    @JsonProperty("addr")
    private String address;
    @JsonProperty("inspect")
    private boolean requestInspectionEnabled;
    @JsonProperty("bind_tls")
    private TunnelCreationTlsType tlsType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TunnelProtocol getProtocol() {
        return protocol;
    }

    public void setProtocol(TunnelProtocol protocol) {
        this.protocol = protocol;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isRequestInspectionEnabled() {
        return requestInspectionEnabled;
    }

    public void setRequestInspectionEnabled(boolean requestInspectionEnabled) {
        this.requestInspectionEnabled = requestInspectionEnabled;
    }

    public TunnelCreationTlsType getTlsType() {
        return tlsType;
    }

    public void setTlsType(TunnelCreationTlsType tlsType) {
        this.tlsType = tlsType;
    }
}
