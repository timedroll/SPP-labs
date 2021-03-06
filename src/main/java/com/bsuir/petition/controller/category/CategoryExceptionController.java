package com.bsuir.petition.controller.category;

import com.bsuir.petition.bean.dto.message.MessageDTO;
import com.bsuir.petition.service.category.exception.CategoryNotFoundException;
import com.bsuir.petition.service.category.exception.SuchCategoryExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = "com.bsuir.petition.controller.category")
public class CategoryExceptionController {

    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public MessageDTO categoryNotFoundExceptionHandler(CategoryNotFoundException exception) {
        return getErrorMessage(exception);
    }

    @ExceptionHandler(SuchCategoryExistsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDTO suchCategoryExistsExceptionHandler(SuchCategoryExistsException exception) {
        return getErrorMessage(exception);
    }

    private MessageDTO getErrorMessage(Exception exception) {
        MessageDTO errorMessage = new MessageDTO();
        errorMessage.setMessage(exception.getMessage());
        return errorMessage;
    }
}
