package com.ote.test.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FabioClientService {

    @Autowired
    private RestOperations restOperations;

    @Scheduled(fixedRate = 100)
    public void scheduled() throws Exception{
        log.warn(test());
    }

    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public String test() throws Exception {
            String fabioUrl = "http://localhost:9999";
            return restOperations.getForObject(fabioUrl + "/server/test", String.class);
    }

    @Recover
    public String recover(Exception e) {
        log.error("FABIO - Definitive Error", e);
        return "exception " + e.getClass().getSimpleName() + "occured : " + e.getMessage();
    }
}
