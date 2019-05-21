package com.lambdaschool.vertical.jump.repository;

import com.lambdaschool.vertical.jump.model.Goal;
import org.springframework.data.repository.CrudRepository;

public interface GoalRepository extends CrudRepository<Goal, Long>
{
}
