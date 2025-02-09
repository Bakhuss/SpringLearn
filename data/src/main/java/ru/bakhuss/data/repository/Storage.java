package ru.bakhuss.data.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import ru.bakhuss.data.config.props.DataLocation;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@Slf4j
@Configuration
@RequiredArgsConstructor
public class Storage {
    @Value("${spring.learn.data.base-dir}")
    private String baseDir;
    private String path;

    private final PathMatchingResourcePatternResolver resourceResolver;
    private final List<DataLocation> availableLocationsBaseDir;
    private final ObjectMapper objectMapper;


    public Set<String> resolveAllNames(DataLocation dataLocation, String subDir) {
        return resolveAll(dataLocation, subDir, Object.class).keySet();
    }

    public <T> Map<String, T> resolveAll(DataLocation dataLocation, String subDir, Class<T> clazz) {
        return resolveFromLocation(dataLocation, subDir, clazz);
    }

    public <T> Map<String, T> resolveFromLocation(DataLocation location, String subDir, Class<T> clazz) {
        log.debug(location.getPathPrefix());
        String locationString = location.getPathPrefix() + baseDir + File.separator + subDir + "/*";
        Map<String, T> objectsFromLocation = new HashMap<>();
        try {
            Resource[] resources = resourceResolver.getResources(locationString);
            int length = resources.length;
            for (Resource r : resources) {
                T t = objectMapper.readValue(r.getContentAsByteArray(), clazz);
                objectsFromLocation.put(r.getFilename(), t);
            }
        } catch (IOException e) {
            log.error("{}", e.getMessage());
        }
        return objectsFromLocation;
    }
}
