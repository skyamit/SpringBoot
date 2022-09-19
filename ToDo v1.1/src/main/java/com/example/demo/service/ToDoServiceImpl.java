package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ToDo;
import com.example.demo.repository.ToDoRepository;

@Service
public class ToDoServiceImpl implements ToDoService{

	@Autowired
	ToDoRepository todoRepo;
	
	@Override
	public List<ToDo> findAll(){
		return todoRepo.findAll();
	}
	
	@Override	
	public void save(ToDo todo) {
		todoRepo.save(todo);
	}
	
	@Override	
	public Optional<ToDo> findById(Integer id) {
		return todoRepo.findById(id);
	}
	
	@Override
	public void delete(ToDo todo) {
		todoRepo.delete(todo);
	}
	
	@Override	
	public List<ToDo> findAllPending() {
		return todoRepo.findAllPending();
	}
	
	@Override
	public List<ToDo> findAllDone() {
		return todoRepo.findAllDone();
	}
	
	@Override
	public List<ToDo> findAllDoneByName(String task, boolean status) {
		return todoRepo.findAllDoneByName(task, status);
	}
	
	@Override
	public void deleteById(Integer id) {
		todoRepo.deleteById(id);
	}
	
	@Override
	public Integer countStatus(Boolean status) {
		return todoRepo.countStatus(status);
	}
	
	public List<Pair> getPair() {
		List<Boolean> list = todoRepo.countAll();
		List<Pair> result = new ArrayList<>();
		
		for(Boolean s : list) {
			result.add(new Pair(s,todoRepo.countStatus(s)));
		}
		
		return result;
	}
	
	
}
