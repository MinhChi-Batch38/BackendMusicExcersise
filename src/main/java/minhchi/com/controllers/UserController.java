//package minhchi.com.controllers;
//
////import com.mysql.cj.xdevapi.JsonArray;
//////import minhchi.com.models.User;
//import minhchi.com.models.UserMongo;
//import minhchi.com.repository.MongoUserRepository;
////import minhchi.com.repository.UserRepository;
////import minhchi.com.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//////import org.springframework.stereotype.Controller;
//////import org.springframework.ui.Model;
////import org.springframework.stereotype.Component;
////import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//
//import java.util.List;
//
//@CrossOrigin(origins = "http://localhost:3000")
////@EnableJpaRepositories
//@RestController
//@RequestMapping("/users")
//public class UserController {
//    @Autowired
//    private MongoUserRepository repository;
//
//    @GetMapping("/get-users")
//    @ResponseBody
//    public List<UserMongo> listAll() {
//        List<UserMongo> listUsers = repository.findAll();
//        //model.addAttribute("listUsers", listUsers);
//        return listUsers;
//    }
//
//    @PostMapping("/login")
//    @ResponseBody
//    public UserMongo getUser(@RequestBody UserMongo obj) {
//        String username = obj.getUsername();
//        String password = obj.getPassword();
//        UserMongo check = repository.findUserMongoByUsernameAndPassword(username, password);
//        if (check==null) {
//            return null;
//        }
//
//        return check;
//    }
//
//}