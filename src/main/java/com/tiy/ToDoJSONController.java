package com.tiy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Brice on 9/19/16.
 */

@RestController
public class ToDoJSONController {

    @Autowired
    ToDoRepo todos;

    @RequestMapping(path = "/todos.json", mehtod = RequestMethod.GET)
    public
}
