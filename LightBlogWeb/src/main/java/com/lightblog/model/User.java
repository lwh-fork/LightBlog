package com.lightblog.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class User {
    private int id;
    private String name;
    private int age;
    private String password;
}
