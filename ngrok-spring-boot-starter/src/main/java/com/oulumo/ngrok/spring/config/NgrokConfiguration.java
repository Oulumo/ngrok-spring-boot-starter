package com.oulumo.ngrok.spring.config;

import com.oulumo.ngrok.client.NgrokClient;
import com.oulumo.ngrok.spring.INgrokStatusListener;
import com.oulumo.ngrok.spring.NgrokTunnelManager;
import com.oulumo.ngrok.spring.startup.NgrokStartupInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
@Configuration
@EnableConfigurationProperties(NgrokProperties.class)
@AutoConfigureOrder
public class NgrokConfiguration {
    @Autowired
    NgrokProperties ngrokProperties;

    @Bean
    public NgrokClient ngrokClient() {
        return new NgrokClient();
    }

    @Bean
    public NgrokTunnelManager ngrokTunnelManager(NgrokClient ngrokClient, Optional<List<INgrokStatusListener>> listeners) {
        if (listeners.isPresent()) {
            return new NgrokTunnelManager(ngrokClient, ngrokProperties, listeners.get());
        }
        else {
            return new NgrokTunnelManager(ngrokClient, ngrokProperties, null);
        }
    }

    @Bean
    public NgrokStartupInitializer ngrokStartupInitializer(NgrokTunnelManager ngrokTunnelManager) {
        return new NgrokStartupInitializer(ngrokTunnelManager);
    }
}
