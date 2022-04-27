package com.bae.qaspringtodolistproject.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bae.qaspringtodolistproject.domain.ToDoListEntry;
import com.bae.qaspringtodolistproject.service.ToDoListEntryService;

@CrossOrigin
@RestController
@RequestMapping("/todolist")
public class ToDoListEntryController {

	private ToDoListEntryService toDoListEntryService;

	public ToDoListEntryController(ToDoListEntryService toDoListService) {
		super();
		this.toDoListEntryService = toDoListService;
	}
	
	//Methods
	
	//Post - Create
	
	@PostMapping("/create")
	public ResponseEntity<ToDoListEntry> create(@RequestBody ToDoListEntry toDoListEntry) {
		return new ResponseEntity<ToDoListEntry>(toDoListEntryService.create(toDoListEntry),
				HttpStatus.CREATED);
	}
	
	//Get - Read
	
	@GetMapping("/getall")
	public ResponseEntity<List<ToDoListEntry>> getAll() {
		return new ResponseEntity<List<ToDoListEntry>>(toDoListEntryService.getAll(),
				HttpStatus.OK);
	}
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<ToDoListEntry> getById(@PathVariable long id) {
		return new ResponseEntity<ToDoListEntry>(toDoListEntryService.getById(id),
				HttpStatus.OK);
	}
	
	@GetMapping("/getbytitle/{title}")
	public ResponseEntity<ToDoListEntry> getByTitle(@PathVariable String title) {
		return new ResponseEntity<ToDoListEntry>(toDoListEntryService.getByTitle(title),
				HttpStatus.OK);
	}
	
	//Put - Update
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ToDoListEntry> update(@PathVariable long id, 
			@RequestBody ToDoListEntry toDoListEntry) {
		return new ResponseEntity<ToDoListEntry>(toDoListEntryService.update(id, toDoListEntry),
				HttpStatus.OK);
	}
	
	//Delete - Delete
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		return (toDoListEntryService.delete(id)) ?
				new ResponseEntity<>(HttpStatus.NO_CONTENT) :
				new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
