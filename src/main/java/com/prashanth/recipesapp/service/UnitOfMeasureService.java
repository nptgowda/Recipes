package com.prashanth.recipesapp.service;

import com.prashanth.recipesapp.command.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    public Set<UnitOfMeasureCommand> listAllUnitsOfMeasure();
}
