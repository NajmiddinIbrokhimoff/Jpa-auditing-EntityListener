package uz.ibrokhimoff.jpaauditing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ibrokhimoff.jpaauditing.entity.User;
import uz.ibrokhimoff.jpaauditing.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController{
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/post")
    public ResponseEntity<User> postUser(@RequestBody User user){
        return userService.create(user);
    }
    
    @PutMapping("/put")
    public ResponseEntity<User> putUser(@RequestBody User user){
        return userService.update(user);
    }
}