package br.edu.ufersa.pw20222todoList.api.restControllers;

import java.time.LocalDate;   
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufersa.pw20222todoList.api.dto.TodoDTO;
import br.edu.ufersa.pw20222todoList.domain.service.ConcludedService;





@RestController
@RequestMapping("/api/concludeds")
public class ConcludedController {
	@Autowired
	ConcludedService service;
	
	@GetMapping("/search/byEmail")
	public ResponseEntity<?> getByEmail(@Param("email") String email){
		return new ResponseEntity<List<TodoDTO>>(service.findByUser(email), HttpStatus.OK);
	}
	@GetMapping("/search/byEmailAndConcluded")
	public ResponseEntity<?> getByEmailAndConcluded(@Param("email") String email, @Param("concluded")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate deadline){
		return new ResponseEntity<List<TodoDTO>>(service.findByUserAndDeadLine(email, deadline), HttpStatus.OK);
	}
	
}
