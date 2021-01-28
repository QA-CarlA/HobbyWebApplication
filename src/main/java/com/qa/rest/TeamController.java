package com.qa.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.persistence.domain.TeamDomain;
import com.qa.persistence.dto.TeamDTO;
import com.qa.services.TeamService;

@RestController
@RequestMapping("/team")
public class TeamController 
{
	private TeamService service;
	
	@Autowired
	public TeamController(TeamService service)
	{
		super();
		this.service = service;
	}
	
	// GET, POST, PUT, DELETE
	
		//READALL
		@GetMapping("/readAll")
		public ResponseEntity<List<TeamDTO>> readAllTeam()
		{
			return ResponseEntity.ok(this.service.readAll());
		}
		
		//READBYID
		@GetMapping("/read/{id}")
		public ResponseEntity<TeamDTO> readTeam(@PathVariable("teamID") Long id)
		{
			return ResponseEntity.ok(this.service.readTeam(id));
		}
		
		//CREATE
		@PostMapping("/create")
		public ResponseEntity<TeamDTO> create(@RequestBody TeamDomain team)
		{
			return new ResponseEntity<TeamDTO>(this.service.create(team), HttpStatus.CREATED);
		}
		
		//UPDATE
		@PutMapping("/update/{id}")
		public ResponseEntity<TeamDTO> update(@PathVariable("teamID") Long id, @RequestBody TeamDomain updateTeam)
		{
			return new ResponseEntity<>(this.service.updateTeam(id, updateTeam), HttpStatus.ACCEPTED);
		}
		
		//DELETE
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<TeamDTO> deleteTeam(@PathVariable Long id)
		{
			return this.service.deleteTeam(id) ? 
					new ResponseEntity<>(HttpStatus.NO_CONTENT) :
					new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
}