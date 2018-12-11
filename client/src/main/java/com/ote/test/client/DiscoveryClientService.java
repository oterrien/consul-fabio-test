package com.ote.test.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DiscoveryClientService {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestOperations restOperations;

    // @Scheduled(fixedRate = 5000)
    public void test() {
        String serviceName = "server";
        Optional<String> serviceUrl = serviceUrl(serviceName);
        if (serviceUrl.isPresent()) {
            String result = restOperations.getForObject(serviceUrl.get() + "/test", String.class);
            log.warn("DISCOVERY - Service returns {}", result);
        } else {
            log.error("DISCOVERY - Service not found", serviceName);
        }
    }

    private Optional<String> serviceUrl(String serviceName) {
        List<ServiceInstance> list = discoveryClient.getInstances(serviceName);
        if (list != null && list.size() > 0) {
            return Optional.of(list.get(0).getUri().toString());
        }
        return Optional.empty();
    }
}
