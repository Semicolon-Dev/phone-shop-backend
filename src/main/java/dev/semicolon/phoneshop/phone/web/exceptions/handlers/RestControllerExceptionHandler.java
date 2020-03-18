package dev.semicolon.phoneshop.phone.web.exceptions.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

@Slf4j
@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public void handleValidationException(ConstraintViolationException ex,
                                          HttpServletResponse response) throws IOException {

        log.warn(ex.getMessage());

        String[] messageParts = ex.getMessage().split("\\.");
        String message = messageParts[messageParts.length -1];

        response.sendError(HttpStatus.BAD_REQUEST.value(), message);
    }

}
