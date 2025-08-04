package com.campos.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campos.workshopmongo.domain.User;
import com.campos.workshopmongo.dto.UserDTO;
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
	
	public User insert(User obj) {
		return repository.insert(obj);
	}
	
	public User update(User obj) {
		User newObj = repository.findById(obj.getId()).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
		
		updateData(newObj, obj);
	
		
		return repository.save(newObj);
		}
	
	public void deleteById(String id) {
		findUserById(id);
		repository.deleteById(id);
	}
		


	public void updateData(User newObj,User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
	
	public User fromDto(UserDTO obj) {
		return new User(obj.getId(), obj.getName(), obj.getEmail());
	}
	
	
	
	
}
