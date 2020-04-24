package com.uter.frontend.parser;

import com.uter.commons.dto.DriverDTO;
import com.uter.commons.dto.TripDTO;
import com.uter.commons.dto.VehicleDTO;
import com.uter.commons.entities.Driver;
import com.uter.commons.entities.Trip;
import com.uter.commons.entities.Vehicle;
import com.uter.commons.parser.Parser;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ParseTrip implements Parser <Trip, TripDTO> {

    @Override
    public TripDTO parse(Trip original) {
        TripDTO tripDTO = new TripDTO();
        if (original != null) {
            tripDTO.setId(original.getId());
            tripDTO.setDate(original.getDate());
            tripDTO.setDrivers(original.getDrivers().stream().map(this::parse).collect(Collectors.toList()));
            tripDTO.setVehicles(original.getVehicles().stream().map(this::parse).collect(Collectors.toList()));
        }
        return tripDTO;
    }

    @Override
    public Trip unparse(TripDTO original) {
        Trip trip = new Trip();
        if (original != null) {
            trip.setId(original.getId());
            trip.setDate(original.getDate());
            trip.setDrivers(original.getDrivers().stream().map(this::unparse).collect(Collectors.toList()));
            trip.setVehicles(original.getVehicles().stream().map(this::unparse).collect(Collectors.toList()));
        }
        return trip;
    }

    public VehicleDTO parse(Vehicle original) {
        VehicleDTO dto = new VehicleDTO();
        if (original != null) {
            dto.setId(original.getId());
            dto.setModel(original.getModel());
            dto.setBrand(original.getBrand());
            dto.setPlate(original.getPlate());
            dto.setLicenseRequired(original.getLicenseRequired());
        }
        return dto;
    }

    public DriverDTO parse(Driver original) {
        DriverDTO driverDTO = new DriverDTO();
        if (original != null) {
            driverDTO.setId(original.getId());
            driverDTO.setName(original.getName());
            driverDTO.setSurname(original.getSurname());
            driverDTO.setLicense(original.getLicense());
        }
        return driverDTO;
    }

    public Vehicle unparse(VehicleDTO original) {
        Vehicle vehicle = new Vehicle();
        if (original != null) {
            vehicle.setId(original.getId());
            vehicle.setModel(original.getModel());
            vehicle.setBrand(original.getBrand());
            vehicle.setPlate(original.getPlate());
            vehicle.setLicenseRequired(original.getLicenseRequired());
        }
        return vehicle;
    }

    public Driver unparse(DriverDTO original) {
        Driver driver = new Driver();
        if (original != null) {
            driver.setId(original.getId());
            driver.setName(original.getName());
            driver.setSurname(original.getSurname());
            driver.setLicense(original.getLicense());
        }
        return driver;
    }
}
