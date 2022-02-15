package com.polygogo.backend.models.service.implementation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polygogo.backend.models.dao.IGameDao;
import com.polygogo.backend.models.dao.IPlayerDetailsDao;
import com.polygogo.backend.models.dao.IPropertiesDao;
import com.polygogo.backend.models.entity.Game;
import com.polygogo.backend.models.entity.PlayerDetails;
import com.polygogo.backend.models.entity.Properties;
import com.polygogo.backend.models.entity.User;
import com.polygogo.backend.models.service.IGameService;

@Service
public class GameServiceImpl implements IGameService{

	@Autowired
	private IGameDao gameDao;
	
	@Autowired
	private IPlayerDetailsDao playerDetailsDao;
	
	@Autowired
	private IPropertiesDao propertiesDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Game> findAll() {
		return (List<Game>) gameDao.findAll();
	}

	@Override
	@Transactional
	public Game findById(Long id) {
		return gameDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Game save(Game game) {
		return gameDao.save(game);
	}

	@Override
	public Game enterGame(User user) {
		Game game = new Game();
		Long game_id = gameDao.getGameIdwhereAreLessThan4Players();

		if(game_id==null) {
			System.out.println("no hay juego");
			game = new Game();
			Set<PlayerDetails> listPlayerDetails = new HashSet<PlayerDetails>();
			PlayerDetails playerDetails = new PlayerDetails();
			
			game.setEnabled(true);
			game = gameDao.save(game);
			
			playerDetails.setPlayer_number(1);
			playerDetails.setUser_id(user.getUsername());
			playerDetails.setGame_id(game.getId());
			playerDetails.setCoins(1000);
			playerDetails.setPosition(0);
			playerDetails.setTurn(true);
			playerDetails.setProperties(new ArrayList<int[]>());
			
			playerDetails = playerDetailsDao.save(playerDetails);
			
			listPlayerDetails.add(playerDetails);
			
			game.setPlayers_details(listPlayerDetails);
			
			game = gameDao.save(game);
		
		}else {
			game =gameDao.findById(game_id).orElse(null);
			
			Set<PlayerDetails> listPlayerDetails = game.getPlayers_details();
			
			PlayerDetails playerDetails = new PlayerDetails();
			
			playerDetails.setPlayer_number(listPlayerDetails.size()+1);
			playerDetails.setUser_id(user.getUsername());
			playerDetails.setGame_id(game.getId());
			playerDetails.setCoins(1000+listPlayerDetails.size()*50);
			playerDetails.setPosition(0);
			playerDetails.setTurn(false);
			playerDetails.setProperties(new ArrayList<int[]>());
			
			playerDetails = playerDetailsDao.save(playerDetails);
			
			listPlayerDetails.add(playerDetails);
			
			game.setPlayers_details(listPlayerDetails);
			
			game = gameDao.save(game);
		}
		
		return game;
	}

	@Override
	public Game checkIfPlayerInGame(User user) {
		Long game_id = gameDao.getGameIfPlayerInGame(user);
		
		if(game_id == null) 
			return null;
		else
			return gameDao.findById(game_id).orElse(null);
	}

	@Override
	public List<PlayerDetails>getPlayerDetails(Long game_id) {
		
		return playerDetailsDao.findPlayerDetailsFromGame(game_id);
	}
	
	@Override
	public Properties movePlayer(PlayerDetails playerDetails) {
		
		int random = -1;
		if(playerDetails.isTurn()) {
			System.out.println("hola");
			return null;
		}
		random = (int)(Math.random()*6+1);
		playerDetails.setPosition(playerDetails.getPosition()+random);
		if(playerDetails.getPosition()>=27) {
			playerDetails.setPosition(playerDetails.getPosition()-27);
			playerDetails.setCoins(playerDetails.getCoins()+200);
		}
		playerDetailsDao.save(playerDetails);
		
		return propertiesDao.findById((long) playerDetails.getPosition()).orElse(null);
	}

	@Override
	public List<Properties> getProperties() {
		
		return (List<Properties>) propertiesDao.findAll();
	}

	@Override
	public PlayerDetails buyProperty(PlayerDetails playerDetails, Long property_id) {
		
		playerDetails = playerDetailsDao.findById(playerDetails.getId()).orElse(null);
		ArrayList<int[]> details=playerDetails.getProperties();
		
		int[] a = {Math.toIntExact(property_id), 0};
		
		details.add(a);
		
		playerDetails.setProperties(details);
		
		playerDetails.setCoins(playerDetails.getCoins()-propertiesDao.findById(property_id).orElse(null).getPrice());
		
		return playerDetailsDao.save(playerDetails);
	}

	@Override
	public PlayerDetails pasarTurno(PlayerDetails playerDetails) {
		
		List<PlayerDetails> player_details =getPlayerDetails(playerDetails.getGame_id());
		
		PlayerDetails[] ordenado = new PlayerDetails[4];
		
		for(PlayerDetails b : player_details) {
			ordenado[b.getPlayer_number()-1] = b;
		}
		
		if(playerDetails.getPlayer_number()==4) {
			
			ordenado[3].setTurn(false);
			
			ordenado[0].setTurn(true);
			
			playerDetailsDao.save(ordenado[3]);
			playerDetailsDao.save(ordenado[0]);
			
		}else {
			ordenado[playerDetails.getPlayer_number()-1].setTurn(false);
			ordenado[playerDetails.getPlayer_number()].setTurn(true);
			
			playerDetailsDao.save(ordenado[playerDetails.getPlayer_number()-1]);
			playerDetailsDao.save(ordenado[playerDetails.getPlayer_number()]);
		}
		
		return ordenado[playerDetails.getPlayer_number()-1];
	}

}
