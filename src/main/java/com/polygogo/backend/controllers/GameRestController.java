package com.polygogo.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.polygogo.backend.models.entity.Game;
import com.polygogo.backend.models.entity.PlayerDetails;
import com.polygogo.backend.models.entity.Properties;
import com.polygogo.backend.models.entity.User;
import com.polygogo.backend.models.service.IGameService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/game")
public class GameRestController {

	@Autowired
	private IGameService gameService;
	
	@GetMapping("/")
	public List<Game> index(){
		return gameService.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Game show(@PathVariable Long id) {
		return gameService.findById(id);
	}
	

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Game create(@RequestBody Game game) {
		return gameService.save(game);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Game update(@RequestBody Game game, @PathVariable Long id) {
		Game actualGame = gameService.findById(id);
		
		actualGame = game;
		
		return gameService.save(actualGame);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/search")
	@ResponseStatus(HttpStatus.OK)
	public Game enterGame(@RequestBody User user) {
		Game game = gameService.enterGame(user);
		return game;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/check")
	@ResponseStatus(HttpStatus.OK)
	public Game checkIfPlayerInGame(@RequestBody User user) {
		return gameService.checkIfPlayerInGame(user);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/move")
	@ResponseStatus(HttpStatus.OK)
	public Properties movePlayer(@RequestBody PlayerDetails playerDetails) {
		return gameService.movePlayer(playerDetails);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/properties")
	@ResponseStatus(HttpStatus.OK)
	public List<Properties> getProperties() {
		return gameService.getProperties();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/buy/{id}")
	@ResponseStatus(HttpStatus.OK)
	public PlayerDetails buyProperty(@RequestBody PlayerDetails playerDetails, @PathVariable Long id) {
		return gameService.buyProperty(playerDetails, id);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/pasar")
	@ResponseStatus(HttpStatus.OK)
	public PlayerDetails pasarTurno(@RequestBody PlayerDetails playerDetails) {
		return gameService.pasarTurno(playerDetails);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/lastresponse")
	@ResponseStatus(HttpStatus.OK)
	public PlayerDetails lastResponse(@RequestBody PlayerDetails playerDetails) {
		return gameService.setLastResponse(playerDetails);
	}
	
}
