package com.tiy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brice on 9/19/16.
 */

@RestController
public class ToDoJSONController {

    @Autowired
    ToDoRepo todos;

    @RequestMapping(path = "/todos.json", method = RequestMethod.GET)
    public List<ToDo> getToDos() {
        List<ToDo> todoList = new ArrayList<ToDo>();
        Iterable<ToDo> allToDos = todos.findAllByOrderById();

        for (ToDo currentToDo : allToDos) {
            todoList.add(currentToDo);
        }
        return todoList;
    }

    @RequestMapping(path = "/addToDo.json", method = RequestMethod.POST)
    public List<ToDo> addToDo(HttpSession session, @RequestBody ToDo myToDo) throws Exception {
        User user = (User)session.getAttribute("user");

        if (user == null) {
            throw new Exception("Unable to add game without an active user in the session");
        }
        myToDo.user = user;

        todos.save(myToDo);

        return getToDos();
    }

    @RequestMapping(path = "/delete.json", method = RequestMethod.GET)
    public List<ToDo> deleteToDo(int todoID) {
        ToDo myToDo = todos.findOne(todoID);
        todos.delete(myToDo.id);

        return getToDos();
    }

    @RequestMapping(path = "/toggleToDo.json", method = RequestMethod.GET)
    public List<ToDo> toggleToDo(int todoID) {
        ToDo myToDo = todos.findOne(todoID);
        myToDo.isDone = !myToDo.isDone;
        todos.save(myToDo);

        return getToDos();
    }
}