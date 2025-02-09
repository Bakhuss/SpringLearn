package ru.bakhuss.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bakhuss.data.config.props.DataLocation;
import ru.bakhuss.data.repository.Storage;

import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StorageService {
    private final Storage storage;

    public Set<String> fileNames(DataLocation location) {
        return storage.resolveAllNames(location, "one");
    }

    public <T> Map<String, T> files(DataLocation location, String subDir, Class<T> clazz) {
        return storage.resolveAll(location, subDir, clazz);
    }
}
