package com.final_project.carRegistrationSystem.bussiness.concretes;

import com.final_project.carRegistrationSystem.DTOs.request.ImageAddRequest;
import com.final_project.carRegistrationSystem.DTOs.response.ImageResponse;
import com.final_project.carRegistrationSystem.bussiness.abstractt.ICarService;
import com.final_project.carRegistrationSystem.bussiness.abstractt.IImageCarService;
import com.final_project.carRegistrationSystem.core.utilities.DataResult;
import com.final_project.carRegistrationSystem.core.utilities.ErrorDataResult;
import com.final_project.carRegistrationSystem.core.utilities.SuccesDataResult;
import com.final_project.carRegistrationSystem.entities.Car;
import com.final_project.carRegistrationSystem.entities.ImageCar;
import com.final_project.carRegistrationSystem.repository.ImageCarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImageCarManager implements IImageCarService {

    private final ImageCarRepository imageCarRepository;
    private final ICarService carService;

    public ImageCarManager(ImageCarRepository imageCarRepository, ICarService carService) {
        this.imageCarRepository = imageCarRepository;
        this.carService = carService;
    }


    @Override
    public DataResult<List<ImageResponse>> getAllOrByCarId(Optional<Integer> carId) {
        List<ImageCar> images;
        if (carId.isPresent()){
            images=imageCarRepository.findByCarId(carId.get());
            return new
                    SuccesDataResult<>
                    ("Data getirildi", images.stream().map(ImageResponse::new).collect(Collectors.toList()));
        }
        images=imageCarRepository.findAll();
        return new SuccesDataResult<>
                ("Data getirildi", images.stream().map(ImageResponse::new).collect(Collectors.toList()));
    }

    @Override
    public DataResult<ImageResponse> getImageById(int imageId) {
        Optional<ImageCar> image=imageCarRepository.findById(imageId);
        if (image.isPresent()){
            return new SuccesDataResult<>("Data Getirildi", new ImageResponse(image.get()));
        }
        return new ErrorDataResult<>("Data Getirilemedi", null);
    }

    public DataResult<ImageCar> getImageByIdHelp(int imageId) {
        Optional<ImageCar> image=imageCarRepository.findById(imageId);
        if (image.isPresent()){
            return new SuccesDataResult<>("Data Getirildi", image.get());
        }
        return new ErrorDataResult<>("Data Getirilemedi", null);
    }

    @Override
    public DataResult<ImageResponse> addOneImage(ImageAddRequest imageAddRequest) {
        Car isHaveCar=carService.getOneCarsByIdHelp(imageAddRequest.getCarId()).getData();
        if (isHaveCar!=null){
            ImageCar imageCar=new ImageCar();
            imageCar.setUrl(imageAddRequest.getUrl());
            imageCar.setCar(isHaveCar);
            imageCarRepository.save(imageCar);
            return new SuccesDataResult<>("Resim Eklendi", new ImageResponse(imageCar));
        }

        return new ErrorDataResult<>("Resim eklenemedi", null);
    }

    @Override
    public DataResult<Integer> removeOneImage(int imageId) {
        ImageCar imageCar=getImageByIdHelp(imageId).getData();
        if (imageCar!=null){
            return new SuccesDataResult<>("Resim silindi",imageId);
        }
        return new ErrorDataResult<>("Silinecek resim yok",null);
    }


}
