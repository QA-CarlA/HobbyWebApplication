package com.qa.persistence.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.HobbyWebAppApplication;

@SpringBootTest(classes = HobbyWebAppApplication.class)
public class PlayerDTOTest 
{
	@Test
	public void testToString()
	{
		PlayerDTO TEST_PLAYER = new PlayerDTO(1L, "Fubuki", "FBK");
		String output = "PlayerDTO(playerID=1, playerName=Fubuki, playerIGN=FBK)";
		
		assertEquals(TEST_PLAYER.toString(), output);
	}
	
	@Test
	public void testEqualsAndHashCode()
	{
		PlayerDTO TEST_PLAYER1 = new PlayerDTO(1L, "Fubuki", "FBK");
		PlayerDTO TEST_PLAYER2 = new PlayerDTO(1L, "Fubuki", "FBK");
		
		assertEquals(TEST_PLAYER1, TEST_PLAYER2);
		assertEquals(TEST_PLAYER1.hashCode(), TEST_PLAYER2.hashCode());
	}
	
	@Test
	public void testNotEquals()
	{
		PlayerDTO TEST_PLAYER1 = new PlayerDTO(1L, "Fubuki", "FBK");
		PlayerDTO TEST_PLAYER2 = new PlayerDTO(2L, "Fubuki", "FBK");
		
		assertFalse(TEST_PLAYER1.equals(TEST_PLAYER2));
	}
}
