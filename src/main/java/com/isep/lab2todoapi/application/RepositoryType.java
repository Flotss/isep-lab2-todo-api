package com.isep.lab2todoapi.application;

public enum RepositoryType {
    INMEMORY("INMEMORY"),
    CSV("CSV");

    private final String value;

    RepositoryType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}