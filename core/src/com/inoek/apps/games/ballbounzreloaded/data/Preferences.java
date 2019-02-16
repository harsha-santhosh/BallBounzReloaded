package com.inoek.apps.games.ballbounzreloaded.data;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

public class Preferences implements Serializable{

private int music;
	
	public Preferences()
	{
		music = 1;
	}

	
	public int getMusic() {
		return music;
	}


	public void setMusic(int music) {
		this.music = music;
	}

	@Override
	public void write(Json json) {
		// TODO Auto-generated method stub
		json.writeValue("music", music);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		// TODO Auto-generated method stub
		music = json.readValue("music", int.class, jsonData);
	}

}
