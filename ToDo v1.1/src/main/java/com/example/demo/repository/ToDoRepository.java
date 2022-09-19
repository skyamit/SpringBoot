package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.*;

public interface ToDoRepository extends JpaRepository<ToDo,Integer>{
	
	@Query(value = "SELECT * FROM ToDo WHERE status=false", nativeQuery = true)
	List<ToDo> findAllPending();
	
	@Query(value="Select * from todo where status=true",nativeQuery = true)
	List<ToDo> findAllDone();
	
	@Query(value="select * from todo where task=:task and status=:status",nativeQuery = true)
	List<ToDo> findAllDoneByName(@Param("task") String task,@Param("status") boolean status);
	
	@Query(value="select count(*) from todo where status=:status",nativeQuery = true)
	Integer countStatus(@Param("status") boolean status);
	
	@Query(value="select status from todo group by status",nativeQuery = true)
	public List<Boolean> countAll();
}
