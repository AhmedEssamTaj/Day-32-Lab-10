package com.example.day32lab10.Service;

import com.example.day32lab10.Model.User;
import com.example.day32lab10.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public boolean updateUser(Integer id, User user) {

        for (User u : userRepository.findAll()) {
            if (u.getId() == id) {
                u.setName(user.getName());
                u.setEmail(user.getEmail());
                u.setPassword(user.getPassword());
                u.setAge(user.getAge());
                u.setRole(user.getRole());
                userRepository.save(u);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(Integer id) {
        for (User u : userRepository.findAll()) {
            if (u.getId() == id) {
                userRepository.delete(u);
                return true;
            }
        }
        return false;
    }
}
