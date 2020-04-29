package com.uter.backoffice.controller;

import com.uter.backoffice.parser.ParseDriver;
import com.uter.backoffice.repository.DriverRepository;
import com.uter.commons.dto.DriverDTO;
import com.uter.commons.entities.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/drivers")
public class DriverController {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private ParseDriver parser;
    
    @GetMapping
    public List<DriverDTO> getAllDrivers() {
        return driverRepository.findAll().stream().map(driver -> parser.parse(driver)).collect(Collectors.toList());
    }

    @GetMapping("/free")
    public List<DriverDTO> getFreeDrivers(@RequestParam(value = "fecha") @DateTimeFormat(pattern="yyyyddMM") Date date, @RequestParam String license) {
        return driverRepository.findFreeDriver(date, license).stream().map(driver -> parser.parse(driver)).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public DriverDTO getDriverById(@PathVariable(value = "id") Long id) {
        return parser.parse(driverRepository.findById(id).orElse(null));
    }

    @PostMapping
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driver) {
        return parser.parse(driverRepository.save(parser.unparse(driver)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverDTO> updateDriver(@PathVariable(value = "id") Long id, @Valid @RequestBody DriverDTO driverDetail) {
        Driver driver = driverRepository.findById(id).orElse(null);
        ResponseEntity<DriverDTO> responseEntity;
        if (driver != null) {
            driver.setName(driverDetail.getName());
            driver.setSurname(driverDetail.getSurname());
            driver.setLicense(driverDetail.getLicense());
            responseEntity = ResponseEntity.ok().body(parser.parse(driverRepository.save(driver)));
        } else {
            responseEntity = ResponseEntity.noContent().build();
        }
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDriver(@PathVariable(value = "id") Long id) {
        driverRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
}
