package com.qa.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.persistence.domain.PlayerDomain;

@Repository
public interface PlayerRepo extends JpaRepository<PlayerDomain, Long>
{
	//CRUD for PlayerDomain class
}
