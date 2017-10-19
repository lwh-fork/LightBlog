package com.lightblog.handler;

import com.lightblog.exception.ParameterException;
import com.lightblog.exception.ServerException;
import com.lightblog.model.ResultModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Description:
 * @Author: Minsghan
 * @Date: Created in 20:58 2017/10/18
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) // #issue 2
    @ResponseBody
    public String handleInvalidRequestError(ParameterException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ServerException.class)
    @ResponseStatus(value = HttpStatus.NO_CONTENT) // #issue 2
    @ResponseBody
    public ResultModel handleServiceException(ServerException ex) {
        return ex.getResult();
    }
}
