package com.qa.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.qa.persistence.domain.PlayerDomain;
import com.qa.persistence.dto.PlayerDTO;

@SpringBootTest(classes = HobbyWebAppApplication.class)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:schema-test.sql", "classpath:data-test.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class PlayerControllerIntegrationTest 
{
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ObjectMapper jsonifier;
	
	private PlayerDTO mapToDTO(PlayerDomain player)
	{
		return this.mapper.map(player,  PlayerDTO.class);
	}
	
	// TEST VARIABLES
	private final Long TEST_ID = 1L;
	private final String TEST_NAME = "Ayame";
	private final String TEST_IGN = "GON";
	
	
	// TESTS
	// CREATE
	@Test
	public void createPlayer() throws Exception
	{
		PlayerDomain TEST_PLAYER = new PlayerDomain();
		TEST_PLAYER.setPlayerName("Mark");
		TEST_PLAYER.setPlayerIGN("Maryne");
		TEST_PLAYER.setPlayerID(4L);
		// Prepared REST Request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.POST, "/player/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(TEST_PLAYER))
				.accept(MediaType.APPLICATION_JSON);
		
		// Assertion Checks
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(mapToDTO(TEST_PLAYER)));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		
		// Perform and Assert
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	
	// READ by ID
	@Test
	public void testReadPlayer() throws Exception
	{
		PlayerDTO TEST_PLAYER = new PlayerDTO();
		TEST_PLAYER.setPlayerID(TEST_ID);
		TEST_PLAYER.setPlayerName(TEST_NAME);
		TEST_PLAYER.setPlayerIGN(TEST_IGN);
		
		// Prepared REST Request
		String content = this.mock.perform(request(HttpMethod.GET, ("/player/read/" + TEST_ID))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		assertEquals(this.jsonifier.writeValueAsString(TEST_PLAYER), content);
	}
	
	// READ ALL Player
	@Test
	public void testReadAllPlayers() throws Exception
	{
		List<PlayerDTO> playerList = new ArrayList<>();
		playerList.add(new PlayerDTO(1L, "Ayame", "GON"));
		playerList.add(new PlayerDTO(2L, "Rion", "Takamij"));
		playerList.add(new PlayerDTO(3L, "Ren", "Joker"));
		
		// Prepared REST Request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.GET, "/player/readAll")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		// Assertion checks
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(playerList));
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
		PlayerDomain TEST_PLAYER = new PlayerDomain("Fubuki", "FBK");
		TEST_PLAYER.setPlayerID(TEST_ID);
		
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.PUT, "/player/update/" + TEST_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(TEST_PLAYER))
				.accept(MediaType.APPLICATION_JSON);
		
		// Assertion Checks
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(mapToDTO(TEST_PLAYER)));
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
				MockMvcRequestBuilders.request(HttpMethod.DELETE, "/player/delete/" + TEST_ID);
		
		this.mock.perform(mockRequest)
		.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
}
