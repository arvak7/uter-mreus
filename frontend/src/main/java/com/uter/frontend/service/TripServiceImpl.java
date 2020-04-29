package com.uter.frontend.service;

import com.uter.commons.dto.TripDTO;
import com.uter.commons.entities.Trip;
import com.uter.commons.parser.ParseDriver;
import com.uter.commons.parser.ParseTrip;
import com.uter.commons.parser.ParseVehicle;
import com.uter.commons.service.GenericService;
import com.uter.frontend.repository.TripRepository;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("tripService")
public class TripServiceImpl implements GenericService<Trip, TripDTO, Long>  {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private ParseTrip parser;

    @Autowired
    public ParseVehicle parserVehicle;

    @Autowired
    public ParseDriver parserDriver;

    @Override
    public TripDTO findById(Long id) {
        return parser.parse(tripRepository.findById(id).orElse(null));
    }

    @Override
    public List<TripDTO> findAll() {
        return tripRepository.findAll().stream().map(trip -> parser.parse(trip)).collect(Collectors.toList());
    }

    @Override
    public List<TripDTO> findFree(Date date, String license) {
        throw new NotImplementedException();
    }

    @Override
    public TripDTO create(TripDTO entity) {
        return parser.parse(tripRepository.save(parser.unparse(entity)));
    }

    @Override
    public ResponseEntity<TripDTO> update(Long id, TripDTO tripDTO) {
        Trip trip = tripRepository.findById(id).orElse(null);
        ResponseEntity<TripDTO> responseEntity;
        if (trip != null) {
            trip.setDate(tripDTO.getDate());
            trip.setDrivers(tripDTO.getDrivers().stream().map(driver -> parserDriver.unparse(driver)).collect(Collectors.toList()));
            trip.setVehicles(tripDTO.getVehicles().stream().map(vehicle -> parserVehicle.unparse(vehicle)).collect(Collectors.toList()));
            responseEntity = ResponseEntity.ok().body(parser.parse(tripRepository.save(trip)));
        } else {
            responseEntity = ResponseEntity.noContent().build();
        }
        return responseEntity;
    }

    @Override
    public void deleteById(Long id) {
        tripRepository.deleteById(id);
    }
}
