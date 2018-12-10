package com.ote.test.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ServerRestController {

    @Value("${server.port}")
    private int port;

    @GetMapping("/test")
    public String home() {
        String message = "Hello world from " + port;
        log.warn(message);
        return message;
    }
}
