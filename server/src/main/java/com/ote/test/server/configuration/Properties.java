package com.ote.test.server.configuration;

import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class Properties {

    private String message;

    private Sub sub;

    @Data
    public static class Sub {
        private int port;
    }

    public void update(Properties updated) {
        BeanUtils.copyProperties(updated, this);
    }
}