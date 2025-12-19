package org.example.teahouse.core.error;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    private final String name;

    public ResourceNotFoundException(String name) {
        super("Resource not found: " + name);
        this.name = name;
    }
}
