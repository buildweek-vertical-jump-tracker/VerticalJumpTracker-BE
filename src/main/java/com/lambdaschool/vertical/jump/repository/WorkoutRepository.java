package com.lambdaschool.vertical.jump.repository;

import com.lambdaschool.vertical.jump.model.Workout;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkoutRepository extends CrudRepository<Workout, Long>
{
}
