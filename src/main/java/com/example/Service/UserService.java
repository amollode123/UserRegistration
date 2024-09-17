package com.example.Service;

import org.springframework.stereotype.Service;
import com.example.Entity.User;


public interface UserService {
User save(User user);
User validateUser(String email, String password);
}
