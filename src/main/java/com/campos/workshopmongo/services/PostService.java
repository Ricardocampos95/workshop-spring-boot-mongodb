package com.campos.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campos.workshopmongo.domain.Post;
import com.campos.workshopmongo.repository.PostRepository;
import com.campos.workshopmongo.services.exceptions.ObjectNotFoundException;


@Service
public class PostService {
	
	@Autowired
	private PostRepository repository;

	public Post findById(String id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
	}
	
	public List<Post> findAll() {
		return repository.findAll();
	}
	
	public List<Post> findByTitle(String text){
		return repository.searchTitle(text);
	}
	
}
