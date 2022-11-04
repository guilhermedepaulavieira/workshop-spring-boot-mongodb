package com.depaulavieira.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.depaulavieira.workshopmongo.domain.Post;
import com.depaulavieira.workshopmongo.domain.User;
import com.depaulavieira.workshopmongo.dto.AuthorDTO;
import com.depaulavieira.workshopmongo.dto.CommentDTO;
import com.depaulavieira.workshopmongo.repository.PostRepository;
import com.depaulavieira.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		userRepository.saveAll(Arrays.asList(maria, alex, bob)); // needed for to have an own ID
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Let's travel", "I will travel to São Paulo. Hugs!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Good Morning", "I woke up happy today!", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("good trip bro!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("enjoy", sdf.parse("22/03/2018"), new AuthorDTO(alex));
		CommentDTO c3 = new CommentDTO("Have a great day!", sdf.parse("23/03/2018"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
		
	}

}
