package com.final_project.carRegistrationSystem.bussiness.abstractt;


import java.util.List;
import java.util.Optional;

import com.final_project.carRegistrationSystem.DTOs.request.ImageAddRequest;
import com.final_project.carRegistrationSystem.DTOs.response.ImageResponse;
import com.final_project.carRegistrationSystem.core.utilities.DataResult;

public interface IImageCarService {
    DataResult<List<ImageResponse>> getAllOrByCarId(Optional<Integer> carId);

    DataResult<ImageResponse> getImageById(int imageId);

    DataResult<ImageResponse> addOneImage(ImageAddRequest imageAddRequest);

    DataResult<Integer> removeOneImage(int imageId);
}
