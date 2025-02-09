package ru.bakhuss.data.config.props;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataLocation {
    CLASSPATH("classpath:"),
    FILE("file:");

    private final String pathPrefix;
}
