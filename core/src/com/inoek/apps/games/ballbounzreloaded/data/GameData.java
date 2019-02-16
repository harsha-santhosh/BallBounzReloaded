package com.inoek.apps.games.ballbounzreloaded.data;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

public class GameData implements Serializable{

	private long[] highscores;
	private int[] levels;
	
	public int[] getLevels() {
		return levels;
	}

	public void setLevels(int[] levels) {
		this.levels = levels;
	}

	public long getTempscore() {
		return tempscore;
	}

	public void setTempscore(long tempscore) {
		this.tempscore = tempscore;
	}

	public long[] getHighscores() {
		return highscores;
	}

	public String[] getNames() {
		return names;
	}

	private String[] names;
	private long tempscore;
	private String tempname;
	private int templevel;

	public GameData() {
		highscores = new long[10];
		names = new String[10];
		levels = new int[10];
		tempscore = 0;
		tempname = "Player 1";
	}

	public void init() {
		for (int i = 0; i < 10; i++) {
			highscores[i] = 0;
			names[i] = "----";
			levels[i]=0;
		}
	}

	public void addHighScore(long score,int level) {
		if (score > highscores[9]) {
			highscores[9] = score;
			names[9] = tempname;
			levels[9]=level;
			sorthighscores();
		}
	}

	public boolean changeName(long score, String name){
		boolean success=false;
		tempname = name;
		for(int i=0;i<9;i++)
		{
			if(highscores[i]==score && names[i]!=tempname && (tempname!="" || tempname !=" "))
			{
				names[i]=tempname;
				success=true;
			}
		}
		return success;
	}
	
	public long getHighScore() {
		return highscores[0];
	}

	private void sorthighscores() {
		// TODO Auto-generated method stub
		for (int i = 1; i < 9; i++) {
			for (int j = 9; j > 0; j--) {
				if (highscores[j] > highscores[j - 1]) {
					tempscore = highscores[j];
					tempname = names[j];
					templevel = levels[j];
					highscores[j] = highscores[j - 1];
					names[j] = names[j - 1];
					levels[j]=levels[j-1];
					highscores[j - 1] = tempscore;
					names[j - 1] = tempname;
					levels[j-1]=templevel;
				}
			}
		}
	}
	@Override
	public void write(Json json) {
		// TODO Auto-generated method stub
		json.writeValue("highscores", highscores);
		json.writeValue("names", names);
		json.writeValue("levels",levels);
		json.writeValue("tempscore", tempscore);
		json.writeValue("tempname", tempname);
		json.writeValue("templevel",templevel);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		// TODO Auto-generated method stub
		highscores = json.readValue("highscores", long[].class, jsonData);
		names = json.readValue("names", String[].class, jsonData);
		levels = json.readValue("levels",int[].class ,jsonData);
		tempscore = json.readValue("tempscore", long.class, jsonData);
		tempname = json.readValue("tempname", String.class, jsonData);
		templevel = json.readValue("templevel", int.class,jsonData);
	}

}
