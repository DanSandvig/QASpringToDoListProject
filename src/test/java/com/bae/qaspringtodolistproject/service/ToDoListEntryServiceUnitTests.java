package com.bae.qaspringtodolistproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
