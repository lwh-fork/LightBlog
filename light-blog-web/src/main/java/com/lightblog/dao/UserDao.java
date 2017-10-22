package com.lightblog.dao;

import com.common.dao.BaseDao;
import com.lightblog.model.User;

/**
 *
 */
public interface UserDao extends BaseDao<User> {
    User selectByUserName(String userName);
}
