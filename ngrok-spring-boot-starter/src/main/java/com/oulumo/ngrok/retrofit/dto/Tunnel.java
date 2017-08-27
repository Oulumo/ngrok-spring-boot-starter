package com.oulumo.ngrok.retrofit.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Zsolt Homorodi.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tunnel {
    private String name;
    private String uri;
    @JsonProperty("public_url")
    private String publicUrl;
    @JsonProperty("proto")
    private TunnelProtocol protocol;
    @JsonProperty("config")
    private TunnelConfiguration configuration;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getPublicUrl() {
        return publicUrl;
    }

    public void setPublicUrl(String publicUrl) {
        this.publicUrl = publicUrl;
    }

    public TunnelProtocol getProtocol() {
        return protocol;
    }

    public void setProtocol(TunnelProtocol protocol) {
        this.protocol = protocol;
    }

    public TunnelConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(TunnelConfiguration configuration) {
        this.configuration = configuration;
    }
}
