package com.github.ahisyfa.saad.saad.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * @author Ahmad Isyfalana Amin
 * @version $Id: ExceptionController.java, v 0.1 2023-07-16  15.56 Ahmad Isyfalana Amin Exp $
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler ({org.hibernate.exception.ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleConstraintViolationException(org.hibernate.exception.ConstraintViolationException exception,
                                                   ServletWebRequest webRequest) throws IOException {
        webRequest
                .getResponse()
                .sendError(HttpStatus.BAD_REQUEST.value(), "Hello coy");
    }

}