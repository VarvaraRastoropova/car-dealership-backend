package ru.rastoropova.integration_layer.exceptions;

public class StorageServiceUnavailableException extends RuntimeException {
    public StorageServiceUnavailableException(String message) {
        super(message);
    }
}
