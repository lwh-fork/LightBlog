package com.lightblog.controller;

import com.lightblog.config.Constants;
import com.lightblog.model.ResultModel;
import com.lightblog.model.User;
import com.lightblog.service.UserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * The api of user.
 * @Author: Minsghan
 * @Date: Created in 15:27 2017/10/3
 * @Modified By:
 */
@Api(value="users")
@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    ResultModel result = new ResultModel();

    /**
     * Gets list of user.
     * @param:  null
     * @Date: 15:16 2017/10/3 
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value="获取所有用户信息", httpMethod="GET", notes="Get users")
    public ResponseEntity<ResultModel> listAllUsers() {
        List<User> users = userService.findAll();
        if(users.isEmpty()){
            // You many decide to return HttpStatus.NOT_FOUND
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        result.setSuccess(true);
        result.setMessage(Constants.RESPONSE_OK);
        result.setContent(users);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Gets information of user by id.
     * @param:  * @param id
     * @Date: 15:34 2017/10/3 
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="获取用户信息", httpMethod="GET", notes="Get user by id")
    public ResponseEntity<ResultModel> getUser(@ApiParam(required=true,value="用户ID",name="id")
                                               @PathVariable("id") long id) {
        logger.info("Fetching User with id " + id);
        User user = userService.findById(id);
        if (user == null) {
            result.setSuccess(false);
            result.setCode(1002);
            result.setContent("User with id " + id + " not found");
            logger.info("User with id " + id + " not found");
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        result.setSuccess(true);
        result.setMessage(Constants.RESPONSE_OK);
        result.setContent(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Creates a user.
     * @param:  * @param null
     * @Date: 15:34 2017/10/3 
     */
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value="新增用户", httpMethod="POST", notes="Create user")
    public ResponseEntity<ResultModel> createUser(@ApiParam(required=true,value="用户信息",name="User")
                                                  @RequestBody User user, UriComponentsBuilder ucBuilder) {
        logger.info("Creating User " + user.getName());

        if (userService.isUserExist(user)) {
            result.setSuccess(false);
            result.setCode(1003);
            result.setContent("A User with name " + user.getName() + " already exist");
            logger.info("A User with name " + user.getName() + " already exist");
            return new ResponseEntity<>(result, HttpStatus.CONFLICT);
        }

        userService.insert(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        result.setSuccess(true);
        result.setMessage(Constants.RESPONSE_OK);
        result.setContent(user);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
    
    /**
     * Updates a user.
     * @param:  * @param null
     * @Date: 15:33 2017/10/3 
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value="更新用户信息", httpMethod="PUT", notes="Update user")
    public ResponseEntity<ResultModel> updateUser(@RequestBody User user) {
        logger.info("Updating User " + user);

        User currentUser = userService.findById(user.getId());

        if (currentUser == null) {
            result.setSuccess(false);
            result.setCode(1004);
            result.setContent("User with id " + user.getId() + " not found");
            logger.info("User with id " + user.getId() + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentUser.setName(user.getName());
        currentUser.setAge(user.getAge());

        userService.update(currentUser);
        result.setSuccess(true);
        result.setMessage(Constants.RESPONSE_OK);
        result.setContent(currentUser);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    /**
     * Deletes a user by id.
     * @param:  * @param null
     * @Date: 15:32 2017/10/3 
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value="删除用户", httpMethod="DELETE", notes="Delete user by id")
    public ResponseEntity<ResultModel> deleteUser(@ApiParam(required=true, value="用户ID", name="id")
                                                  @PathVariable("id") long id) {
        logger.info("Fetching & Deleting User with id " + id);

        User user = userService.findById(id);
        if (user == null) {
            result.setSuccess(false);
            result.setCode(1005);
            result.setContent("Unable to delete. User with id " + id + " not found");
            logger.info("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userService.delete(id);
        result.setSuccess(true);
        result.setMessage(Constants.RESPONSE_OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
