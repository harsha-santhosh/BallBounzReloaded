package com.inoek.apps.games.ballbounzreloaded.data;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

public class PlayedClass implements Serializable {

	boolean playedFlag;
	String mEmail, mName;
	
	public PlayedClass()
	{
		playedFlag = false;
		mEmail="";
		mName="";
	}
	

	public boolean isPlayedFlag() {
		return playedFlag;
	}

	public String getmEmail() {
		return mEmail;
	}

	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public void setPlayedFlag(boolean playedFlag) {
		this.playedFlag = playedFlag;
	}
	
	@Override
	public void write(Json json) {
		// TODO Auto-generated method stub
		json.writeValue("playedFlag", playedFlag);
		json.writeValue("mEmail",mEmail);
		json.writeValue("mName",mName);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		// TODO Auto-generated method stub
		playedFlag = json.readValue("playedFlag",boolean.class, jsonData);
		mEmail = json.readValue("mEmail",String.class,jsonData);
		mName = json.readValue("mName",String.class,jsonData);
	}

}
