package com.final_project.carRegistrationSystem.bussiness.concretes;

import com.final_project.carRegistrationSystem.DTOs.request.CreateCarRequest;
import com.final_project.carRegistrationSystem.DTOs.request.UpdateCarRequest;
import com.final_project.carRegistrationSystem.DTOs.response.CarResponse;
import com.final_project.carRegistrationSystem.DTOs.response.ImageResponse;
import com.final_project.carRegistrationSystem.bussiness.abstractt.ICarService;
import com.final_project.carRegistrationSystem.bussiness.abstractt.IImageCarService;
import com.final_project.carRegistrationSystem.bussiness.abstractt.IUserService;
import com.final_project.carRegistrationSystem.core.utilities.*;
import com.final_project.carRegistrationSystem.entities.Car;
import com.final_project.carRegistrationSystem.entities.User;
import com.final_project.carRegistrationSystem.repository.CarRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarManager implements ICarService {

  private final CarRepository carRepository;
  private final IUserService userService;
  private final IImageCarService imageCarService;

    @Lazy
    public CarManager(CarRepository carRepository, IUserService userService, IImageCarService imageCarService) {
        this.carRepository = carRepository;
        this.userService = userService;
        this.imageCarService = imageCarService;
    }


    @Override
    public DataResult<List<CarResponse>> getAllCarsOrFindByUserIdOrBrandOrModel(Optional<Integer> userId, Optional<String> modal, Optional<String> brand) {
        List<Car> car;
        if (userId.isPresent()){
            car=carRepository.findByUserId(userId);

            return new
                    SuccesDataResult<>
                    ("Kullanıcıya ait araçlar getirildi",
                            car.stream().map(car1 -> {
                                List<ImageResponse> images = imageCarService.getAllOrByCarId(Optional.of(car1.getId())).getData();
                                return new CarResponse(car1, images);
                            }).collect(Collectors.toList()));
        }

        if (modal.isPresent()) {
            car=carRepository.findByModal(modal);
            return new
                    SuccesDataResult<>
                    (modal.get() + " model tipi araçlar getirildi", car.stream().map(car1 -> {
                        List<ImageResponse> images = imageCarService.getAllOrByCarId(Optional.of(car1.getId())).getData();
                        return new CarResponse(car1, images);
                    }).collect(Collectors.toList()));

        }

        if (brand.isPresent()) {
            car=carRepository.findByBrand(brand);
            return new
                    SuccesDataResult<>
                    (brand.get() + " marka araçlar getirildi", car.stream().map(car1 -> {
                        List<ImageResponse> images = imageCarService.getAllOrByCarId(Optional.of(car1.getId())).getData();
                        return new CarResponse(car1, images);
                    }).collect(Collectors.toList()));

        }

        if(brand.isPresent() && modal.isPresent()){
            car=carRepository.findByBrandAndModal(brand,modal);
            return new
                    SuccesDataResult<>
                    (brand.get() + " marka araç ve" + modal.get() + "model tipi araçlar getirildi", car.stream().map(car1 -> {
                        List<ImageResponse> images = imageCarService.getAllOrByCarId(Optional.of(car1.getId())).getData();
                        return new CarResponse(car1, images);
                    }).collect(Collectors.toList()));
        }

        car=carRepository.findAll();
        return new
                SuccesDataResult<>("Tüm araçlar getirildi", car.stream().map(car1 -> {
            List<ImageResponse> images = imageCarService.getAllOrByCarId(Optional.of(car1.getId())).getData();
            return new CarResponse(car1, images);
        }).collect(Collectors.toList()));
    }

    @Override
    public DataResult<Car> getOneCarsByIdHelp(int carId) {
        Optional<Car> haveIsCar=carRepository.findById(carId);
        if (haveIsCar.isPresent()){
            return new SuccesDataResult<>("Araç Getirildi", haveIsCar.get());
        }
        return new ErrorDataResult<>("Böyle bir araç bulunmamaktadır ", null);
    }

    @Override
    public DataResult<CarResponse> getOneCarsByIdApi(int carId) {
        Optional<Car> haveIsCar=carRepository.findById(carId);
        List<ImageResponse> images= imageCarService.getAllOrByCarId(Optional.of(haveIsCar.get().getId())).getData();
        if (haveIsCar.isPresent()) {
            return new SuccesDataResult<>("Araç Getirildi.", new CarResponse(haveIsCar.get(), images));
        }
        return new ErrorDataResult<>("Böyle bir araç bulunmamaktadır ", null);
    }

    @Override
    public DataResult<Car> createOneCar(CreateCarRequest createCarRequest) {
        User haveIsUser= userService.getById(createCarRequest.getUserId()).getData();
        if (haveIsUser==null){
            return new ErrorDataResult<>("Araç Eklenemedi", null);
        }

        Car toSaveCar=new Car();
        toSaveCar.setCarName(createCarRequest.getCarName());
        toSaveCar.setPlate(turkishToEnglishConverter(createCarRequest.getPlate()).toUpperCase());
        toSaveCar.setYear(createCarRequest.getYear());
        toSaveCar.setBrand(createCarRequest.getBrand());
        toSaveCar.setModal(createCarRequest.getModal());
        toSaveCar.setUser(haveIsUser);
        toSaveCar.setCreateDate(new Date());
        carRepository.save(toSaveCar);

        return new SuccesDataResult<>("Araç eklendi", toSaveCar);
    }

    @Override
    public DataResult<Car> updateOneCar(int carId, UpdateCarRequest updateCarRequest) {
        Optional<Car> haveIsCar=carRepository.findById(carId);
        if (haveIsCar.isPresent()){
            Car toUpdateCar=haveIsCar.get();
            toUpdateCar.setCarName(updateCarRequest.getCarName());
            toUpdateCar.setBrand(updateCarRequest.getBrand());
            toUpdateCar.setPlate(turkishToEnglishConverter(updateCarRequest.getPlate()).toUpperCase());
            toUpdateCar.setModal(updateCarRequest.getModal());
            toUpdateCar.setYear(updateCarRequest.getYear());
            carRepository.save(toUpdateCar);
            return new SuccesDataResult<>("Araç güncellendi", toUpdateCar);
        }
        return new ErrorDataResult<>("Araç bulunamadı", null);
    }

    @Override
    public DataResult<Integer> removeById(int carId) {
        Optional<Car> haveIsCar=carRepository.findById(carId);
        if (haveIsCar.isPresent()){
            carRepository.deleteById(carId);
            return new SuccesDataResult<>("Araç silindi", carId);
        }
        return new ErrorDataResult<>("Araç kaydı bulunamadı", null);
    }


    public  String turkishToEnglishConverter(String text)
    {
        char[] turkishChars = {'ı', 'ğ', 'İ', 'Ğ', 'ç', 'Ç', 'ş', 'Ş', 'ö', 'Ö', 'ü', 'Ü'};
        char[] englishChars = {'i', 'g', 'I', 'G', 'c', 'C', 's', 'S', 'o', 'O', 'u', 'U'};

        // Match chars
        for (int i = 0; i < turkishChars.length; i++)
            text = text.replace(turkishChars[i], englishChars[i]);
        return text;
    }
}
