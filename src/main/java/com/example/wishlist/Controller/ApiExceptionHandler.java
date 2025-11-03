package com.example.wishlist.Controller;

import com.example.wishlist.exception.ApiException;
import com.example.wishlist.exception.ApiRequestException;
import com.example.wishlist.exception.DatabaseOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        // create payload containing exception details
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(e.getMessage(), e, badRequest, ZonedDateTime.now(ZoneId.of("Z")));
        // Return response entity
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler({DatabaseOperationException.class})
    public String handleGeneric(Exception ex, Model model) {
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("error", "Internal Server Error");
        model.addAttribute("message", "Something went wrong. Please try again later.");
        return "error/500";
    }
}

