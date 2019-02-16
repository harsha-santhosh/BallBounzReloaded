package com.inoek.apps.games.ballbounzreloaded.data;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;
import com.inoek.apps.games.ballbounzreloaded.Save;

public class levelDecider implements Serializable{
	private int completedLevels, constant, n;
	private long  _target, _score, _prevScore;
	private long _timerCount, _prevTimerCount;
	private Logic l;
	
	public levelDecider()
	{
		l = new Logic();
		completedLevels = l.getCompletedLevels();
		constant = l.getConstant();
		_timerCount = l.get_timerCount();
		_target = l.get_target();
		_score = l.get_score();
		_prevScore=l.get_prevScore();
		_prevTimerCount = l.get_prevTimerCount();
		n=l.getN();
	}
	
	public void reset(int achnum)
	{
		l = new Logic();
		l.getValues(achnum);
		completedLevels = l.getCompletedLevels();
		constant = l.getConstant();
		_timerCount = l.get_timerCount();
		_target = l.get_target();
		_score = l.get_score();
		_prevScore=l.get_prevScore();
		n=l.getN();
		_prevTimerCount = l.get_prevTimerCount();
		Save.SaveLevelDecider(this);
	}
	public void resetOffline(int achnum)
	{
		l = new Logic();
		l.getValues(achnum);
		completedLevels = l.getCompletedLevels();
		constant = l.getConstant();
		_timerCount = l.get_timerCount();
		_target = l.get_target();
		_score = l.get_score();
		_prevScore=l.get_prevScore();
		n=l.getN();
		_prevTimerCount = l.get_prevTimerCount();
		Save.SaveLevelDeciderOffline(this);
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getCompletedLevels() {
		return completedLevels;
	}

	public void setCompletedLevels(int completedLevels) {
		this.completedLevels = completedLevels;
	}

	public int getConstant() {
		return constant;
	}

	public void setConstant(int constant) {
		this.constant = constant;
	}

	public long get_target() {
		return _target;
	}

	public void set_target(long _target) {
		this._target = _target;
	}

	public long get_score() {
		return _score;
	}

	public void set_score(long _score) {
		this._score = _score;
	}

	public long get_prevScore() {
		return _prevScore;
	}

	public void set_prevScore(long _prevScore) {
		this._prevScore = _prevScore;
	}

	public long get_timerCount() {
		return _timerCount;
	}

	public long get_prevTimerCount() {
		return _prevTimerCount;
	}

	public void set_prevTimerCount(long _prevTimerCount) {
		this._prevTimerCount = _prevTimerCount;
	}

	public void set_timerCount(long _timerCount) {
		this._timerCount = _timerCount;
	}
	
	
	@Override
	public void write(Json json) {
		// TODO Auto-generated method stub
		json.writeValue("completedLevels", completedLevels);
		json.writeValue("constant", constant);
		json.writeValue("_timerCount", _timerCount);
		json.writeValue("_target", _target);
		json.writeValue("_score", _score);
		json.writeValue("_prevScore",_prevScore);
		json.writeValue("n",n);
		json.writeValue("_prevTimerCount",_prevTimerCount);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		// TODO Auto-generated method stub
		completedLevels = json.readValue("completedLevels",int.class, jsonData);
		constant = json.readValue("constant",int.class, jsonData);
		_timerCount = json.readValue("_timerCount",long.class, jsonData);
		_target = json.readValue("_target",long.class, jsonData);
		_score = json.readValue("_score",long.class, jsonData);
		_prevScore = json.readValue("_prevScore",long.class, jsonData);
		n = json.readValue("n",int.class, jsonData);
		_prevTimerCount = json.readValue("_prevTimerCount",long.class, jsonData);
	}

}
