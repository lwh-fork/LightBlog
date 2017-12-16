package com.lightblog.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: mingshan
 * @Date: Created in 21:55 2017/10/10
 * @Modified By:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Comment implements Serializable{
    private static final long serialVersionUID = -594632857588593363L;

    private long id;
    private String userName;
    private String email;
    private String siteUrl;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;
}
