package br.edu.ufersa.pw.todolist.dto;

import java.io.Serializable;
import java.time.LocalDate;

import br.edu.ufersa.pw.todolist.entity.Concluded;
import br.edu.ufersa.pw.todolist.entity.Todo;

public class TodoDTO implements Serializable{
	LocalDate data;
	String todo;
	Long id;
	
	public TodoDTO(Todo todo) {
		setData(todo.getDeadLine());
		setTodo(todo.getTodo());
		setId(todo.getId());
		
	}
	public TodoDTO() {}
	public TodoDTO(Concluded todo) {
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
