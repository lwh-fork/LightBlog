package com.lightblog.manager.impl;

import com.lightblog.config.Constants;
import com.lightblog.manager.TokenManager;
import com.lightblog.model.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description: The implement class of Token manager.
 * @Author: Minsghan
 * @Date: Created in 23:41 2017/10/13
 * @Modified By:
 */
public class RedisTokenManager implements TokenManager {
    private static final Logger logger = LoggerFactory.getLogger(RedisTokenManager.class);
    private RedisTemplate redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Token creatToken(long userId) {
        String tokenStr = UUID.randomUUID().toString().replace("_", "");
        Token token = new Token(userId, tokenStr);
        redisTemplate.boundValueOps (userId).set (token, Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return token;
    }

    @Override
    public void deleteToken(long userId) {
        redisTemplate.delete(userId);
    }

    @Override
    public boolean checkToken(Token token) {
        if (token == null) {
            return false;
        }

        Object source = redisTemplate.boundValueOps (token.getUserId()).get();
        if (source == null) {
            return false;
        }

        String tokenStr = source.toString();

        if ("".equals(tokenStr) || !tokenStr.equals(token.getToken())) {
            return false;
        }

        redisTemplate.boundValueOps(token.getUserId()).expire(Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return false;
    }

    @Override
    public Token getToken(String authorization) {
        if (authorization == null || "".equals(authorization)) {
            return null;
        }

        String[] params = authorization.split("_");
        if (params.length != 2) {
            return null;
        }

        long userId;
        try {
            userId = Long.parseLong(params[0]);
        } catch (NumberFormatException e) {
            logger.error("RedisTokenManager occurs error : The userId of authorization string is not number!" );
            return null;
        }

        String tokenStr = params[1];
        Token token = new Token(userId, tokenStr);
        return token;
    }
}
