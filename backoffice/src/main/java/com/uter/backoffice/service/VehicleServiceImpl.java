package com.uter.backoffice.service;

import com.uter.backoffice.repository.VehicleRepository;
import com.uter.backoffice.util.Utils;
import com.uter.commons.dto.VehicleDTO;
import com.uter.commons.entities.Vehicle;
import com.uter.commons.parser.ParseVehicle;
import com.uter.commons.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("vehicleService")
public class VehicleServiceImpl implements GenericService<Vehicle, VehicleDTO, Long> {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ParseVehicle parser;

    @Autowired
    private Utils utils;

    @Override
    public List<VehicleDTO> findAll() {
        return vehicleRepository.findAll().stream().map(vehicle -> parser.parse(vehicle)).collect(Collectors.toList());
    }

    @Override
    public VehicleDTO findById(Long id) {
        return parser.parse(vehicleRepository.findById(id).orElse(null));
    }

    @Override
    public List<VehicleDTO> findFree(Date date, String license) {
        List<Vehicle> freeVehicle = vehicleRepository.findFreeVehicle(utils.removeTime(date));
        return freeVehicle.stream().map(vehicle -> parser.parse(vehicle)).collect(Collectors.toList());
    }

    @Override
    public VehicleDTO create(VehicleDTO vehicleDTO) {
        return parser.parse(vehicleRepository.save(parser.unparse(vehicleDTO)));
    }

    @Override
    public ResponseEntity<VehicleDTO> update(Long id, VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleRepository.findById(id).orElse(null);
        ResponseEntity<VehicleDTO> responseEntity = null;
        if (vehicle != null) {
            vehicle.setBrand(vehicleDTO.getBrand());
            vehicle.setModel(vehicleDTO.getModel());
            vehicle.setPlate(vehicleDTO.getPlate());
            vehicle.setLicenseRequired(vehicleDTO.getLicenseRequired());
            responseEntity = ResponseEntity.ok().body(parser.parse(vehicleRepository.save(vehicle)));
        } else {
            responseEntity = ResponseEntity.noContent().build();
        }
        return responseEntity;
    }

    @Override
    public void deleteById(Long id) {
        vehicleRepository.deleteById(id);
    }
}
