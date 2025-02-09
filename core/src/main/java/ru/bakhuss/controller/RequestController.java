package ru.bakhuss.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bakhuss.data.config.props.DataLocation;
import ru.bakhuss.service.StorageService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RequestController {
    private final StorageService service;

    @GetMapping(path = "/names")
    public List<String> allNames(@RequestParam String location) {
        return service.fileNames(DataLocation.valueOf(location.toUpperCase()))
                .stream()
                .toList();
    }

    @GetMapping(path = "/all")
    public Map<String, Object> allFiles(@RequestParam String location) {
        return service.files(DataLocation.valueOf(location.toUpperCase()), "one", Object.class);
    }
}
