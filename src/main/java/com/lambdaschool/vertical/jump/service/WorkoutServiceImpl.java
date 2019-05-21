package com.lambdaschool.vertical.jump.service;

import com.lambdaschool.vertical.jump.model.User;
import com.lambdaschool.vertical.jump.model.Workout;
import com.lambdaschool.vertical.jump.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.annotation.W3CDomHandler;
import java.util.ArrayList;
import java.util.List;

@Service(value = "workoutService")
public class WorkoutServiceImpl implements WorkoutService
{
    @Autowired
    private WorkoutRepository workoutRepository;
    
    @Override
    public List<Workout> findAllWorkouts()
    {
        ArrayList<Workout> list = new ArrayList<>();
        workoutRepository.findAll().iterator().forEachRemaining(list::add);
        
        return list;
    }
    
    @Override
    public Workout getToday(User user)
    {
        //Figure out what day of cycle the user is on
        return workoutRepository.findByDay((user.getExercisescompleted() % 5) + 1);
    }
}
