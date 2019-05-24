package com.lambdaschool.vertical.jump.repository;

import com.lambdaschool.vertical.jump.model.Exercise;
import org.springframework.data.repository.CrudRepository;

public interface ExerciseRepository extends CrudRepository<Exercise, Long>
{
}
