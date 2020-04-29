package com.uter.frontend.controller;

import com.uter.commons.dto.TripDTO;
import com.uter.commons.entities.Trip;
import com.uter.commons.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/trips")
public class TripController {

    @Autowired
    @Qualifier("tripService")
    private GenericService<Trip, TripDTO, Long> service;

    @GetMapping("/{id}")
    public TripDTO getTripById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<TripDTO> getAllTrips() {
        return service.findAll();
    }

    @PostMapping
    public TripDTO createTrip(@Valid @RequestBody TripDTO tripDTO) {
        return service.create(tripDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripDTO> updateTrip(@PathVariable(value = "id") Long id, @Valid @RequestBody TripDTO tripDTO) {
        return service.update(id, tripDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTrip(@PathVariable(value = "id") Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
