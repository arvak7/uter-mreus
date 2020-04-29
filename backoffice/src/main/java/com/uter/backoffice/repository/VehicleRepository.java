package com.uter.backoffice.repository;

import com.uter.commons.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByBrand(String model);

    @Query(value = "select v from Vehicle v left join v.trips t where (t.date < :fecha or t.date > :fecha) or t.date is null" )
    List<Vehicle> findFreeVehicle(@Param("fecha") Date date);

}
