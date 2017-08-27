package com.oulumo.ngrok.retrofit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Zsolt Homorodi.
 */
public enum TunnelProtocol {
    @JsonProperty("http")
    Http,
    @JsonProperty("https")
    Https,
    @JsonProperty("tcp")
    TCP,
    @JsonProperty("tls")
    TLS
}
