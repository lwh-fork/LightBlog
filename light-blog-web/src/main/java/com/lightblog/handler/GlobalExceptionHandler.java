package com.lightblog.handler;

import com.lightblog.exception.ParameterException;
import com.lightblog.exception.ServerException;
import com.lightblog.model.ResultModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handels gloabl exception of controller.
 * @Author: Minsghan
 * @Date: Created in 20:58 2017/10/18
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) // #issue 02
    @ResponseBody
    public String handleInvalidRequestError(ParameterException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<ResultModel> handleServiceException(ServerException ex) {
        return new ResponseEntity<ResultModel>(ex.getResult(), ex.getHttpStatus());
    }
}
