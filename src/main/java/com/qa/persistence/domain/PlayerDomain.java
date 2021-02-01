package com.qa.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDomain 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long playerID;
	
	@NotNull
	private String playerName;
	@NotNull
	private String playerIGN;
	
	@ManyToOne
	private TeamDomain team;
	
	public PlayerDomain(String playerName, String playerIGN) 
	{
		super();
		this.playerID = 0L;
		this.playerName = playerName;
		this.playerIGN = playerIGN;
	}
	
	
}
