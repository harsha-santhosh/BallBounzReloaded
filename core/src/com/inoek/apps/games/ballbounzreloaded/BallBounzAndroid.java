package com.inoek.apps.games.ballbounzreloaded;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.inoek.apps.games.ballbounzreloaded.data.PlayedClass;
import com.inoek.apps.games.ballbounzreloaded.data.SoundManager;
import com.inoek.apps.games.ballbounzreloaded.screens.HighScorescreen;
import com.inoek.apps.games.ballbounzreloaded.screens.LevelCompleteScreen;
import com.inoek.apps.games.ballbounzreloaded.screens.LevelScreen;
import com.inoek.apps.games.ballbounzreloaded.screens.LoginScreen;
import com.inoek.apps.games.ballbounzreloaded.screens.Mainscreen;
import com.inoek.apps.games.ballbounzreloaded.screens.MessageScreen;
import com.inoek.apps.games.ballbounzreloaded.screens.ModeScreen;
import com.inoek.apps.games.ballbounzreloaded.screens.SplashScreen;
import com.inoek.apps.games.ballbounzreloaded.screens.firstscreen;
import com.inoek.apps.games.ballbounzreloaded.screens.gameoverscreen;
import com.inoek.apps.games.ballbounzreloaded.screens.instructionScreen;
import com.inoek.apps.games.ballbounzreloaded.screens.pausescreen;
import com.inoek.apps.games.ballbounzreloaded.screens.secondscreen;
import com.inoek.apps.games.ballbounzreloaded.screens.settingscreen;
import com.inoek.apps.games.ballbounzreloaded.screens.startScreen;
import com.inoek.apps.games.ballbounzreloaded.screens.statsScreen;

public class BallBounzAndroid extends Game {
	public enum State {
		run, pause, back, gameover, levelComplete
	}
	public State st;
	public firstscreen first;
	public startScreen startscreen;
	public pausescreen ps;
	public secondscreen second;
	public gameoverscreen go;
	public LevelScreen levelscreen;
	public LevelCompleteScreen levelcompletescreen;
	public statsScreen stats;
	public instructionScreen instruScreen;
	public HighScorescreen hs;
	public SplashScreen ss;
	public LoginScreen ls;
	public settingscreen set;
	public Mainscreen ms;
	public ModeScreen mc;
	public MessageScreen msgsc;
	public int delay1, delay2, delay3;
	public TextureAtlas atlas;
	public Skin skin;
	public float v;
	public int width, height, presentLevel;
	public AdsController adsController;
	public String[] achievementsArray;
	public PlayedClass plc;
	private SoundManager smanager;
	public boolean firstTimeInstall;

	public interface MyGameCallBack {
		public void onStartActivityA();

		public void onStartActivityB();
	}

	public MyGameCallBack mygamecallback;

	public void setMygamecallback(MyGameCallBack mygamecallback) {
		this.mygamecallback = mygamecallback;
	}

	public BallBounzAndroid(AdsController adsController) {
		this.adsController = adsController;

	}

	public SoundManager getSoundManager() {
		return smanager;
	}

	@Override
	public void create() {
		achievementsArray = new String[] {"CgkIkv6WzaUcEAIQAQ","CgkIkv6WzaUcEAIQAg","CgkIkv6WzaUcEAIQAw","CgkIkv6WzaUcEAIQBA","CgkIkv6WzaUcEAIQBQ",
				"CgkIkv6WzaUcEAIQBg","CgkIkv6WzaUcEAIQBw","CgkIkv6WzaUcEAIQCA","CgkIkv6WzaUcEAIQCQ","CgkIkv6WzaUcEAIQCg",
				"CgkIkv6WzaUcEAIQCw","CgkIkv6WzaUcEAIQDA","CgkIkv6WzaUcEAIQDQ","CgkIkv6WzaUcEAIQDg","CgkIkv6WzaUcEAIQDw",
				"CgkIkv6WzaUcEAIQEA","CgkIkv6WzaUcEAIQEQ","CgkIkv6WzaUcEAIQEg","CgkIkv6WzaUcEAIQEw","CgkIkv6WzaUcEAIQFA",
				"CgkIkv6WzaUcEAIQFQ","CgkIkv6WzaUcEAIQFg","CgkIkv6WzaUcEAIQFw","CgkIkv6WzaUcEAIQGA","CgkIkv6WzaUcEAIQGQ",
				"CgkIkv6WzaUcEAIQGw","CgkIkv6WzaUcEAIQHA","CgkIkv6WzaUcEAIQHQ","CgkIkv6WzaUcEAIQHg","CgkIkv6WzaUcEAIQHw",
				"CgkIkv6WzaUcEAIQIA","CgkIkv6WzaUcEAIQIQ","CgkIkv6WzaUcEAIQIg","CgkIkv6WzaUcEAIQIw","CgkIkv6WzaUcEAIQJA",
				"CgkIkv6WzaUcEAIQJQ","CgkIkv6WzaUcEAIQJg","CgkIkv6WzaUcEAIQJw","CgkIkv6WzaUcEAIQKA","CgkIkv6WzaUcEAIQKQ",
				"CgkIkv6WzaUcEAIQKg","CgkIkv6WzaUcEAIQKw","CgkIkv6WzaUcEAIQLA","CgkIkv6WzaUcEAIQLQ","CgkIkv6WzaUcEAIQLg",
				"CgkIkv6WzaUcEAIQLw","CgkIkv6WzaUcEAIQMA","CgkIkv6WzaUcEAIQMQ","CgkIkv6WzaUcEAIQMg","CgkIkv6WzaUcEAIQMw",
				"CgkIkv6WzaUcEAIQNA","CgkIkv6WzaUcEAIQNQ","CgkIkv6WzaUcEAIQNg","CgkIkv6WzaUcEAIQNw","CgkIkv6WzaUcEAIQOA",
				"CgkIkv6WzaUcEAIQOQ","CgkIkv6WzaUcEAIQOg","CgkIkv6WzaUcEAIQOw","CgkIkv6WzaUcEAIQPA","CgkIkv6WzaUcEAIQPQ",
				"CgkIkv6WzaUcEAIQPg","CgkIkv6WzaUcEAIQPw","CgkIkv6WzaUcEAIQQA","CgkIkv6WzaUcEAIQQQ","CgkIkv6WzaUcEAIQQg",
				"CgkIkv6WzaUcEAIQQw","CgkIkv6WzaUcEAIQRA","CgkIkv6WzaUcEAIQRQ","CgkIkv6WzaUcEAIQRg","CgkIkv6WzaUcEAIQRw",
				"CgkIkv6WzaUcEAIQSA","CgkIkv6WzaUcEAIQSQ","CgkIkv6WzaUcEAIQSg","CgkIkv6WzaUcEAIQSw","CgkIkv6WzaUcEAIQTA",
				"CgkIkv6WzaUcEAIQTQ","CgkIkv6WzaUcEAIQTg","CgkIkv6WzaUcEAIQTw","CgkIkv6WzaUcEAIQUA","CgkIkv6WzaUcEAIQUQ",
				"CgkIkv6WzaUcEAIQUg","CgkIkv6WzaUcEAIQUw","CgkIkv6WzaUcEAIQVA","CgkIkv6WzaUcEAIQVQ","CgkIkv6WzaUcEAIQVg",
				"CgkIkv6WzaUcEAIQVw","CgkIkv6WzaUcEAIQWA","CgkIkv6WzaUcEAIQWQ","CgkIkv6WzaUcEAIQWg","CgkIkv6WzaUcEAIQWw",
				"CgkIkv6WzaUcEAIQXA","CgkIkv6WzaUcEAIQXQ","CgkIkv6WzaUcEAIQXg","CgkIkv6WzaUcEAIQXw","CgkIkv6WzaUcEAIQYA",
				"CgkIkv6WzaUcEAIQYQ","CgkIkv6WzaUcEAIQYg","CgkIkv6WzaUcEAIQYw","CgkIkv6WzaUcEAIQZA","CgkIkv6WzaUcEAIQZQ",
				"CgkIkv6WzaUcEAIQZg","CgkIkv6WzaUcEAIQZw","CgkIkv6WzaUcEAIQaA","CgkIkv6WzaUcEAIQaQ","CgkIkv6WzaUcEAIQag",
				"CgkIkv6WzaUcEAIQaw","CgkIkv6WzaUcEAIQbA","CgkIkv6WzaUcEAIQbQ","CgkIkv6WzaUcEAIQbg","CgkIkv6WzaUcEAIQbw",
				"CgkIkv6WzaUcEAIQcA","CgkIkv6WzaUcEAIQcQ","CgkIkv6WzaUcEAIQcg","CgkIkv6WzaUcEAIQcw","CgkIkv6WzaUcEAIQdA",
				"CgkIkv6WzaUcEAIQdQ","CgkIkv6WzaUcEAIQdg","CgkIkv6WzaUcEAIQdw","CgkIkv6WzaUcEAIQeA","CgkIkv6WzaUcEAIQeQ",
				"CgkIkv6WzaUcEAIQeg","CgkIkv6WzaUcEAIQew","CgkIkv6WzaUcEAIQfA","CgkIkv6WzaUcEAIQfQ","CgkIkv6WzaUcEAIQfg"};
		st = State.run;
		plc = new PlayedClass();
		plc = Save.loadPlayerData();
		Save.SavePlayedClass(plc);
		smanager = new SoundManager();
		smanager.load();
		first = new firstscreen(this);
		startscreen = new startScreen(this);
		ps = new pausescreen(this);
		second = new secondscreen(this);
		levelscreen = new LevelScreen(this);
		levelcompletescreen = new LevelCompleteScreen(this);
		go = new gameoverscreen(this);
		hs = new HighScorescreen(this);
		ss = new SplashScreen(this);
		set = new settingscreen(this);
		ms = new Mainscreen(this);
		mc = new ModeScreen(this);
		stats = new statsScreen(this);
		ls = new LoginScreen(this);
		msgsc = new MessageScreen(this);
		instruScreen = new instructionScreen(this);
		atlas = new TextureAtlas("cricpack.pack");
		skin = new Skin(Gdx.files.internal("uijson.json"), atlas);
		if (adsController.isWifiConnected()) {
			adsController.showBannerAd();
		}
		delay1 = 0;
		delay2 = 0;
		delay3 = 0;
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		firstTimeInstall = true;
		setScreen(ms);
	}

	@Override
	public void dispose() {
		super.dispose();
		atlas.dispose();
		skin.dispose();
		smanager.dispose();
	}

	@Override
	public void render() {
		super.render();
		if (adsController.isWifiConnected() && !adsController.isBannerShowing()) {
			adsController.showBannerAd();
		}
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
		smanager.dispose();
	}

	@Override
	public void resume() {
		super.resume();
		smanager.load();
	}
}
