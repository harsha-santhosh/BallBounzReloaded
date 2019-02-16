package com.inoek.apps.games.ballbounzreloaded.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.inoek.apps.games.ballbounzreloaded.BallBounzAndroid;
import com.inoek.apps.games.ballbounzreloaded.Save;
import com.inoek.apps.games.ballbounzreloaded.data.levelDecider;

public class instructionScreen implements Screen {

	BallBounzAndroid game;
	Stage stage;
	Label description;
	Table parentTable;
	String desc;
	TextButton button,video;
	levelDecider ld;
	ScrollPane pane;
	
	public instructionScreen(BallBounzAndroid game) {
		this.game = game;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		stage = new Stage();
		ld = new levelDecider();
		ld = Save.loadLevelDecider();
		desc = "Hi there, Thank you for downloading our game Ballbounz reloaded version 1.0. \n" +
				"In this video I will be giving you some rules and tips on how to play our game.\n" +
				"\n" +
				"First we will talk about basic positions in our game. \n" +
				"There are three basic positions in our game, LEFT, MIDDLE and RIGHT defining the movements of the bat. \n" +
				"Which means to say that, you can move the bat to one of these positions. \n" +
				"In the beginning of the game, Bat will be in MIDDLE position.\n" +
				"\n" +
				"Next we will talk about the basic moves in our game.\n" +
				"We have 3 basic moves in our game,\n" +
				"TILT LEFT\n" +
				"KEEP STRAIGHT and \n" +
				"TILT RIGHT defining the movements of the bat.\n" +
				"TILT LEFT - moves the bat to a position left of its current position\n" +
				"KEEP STRAIGHT - Fixes the bat at a particular position\n" +
				"TILT RIGHT - moves the bat to a position right of its current position\n" +
				"\n" +
				"After every 'Tilt Left' and \n" +
				"'Tilt Right' moves the bat should be brought back to 'Keep Straight' position. \n" +
				"'Keep Straight' is considered as neutral position in the game. \n" +
				"In order to 'Tilt Left' or 'Tilt Right' the bat has to be in 'Keep Straight' position.\n" +
				"\n" +
				"Here is some information on different Moves and appropriate tilts:\n" +
				"1.MIDDLE-LEFT = 'Tilt Left'\n" +
				"2.MIDDLE-RIGHT='Tilt Right'\n" +
				"3.LEFT-MIDDLE='Keep Straight'->'Tilt Right'\n" +
				"4.LEFT-RIGHT= 'Keep Straight'->' Tilt Left' or \n'Keep Straight'->'Tilt Right'->'Keep Straight'->'Tilt Right'\n" +
				"5.RIGHT-MIDDLE='Keep Straight'->'Tilt Left'\n" +
				"6.RIGHT-LEFT= 'Keep Straight'-> 'Tilt Right' or \n'Keep Straight'->'Tilt Left'->'Keep Straight'->'Tilt Left'";
		parentTable = new Table(game.skin);
		parentTable.setBackground("uibackground");
		parentTable.setBounds(0, 0, game.width, game.height);
		description = new Label(desc, game.skin);
		description.setBounds(0, 0, game.width, game.height);
		description.setWrap(true);
		description.setAlignment(Align.topLeft);
		pane = new ScrollPane(description);
		parentTable.add(pane).size(game.width, game.height/2).top().row();
		video = new TextButton("", game.skin, "video");
		video.addListener(new ClickListener() {
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
						game.adsController.showInstructions();
			}

		});
		parentTable.add(video).size(0.5729167f * game.width,0.146806f * game.height).row();
		button = new TextButton("", game.skin, "backbutton");
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
						Actions.moveBy(stage.getWidth(), 0, .5f),
						Actions.run(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								Gdx.input.setInputProcessor(null);
								if (ld.getCompletedLevels() == 1)
									game.setScreen(game.first);
								else
									game.setScreen(game.startscreen);
							}
						})));

			}

		});
		parentTable.add(button).size(0.5729167f * game.width,0.146806f * game.height).row();
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
