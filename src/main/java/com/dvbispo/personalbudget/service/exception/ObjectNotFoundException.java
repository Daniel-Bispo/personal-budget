package com.dvbispo.personalbudget.service.exception;

import java.io.Serializable;

public class ObjectNotFoundException extends RuntimeException implements Serializable {

    private static final long SerialVersionUID = 1L;

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
