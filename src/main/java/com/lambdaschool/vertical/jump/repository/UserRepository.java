package com.lambdaschool.vertical.jump.repository;

import com.lambdaschool.vertical.jump.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
    User findByUserid(long id);
    
    @Modifying
    @Query(value = "UPDATE users SET exercisescompleted=:workoutcompleted WHERE userid=:userid", nativeQuery = true)
    void incrementWorkout(long userid, int workoutcompleted);
}
