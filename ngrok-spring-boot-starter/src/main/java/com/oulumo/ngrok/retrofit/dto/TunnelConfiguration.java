package com.oulumo.ngrok.retrofit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Zsolt Homorodi.
 */
public class TunnelConfiguration {
    @JsonProperty("addr")
    private String address;
    @JsonProperty("inspect")
    private boolean requestInspectionEnabled;

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
}
