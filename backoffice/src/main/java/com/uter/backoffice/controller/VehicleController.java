package com.uter.backoffice.controller;

import com.uter.backoffice.repository.VehicleRepository;
import com.uter.commons.dto.VehicleDTO;
import com.uter.commons.entities.Vehicle;
import com.uter.backoffice.parser.ParseVehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/vehicles")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ParseVehicle parser;

    @GetMapping
    public List<VehicleDTO> getAllVehicles() {
        return vehicleRepository.findAll().stream().map(vehicle -> parser.parse(vehicle)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public VehicleDTO getVehicleById(@PathVariable(value = "id") Long id) {
        return parser.parse(vehicleRepository.findById(id).orElse(null));
    }

    @GetMapping("/brand/{brand}")
    public List<VehicleDTO> getVehicleByBrand(@PathVariable(value = "brand") String model) {
        List<VehicleDTO> vehiclesDTO = new ArrayList<>();
        vehicleRepository.findByBrand(model).stream().map(vehicle -> vehiclesDTO.add(parser.parse(vehicle))).collect(Collectors.toList());
        return vehiclesDTO;
    }

    @PostMapping
    public VehicleDTO createVehicle(@Valid @RequestBody VehicleDTO vehicle) {
        return parser.parse(vehicleRepository.save(parser.unparse(vehicle)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable(value = "id") Long id, @Valid @RequestBody VehicleDTO vehicleDetail) {
        Vehicle vehicle = vehicleRepository.findById(id).orElse(null);
        ResponseEntity<VehicleDTO> responseEntity = null;
        if (vehicle != null) {
            vehicle.setBrand(vehicleDetail.getBrand());
            vehicle.setModel(vehicleDetail.getModel());
            vehicle.setPlate(vehicleDetail.getPlate());
            vehicle.setLicenseRequired(vehicleDetail.getLicenseRequired());
            responseEntity = ResponseEntity.ok().body(parser.parse(vehicleRepository.save(vehicle)));
        } else {
            responseEntity = ResponseEntity.noContent().build();
        }
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteVehicle(@PathVariable(value = "id") Long id) {
        vehicleRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
