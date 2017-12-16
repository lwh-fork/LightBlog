package com.lightblog.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: mingshan
 * @Date: Created in 21:35 2017/10/10
 * @Modified By:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Article implements Serializable{
    private static final long serialVersionUID = -7257614789020321323L;

    private long id;
    private String title;
    private String content;
    private long authorId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;
}
