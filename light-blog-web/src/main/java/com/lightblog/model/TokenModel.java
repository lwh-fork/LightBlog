package com.lightblog.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author: Minsghan
 * @Date: Created in 23:17 2017/10/13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TokenModel implements Serializable {
    private static final long serialVersionUID = -4743364335923069447L;

    private long userId;
    private String token;

    public TokenModel() { }

    public TokenModel(long userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}
