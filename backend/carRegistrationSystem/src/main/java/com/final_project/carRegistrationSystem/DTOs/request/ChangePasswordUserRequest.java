package com.final_project.carRegistrationSystem.DTOs.request;

import lombok.Data;

@Data
public class ChangePasswordUserRequest {
    private String oldPassword;
    private String newPassword;
}
