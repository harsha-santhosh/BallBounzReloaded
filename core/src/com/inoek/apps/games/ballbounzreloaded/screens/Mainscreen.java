package com.inoek.apps.games.ballbounzreloaded.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.inoek.apps.games.ballbounzreloaded.BallBounzAndroid;

public class Mainscreen implements Screen{

	BallBounzAndroid game;
	Stage stage;
	Image image;
	
	public Mainscreen(BallBounzAndroid game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		stage = new Stage();
		Table table = new Table(game.skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.input.setInputProcessor(stage);
		image = new Image(game.skin.getRegion("mylogo3"));
		table.add(image).center();
		stage.addActor(table);
		stage.addAction(Actions.sequence(Actions.delay(1), Actions.fadeOut(1),
				Actions.run(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Gdx.input.setInputProcessor(null);
						game.setScreen(game.ss);
					}
				})));
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
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
