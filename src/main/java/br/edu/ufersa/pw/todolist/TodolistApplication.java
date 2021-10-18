package br.edu.ufersa.pw.todolist;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.format.Formatter;
import br.edu.ufersa.pw.todolist.repositories.UserRepository;
import br.edu.ufersa.pw.todolist.util.LocalDateFormatter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@SpringBootApplication
public class TodolistApplication {
	@Autowired
	UserRepository userRepo;
	private static final Logger logger =
			LoggerFactory.getLogger(TodolistApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
		logger.info("Hello start");
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Bean
	@Primary
	public Formatter<LocalDate> localDateFormatter() {
	    return new LocalDateFormatter();
	}
    @Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/*").allowedOrigins("*");
			}
		};
	}
}
