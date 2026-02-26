package ru.rastoropova.business_logic.exceptions;

public class IncompatibleComponentException extends DomainException {
    public IncompatibleComponentException(String message) {
        super(message);
    }
}
