package com.lightblog.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: Minsghan
 * @Date: Created in 21:41 2017/10/10
 * @Modified By:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Tag implements Serializable {
    private static final long serialVersionUID = -8746602257667025220L;
    
    private long id;
    private String name;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;
}
