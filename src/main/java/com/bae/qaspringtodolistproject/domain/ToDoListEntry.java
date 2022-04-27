package com.bae.qaspringtodolistproject.domain;

import java.util.Objects;

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	@Override
	public int hashCode() {
		return Objects.hash(complete, description, priority, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToDoListEntry other = (ToDoListEntry) obj;
		return complete == other.complete && Objects.equals(description, other.description)
				&& priority == other.priority && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "ToDoListEntry [id=" + id + ", priority=" + priority + ", title=" + title + ", description="
				+ description + ", complete=" + complete + "]";
	}
}
