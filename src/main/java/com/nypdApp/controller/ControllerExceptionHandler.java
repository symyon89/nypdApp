package com.nypdApp.controller;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Comparator;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Value
    private static class FieldErrorJson implements Comparable<FieldErrorJson> {

        String fieldName;
        String message;
        String messageCode;

        private static final Comparator<String> nullSafeStringComparator = Comparator.nullsFirst(String::compareToIgnoreCase);

        // keep them ordered for predictability
        @Override
        public final int compareTo(FieldErrorJson o) {
            int compareByField = nullSafeStringComparator.compare(fieldName, o.fieldName);
            if (compareByField != 0) return compareByField;
            int compareByMessage = nullSafeStringComparator.compare(message, o.message);
            if (compareByMessage != 0) return compareByMessage;
            return nullSafeStringComparator.compare(messageCode, o.messageCode);
        }
    }

    /**
     * override how validation constraints in REST layer are handled
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (e.getFieldErrors().isEmpty()) {
            return super.handleMethodArgumentNotValid(e, headers, status, request);
        }

        // can log errors here and not in the application
        log.error("validation error", e);

        return new ResponseEntity<>(
                e.getFieldErrors()
                        .stream()
                        .map(fieldError -> new FieldErrorJson(fieldError.getField(), fieldError.getDefaultMessage(), fieldError.getCode()))
                        .sorted()// keep them ordered for predictability
                        .toList(),
                HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {

        // can log errors here and not in the application
        log.error("validation error", e);

        return new ResponseEntity<>(
                e.getConstraintViolations().stream()
                        .map(fieldError -> new FieldErrorJson(fieldError.getPropertyPath().toString(), fieldError.getMessage(), fieldError.getMessageTemplate()))
                        .sorted()// keep them ordered for predictability
                        .toList(),
                HttpStatus.BAD_REQUEST);
    }
}
