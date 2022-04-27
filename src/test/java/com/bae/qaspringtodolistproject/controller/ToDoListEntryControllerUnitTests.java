package com.bae.qaspringtodolistproject.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.bae.qaspringtodolistproject.domain.ToDoListEntry;
import com.bae.qaspringtodolistproject.service.ToDoListEntryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class ToDoListEntryControllerUnitTests {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@MockBean
	private ToDoListEntryService toDoListEntryService;
	
	//Methods
	
	//Put - Create
	
	@Test
	public void testCreate() throws Exception {
		ToDoListEntry testInput = new ToDoListEntry(1, "KIS", "Stupid", false);
		ToDoListEntry mockOutput = new ToDoListEntry(1L, 1, "KIS", "Stupid", false);
		
		String testInputAsJson = mapper.writeValueAsString(testInput);
		String mockOutputAsJson = mapper.writeValueAsString(mockOutput);
		
		Mockito.when(toDoListEntryService.create(testInput)).thenReturn(mockOutput);
		
		mvc.perform(post("/todolist/create")
				.content(testInputAsJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(content().json(mockOutputAsJson));
	}
}
