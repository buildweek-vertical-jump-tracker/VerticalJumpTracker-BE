package com.lambdaschool.vertical.jump.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
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
    @Cascade({org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JsonIgnoreProperties("exercises")
    private List<Workout> workouts = new ArrayList<>();
    
    public Exercise()
    {
    }
    
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
    
    public void setExerciseid(long exerciseid)
    {
        this.exerciseid = exerciseid;
    }
    
    public List<Workout> getWorkouts()
    {
        return workouts;
    }
    
    public void setWorkouts(List<Workout> workouts)
    {
        this.workouts = workouts;
    }
}
