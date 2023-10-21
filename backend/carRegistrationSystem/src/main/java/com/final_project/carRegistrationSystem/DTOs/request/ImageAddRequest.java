package com.final_project.carRegistrationSystem.DTOs.request;

import com.final_project.carRegistrationSystem.core.utilities.DataResult;
import com.final_project.carRegistrationSystem.entities.ImageCar;
import lombok.Data;

@Data
public class ImageAddRequest {

    private String url;
    private int carId;


}
