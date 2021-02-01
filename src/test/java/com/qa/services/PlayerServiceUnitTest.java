package com.qa.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.HobbyWebAppApplication;
import com.qa.persistence.domain.PlayerDomain;
import com.qa.persistence.domain.TeamDomain;
import com.qa.persistence.dto.PlayerDTO;
import com.qa.persistence.repos.PlayerRepo;

@SpringBootTest(classes = HobbyWebAppApplication.class)
public class PlayerServiceUnitTest 
{
	@MockBean
	private PlayerRepo repo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PlayerService service;
	
	private PlayerDTO mapToDTO(PlayerDomain player)
	{
		return this.mapper.map(player, PlayerDTO.class);
	}
	
	@Test
	public void consructorTest()
	{
		PlayerService testService = new PlayerService(repo, mapper);
		
		assertThat(testService).isInstanceOf(PlayerService.class);
	}
	
	@Test
	public void createTest()
	{
		PlayerDomain TEST_PLAYER = 
				new PlayerDomain(1L, "Fubuki", "FBK", new TeamDomain(1L, "Sukonbu", new ArrayList<PlayerDomain>()));
		Mockito.when(this.repo.save(Mockito.any(PlayerDomain.class))).thenReturn(TEST_PLAYER);
		
		PlayerDTO result = this.service.create(TEST_PLAYER);
		assertEquals(result, mapToDTO(TEST_PLAYER));
		Mockito.verify(this.repo, Mockito.times(1)).save(TEST_PLAYER);
	}
	
	@Test
	public void readPlayerTest()
	{
		PlayerDomain TEST_PLAYER = 
				new PlayerDomain(1L, "Fubuki", "FBK", new TeamDomain(1L, "Sukonbu", new ArrayList<PlayerDomain>()));
		
		Mockito.when(this.repo.findById(1L)).thenReturn(Optional.of(TEST_PLAYER));
		assertEquals(this.service.readPlayer(1L), mapToDTO(TEST_PLAYER));
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
	}
	
	@Test
	public void readAllPlayerTest()
	{
		List<PlayerDomain> players = new ArrayList<>();
		players.add(new PlayerDomain(1L, "Fubuki", "FBK", new TeamDomain(1L, "Sukonbu", new ArrayList<PlayerDomain>())));
		players.add(new PlayerDomain(2L, "Noah", "963Noah", new TeamDomain(2L, "963Fam", new ArrayList<PlayerDomain>())));
		
		List<PlayerDTO> playerDTO = new ArrayList<>();
		playerDTO.add(new PlayerDTO(1L, "Fubuki", "FBK"));
		playerDTO.add(new PlayerDTO(2L, "Noah", "963Noah"));
		
		Mockito.when(this.repo.findAll()).thenReturn(players);
		
		List<PlayerDTO> dtoList = this.service.readAll();
		
		assertEquals(dtoList, playerDTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void updatePlayerTest()
	{
		PlayerDomain TEST_PLAYER = 
				new PlayerDomain(1L, "Fubuki", "FBK", new TeamDomain(1L, "Sukonbu", new ArrayList<PlayerDomain>()));
		
		PlayerDomain TEST_UPDATE = 
				new PlayerDomain(1L, "Noah", "963Noah", new TeamDomain(1L, "963Fam", new ArrayList<PlayerDomain>()));
		PlayerDTO TEST_UPDATE_DTO =
				new PlayerDTO(1L, "Noah", "963Noah");
		
		Mockito.when(this.repo.findById(1L)).thenReturn(Optional.of(TEST_PLAYER));
		Mockito.when(this.repo.save(TEST_UPDATE)).thenReturn(TEST_UPDATE);
		
		PlayerDTO result = this.service.updatePlayer(1L, TEST_UPDATE);
		
		assertEquals(result, TEST_UPDATE_DTO);
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).save(TEST_UPDATE);
		
	}
	
	@Test
	public void deletePlayerTest() 
	{
		Mockito.when(this.repo.existsById(1L)).thenReturn(false);
		
		assertEquals(this.service.deletePlayer(1L), true);
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
	}
	
	
}
