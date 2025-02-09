package ru.bakhuss.integration.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.bakhuss.integration.config.props.IntegrationProperties;

@Slf4j
@Configuration
@EnableConfigurationProperties(IntegrationProperties.class)
public class BasicConfigIntegration {
}
