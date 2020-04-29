package com.uter.commons.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Getter
@Setter
public class TripDTO  {
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date date;
    @NotEmpty
    @NotNull
    private List<VehicleDTO> vehicles;
    @NotEmpty
    @NotNull
    private List<DriverDTO> drivers;
}


