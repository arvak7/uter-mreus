package com.uter.backoffice.service;

import com.uter.backoffice.repository.DriverRepository;
import com.uter.commons.dto.DriverDTO;
import com.uter.commons.entities.Driver;
import com.uter.commons.parser.ParseDriver;
import com.uter.commons.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("driverService")
public class DriverServiceImpl implements GenericService<Driver, DriverDTO, Long> {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private ParseDriver parser;

    @Override
    public DriverDTO findById(Long id) {
        return parser.parse(driverRepository.findById(id).orElse(null));
    }

    @Override
    public List<DriverDTO> findAll() {
        return driverRepository.findAll().stream().map(driver -> parser.parse(driver)).collect(Collectors.toList());
    }

    @Override
    public List<DriverDTO> findFree(Date date, String license) {
        return driverRepository.findFreeDriver(date, license).stream().map(driver -> parser.parse(driver)).collect(Collectors.toList());
    }

    @Override
    public DriverDTO create(DriverDTO driverDTO) {
        return parser.parse(driverRepository.save(parser.unparse(driverDTO)));
    }

    @Override
    public ResponseEntity<DriverDTO> update(Long id, DriverDTO driverDTO) {
        Driver driver = driverRepository.findById(id).orElse(null);
        ResponseEntity<DriverDTO> responseEntity;
        if (driver != null) {
            driver.setName(driverDTO.getName());
            driver.setSurname(driverDTO.getSurname());
            driver.setLicense(driverDTO.getLicense());
            responseEntity = ResponseEntity.ok().body(parser.parse(driverRepository.save(driver)));
        } else {
            responseEntity = ResponseEntity.noContent().build();
        }
        return responseEntity;
    }

    @Override
    public void deleteById(Long id) {
        driverRepository.deleteById(id);
    }
}
