package ru.bakhuss.data.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import ru.bakhuss.data.config.props.DataLocation;
import ru.bakhuss.data.config.props.DataProperties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@EnableConfigurationProperties(DataProperties.class)
public class BasicConfigData {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertiesData() {
        PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        Resource[] resources = new ClassPathResource[]{
                new ClassPathResource("data.properties")
        };
        pspc.setLocations(resources);
        pspc.setIgnoreUnresolvablePlaceholders(true);
        return pspc;
    }

    @Bean
    public PathMatchingResourcePatternResolver resourceResolver() {
        return new PathMatchingResourcePatternResolver();
    }


    @Bean
    public List<DataLocation> availableLocationsBaseDir(@Value("${spring.learn.data.base-dir}") String baseDir,
                                                        DataProperties dataProperties,
                                                        PathMatchingResourcePatternResolver resourceResolver) {
        final List<DataLocation> availableLocations = new ArrayList<>(2);
        for (DataLocation location : dataProperties.getOrderLocations()) {
            try {
                Resource[] resources = resourceResolver.getResources(location.getPathPrefix() + baseDir + "/**");
                if (resources.length == 0) {
                    throw new IOException();
                }
                availableLocations.add(location);
            } catch (IOException e) {
                log.error("In location {} not found base dir: {}", location, baseDir);
            }
        }
        return availableLocations;


/*        try {
            Resource[] resources = resourceResolver.getResources(DataLocation.CLASSPATH.getPathPrefix() + baseDir + "/**");
            if (resources.length == 0) {
                throw new IOException();
            }
        } catch (IOException e) {
            log.error("In location CLASSPATH not found base dir: {}", baseDir);
        }
        try {
            Resource[] resources = resourceResolver.getResources(DataLocation.FILE.getPathPrefix() + baseDir + "/**");
            if (resources.length == 0) {
                throw new IOException();
            }
        } catch (IOException e) {
            log.error("In location FILE not found base dir: {}", baseDir);
        }
        return List.of();*/
    }
}
