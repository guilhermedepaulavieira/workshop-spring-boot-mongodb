package com.depaulavieira.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.depaulavieira.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	@Query("{ 'Title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String texto);
	
	List<Post> findByTitleContainingIgnoreCase(String text);
}
