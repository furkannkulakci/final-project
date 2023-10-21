package com.final_project.carRegistrationSystem.repository;

import com.final_project.carRegistrationSystem.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {
    List<Car> findByUserId(Optional<Integer> userId);

    List<Car> findByModal(Optional<String> modal);

    List<Car> findByBrand(Optional<String> brand);

    List<Car> findByBrandAndModal(Optional<String> brand, Optional<String> modal);
}
