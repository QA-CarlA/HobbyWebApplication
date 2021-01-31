package com.qa.persistence.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.HobbyWebAppApplication;

@SpringBootTest(classes = HobbyWebAppApplication.class)
public class TeamDTOTest 
{
	@Test
	public void testToString()
	{
		TeamDTO TEST_TEAM = new TeamDTO(1L, "Bamboozle", new ArrayList<PlayerDTO>());
		String output = "TeamDTO(teamID=1, teamName=Bamboozle, listPlayer=[])";
		
		assertEquals(TEST_TEAM.toString(), output);
	}
	
	@Test
	public void testEqualsAndHashCode()
	{
		TeamDTO TEST_TEAM1 = new TeamDTO(1L, "Bamboozle", new ArrayList<PlayerDTO>());
		TeamDTO TEST_TEAM2 = new TeamDTO(1L, "Bamboozle", new ArrayList<PlayerDTO>());
		
		assertEquals(TEST_TEAM1, TEST_TEAM2);
		assertEquals(TEST_TEAM1.hashCode(), TEST_TEAM2.hashCode());
	}
	
	@Test
	public void testNotEquals()
	{
		TeamDTO TEST_TEAM1 = new TeamDTO(1L, "Bamboozle", new ArrayList<PlayerDTO>());
		TeamDTO TEST_TEAM2 = new TeamDTO(1L, "BAM", new ArrayList<PlayerDTO>());
		
		assertFalse(TEST_TEAM1.equals(TEST_TEAM2));
	}
}
