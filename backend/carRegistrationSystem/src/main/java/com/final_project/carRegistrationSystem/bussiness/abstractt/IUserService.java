package com.final_project.carRegistrationSystem.bussiness.abstractt;

import com.final_project.carRegistrationSystem.DTOs.request.ChangePasswordUserRequest;
import com.final_project.carRegistrationSystem.DTOs.request.CreateUserRequest;
import com.final_project.carRegistrationSystem.DTOs.response.ChangePasswordResponse;
import com.final_project.carRegistrationSystem.core.utilities.DataResult;
import com.final_project.carRegistrationSystem.core.utilities.Result;
import com.final_project.carRegistrationSystem.entities.User;

import java.util.List;

public interface IUserService {
    DataResult<List<User>> getAllUser();

    DataResult<User> getById(int userId);

    DataResult<User> getByUserName(String userName);

    DataResult<User> createOneUser(CreateUserRequest createUserRequest);

   ChangePasswordResponse updateOneUser(int userId, ChangePasswordUserRequest changePasswordUserRequest);

    Result removeById(int userId);
}
