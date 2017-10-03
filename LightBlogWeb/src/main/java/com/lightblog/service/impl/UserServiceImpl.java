package com.lightblog.service.impl;

import com.lightblog.model.User;
import com.lightblog.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: Minsghan
 * @Date: Created in 16:07 2017/10/3
 * @Modified By:
 */
@Service
public class UserServiceImpl implements UserService {


    @Override
    public User findById(long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public long insert(User model) {
        return 0;
    }

    @Override
    public long insertSelective(User model) {
        return 0;
    }

    @Override
    public User selectByUniqueFiled(User model) {
        return null;
    }

    @Override
    public long update(User model) {
        return 0;
    }

    @Override
    public long delete(long id) {
        return 0;
    }

    @Override
    public boolean isUserExist(User user) {
        return false;
    }
}
