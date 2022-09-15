package com.example.demo.DAO;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.*;

public interface ToDoDAO extends JpaRepository<ToDo,Integer>{

}
