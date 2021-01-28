package com.qa.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO 
{
	private Long playerID;
	private String playerName;
	private String playerIGN;
}
