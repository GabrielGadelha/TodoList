package br.edu.ufersa.pw.todolist.dto;

import java.time.LocalDate;

import br.edu.ufersa.pw.todolist.entity.Concluded;
import br.edu.ufersa.pw.todolist.entity.Todo;

public class CreateTodoDTO {
	LocalDate data;
	String todo;
	String email;
	Long id;
	public CreateTodoDTO(Todo todo) {
		setData(todo.getDeadLine());
		setTodo(todo.getTodo());
		setId(todo.getId());
	}
	public CreateTodoDTO() {}
	public CreateTodoDTO(Concluded todo) {
		setData(todo.getCompletedIn());
		setTodo(todo.getTodo());
		setId(todo.getId());		
	}

	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTodo() {
		return todo;
	}
	public void setTodo(String todo) {
		this.todo = todo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
