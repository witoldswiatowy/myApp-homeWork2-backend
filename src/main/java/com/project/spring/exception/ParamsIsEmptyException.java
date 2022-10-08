package com.project.spring.exception;

/**
 * Generic business exception indicating that some params in methode could be empty.
 */
public class ParamsIsEmptyException extends BusinessException {

    public ParamsIsEmptyException(String message) {
        super(message);
    }
}
