package com.backend.qa.controller;

import com.backend.qa.common.CustomResponse;
import com.backend.qa.common.CustomResponseStatus;
import com.backend.qa.domain.User;
import com.backend.qa.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Api(tags = "User")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("Get All Users")
    @GetMapping(value = "/users")
    @ResponseBody
    public CustomResponse<ArrayList<User>> getUserByUid(){
        ArrayList<User> users = new ArrayList<User>();
        CustomResponse<ArrayList<User>> result = CustomResponse.build();
        users = userService.getAllUsers();
        if(users == null){
            result.withError(CustomResponseStatus.NOT_FOUND.getCode(), CustomResponseStatus.NOT_FOUND.getMessage());
            return result;
        }
        result.setData(users);
        return result;
    }

    @ApiOperation("Get User By Id")
    @GetMapping(value = "/users/{id}")
    @ResponseBody
    public CustomResponse<User> getUserByUid(@PathVariable("id") int uid){
        CustomResponse<User> result = CustomResponse.build();
        User user = userService.getByuid(uid);
        if(user == null){
            result.withError(CustomResponseStatus.NOT_FOUND.getCode(), CustomResponseStatus.NOT_FOUND.getMessage());
            return result;
        }
        result.setData(user);
        return result;
    }

    @ApiOperation("Register")
    @PostMapping(value = "/users")
    @ResponseBody
    public CustomResponse<User> register(HttpServletResponse response, @RequestParam("username") String username,
                                         @RequestParam("password") String password, @RequestParam("email") String email){
        CustomResponse<User> result = CustomResponse.build();
        if(!userService.register(response, username, password, email)){
            result.withError(CustomResponseStatus.REGISTRATION_ERROR.getCode(), CustomResponseStatus.REGISTRATION_ERROR.getMessage());
            return result;
        }
        User newUser = userService.getByUsername(username);
        result.setData(newUser);
        return result;
    }

    @ApiOperation("Login")
    @PostMapping(value = "/users/login")
    @ResponseBody
    public CustomResponse<Integer> login(HttpServletResponse response, HttpServletRequest request, @RequestParam("username") String username,
                                         @RequestParam("password") String password){
        CustomResponse<Integer> result = CustomResponse.build();
        if(!userService.login(response, username, password)){
            result.withError(CustomResponseStatus.AUTH_ERROR.getCode(), CustomResponseStatus.AUTH_ERROR.getMessage());
            return result;
        }
        User user = userService.getByUsername(username);
        result.setData(user.getUid());
        return result;
    }

    @ApiOperation("Logout")
    @GetMapping(value = "/users/logout")
    @ResponseBody
    public CustomResponse<Boolean> logout(HttpServletResponse response, HttpServletRequest request){
        CustomResponse<Boolean> result = CustomResponse.build();
        if(!userService.logout(response, request)){
            result.withError(CustomResponseStatus.SESSION_ERROR.getCode(), CustomResponseStatus.SESSION_ERROR.getMessage());
            return result;
        }
        result.setData(true);
        return result;
    }
}

