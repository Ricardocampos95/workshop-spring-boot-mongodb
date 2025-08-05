package com.campos.workshopmongo.resources;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campos.workshopmongo.domain.Post;
import com.campos.workshopmongo.domain.User;
import com.campos.workshopmongo.repository.UserRepository;
import com.campos.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	
	@Autowired
	private PostService service;
	
	@Autowired
	private UserRepository userRepository;

	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById (@PathVariable String id){
		Post post = service.findById(id);
		return ResponseEntity.ok().body(post);
	}
	
	@GetMapping
	public ResponseEntity<Set<Post>> findAll(){
		Set<Post> posts = new HashSet<>();
		List<User> users = userRepository.findAll();
		
		for (User user : users) {
			posts.addAll(user.getPosts());
		}
		
		return ResponseEntity.ok().body(posts);
	}

}
