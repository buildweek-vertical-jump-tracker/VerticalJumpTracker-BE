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
    GoalRepository goalRepository;
    
    public SeedData(WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository, MeasurementRepository measurementRepository, UserRepository userRepository, RoleRepository roleRepository, GoalRepository goalRepository)
    {
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
        this.measurementRepository = measurementRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.goalRepository = goalRepository;
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
        User u1 = new User("andrew", "6\'0\"", "password", users, u1Measures);
        m1.setUser(u1);
        
        User u2 = new User("charles", "5\'10\"", "password", users, u2Measures);
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
        Exercise e8 = new Exercise("Bench Press", 10, 3);
        Exercise e9 = new Exercise("Day off", 0, 0);
        
        ArrayList<Exercise> day1 = new ArrayList<>();
        ArrayList<Exercise> day2 = new ArrayList<>();
        ArrayList<Exercise> day3 = new ArrayList<>();
        ArrayList<Exercise> day4 = new ArrayList<>();
        ArrayList<Exercise> day5 = new ArrayList<>();
        
        day1.add(e1);
        day1.add(e2);
        day2.add(e3);
        day2.add(e4);
        day3.add(e9);
        day4.add(e5);
        day4.add(e6);
        day5.add(e7);
        day5.add(e8);
        
        Workout w1 = new Workout(1, day1);
        Workout w2 = new Workout(2, day2);
        Workout w3 = new Workout(3, day3);
        Workout w4 = new Workout(4, day4);
        Workout w5 = new Workout(5, day5);
        
        ArrayList<Workout> firstWorkout = new ArrayList<>();
        firstWorkout.add(w1);
        
        exerciseRepository.save(e1);
        exerciseRepository.save(e2);
        exerciseRepository.save(e3);
        exerciseRepository.save(e4);
        exerciseRepository.save(e5);
        exerciseRepository.save(e6);
        exerciseRepository.save(e7);
        exerciseRepository.save(e8);
        exerciseRepository.save(e9);
        
        
        workoutRepository.save(w1);
        workoutRepository.save(w2);
        workoutRepository.save(w3);
        workoutRepository.save(w4);
        workoutRepository.save(w5);
        
        Goal g1 = new Goal(24, u1);
        ArrayList<Goal> u1goals = new ArrayList<>();
        u1goals.add(g1);
        
        u1.setGoals(u1goals);
        
        goalRepository.save(g1);
        
        Goal g2 = new Goal(24, u2);
        Goal g3 = new Goal(26, u2);
        
        ArrayList<Goal> u2goals = new ArrayList<>();
        u2goals.add(g2);
        u2goals.add(g3);
        
        u2.setGoals(u2goals);
        
        goalRepository.save(g2);
        goalRepository.save(g3);
        

    }
}
