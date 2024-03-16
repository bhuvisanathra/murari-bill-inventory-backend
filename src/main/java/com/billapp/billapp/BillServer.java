package com.billapp.billapp;

import com.billapp.billapp.dao.*;
import com.billapp.billapp.entities.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.billapp.billapp.config.CorsConfig;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
@Import(CorsConfig.class)
public class BillServer {

	public static void main(String[] args) {
		SpringApplication.run(BillServer.class, args);
	}

	@Bean
	CommandLineRunner run(roleDao roleDao, userDao userDao, PasswordEncoder passwordEncoder){
		return args->{
			if(roleDao.findByAuthority("ADMIN").isPresent()) return;

			Role adminRole=roleDao.save(new Role("ADMIN"));
			roleDao.save(new Role("USER"));

			Set<Role> roles=new HashSet<>();
			roles.add(adminRole);

			ApplicationUser admin=new ApplicationUser(1,"murari",passwordEncoder.encode("murari"),"bhuvneshsanathara@gmail.com",roles);

			userDao.save(admin);
		};
	}

}
