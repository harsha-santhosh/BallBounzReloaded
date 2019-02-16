package com.inoek.apps.games.ballbounzreloaded.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.inoek.apps.games.ballbounzreloaded.BallBounzAndroid;
import com.inoek.apps.games.ballbounzreloaded.Save;
import com.inoek.apps.games.ballbounzreloaded.data.AchievementsTracker;
import com.inoek.apps.games.ballbounzreloaded.data.ModeClass;
import com.inoek.apps.games.ballbounzreloaded.data.PlayedClass;
import com.inoek.apps.games.ballbounzreloaded.data.levelDecider;

public class MessageScreen implements Screen {

	BallBounzAndroid game;
	Stage stage;
	Label description;
	Table parentTable;
	String desc;
	TextButton back, next;
	Image ima;
	ScrollPane pane;
	ModeClass mc;
	levelDecider ld;
	PlayedClass plc;
	AchievementsTracker atracker;

	public MessageScreen(BallBounzAndroid game) {
		this.game = game;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		stage = new Stage();
		mc = new ModeClass();
		mc = Save.loadMode();
		ld = new levelDecider();
		plc = new PlayedClass();
		atracker = new AchievementsTracker();
		if (mc.isOnlineFlag()) {
			ld = Save.loadLevelDecider();
			plc = Save.loadPlayerData();
			atracker = Save.loadAchievemntsData();
			desc = "\n\nYou need to Sign in to Google Play to Play Live\n"
					+ "Press Next to Continue.............\n\n\n";
		} else {
			ld = Save.loadLevelDeciderOffline();
			plc = Save.loadPlayerDataOffline();
			atracker = Save.loadAchievemntsDataOffline();
			desc = "Press Back to Sign-in to " + "Game Play & Play Live!!\n"
					+ "Through game center your achievements would"
					+ "sync up with game avoiding the loss of your"
					+ "game progress information during phone crash or"
					+ "any other system failures.\n" + "Or\n"
					+ "Press Next to Continue without Google Play....\n";
		}
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		Gdx.input.setInputProcessor(stage);
		stage.getBatch().begin();
		stage.getBatch().draw(game.skin.getRegion("UIBackground"), 0, 0,
				game.width, game.height);
		stage.getBatch().end();
		stage.draw();
		Gdx.input.setInputProcessor(stage);
		parentTable = new Table(game.skin);
		parentTable.setBackground("uibackground");
		parentTable.setBounds(0, 0, game.width, game.height);
		ima = new Image(game.skin.getRegion("message"));
		parentTable.add(ima)
				.size(0.3334f * game.width, 0.126806f * game.height).row();
		parentTable.row();
		description = new Label(desc, game.skin);
		description.setBounds(0, 0.3f * game.height, 0.8334f * game.width,
				0.3f * game.height);
		description.setWrap(true);
		description.setAlignment(Align.topLeft);
		pane = new ScrollPane(description);
		parentTable.add(pane).size(0.8334f * game.width, 0.3f * game.height)
				.top().row();
		next = new TextButton("", game.skin, "next");
		next.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				Gdx.input.vibrate(75);
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (!mc.isOnlineFlag()) {
					if (!plc.isPlayedFlag()) {
						atracker.setAchievementNumber(0);
						Save.saveAchievementsTrackOffline(atracker);
					}
				}
				stage.addAction(Actions.sequence(
						Actions.moveBy(-stage.getWidth(), 0, .5f),
						Actions.run(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								if (mc.isOnlineFlag()) {
									game.adsController.loginGPGS();
									game.setScreen(game.ls);
								} else {
									if (ld.getCompletedLevels() == 1)
										game.setScreen(game.first);
									else
										game.setScreen(game.startscreen);
								}
							}
						})));
			}

		});
		parentTable.add(next)
				.size(0.5729167f * game.width, 0.126806f * game.height).row();
		back = new TextButton("", game.skin, "bac");
		back.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				Gdx.input.vibrate(75);
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				stage.addAction(Actions.sequence(
						Actions.moveBy(stage.getWidth(), 0, .5f),
						Actions.run(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								Gdx.input.setInputProcessor(null);
								game.setScreen(game.mc);
							}
						})));

			}

		});
		parentTable.add(back)
				.size(0.5729167f * game.width, 0.126806f * game.height).row();
		stage.addActor(parentTable);
		stage.addAction(Actions.sequence(Actions.moveTo(-stage.getWidth(), 0),
				Actions.moveTo(0, 0, .5f)));
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		Gdx.input.setInputProcessor(stage);
		stage.getBatch().begin();
		stage.getBatch().draw(game.skin.getRegion("UIBackground"), 0, 0,
				game.width, game.height);
		stage.getBatch().end();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
	}

}
