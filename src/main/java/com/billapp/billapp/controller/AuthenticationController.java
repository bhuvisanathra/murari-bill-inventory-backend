package com.billapp.billapp.controller;

import com.billapp.billapp.dto.loginResponseDTO;
import com.billapp.billapp.dto.registrationDTO;
import com.billapp.billapp.entities.ApplicationUser;
import com.billapp.billapp.services.authenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = { "http://localhost:5173", "https://muraribhuvi.netlify.app" })
public class AuthenticationController {

    @Autowired
    private authenticationService authenticationService;

//    Register User
    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody registrationDTO body){
        return authenticationService.registerUser(body.getUsername(),body.getPassword(),body.getEmail());
    }

    @PostMapping("/login")
    public loginResponseDTO loginUser(@RequestBody registrationDTO body){
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }
}
