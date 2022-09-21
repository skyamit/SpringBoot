package com.ImplementAll.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ImplementAll.model.UserEntity;
import com.ImplementAll.service.UserServiceImpl;

@RestController
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;
	
	@GetMapping("/users")
	public List<UserEntity> retrieveAllUsers(){
		return userServiceImpl.getAll();
	}
	
	@GetMapping("/user/{id}")
	public Optional<UserEntity> getUserById(@PathVariable("id") Long id) {
		return userServiceImpl.getById(id);
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
}
