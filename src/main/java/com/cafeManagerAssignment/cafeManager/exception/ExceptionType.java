package com.cafeManagerAssignment.cafeManager.exception;

public enum ExceptionType {
    ENTITY_NOT_FOUND("entity not found"),

    DUPLICATE_ENTITY("entity duplicate"),

    DUPLICATE_USER("duplicate user");

    String value;

    ExceptionType(String value) {
        this.value = value;
    }
}
