package com.Farmer.Farm4U.Controlleurs;

import com.Farmer.Farm4U.Entities.User.User;
import com.Farmer.Farm4U.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/user")
@CrossOrigin("http://localhost:5173")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @PostMapping()
    public boolean registerNewUser(@RequestBody User user){
        userService.addNewUser(user);
        return true;
    }
    @DeleteMapping(path = "{userId}")
    public  void deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
    }
    @PutMapping(path = "{userId}")
    public void updateUser(@PathVariable("userId") Long userId,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) String address,
                           @RequestBody long phone){
        userService.updateUser(userId,name,email,address,phone);
    }
}
