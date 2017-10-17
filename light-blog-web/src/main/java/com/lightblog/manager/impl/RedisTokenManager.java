package com.lightblog.manager.impl;

import com.lightblog.config.Constants;
import com.lightblog.manager.TokenManager;
import com.lightblog.model.TokenModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import java.util.UUID;
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
        // 泛型设置成Long后必须更改对应的序列化方案
        redisTemplate.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    @Override
    public TokenModel creatToken(long userId) {
        String token = UUID.randomUUID().toString().replace("-", "");
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

        String token = params[1];
        TokenModel model = new TokenModel(userId, token);
        return model;
    }
}
