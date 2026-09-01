package com.prathm.App.controller;


import com.prathm.App.Entity.User;
import com.prathm.App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController_v2 {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllusers(){
        return userService.getAll();
    }

   @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveEntry(user);
   }

   @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
        User userInDb = userService.findByUsername(userName);
        if(userInDb != null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
        }
        return new  ResponseEntity<>(userInDb, HttpStatus.OK);
   }


}