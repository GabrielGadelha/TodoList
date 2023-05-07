package br.edu.ufersa.pw20222todoList.api.restControllers;

import java.util.ArrayList;  
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufersa.pw20222todoList.api.dto.CreateUserDTO;
import br.edu.ufersa.pw20222todoList.api.dto.UpdateDTO;
import br.edu.ufersa.pw20222todoList.api.dto.UserDTO;
import br.edu.ufersa.pw20222todoList.domain.entity.User;
import br.edu.ufersa.pw20222todoList.domain.service.UserService;

@RestController
@RequestMapping("/api/user") 
public class UserController {
	@Autowired
	private UserService service;
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	public List<UserDTO> listar(){ 
		
		List<UserDTO> users = new ArrayList<UserDTO>();
		for(User user: service.getAll()) {
			users.add(mapper.map(user, UserDTO.class));
		}
		
		return users;
	}
	@GetMapping ("/{userId}")
	public ResponseEntity<UserDTO> buscar(@PathVariable UUID userId){
		User user = service.getById(userId);
		if(user!=null) {
			UserDTO dto = mapper.map(user, UserDTO.class);
			return new ResponseEntity<>(dto,HttpStatus.OK);
		}
	    return ResponseEntity.notFound().build();
	}
	@PostMapping
	public ResponseEntity<UserDTO> criar(@Valid @RequestBody CreateUserDTO dto){
		
		User user = service.createUser(mapper.map(dto, User.class));
		if(user!=null) {
			UserDTO dto2 = mapper.map(user, UserDTO.class);
			return new ResponseEntity<>(dto2, HttpStatus.CREATED);	
		}
		else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@PutMapping
	public ResponseEntity<UserDTO> atualizarTudo(@Valid @RequestBody UpdateDTO dto){
		User user = service.updateUser(mapper.map(dto, User.class));
		if(user != null) {
			UserDTO dto2 = mapper.map(user, UserDTO.class);
			return new ResponseEntity<>(dto2, HttpStatus.OK);
		}
			
		
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PatchMapping
	public ResponseEntity<UserDTO> atualizar(@Valid @RequestBody CreateUserDTO dto){
		User user = service.updateUserPatch(mapper.map(dto, User.class));
		if(user != null) {
			UserDTO dto2 = mapper.map(user, UserDTO.class);
			return new ResponseEntity<>(dto2, HttpStatus.OK);
		}
			
		
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping ("/{uuid}")
	public ResponseEntity<UUID> excluir(@PathVariable UUID uuid){
		String teste = service.deleteUser(uuid);
		
		if(teste.equals("ok")) {
			return new ResponseEntity<>(uuid, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	
	
}
