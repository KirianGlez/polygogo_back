package com.polygogo.backend.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

@Entity
@Table(name="players_details")
public class PlayerDetails implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private long game_id;
	
	private int player_number;
	
	private String user_id;
	
	private int position;
	
	private boolean turn;
	
	private int coins;
	
	@Nullable
	private ArrayList<int[]> properties;
	
	public PlayerDetails() {}

	public PlayerDetails(long id, long game_id, int player_number, String user_id, int position, boolean turn,
			int coins, ArrayList<int[]> properties) {
		super();
		this.id = id;
		this.game_id = game_id;
		this.player_number = player_number;
		this.user_id = user_id;
		this.position = position;
		this.turn = turn;
		this.coins = coins;
		this.properties = properties;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getGame_id() {
		return game_id;
	}

	public void setGame_id(long game_id) {
		this.game_id = game_id;
	}

	public int getPlayer_number() {
		return player_number;
	}

	public void setPlayer_number(int player_number) {
		this.player_number = player_number;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public ArrayList<int[]> getProperties() {
		return properties;
	}

	public void setProperties(ArrayList<int[]> properties) {
		this.properties = properties;
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	
}