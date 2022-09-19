package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.*;
import com.example.demo.service.Pair;
import com.example.demo.service.ToDoServiceImpl;

@RestController
public class ToDoController {
	
	@Autowired
	ToDoServiceImpl todoServiceImpl;
	
	@PostMapping(path="/create",produces="application/json",consumes="application/json")
	public boolean createTodo(@RequestBody ToDo todo) {
		todoServiceImpl.save(todo);
		return true;
	}
	
	@GetMapping(path="/select",produces="application/json",consumes="application/json")
	public List<ToDo> selectToDo() {
		List<ToDo> list = todoServiceImpl.findAll();
		return list;
	}
	
	@GetMapping(path="/select/{id}",produces="application/json",consumes="application/json")
	public Optional<ToDo> selectById(@PathVariable("id") Integer id) {
		return todoServiceImpl.findById(id);
	}
	
	@PutMapping(path="/update",produces="application/json",consumes="application/json")
	public boolean update(@RequestBody ToDo todo) {
		todoServiceImpl.delete(todoServiceImpl.findById(todo.getId()).orElse(null));
		todoServiceImpl.save(todo);
		return true;
	}
	
	@DeleteMapping(path="/delete/{id}",produces="application/json",consumes="application/json")
	public boolean deleteById(@PathVariable("id") Integer id) {
		todoServiceImpl.deleteById(id);
		return true;
	}
	
	@GetMapping(path="/pending",produces="application/json",consumes="application/json")
	public List<ToDo> findAllPending() {
		return todoServiceImpl.findAllPending();
	}
	
	@GetMapping(path="/done/{task}/{status}",produces="application/json",consumes="application/json")
	public List<ToDo> findAllDoneByName(@PathVariable("task") String task,@PathVariable("status") boolean status) {
		return todoServiceImpl.findAllDoneByName(task,status);
	}
	
	@GetMapping(path="/count/{status}",produces="application/json",consumes="application/json")
	public Integer countPending(@PathVariable("status") boolean status) {
		return todoServiceImpl.countStatus(status);
	}
	
	
	@GetMapping(path="/getPair",produces="application/json",consumes="application/json")
	public List<Pair> getPair() {
		return todoServiceImpl.getPair();
	}
}
