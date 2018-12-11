package com.ote.test.server;

import com.ote.test.server.configuration.Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ServerRestController {

    @Autowired
    private Properties properties;

    @Value("${server.port}")
    private int port;

    @GetMapping("/test")
    public String home() {
        String message = "Message from " + port + ": " + this.properties.getMessage() + ", " + this.properties.getSub().getPort();
        log.warn(message);
        return message;
    }
}
