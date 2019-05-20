package com.lambdaschool.vertical.jump.controller;

import com.lambdaschool.vertical.jump.service.UserService;
import com.lambdaschool.vertical.jump.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class Controller
{
    @Autowired
    private UserService userService;
    
    @Autowired
    private WorkoutService workoutService;
    
    @GetMapping("/users/me")
    public ResponseEntity<?> findCurrentUser(Authentication authentication)
    {
        String username = authentication.getName();
        System.out.println(username);
        
        return new ResponseEntity<>(userService.findUserByUsername(username), HttpStatus.OK);
    }
    
    @GetMapping("/workouts/all")
    public ResponseEntity<?> getAllWorkouts()
    {
        return new ResponseEntity<>(workoutService.findAllWorkouts(), HttpStatus.OK);
    }
}
