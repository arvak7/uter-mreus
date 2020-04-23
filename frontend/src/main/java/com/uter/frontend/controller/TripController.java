package com.uter.frontend.controller;

import com.uter.commons.model.Trip;
import com.uter.frontend.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/trips")
public class TripController {

    @Autowired
    private TripRepository tripRepository;

    @GetMapping
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    @GetMapping("/{id}")
    public Trip getTripById(@PathVariable(value = "id") Long id) {
        return tripRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Trip createTrip(@Valid @RequestBody Trip tripEntity) {
        return tripRepository.save(tripEntity);
    }

    @PutMapping("/{id}")
    public Trip updateVehicle(@PathVariable(value = "id") Long id, @Valid @RequestBody Trip tripDetail) {
        Trip trip = tripRepository.findById(id).orElse(null);
        trip.setDate(tripDetail.getDate());
        trip.setDriver(tripDetail.getDriver());
        trip.setVehicle(tripDetail.getVehicle());
        return tripRepository.save(trip);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable(value = "id") Long id) {
        tripRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
