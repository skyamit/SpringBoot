package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="todo")
public class ToDo {
	
	@Id
	int id;
	
	@Column(name="task")
	String task;
	
	@Column(name="status")
	boolean status;
	
	@Column(name="date")
	String date;
	
	ToDo(){
		id= 0;
		task = new String();
		date = new String();
		status = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ToDo [id=" + id + ", task=" + task + ", date=" + date + "]";
	}
	
}
