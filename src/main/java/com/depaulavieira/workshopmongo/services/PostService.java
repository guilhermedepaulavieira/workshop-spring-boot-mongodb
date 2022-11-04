package com.depaulavieira.workshopmongo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depaulavieira.workshopmongo.domain.Post;
import com.depaulavieira.workshopmongo.repository.PostRepository;
import com.depaulavieira.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	
	@Autowired
	private PostRepository repo;
		
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not Found"));
	}	
}