package br.edu.ufersa.pw20222todoList.domain.service;

import java.time.LocalDate;     
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufersa.pw20222todoList.api.dto.TodoDTO;
import br.edu.ufersa.pw20222todoList.domain.entity.Concluded;
import br.edu.ufersa.pw20222todoList.domain.repository.ConcludedRepository;


@Service
public class ConcludedService {
	@Autowired
	private ConcludedRepository repo;
	
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false) 
	public List<TodoDTO> findByUser(String email) {
		List<Concluded> todos = repo.findByUser(email);
		
		return todos.stream().map(x -> new TodoDTO(x)).collect(Collectors.toList());
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false) 
	public List<TodoDTO> findByUserAndDeadLine (String email, LocalDate deadline){
		List<Concluded> todos = repo.findByUserAndDeadLine(email, deadline);
		
		return todos.stream().map(x -> new TodoDTO(x)).collect(Collectors.toList());
	}
	
}
