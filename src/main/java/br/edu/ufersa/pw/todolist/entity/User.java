package br.edu.ufersa.pw.todolist.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="tb_users")
public class User {
	@Column(unique=true)
	private String email;
	private String senha;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private UUID uuid;
	@OneToMany(mappedBy="user")
	private List<Todo> todoList = new ArrayList<Todo>();
	@OneToMany(mappedBy="user")
	private List<Concluded> concludedList = new ArrayList<Concluded>();
	public User() {
		uuid = UUID.randomUUID();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
}
