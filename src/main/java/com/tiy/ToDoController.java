package com.tiy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brice on 9/15/16.
 */

@Controller
public class ToDoController {

    @Autowired
    ToDoRepo todos;

    @RequestMapping(path="/", method = RequestMethod.GET)
    public String home(Model model) {

        Iterable<ToDo> allTodos = todos.findAll();
        List<ToDo> toDoList = new ArrayList<>();
        for (ToDo currentToDo : allTodos) {
            toDoList.add(currentToDo);
        }
        model.addAttribute("todos", toDoList);

        return "home";
    }

    @RequestMapping(path="/add-todo", method = RequestMethod.POST)
    public String addToDo(String toDoText) {
        ToDo myToDo = new ToDo(toDoText);
        todos.save(myToDo);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public String deleteToDo(Model model, Integer todoID) {
        if (todoID != null) {
            todos.delete(todoID);
        }

        return "redirect:/";
    }

//    @RequestMapping(path = "/modify", method = RequestMethod.GET)
//    public String modify(Model model, Integer todoID) {
//        if (todoID != null) {
//            ToDo todo = todos.findOne(todoID);
//            todos.name = " ** " + game.name;
//            games.save(game);
//        }
//
//        return "redirect:/";
//    }

}
