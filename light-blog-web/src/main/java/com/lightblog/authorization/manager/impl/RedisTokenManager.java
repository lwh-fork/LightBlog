package com.lightblog.authorization.manager.impl;

import com.lightblog.authorization.check.RequestCheck;
import com.lightblog.authorization.manager.TokenManager;
import com.lightblog.config.Constants;
import com.lightblog.model.TokenModel;
import com.lightblog.model.User;
import com.lightblog.util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * The implement class of Token manager.
 * @Author: Minsghan
 * @Date: Created in 23:41 2017/10/13
 */
public class RedisTokenManager implements TokenManager {
    private static final Logger logger = LoggerFactory.getLogger(RedisTokenManager.class);
    private RedisTemplate<Long, String> redisTemplate;

    public void setRedisTemplate(RedisTemplate<Long, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public TokenModel creatToken(long userId) {
        User user = new User();
        user.setId(userId);
        String subject = JWTUtil.generalSubject(user);
        String token = JWTUtil.createJWT(userId, subject, Constants.JWT_TTL);
        TokenModel model = new TokenModel(userId, token);
        redisTemplate.boundValueOps(userId).set(token, Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return model;
    }

    @Override
    public void deleteToken(long userId) {
        redisTemplate.delete(userId);
    }

    @Override
    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        Object source = redisTemplate.boundValueOps(model.getUserId()).get();
        if (source == null) {
            return false;
        }

        String token = source.toString();
        if ("".equals(token) || !token.equals(model.getToken())) {
            return false;
        }

        redisTemplate.boundValueOps(model.getUserId()).expire(Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return true;
    }

    @Override
    public TokenModel getToken(String authorization) {
        if (authorization == null || "".equals(authorization)) {
            return null;
        }

        User user = RequestCheck.getUserFromToken(authorization);
        if (user == null) {
            return null;
        }

        long userId = user.getId();

        String token = RequestCheck.extractJwtTokenFromAuthorizationHeader(authorization);
        TokenModel model = new TokenModel(userId, token);
        return model;
    }
}
