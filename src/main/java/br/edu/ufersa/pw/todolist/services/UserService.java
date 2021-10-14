package br.edu.ufersa.pw.todolist.services;

import org.modelmapper.ModelMapper; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufersa.pw.todolist.dto.CreateUserDto;
import br.edu.ufersa.pw.todolist.dto.UserDto;
import br.edu.ufersa.pw.todolist.entity.User;
import br.edu.ufersa.pw.todolist.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repo;
	@Autowired
	private ModelMapper mapper;
	public UserDto findByEmail(String email) {
		User user = repo.findByEmail(email);
		UserDto dto = mapper.map(user, UserDto.class);
		return dto;
	}
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public UserDto save(CreateUserDto dto) {
		User user = new User();
		user.setEmail(dto.getEmail());
		user.setSenha(dto.getSenha());
		return mapper.map(repo.save(user), UserDto.class);
	}
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void delete (UserDto dto) {
		repo.deleteByEmail(dto.getEmail());
	}
}
