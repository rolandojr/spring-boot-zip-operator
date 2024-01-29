package com.example.poc.zipoperator.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.application")
public class ApplicationProperties {

    private Map<String, String> extraFields;

}
