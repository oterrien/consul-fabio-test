package com.ote.test.server.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * Create Key/Values in config/server/my/prop
 * <p>
 * config for configuration
 * server for the name of the service in consul
 * my/prop
 */
@ConditionalOnProperty(value = "configuration.loader.type", havingValue = "CONSUL", matchIfMissing = false)
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "my") // bind with "my"
@Slf4j
public class ConsulConfigurationLoader {

    @Autowired
    private ConfigurationLoader configurationLoader;

    /**
     * bind with "prop" and parse the content in YAML object (Properties).
     * This method is called when the service is started and each time the configuration is updated in consul (@RefreshScope)
     */
    public void setProp(String content) {
        configurationLoader.updateProperties(content);
    }

}


