package com.dvbispo.personalbudget.service.exception;

import java.io.Serializable;

public class NullObject extends RuntimeException implements Serializable {

    private static final long SerialVersionUID = 1L;

    public NullObject(String message){
        super(message);
    }
}
