package com.uter.backoffice.controller;

import com.uter.backoffice.model.Driver;
import com.uter.backoffice.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/drivers")
public class DriverController {

    @Autowired
    private DriverRepository driverRepository;
    
    @GetMapping
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @GetMapping("/{id}")
    public Driver getDriverById(@PathVariable(value = "id") Long id) {
        return driverRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Driver createDriver(@Valid @RequestBody Driver Driver) {
        return driverRepository.save(Driver);
    }

    @PutMapping("/{id}")
    public Driver updateDriver(@PathVariable(value = "id") Long id, @Valid @RequestBody Driver DriverDetail) {
        Driver Driver = driverRepository.findById(id).orElse(null);
        Driver.setName(DriverDetail.getName());
        Driver.setSurname(DriverDetail.getSurname());
        Driver.setLicense(DriverDetail.getLicense());
        return driverRepository.save(Driver);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDriver(@PathVariable(value = "id") Long id) {
        driverRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
}
