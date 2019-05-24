package com.lambdaschool.vertical.jump.service;

import com.lambdaschool.vertical.jump.model.User;
import com.lambdaschool.vertical.jump.model.Workout;

import java.util.List;

public interface WorkoutService
{
    List<Workout> findAllWorkouts();
    Workout getToday(User user);
}
