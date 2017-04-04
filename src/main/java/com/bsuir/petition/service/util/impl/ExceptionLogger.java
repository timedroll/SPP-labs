package com.bsuir.petition.service.util.impl;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionLogger {

    @AfterThrowing(pointcut = "execution(* com.bsuir.petition.service.UserService.*(..))", throwing = "exception")
    public void userDaoExceptionHandler(Exception exception) {
        exception.printStackTrace();
    }
}
