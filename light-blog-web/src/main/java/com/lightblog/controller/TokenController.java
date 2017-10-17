package com.lightblog.controller;

import com.lightblog.annotation.Authorization;
import com.lightblog.annotation.CurrentUser;
import com.lightblog.config.Constants;
import com.lightblog.manager.TokenManager;
import com.lightblog.model.ResultModel;
import com.lightblog.model.TokenModel;
import com.lightblog.model.User;
import com.lightblog.service.UserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * The API of token for login and logout.
 * @Author: Minsghan
 * @Date: Created in 23:14 2017/10/14
 */
@Api(value = "tokens")
@RestController
@RequestMapping("/api/tokens")
public class TokenController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenManager tokenManager;

    /**
     * Login.
     * @param userName
     * @param password
     * @return ResultModel
     */
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value="登录处理", httpMethod="POST", notes="Login")
    public ResponseEntity<ResultModel> login(@RequestParam String userName,
                                             @RequestParam String password) {
        ResultModel result = new ResultModel();

        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            result.setSuccess(false);
            result.setCode(1000);
            result.setMessage("UserName or password is empty.");
            logger.info("UserName or password is empty.");
            return new ResponseEntity<ResultModel>(HttpStatus.BAD_REQUEST);
        }

        User user = userService.findByUserName(userName);

        if (user == null || !password.equals(user.getPassword())) {
            result.setSuccess(false);
            result.setCode(1001);
            result.setMessage("UserName or password is incorrect.");
            logger.info("UserName or password is incorrect.");
            return new ResponseEntity<ResultModel>(result, HttpStatus.NOT_FOUND);
        }

        TokenModel token = tokenManager.creatToken(user.getId());
        result.setSuccess(true);
        result.setMessage(Constants.RESPONSE_OK);
        result.setContent(token);
        return new ResponseEntity<ResultModel>(result, HttpStatus.OK);
    }

    /**
     * Logout
     * @param user
     * @return ResultModel
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @Authorization
    @ApiOperation(value="登出处理", httpMethod="DELETE", notes="Logout")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "String",
                    paramType = "header")
    })
    public ResponseEntity<ResultModel> logout(@CurrentUser User user) {
        tokenManager.deleteToken(user.getId());
        ResultModel result = new ResultModel();
        result.setSuccess(true);
        result.setMessage(Constants.RESPONSE_OK);
        return new ResponseEntity<ResultModel>(result, HttpStatus.OK);
    }
}
