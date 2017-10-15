package com.lightblog.service;

import com.lightblog.model.User;

/**
 * @Description:
 * @Author: Minsghan
 * @Date: Created in 16:11 2017/10/3
 */
public interface UserService extends BaseService<User> {
    boolean isUserExist(User user);

    User findByUserName(String userName);
}
