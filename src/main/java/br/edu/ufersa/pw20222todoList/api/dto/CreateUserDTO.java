package br.edu.ufersa.pw20222todoList.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserDTO {
	@NotBlank(message="O email nao pode ser null!!")
	@Email(message="O email deve ser um email!!")
	private String email;
	@Size(min=5,max=20, message="A senha deve ter entre 5 e 20 caracteres!")
	private String senha;
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
	
}
