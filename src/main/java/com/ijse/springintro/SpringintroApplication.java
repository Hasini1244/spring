package com.ijse.springintro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;

@SpringBootApplication
public class SpringintroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringintroApplication.class, args);
	}
	@PostMapping("/hello")
	public String postRequest(){
        return "This is a Post request to Hello endpoint";
    }


}
