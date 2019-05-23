package com.lambdaschool.vertical.jump.service;

import com.lambdaschool.vertical.jump.model.Measurement;
import com.lambdaschool.vertical.jump.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "measurementService")
public class MeasurementServiceImpl implements MeasurementService
{
    @Autowired
    MeasurementRepository measurementRepository;
    
    @Transactional
    @Override
    public List<Measurement> save(long userid, Measurement measurement)
    {
        measurementRepository.save(measurement);
        List<Measurement> list = new ArrayList<>();
        measurementRepository.findAllByUser(userid).iterator().forEachRemaining(list::add);
        return list;
    }
}
