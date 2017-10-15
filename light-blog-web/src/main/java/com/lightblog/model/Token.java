package com.lightblog.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Minsghan
 * @Date: Created in 23:17 2017/10/13
 * @Modified By:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Token implements Serializable {
    private static final long serialVersionUID = -4743364335923069447L;

    private long userId;
    private String token;

    public Token() { }

    public Token(long userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}
