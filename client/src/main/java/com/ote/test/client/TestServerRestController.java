package com.ote.test.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestServerRestController {

    @Autowired
    private FabioClientService clientService;

    @GetMapping("/test")
    public String test() throws Exception {
        return clientService.test();
    }
}
