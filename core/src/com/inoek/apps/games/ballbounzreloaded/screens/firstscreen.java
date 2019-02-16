package com.inoek.apps.games.ballbounzreloaded.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.inoek.apps.games.ballbounzreloaded.BallBounzAndroid;
import com.inoek.apps.games.ballbounzreloaded.Save;
import com.inoek.apps.games.ballbounzreloaded.data.ModeClass;

public class firstscreen implements Screen{

	BallBounzAndroid game;
	Stage stage;
	Table table;
	TextButton button, button1, button2, button3, ok, no, instruction,switchMode;
	Label label;
	Image image;
	Dialog dialog;
	ModeClass mc;

	public firstscreen(BallBounzAndroid game) {
		this.game = game;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		mc = new ModeClass();
		mc = Save.loadMode();
		stage = new Stage();
		table = new Table(game.skin);
		table.setBackground("uibackground");
		table.setBounds(0, 0, game.width, game.height);
		image = new Image(game.skin.getDrawable("GameTitle"));
		table.add(image).center().size(0.8f * game.width, game.height / 10)
				.row();
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
				button.setVisible(true);
				button2.setVisible(true);
				button3.setVisible(true);
				button1.setVisible(true);
				instruction.setVisible(true);
				switchMode.setVisible(true);
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
		button = new TextButton("", game.skin, "start");
		button.addListener(new ClickListener() {
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
						Actions.moveBy(-stage.getWidth(), 0, .5f),
						Actions.run(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								Gdx.input.setInputProcessor(null);
								game.setScreen(game.levelscreen);
							}
						})));

			}
		});
		table.add(button)
				.size(0.5729167f * game.width, 0.126806f * game.height).row();

		button2 = new TextButton("", game.skin, "stats");
		button2.addListener(new ClickListener() {
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
						Actions.moveBy(-stage.getWidth(), 0, .5f),
						Actions.run(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								Gdx.input.setInputProcessor(null);
								if (mc.isOnlineFlag())
									game.setScreen(game.stats);
								else
									game.setScreen(game.hs);
							}
						})));

			}
		});
		table.add(button2)
				.size(0.5729167f * game.width, 0.126806f * game.height).row();

		button3 = new TextButton("", game.skin, "setting");
		button3.addListener(new ClickListener() {
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
						Actions.moveBy(-stage.getWidth(), 0, .5f),
						Actions.run(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								Gdx.input.setInputProcessor(null);
								game.setScreen(game.set);
							}
						})));

			}
		});
		table.add(button3)
				.size(0.5729167f * game.width, 0.126806f * game.height).row();

		instruction = new TextButton("", game.skin, "instructions");
		instruction.addListener(new ClickListener() {
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
						Actions.moveBy(-stage.getWidth(), 0, .5f),
						Actions.run(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								Gdx.input.setInputProcessor(null);
								game.setScreen(game.instruScreen);
							}
						})));

			}
		});
		table.add(instruction)
				.size(0.5729167f * game.width, 0.126806f * game.height).row();

		switchMode = new TextButton("", game.skin, "switchmode");
		switchMode.addListener(new ClickListener() {
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
						Actions.moveBy(-stage.getWidth(), 0, .5f),
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
		table.add(switchMode)
				.size(0.5729167f * game.width, 0.126806f * game.height).row();

		button1 = new TextButton("", game.skin, "exit");
		button1.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				Gdx.input.vibrate(75);
				button.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(false);
				button1.setVisible(false);
				instruction.setVisible(false);
				switchMode.setVisible(false);
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
		table.add(button1)
				.size(0.5729167f * game.width, 0.126806f * game.height).row();
		table.setFillParent(true);
		stage.addActor(table);
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
