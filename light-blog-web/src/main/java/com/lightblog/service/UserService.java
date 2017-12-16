package com.lightblog.service;

import com.common.service.BaseService;
import com.lightblog.model.User;

/**
 * @Description:
 * @Author: mingshan
 * @Date: Created in 16:11 2017/10/3
 */
public interface UserService extends BaseService<User> {
    /**
     * Verifys current user is exist.
     * @param user
     * @return
     */
    boolean isUserExist(User user);

    /**
     * Finds user by userName.
     * @param userName
     * @return The modle of user.
     */
    User findByUserName(String userName);
}
