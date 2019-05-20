package com.lambdaschool.vertical.jump.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "workouts")
public class Workout
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long workoutid;
    
    private int day;
    
    @ManyToMany
    @JoinTable(name = "workoutexercises",
                joinColumns = {@JoinColumn(name = "workoutid")},
                inverseJoinColumns = {@JoinColumn(name = "exerciseid")})
    @JsonIgnoreProperties("workouts")
    private ArrayList<Exercise> exercises;
    
    public Workout(int day, ArrayList<Exercise> exercises)
    {
        this.day = day;
        this.exercises = exercises;
    }
    
    public long getWorkoutid()
    {
        return workoutid;
    }
    
    public int getDay()
    {
        return day;
    }
    
    public void setDay(int day)
    {
        this.day = day;
    }
    
    public void setWorkoutid(long workoutid)
    {
        this.workoutid = workoutid;
    }
    
    public ArrayList<Exercise> getExercises()
    {
        return exercises;
    }
    
    public void setExercises(ArrayList<Exercise> exercises)
    {
        this.exercises = exercises;
    }
}
