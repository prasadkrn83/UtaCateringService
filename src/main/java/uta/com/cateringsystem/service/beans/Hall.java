package uta.com.cateringsystem.service.beans;

import org.springframework.stereotype.Component;

@Component
public class Hall {
	private int id;
	private String name;
	private int capacity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	
	
	
	

}
