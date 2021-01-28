package com.qa.persistence.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO 
{
	private Long teamID;
	private String teamName;
	private List<PlayerDTO> listPlayer;
}
