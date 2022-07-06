package com.brother.entities;

import java.io.Serializable;


@SuppressWarnings("serial")
public class Model implements Serializable{
	
	private int id;
	private String name;
	
	
	
	public Model() {
		
	}

	public Model(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

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

	@Override
	public String toString() {
		return "Model [id=" + id + ", name=" + name + "]";
	}
}
