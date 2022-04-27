package com.bae.qaspringtodolistproject.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
		ToDoListEntry testInput = new ToDoListEntry(3, "Again?", "Really?", false);
		ToDoListEntry mockOutput = new ToDoListEntry(2L, 3, "Again?", "Really?", false);
		
		String testInputAsJson = mapper.writeValueAsString(testInput);
		String mockOutputAsJson = mapper.writeValueAsString(mockOutput);
		
		mvc.perform(post("/todolist/create")
				.content(testInputAsJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(content().json(mockOutputAsJson));
	}
	
}
