package com.billapp.billapp.services;

import com.billapp.billapp.dao.userDao;
import com.billapp.billapp.entities.ApplicationUser;
import com.billapp.billapp.entities.Role;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class userService implements UserDetailsService {
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    userDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("In the user detail service");
        return userDao.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User doesnot Exists"));
    }

    public List<ApplicationUser> getAllUsers(){
        return userDao.findAll();
    }

    public void deleteById(Integer id){
        userDao.deleteById(id);
    }


}
