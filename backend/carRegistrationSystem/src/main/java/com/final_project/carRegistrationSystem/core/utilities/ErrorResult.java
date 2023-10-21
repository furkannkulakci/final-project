package com.final_project.carRegistrationSystem.core.utilities;

public class ErrorResult extends Result{

    public ErrorResult(String message) {
        super(false, message);
    }
}
