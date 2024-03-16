package com.billapp.billapp.controller;

import com.billapp.billapp.entities.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.billapp.billapp.services.*;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = { "http://localhost:5173", "https://muraribhuvi.netlify.app" })
public class UserController {

    @Autowired
    public userService us;
    @GetMapping("/getAllUser")
    public List<ApplicationUser> getAllUser(){
        return us.getAllUsers();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){
        us.deleteById(id);
    }
}
