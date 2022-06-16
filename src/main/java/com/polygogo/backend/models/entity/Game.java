package com.polygogo.backend.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

@Entity
@Table(name="games")
public class Game implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private boolean enabled;
	
	@Nullable
	private int turn;
	
	@OneToMany(mappedBy="game_id")
	private Set<PlayerDetails> players_details;
	
	public Game() {
	}
	
	public Game(long id, boolean enabled, Set<PlayerDetails> players_details) {
		super();
		this.id = id;
		this.enabled = enabled;
		this.players_details = players_details;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<PlayerDetails> getPlayers_details() {
		return players_details;
	}

	public void setPlayers_details(Set<PlayerDetails> players_details) {
		this.players_details = players_details;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}
	
}
