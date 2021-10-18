package br.edu.ufersa.pw.todolist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufersa.pw.todolist.dto.CreateUserDto;
import br.edu.ufersa.pw.todolist.dto.UserDto;
import br.edu.ufersa.pw.todolist.repositories.UserRepository;
import br.edu.ufersa.pw.todolist.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	UserRepository userRepo;
	@Autowired
	UserService service;
	@GetMapping("/search/byEmail")
	public UserDto getByEmail(@Param("email") String email) {
		return service.findByEmail(email);
	}
	@PostMapping
	public ResponseEntity<UserDto> save (@RequestBody CreateUserDto dto) {


		UserDto user =   service.save(dto);
		if(user==null) {
			return new ResponseEntity<> (null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else {
			return new ResponseEntity<> (user, HttpStatus.CREATED);
		}
	}
	@DeleteMapping(value="/{email}")
	public ResponseEntity<String> delete (@PathVariable String email){
		UserDto dto = new UserDto();
		dto.setEmail(email);
		try {
			service.delete(dto);
			return new ResponseEntity<>(email,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>("",HttpStatus.NOT_FOUND);
		}
	}
}
