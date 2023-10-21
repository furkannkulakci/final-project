package com.final_project.carRegistrationSystem.DTOs.response;

import com.final_project.carRegistrationSystem.entities.Car;
import com.final_project.carRegistrationSystem.entities.ImageCar;
import com.final_project.carRegistrationSystem.entities.User;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

@Data
public class CarResponse {
    private int id;

   private int userId;
    private String userName;

    private String carName;

    private String brand;

    private String modal;

    private String year;

    private String plate;
    private Date createDate;
    private List<ImageResponse> carImages;

    public CarResponse(Car entity, List<ImageResponse>images) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUserName();
        this.carName = entity.getCarName();
        this.brand = entity.getBrand();
        this.modal = entity.getModal();
        this.year = entity.getYear();
        this.plate = entity.getPlate();
        this.createDate = entity.getCreateDate();
        this.carImages=images;
    }
}

