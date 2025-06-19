package de.upteams.sortmeister.dto.exception;

public class ContainerAlreadyExistException extends RuntimeException {
    public ContainerAlreadyExistException(String message) {

        super(message);
    }
}
