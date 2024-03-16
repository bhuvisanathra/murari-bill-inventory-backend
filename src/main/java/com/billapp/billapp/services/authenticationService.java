package com.billapp.billapp.services;

import com.billapp.billapp.dto.loginResponseDTO;
import com.billapp.billapp.entities.ApplicationUser;
import com.billapp.billapp.entities.Role;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.billapp.billapp.dao.*;

import org.springframework.security.core.AuthenticationException;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class authenticationService {

    @Autowired
    private userDao userDao;

    @Autowired
    private roleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private tokenService tokenService;

//    Register method implementation
    public ApplicationUser registerUser(String username,String password,String email){
        String encodedPassword =passwordEncoder.encode(password);
        Role userRole=roleDao.findByAuthority("USER").get();

        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        return userDao.save(new ApplicationUser(0,username,encodedPassword,email,authorities));

    }

    public loginResponseDTO loginUser(String username,String password){
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJWT(auth);

            return new loginResponseDTO(userDao.findByUsername(username).get(), token);

        } catch(AuthenticationException e){
            return new loginResponseDTO(null, "");
        }
    }

}

