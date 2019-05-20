package com.lambdaschool.vertical.jump.repository;

import com.lambdaschool.vertical.jump.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
