package com.inoek.apps.games.ballbounzreloaded.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.inoek.apps.games.ballbounzreloaded.BallBounzAndroid;
import com.inoek.apps.games.ballbounzreloaded.BallBounzAndroid.State;
import com.inoek.apps.games.ballbounzreloaded.Save;
import com.inoek.apps.games.ballbounzreloaded.data.GameData;
import com.inoek.apps.games.ballbounzreloaded.data.ModeClass;
import com.inoek.apps.games.ballbounzreloaded.data.PlayedClass;
import com.inoek.apps.games.ballbounzreloaded.data.gamename;
import com.inoek.apps.games.ballbounzreloaded.data.levelDecider;

public class LevelCompleteScreen implements Screen {

	BallBounzAndroid game;
	GameData gd;
	gamename gn;
	Stage stage;
	long score;
	Label congratulations, level, completedLevels, complete, scorelabel,instruction;
	TextButton continueButton, playLater,ok, share, confirm, yes,no;
	Table parentTable, childTable1, childTable2, childTable3;
	String levelsCount;
	int levelsCompleted;
	levelDecider ld;
	PlayedClass plc;
	TextField textfield;
	int cou;
	Dialog dialog1,dialog2;
	ModeClass mc;
	public LevelCompleteScreen(BallBounzAndroid game) {
		this.game = game;
	}
	
	public void showSave()
	{
		dialog1 = new Dialog("", game.skin, "dialog"){
			{
					text("Your name saved succesfully");
			getButtonTable().add(confirm).size(0.2f*game.height, 0.16f*game.height);
			}
		}.show(stage);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		mc = new ModeClass();
		mc = Save.loadMode();
		if(mc.isOnlineFlag()) {
			Save.delete1();
		}
		else
		{
			Save.delete1Offline();
		}
		game.getSoundManager().playCheers();
		game.st = State.run;
		ld = new levelDecider();
		if(mc.isOnlineFlag())
		{
			ld = Save.loadLevelDecider();
		}
		else
		{
			ld = Save.loadLevelDeciderOffline();
		}
		plc = new PlayedClass();
		if(mc.isOnlineFlag()) {
			plc = Save.loadPlayerData();
			plc.setPlayedFlag(true);
			Save.SavePlayedClass(plc);
		}
		else
		{
			plc = Save.loadPlayerDataOffline();
			plc.setPlayedFlag(true);
			Save.SavePlayedClassOffline(plc);
		}
		gd = new GameData();
		if(mc.isOnlineFlag()) {
			gd = Save.load();
		}
		else
		{
			gd = Save.loadOffline();
		}
		gn = new gamename();
		if(mc.isOnlineFlag()) {
			gn = Save.loadname();
		}
		else
		{
			gn = Save.loadnameOffline();
		}
		levelsCompleted = ld.getCompletedLevels() - 1;
		stage = new Stage();
		score = ld.get_score();
		cou=1;
		levelsCount = String.valueOf(levelsCompleted);
		gd.addHighScore(score,levelsCompleted);
		if(mc.isOnlineFlag())
		{
			Save.save(gd);
		}
		else
		{
			Save.saveOffline(gd);
		}
		yes  = new TextButton("yes",game.skin);
		yes.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				Gdx.input.vibrate(75);
				Gdx.app.exit();
			}
		});
		confirm  = new TextButton("ok",game.skin);
		confirm.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				Gdx.input.vibrate(75);
				dialog1.remove();
			}
		});
		no = new TextButton("no",game.skin);
		no.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				dialog2.remove();
				Gdx.input.vibrate(75); 
			}
		});
		
		dialog2 = new Dialog("", game.skin, "dialog"){
			{
			text("Are you sure to Quit ?");
			getButtonTable().add(yes).size(0.2f*game.height, 0.16f*game.height).left();
			getButtonTable().add(no).size(0.2f*game.height, 0.16f*game.height).right();
			}
		};
		parentTable = new Table(game.skin);
		parentTable.setBackground("uibackground");
		parentTable.setBounds(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		congratulations = new Label("CONGRATULATIONS", game.skin);
		congratulations.scaleBy((game.height / game.width) + 1);
		parentTable.add(congratulations).padBottom(20).center().row();
		parentTable.row();
		childTable1 = new Table(game.skin);
		childTable1.setBounds(0, 0, Gdx.graphics.getWidth() / 4,
				Gdx.graphics.getHeight() / 4);
		level = new Label("LEVEL  ", game.skin);
		childTable1.add(level).left();
		completedLevels = new Label(levelsCount, game.skin);
		childTable1.add(completedLevels).right();
		parentTable.add(childTable1).center().row();
		String s = String.valueOf(ld.get_score()) + "/"
				+ String.valueOf(ld.get_prevScore());
		scorelabel = new Label(s, game.skin);
		scorelabel.scaleBy((game.height / game.width) + 1);
		parentTable.add(scorelabel).padBottom(20).center().row();
		complete = new Label("COMPLETE", game.skin);
		complete.scaleBy((game.height / game.width) + 1);
		parentTable.add(complete).padBottom(20).center().row();
		childTable3 = new Table(game.skin);
		childTable3.setBounds(0, 0, Gdx.graphics.getWidth() / 4,
				Gdx.graphics.getHeight() / 4);
		textfield = new TextField("", game.skin);
		textfield.setMaxLength(20);
		textfield.setMessageText("Player 1");
		textfield.setText(gn.getPlayerName());
		childTable3.add(textfield).size(game.width / 2, 50).left();
		ok = new TextButton("", game.skin, "okbutton");
		ok.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				Gdx.input.vibrate(75);
				if (textfield.getText() == "" || textfield.getText()==" ") {
					textfield.setText("Player 1");
					gd.changeName(score, textfield.getText().toString());
					if(mc.isOnlineFlag()) {
						Save.save(gd);
					}
					else
					{
						Save.saveOffline(gd);
					}
				} else {
					gn.setPlayerName(textfield.getText()
							.toString());
					gd.changeName(score, textfield.getText().toString());
					if(mc.isOnlineFlag()) {
						Save.savename(gn);
						Save.save(gd);
					}
					else
					{
						Save.savenameOffline(gn);
						Save.saveOffline(gd);
					}
				}
				cou=0;
				showSave();
			}
		});
		childTable3.add(ok).size(0.1334f*game.width, 0.091f*game.height).right();
		parentTable.add(childTable3).row();
		instruction = new Label("Press tick & add save your name", game.skin);
		parentTable.add(instruction).size(game.width / 2, game.height / 20)
				.padBottom(20).center().row();
		parentTable.row();
		childTable2 = new Table(game.skin);
		childTable2.setBounds(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight() / 4);
		continueButton = new TextButton("", game.skin, "continue");
		continueButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				Gdx.input.vibrate(75);
				game.getSoundManager().stopCheers();
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
								game.setScreen(game.startscreen);
							}
						})));
			}
		});
		childTable2.add(continueButton).size(0.2f*game.height, 0.16f*game.height).left();
		share = new TextButton("Share", game.skin, "sharebutton");
		share.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				Gdx.input.vibrate(75);
				game.getSoundManager().stopCheers();
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				game.mygamecallback.onStartActivityA();

			}
		});
		childTable2.add(share).size(0.2f*game.height, 0.16f*game.height).center();
		playLater = new TextButton("Later", game.skin, "playlater");
		playLater.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				Gdx.input.vibrate(75);
				game.getSoundManager().stopCheers();
				if (game.adsController.isWifiConnected()) {
				    game.adsController.showInterstitialAd(new Runnable() {
				        @Override
				        public void run() {
				                
				        }
				    });
				} else {
				    
				}
				dialog2.show(stage);
			}
		});
		childTable2.add(playLater).size(0.2f*game.height, 0.16f*game.height).right();
		childTable2.row();
		parentTable.add(childTable2).center().row();
		parentTable.row();
		parentTable.setFillParent(true);
		stage.addActor(parentTable);
		stage.addAction(Actions.sequence(Actions.moveTo(0, stage.getHeight()),
				Actions.moveTo(0, 0, 1)));
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
