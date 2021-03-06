package com.lambdaschool.vertical.jump.service;


import com.lambdaschool.vertical.jump.model.User;

import java.util.List;

public interface UserService {
    
    List<User> findAll();
    
    User findUserByUsername(String username);
    
    User findUserById(long id);
    
    void delete(long id);
    
    User save(User user);
    
    User update(User user, long id);
    
    void incrementWorkout(long id);

    void addPlan(long id, int length, int increment);
}
