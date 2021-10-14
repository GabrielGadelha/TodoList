package br.edu.ufersa.pw.todolist.services;

import java.time.LocalDate;    
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufersa.pw.todolist.dto.CreateTodoDTO;
import br.edu.ufersa.pw.todolist.dto.TodoDTO;
import br.edu.ufersa.pw.todolist.entity.Concluded;
import br.edu.ufersa.pw.todolist.entity.Todo;
import br.edu.ufersa.pw.todolist.repositories.ConcludedRepository;
import br.edu.ufersa.pw.todolist.repositories.UserRepository;

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
