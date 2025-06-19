package de.upteams.sortmeister.exception.handling;

import de.upteams.sortmeister.dto.exception.ContainerAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ContainerAlreadyExistException.class)
    public ResponseEntity<String> handleException(ContainerAlreadyExistException exception) {
     return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
}
