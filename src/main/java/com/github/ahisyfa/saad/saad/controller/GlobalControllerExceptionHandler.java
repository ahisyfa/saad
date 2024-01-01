package com.github.ahisyfa.saad.saad.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;



/**
 * GlobalControllerExceptionHandler
 *
 * Please see: https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: GlobalControllerExceptionHandler.java, v 0.1 2023-07-23  00.17 Ahmad Isyfalana Amin Exp $
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "errorPage";

//    @ResponseStatus(HttpStatus.CONFLICT)  // 409
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public void handleConflict() {
//        // Nothing to do
//    }

    @ExceptionHandler(value = {
            Exception.class,
            DataIntegrityViolationException.class
    })
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - like the OrderNotFoundException example
        // at the start of this post.
        // AnnotationUtils is a Spring Framework utility class.
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        // Otherwise setup and send the user to a default error-view.
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e.getMessage());
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    @ModelAttribute("servletPath")
    String getRequestServletPath(HttpServletRequest request) {
        return request.getServletPath();
    }


}