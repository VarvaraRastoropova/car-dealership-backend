package ru.rastoropova.service_layer.exceptions;

public class DomainValidationException extends DomainException {
    public DomainValidationException(String message) {
        super(message);
    }
}
