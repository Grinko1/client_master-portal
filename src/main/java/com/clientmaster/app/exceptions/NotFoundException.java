package com.clientmaster.app.exceptions;

public class NotFoundException extends RuntimeException{
    String resourceName;
    String fieldName;
    Integer fieldValue;

    public NotFoundException( String resourceName, String fieldName, Integer fieldValue) {
        super(String.format("%s with %s: %d wasn't found", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
