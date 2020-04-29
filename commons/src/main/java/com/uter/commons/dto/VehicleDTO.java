package com.uter.commons.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class VehicleDTO {
    private Long id;
    @NotBlank
    private String brand;
    @NotBlank
    private String model;
    @NotBlank
    private String plate;
    @NotBlank
    @Size(min = 1, max = 1)
    private String licenseRequired;
}
