package com.inoek.apps.games.ballbounzreloaded.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.inoek.apps.games.ballbounzreloaded.BallBounzAndroid;
import com.inoek.apps.games.ballbounzreloaded.BallBounzAndroid.State;
import com.inoek.apps.games.ballbounzreloaded.Save;
import com.inoek.apps.games.ballbounzreloaded.data.AchievementsTracker;
import com.inoek.apps.games.ballbounzreloaded.data.GameData;
import com.inoek.apps.games.ballbounzreloaded.data.ModeClass;
import com.inoek.apps.games.ballbounzreloaded.data.PlayedClass;
import com.inoek.apps.games.ballbounzreloaded.data.Preferences;
import com.inoek.apps.games.ballbounzreloaded.data.gameclass;
import com.inoek.apps.games.ballbounzreloaded.data.levelDecider;

import java.util.Random;

public class secondscreen implements Screen {

	SpriteBatch batch;
	gameclass gc;
	GameData gd;
	levelDecider ld;
	Preferences pref;
	AchievementsTracker atracker;
	int losedelay;
	float w, h;
	BallBounzAndroid game;
	float positionx;
	String str, str1, timerstr;
	int gamestart, down;
	TextureRegion texture, btexture,lostText;
	TextureRegion[] frames, bframes;
	int col = 2, row = 1, col1 = 3, row1 = 1;
	Animation animation, animation1;
	BitmapFont font, fonts, timer, fontinfo, fonta;
	Sprite s;
	float stateTime, bstateTime;
	Vector2 position;
	int o, p, q, r, q1, r1, p1, index, scenic, lost, hitcount, hitdecider,
			completedLevels;
	long count, target, prevTimerCount;
	int pos, batIndex, flag, timercount;
	int x, y1, z1, x1, y, z, n, s1, s2, s11, s12, sign, signb, xscene;
	double xa, xb;
	float sizex, sizey;
	long icount;
	Random rand;
	float batsizex, batsizey, exactbatpos, bposx, bposy, bsizx, bsizy;
	int counter, batpos, inputpos, constant, num;
	float wid, hei;
	String quotes, quotes1, quotes2;
	int mapper, set, deltacount;
	boolean TimeOutFlag, scene, motionFlag;
	Stage stage;
	PlayedClass plc;
	ModeClass mc;
	long lostTimer = 0;
	boolean lostBall = false, normalPos = true;

	public secondscreen(BallBounzAndroid game) {
		this.game = game;
	}

	private void showDialog() {

		lostBall = true;
		Gdx.input.vibrate(75);
		game.st = State.gameover;
/*		dialog = new Dialog("", game.skin, "dialog") {
			{
				text("You Have Lost the Ball!");
				button("ok", "1").setSize(0.2f*game.height, 0.16f*game.height);
			}

			@Override
			protected void result(Object object) {
				int i = Integer.parseInt(object.toString());
				if (i == 1) {
					Gdx.input.vibrate(75);
					game.st = State.gameover;
				}
			}
		};
		dialog.setSize(game.width,game.height);
		dialog.show(stage);*/
	}

	public void finalModifications() {

		atracker.nextLevel();

		if(mc.isOnlineFlag()) {
			Save.saveAchievementsTrack(atracker);
			game.adsController.submitScoreGPGS(count);
			game.adsController.unlockAchievementGPGS(game.achievementsArray[atracker.getAchievementNumber() - 1]);
		}
		else
		{
			Save.saveAchievementsTrackOffline(atracker);
		}

		completedLevels = completedLevels + 1;
		long prevTarget = target;
		constant++;
		prevTimerCount = icount;
		icount = num * completedLevels;
		target = (icount * constant) + prevTarget;
		ld.set_prevScore(prevTarget);
		ld.set_score(count);
		ld.set_target(target);
		ld.set_timerCount(icount);
		ld.setCompletedLevels(completedLevels);
		ld.setConstant(constant);
		ld.setN(num);
		ld.set_prevTimerCount(prevTimerCount);
		if(mc.isOnlineFlag())
		{
			Save.SaveLevelDecider(ld);
		}
		else
		{
			Save.SaveLevelDeciderOffline(ld);
		}
	}

	public void savingfunction() {
		gc.setBsizx(bsizx);
		gc.setBsizy(bsizy);
		gc.setBposx(bposx);
		gc.setBposy(bposy);
		gc.setWid(wid);
		gc.setHei(hei);
		gc.setBatsizex(batsizex);
		gc.setBatsizey(batsizey);
		gc.setSizex(sizex);
		gc.setSizey(sizey);
		gc.setPosition(position);
		gc.setPositionx(positionx);
		gc.setGamestart(gamestart);
		gc.setDown(down);
		gc.setStateTime(stateTime);
		gc.setBstateTime(bstateTime);
		gc.setO(o);
		gc.setP(p);
		gc.setCount(count);
		gc.setR(r);
		gc.setQ1(q1);
		gc.setIndex(index);
		gc.setP1(p1);
		gc.setQ(q);
		gc.setR1(r1);
		gc.setLost(lost);
		gc.setHitcount(hitcount);
		gc.setPos(pos);
		gc.setFlag(flag);
		gc.setScenic(scenic);
		gc.setX(x);
		gc.setY1(y1);
		gc.setZ1(z1);
		gc.setX1(x1);
		gc.setY(y);
		gc.setZ(z);
		gc.setN(n);
		gc.setS1(s1);
		gc.setS2(s2);
		gc.setS11(s11);
		gc.setS12(s12);
		gc.setSign(sign);
		gc.setSignb(signb);
		gc.setCounter(counter);
		gc.setQuotes(quotes);
		gc.setMapper(mapper);
		gc.setSet(set);
		gc.setHitdecider(hitdecider);
		gc.setIcount(icount);
		gc.setTarget(target);
		gc.setConstant(constant);
		gc.setDeltacount(deltacount);
		gc.setTimeOutFlag(TimeOutFlag);
		gc.setMotionFlag(motionFlag);
		gc.setBatIndex(batIndex);
		gc.setScene(scene);
		gc.setXscene(xscene);
		gc.setNum(num);
		gc.setPrevTimerCount(prevTimerCount);
	}

	public void mappingresets() {
		switch (mapper) {
		case 0:
			x = 36;
			x1 = 1;
			y = 1;
			y1 = 36;
			z1 = 36;
			z = 1;
			deltacount = constant;// level1
			break;
		case 1:
			x = 36;
			x1 = 1;
			y = 1;
			y1 = 36;
			z1 = 36;
			z = 1;
			deltacount = constant - 2;// level2
			break;
		}
	}

	public void resetvalues() {
		// new code
		flag = 0;
		index = 0;
		o = 1;
		p = 1;
		q = 0;
		r = 1;
		q1 = 1;
		r1 = 0;
		p1 = 0;
		set = 0;
		hitdecider = 0;
		n = rand.nextInt(9);
	}

	public void decidebounds() {
		if (pos == 0) {
			xa = 0;
			xb = Gdx.graphics.getWidth() / 3 - 1;
		} else if (pos == 1) {
			xa = Gdx.graphics.getWidth() / 3;
			xb = 2 * Gdx.graphics.getWidth() / 3 - 1;
		} else if (pos == 2) {
			xa = 2 * Gdx.graphics.getWidth() / 3;
			xb = Gdx.graphics.getWidth() - 1;
		}

		if (exactbatpos > 0 && exactbatpos <= wid / 3)
			batpos = 0;
		else if (exactbatpos > wid / 3 && exactbatpos <= 2 * wid / 3)
			batpos = 1;
		else if (exactbatpos > 2 * wid / 3 && exactbatpos <= wid)
			batpos = 2;
	}

	public void update() {
		if (timercount == 0) {
			down = 1;
			quotes1 = "TIME OUT !!!";
			TimeOutFlag = true;
		}
		if (count >= target) {
			scene = true;
		}
		exactbatpos = positionx + batsizex / 2;
		signb = (signb + 1) % 6;
		if (counter > 0) {
			sign = (sign + 1) % 3;
			if (sign == 2) {
				bstateTime = (bstateTime + 0.5f) % 1.5f;
			}

		}

		if (scene == false) {
			icount = icount - icount / (140 + icount * icount);
			timercount = (int) icount;
			timerstr = String.valueOf(timercount);

			LosingTheBall();

			// new code
			decidebounds();

			// User Hint
			if (position.y <= h / 4 && position.y > h / 4 - (0.12074) * h) {
				if (hitcount < 5)
					quotes = "HIT   !!!";
				else if (hitcount >= 5)
					quotes = " ";
			} else {
				quotes = " ";
			}

			// checking the ball position and input position
			// This is a check for Motion Flag. If the flag is true you're gonna
			// execute it
			AdjustBatMovements();

			// What would be the next movements of the ball
			DecidingNextMovementsOfBall();

		} else if (scene == true) {
			gd.setTempscore(count);
			game.getSoundManager().stopGameMusic();
			if (xscene > 0) {
				xscene--;
				position.x = (float) (wid / 2 - wid / 12 + (wid / 3 - wid / 12)
						* Math.cos(10 * Math.PI / 180 * xscene));
				position.y = (float) (3 * hei / 4 - wid / 12 + (wid / 3 - wid / 12)
						* Math.sin(10 * Math.PI / 180 * xscene));
				if (xscene <= 0) {
					font.dispose();
					fontinfo.dispose();
					timer.dispose();
					fonts.dispose();
					game.st = State.levelComplete;
				}
			}
		}
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		mc = new ModeClass();
		mc = Save.loadMode();
		gc = new gameclass();
		gd = new GameData();
		ld = new levelDecider();
		pref = new Preferences();
		plc = new PlayedClass();
		atracker = new AchievementsTracker();
		if(mc.isOnlineFlag())
		{
			gd = Save.load();
			ld = Save.loadLevelDecider();
			gc = Save.load1();
			pref = Save.loadpreferences();
			plc = Save.loadPlayerData();
			plc.setPlayedFlag(true);
			Save.SavePlayedClass(plc);
			atracker = Save.loadAchievemntsData();
		}
		else
		{
			gd = Save.loadOffline();
			ld = Save.loadLevelDeciderOffline();
			gc = Save.load1Offline();
			pref = Save.loadpreferencesOffline();
			plc = Save.loadPlayerDataOffline();
			plc.setPlayedFlag(true);
			Save.SavePlayedClassOffline(plc);
			atracker = Save.loadAchievemntsDataOffline();
		}
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		bsizx = gc.getBsizx();
		bsizy = gc.getBsizy();
		bposx = gc.getBposx();
		bposy = gc.getBposy();
		wid = gc.getWid();
		hei = gc.getHei();
		batsizex = gc.getBatsizex();
		batsizey = gc.getBatsizey();
		sizex = gc.getSizex();
		sizey = gc.getSizey();
		position = gc.getPosition();
		positionx = gc.getPositionx();
		count = gc.getCount();
		target = gc.getTarget();
		str = "S:" + String.valueOf(count);
		str1 = "\nT:" + String.valueOf(target);
		icount = gc.getIcount();
		constant = gc.getConstant();
		deltacount = gc.getDeltacount();
		completedLevels = gc.getCompletedLevels();
		timercount = (int) icount;
		timerstr = String.valueOf(timercount);
		rand = new Random();
		gamestart = gc.getGamestart();
		down = gc.getDown();
		stateTime = gc.getStateTime();
		bstateTime = gc.getBstateTime();
		o = gc.getO();
		p = gc.getP();
		r = gc.getR();
		q1 = gc.getQ1();
		p1 = gc.getP1();
		q = gc.getQ();
		r1 = gc.getR1();
		index = gc.getIndex();
		lost = gc.getLost();
		hitcount = gc.getHitcount();
		pos = gc.getPos();
		flag = gc.getFlag();
		scenic = gc.getScenic();
		x = gc.getX();
		y1 = gc.getY1();
		z1 = gc.getZ1();
		x1 = gc.getX1();
		y = gc.getY();
		z = gc.getZ();
		n = gc.getN();
		s1 = gc.getS1();
		s2 = gc.getS2();
		s11 = gc.getS11();
		s12 = gc.getS12();
		sign = gc.getSign();
		signb = gc.getSignb();
		counter = gc.getCounter();
		quotes = gc.getQuotes();
		mapper = gc.getMapper();
		TimeOutFlag = gc.isTimeOutFlag();
		motionFlag = gc.isMotionFlag();
		batIndex = gc.getBatIndex();
		set = gc.getSet();
		hitdecider = gc.getHitdecider();
		scene = gc.isScene();
		xscene = gc.getXscene();
		num = gc.getNum();
		prevTimerCount = gc.getPrevTimerCount();
		texture = game.atlas.findRegion("gameback");
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setCatchMenuKey(true);
		stage = new Stage();
		batch = (SpriteBatch) stage.getBatch();
		font = new BitmapFont();
		font.getData().scale((game.height / game.width));
		fontinfo = new BitmapFont();
		fontinfo.setColor(Color.RED);
		fontinfo.getData().scale((game.height / game.width));
		fonta = new BitmapFont();
		fonta.setColor(Color.ORANGE);
		fonta.getData().scale((game.height / game.width));
		fonts = new BitmapFont();
		fonts.setColor(new Color(0, 1, 1, 1));
		fonts.getData().scale((game.height / game.width));
		timer = new BitmapFont();
		timer.setColor(Color.RED);
		timer.getData().scale((game.height / game.width));
		btexture = game.atlas.findRegion("b3");
		lostText = game.atlas.findRegion("keep_straight");
		quotes1 = "";
		quotes2 = " ";
		frames = new TextureRegion[2];
		bframes = new TextureRegion[3];
		frames[0] = game.atlas.findRegion("b1");
		frames[1] = game.atlas.findRegion("b2");
		bframes[0] = game.atlas.findRegion("n1");
		bframes[1] = game.atlas.findRegion("n2");
		bframes[2] = game.atlas.findRegion("n3");
		lostTimer = 0;
		animation = new Animation<TextureRegion>(1 / 2, frames);
		animation1 = new Animation<TextureRegion>(1 / 2, bframes);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		Gdx.input.setInputProcessor(stage);
		if (lost == 1) {
			losedelay = 0;
		} else if (lost == 0) {
			losedelay = 18;
		}
		switch (game.st) {
		case run:
			update();
			if (pref.getMusic() == 1 && lost == 0 && scene == false)
				game.getSoundManager().playGameMusic();
			if (Gdx.input.justTouched() && Gdx.input.getX() >= 2 * w / 3
					&& Gdx.input.getY() <= h / 8) {
				Gdx.input.vibrate(75);
				game.st = State.pause;
				savingfunction();
				if(mc.isOnlineFlag())
				{
					Save.save1(gc);
					Save.save(gd);
				}
				else
				{
					Save.save1Offline(gc);
					Save.saveOffline(gd);
				}
			}
			if (Gdx.input.isKeyPressed(Keys.BACK)) {
				Gdx.input.vibrate(75);
				game.st = State.back;
				savingfunction();
				if(mc.isOnlineFlag())
				{
					Save.save1(gc);
					Save.save(gd);
				}
				else
				{
					Save.save1Offline(gc);
					Save.saveOffline(gd);
				}
			}
			batch.begin();
			batch.draw(texture, 0, 0, Gdx.graphics.getWidth(),
					Gdx.graphics.getHeight());
			batch.draw(btexture, bposx, bposy, bsizx, bsizy);
			fonts.draw(batch, str, 10, h - 10);
			fonta.draw(batch, str1, 10, h - 10);
			timer.draw(batch, timerstr, w / 2 - 10, h - 10);
			fontinfo.draw(batch, quotes1, 0, h / 2, w - 1, Align.center, true);
			fontinfo.draw(batch, quotes2, 0, 3 * h / 4, w - 1, Align.center,
					true);
			if (scene == false) {
				if (lost == 0) {
					if(ld.getCompletedLevels() <= 15) {
						batch.draw(lostText, (Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/2)/2, Gdx.graphics.getHeight() / 2, Gdx.graphics.getWidth()/2,
								Gdx.graphics.getWidth() / 3);
					}
					batch.draw((TextureRegion) animation.getKeyFrame(stateTime), positionx, 1,
							batsizex, batsizey);
					batch.draw((TextureRegion)animation1.getKeyFrame(bstateTime), position.x,
							position.y, sizex, sizey);
					font.setColor(Color.BLUE);
					font.draw(batch, quotes, 0, 2 * hei / 3, wid - 1,
							Align.center, true);
				} else if (lost == 1) {
					game.getSoundManager().stopGameMusic();
					batch.draw((TextureRegion)animation1.getKeyFrame(bstateTime), position.x,
							position.y, sizex, sizey);
					batch.draw((TextureRegion)animation.getKeyFrame(stateTime), positionx, 1,
							batsizex, batsizey);
				}
			} else if (scene == true) {
				batch.draw((TextureRegion)animation1.getKeyFrame(bstateTime), position.x,
						position.y, sizex, sizey);
			}
			batch.end();
			stage.draw();
			break;

		case pause:
			if (game.st != State.levelComplete) {
				game.getSoundManager().stopGameMusic();
			}
			game.setScreen(game.ps);
			break;

		case back:
			if (game.st != State.levelComplete) {
				game.getSoundManager().stopGameMusic();
			}
			game.setScreen(game.ps);
			break;

		case gameover:
			batch.begin();
			batch.draw(texture, 0, 0, Gdx.graphics.getWidth(),
					Gdx.graphics.getHeight());
			batch.draw(btexture, bposx, bposy, bsizx, bsizy);
			fonts.draw(batch, str, 10, h - 10);
			fonta.draw(batch, str1, 10, h - 10);
			timer.draw(batch, timerstr, w / 2 - 10, h - 10);
			fontinfo.draw(batch, quotes1, 0, h / 2, w - 1, Align.center, true);
			fontinfo.draw(batch, quotes2, 0, 3 * h / 4, w - 1, Align.center,
					true);
			lostText = game.atlas.findRegion("out");

			batch.draw(lostText, (Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/2)/2, Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth()/2,
					Gdx.graphics.getWidth()/3);
			if (scene == false) {
				if (lost == 0) {
					batch.draw((TextureRegion) animation.getKeyFrame(stateTime), positionx, 1,
							batsizex, batsizey);
					batch.draw((TextureRegion)animation1.getKeyFrame(bstateTime), position.x,
							position.y, sizex, sizey);
					font.setColor(Color.BLUE);
					font.draw(batch, quotes, 0, 2 * hei / 3, wid - 1,
							Align.center, true);
				} else if (lost == 1) {
					game.getSoundManager().stopGameMusic();
					batch.draw((TextureRegion)animation1.getKeyFrame(bstateTime), position.x,
							position.y, sizex, sizey);
					batch.draw((TextureRegion)animation.getKeyFrame(stateTime), positionx, 1,
							batsizex, batsizey);
				}
			} else if (scene == true) {
				batch.draw((TextureRegion)animation1.getKeyFrame(bstateTime), position.x,
						position.y, sizex, sizey);
			}
			batch.end();
			lostTimer++;
			if(lostTimer > 100) {
				game.getSoundManager().stopGameMusic();
				if(mc.isOnlineFlag())
				{
					Save.save(gd);
				}
				else
				{
					Save.saveOffline(gd);
				}

				game.setScreen(game.go);
			}
			break;

		case levelComplete:
			if(mc.isOnlineFlag())
			{
				Save.save(gd);
				finalModifications();
				Save.SaveLevelDecider(ld);
				Save.save1(gc);
			}
			else
			{
				Save.saveOffline(gd);
				finalModifications();
				Save.SaveLevelDeciderOffline(ld);
				Save.save1Offline(gc);
			}
			game.setScreen(game.levelcompletescreen);
			break;
		}

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		game.st = State.pause;
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		savingfunction();
		if(mc.isOnlineFlag())
		{
			Save.save1(gc);
			Save.save(gd);
			Save.SaveLevelDecider(ld);
			Save.savepreferences(pref);
		}
		else
		{
			Save.save1Offline(gc);
			Save.saveOffline(gd);
			Save.SaveLevelDeciderOffline(ld);
			Save.savepreferencesOffline(pref);
		}

		game.st = State.pause;
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		if(mc.isOnlineFlag()) {
			gc = Save.load1();
			gd = Save.load();
			ld = Save.loadLevelDecider();
			pref = Save.loadpreferences();
		}
		else
		{
			gc = Save.load1Offline();
			gd = Save.loadOffline();
			ld = Save.loadLevelDeciderOffline();
			pref = Save.loadpreferencesOffline();
		}
		game.st = State.pause;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		font.dispose();
		fontinfo.dispose();
		fonts.dispose();
		batch.dispose();
		stage.dispose();
	}

	// Using Motion Sensors
	public void AdjustBatMovements() {
		/*if (Gdx.input.getAccelerometerY() >= 4) {
			System.out.println("Hit - up");
			stateTime = 1;
		} else {
			System.out.println("Normal - down");
			stateTime = 0;
			motionFlag = true;
		}
*/
/*		if (Gdx.input.justTouched() && Gdx.input.getY() > 3 * hei / 4) {
			System.out.println("Hit - up");
			stateTime = 1;
		} else {
			System.out.println("Normal - down");
			stateTime = 0;
			motionFlag = true;
		}*/

		stateTime = 0;
		motionFlag = false;
		if(Gdx.input.getAccelerometerX() <= 1 && Gdx.input.getAccelerometerX() >= -1 && !normalPos)
		{
			normalPos = true;
		}

		if(Gdx.input.getAccelerometerX() >= 4 && normalPos)
		{
			normalPos = false;
			if(batIndex == 0)
			{
				game.getSoundManager().playMove();
				batIndex = 2;
				positionx = wid * 0.82f - wid * 0.28f;
			}
			else if(batIndex == 1)
			{
				game.getSoundManager().playMove();
				batIndex = 0;
				positionx = wid * 0.16f - wid * 0.28f;
			}
			else if(batIndex == 2)
			{
				game.getSoundManager().playMove();
				batIndex = 1;
				positionx = wid / 2 - wid * 0.28f;
			}
		}
		else if(Gdx.input.getAccelerometerX()<=-4  && normalPos)
		{
			normalPos = false;
			if(batIndex == 0)
			{
				game.getSoundManager().playMove();
				batIndex = 1;
				positionx = wid / 2 - wid * 0.28f;
			}
			else if(batIndex == 1)
			{
				game.getSoundManager().playMove();
				batIndex = 2;
				positionx = wid * 0.82f - wid * 0.28f;
			}
			else if(batIndex == 2)
			{
				game.getSoundManager().playMove();
				batIndex = 0;
				positionx = wid * 0.16f - wid * 0.28f;
			}
		}
		
/*		if (Gdx.input.justTouched() && Gdx.input.getY() > 3 * hei / 4) {
			if (Gdx.input.getX() <= wid / 3) {
				game.getSoundManager().playMove();
				batIndex = 0;
				positionx = wid * 0.16f - wid * 0.28f;
			}

			else if (Gdx.input.getX() > wid / 3 && Gdx.input.getX() < 2 * wid / 3) {
				game.getSoundManager().playMove();
				batIndex = 1;
				positionx = wid / 2 - wid * 0.28f;
			}

			else if (Gdx.input.getX() >= 2 * wid / 3) {
				game.getSoundManager().playMove();
				batIndex = 2;
				positionx = wid * 0.82f - wid * 0.28f;
			}
		}*/

		if (position.y <= h / 4 && position.y > (h / 4 - 9 * h * 0.32 / 20))
		{
			if (batIndex == pos) {
				stateTime = 1;
				motionFlag = true;
			}
		}

		if (TimeOutFlag == false) {
			if (batIndex == pos) {
				if (position.y <= h / 4
						&& position.y > (h / 4 - 9 * h * 0.32 / 20)
						&& stateTime == 1 && motionFlag && flag == 0) {
					gamestart = 1;
					hitcount++;
					str = "S:" + String.valueOf(count += deltacount);
					str1 = "\nT:" + String.valueOf(target);
					game.getSoundManager().playHit();
					flag = 1;
					counter++;
					hitdecider = 1;
					// After the hitting process is done
					motionFlag = false;
				}
				try {
					Thread.sleep(losedelay);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				if (gamestart == 0) {
					down = 1;
				}
			}
		} else {
			try {
				Thread.sleep(losedelay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// Deciding Ball's Movements

	public void DecidingNextMovementsOfBall() {
		if (down == 1 && flag == 0) {
			position.y -= 10;
			index++;
			if (index == 14) {
				scenic = 1;
				lost = 1;
				// print game over
			}
		}

		if (position.y <= h / 4 + h / 16 && flag == 0 && index < 40
				&& gamestart > 0) {

			position.y -= 10;
			index++;
			if (index == 14) {
				scenic = 1;
				lost = 1;
				// print game over
			}
		}

		else if (position.y <= h / 4
				&& position.y > h / 4 - (h * 0.32f * 5 / 20) && flag == 1
				&& set == 0) {
			mapper = 0;
			mappingresets();
			set = 1;
		}

		else if (position.y <= h / 4 - (h * 0.32f * 5 / 20)
				&& position.y > h / 4 - (h * 0.32f * 10 / 20) && flag == 1
				&& set == 0) {
			mapper = 1;
			mappingresets();
			set = 1;
		}

		else if (position.y <= h / 4 + h / 16 && flag == 1) {
			lostText = game.atlas.findRegion("keep_straight");
			if (index > 0) {
				index--;
				position.y += 10;

			} else if (index == 0) {
				flag = 2;
				position.y += 15;
			}
		}

		// Parabolic movements of the ball
		else if (flag == 2) {
			switch (mapper) {
			case 0:

				if (pos == 1) {
					// Straight movement
					if (n == 0 || n == 1) {
						lostText = game.atlas.findRegion("keep_straight");
						if (o < 37) {
							position.y = (float) (hei / 4 + (3 * hei / 4 - wid / 3)
									* Math.sin(5 * Math.PI / 180 * o));
							try {
								Thread.sleep(game.delay1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if (o == 37) {
							pos = 1;
							resetvalues();
						} else
							o++;
					} else if (n == 2 || n == 3 || n == 4 || n == 5) {
						// movement of ball from middle node to left node
						lostText = game.atlas.findRegion("tilt_left");
						if (y < 37 && q == 0) {
							position.x = (float) (wid / 4 + (wid / 4 - wid / 12)
									* Math.cos(5 * Math.PI / 180 * y));
							position.y = (float) (hei / 4 + (3 * hei / 4 - wid / 3)
									* Math.sin(5 * Math.PI / 180 * y));

							try {
								Thread.sleep(game.delay1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if (y == 37 && q == 0) {
							pos = 0;
							resetvalues();

						} else
							y++;

					} else if (n == 6 || n == 7 || n == 8 || n == 9) {
						// movement of ball from middle node to right node
						if (x > 0 && p == 1) {
							x--;
							lostText = game.atlas.findRegion("tilt_right");
							position.x = (float) (3 * wid / 4 - wid / 6 + (wid / 4 - wid / 12)
									* Math.cos(5 * Math.PI / 180 * x));
							position.y = (float) (hei / 4 + (3 * hei / 4 - wid / 3)
									* Math.sin(5 * Math.PI / 180 * x));

							try {
								Thread.sleep(game.delay1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (x == 0 && p == 1) {
								pos = 2;
								resetvalues();

							}
						}

					}
				}

				else if (pos == 2) {
					if (n == 0 || n == 1) {
						// Straight movement
						if (o < 37) {
							lostText = game.atlas.findRegion("keep_straight");
							position.y = (float) (hei / 4 + (3 * hei / 4 - wid / 3)
									* Math.sin(5 * Math.PI / 180 * o));

							try {
								Thread.sleep(game.delay1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if (o == 37) {

							pos = 2;
							resetvalues();
						} else
							o++;

					} else if (n == 2 || n == 3 || n == 4 || n == 5) {
						// movement of ball from right node to middle node
						if (x1 < 37 && p1 == 0) {
							lostText = game.atlas.findRegion("tilt_left");
							x1++;
							position.x = (float) (3 * wid / 4 - wid / 6 + (wid / 4 - wid / 12)
									* Math.cos(5 * Math.PI / 180 * x1));
							position.y = (float) (hei / 4 + wid / 12 + (3 * hei / 4 - wid / 3)
									* Math.sin(5 * Math.PI / 180 * x1));

							try {
								Thread.sleep(game.delay1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (x1 == 37 && p1 == 0) {

								pos = 1;
								resetvalues();
							}
						}

					} else if (n == 6 || n == 7 || n == 8 || n == 9) {
						// movement of ball from right node to left node
						if (z < 37 && r == 1) {
							lostText = game.atlas.findRegion("tilt_right");
							z++;
							position.x = (float) (wid / 2 - wid / 12 + (wid / 2 - wid / 6)
									* Math.cos(5 * Math.PI / 180 * z));
							position.y = (float) (hei / 4 + wid / 12 + (3 * hei / 4 - wid / 3)
									* Math.sin(5 * Math.PI / 180 * z));

							try {
								Thread.sleep(game.delay1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (z == 37 && r == 1) {

								pos = 0;
								resetvalues();
							}

						}

					}
				}

				else if (pos == 0) {
					// Straight movement
					if (n == 0 || n == 1) {
						if (o < 37) {
							lostText = game.atlas.findRegion("keep_straight");
							position.y = (float) (hei / 4 + (3 * hei / 4 - wid / 3)
									* Math.sin(5 * Math.PI / 180 * o));

							try {
								Thread.sleep(game.delay1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if (o == 37) {
							pos = 0;
							resetvalues();
						} else
							o++;
					} else if (n == 2 || n == 3 || n == 4 || n == 5) {
						// movement of ball from left node to right node
						if (z1 > 0 && r1 == 0) {
							lostText = game.atlas.findRegion("tilt_left");
							z1--;
							position.x = (float) (wid / 2 - wid / 12 + (wid / 2 - wid / 6)
									* Math.cos(5 * Math.PI / 180 * z1));
							position.y = (float) (hei / 4 + (3 * hei / 4 - wid / 3)
									* Math.sin(5 * Math.PI / 180 * z1));

							try {
								Thread.sleep(game.delay1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (z1 == 0 && r1 == 0) {
								pos = 2;
								resetvalues();
							}
						}

					} else if (n == 6 || n == 7 || n == 8 || n == 9) {
						// movement of ball from left node to middle node
						if (y1 > 0 && q1 == 1) {
							y1--;
							lostText = game.atlas.findRegion("tilt_right");
							position.x = (float) (wid / 4 + (wid / 4 - wid / 12)
									* Math.cos(5 * Math.PI / 180 * y1));
							position.y = (float) (hei / 4 + (3 * hei / 4 - wid / 3)
									* Math.sin(5 * Math.PI / 180 * y1));

							try {
								Thread.sleep(game.delay1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (y1 == 0 && q1 == 1) {

								pos = 1;
								resetvalues();

							}
						}

					}
				}

				break;
			case 1:
				if (pos == 1) {
					// Straight movement
					if (n == 0 || n == 1) {
						if (o < 37) {
							lostText = game.atlas.findRegion("keep_straight");
							position.y = (float) (hei / 4 + (2.25 * hei / 4 - wid / 3)
									* Math.sin(5 * Math.PI / 180 * o));
							try {
								Thread.sleep(game.delay2);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if (o == 37) {
							pos = 1;
							resetvalues();
						} else
							o++;
					} else if (n == 2 || n == 3 || n == 4 || n == 5) {
						// movement of ball from middle node to left node

						if (y < 37 && q == 0) {
							lostText = game.atlas.findRegion("tilt_left");
							position.x = (float) (wid / 4 + (wid / 4 - wid / 12)
									* Math.cos(5 * Math.PI / 180 * y));
							position.y = (float) (hei / 4 + (2.25 * hei / 4 - wid / 3)
									* Math.sin(5 * Math.PI / 180 * y));

							try {
								Thread.sleep(game.delay2);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if (y == 37 && q == 0) {
							pos = 0;
							resetvalues();

						} else
							y++;

					} else if (n == 6 || n == 7 || n == 8 || n == 9) {
						// movement of ball from middle node to right node
						if (x > 0 && p == 1) {
							x--;
							lostText = game.atlas.findRegion("tilt_right");
							position.x = (float) (3 * wid / 4 - wid / 6 + (wid / 4 - wid / 12)
									* Math.cos(5 * Math.PI / 180 * x));
							position.y = (float) (hei / 4 + (2.25 * hei / 4 - wid / 3)
									* Math.sin(5 * Math.PI / 180 * x));

							try {
								Thread.sleep(game.delay2);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (x == 0 && p == 1) {
								pos = 2;
								resetvalues();

							}
						}

					}
				}

				else if (pos == 2) {
					if (n == 0 || n == 1) {
						// Straight movement
						if (o < 37) {
							lostText = game.atlas.findRegion("keep_straight");
							position.y = (float) (hei / 4 + (2.25 * hei / 4 - wid / 3)
									* Math.sin(5 * Math.PI / 180 * o));

							try {
								Thread.sleep(game.delay2);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if (o == 37) {

							pos = 2;
							resetvalues();
						} else
							o++;

					} else if (n == 2 || n == 3 || n == 4 || n == 5) {
						// movement of ball from right node to middle node
						if (x1 < 37 && p1 == 0) {
							x1++;
							lostText = game.atlas.findRegion("tilt_left");
							position.x = (float) (3 * wid / 4 - wid / 6 + (wid / 4 - wid / 12)
									* Math.cos(5 * Math.PI / 180 * x1));
							position.y = (float) (hei / 4 + wid / 12 + (2.25 * hei / 4 - wid / 3)
									* Math.sin(5 * Math.PI / 180 * x1));

							try {
								Thread.sleep(game.delay2);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (x1 == 37 && p1 == 0) {

								pos = 1;
								resetvalues();
							}
						}

					} else if (n == 6 || n == 7 || n == 8 || n == 9) {
						// movement of ball from right node to left node
						if (z < 37 && r == 1) {
							z++;
							lostText = game.atlas.findRegion("tilt_right");
							position.x = (float) (wid / 2 - wid / 12 + (wid / 2 - wid / 6)
									* Math.cos(5 * Math.PI / 180 * z));
							position.y = (float) (hei / 4 + wid / 12 + (2.25 * hei / 4 - wid / 3)
									* Math.sin(5 * Math.PI / 180 * z));

							try {
								Thread.sleep(game.delay2);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (z == 37 && r == 1) {

								pos = 0;
								resetvalues();
							}

						}

					}
				}

				else if (pos == 0) {
					// Straight movement
					if (n == 0 || n == 1) {
						if (o < 37) {
							lostText = game.atlas.findRegion("keep_straight");
							position.y = (float) (hei / 4 + (2.25 * hei / 4 - wid / 3)
									* Math.sin(5 * Math.PI / 180 * o));

							try {
								Thread.sleep(game.delay2);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if (o == 37) {
							pos = 0;
							resetvalues();
						} else
							o++;
					} else if (n == 2 || n == 3 || n == 4 || n == 5) {
						// movement of ball from left node to right node
						if (z1 > 0 && r1 == 0) {
							z1--;
							lostText = game.atlas.findRegion("tilt_left");
							position.x = (float) (wid / 2 - wid / 12 + (wid / 2 - wid / 6)
									* Math.cos(5 * Math.PI / 180 * z1));
							position.y = (float) (hei / 4 + (2.25 * hei / 4 - wid / 3)
									* Math.sin(5 * Math.PI / 180 * z1));

							try {
								Thread.sleep(game.delay2);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (z1 == 0 && r1 == 0) {
								pos = 2;
								resetvalues();
							}
						}

					} else if (n == 6 || n == 7 || n == 8 || n == 9) {
						// movement of ball from left node to middle node
						if (y1 > 0 && q1 == 1) {
							y1--;
							lostText = game.atlas.findRegion("tilt_right");
							position.x = (float) (wid / 4 + (wid / 4 - wid / 12)
									* Math.cos(5 * Math.PI / 180 * y1));
							position.y = (float) (hei / 4 + (2.25 * hei / 4 - wid / 3)
									* Math.sin(5 * Math.PI / 180 * y1));

							try {
								Thread.sleep(game.delay2);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (y1 == 0 && q1 == 1) {

								pos = 1;
								resetvalues();

							}
						}

					}
				}

				break;
			}
		}
	}

	public void LosingTheBall() {

		// Ball animations for losing it....
		if (pos == 0) {
			if (scenic == 1) {
				if (s1 > 0) {
					position.x = (float) ((wid / 4 - wid / 12) + wid / 4
							* Math.cos(5 * Math.PI * s1 / 180));
					position.y = (float) (hei / 18 + hei / 30
							* Math.sin(5 * Math.PI * s1 / 180));
					sizex -= 1;
					sizey -= 1;
				} else if (s1 == 0) {
					scenic = 2;
				}
				if (s1 > 0)
					s1--;

			}

			if (scenic == 2) {
				if (s2 > 0) {
					position.x = (float) (5 * wid / 12 + wid / 30
							* Math.cos(5 * Math.PI * s2 / 180));
					position.y = (float) (hei / 18 + hei / 30
							* Math.sin(5 * Math.PI * s2 / 180));
					sizex -= 1;
					sizey -= 1;
				}
				if (s2 > 0)
					s2--;
				if (s2 <= 0) {
					gd.setTempscore(count);
					timer.dispose();
					showDialog();
				}
			}
		}

		if (pos == 1) {
			if (scenic == 1) {
				if (s1 > 0) {
					position.x = (float) ((wid / 2 - wid / 12) + wid / 4
							* Math.cos(5 * Math.PI * s1 / 180));
					position.y = (float) (hei / 18 + hei / 30
							* Math.sin(5 * Math.PI * s1 / 180));
					sizex -= 1;
					sizey -= 1;
				} else if (s1 == 0) {
					scenic = 2;
				}
				if (s1 > 0)
					s1--;

			}

			if (scenic == 2) {
				if (s2 > 0) {
					position.x = (float) (2 * wid / 3 + wid / 30
							* Math.cos(5 * Math.PI * s2 / 180));
					position.y = (float) (hei / 18 + hei / 30
							* Math.sin(5 * Math.PI * s2 / 180));
					sizex -= 1;
					sizey -= 1;
				}
				if (s2 > 0)
					s2--;

				if (s2 <= 0) {
					gd.setTempscore(count);
					timer.dispose();
					showDialog();
				}

			}
		}

		if (pos == 2) {
			if (scenic == 1) {
				if (s11 < 18) {
					position.x = (float) ((wid / 2 - wid / 12) + wid / 4
							* Math.cos(5 * Math.PI * s11 / 180));
					position.y = (float) (hei / 18 + hei / 30
							* Math.sin(5 * Math.PI * s11 / 180));
					sizex -= 1;
					sizey -= 1;
				} else if (s11 == 18) {
					scenic = 2;
				}
				if (s11 < 18)
					s11++;

			}

			if (scenic == 2) {
				if (s12 < 18) {
					position.x = (float) (7 * wid / 24 + wid / 30
							* Math.cos(5 * Math.PI * s12 / 180));
					position.y = (float) (hei / 18 + hei / 30
							* Math.sin(5 * Math.PI * s12 / 180));
					sizex -= 1;
					sizey -= 1;
				}
				if (s12 < 18)
					s12++;
				if (s12 >= 18) {
					gd.setTempscore(count);
					timer.dispose();
					showDialog();
				}
			}
		}
	}
}
