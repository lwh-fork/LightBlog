package com.lightblog.controller;

import com.lightblog.manager.TokenManager;
import com.lightblog.model.ResultModel;
import com.lightblog.model.Token;
import com.lightblog.model.User;
import com.lightblog.service.UserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Minsghan
 * @Date: Created in 23:14 2017/10/14
 */
@Api(value="token")
@RestController
@RequestMapping("/api/token")
public class TokenController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenManager tokenManager;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value="登录处理", httpMethod="GET", notes="Login", response=ResponseEntity.class)
    private ResponseEntity<ResultModel> login(@RequestParam String userName,
                                              @RequestParam String password) {
        ResultModel result = new ResultModel();

        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            result.setCode(1000);
            result.setMessage("");
            logger.info("UserName or password is empty.");
            return new ResponseEntity<ResultModel>(HttpStatus.BAD_REQUEST);
        }

        User user = userService.findByUserName(userName);

        if (user == null || !password.equals(user.getPassword())) {
            result.setCode(1001);
            result.setMessage("UserName or password is incorrect.");
            logger.info("UserName or password is incorrect.");
            return new ResponseEntity<ResultModel>(HttpStatus.NOT_FOUND);
        }

        Token token = tokenManager.creatToken(user.getId());
        result.setCode(1003);
        result.setMessage("OK!");
        result.setContent(result);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
