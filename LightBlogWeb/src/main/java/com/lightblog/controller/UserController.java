package com.lightblog.controller;

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
 * @Description: The api of user.
 * @Author: Minsghan
 * @Date: Created in 15:27 2017/10/3
 * @Modified By:
 */
@Api(value="user")
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    /**
     * @Author: Mingshan
     * @Description: Get list of user.
     * @param:  null
     * @Date: 15:16 2017/10/3 
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(value="获取所有用户信息", httpMethod="GET", notes="Get users", response=ResponseEntity.class)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAll();
        if(users.isEmpty()){
            //You many decide to return HttpStatus.NOT_FOUND
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    /**
     * @Author: Mingshan
     * @Description: Get information of user by id.
     * @param:  * @param id
     * @Date: 15:34 2017/10/3 
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="获取用户信息", httpMethod="GET", notes="Get user by id", response=ResponseEntity.class)
    public ResponseEntity<User> getUser(@ApiParam(required=true,value="用户ID",name="id")@PathVariable("id") long id) {
        logger.info("Fetching User with id " + id);
        User user = userService.findById(id);
        if (user == null) {
            logger.info("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    /**
     * @Author: Mingshan
     * @Description: Create a user.
     * @param:  * @param null
     * @Date: 15:34 2017/10/3 
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ApiOperation(value="新增用户", httpMethod="POST", notes="Create user", response=ResponseEntity.class)
    public ResponseEntity<Void> createUser(@ApiParam(required=true,value="用户信息",name="User")
                                               @RequestBody User user, UriComponentsBuilder ucBuilder) {
        logger.info("Creating User " + user.getName());

        if (userService.isUserExist(user)) {
            System.out.println("A User with name " + user.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        userService.insert(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    /**
     * @Author: Mingshan
     * @Description: Update a user.
     * @param:  * @param null
     * @Date: 15:33 2017/10/3 
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value="更新用户信息", httpMethod="PUT", notes="Update user", response=ResponseEntity.class)
    public ResponseEntity<User> updateUser(@ApiParam(required=true,value="用户ID",name="id")@PathVariable("id") long id,
                                           @RequestBody User user) {
        logger.info("Updating User " + id);

        User currentUser = userService.findById(id);

        if (currentUser == null) {
            logger.info("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        currentUser.setName(user.getName());
        currentUser.setAge(user.getAge());

        userService.update(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }
    
    /**
     * @Author: Mingshan
     * @Description: Delete a user by id.
     * @param:  * @param null
     * @Date: 15:32 2017/10/3 
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value="删除用户", httpMethod="DELETE", notes="Delete user by id", response=ResponseEntity.class)
    public ResponseEntity<User> deleteUser(@ApiParam(required=true,value="用户ID",name="id")@PathVariable("id") long id) {
        logger.info("Fetching & Deleting User with id " + id);

        User user = userService.findById(id);
        if (user == null) {
            logger.info("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        userService.delete(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}
