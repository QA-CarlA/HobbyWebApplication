package com.qa.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.HobbyWebAppApplication;

@SpringBootTest(classes = HobbyWebAppApplication.class)
public class PlayerDomainTest 
{
	@Test
	public void testToString()
	{
		PlayerDomain TEST_PLAYER = 
				new PlayerDomain(1L, "Fubuki", "FBK", new TeamDomain(1L, "Sukonbu", new ArrayList<PlayerDomain>()));
		String output = "PlayerDomain(playerID=1, playerName=Fubuki, playerIGN=FBK, team=TeamDomain(teamID=1, teamName=Sukonbu, playerList=[]))";
		
		assertEquals(TEST_PLAYER.toString(), output);
	}
	
	@Test
	public void testEqualsAndHashCode()
	{
		PlayerDomain TEST_PLAYER1 = 
				new PlayerDomain(1L, "Fubuki", "FBK", new TeamDomain(1L, "Sukonbu", new ArrayList<PlayerDomain>()));
		PlayerDomain TEST_PLAYER2 = 
				new PlayerDomain(1L, "Fubuki", "FBK", new TeamDomain(1L, "Sukonbu", new ArrayList<PlayerDomain>()));
		
		assertEquals(TEST_PLAYER1, TEST_PLAYER2);
		assertEquals(TEST_PLAYER1.hashCode(), TEST_PLAYER2.hashCode());
	}
	
	@Test
	public void testNotEquals()
	{
		PlayerDomain TEST_PLAYER1 = 
				new PlayerDomain(1L, "Fubuki", "FBK", new TeamDomain(1L, "Sukonbu", new ArrayList<PlayerDomain>()));
		PlayerDomain TEST_PLAYER2 = 
				new PlayerDomain(2L, "Fubuki", "FBK", new TeamDomain(1L, "Sukonbu", new ArrayList<PlayerDomain>()));
		
		assertFalse(TEST_PLAYER1.equals(TEST_PLAYER2));
	}
}
