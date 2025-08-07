package com.campos.workshopmongo.resources;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.campos.workshopmongo.domain.Post;
import com.campos.workshopmongo.domain.User;
import com.campos.workshopmongo.repository.UserRepository;
import com.campos.workshopmongo.resources.exceptions.ResourceExceptionHandler;
import com.campos.workshopmongo.resources.util.URL;
import com.campos.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    private final ResourceExceptionHandler resourceExceptionHandler;

	
	@Autowired
	private PostService service;
	
	@Autowired
	private UserRepository userRepository;


    PostResource(ResourceExceptionHandler resourceExceptionHandler) {
        this.resourceExceptionHandler = resourceExceptionHandler;
    }

	
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

	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle (@RequestParam(defaultValue="") String text){
		
		text = URL.decodeParam(text);
		List<Post> posts = service.findByTitle(text);
		return ResponseEntity.ok().body(posts);
	
	}
	
	@GetMapping(value = "/fullsearch")
	public ResponseEntity<List<Post>> fullSearch (
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate minDate,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate maxDate,
			@RequestParam String keyWord){
		
		if (minDate == null) minDate = LocalDate.of(1970, 1, 1);
	    if (maxDate == null) maxDate = LocalDate.now();
		
		List<Post> list = service.fullSearch(keyWord, minDate, maxDate);
		
		return ResponseEntity.ok().body(list);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
