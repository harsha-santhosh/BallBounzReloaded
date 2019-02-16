package com.inoek.apps.games.ballbounzreloaded.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.inoek.apps.games.ballbounzreloaded.BallBounzAndroid;
import com.inoek.apps.games.ballbounzreloaded.Save;
import com.inoek.apps.games.ballbounzreloaded.data.ModeClass;
import com.inoek.apps.games.ballbounzreloaded.data.Preferences;
import com.inoek.apps.games.ballbounzreloaded.data.levelDecider;

public class settingscreen implements Screen{

	BallBounzAndroid game;
	Stage stage;
	Table table, table1, table2;
	TextButton button, button1, button3, mainmen;
	String mu, so;
	Preferences pref;
	levelDecider ld;
	ModeClass mc;

	public settingscreen(BallBounzAndroid game) {
		this.game = game;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		mc = new ModeClass();
		mc = Save.loadMode();
		pref = new Preferences();
		ld = new levelDecider();
		if(mc.isOnlineFlag())
		{
			pref = Save.loadpreferences();
			ld = Save.loadLevelDecider();
		}
		else
		{
			pref = Save.loadpreferencesOffline();
			ld = Save.loadLevelDeciderOffline();
		}
		stage = new Stage();
		table = new Table(game.skin);
		table.setBackground("uibackground");
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		mainmen = new TextButton("", game.skin, "backbutton");
		mainmen.addListener(new ClickListener() {
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
								Save.savepreferences(pref);
								Gdx.input.setInputProcessor(null);
								if(ld.getCompletedLevels()==1)
									  game.setScreen(game.first);
									else
									  game.setScreen(game.startscreen);
							}
						})));

			}
		});
		table.add(mainmen).size(0.5729167f * game.width,0.146806f * game.height).top();
		table.row();
		if(pref.getMusic()==1)
		{
			mu = "Music: ON";
		}
		else if(pref.getMusic()==0)
		{
			mu = "Music: OFF";
		}

		button = new TextButton(mu, game.skin, "musics");
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				Gdx.input.vibrate(75);
				if(pref.getMusic() == 1)
				{
					mu = "Music: OFF";
					button.setText(mu);
					pref.setMusic(0);
				}
				else if(pref.getMusic() == 0)
				{
					mu = "Music: ON";
					button.setText(mu);
					pref.setMusic(1);
				}
				if(mc.isOnlineFlag()) {
					Save.savepreferences(pref);
				}
				else
				{
					Save.savepreferencesOffline(pref);
				}
			}
		});
		table.add(button).size(0.5729167f * game.width,0.146806f * game.height).row();
		table.row();
		button3 = new TextButton("", game.skin, "feedback");
		button3.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				Gdx.input.vibrate(75);
				game.adsController.getPackageNameForFeedback();
			}
		});
		table.add(button3).size(0.5729167f * game.width,0.146806f * game.height).row();
		table.row();
		button1 = new TextButton("", game.skin, "aboutus");
		button1.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				// code for about us
				Gdx.input.vibrate(75);
				if(game.adsController.checkForFaceBook())
				{
					Gdx.net.openURI("fb://page/1485236118445860");
				}
				else
				{
					Gdx.net.openURI("https://www.facebook.com/Inoek-1485236118445860");
				}
			}
		});
		table.add(button1).size(0.5729167f * game.width,0.146806f * game.height).row();
		table.setFillParent(true);
		stage.addActor(table);
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
				Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
