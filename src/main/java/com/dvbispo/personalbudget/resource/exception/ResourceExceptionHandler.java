package com.dvbispo.personalbudget.resource.exception;

import com.dvbispo.personalbudget.service.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        String timeStamp = new Timestamp(System.currentTimeMillis()).toString();

        StandardError error = new StandardError(timeStamp, status.value(), "Not found", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }
}
