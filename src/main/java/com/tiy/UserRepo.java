package com.tiy;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Brice on 9/15/16.
 */
public interface UserRepo extends CrudRepository<User, Integer> {
    User findFirstByName(String name);
}

