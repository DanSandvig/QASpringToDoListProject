package com.bae.qaspringtodolistproject.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;

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
		
		//Create variables for testing
		
		ToDoListEntry testInput = new ToDoListEntry(1, "KIS", "Stupid", false);
		ToDoListEntry mockOutput = new ToDoListEntry(1L, 1, "KIS", "Stupid", false);
		
		//Convert java objects to JSON
		
		String testInputAsJson = mapper.writeValueAsString(testInput);
		String mockOutputAsJson = mapper.writeValueAsString(mockOutput);
		
		//Instruct Mockito how to respond to the service method call
		
		Mockito.when(toDoListEntryService.create(testInput)).thenReturn(mockOutput);
		
		//Test the controller method
		
		mvc.perform(post("/todolist/create")
				.content(testInputAsJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(content().json(mockOutputAsJson));
	}
	
	//Get - Read
	
	@Test
	public void testGetAll() throws Exception {
		List<ToDoListEntry> mockOutput = new ArrayList<>();
		mockOutput.add(new ToDoListEntry(1L, 1, "KIS", "Stupid", false));
		mockOutput.add(new ToDoListEntry(2L, 3, "Again?", "Really?", false));
		
		String mockOutputAsJson = mapper.writeValueAsString(mockOutput);
		
		Mockito.when(toDoListEntryService.getAll()).thenReturn(mockOutput);
		
		mvc.perform(get("/todolist/getall")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(mockOutputAsJson));
	}
	
	@Test
	public void testGetById() throws Exception {
		ToDoListEntry mockOutput = new ToDoListEntry(1L, 1, "KIS", "Stupid", false);
		
		String mockOutputAsJson = mapper.writeValueAsString(mockOutput);
		
		Mockito.when(toDoListEntryService.getById(1L)).thenReturn(mockOutput);
		
		mvc.perform(get("/todolist/getbyid/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(mockOutputAsJson));
	}
	
	@Test
	public void testGetByTitle() throws Exception {
		ToDoListEntry mockOutput = new ToDoListEntry(1L, 1, "KIS", "Stupid", false);
		
		String mockOutputAsJson = mapper.writeValueAsString(mockOutput);
		
		Mockito.when(toDoListEntryService.getByTitle("KIS")).thenReturn(mockOutput);
		
		mvc.perform(get("/todolist/getbytitle/KIS")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(mockOutputAsJson));
	}
	
	//Put - Update
	
	@Test
	public void testUpdate() throws Exception {
		ToDoListEntry testInput = new ToDoListEntry(1L, 1, "KIS", "Stupid", true);
		
		String testInputAsJson = mapper.writeValueAsString(testInput);
		
		Mockito.when(toDoListEntryService.update(1L, testInput)).thenReturn(testInput);
		
		mvc.perform(put("/todolist/update/1")
				.content(testInputAsJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(testInputAsJson));
	}
	
}
