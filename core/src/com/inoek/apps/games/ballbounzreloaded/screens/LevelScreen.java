package com.inoek.apps.games.ballbounzreloaded.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.inoek.apps.games.ballbounzreloaded.BallBounzAndroid;
import com.inoek.apps.games.ballbounzreloaded.Save;
import com.inoek.apps.games.ballbounzreloaded.data.ModeClass;
import com.inoek.apps.games.ballbounzreloaded.data.levelDecider;

public class LevelScreen implements Screen{
	BallBounzAndroid game;
	Stage stage;
	Label yourein, level, number;
	Table parentTable, childTable;
	String levelsCount;
	int levelsCompleted;
	levelDecider ld;
	ModeClass mc;
	public LevelScreen(BallBounzAndroid game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		stage = new Stage();
		mc = new ModeClass();
		mc = Save.loadMode();
		ld = new levelDecider();
		if(mc.isOnlineFlag()) {
			ld = Save.loadLevelDecider();
		}
		else
		{
			ld = Save.loadLevelDeciderOffline();
		}
		levelsCompleted = ld.getCompletedLevels();
		levelsCount = String.valueOf(levelsCompleted);
		parentTable = new Table(game.skin);
		parentTable.setBackground("uibackground");
		parentTable.setBounds(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		yourein = new Label("YOU'RE IN", game.skin);
		yourein.scaleBy((game.height / game.width) + 1);
		parentTable.add(yourein).padBottom(20).center().row();
		parentTable.row();
		childTable = new Table(game.skin);
		childTable.setBounds(0, 0, Gdx.graphics.getWidth() / 4,
				Gdx.graphics.getHeight() / 4);
		level = new Label("LEVEL ", game.skin);
		childTable.add(level).left();
		number = new Label(levelsCount, game.skin);
		childTable.add(number).right();
		parentTable.add(childTable).center().row();
		parentTable.row();
		parentTable.setFillParent(true);
		stage.addActor(parentTable);
		stage.addAction(Actions.sequence(Actions.delay(1), Actions.fadeOut(1),
				Actions.run(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Gdx.input.setInputProcessor(null);
						game.setScreen(game.second);
					}
				})));
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stubs
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
