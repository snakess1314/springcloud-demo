package com.wzq.springcloudcardprovider.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandle {
    //捕获@Valid校验不通过的异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        BindingResult ex = e.getBindingResult();
        List<ObjectError> allErrors = ex.getAllErrors();
        ObjectError error = allErrors.get(0);
        String defaultMessage = error.getDefaultMessage();


        return e.getMessage();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public String constraintViolationExceptionHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        List<String> collect = constraintViolations.stream()
                .map(o -> o.getMessage())
                .collect(Collectors.toList());
        return collect.toString();
    }

    @ExceptionHandler(BindException.class)
    public String exception(BindException e) {
        String message = e.getMessage();
        String s = e.getFieldError().getDefaultMessage();
        return s;
    }


}
