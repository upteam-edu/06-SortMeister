package de.upteams.sortmeister.exception;

public class ContainerValidationException extends RuntimeException {
    
    public ContainerValidationException(String message) {
        super(message);
    }
    
    public ContainerValidationException(String message, Throwable cause) {
        super(message, cause);
    }
} 