package com.lightblog.manager;

import com.lightblog.model.Token;

/**
 * @Description: The manager of token with Redis.
 * @Author: Minsghan
 * @Date: Created in 23:22 2017/10/13
 * @Modified By:
 */
public interface TokenManager {

    /**
     * Creates the token of authorization.
     * @param userId
     * @return The model of Token.
     */
    Token creatToken(long userId);

    /**
     * Deteles the token of authorization.
     * @param userId
     */
    void deleteToken(long userId);

    /**
     * Checks out the token that is from Redis.
     * @param token
     * @return
     */
    boolean checkToken(Token token);

    /**
     * Gets the model of Token from authorization string.
     * @param authorization
     * @return The model of Token.
     */
    Token getToken(String authorization);
}
