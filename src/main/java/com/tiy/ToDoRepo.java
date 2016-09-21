package com.tiy;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Brice on 9/15/16.
 */


public interface ToDoRepo extends CrudRepository<ToDo, Integer> {
    List<ToDo> findByUser(User user);
    List<ToDo> findAllByOrderById();
}