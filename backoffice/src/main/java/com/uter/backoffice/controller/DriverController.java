package com.uter.backoffice.controller;

import com.uter.backoffice.repository.DriverRepository;
import com.uter.commons.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
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
    public ResponseEntity<Driver> updateDriver(@PathVariable(value = "id") Long id, @Valid @RequestBody Driver DriverDetail) {
        Driver driver = driverRepository.findById(id).orElse(null);
        ResponseEntity<Driver> responseEntity;
        if (driver != null) {
            driver.setName(DriverDetail.getName());
            driver.setSurname(DriverDetail.getSurname());
            driver.setLicense(DriverDetail.getLicense());
            responseEntity = ResponseEntity.ok().body(driverRepository.save(driver));
        } else {
            responseEntity = ResponseEntity.noContent().build();
        }
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDriver(@PathVariable(value = "id") Long id) {
        driverRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
}
