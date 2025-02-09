package ru.bakhuss.data.repository;

import java.util.List;

public interface StorageInterface<T> {
    List<T> resolveAll();
    List<String> resolveAllNames();
}
