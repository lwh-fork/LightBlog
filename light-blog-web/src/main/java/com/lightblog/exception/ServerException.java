package com.lightblog.exception;

import com.lightblog.model.ResultModel;

/**
 * The exception of server.
 * @Author: Minsghan
 * @Date: Created in 20:11 2017/10/8
 * @Modified By:
 */
public class ServerException extends RuntimeException {
    private static final long serialVersionUID = -8183259784734482522L;
    private ResultModel result;

    public ServerException() { }

    public ServerException(ResultModel result) {
        this.result = result;
    }

    public ResultModel getResult() {
        return this.result;
    }
}
