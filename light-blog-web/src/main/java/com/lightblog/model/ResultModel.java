package com.lightblog.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Minsghan
 * @Date: Created in 20:02 2017/10/14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ResultModel implements Serializable {
    private static final long serialVersionUID = -3161231835832768735L;

    private long code;
    private String message;
    private Object content;

    public ResultModel() {}

    public ResultModel(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultModel(long code, String message, Object content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }
}
