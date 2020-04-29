package com.uter.backoffice.controller;

import com.uter.commons.dto.VehicleDTO;
import com.uter.commons.entities.Vehicle;
import com.uter.commons.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/vehicles")
public class VehicleController {

    @Autowired
    @Qualifier("vehicleService")
    private GenericService<Vehicle, VehicleDTO, Long> service;

    @GetMapping
    public List<VehicleDTO> getAllVehicles() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public VehicleDTO getVehicleById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @GetMapping("/free/{fecha}")
    public List<VehicleDTO> getFreeVehicles(@PathVariable(value = "fecha") @DateTimeFormat(pattern="yyyyddMM") Date date) {
        return service.findFree(date, null);
    }

    @PostMapping
    public VehicleDTO createVehicle(@Valid @RequestBody VehicleDTO vehicle) {
        return service.create(vehicle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable(value = "id") Long id, @Valid @RequestBody VehicleDTO vehicleDetail) {
        return service.update(id, vehicleDetail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteVehicle(@PathVariable(value = "id") Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
