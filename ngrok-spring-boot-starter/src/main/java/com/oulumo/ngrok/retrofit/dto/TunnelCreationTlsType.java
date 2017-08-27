package com.oulumo.ngrok.retrofit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Zsolt Homorodi.
 */
public enum TunnelCreationTlsType {
    @JsonProperty("true")
    HttpsOnly,
    @JsonProperty("false")
    HttpOnly,
    @JsonProperty("both")
    Both
}
