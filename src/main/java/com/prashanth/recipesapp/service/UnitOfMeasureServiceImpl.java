package com.prashanth.recipesapp.service;

import com.prashanth.recipesapp.command.UnitOfMeasureCommand;
import com.prashanth.recipesapp.converter.UnitOfMeasureToUnitOfMeasureCommand;
import com.prashanth.recipesapp.repository.UnitOfMeasureRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Component
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private UnitOfMeasureRepository unitOfMeasureRepository;
    private UnitOfMeasureToUnitOfMeasureCommand converter;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand converter) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.converter = converter;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUnitsOfMeasure() {
        return StreamSupport.stream(unitOfMeasureRepository.findAll()
        .spliterator(),false)
                .map(converter::convert)
                .collect(Collectors.toSet());

    }

}
