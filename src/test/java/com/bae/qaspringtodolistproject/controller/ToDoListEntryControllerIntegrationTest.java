package com.bae.qaspringtodolistproject.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.bae.qaspringtodolistproject.domain.ToDoListEntry;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:testschema.sql", "classpath:testdata.sql"},
	 executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class ToDoListEntryControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	//Methods
	
	//Post - Create
	
	@Test
	public void testCreate() throws Exception {
		
		//Create variables for testing
		
		ToDoListEntry testInput = new ToDoListEntry(3, "Again?", "Really?", false);
		ToDoListEntry expectedOutput = new ToDoListEntry(2L, 3, "Again?", "Really?", false);
		
		//Convert java objects to JSON
		
		String testInputAsJson = mapper.writeValueAsString(testInput);
		String expectedOutputAsJson = mapper.writeValueAsString(expectedOutput);
		
		//Test the controller method
		
		mvc.perform(post("/todolist/create")
				.content(testInputAsJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(content().json(expectedOutputAsJson));
	}
	
	
	//Get - Read
	
	@Test
	public void testGetAll() throws Exception {
		List<ToDoListEntry> expectedOutput = new ArrayList<>();
		expectedOutput.add(new ToDoListEntry(1L, 1, "KIS", "Stupid", false));
		
		String expectedOutputAsJson = mapper.writeValueAsString(expectedOutput);
		
		mvc.perform(get("/todolist/getall")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(expectedOutputAsJson));
	}
	
	@Test
	public void testGetById() throws Exception {
		ToDoListEntry expectedOutput = new ToDoListEntry(1L, 1, "KIS", "Stupid", false);
		
		String expectedOutputAsJson = mapper.writeValueAsString(expectedOutput);
		
		mvc.perform(get("/todolist/getbyid/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(expectedOutputAsJson));
	}
	
	@Test
	public void testGetByTitle() throws Exception {
		ToDoListEntry expectedOutput = new ToDoListEntry(1L, 1, "KIS", "Stupid", false);
		
		String expectedOutputAsJson = mapper.writeValueAsString(expectedOutput);
		
		mvc.perform(get("/todolist/getbytitle/KIS")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(expectedOutputAsJson));
	}
	
	//Update
	
	@Test
	public void testUpdate() throws Exception {
		ToDoListEntry testInput = new ToDoListEntry(1L, 1, "KIS", "Stupid", true);
		
		String testInputAsJson = mapper.writeValueAsString(testInput);
		
		mvc.perform(put("/todolist/update/1")
				.content(testInputAsJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(testInputAsJson));
	}
}
