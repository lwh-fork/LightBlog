package com.lightblog.dao;

import com.lightblog.model.User;

import java.util.List;

/**
 * @Description:
 * @Author: Minsghan
 * @Date: Created in 21:49 2017/10/10
 * @Modified By:
 */
public interface BaseDao<T> {
    //根据主键删除
    long deleteByPrimaryKey(long id);

    //插入
    long insert(T model);

    //根据主键查询
    User selectByPrimaryKey(long id);

    //更新
    long update(T model);

    //查询全部
    List<User> selectAll();
}
