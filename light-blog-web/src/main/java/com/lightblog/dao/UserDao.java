package com.lightblog.dao;

import com.lightblog.model.User;

import java.util.List;


public interface UserDao {
    //根据主键删除
    long deleteByPrimaryKey(long id);

    //插入
    long insert(User model);

    //根据主键查询
    User selectByPrimaryKey(long id);

    //更新
    long update(User model);

    //查询全部
    List<User> selectAll();
}
