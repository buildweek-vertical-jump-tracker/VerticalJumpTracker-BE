package com.lambdaschool.vertical.jump.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workouts")
public class Workout extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long workoutid;
    
    private int day;
    
    @ManyToMany
    @Cascade({CascadeType.MERGE, CascadeType.SAVE_UPDATE})
    @JoinTable(name = "workoutexercises",
                joinColumns = {@JoinColumn(name = "workoutid")},
                inverseJoinColumns = {@JoinColumn(name = "exerciseid")})
    @JsonIgnoreProperties("workouts")
    private List<Exercise> exercises = new ArrayList<>();
    
    public Workout()
    {
    }
    
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
    
    public List<Exercise> getExercises()
    {
        return exercises;
    }
    
    public void setExercises(ArrayList<Exercise> exercises)
    {
        this.exercises = exercises;
    }
}
