package com.common.dao;

import com.lightblog.model.User;

import java.util.List;

/**
 * @Description:
 * @Author: Minsghan
 * @Date: Created in 9:20 2017/10/21
 */
public interface BaseDao<T> {
    /**
     * Deletes model by id.
     * @param id
     * @return
     */
    long deleteByPrimaryKey(long id);

    /**
     * Inserts model to database.
     * @param model
     * @return
     */
    long insert(T model);

    /**
     * Selects model by id.
     * @param id
     * @return
     */
    User selectByPrimaryKey(long id);

    /**
     * Update model information.
     * @param model
     * @return
     */
    long update(T model);

    /**
     *
     * @return
     */
    List<User> selectAll();
}
