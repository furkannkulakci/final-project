package com.final_project.carRegistrationSystem.api;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.final_project.carRegistrationSystem.DTOs.request.CreateCarRequest;
import com.final_project.carRegistrationSystem.DTOs.request.UpdateCarRequest;
import com.final_project.carRegistrationSystem.DTOs.response.CarResponse;
import com.final_project.carRegistrationSystem.bussiness.abstractt.ICarService;
import com.final_project.carRegistrationSystem.core.utilities.DataResult;
import com.final_project.carRegistrationSystem.entities.Car;

@RestController
@RequestMapping("/cars")
public class CarsController {
    private final ICarService carService;

    public CarsController(ICarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public DataResult<List<CarResponse>> getAllCarsOrFindByUserIdOrBrandOrModel
            (@RequestParam Optional<Integer> userId,@RequestParam Optional<String> brand,@RequestParam Optional<String> modal){
        return carService.getAllCarsOrFindByUserIdOrBrandOrModel(userId,modal,brand);
    }


    @GetMapping("/{carId}")
    public DataResult<CarResponse> getOneCarsById(@PathVariable int carId){
        return carService.getOneCarsByIdApi(carId);
    }

    @PostMapping
    public DataResult<Car> createOneCar(@RequestBody CreateCarRequest createCarRequest){
        return carService.createOneCar(createCarRequest);
    }

    @PutMapping("/{carId}")
    public DataResult<Car> updateOneCar(@PathVariable int carId,@RequestBody UpdateCarRequest UpdateCarRequest){
        return carService.updateOneCar(carId,UpdateCarRequest);
    }

    @DeleteMapping("{carId}")
    public DataResult<Integer> removeById(@PathVariable int carId){
        return carService.removeById(carId);
    }
}
