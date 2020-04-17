package com.uter.backoffice.controller;

import com.uter.backoffice.repository.VehicleRepository;
import com.uter.commons.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/vehicles")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable(value = "id") Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    @GetMapping("/brand/{brand}")
    public List<Vehicle> getVehicleByBrand(@PathVariable(value = "brand") String model) {
        return vehicleRepository.findByBrand(model);
    }

    @PostMapping
    public Vehicle createVehicle(@Valid @RequestBody Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @PutMapping("/{id}")
    public Vehicle updateVehicle(@PathVariable(value = "id") Long id, @Valid @RequestBody Vehicle vehicleDetail) {
        Vehicle vehicle = vehicleRepository.findById(id).orElse(null);
        vehicle.setBrand(vehicleDetail.getBrand());
        vehicle.setModel(vehicleDetail.getModel());
        vehicle.setPlate(vehicleDetail.getPlate());
        vehicle.setLicenseRequired(vehicleDetail.getLicenseRequired());
        return vehicleRepository.save(vehicle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable(value = "id") Long id) {
        vehicleRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
