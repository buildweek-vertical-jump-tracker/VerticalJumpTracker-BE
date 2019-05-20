package com.lambdaschool.vertical.jump;

import com.lambdaschool.vertical.jump.model.*;
import com.lambdaschool.vertical.jump.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    WorkoutRepository workoutRepository;
    ExerciseRepository exerciseRepository;
    MeasurementRepository measurementRepository;
    UserRepository userRepository;
    RoleRepository roleRepository;
    
    public SeedData(WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository, MeasurementRepository measurementRepository, UserRepository userRepository, RoleRepository roleRepository)
    {
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
        this.measurementRepository = measurementRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    
    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("user");

        r1 = roleRepository.save(r1);
    
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r1));
        
        Measurement m1 = new Measurement(19, "today", new User());
        ArrayList<Measurement> u1Measures = new ArrayList<>();
        u1Measures.add(m1);
        
        Measurement m2 = new Measurement(22, "May 20, 2019", new User());
        Measurement m3 = new Measurement(23, "June 2, 2019", new User());
        
        ArrayList<Measurement> u2Measures = new ArrayList<>();
        u2Measures.add(m2);
        u2Measures.add(m3);
        
        
        //public User(String username, String height, double vertical, long exercisescompleted, String password, List<UserRoles> userRoles, List<Measurement> measurements)
        User u1 = new User("andrew", "6\'0\"", 18, 0, "password", users, u1Measures);
        m1.setUser(u1);
        
        User u2 = new User("charles", "5\'10\"", 20, 0, "password", users, u2Measures);
        m2.setUser(u2);
        m3.setUser(u2);
        
        
        userRepository.save(u1);
        userRepository.save(u2);
        measurementRepository.save(m2);
        measurementRepository.save(m3);
        measurementRepository.save(m1);
    
        Exercise e1 = new Exercise("Clean Pulls", 3, 3);
        Exercise e2 = new Exercise("Back Squats", 10, 3);
        Exercise e3 = new Exercise("Lunges", 10, 3);
        Exercise e4 = new Exercise("Romanian Deadlifts", 10, 3);
        Exercise e5 = new Exercise("Reverse Hyperextensions", 10, 3);
        Exercise e6 = new Exercise("Squat Jumps", 10, 1);
        Exercise e7 = new Exercise("Ankle Hops", 10, 3);
        
        ArrayList<Exercise> day1 = new ArrayList<>();
        day1.add(e1);
        day1.add(e2);
        day1.add(e3);
        day1.add(e4);
        day1.add(e5);
        day1.add(e6);
        day1.add(e7);
        
        Workout w1 = new Workout(1, day1);
        
        ArrayList<Workout> firstWorkout = new ArrayList<>();
        firstWorkout.add(w1);
        
        for (Exercise e : day1)
        {
            e.setWorkouts(firstWorkout);
            exerciseRepository.save(e);
        }
        workoutRepository.save(w1);
        
        
        
        
        
    
        System.out.println("Made it to seed data");
    }
}
