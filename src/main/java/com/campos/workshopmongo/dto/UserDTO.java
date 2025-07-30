package com.campos.workshopmongo.dto;

import java.io.Serializable;

import com.campos.workshopmongo.domain.User;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String email;
	private String name;
	
	public UserDTO() {
	}

	public UserDTO(User user) {
		this.email = user.getEmail();
		this.name = user.getName();
		this.id = user.getId();
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
