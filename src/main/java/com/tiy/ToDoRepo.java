package com.tiy;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Brice on 9/15/16.
 */


public interface ToDoRepo extends CrudRepository<ToDo, Integer> {

    List<ToDo> findByUser(User user);

//    @Query("SELECT g FROM ToDo g WHERE g.name LIKE ?1%")
//    List<ToDo> findByNameStartsWith(String name);
}