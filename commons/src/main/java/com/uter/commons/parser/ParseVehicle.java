package com.uter.commons.parser;

import com.uter.commons.dto.VehicleDTO;
import com.uter.commons.entities.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class ParseVehicle implements Parser<Vehicle, VehicleDTO> {
    @Override
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

    @Override
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
}
