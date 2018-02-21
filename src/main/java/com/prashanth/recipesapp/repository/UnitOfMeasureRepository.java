package com.prashanth.recipesapp.repository;

import com.prashanth.recipesapp.model.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
    Optional<UnitOfMeasure> findByUnitOfMeasure(String unitOfMeasure);
}