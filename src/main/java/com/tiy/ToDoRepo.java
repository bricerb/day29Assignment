package com.tiy;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Brice on 9/15/16.
 */


public interface ToDoRepo extends CrudRepository<ToDo, Integer> {
}