package com.lambdaschool.vertical.jump.repository;

import com.lambdaschool.vertical.jump.model.Goal;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GoalRepository extends CrudRepository<Goal, Long>
{
    @Modifying
    @Query(value = "UPDATE GOALS SET COMPLETED=:completed, GOALVERTICAL=:goalvertical WHERE GOALID=:goalid", nativeQuery = true)
    void updateGoals(long goalid, boolean completed, double goalvertical);
    
    @Query(value = "SELECT * FROM goals WHERE userid=:userid", nativeQuery = true)
    List<Goal> findAllByUser(long userid);
}
