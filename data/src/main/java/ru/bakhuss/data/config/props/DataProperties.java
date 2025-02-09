package ru.bakhuss.data.config.props;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Getter
@AllArgsConstructor
@PropertySource("classpath:data.properties")
@ConfigurationProperties(prefix = "spring.learn.data")
public class DataProperties {
    String baseDir;
    List<DataLocation> orderLocations;
}
