package com.uter.commons.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleDTO {
    private Long id;
    private String brand;
    private String model;
    private String plate;
    private String licenseRequired;
}
