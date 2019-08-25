package com.schoolcourses.app.exception;

/**
 * Wrapper of RuntimeException to personalize message
 */
public class TechnicalException extends RuntimeException{

    public TechnicalException(String message){
         super(message);
    }

}
