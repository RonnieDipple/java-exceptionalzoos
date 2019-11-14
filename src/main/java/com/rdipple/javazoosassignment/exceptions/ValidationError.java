package com.rdipple.javazoosassignment.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationError {
    private String code;
    private String message;
    private static final Logger logger = LoggerFactory.getLogger(ValidationError.class);


    public String getCode() {
        logger.info("ValidationError getCode accessed");
        return code;
    }

    public void setCode(String code) {
        logger.info("ValidationError setCode accessed");
        this.code = code;
    }

    public String getMessage() {
        logger.info("ValidationError getMessage accessed");
        return message;
    }

    public void setMessage(String message) {
        logger.info("ValidationError setMessage accessed");
        this.message = message;
    }
}
