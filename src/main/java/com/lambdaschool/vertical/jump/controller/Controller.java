package com.lambdaschool.vertical.jump.controller;

import com.lambdaschool.vertical.jump.model.Goal;
import com.lambdaschool.vertical.jump.model.User;
import com.lambdaschool.vertical.jump.service.GoalService;
import com.lambdaschool.vertical.jump.service.UserService;
import com.lambdaschool.vertical.jump.service.WorkoutService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping()
public class Controller
{
    @Autowired
    private UserService userService;
    
    @Autowired
    private WorkoutService workoutService;
    
    @Autowired
    private GoalService goalService;
    
    @ApiOperation(value = "Return current user", response = User.class)
    @GetMapping(value = "/users/me", produces = {"application/json"})
    public ResponseEntity<?> findCurrentUser(/*Authentication authentication*/)
    {
        //String username = authentication.getName();
        
        return new ResponseEntity<>(userService.findUserByUsername("charles"), HttpStatus.OK);
    }
    
    @GetMapping(value = "/workouts/all", produces = {"application/json"})
    public ResponseEntity<?> getAllWorkouts()
    {
        return new ResponseEntity<>(workoutService.findAllWorkouts(), HttpStatus.OK);
    }
    
    @PostMapping(value = "/users", consumes = {"application/json"})
    public ResponseEntity<?> addUser(@Valid @RequestBody User newUser) throws URISyntaxException
    {
        newUser = userService.save(newUser);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newUser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
    
    @GetMapping(value = "/workouts/today", produces = {"application/json"})
    public ResponseEntity<?> getWorkoutToday(/*Authentication authentication*/)
    {
        //String username = authentication.getName()
        
        String username = "charles";
        
        return new ResponseEntity<>(workoutService.getToday(userService.findUserByUsername(username)), HttpStatus.OK);
    }
    
    @GetMapping(value = "/workouts/{id}", produces = {"application/json"})
    public ResponseEntity<?> incrementWorkout(@PathVariable long id)
    {
        userService.incrementWorkout(id);
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }
    
    @PostMapping(value = "/goals/{id}", consumes = {"application/json"})
    public ResponseEntity<?> addGoals(@PathVariable long id, @RequestBody Goal goalVertical)
    {
        System.out.println(goalVertical.toString());
//        User goalUser = userService.findUserById(id);
//
//        Goal newGoal = new Goal(goalVertical, goalUser);
//
//        goalService.save(newGoal);
        
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
