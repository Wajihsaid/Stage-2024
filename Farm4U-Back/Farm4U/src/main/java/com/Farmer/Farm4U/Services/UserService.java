package com.Farmer.Farm4U.Services;

import com.Farmer.Farm4U.Entities.User.User;
import com.Farmer.Farm4U.Repositories.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByName(String userName) {
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if (userOptional.isEmpty()) {
            return null;
        } else {
            return userOptional.get();
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addNewUser(@NotNull User user) {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new IllegalStateException("email already in use");
        }
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.findByUserId(userId);
        boolean exists = userRepository.existsById(userId);
        if (exists) {
            userRepository.deleteById(userId);
        } else {
            throw new IllegalStateException("user with " + userId + " not found");
        }
    }
    @Transactional
    public void updateUser ( long userId, String name, String email,String address,long phone){
        User user = userRepository.findByUserId(userId).
                orElseThrow(() -> new IllegalStateException("user with " + userId + " not found"));
        if (name != null && !name.isEmpty() && !user.getUserName().equals(name)) {
            user.setUserName(name);
        }
        if(email != null && !email.isEmpty() && !user.getEmail().equals(email)) {
            Optional<User> userOptional = userRepository.findByEmail(email);
            if (userOptional.isPresent()) {
                throw new IllegalStateException("email already in use");
            }
            user.setEmail(email);
        }
        if(phone > 0 && phone != user.getPhone()) {
            Optional<User> userOptional = userRepository.findByPhone(phone);
            if (userOptional.isPresent()) {
                throw new IllegalStateException("phone already in use");
            }
            user.setPhone(phone);
        }
        if(address != null && !address.isEmpty() && !user.getAddress().equals(address)) {
            Optional<User> userOptional = userRepository.findByAddress(address);
            if (userOptional.isPresent()) {
                throw new IllegalStateException("address already in use");
            }
            user.setEmail(address);
        }
    }
}
