package com.inoek.apps.games.ballbounzreloaded.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.inoek.apps.games.ballbounzreloaded.BallBounzAndroid;
import com.inoek.apps.games.ballbounzreloaded.BallBounzAndroid.State;
import com.inoek.apps.games.ballbounzreloaded.Save;
import com.inoek.apps.games.ballbounzreloaded.data.GameData;
import com.inoek.apps.games.ballbounzreloaded.data.ModeClass;
import com.inoek.apps.games.ballbounzreloaded.data.gamename;
import com.inoek.apps.games.ballbounzreloaded.data.levelDecider;

public class pausescreen implements Screen{

	BallBounzAndroid game;
	gamename gn;
	GameData gd;
	Stage stage;
	Table table;
	levelDecider ld;
	TextButton button, button1, button2, button3, yes,no;
	Dialog dialog;
	ModeClass mc;
	public pausescreen(BallBounzAndroid game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		mc = new ModeClass();
		mc = Save.loadMode();
		game.getSoundManager().stopGameMusic();
		stage = new Stage();
		ld = new levelDecider();
		if(mc.isOnlineFlag())
		{
			gd = Save.load();
			gn = Save.loadname();
			ld = Save.loadLevelDecider();
		}
		else
		{
			gd = Save.loadOffline();
			gn = Save.loadnameOffline();
			ld = Save.loadLevelDeciderOffline();
		}
		yes  = new TextButton("yes",game.skin);
		yes.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				Gdx.input.vibrate(75);
				if(mc.isOnlineFlag()) {
					Save.save(gd);
					Save.savename(gn);
					Save.delete1();
				}
				else
				{
					Save.saveOffline(gd);
					Save.savenameOffline(gn);
					Save.delete1Offline();
				}
				Gdx.app.exit();
			}
		});
		no = new TextButton("no",game.skin);
		no.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				dialog.remove();
				button.setVisible(true);
				button2.setVisible(true);
				button3.setVisible(true);
				button1.setVisible(true);
				Gdx.input.vibrate(75);
			}
		});
		dialog = new Dialog("", game.skin, "dialog"){
			{
			text("Are you sure to Quit ?");
			getButtonTable().add(yes).size(0.2f*game.height, 0.16f*game.height).left();
			getButtonTable().add(no).size(0.2f*game.height, 0.16f*game.height).right();
			}
		};
		table = new Table(game.skin);
		table.setBackground("uibackground");
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		button = new TextButton("", game.skin, "resume");
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
								game.st = State.run;
								game.setScreen(game.second);
							}
						})));

			}
		});
		table.add(button).size(0.4f*game.height, 0.382102f*game.height).padBottom(20).center();
		table.row();
		Table table1 = new Table(game.skin);
		table1.setBounds(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight() / 4);
		button2 = new TextButton("", game.skin, "mainmenu");
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
								if(ld.getCompletedLevels()==1)
									  game.setScreen(game.first);
									else
									  game.setScreen(game.startscreen);
							}
						})));

			}
		});
		table1.add(button2).size(0.2f*game.height, 0.16f*game.height).left();
		button3 = new TextButton("", game.skin, "restart");
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
								Save.delete1();
								Gdx.input.setInputProcessor(null);
								game.st = State.run;
								game.setScreen(game.levelscreen);

							}
						})));

			}
		});
		table1.add(button3).size(0.2f*game.height, 0.16f*game.height).center();
		button1 = new TextButton("Exit", game.skin, "runaway");
		button1.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				Gdx.input.vibrate(75);
				button.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(false);
				button1.setVisible(false);
				if (game.adsController.isWifiConnected()) {
				    game.adsController.showInterstitialAd(new Runnable() {
				        @Override
				        public void run() {
				               
				        }
				    });
				} else {
				   
				}
				dialog.show(stage);
			}
		});
		table1.add(button1).size(0.2f*game.height, 0.16f*game.height).right();
		table.add(table1).padTop(100).row();
		table.row();
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
