package com.inoek.apps.games.ballbounzreloaded.data;

public class Logic {
	private int completedLevels, constant, n;
	private long _target, _score, _prevScore;
	private long _timerCount, _prevTimerCount;

	public Logic() {
		completedLevels = 1;
		constant = 2;
		_timerCount = 13;
		_target = 20;
		_score = 0;
		_prevScore = 0;
		_prevTimerCount = 0;
		n = 13;
	}

	public void getValues(int CompletedLevels) {
		if (CompletedLevels == 0) {
			return;
		} else {
			for (int i = 1; i <= CompletedLevels; i++) {
				completedLevels = completedLevels + 1;
				long prevTarget = _target;
				constant++;
				_prevTimerCount = _timerCount;
				_timerCount = n * completedLevels;
				_target = (_timerCount * constant) + prevTarget;
				_prevScore = prevTarget;
				_score = prevTarget;
			}
		}
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

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
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

	public void set_timerCount(long _timerCount) {
		this._timerCount = _timerCount;
	}

	public long get_prevTimerCount() {
		return _prevTimerCount;
	}

	public void set_prevTimerCount(long _prevTimerCount) {
		this._prevTimerCount = _prevTimerCount;
	}
}
