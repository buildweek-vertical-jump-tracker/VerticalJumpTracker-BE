package com.lambdaschool.vertical.jump.repository;

import com.lambdaschool.vertical.jump.model.Measurement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MeasurementRepository extends CrudRepository<Measurement, Long>
{
    @Query(value = "SELECT * FROM measurements WHERE userid=:userid", nativeQuery = true)
    List<Measurement> findAllByUser(long userid);
}
