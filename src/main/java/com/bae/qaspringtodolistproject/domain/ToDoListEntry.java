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
}
