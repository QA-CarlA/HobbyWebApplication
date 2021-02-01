package com.qa.rest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.HobbyWebAppApplication;
import com.qa.persistence.domain.TeamDomain;
import com.qa.persistence.dto.PlayerDTO;
import com.qa.persistence.dto.TeamDTO;

@SpringBootTest(classes = HobbyWebAppApplication.class)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:schema-test.sql", "classpath:data-test.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class TeamControllerIntegrationTest 
{
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ObjectMapper jsonifier;
	
	private TeamDTO mapToDTO(TeamDomain team)
	{
		return this.mapper.map(team,  TeamDTO.class);
	}
	
	// TEST VARIABLES
	private final Long TEST_ID = 1L;
	private final String TEST_NAME = "Sukonbu";
	
	
	// TESTS
	// CREATE
	@Test
	public void createPlayer() throws Exception
	{
		TeamDomain TEST_TEAM = new TeamDomain();
		TEST_TEAM.setTeamName(TEST_NAME);
		TEST_TEAM.setTeamID(3L);
		
		// Prepared REST Request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.POST, "/team/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(TEST_TEAM))
				.accept(MediaType.APPLICATION_JSON);
		
		// Assertion Checks
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(mapToDTO(TEST_TEAM)));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		
		// Perform and Assert
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	
	// READ by ID
	@Test
	public void testReadTeam() throws Exception
	{
		
		List<PlayerDTO> listPlayer = new ArrayList<>();
		listPlayer.add(new PlayerDTO(1L, "Ayame", "GON"));
		listPlayer.add(new PlayerDTO(2L, "Rion", "Takamij"));
		TeamDTO TEST_TEAM = new TeamDTO(TEST_ID, TEST_NAME, listPlayer);
		
		// Prepared REST Request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.GET, "/team/read/" + TEST_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		// Assertion checks
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(TEST_TEAM));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
				
		//Perform & assert
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	
	// READ ALL Player
	@Test
	public void testReadAllTeam() throws Exception
	{
		List<PlayerDTO> listPlayer1 = new ArrayList<>();
		listPlayer1.add(new PlayerDTO(1L, "Ayame", "GON"));
		listPlayer1.add(new PlayerDTO(2L, "Rion", "Takamij"));
		List<PlayerDTO> listPlayer2 = new ArrayList<>();
		listPlayer2.add(new PlayerDTO(3L, "Ren", "Joker"));
		
		List<TeamDTO> team = new ArrayList<>();
		TeamDTO team1 = new TeamDTO(TEST_ID, TEST_NAME, listPlayer1);
		TeamDTO team2 = new TeamDTO(2L, "Doge", listPlayer2);
		team.add(team1);
		team.add(team2);
		
		// Prepared REST Request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.GET, "/team/readAll")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		// Assertion checks
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(team));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		//Perform & assert
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	
	// UPDATE
	@Test
	public void update() throws Exception
	{
		List<PlayerDTO> listPlayer = new ArrayList<>();
		listPlayer.add(new PlayerDTO(1L, "Ayame", "GON"));
		listPlayer.add(new PlayerDTO(2L, "Rion", "Takamij"));
		TeamDTO TEST_TEAM = new TeamDTO(TEST_ID, "963", listPlayer);
		
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.PUT, "/team/update/" + TEST_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(TEST_TEAM))
				.accept(MediaType.APPLICATION_JSON);
		
		// Assertion Checks
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(TEST_TEAM));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
				
		// Perform and Assert
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	
	// DELETE
	@Test
	public void testDelete() throws Exception
	{
		MockHttpServletRequestBuilder mockRequest =
				MockMvcRequestBuilders.request(HttpMethod.DELETE, "/team/delete/" + TEST_ID);
		
		this.mock.perform(mockRequest)
		.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
}
