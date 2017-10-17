package com.lightblog.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * The DTO of user.
 * @Author: Minsghan
 * @Date: Created in 23:20 2017/10/17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -5970277945472308304L;

    private long id;
    private String name;
    private String password;
}
