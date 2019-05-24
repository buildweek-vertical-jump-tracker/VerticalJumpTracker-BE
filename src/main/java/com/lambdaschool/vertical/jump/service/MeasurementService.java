package com.lambdaschool.vertical.jump.service;

import com.lambdaschool.vertical.jump.model.Measurement;

import java.util.List;

public interface MeasurementService
{
    List<Measurement> save(long userid, Measurement measurement);
}
