package com.lambdaschool.vertical.jump.service;

import com.lambdaschool.vertical.jump.model.Goal;
import com.lambdaschool.vertical.jump.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "goalService")
public class GoalServiceImpl implements GoalService
{
    @Autowired
    private GoalRepository goalRepository;
    
    @Override
    public void save(Goal goal)
    {
        goalRepository.save(goal);
    }
}
