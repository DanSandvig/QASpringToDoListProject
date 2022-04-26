package com.bae.qaspringtodolistproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ToDoListEntry {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private int priority;
	
	@Column(nullable = false)
	private String title;
	
	@Column
	private String description;
	
	@Column(nullable = false)
	private boolean complete;
	
	//Default Constructor
	public ToDoListEntry() {
		
	}
	
	//Test Constructor
	public ToDoListEntry(long id, int priority, String title, String description, boolean complete) {
		super();
		this.id = id;
		this.priority = priority;
		this.title = title;
		this.description = description;
		this.complete = complete;
	}
	
	//Production Constructor	
	public ToDoListEntry(int priority, String title, String description, boolean complete) {
		super();
		this.priority = priority;
		this.title = title;
		this.description = description;
		this.complete = complete;
	}
}
