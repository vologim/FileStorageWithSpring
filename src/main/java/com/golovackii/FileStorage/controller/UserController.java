
package com.golovackii.FileStorage.controller;

import com.golovackii.FileStorage.model.User;
import com.golovackii.FileStorage.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id) {
        User user = service.get(id);
        return user;
    }
    
    @GetMapping
    public List<User> getUsers() {
        return service.getAll();
    }
    
    @PostMapping
    public User saveUser(@RequestBody User user) {
        service.save(user);
        return user;
    }
    
    @PutMapping
    public User updateUser(@RequestBody User user) {
        service.udate(user);
        return user;
    }
    
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable long id) {
        service.delete(id);
        return "User ud: " + id + " was deleted";
    }
}
