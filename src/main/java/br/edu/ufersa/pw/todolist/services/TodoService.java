package br.edu.ufersa.pw.todolist.services;

import java.time.LocalDate;   
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.edu.ufersa.pw.todolist.dto.CreateTodoDTO;
import br.edu.ufersa.pw.todolist.dto.TodoDTO;
import br.edu.ufersa.pw.todolist.dto.UserDto;
import br.edu.ufersa.pw.todolist.entity.Concluded;
import br.edu.ufersa.pw.todolist.entity.Todo;
import br.edu.ufersa.pw.todolist.repositories.ConcludedRepository;
import br.edu.ufersa.pw.todolist.repositories.TodoRepository;
import br.edu.ufersa.pw.todolist.repositories.UserRepository;

@Service
public class TodoService {
	@Autowired
	private TodoRepository repo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ConcludedRepository concRepo;
	@Autowired
	private ModelMapper mapper;
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false) 
	public List<TodoDTO> findByUser(String email) {
		List<Todo> todos = repo.findByUser(email);
		
		return todos.stream().map(x -> new TodoDTO(x)).collect(Collectors.toList());
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false) 
	public List<TodoDTO> findByUserAndDeadLine (String email, LocalDate deadline){
		List<Todo> todos = repo.findByUserAndDeadLine(email, deadline);
		
		return todos.stream().map(x -> new TodoDTO(x)).collect(Collectors.toList());
	}
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public CreateTodoDTO save(CreateTodoDTO dto) {
		Todo todo = new Todo();
		todo.setDeadLine(dto.getData());
		todo.setTodo(dto.getTodo());
		todo.setUser(userRepo.findByEmail(dto.getEmail()));
		todo = repo.save(todo);
		CreateTodoDTO retorno = new CreateTodoDTO();
		retorno.setData(todo.getDeadLine());
		retorno.setEmail(dto.getEmail());
		retorno.setTodo(todo.getTodo());
		retorno.setId(todo.getId());
		return retorno;
	}
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public CreateTodoDTO update(CreateTodoDTO dto) {
		Todo todo = new Todo();
		todo.setDeadLine(dto.getData());
		todo.setTodo(dto.getTodo());
		todo.setUser(userRepo.findByEmail(dto.getEmail()));
		todo.setId(dto.getId());
		todo = repo.save(todo);
		CreateTodoDTO retorno = new CreateTodoDTO();
		retorno.setData(todo.getDeadLine());
		retorno.setEmail(dto.getEmail());
		retorno.setTodo(todo.getTodo());
		retorno.setId(todo.getId());
		return retorno;
	}
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void delete(TodoDTO dto) {
		Todo todo = repo.getById(dto.getId());
		Concluded conc = mapper.map(todo, Concluded.class);
		conc.setCompletedIn(LocalDate.now());
		concRepo.save(conc);
		repo.delete(todo);
	}
	 
}
