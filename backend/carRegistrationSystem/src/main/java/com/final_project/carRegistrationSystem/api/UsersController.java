package com.final_project.carRegistrationSystem.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.final_project.carRegistrationSystem.DTOs.request.ChangePasswordUserRequest;
import com.final_project.carRegistrationSystem.DTOs.request.CreateUserRequest;
import com.final_project.carRegistrationSystem.DTOs.response.ChangePasswordResponse;
import com.final_project.carRegistrationSystem.bussiness.abstractt.IUserService;
import com.final_project.carRegistrationSystem.core.utilities.DataResult;
import com.final_project.carRegistrationSystem.core.utilities.Result;
import com.final_project.carRegistrationSystem.entities.User;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final IUserService userService;

    public UsersController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public DataResult<List<User>>getAllUsers(){
        return userService.getAllUser();
    }

    @GetMapping("/{userId}")
    public DataResult<User> getById(@PathVariable int userId){
        return userService.getById(userId);

    }



    @PostMapping
    public DataResult<User> createOneUser(@RequestBody CreateUserRequest createUserRequest){
        return userService.createOneUser(createUserRequest);
    }

    @PutMapping("/{userId}")
    public ChangePasswordResponse updateOneUser(@PathVariable int userId, @RequestBody ChangePasswordUserRequest changePasswordUserRequest){
        return userService.updateOneUser(userId,changePasswordUserRequest);
    }

    @DeleteMapping("/{userId}")
    public Result removeById(@PathVariable int userId){
        return userService.removeById(userId);
    }
}
