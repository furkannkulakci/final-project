package com.final_project.carRegistrationSystem.bussiness.abstractt;

import com.final_project.carRegistrationSystem.DTOs.request.CreateCarRequest;
import com.final_project.carRegistrationSystem.DTOs.request.UpdateCarRequest;
import com.final_project.carRegistrationSystem.DTOs.response.CarResponse;
import com.final_project.carRegistrationSystem.core.utilities.DataResult;
import com.final_project.carRegistrationSystem.entities.Car;

import java.util.List;
import java.util.Optional;

public interface ICarService {
    DataResult<List<CarResponse>> getAllCarsOrFindByUserIdOrBrandOrModel(Optional<Integer> userId, Optional<String> modal, Optional<String> brand);

    DataResult<Car> getOneCarsByIdHelp(int carId);
    DataResult<CarResponse> getOneCarsByIdApi(int carId);


    DataResult<Car> createOneCar(CreateCarRequest createCarRequest);

    DataResult<Car> updateOneCar(int carId, UpdateCarRequest updateCarRequest);

    DataResult<Integer> removeById(int carId);
}
