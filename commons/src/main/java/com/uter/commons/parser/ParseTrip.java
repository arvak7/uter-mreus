package com.uter.commons.parser;

import com.uter.commons.dto.TripDTO;
import com.uter.commons.entities.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ParseTrip implements Parser <Trip, TripDTO> {

    @Autowired
    public ParseVehicle parserVehicle;

    @Autowired
    public ParseDriver parserDriver;

    @Override
    public TripDTO parse(Trip original) {
        TripDTO tripDTO = new TripDTO();
        if (original != null) {
            tripDTO.setId(original.getId());
            tripDTO.setDate(original.getDate());
            tripDTO.setDrivers(original.getDrivers().stream().map(driver -> parserDriver.parse(driver)).collect(Collectors.toList()));
            tripDTO.setVehicles(original.getVehicles().stream().map(vehicle -> parserVehicle.parse(vehicle)).collect(Collectors.toList()));
        }
        return tripDTO;
    }

    @Override
    public Trip unparse(TripDTO original) {
        Trip trip = new Trip();
        if (original != null) {
            trip.setId(original.getId());
            trip.setDate(original.getDate());
            trip.setDrivers(original.getDrivers().stream().map(driver -> parserDriver.unparse(driver)).collect(Collectors.toList()));
            trip.setVehicles(original.getVehicles().stream().map(vehicle -> parserVehicle.unparse(vehicle)).collect(Collectors.toList()));
        }
        return trip;
    }
}
