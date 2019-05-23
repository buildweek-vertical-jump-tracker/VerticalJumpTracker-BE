package com.lambdaschool.vertical.jump.service;

import com.lambdaschool.vertical.jump.exception.ResourceNotFoundException;
import com.lambdaschool.vertical.jump.model.Measurement;
import com.lambdaschool.vertical.jump.model.Role;
import com.lambdaschool.vertical.jump.model.User;
import com.lambdaschool.vertical.jump.model.UserRoles;
import com.lambdaschool.vertical.jump.repository.RoleRepository;
import com.lambdaschool.vertical.jump.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService
{
    
    @Autowired
    private UserRepository userrepos;
    
    @Autowired
    private RoleRepository rolerepos;
    
    @Transactional
    public UserDetails loadUserByUsername(String username) throws ResourceNotFoundException
    {
        User user = userrepos.findByUsername(username);
        if (user == null)
        {
            throw new ResourceNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthority());
    }
    
    public User findUserByUsername(String username) throws ResourceNotFoundException
    {
        if(userrepos.findByUsername(username) != null)
        {
            User me = userrepos.findByUsername(username);
            if (me.getMeasurements().size() != 0) // sort measurements for chart in order
            {
                List<Measurement> sorted = me.getMeasurements();
                Collections.sort(sorted, (m1, m2) -> m1.getRawDate().compareTo(m2.getRawDate()));
                me.setMeasurements(sorted);
            }
            return me;
        } else
        {
            throw new ResourceNotFoundException("Could not find user: " + username);
        }
    }
    
    public User findUserById(long id) throws ResourceNotFoundException
    {
        return userrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }
    
    public List<User> findAll()
    {
        List<User> list = new ArrayList<>();
        userrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
    
    @Override
    public void delete(long id)
    {
        if (userrepos.findById(id).isPresent())
        {
            userrepos.deleteById(id);
        }
        else
        {
            throw new ResourceNotFoundException(Long.toString(id));
        }
    }
    
    @Transactional
    @Override
    public User save(User user)
    {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPasswordNoEncrypt(user.getPassword());
        
        newUser.setHeight(user.getHeight());
        
        return userrepos.save(newUser);
    }
    
    @Transactional
    @Override
    public User update(User user, long id)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userrepos.findByUsername(authentication.getName());
        
        if (currentUser != null)
        {
            if (id == currentUser.getUserid())
            {
                if (user.getUsername() != null)
                {
                    currentUser.setUsername(user.getUsername());
                }
                
                if (user.getPassword() != null)
                {
                    currentUser.setPasswordNoEncrypt(user.getPassword());
                }
                
                if (user.getUserRoles().size() > 0)
                {
                    // with so many relationships happening, I decided to go
                    // with old school queries
                    // delete the old ones
                    rolerepos.deleteUserRolesByUserId(currentUser.getUserid());
                    
                    // add the new ones
                    for (UserRoles ur : user.getUserRoles())
                    {
                        rolerepos.insertUserRoles(id, ur.getRole().getRoleid());
                    }
                }
                return userrepos.save(currentUser);
            }
            else
            {
                throw new ResourceNotFoundException(Long.toString(id) + " Not current user");
            }
        }
        else
        {
            throw new ResourceNotFoundException(authentication.getName());
        }
        
    }
    
    @Override
    @Transactional
    public void incrementWorkout(long id)
    {
        // get the current user
        User incrementing = userrepos.findByUserid(id);
        int endpoint = incrementing.getEndpoint();
        int completed = incrementing.getExercisescompleted();
        int interval = incrementing.getInterval();
        int initial = incrementing.getStartpoint();
        //figure out if they have a workout plan
        if (endpoint != 0)
        {
            //if here, they must have a plan, need to find out if it's active or completed
            if(endpoint == completed)
            {
                //needs measurement
                userrepos.needMeasurement(id);
            }else if(endpoint > completed)
            {
                //check if interval has passed
                if((completed - endpoint) % interval == 0)
                {
                    //needs measurement
                    userrepos.needMeasurement(id);
                }
            }
            else
            {
                //check if halfway through program
                int portionDone = completed - initial;
                int portionToGo = endpoint - completed;
                if(portionDone == portionToGo || portionDone - portionToGo == 1)
                {
                    //needs measurement
                    userrepos.needMeasurement(id);
                }
            }
        }
        userrepos.incrementWorkout(id, completed + 1);
    }
    
    @Override
    @Transactional
    public void addPlan(long id, int length, int interval)
    {
        User planner = userrepos.findByUserid(id);
        
        int startpoint = planner.getExercisescompleted();
        int endpoint = startpoint + length;
        
        userrepos.addPlan(id, startpoint, endpoint, interval);
    }
}
