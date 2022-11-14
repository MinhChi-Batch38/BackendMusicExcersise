package minhchi.com.controllers;

import com.mysql.cj.xdevapi.JsonArray;
import minhchi.com.models.User;
import minhchi.com.repository.UserRepository;
import minhchi.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@EnableJpaRepositories
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping("/get-users")
    @ResponseBody
    public List<User> listAll() {
        List<User> listUsers = repository.findAll();
        //model.addAttribute("listUsers", listUsers);
        return listUsers;
    }

    @PostMapping("/login")
    @ResponseBody
    public User getUser(@RequestBody User obj) {
        String username = obj.getUsername();
        String password = obj.getPassword();
        User check = repository.getUserByUsernameAndPassword(username, password);
        if (check==null) {
            return null;
        }

        return check;
    }

}