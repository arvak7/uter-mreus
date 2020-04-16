package com.uter.backoffice.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String brand;
    @NotBlank
    private String model;
    @Column(unique=true)
    @NotBlank
    private String plate;
    @NotBlank
    @Size(min = 1, max = 1)
    @Column(name = "licenserequired")
    private String licenseRequired;


}
