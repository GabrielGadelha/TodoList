package br.edu.ufersa.pw20222todoList.api.dto;

import java.util.UUID;

public class UserDTO {
	private String email;
	private UUID uuid;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
	
}
