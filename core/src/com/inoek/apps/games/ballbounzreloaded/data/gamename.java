package com.inoek.apps.games.ballbounzreloaded.data;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

public class gamename implements Serializable {

	private String PlayerName;
	
	public gamename() {
		PlayerName = "Player 1";
	}

	public String getPlayerName() {
		return PlayerName;
	}

	public void setPlayerName(String playerName) {
		PlayerName = playerName;
	}
	
	@Override
	public void write(Json json) {
		// TODO Auto-generated method stub
		json.writeValue("PlayerName", PlayerName);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		// TODO Auto-generated method stub
		PlayerName = json.readValue("PlayerName", String.class, jsonData);
	}

}
