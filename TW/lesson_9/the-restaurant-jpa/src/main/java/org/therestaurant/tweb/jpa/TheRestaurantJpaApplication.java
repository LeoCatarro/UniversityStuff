package org.therestaurant.tweb.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TheRestaurantJpaApplication {

    @Autowired
    private ClientRepository repository;
    
	public static void main(String[] args) {
		SpringApplication.run(TheRestaurantJpaApplication.class, args);
	}

}
