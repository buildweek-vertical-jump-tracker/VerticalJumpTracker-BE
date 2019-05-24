package com.lambdaschool.vertical.jump.controller;

import com.lambdaschool.vertical.jump.model.Goal;
import com.lambdaschool.vertical.jump.model.Measurement;
import com.lambdaschool.vertical.jump.model.User;
import com.lambdaschool.vertical.jump.model.Workout;
import com.lambdaschool.vertical.jump.service.GoalService;
import com.lambdaschool.vertical.jump.service.MeasurementService;
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
    
    @Autowired
    private MeasurementService measurementService;
    
    @ApiOperation(value = "Return current user", response = User.class)
    @GetMapping(value = "/users/me", produces = {"application/json"})
    public ResponseEntity<?> findCurrentUser(Authentication authentication)
    {
        String username = authentication.getName();
        
        return new ResponseEntity<>(userService.findUserByUsername(username), HttpStatus.OK);
    }
    
    @ApiOperation(value = "Returns a list of all workouts and their assigned exercises", response = Workout.class, responseContainer = "list")
    @GetMapping(value = "/workouts/all", produces = {"application/json"})
    public ResponseEntity<?> getAllWorkouts()
    {
        return new ResponseEntity<>(workoutService.findAllWorkouts(), HttpStatus.OK);
    }
    
    @ApiOperation(value = "Add a new user", notes = "Requires \"username\", \"password\", and \"height\" as strings")
    @PostMapping(value = "/newuser", consumes = {"application/json"})
    public ResponseEntity<?> addUser(@Valid @RequestBody User newUser) throws URISyntaxException
    {
        //requires: Username, password, height (String)
        System.out.println(newUser.getPassword());
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
    
    @ApiOperation(value = "Return's the user's currently assigned workout", response = Workout.class)
    @GetMapping(value = "/workouts/today", produces = {"application/json"})
    public ResponseEntity<?> getWorkoutToday(Authentication authentication)
    {
        String username = authentication.getName();
        
        //String username = "charles";
        
        return new ResponseEntity<>(workoutService.getToday(userService.findUserByUsername(username)), HttpStatus.OK);
    }
    
    @ApiOperation(value = "Iterates user's exercises completed value, changing data received from \"/workouts/today\" endpoint", response = User.class)
    @GetMapping(value = "/workouts/{id}", produces = {"application/json"})
    public ResponseEntity<?> incrementWorkout(@PathVariable long id)
    {
        userService.incrementWorkout(id);
        User updated = userService.findUserById(id);
        updated.setExercisescompleted(updated.getExercisescompleted() + 1);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
    
    @ApiOperation(value = "Adds new goal for user with id {id}", notes = "Requires \"goalvertical\" as an integer")
    @PostMapping(value = "/goals/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addGoals(@PathVariable long id, @RequestBody Goal goalVertical)
    {
        System.out.println(goalVertical.toString());
        User goalUser = userService.findUserById(id);

        goalVertical.setUser(goalUser);

        goalService.save(goalVertical);
        
        return new ResponseEntity<>(goalService.getGoalsByUserId(id), HttpStatus.OK);
    }
    
    @ApiOperation(value = "Updates the value and completion status of an existing goal", notes = "Requires \"goalvertical\" as an integer and \"completed\" as a boolean")
    @PutMapping(value = {"/goals/update/{goalid}"}, consumes = {"application/json"})
    public ResponseEntity<?> updateGoal(@PathVariable long goalid, @RequestBody Goal goalVertical)
    {
        goalService.updateGoal(goalid, goalVertical);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @ApiOperation(value = "Deletes the selected goal")
    @DeleteMapping(value = "/goals/delete/{goalid}")
    public ResponseEntity<?> deleteGoal(@PathVariable long goalid)
    {
        goalService.deleteGoal(goalid);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @ApiOperation(value = "Adds a workout plan to the user with given length and interval between reminders for completed plan", notes = "Any new workout plans added will override existing plans")
    @GetMapping(value = "/workouts/plan/{userid}/{planlength}/{interval}")
    public ResponseEntity<?> addPlan(@PathVariable long userid, @PathVariable int planlength, @PathVariable int interval)
    {
        userService.addPlan(userid, planlength, interval);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @ApiOperation(value = "Adds a new measurement to a user", notes = "Requires \"vertical\" as an integer and toggles user's needmeasurement boolean to false")
    @PostMapping(value = "/measurements/{userid}", consumes = {"application/json"})
    public ResponseEntity<?> addMeasurement(@PathVariable long userid, @RequestBody Measurement measurement)
    {
        User user = userService.findUserById(userid);
        measurement.setUser(user);
        return new ResponseEntity<>(measurementService.save(userid, measurement), HttpStatus.OK);
    }
}
