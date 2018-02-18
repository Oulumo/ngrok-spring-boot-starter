package com.oulumo.ngrok.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */

@Component
@ConfigurationProperties("oulumo.ngrok")
public class NgrokProperties {
    /**
     * Enables / disables the creation of Ngrok tunnels (exposing the application ports through Ngrok).
     */
    private boolean enabled = false;

    /**
     * If true the HTTP tunnels opened by Ngrok will be secured with TLS, if false TLS will not be enabled.
     */
    private boolean secureHttpWithTls = true;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isSecureHttpWithTls() {
        return secureHttpWithTls;
    }

    public void setSecureHttpWithTls(boolean secureHttpWithTls) {
        this.secureHttpWithTls = secureHttpWithTls;
    }
}