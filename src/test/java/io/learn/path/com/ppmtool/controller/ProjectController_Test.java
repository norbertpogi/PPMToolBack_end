package io.learn.path.com.ppmtool.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import io.learn.path.com.ppmtool.PpmtoolApplication;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PpmtoolApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectController_Test {

	final String URI = "/api/project/";

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}


	@Test
	public void getAllProject_test() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get(URI + "all")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(jsonPath("$", hasSize(3)))
				.andReturn();
	}
	
	@Test
	public void getProjectByIdentity_test() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get(URI + "E001")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(jsonPath("$.id").exists())				
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.projectName").exists())
				.andExpect(jsonPath("$.projectName").value("Project name1"))
				.andExpect(jsonPath("$.projectIdentifier").exists())
				.andExpect(jsonPath("$.projectIdentifier").value("E001"))
				.andExpect(jsonPath("$.*", hasSize(8)))
				.andReturn();
	}


	@Test
	public void getInvalidProjectByIdentity_test() throws Exception {		
		this.mockMvc.perform(MockMvcRequestBuilders.get(URI + "0")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.projectIdentifier")
						.value("identifier id: 0 Not Found"))
				.andReturn();
	}
	
	@Test
	public void createNewProject_test() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post(URI)
				.contentType(MediaType.APPLICATION_JSON)
	            .content("{\r\n" + 
	            		" \"id\": 2,\r\n" + 
	            		"  \"projectName\": \"Project name2_update_test\",\r\n" + 
	            		"  \"projectIdentifier\": \"E002\",\r\n" + 
	            		"  \"description\": \"Project description2_update_test\"\r\n" + 
	            		"}")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(jsonPath("$.id").exists())				
				.andExpect(jsonPath("$.id").value(2))
				.andExpect(jsonPath("$.projectName").exists())
				.andExpect(jsonPath("$.projectName").value("Project name2_update_test"))
				.andExpect(jsonPath("$.projectIdentifier").exists())
				.andExpect(jsonPath("$.projectIdentifier").value("E002"))
				.andExpect(jsonPath("$.description").exists())
				.andExpect(jsonPath("$.description").value("Project description2_update_test"))
				.andExpect(jsonPath("$.*", hasSize(8)))
				.andReturn();
	}

	@Test
	public void deleteProjectByIdentity_test() throws Exception {		
		this.mockMvc.perform(MockMvcRequestBuilders.delete(URI + "E002")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.projectIdentifier")
                        .value("identifier id: E002 was successfully deleted"))
                .andReturn();
	}


}
