package ru.bakhuss.integration.config.props;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Setter
@PropertySource("classpath:integration.properties")
@ConfigurationProperties(prefix = "spring.learn.integration")
public class IntegrationProperties {
    String url;
}
