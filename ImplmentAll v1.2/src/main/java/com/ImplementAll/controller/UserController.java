package com.ImplementAll.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ImplementAll.model.Post;
import com.ImplementAll.model.UserEntity;
import com.ImplementAll.service.PostServiceImpl;
import com.ImplementAll.service.UserServiceImpl;

@RestController
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	PostServiceImpl postServiceImpl;
	
	@GetMapping("/users")
	public List<UserEntity> retrieveAllUsers(){
		return userServiceImpl.getAll();
	}
	
	@GetMapping("/user/{id}")
	public EntityModel<UserEntity> hateoasGetByID(@PathVariable("id") Long id) {
		UserEntity user = userServiceImpl.getById(id).orElse(null);
		EntityModel<UserEntity> entityModel = EntityModel.of(user);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}
	
	@PostMapping("/user")
	public void insertUser(@RequestBody UserEntity user) {
		userServiceImpl.save(user);
	}
	
	@DeleteMapping("/user/{id}")
	public boolean deleteById(@PathVariable("id") Long id) {
		userServiceImpl.deleteById(id);
		return true;
	}
	
	@GetMapping("/user/{id}/posts")
	public ResponseEntity<List<Post>> getUserPosts(@PathVariable("id") Long id) {
		UserEntity user = userServiceImpl.getById(id).orElse(null);
		if(user==null)
			return null;
		return new ResponseEntity<List<Post>>(user.getPosts(),HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}/post")
	public List<Post> getPostById(@PathVariable("id") long id){
		return postServiceImpl.getPostByUserId(id);
	}
}
