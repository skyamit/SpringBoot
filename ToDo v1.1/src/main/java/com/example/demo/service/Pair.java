package com.example.demo.service;

import java.util.Objects;

public class Pair {
	Boolean status;
	Integer count;
	
	public Pair(){}
	
	public Pair(boolean status,Integer count){
		this.status = status;
		this.count = count;
	}
	
	public Boolean getName() {
		return status;
	}
	public void setName(Boolean name) {
		this.status = name;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	@Override
	public int hashCode() {
		return Objects.hash(count, status);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair other = (Pair) obj;
		return Objects.equals(count, other.count) && Objects.equals(status, other.status);
	}
	
	
}
