package com.inoek.apps.games.ballbounzreloaded.data;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

public class ModeClass implements Serializable{

	private boolean onlineFlag;
	
	public ModeClass()
	{
		onlineFlag = false;
	}
	
	public boolean isOnlineFlag() {
		return onlineFlag;
	}

	public void setOnlineFlag(boolean onlineFlag) {
		this.onlineFlag = onlineFlag;
	}

	@Override
	public void write(Json json) {
		// TODO Auto-generated method stub
		json.writeValue("onlineFlag", onlineFlag);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		// TODO Auto-generated method stub
		onlineFlag = json.readValue("onlineFlag", boolean.class,jsonData);
	}

}
