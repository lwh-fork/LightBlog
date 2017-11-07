package com.lightblog.service.impl;

import com.lightblog.annotation.RedisCache;
import com.lightblog.annotation.RedisEvict;
import com.lightblog.dao.UserDao;
import com.lightblog.model.User;
import com.lightblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: Minsghan
 * @Date: Created in 16:07 2017/10/3
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @RedisCache(type = User.class)
    public User findById(long id) {
        User user = userDao.selectByPrimaryKey(id);
        return user;
    }

    @Override
    @RedisCache(type = User.class)
    public List<User> findAll() {
        List<User> users = userDao.selectAll();
        return users;
    }

    @Override
    @RedisCache(type = User.class)
    public long insert(User model) {
        long uid = userDao.insert(model);
        return uid;
    }

    @Override
    @RedisCache(type = User.class)
    public long insertSelective(User model) {
        return 0;
    }

    @Override
    @RedisCache(type = User.class)
    public User selectByUniqueFiled(User model) {
        return null;
    }

    @Override
    @RedisCache(type = User.class)
    public long update(User model) {
        return 0;
    }

    @Override
    @RedisEvict(type = User.class)
    public long delete(long id) {
        return 0;
    }

    @Override
    public boolean isUserExist(User user) {
        return false;
    }

    @Override
    @RedisCache(type = User.class)
    public User findByUserName(String userName) {
        User user = userDao.selectByUserName(userName);
        return user;
    }
}
