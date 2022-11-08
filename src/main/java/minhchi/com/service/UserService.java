package minhchi.com.service;

import minhchi.com.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    User getUserById(int id);
}