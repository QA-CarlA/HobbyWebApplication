package com.qa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.persistence.domain.PlayerDomain;
import com.qa.persistence.dto.PlayerDTO;
import com.qa.persistence.repos.PlayerRepo;
import com.qa.utils.MyBeanUtils;

@Service
public class PlayerService 
{
	private PlayerRepo repo;
	private ModelMapper mapper;
	
	@Autowired
	public PlayerService(PlayerRepo repo, ModelMapper mapper)
	{
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private PlayerDTO mapToDTO(PlayerDomain model)
	{
		return this.mapper.map(model, PlayerDTO.class);
	}
	
	//CRUD
	
	//CREATE
	public PlayerDTO create(PlayerDomain model)
	{
		return this.mapper.map(model,  PlayerDTO.class);
	}
	
	//READ
	public PlayerDTO readPlayer(Long id)
	{
		return this.mapToDTO(this.repo.findById(id).orElseThrow());
	}
	
	public List<PlayerDTO> readAll()
	{
		List<PlayerDomain> playerList = this.repo.findAll();
		List<PlayerDTO> playerListDTO = playerList.stream().map(this::mapToDTO).collect(Collectors.toList());
		return playerListDTO;
	}
	
	//UPDATE
	public PlayerDTO updatePlayer(Long id, PlayerDomain player)
	{
		PlayerDomain updatedPlayer = this.repo.findById(id).orElseThrow();
		MyBeanUtils.mergeNotNull(player, updatedPlayer);
		return this.mapToDTO(this.repo.save(updatedPlayer));
	}
	
	//DELETE
	public boolean deletePlayer(Long id)
	{
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
}
