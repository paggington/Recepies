package com.example.recepies.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class ErrorsHandling implements ErrorController {
    @GetMapping
    public String errorHandling(HttpServletRequest httpServletRequest){
        Object status = httpServletRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "errorsHandling/error-404";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "errorsHandling/error-500";
            }else if(statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()){
                return "errorsHandling/error-404";
            }
        }
        return "errorsHandling/error-404";
    }
}
