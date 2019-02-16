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
import com.inoek.apps.games.ballbounzreloaded.data.levelDecider;

public class statsScreen implements Screen{

	BallBounzAndroid game;
	Stage stage;
	Table table,childTable;
	TextButton achievements,leaderboard,levels,button;
	levelDecider ld;

	public statsScreen(	BallBounzAndroid game)
	{
		this.game=game;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		stage = new Stage();
		ld = new levelDecider();
		ld = Save.loadLevelDecider();
		table = new Table(game.skin);
		table.setBackground("uibackground");
		table.setBounds(0, 0, game.width, game.height);
		levels = new TextButton("", game.skin, "highscore");
		levels.addListener(new ClickListener() {
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
						Actions.moveBy(0, stage.getHeight(), .5f),
						Actions.run(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								Gdx.input.setInputProcessor(null);
								game.setScreen(game.hs);
							}
						})));

			}
		});
		table.add(levels).size(0.5729167f * game.width,0.146806f * game.height).row();
		childTable = new Table(game.skin);
		childTable.setBounds(0, 0, game.width,
				game.height / 4);
		achievements = new TextButton("", game.skin, "achievements");
		achievements.addListener(new ClickListener() {
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
				game.adsController.getAchievementsGPGS();
			}
		});
		childTable.add(achievements).size(0.2f*game.height, 0.16f*game.height).left();
		
		leaderboard = new TextButton("", game.skin, "leaderboard");
		leaderboard.addListener(new ClickListener() {
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
				game.adsController.getLeaderboardGPGS();			}
		});
		childTable.add(leaderboard).size(0.2f*game.height, 0.16f*game.height).right();
		table.add(childTable).row();
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
		table.add(button).size(0.5729167f * game.width,0.146806f * game.height).row();
		table.setFillParent(true);
		stage.addActor(table);
		stage.addAction(Actions.sequence(Actions.moveTo(0, stage.getHeight()),
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
