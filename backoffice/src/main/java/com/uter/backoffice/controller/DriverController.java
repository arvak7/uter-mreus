package com.uter.backoffice.controller;

import com.uter.commons.dto.DriverDTO;
import com.uter.commons.entities.Driver;
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
@RequestMapping("/v1/drivers")
public class DriverController {

    @Autowired
    @Qualifier("driverService")
    private GenericService<Driver, DriverDTO, Long> service;

    @GetMapping("/{id}")
    public DriverDTO getDriverById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }
    
    @GetMapping
    public List<DriverDTO> getAllDrivers() {
        return service.findAll();
    }

    @GetMapping("/free")
    public List<DriverDTO> getFreeDrivers(@RequestParam(value = "fecha") @DateTimeFormat(pattern="yyyyddMM") Date date, @RequestParam String license) {
        return service.findFree(date, license);
    }

    @PostMapping
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) {
        return service.create(driverDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverDTO> updateDriver(@PathVariable(value = "id") Long id, @Valid @RequestBody DriverDTO driverDTO) {
        return service.update(id, driverDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDriver(@PathVariable(value = "id") Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
}
