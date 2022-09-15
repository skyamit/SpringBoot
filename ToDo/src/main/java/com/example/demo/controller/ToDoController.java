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

import com.example.demo.DAO.ToDoDAO;
import com.example.demo.model.*;

@RestController
public class ToDoController {

	@Autowired
	ToDoDAO todoDao;
	
	@PostMapping(path="/create",produces="application/json",consumes="application/json")
	public boolean createTodo(@RequestBody ToDo todo) {
		todoDao.save(todo);
		return true;
	}
	
	@GetMapping(path="/select",produces="application/json",consumes="application/json")
	public List<ToDo> selectToDo() {
		List<ToDo> list = todoDao.findAll();
		return list;
	}
	
	@GetMapping(path="/select/{id}",produces="application/json",consumes="application/json")
	public Optional<ToDo> selectById(@PathVariable("id") Integer id) {
		return todoDao.findById(id);
	}
	
	@PutMapping(path="/update",produces="application/json",consumes="application/json")
	public boolean update(@RequestBody ToDo todo) {
		todoDao.delete(todoDao.getReferenceById(todo.getId()));
		todoDao.save(todo);
		return true;
	}
	
	@DeleteMapping(path="/delete/{id}",produces="application/json",consumes="application/json")
	public boolean deleteById(@PathVariable("id") Integer id) {
		todoDao.deleteById(id);
		return true;
	}
}
