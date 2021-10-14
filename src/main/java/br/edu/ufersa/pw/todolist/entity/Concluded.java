package br.edu.ufersa.pw.todolist.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_concluded")
public class Concluded {
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	private Long id;
	private String todo;
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;
	private LocalDate completedIn;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTodo() {
		return todo;
	}
	public void setTodo(String todo) {
		this.todo = todo;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public LocalDate getCompletedIn() {
		return completedIn;
	}
	public void setCompletedIn(LocalDate completedIn) {
		this.completedIn = completedIn;
	}
	
}
