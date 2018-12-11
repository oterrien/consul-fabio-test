package com.ote.test.server.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.ote.test.server.ApplicationContextProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ConfigurationLoader {

    private static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    @Bean
    public Properties properties() {
        return new Properties();
    }

    public void updateProperties(String content) {
        try {
            content = StringUtils.replace(content, "\t", "  ");
            ApplicationContextProvider.getContext().getBean(Properties.class).
                    update(mapper.readValue(content, Properties.class));
        } catch (Exception e) {
            log.error("Unable to parse configuration", e);
        }
    }

}
