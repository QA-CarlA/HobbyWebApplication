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
import com.qa.persistence.dto.TeamDTO;
import com.qa.persistence.repos.TeamRepo;

@SpringBootTest(classes = HobbyWebAppApplication.class)
public class TeamServiceUnitTest 
{
	@MockBean
	private TeamRepo repo;
	
	@Autowired 
	private ModelMapper mapper;
	
	@Autowired
	private TeamService service;
	
	private TeamDTO mapToDTO(TeamDomain team) 
	{
		return this.mapper.map(team, TeamDTO.class);
	}
	
	@Test
	public void constructorTest() 
	{
		TeamService testService = new TeamService(repo, mapper);
		
		assertThat(testService).isInstanceOf(TeamService.class);
	}
	
	@Test
	public void createTeamTest() {
		TeamDomain TEST_TEAM = new TeamDomain (1L, "Bamboozle", new ArrayList<PlayerDomain>());
		
		Mockito.when(this.repo.save(Mockito.any(TeamDomain.class))).thenReturn(TEST_TEAM);
		assertEquals(this.service.create(TEST_TEAM), mapToDTO(TEST_TEAM));
		Mockito.verify(this.repo, Mockito.times(1)).save(TEST_TEAM);
	}
	
	@Test
	public void readTeamTest() {
		TeamDomain TEST_TEAM = new TeamDomain (1L, "Bamboozle", new ArrayList<PlayerDomain>());
		
		Mockito.when(this.repo.findById(1L)).thenReturn(Optional.of(TEST_TEAM));
		TeamDTO result = this.service.readTeam(1L);
		assertEquals(result, mapToDTO(TEST_TEAM));
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
	}
	
	@Test
	public void readAllTeamTest()
	{
		List<PlayerDomain> players =  new ArrayList<PlayerDomain>();
		players.add(new PlayerDomain(1L, "Fubuki", "FBK", new TeamDomain()));
		List<TeamDomain> teams = new ArrayList<>();
		teams.add(new TeamDomain(1L, "Bamboozle", players));

	
		List<PlayerDTO> playerDTO =  new ArrayList<PlayerDTO>();
		playerDTO.add(new PlayerDTO(1L, "Fubuki", "FBK"));
		List<TeamDTO> teamDTO = new ArrayList<>();
		teamDTO.add(new TeamDTO(1L, "Bamboozle", playerDTO));


		Mockito.when(this.repo.findAll()).thenReturn(teams);
		assertEquals(this.service.readAll(), teamDTO);
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void updateTeamTest()
	{
		TeamDomain TEST_TEAM = new TeamDomain (1L, "Bamboozle", new ArrayList<PlayerDomain>());
		
		List<PlayerDomain> players =  new ArrayList<PlayerDomain>();
		players.add(new PlayerDomain(1L, "Fubuki", "FBK", new TeamDomain()));
		List<PlayerDTO> playerDTO =  new ArrayList<PlayerDTO>();
		playerDTO.add(new PlayerDTO(1L, "Fubuki", "FBK"));
		
		
		TeamDomain UPDATE_TEAM = new TeamDomain(1L, "Bamboozle", players);
		TeamDTO UPDATE_TEAM_DTO = new TeamDTO(1L, "Bamboozle", playerDTO);
		
		Mockito.when(this.repo.findById(1L)).thenReturn(Optional.of(TEST_TEAM));
		Mockito.when(this.repo.save(UPDATE_TEAM)).thenReturn(UPDATE_TEAM);
		
		assertEquals(this.service.updateTeam(1L, UPDATE_TEAM), UPDATE_TEAM_DTO);
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).save(UPDATE_TEAM);
	}
	
	@Test
	public void deleteTeamTest() 
	{
		Mockito.when(this.repo.existsById(1L)).thenReturn(false);
		
		assertEquals(this.service.deleteTeam(1L), true);
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
	}
}
