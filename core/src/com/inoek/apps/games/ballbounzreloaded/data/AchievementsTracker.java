package com.inoek.apps.games.ballbounzreloaded.data;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

public class AchievementsTracker implements Serializable {
private int achievementNumber;
	
	public AchievementsTracker()
	{
		achievementNumber=0;
	}
	
	public void setAchievementNumber(int achievementNumber) {
		this.achievementNumber = achievementNumber;
	}

	public int getAchievementNumber() {
		return achievementNumber;
	}

	public void nextLevel()
	{
		achievementNumber++;
	}

	@Override
	public void write(Json json) {
		// TODO Auto-generated method stub
		json.writeValue("achievementNumber", achievementNumber);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		// TODO Auto-generated method stub
		achievementNumber = json.readValue("achievementNumber", int.class,jsonData);
	}

}
