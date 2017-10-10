package com.lightblog.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: Minsghan
 * @Date: Created in 21:40 2017/10/10
 * @Modified By:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Category implements Serializable {
    private static final long serialVersionUID = -9060394470347111313L;

    private long id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;
}
