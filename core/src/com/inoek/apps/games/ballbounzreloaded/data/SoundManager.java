package com.inoek.apps.games.ballbounzreloaded.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

	private Sound hit,move;
	private Music GameMusic;
	private Music cheers;

	public void load() {
		hit = Gdx.audio.newSound(Gdx.files.internal("hit.ogg"));
		move = Gdx.audio.newSound(Gdx.files.internal("move.ogg"));
		GameMusic = Gdx.audio.newMusic(Gdx.files.internal("gamemusic.ogg"));
		GameMusic.setVolume(0.5f);
		cheers = Gdx.audio.newMusic(Gdx.files.internal("cheers.ogg"));
		cheers.setVolume(1);
	}

	public void dispose() {
		hit.dispose();
		move.dispose();
		GameMusic.dispose();
		cheers.dispose();
	}

	public void playHit() {

		hit.play();

	}

	public void playMove() {

		move.play();

	}
	
	public void playGameMusic() {

		if (!GameMusic.isPlaying()) {
			GameMusic.play();
		}

	}

	public void playCheers() {

		if (!cheers.isPlaying()) {
			cheers.play();
		}

	}

	public void stopGameMusic() {
		
			GameMusic.stop();
	}

	public void stopCheers() {
		
			cheers.stop();
		
	}

}
