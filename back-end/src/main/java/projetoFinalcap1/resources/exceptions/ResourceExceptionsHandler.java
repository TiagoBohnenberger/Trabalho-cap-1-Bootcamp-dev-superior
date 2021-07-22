package projetoFinalcap1.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import projetoFinalcap1.services.exceptions.DatabaseException;
import projetoFinalcap1.services.exceptions.ResourceNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionsHandler {

    private static final String RESOURCE_ERROR_MESSAGE = "Erro de recurso";

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        StandardError standardError = new StandardError();
        HttpStatus status = HttpStatus.NOT_FOUND;

        standardError.setStatusCode(status.value());
        standardError.setTimestamp(Instant.now());
        standardError.setError(RESOURCE_ERROR_MESSAGE);
        standardError.setMessage(e.getMessage());
        standardError.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }
}