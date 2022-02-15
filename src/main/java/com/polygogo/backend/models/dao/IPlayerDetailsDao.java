package com.polygogo.backend.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.polygogo.backend.models.entity.PlayerDetails;

public interface IPlayerDetailsDao extends CrudRepository<PlayerDetails, Long>{

	@Query(value="select * from players_details p "
			+ "where p.game_id = :#{#game_id}"
			,nativeQuery = true)
	List<PlayerDetails> findPlayerDetailsFromGame(Long game_id);
	
	
	
}
