package com.lightblog.service.impl;

import com.lightblog.model.Comment;
import com.lightblog.service.CommentService;

import java.util.List;

/**
 * @Description:
 * @Author: Minsghan
 * @Date: Created in 23:06 2017/10/19
 */
public class CommentServiceImpl implements CommentService {
    @Override
    public Comment findById(long id) {
        return null;
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public long insert(Comment model) {
        return 0;
    }

    @Override
    public long insertSelective(Comment model) {
        return 0;
    }

    @Override
    public Comment selectByUniqueFiled(Comment model) {
        return null;
    }

    @Override
    public long update(Comment model) {
        return 0;
    }

    @Override
    public long delete(long id) {
        return 0;
    }
}
