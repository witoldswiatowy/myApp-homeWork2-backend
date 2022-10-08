package com.project.spring.exception;

/**
 * Generic business exception indicating that some params in methode could be incorrect.
 */
public class ParamsIsIncorrectException extends BusinessException{

    public ParamsIsIncorrectException(String message) {
        super(message);
    }
}
