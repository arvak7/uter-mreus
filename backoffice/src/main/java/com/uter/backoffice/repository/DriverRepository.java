package com.uter.backoffice.repository;

import com.uter.commons.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    @Query(value = "select distinct(d) from Driver d left join d.trips t where (t.date < :fecha or t.date > :fecha or t.date is null) and d.license = :licencia" )
    List<Driver> findFreeDriver(@Param("fecha") Date date, @Param("licencia") String licencia);
}
