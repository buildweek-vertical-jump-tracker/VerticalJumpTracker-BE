package com.lambdaschool.vertical.jump.service;

import com.lambdaschool.vertical.jump.model.Goal;

import java.util.List;

public interface GoalService
{
    void save(Goal goal);
    
    void updateGoal(long goalid, Goal goal);
    
    void deleteGoal(long goalid);
    
    List<Goal> getGoalsByUserId(long userid);
}
