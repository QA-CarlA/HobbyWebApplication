package com.qa.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.persistence.domain.TeamDomain;

public interface TeamRepo extends JpaRepository<TeamDomain, Long> 
{
	//CRUD for TeamDomain class
}
