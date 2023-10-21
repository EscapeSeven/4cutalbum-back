package com.pyo.cutalbum.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class SecurityConfigProperties {


    @Value("${spring.security.origin}")
    public String ORIGIN;

    @Value("${spring.security.origin2}")
    public String ORIGIN2;
}
