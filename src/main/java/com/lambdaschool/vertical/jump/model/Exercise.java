package com.lambdaschool.vertical.jump.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "exercises")
public class Exercise
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long exerciseid;
    
    private String exerciseName;
    private int reps;
    private int sets;
    
    @ManyToMany(mappedBy = "exercises")
    @JsonIgnoreProperties("exercises")
    private List<Workout> workouts;
    
    public Exercise(String exerciseName, int reps, int sets)
    {
        this.exerciseName = exerciseName;
        this.reps = reps;
        this.sets = sets;
    }
    
    public long getExerciseid()
    {
        return exerciseid;
    }
    
    public String getExerciseName()
    {
        return exerciseName;
    }
    
    public void setExerciseName(String exerciseName)
    {
        this.exerciseName = exerciseName;
    }
    
    public int getReps()
    {
        return reps;
    }
    
    public void setReps(int reps)
    {
        this.reps = reps;
    }
    
    public int getSets()
    {
        return sets;
    }
    
    public void setSets(int sets)
    {
        this.sets = sets;
    }
}
