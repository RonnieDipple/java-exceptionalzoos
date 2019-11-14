package com.rdipple.javazoosassignment.exceptions;

import com.rdipple.javazoosassignment.controllers.AnimalController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceFoundException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(ResourceFoundException.class);


    public ResourceFoundException(String message) {

        super(message);
        logger.info("ResourceFoundException accessed");
    }

    public ResourceFoundException(String message, Throwable cause) {
        super(message, cause);
        logger.info("ResourceFoundException accessed");
    }
}
