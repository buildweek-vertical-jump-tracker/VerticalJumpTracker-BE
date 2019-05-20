package com.lambdaschool.vertical.jump.repository;

import com.lambdaschool.vertical.jump.model.Measurement;
import org.springframework.data.repository.CrudRepository;

public interface MeasurementRepository extends CrudRepository<Measurement, Long>
{
}
