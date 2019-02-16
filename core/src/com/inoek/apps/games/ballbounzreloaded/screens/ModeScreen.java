package com.inoek.apps.games.ballbounzreloaded.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.inoek.apps.games.ballbounzreloaded.BallBounzAndroid;
import com.inoek.apps.games.ballbounzreloaded.Save;
import com.inoek.apps.games.ballbounzreloaded.data.ModeClass;

public class ModeScreen implements Screen {

	BallBounzAndroid game;
	Stage stage;
	TextButton playOnline, playOffline, ExitButton,ok,no;
	Table parentTable;
	Image ima;
	Dialog dialog;
	ModeClass mc;

	public ModeScreen(BallBounzAndroid game) {
		this.game = game;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		stage = new Stage();
		mc = new ModeClass();
		mc = Save.loadMode();
		Gdx.input.setInputProcessor(stage);
		parentTable = new Table(game.skin);
		parentTable.setBackground("uibackground");
		parentTable.setBounds(0, 0, game.width, game.height);
		ima = new Image(game.skin.getRegion("selectmode"));
		parentTable.add(ima).size(0.8334f * game.width, 0.14f * game.height)
				.row();
		parentTable.row();
		ok = new TextButton("yes", game.skin);
		ok.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				Gdx.input.vibrate(75);
				if (mc.isOnlineFlag())
					Save.delete1();
				else
					Save.delete1Offline();
				Gdx.app.exit();
			}
		});
		no = new TextButton("no", game.skin);
		no.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				dialog.remove();
				Gdx.input.vibrate(75);
				playOnline.setVisible(true);
				playOffline.setVisible(true);
				ExitButton.setVisible(true);
			}
		});
		dialog = new Dialog("", game.skin, "dialog") {
			{
				text("Are you sure to Quit ?");
				getButtonTable().add(ok)
						.size(0.2f * game.height, 0.16f * game.height).left();
				getButtonTable().add(no)
						.size(0.2f * game.height, 0.16f * game.height).right();
			}
		};
		playOnline = new TextButton("", game.skin, "online");
		playOnline.addListener(new ClickListener() {
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
				mc.setOnlineFlag(true);
				Save.saveMode(mc);
				// go to message screen
				game.setScreen(game.msgsc);
			}
		});
		parentTable.add(playOnline)
				.size(0.5729167f * game.width, 0.2f * game.height).row();
		parentTable.row();
		playOffline = new TextButton("", game.skin, "offline");
		playOffline.addListener(new ClickListener() {
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
				mc.setOnlineFlag(false);
				Save.saveMode(mc);
				// go to message screen
				game.setScreen(game.msgsc);
			}
		});
		parentTable.add(playOffline)
				.size(0.5729167f * game.width, 0.2f * game.height).row();
		ExitButton = new TextButton("", game.skin, "exit");
		ExitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				Gdx.input.vibrate(75);
				playOnline.setVisible(false);
				playOffline.setVisible(false);
				ExitButton.setVisible(false);
				game.adsController.showInterstitialAd(new Runnable() {
					@Override
					public void run() {

					}
				});
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dialog.show(stage);
			}
		});
		parentTable.add(ExitButton)
				.size(0.5729167f * game.width, 0.126806f * game.height).row();
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
