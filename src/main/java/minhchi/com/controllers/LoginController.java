//package minhchi.com.controllers;
//
////import minhchi.com.security.SecurityUserDetailsService;
//import minhchi.com.models.User;
//import minhchi.com.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.web.bind.annotation.*;
//
//@CrossOrigin(origins = "http://localhost:3000")
//@EnableJpaRepositories
//@RestController
//public class LoginController {
//
////    @Autowired
////    private SecurityUserDetailsService userDetailsManager;
//    @Autowired
//    private UserRepository repository;
//
//    @PostMapping("/login")
//    @ResponseBody
//    public User getUser(@RequestBody User obj) {
//        String username = obj.getUsername();
//        String password = obj.getPassword();
//        User check = repository.getUserByUsernameAndPassword(username, password);
//        if (check==null) {
//            return null;
//        }
//
//        return check;
//    }
////    private String getErrorMessage(HttpServletRequest request, String key) {
////        Exception exception = (Exception) request.getSession().getAttribute(key);
////        String error = "";
////        if (exception instanceof BadCredentialsException) {
////            error = "Invalid username and password!";
////        } else {
////            error = "Invalid username and password!";
////        }
////        return error;
////    }
//}
