package com.final_project.carRegistrationSystem.DTOs.request;


import lombok.Data;

@Data
public class CreateUserRequest {
    private String name;
    private String lastName;
    private String userName;
    private String password;
}
