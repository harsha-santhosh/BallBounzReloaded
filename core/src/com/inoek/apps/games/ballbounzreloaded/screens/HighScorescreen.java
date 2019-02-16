package com.inoek.apps.games.ballbounzreloaded.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.inoek.apps.games.ballbounzreloaded.BallBounzAndroid;
import com.inoek.apps.games.ballbounzreloaded.Save;
import com.inoek.apps.games.ballbounzreloaded.data.GameData;
import com.inoek.apps.games.ballbounzreloaded.data.ModeClass;
import com.inoek.apps.games.ballbounzreloaded.data.levelDecider;


public class HighScorescreen implements Screen{

	BallBounzAndroid game;
	GameData gd;
	Stage stage;
	Table table;
	TextButton button;
	Label Heading;
	Label[] highsc, name, level;
	private long[] highscores;
	private String[] names;
	private int[] levels;
	levelDecider ld;
	ModeClass mc;
	public HighScorescreen(BallBounzAndroid game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		mc = new ModeClass();
		mc = Save.loadMode();
		gd = new GameData();
		ld = new levelDecider();
		if(mc.isOnlineFlag())
		{
			gd = Save.load();
			ld = Save.loadLevelDecider();
		}
		else
		{
			gd = Save.loadOffline();
			ld = Save.loadLevelDeciderOffline();
		}
		highscores = gd.getHighscores();
		names = gd.getNames();
		levels = gd.getLevels();
		highsc = new Label[11];
		name = new Label[11];
		level = new Label[11];
		stage = new Stage();
		table = new Table(game.skin);
		table.setBackground("uibackground");
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Heading = new Label("LEVELS COMPLETED", game.skin);
		Heading.scaleBy((game.height / game.width) + 1);
		Table table1 = new Table();
		table1.setBounds(0, 0, game.width, game.height/8);
		table1.add(Heading).center().row();
		table.add(table1).center().row();
		table.row();
		name[0] = new Label("NAMES", game.skin);
		table.add(name[0]).padBottom(20);
		level[0]=new Label("LEVELS",game.skin);
		table.add(level[0]).padBottom(20).padRight(50);
		highsc[0] = new Label("SCORES", game.skin);
		table.add(highsc[0]).padBottom(20).padRight(50);
		table.row();
		for (int i = 1; i < 11; i++) {
			name[i] = new Label(names[i-1], game.skin);
			table.add(name[i]).padBottom(20);
			level[i] = new Label(String.valueOf(levels[i-1]),game.skin);
			table.add(level[i]).padBottom(20).padRight(50);
			highsc[i] = new Label(String.valueOf(highscores[i-1]), game.skin);
			table.add(highsc[i]).padBottom(20).padRight(50);
			table.row();
		}
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
		table.add(button).size(0.5729167f * game.width,0.146806f * game.height).padBottom(20)
				.row();
		table.row();
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
