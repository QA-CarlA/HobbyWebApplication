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
	private String playerFirstName;
	private String playerLastName;
	private String playerIGN;
	
	@ManyToOne
	private TeamDomain team;
}
