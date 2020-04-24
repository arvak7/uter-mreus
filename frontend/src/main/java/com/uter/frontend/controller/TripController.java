package com.uter.frontend.controller;

import com.uter.commons.dto.TripDTO;
import com.uter.commons.entities.Trip;
import com.uter.frontend.parser.ParseTrip;
import com.uter.frontend.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/trips")
public class TripController {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private ParseTrip parser;

    @GetMapping
    public List<TripDTO> getAllTrips() {
        return tripRepository.findAll().stream().map(trip -> parser.parse(trip)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TripDTO getTripById(@PathVariable(value = "id") Long id) {
        return parser.parse(tripRepository.findById(id).orElse(null));
    }

    @PostMapping
    public TripDTO createTrip(@Valid @RequestBody TripDTO tripEntity) {
        return parser.parse(tripRepository.save(parser.unparse(tripEntity)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripDTO> updateTrip(@PathVariable(value = "id") Long id, @Valid @RequestBody TripDTO tripDetail) {
        Trip trip = tripRepository.findById(id).orElse(null);
        ResponseEntity<TripDTO> responseEntity;
        if (trip != null) {
            trip.setDate(tripDetail.getDate());
            trip.setDrivers(tripDetail.getDrivers().stream().map(driver -> parser.unparse(driver)).collect(Collectors.toList()));
            trip.setVehicles(tripDetail.getVehicles().stream().map(vehicle -> parser.unparse(vehicle)).collect(Collectors.toList()));
            responseEntity = ResponseEntity.ok().body(parser.parse(tripRepository.save(trip)));
        } else {
            responseEntity = ResponseEntity.noContent().build();
        }
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTrip(@PathVariable(value = "id") Long id) {
        tripRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
