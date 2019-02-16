package com.inoek.apps.games.ballbounzreloaded.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.inoek.apps.games.ballbounzreloaded.BallBounzAndroid;


public class SplashScreen implements Screen {

	BallBounzAndroid game;
	Stage stage;
	Image image;
	public SplashScreen(BallBounzAndroid game) {
		this.game = game;
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		image = new Image(game.skin.getRegion("logo"));
		image.setBounds(0, 0, game.width, game.height);
		stage.addActor(image);
		stage.addAction(Actions.sequence(Actions.delay(1),
				Actions.moveBy(-stage.getWidth(), 0, .5f),
				Actions.run(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Gdx.input.setInputProcessor(null);
						game.setScreen(game.mc);
						// show instructions only on first time install
						if(game.adsController.checkFirstTimeInstalled())
						{
							game.adsController.showInstructions();
							game.adsController.updateInstalled(false);
						}
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
