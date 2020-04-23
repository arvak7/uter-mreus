package com.uter.commons.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "vehicles", schema = "uster")
public class Vehicle implements Serializable {
    @Id
    @Column(name = "vehicle_id")
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
