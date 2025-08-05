package com.campos.workshopmongo.resources;



import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.campos.workshopmongo.domain.Post;
import com.campos.workshopmongo.domain.User;
import com.campos.workshopmongo.dto.UserDTO;
import com.campos.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		
		List<User> users = service.findAll();
		
		List<UserDTO> dtoList = users.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());

		return ResponseEntity.ok().body(dtoList);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		User obj = service.findUserById(id);
		UserDTO userDto = new UserDTO(obj);
		return ResponseEntity.ok().body(userDto);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){
		User obj = service.fromDto(objDto);
		service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable String id, @RequestBody UserDTO objDto){
		User obj = service.fromDto(objDto);
		obj.setId(id);
		obj = service.update(obj);
	
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id){
		
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
		User obj = service.findUserById(id);

		return ResponseEntity.ok().body(obj.getPosts());
	}
	
	
	
	
	
	
	
	
	
	
	

}
