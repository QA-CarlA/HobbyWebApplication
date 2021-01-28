package com.qa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.persistence.domain.TeamDomain;
import com.qa.persistence.dto.TeamDTO;
import com.qa.persistence.repos.TeamRepo;
import com.qa.utils.MyBeanUtils;

@Service
public class TeamService 
{
	private TeamRepo repo;
	private ModelMapper mapper;
	
	@Autowired
	public TeamService(TeamRepo repo, ModelMapper mapper)
	{
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private TeamDTO mapToDTO(TeamDomain model)
	{
		return this.mapper.map(model, TeamDTO.class);
	}
	
	//CRUD
	
	//CREATE
	public TeamDTO create(TeamDomain model)
	{
		return this.mapper.map(model,  TeamDTO.class);
	}
	
	//READ
	public TeamDTO readTeam(Long id)
	{
		return this.mapToDTO(this.repo.findById(id).orElseThrow());
	}
	
	public List<TeamDTO> readAll()
	{
		List<TeamDomain> teamList = this.repo.findAll();
		List<TeamDTO> teamListDTO = teamList.stream().map(this::mapToDTO).collect(Collectors.toList());
		return teamListDTO;
	}
	
	//UPDATE
	public TeamDTO updateTeam(Long id, TeamDomain team)
	{
		TeamDomain updatedTeam = this.repo.findById(id).orElseThrow();
		MyBeanUtils.mergeNotNull(team, updatedTeam);
		return this.mapToDTO(this.repo.save(updatedTeam));
	}
	
	//DELETE
	public boolean deleteTeam(Long id)
	{
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
}
