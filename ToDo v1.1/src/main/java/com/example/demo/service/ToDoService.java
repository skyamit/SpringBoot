package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.ToDo;

public interface ToDoService {
	public List<ToDo> findAll();
	public void save(ToDo todo);
	public Optional<ToDo> findById(Integer id);
	public void delete(ToDo todo);
	public List<ToDo> findAllPending();
	public List<ToDo> findAllDone();
	public List<ToDo> findAllDoneByName(String task, boolean status);
	public void deleteById(Integer id);
	public Integer countStatus(Boolean status);
}
