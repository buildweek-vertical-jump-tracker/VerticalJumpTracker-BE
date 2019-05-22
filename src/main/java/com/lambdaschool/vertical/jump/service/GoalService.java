package com.lambdaschool.vertical.jump.service;

import com.lambdaschool.vertical.jump.model.Goal;

public interface GoalService
{
    void save(Goal goal);
    
    void updateGoal(long goalid, Goal goal);
    
    void deleteGoal(long goalid);
}
