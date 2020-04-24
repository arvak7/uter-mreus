package com.uter.backoffice.parser;

import com.uter.commons.dto.DriverDTO;
import com.uter.commons.entities.Driver;
import com.uter.commons.parser.Parser;
import org.springframework.stereotype.Component;

@Component
public class ParseDriver implements Parser<Driver, DriverDTO> {
    @Override
    public DriverDTO parse(Driver original) {
        DriverDTO driverDTO = new DriverDTO();
        if (original != null) {
            driverDTO.setId(original.getId());
            driverDTO.setName(original.getName());
            driverDTO.setSurname(original.getSurname());
            driverDTO.setLicense(original.getLicense());
        }
        return driverDTO;
    }

    @Override
    public Driver unparse(DriverDTO original) {
        Driver driver = new Driver();
        if (original != null) {
            driver.setId(original.getId());
            driver.setName(original.getName());
            driver.setSurname(original.getSurname());
            driver.setLicense(original.getLicense());
        }
        return driver;
    }
}
