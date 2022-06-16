package com.polygogo.backend.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.polygogo.backend.models.entity.Game;
import com.polygogo.backend.models.entity.User;
import com.polygogo.backend.models.entity.PlayerDetails;
import com.polygogo.backend.models.entity.Properties;

public interface IGameDao extends CrudRepository<Game, Long>{
	
	@Query(value="select * from games "
			+ "inner join players_details on players_details.game_id = games.id "
			+ "where games.id = :#{#game.id}"
			,nativeQuery = true)
	List<PlayerDetails> getPlayerDetails(@Param("game") Game game);
	
	@Query(value="select games.id from games "
			+ "inner join players_details on players_details.game_id = games.id "
			+ "where games.enabled = true "
			+ "group by games.id "
			+ "having count(players_details) < 4 "
			+ "order by games.id "
			+ "limit 1"
			,nativeQuery = true)
	Long getGameIdwhereAreLessThan4Players();
	
	@Query(value="select games.id from games "
			+ "inner join players_details on players_details.game_id = games.id "
			+ "where games.enabled = true and players_details.user_id = :#{#user.username} "
			+ "group by games.id"
			,nativeQuery = true)
	Long getGameIfPlayerInGame(@Param("user") User user);

}
