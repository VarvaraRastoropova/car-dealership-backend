package ru.rastoropova.business_logic.exceptions;

public class DomainValidationException extends DomainException {
    public DomainValidationException(String message) {
        super(message);
    }
}
