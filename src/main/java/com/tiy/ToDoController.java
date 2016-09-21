package com.tiy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brice on 9/15/16.
 */

@Controller
public class ToDoController {

    @Autowired
    ToDoRepo todos;

    @Autowired
    UserRepo users;

    @PostConstruct
    public void init() {
        if (users.count() == 0) {
            User user = new User();
            user.name = "brice";
            user.password = "derp";
            users.save(user);
        }
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName, String password) throws Exception {
        User user = users.findFirstByName(userName);
        if (user == null) {
            throw new Exception("Username not found");
        } else if (!password.equals(user.getPassword())) {
            throw new Exception("Incorrect password");
        } else {
            session.setAttribute("user", user);
        }
        return "redirect:/todos";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(path="/", method = RequestMethod.GET)
    public String home(HttpSession session, Model model) {
        List<ToDo> toDoList = new ArrayList<ToDo>();
        if (session.getAttribute("user") != null) {
            model.addAttribute("user", session.getAttribute("user"));
        }

        User myUser = (User)session.getAttribute("user");
        if (myUser != null) {
            toDoList = todos.findByUser(myUser);
        }
//        else {
//            Iterable<ToDo> alltodos = todos.findAll();
//            for (ToDo currentToDo : alltodos) {
//                toDoList.add(currentToDo);
//            }
//        }
        model.addAttribute("todos", toDoList);

        return "home";
    }

    @RequestMapping(path="/add-todo", method = RequestMethod.POST)
    public String addToDo(HttpSession session, String toDoText) {
        User user = (User) session.getAttribute("user");
        ToDo myToDo = new ToDo(toDoText, user);
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

    @RequestMapping(path = "/todos", method = RequestMethod.GET)
    public String jsonToDos() {
        return "todo";
    }

//    @RequestMapping(path = "/create-user", method = RequestMethod.GET)
//    public String createNewUser() {
//        if (user == null) {
//            user = new User(userName, password);
//            users.save(user);
//        }
//        else
//    }

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
