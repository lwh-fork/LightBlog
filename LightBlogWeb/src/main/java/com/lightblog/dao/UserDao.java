package com.lightblog.dao;

import java.util.List;

import com.lightblog.model.User;


public interface UserDao {
    //根据主键删除
    long deleteByPrimaryKey(Long id);

    //插入
    long insert(User model);

    //根据主键查询
    User selectByPrimaryKey(Long id);

    //更新
    long update(User model);

    //查询全部
    List<User> selectAll();
}
