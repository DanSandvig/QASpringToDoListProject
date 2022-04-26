package com.bae.qaspringtodolistproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bae.qaspringtodolistproject.domain.ToDoListEntry;
import com.bae.qaspringtodolistproject.repo.ToDoListEntryRepo;

@SpringBootTest
public class ToDoListEntryServiceUnitTests {

	@Autowired
	private ToDoListEntryService toDoListEntryService;
	
	@MockBean
	private ToDoListEntryRepo toDoListEntryRepo;
	
	//Test methods
	
	//Create
	@Test
	public void testCreate() {
		ToDoListEntry testInput = new ToDoListEntry(1, "KIS", "Stupid", false);
		ToDoListEntry mockOutput = new ToDoListEntry(1L, 1, "KIS", "Stupid", false);
		
		//Instructs the mocked component how to respond to a given method call
		Mockito.when(toDoListEntryRepo.saveAndFlush(testInput)).thenReturn(mockOutput);
		
		//Tests the method in the Service class
		assertEquals(mockOutput, toDoListEntryService.create(testInput));
		
		//Confirms the number of times the method call takes place
		Mockito.verify(toDoListEntryRepo, Mockito.times(1)).saveAndFlush(testInput);
	}
	
	//Read
	@Test
	public void testGetAll() {
		List<ToDoListEntry> mockOutput = new ArrayList<ToDoListEntry>();
		mockOutput.add(new ToDoListEntry(1L, 1, "KIS", "Stupid", false));
		mockOutput.add(new ToDoListEntry(2L, 3, "Again?", "Really?", false));
		
		Mockito.when(toDoListEntryRepo.findAll()).thenReturn(mockOutput);
		
		assertEquals(mockOutput, toDoListEntryService.getAll());
		
		Mockito.verify(toDoListEntryRepo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void testGetById() {
		ToDoListEntry mockOutput = new ToDoListEntry(1L, 1, "KIS", "Stupid", false);
		
		Mockito.when(toDoListEntryRepo.findById(1L)).thenReturn(Optional.of(mockOutput));
		
		assertEquals(mockOutput, toDoListEntryService.getById(1L));
		
		Mockito.verify(toDoListEntryRepo, Mockito.times(1)).findById(1L);
	}
	
	@Test
	public void testGetByTitle() {
		ToDoListEntry mockOutput = new ToDoListEntry(1L, 1, "KIS", "Stupid", false);
		
		Mockito.when(toDoListEntryRepo.findToDoListEntryByTitle("KIS")).thenReturn(mockOutput);
		
		assertEquals(mockOutput, toDoListEntryService.getByTitle("KIS"));
		
		Mockito.verify(toDoListEntryRepo, Mockito.times(1)).findToDoListEntryByTitle("KIS");
	}
	
	//Update
	
	@Test
	public void testUpdate() {
		ToDoListEntry testInput = new ToDoListEntry(1L, 1, "KIS", "Stupid", true);
		ToDoListEntry mockOutput = new ToDoListEntry(1L, 1, "KIS", "Stupid", false);
		
		Mockito.when(toDoListEntryRepo.findById(1L)).thenReturn(Optional.of(mockOutput));
		Mockito.when(toDoListEntryRepo.saveAndFlush(testInput)).thenReturn(testInput);
		
		assertEquals(testInput, toDoListEntryService.update(1L, testInput));
		
		Mockito.verify(toDoListEntryRepo, Mockito.times(1)).findById(1L);
		Mockito.verify(toDoListEntryRepo, Mockito.times(1)).saveAndFlush(testInput);
	}
}
