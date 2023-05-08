package br.edu.ufersa.pw20222todoList.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufersa.pw20222todoList.domain.entity.User;
import br.edu.ufersa.pw20222todoList.domain.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	public List<User> getAll(){
		List<User> users = repository.findAll();
		
		return users;
	}
	public User getById(UUID id) {
		User user = repository.findByUuid(id);
		return user;
	}
	public User getByEmailAndSenha(User usu) {
		User user = repository.findByEmailAndSenha(usu.getEmail(), usu.getSenha());
		return user;
	}
	public User createUser(User usu) {
		usu.setUuid(UUID.randomUUID());
		repository.save(usu);
		return usu;
	}
	public User updateUser(User usu) {
		User dataUser = repository.findByUuid(usu.getUuid());
		dataUser.setSenha(usu.getSenha());
		repository.save(dataUser);
		return dataUser;
	}
	public String deleteUser(UUID uuid) {
		User dataUser = repository.findByUuid(uuid);
		if(dataUser!=null){
			repository.delete(dataUser);
			return"ok";
		}
		return "DeuM";
		
	}
	public User updateUserPatch(User usu) {
		User dataUser = repository.findByEmail(usu.getEmail());
		dataUser.setSenha(usu.getSenha());
		repository.save(dataUser);
		return dataUser;

	}
	
}
