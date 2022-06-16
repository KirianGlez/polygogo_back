package com.polygogo.backend.models.service;

import java.util.List;

import com.polygogo.backend.models.entity.Game;
import com.polygogo.backend.models.entity.PlayerDetails;
import com.polygogo.backend.models.entity.Properties;
import com.polygogo.backend.models.entity.User;

public interface IGameService {

	public List<Game> findAll();
	
	public Game findById(Long id);
	
	public Game save(Game game);
	
	public Game enterGame(User user);
	
	public Game checkIfPlayerInGame(User user);
	
	public List<PlayerDetails> getPlayerDetails(Long game_id);
	
	public Properties movePlayer(PlayerDetails playerDetails);
	
	public List<Properties> getProperties();
	
	public PlayerDetails buyProperty(PlayerDetails playerDetails, Long property_id);
	
	public PlayerDetails pasarTurno(PlayerDetails playerDetails);
	
	public PlayerDetails setLastResponse(PlayerDetails playerDetails);
}
