package com.campos.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campos.workshopmongo.domain.User;
import com.campos.workshopmongo.repository.UserRepository;
import com.campos.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;

	
	public List<User> findAll(){
		return repository.findAll();	
	}
	

	public User findUserById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
	}
	
	public User update(User obj) {
		User entity = repository.findById(obj.getId()).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
		
		updateData(obj, entity);
		
		return repository.save(entity);
		}
		

	private User updateData(User obj,User newObj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		return newObj;
	}
	
	
	
}
