package com.billapp.billapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.billapp.billapp.config.CorsConfig;


@SpringBootApplication
@Import(CorsConfig.class)
public class BillServer {

	public static void main(String[] args) {
		SpringApplication.run(BillServer.class, args);
	}

}
