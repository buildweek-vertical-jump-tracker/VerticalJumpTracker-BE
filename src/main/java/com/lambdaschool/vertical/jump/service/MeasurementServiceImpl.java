package com.lambdaschool.vertical.jump.service;

import com.lambdaschool.vertical.jump.model.Measurement;
import com.lambdaschool.vertical.jump.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "measurementService")
public class MeasurementServiceImpl implements MeasurementService
{
    @Autowired
    MeasurementRepository measurementRepository;
    
    @Override
    public void save(Measurement measurement)
    {
        measurementRepository.save(measurement);
    }
}
