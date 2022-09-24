package com.microservice.Limitsservice.model;

import java.util.Objects;

public class Limits {
	private int min;
	private int max;
	
	public Limits(){}
	
	public Limits(int x,int y){
		min = x;
		max = y;
	}
	
	public int getMin() {
		return min;
	}
	
	public void setMin(int min) {
		this.min = min;
	}
	
	public int getMax() {
		return max;
	}
	
	public void setMax(int max) {
		this.max = max;
	}

	@Override
	public int hashCode() {
		return Objects.hash(max, min);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Limits other = (Limits) obj;
		return max == other.max && min == other.min;
	}

	@Override
	public String toString() {
		return "Limit [min=" + min + ", max=" + max + "]";
	}
}
