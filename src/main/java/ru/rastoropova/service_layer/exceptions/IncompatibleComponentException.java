package ru.rastoropova.service_layer.exceptions;

public class IncompatibleComponentException extends DomainException {
    public IncompatibleComponentException(String message) {
        super(message);
    }
}
