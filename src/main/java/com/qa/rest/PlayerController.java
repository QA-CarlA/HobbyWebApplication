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

import com.qa.persistence.domain.PlayerDomain;
import com.qa.persistence.dto.PlayerDTO;
import com.qa.services.PlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController 
{
	private PlayerService service;
	
	@Autowired
	public PlayerController(PlayerService service)
	{
		super();
		this.service = service;
	}
	
	// GET, POST, PUT, DELETE
	
		//READALL
		@GetMapping("/readAll")
		public ResponseEntity<List<PlayerDTO>> readAllPlayer()
		{
			return ResponseEntity.ok(this.service.readAll());
		}
		
		//READBYID
		@GetMapping("/read/{playerID}")
		public ResponseEntity<PlayerDTO> readPlayer(@PathVariable("playerID") Long playerID) 
		{
			return ResponseEntity.ok(this.service.readPlayer(playerID));
		}
		
		//CREATE
		@PostMapping("/create")
		public ResponseEntity<PlayerDTO> create(@RequestBody PlayerDomain player)
		{
			return new ResponseEntity<PlayerDTO>(this.service.create(player), HttpStatus.CREATED);
		}
		
		//UPDATE
		@PutMapping("/update/{playerID}")
		public ResponseEntity<PlayerDTO> update(@PathVariable("playerID") Long id, @RequestBody PlayerDomain updatePlayer)
		{
			return new ResponseEntity<>(this.service.updatePlayer(id, updatePlayer), HttpStatus.ACCEPTED);
		}
		
		//DELETE
		@DeleteMapping("/delete/{playerID}")
		public ResponseEntity<PlayerDTO> deletePlayer(@PathVariable Long id)
		{
			return this.service.deletePlayer(id) ? 
					new ResponseEntity<>(HttpStatus.NO_CONTENT) :
					new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
}
