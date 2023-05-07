package br.edu.ufersa.pw20222todoList;

import org.modelmapper.ModelMapper; 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Pw20222todoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(Pw20222todoListApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
