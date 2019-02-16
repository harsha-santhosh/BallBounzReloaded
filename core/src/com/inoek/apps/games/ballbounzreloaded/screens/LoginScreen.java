package com.inoek.apps.games.ballbounzreloaded.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.inoek.apps.games.ballbounzreloaded.BallBounzAndroid;
import com.inoek.apps.games.ballbounzreloaded.Save;
import com.inoek.apps.games.ballbounzreloaded.data.AchievementsTracker;
import com.inoek.apps.games.ballbounzreloaded.data.PlayedClass;
import com.inoek.apps.games.ballbounzreloaded.data.levelDecider;

public class LoginScreen implements Screen {

	BallBounzAndroid game;
	Stage stage;
	TextButton NextButton;
	Table parentTable;
	levelDecider ld;
	Image ima;
	PlayedClass plc;
	AchievementsTracker atracker;

	public LoginScreen(BallBounzAndroid game) {
		this.game = game;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		stage = new Stage();
		ld = new levelDecider();
		ld = Save.loadLevelDecider();
		plc = new PlayedClass();
		plc = Save.loadPlayerData();
		atracker = new AchievementsTracker();
		atracker = Save.loadAchievemntsData();
		Gdx.input.setInputProcessor(stage);
		parentTable = new Table(game.skin);
		parentTable.setBackground("uibackground");
		parentTable.setBounds(0, 0, game.width, game.height);
		ima = new Image(game.skin.getRegion("Signin"));
		parentTable.add(ima).size(0.8334f*game.width,0.28409f*game.height).row();
		parentTable.row();
		NextButton = new TextButton("", game.skin, "next");
		NextButton.addListener(new ClickListener() {
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
				Gdx.input.setInputProcessor(null);
				if (game.adsController.getSignedInGPGS()) {
				    if(game.adsController.areAchievementsLoaded()) {
				    	String inEmail = game.adsController.getEmailId();
				    	String inName = game.adsController.getName();
				    	PlayedClass plc = Save.loadPlayerData();
				    	String signedEmail = plc.getmEmail();
				    	String signedName = plc.getmName();
                        if (!plc.isPlayedFlag() || !(inEmail.equals(signedEmail) && inName.equals(signedName))) {
                            game.presentLevel = game.adsController.loadAchievementsDecideLevel();
                            ld.reset(game.presentLevel);
                            Save.SaveLevelDecider(ld);
                            atracker.setAchievementNumber(game.presentLevel);
                            Save.saveAchievementsTrack(atracker);
                            plc.setmEmail(inEmail);
                            plc.setmName(inName);
                            Save.SavePlayedClass(plc);
                            game.adsController.showNameEmailId(inEmail,inName);
                        }
                        stage.addAction(Actions.sequence(
                                Actions.moveBy(-stage.getWidth(), 0, .5f),
                                Actions.run(new Runnable() {
                                    @Override
                                    public void run() {
                                        // TODO Auto-generated method stub
                                        if (ld.getCompletedLevels() == 1)
                                            game.setScreen(game.first);
                                        else
                                            game.setScreen(game.startscreen);
                                    }
                                })));
                    }
				} else {
					game.adsController.loginGPGS();
				}
			}
		});
		parentTable.add(NextButton)
				.size(0.5729167f * game.width,0.146806f * game.height).row();
		parentTable.setFillParent(true);
		stage.addActor(parentTable);
		stage.addAction(Actions.sequence(Actions.moveTo(stage.getWidth(), 0),
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
