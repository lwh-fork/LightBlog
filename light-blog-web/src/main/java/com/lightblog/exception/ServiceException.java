package com.lightblog.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description:
 * @Author: Minsghan
 * @Date: Created in 20:11 2017/10/8
 * @Modified By:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceException {
    private int code;
    private String message;

    public ServiceException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
