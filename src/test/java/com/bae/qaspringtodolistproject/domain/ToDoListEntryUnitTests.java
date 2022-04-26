package com.bae.qaspringtodolistproject.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ToDoListEntryUnitTests {

	//Tests equals and hashCode in a single method (required them to not use id?)
	@Test
	public void testEquals() {
		EqualsVerifier.forClass(ToDoListEntry.class).usingGetClass().verify();
	}
	
	@Test
	public void testConstructorWithId() {
		ToDoListEntry newToDo = new ToDoListEntry(1L, 1, "Do the thing!",
								"There are many things. Do one of them.", false);
		
		assertNotNull(newToDo.getId());
		assertNotNull(newToDo.getPriority());
		assertNotNull(newToDo.getTitle());
		assertNotNull(newToDo.getDescription());
		assertNotNull(newToDo.isComplete());
		
		assertEquals(1L, newToDo.getId());
		assertEquals(1, newToDo.getPriority());
		assertEquals("Do the thing!", newToDo.getTitle());
		assertEquals("There are many things. Do one of them.", newToDo.getDescription());
		assertEquals(false, newToDo.isComplete());
	}
	
	@Test
	public void testConstructorWithoutId() {
		ToDoListEntry newToDo = new ToDoListEntry(1, "Do the thing!",
								"There are many things. Do one of them.", false);
		
		assertNotNull(newToDo.getId());
		assertNotNull(newToDo.getPriority());
		assertNotNull(newToDo.getTitle());
		assertNotNull(newToDo.getDescription());
		assertNotNull(newToDo.isComplete());
		
		assertEquals(0L, newToDo.getId());
		assertEquals(1, newToDo.getPriority());
		assertEquals("Do the thing!", newToDo.getTitle());
		assertEquals("There are many things. Do one of them.", newToDo.getDescription());
		assertEquals(false, newToDo.isComplete());
	}
	
}
