package com.uter.commons.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
public class TripDTO  {
    private Long id;
    private Date date;
    private List<VehicleDTO> vehicles;
    private List<DriverDTO> drivers;
}


