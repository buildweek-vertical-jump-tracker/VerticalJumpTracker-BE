package com.lambdaschool.vertical.jump.service;

import com.lambdaschool.vertical.jump.model.Goal;
import com.lambdaschool.vertical.jump.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    
    @Transactional
    @Override
    public void updateGoal(long goalid, Goal goal)
    {
        goalRepository.updateGoals(goalid, goal.isCompleted(), goal.getGoalvertical());
    }
    
    @Transactional
    @Override
    public void deleteGoal(long goalid)
    {
        goalRepository.deleteById(goalid);
    }
    
    @Override
    public List<Goal> getGoalsByUserId(long userid)
    {
        ArrayList<Goal> list = new ArrayList<>();
        goalRepository.findAllByUser(userid).iterator().forEachRemaining(list::add);
        return list;
    }
}
