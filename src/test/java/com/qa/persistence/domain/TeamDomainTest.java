package com.qa.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.HobbyWebAppApplication;

@SpringBootTest(classes = HobbyWebAppApplication.class)
public class TeamDomainTest 
{
	@Test
	public void testToString()
	{
		TeamDomain TEST_TEAM = new TeamDomain(1L, "Bamboozle", new ArrayList<PlayerDomain>());
		String output = "TeamDomain(teamID=1, teamName=Bamboozle, playerList=[])";
		
		assertEquals(TEST_TEAM.toString(), output);
	}
	
	@Test
	public void testEqualsAndHashCode()
	{
		TeamDomain TEST_TEAM1 = new TeamDomain(1L, "Bamboozle", new ArrayList<PlayerDomain>());
		TeamDomain TEST_TEAM2 = new TeamDomain(1L, "Bamboozle", new ArrayList<PlayerDomain>());
		
		assertEquals(TEST_TEAM1, TEST_TEAM2);
		assertEquals(TEST_TEAM1.hashCode(), TEST_TEAM2.hashCode());
	}
	
	@Test
	public void testNotEquals()
	{
		TeamDomain TEST_TEAM1 = new TeamDomain(1L, "Bamboozle", new ArrayList<PlayerDomain>());
		TeamDomain TEST_TEAM2 = new TeamDomain(2L, "BAM", new ArrayList<PlayerDomain>());
		
		assertFalse(TEST_TEAM1.equals(TEST_TEAM2));
	}
}
