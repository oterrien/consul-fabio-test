package com.ote.test.server.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Scanner;

/**
 * Create Key/Values in config/server/my/prop
 * <p>
 * config for configuration
 * server for the name of the service in consul
 * my/prop
 */
@ConditionalOnProperty(value = "configuration.loader.type", havingValue = "LOCAL", matchIfMissing = true)
@Configuration
@Slf4j
public class LoaderConfigurationLoader {

    @Value("${configuration.loader.local.file}")
    private String configFileName;

    @Autowired
    private ConfigurationLoader configurationLoader;

    @PostConstruct
    public void init() {
        try (Scanner scanner = new Scanner(this.getClass().getClassLoader().getResourceAsStream(configFileName), "UTF-8")) {
            String content = scanner.useDelimiter("\\A").next();
            configurationLoader.updateProperties(content);
        }
    }
}

