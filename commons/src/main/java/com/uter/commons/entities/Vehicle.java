package com.uter.commons.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
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
    @JsonBackReference
    @ManyToMany(mappedBy = "vehicles")
    private List<Trip> trips;



}
