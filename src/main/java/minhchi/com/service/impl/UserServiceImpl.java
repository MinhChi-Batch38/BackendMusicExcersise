//package minhchi.com.service.impl;
//
//import minhchi.com.MusicExcesiseApplication;
//import minhchi.com.controllers.UserController;
//import minhchi.com.models.User;
//import minhchi.com.repository.UserRepository;
//import minhchi.com.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//@Service
//@Repository
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private static UserRepository userRepository;
//
//    @Override
//    public List<User> getAllUser() {
//        userRepository.findAll();
//        return userRepository.findAll();
//    }
//
//    @Override
//    public User getUserById(int id) {
//        return userRepository.findUserById(id);
//    }
//
//    public static void main(String[] args) {
//        System.out.println(userRepository.findUserById(1));
//    }
//}
