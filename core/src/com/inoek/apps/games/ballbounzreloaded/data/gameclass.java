package com.inoek.apps.games.ballbounzreloaded.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;
import com.inoek.apps.games.ballbounzreloaded.Save;

public class gameclass implements Serializable {

	private float positionx;
	private int gamestart, down;
	private float stateTime, bstateTime;
	private Vector2 position;
	private int o, p, q, r, q1, r1, p1, index, scenic, lost, hitcount;
	private int pos, flag;
	private int x, y1, z1, x1, y, z, n, s1, s2, s11, s12, sign, signb, xscene;
	private float sizex, sizey;
	private float batsizex, batsizey, bposx, bposy, bsizx, bsizy;
	private int counter, constant;
	private float wid, hei;
	private String quotes;
	private int mapper, set, deltacount,batIndex;
	private long count, target, icount, prevTimerCount;
	private int hitdecider, completedLevels, num;
	private boolean TimeOutFlag,scene, motionFlag;
	
	public gameclass() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		bsizx = w / 6;
		bsizy = w / 6;
		bposx = w - w / 6;
		bposy = h - w / 6;
		wid = w;
		hei = h;
		batsizex = w / 2;
		batsizey = h*0.32f;
		sizex = w / 6;
		sizey = w / 6;
		positionx = w / 2 - w * 0.28f;
		position = new Vector2((w / 2 - w / 12), h / 4 - 30);
		gamestart = 0;
		down = 0;
		stateTime = 0f;
		bstateTime = 0f;
		o = 1;
		p = 1;
		q = 0;
		r = 1;
		q1 = 1;
		r1 = 0;
		p1 = 0;
		index = 1;
		lost = 0;
		hitcount = 0;
		pos = 1;
		flag = 0;
		scenic = 0;
		x = 36;
		y1 = 36;
		z1 = 36;
		x1 = 1;
		y = 1;
		z = 1;
		n = 0;
		s1 = 36;
		s2 = 36;
		s11 = 0;
		s12 = 0;
		sign = 0;
		signb = 0;
		counter = 0;
		quotes = " ";
		mapper = 0;
		set = 0;
		hitdecider = 0;
		xscene = 360;
		TimeOutFlag = false;
		scene = false;
		motionFlag = true;
		batIndex = 1;
	}

	public void gameClassInitOnline()
	{
		count = Save.leveldecider.get_score();
		constant = Save.leveldecider.getConstant();
		icount = Save.leveldecider.get_timerCount();
		target = Save.leveldecider.get_target();
		completedLevels = Save.leveldecider.getCompletedLevels();
		num = Save.leveldecider.getN();
		prevTimerCount = Save.leveldecider.get_prevTimerCount();
		deltacount = constant;
	}

	public void gameClassInitOffline()
	{
		count = Save.leveldeciderOffline.get_score();
		constant = Save.leveldeciderOffline.getConstant();
		icount = Save.leveldeciderOffline.get_timerCount();
		target = Save.leveldeciderOffline.get_target();
		completedLevels = Save.leveldeciderOffline.getCompletedLevels();
		num = Save.leveldeciderOffline.getN();
		prevTimerCount = Save.leveldeciderOffline.get_prevTimerCount();
		deltacount = constant;
	}
	
	public boolean isMotionFlag() {
		return motionFlag;
	}

	public void setMotionFlag(boolean motionFlag) {
		this.motionFlag = motionFlag;
	}

	public float getPositionx() {
		return positionx;
	}


	public void setPositionx(float positionx) {
		this.positionx = positionx;
	}


	public int getGamestart() {
		return gamestart;
	}


	public void setGamestart(int gamestart) {
		this.gamestart = gamestart;
	}


	public int getDown() {
		return down;
	}


	public void setDown(int down) {
		this.down = down;
	}


	public float getStateTime() {
		return stateTime;
	}


	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}


	public float getBstateTime() {
		return bstateTime;
	}


	public void setBstateTime(float bstateTime) {
		this.bstateTime = bstateTime;
	}


	public Vector2 getPosition() {
		return position;
	}


	public void setPosition(Vector2 position) {
		this.position = position;
	}


	public int getO() {
		return o;
	}


	public void setO(int o) {
		this.o = o;
	}


	public int getP() {
		return p;
	}


	public void setP(int p) {
		this.p = p;
	}


	public int getQ() {
		return q;
	}


	public void setQ(int q) {
		this.q = q;
	}


	public int getR() {
		return r;
	}


	public void setR(int r) {
		this.r = r;
	}


	public int getQ1() {
		return q1;
	}


	public void setQ1(int q1) {
		this.q1 = q1;
	}


	public int getR1() {
		return r1;
	}


	public void setR1(int r1) {
		this.r1 = r1;
	}


	public int getP1() {
		return p1;
	}


	public void setP1(int p1) {
		this.p1 = p1;
	}


	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}


	public int getScenic() {
		return scenic;
	}


	public void setScenic(int scenic) {
		this.scenic = scenic;
	}


	public int getLost() {
		return lost;
	}


	public void setLost(int lost) {
		this.lost = lost;
	}


	public int getHitcount() {
		return hitcount;
	}


	public void setHitcount(int hitcount) {
		this.hitcount = hitcount;
	}


	public int getPos() {
		return pos;
	}


	public void setPos(int pos) {
		this.pos = pos;
	}


	public int getFlag() {
		return flag;
	}


	public void setFlag(int flag) {
		this.flag = flag;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY1() {
		return y1;
	}


	public void setY1(int y1) {
		this.y1 = y1;
	}


	public int getZ1() {
		return z1;
	}


	public void setZ1(int z1) {
		this.z1 = z1;
	}


	public int getX1() {
		return x1;
	}


	public void setX1(int x1) {
		this.x1 = x1;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getZ() {
		return z;
	}


	public void setZ(int z) {
		this.z = z;
	}


	public int getN() {
		return n;
	}


	public void setN(int n) {
		this.n = n;
	}


	public int getS1() {
		return s1;
	}


	public void setS1(int s1) {
		this.s1 = s1;
	}


	public int getS2() {
		return s2;
	}


	public void setS2(int s2) {
		this.s2 = s2;
	}


	public int getS11() {
		return s11;
	}


	public void setS11(int s11) {
		this.s11 = s11;
	}


	public int getS12() {
		return s12;
	}


	public void setS12(int s12) {
		this.s12 = s12;
	}


	public int getSign() {
		return sign;
	}


	public void setSign(int sign) {
		this.sign = sign;
	}


	public int getSignb() {
		return signb;
	}


	public void setSignb(int signb) {
		this.signb = signb;
	}


	public int getXscene() {
		return xscene;
	}


	public void setXscene(int xscene) {
		this.xscene = xscene;
	}


	public float getSizex() {
		return sizex;
	}


	public void setSizex(float sizex) {
		this.sizex = sizex;
	}


	public float getSizey() {
		return sizey;
	}


	public void setSizey(float sizey) {
		this.sizey = sizey;
	}


	public float getBatsizex() {
		return batsizex;
	}


	public void setBatsizex(float batsizex) {
		this.batsizex = batsizex;
	}


	public float getBatsizey() {
		return batsizey;
	}


	public void setBatsizey(float batsizey) {
		this.batsizey = batsizey;
	}


	public float getBposx() {
		return bposx;
	}


	public void setBposx(float bposx) {
		this.bposx = bposx;
	}


	public float getBposy() {
		return bposy;
	}


	public void setBposy(float bposy) {
		this.bposy = bposy;
	}


	public float getBsizx() {
		return bsizx;
	}


	public void setBsizx(float bsizx) {
		this.bsizx = bsizx;
	}


	public float getBsizy() {
		return bsizy;
	}


	public void setBsizy(float bsizy) {
		this.bsizy = bsizy;
	}


	public long getIcount() {
		return icount;
	}


	public void setIcount(long icount) {
		this.icount = icount;
	}


	public int getCounter() {
		return counter;
	}


	public void setCounter(int counter) {
		this.counter = counter;
	}


	public int getConstant() {
		return constant;
	}


	public void setConstant(int constant) {
		this.constant = constant;
	}


	public float getWid() {
		return wid;
	}


	public void setWid(float wid) {
		this.wid = wid;
	}


	public float getHei() {
		return hei;
	}


	public void setHei(float hei) {
		this.hei = hei;
	}


	public String getQuotes() {
		return quotes;
	}


	public void setQuotes(String quotes) {
		this.quotes = quotes;
	}


	public int getMapper() {
		return mapper;
	}


	public void setMapper(int mapper) {
		this.mapper = mapper;
	}


	public int getSet() {
		return set;
	}


	public void setSet(int set) {
		this.set = set;
	}


	public int getDeltacount() {
		return deltacount;
	}


	public void setDeltacount(int deltacount) {
		this.deltacount = deltacount;
	}


	public long getCount() {
		return count;
	}


	public void setCount(long count) {
		this.count = count;
	}


	public long getTarget() {
		return target;
	}


	public void setTarget(long target) {
		this.target = target;
	}

	public int getHitdecider() {
		return hitdecider;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public void setHitdecider(int hitdecider) {
		this.hitdecider = hitdecider;
	}


	public int getCompletedLevels() {
		return completedLevels;
	}


	public void setCompletedLevels(int completedLevels) {
		this.completedLevels = completedLevels;
	}


	public boolean isTimeOutFlag() {
		return TimeOutFlag;
	}


	public void setTimeOutFlag(boolean TimeOutFlag) {
		this.TimeOutFlag = TimeOutFlag;
	}


	public boolean isScene() {
		return scene;
	}


	public void setScene(boolean scene) {
		this.scene = scene;
	}


	public long getPrevTimerCount() {
		return prevTimerCount;
	}


	public void setPrevTimerCount(long prevTimerCount) {
		this.prevTimerCount = prevTimerCount;
	}

	public int getBatIndex() {
		return batIndex;
	}

	public void setBatIndex(int batIndex) {
		this.batIndex = batIndex;
	}

	@Override
	public void write(Json json) {
		// TODO Auto-generated method stub
		json.writeValue("scene",scene);
		json.writeValue("xscene",xscene);
		
		json.writeValue("bsizx", bsizx);

		json.writeValue("bsizy", bsizy);

		json.writeValue("bposx", bposx);

		json.writeValue("bposy", bposy);

		json.writeValue("wid", wid);

		json.writeValue("hei", hei);

		json.writeValue("batsizex", batsizex);
		json.writeValue("batsizey", batsizey);

		json.writeValue("sizex", sizex);

		json.writeValue("sizey", sizey);

		json.writeValue("position", position);

		json.writeValue("positionx", positionx);

		json.writeValue("gamestart", gamestart);
		json.writeValue("down", down);

		json.writeValue("stateTime", stateTime);
		json.writeValue("bstateTime", bstateTime);

		json.writeValue("o", o);
		json.writeValue("p", p);
		json.writeValue("count", count);
		json.writeValue("r", r);
		json.writeValue("q1", q1);
		json.writeValue("p1", p1);
		json.writeValue("q", q);
		json.writeValue("r1", r1);
		json.writeValue("index", index);

		json.writeValue("lost", lost);
		json.writeValue("hitcount", hitcount);

		json.writeValue("pos", pos);
		json.writeValue("flag", flag);
		json.writeValue("scenic", scenic);

		json.writeValue("x", x);
		json.writeValue("y1", y1);
		json.writeValue("z1", z1);
		json.writeValue("x1", x1);
		json.writeValue("y", y);
		json.writeValue("z", z);
		json.writeValue("n", n);
		json.writeValue("s1", s1);

		json.writeValue("s2", s2);
		json.writeValue("s11", s11);
		json.writeValue("s12", s12);
		json.writeValue("sign", sign);
		json.writeValue("signb", signb);

		json.writeValue("counter", counter);

		json.writeValue("quotes", quotes);

		json.writeValue("mapper", mapper);
		json.writeValue("set", set);

		json.writeValue("hitdecider", hitdecider);

		json.writeValue("icount", icount);
		json.writeValue("target", target);
		json.writeValue("TimeOutFlag", TimeOutFlag);
		json.writeValue("motionFlag", motionFlag);
		json.writeValue("batIndex", batIndex);
		json.writeValue("constant", constant);
		json.writeValue("deltacount",deltacount);
		json.writeValue("completedLevels", completedLevels);
		json.writeValue("num",num);
		json.writeValue("prevTimerCount",prevTimerCount);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		// TODO Auto-generated method stub
		scene = json.readValue("scene",boolean.class,jsonData);
		xscene = json.readValue("xscene",int.class, jsonData);
		
		bsizx = json.readValue("bsizx", float.class, jsonData);

		bsizy = json.readValue("bsizy", float.class, jsonData);

		bposx = json.readValue("bposx", float.class, jsonData);

		bposy = json.readValue("bposy", float.class, jsonData);

		wid = json.readValue("wid", float.class, jsonData);

		hei = json.readValue("hei", float.class, jsonData);

		batsizex = json.readValue("batsizex", float.class, jsonData);
		batsizey = json.readValue("batsizey", float.class, jsonData);

		sizex = json.readValue("sizex", float.class, jsonData);

		sizey = json.readValue("sizey", float.class, jsonData);

		position = json.readValue("position", Vector2.class, jsonData);

		positionx = json.readValue("positionx", float.class, jsonData);

		gamestart = json.readValue("gamestart", int.class, jsonData);
		down = json.readValue("down", int.class, jsonData);

		stateTime = json.readValue("stateTime", int.class, jsonData);
		bstateTime = json.readValue("bstateTime", int.class, jsonData);

		o = json.readValue("o", int.class, jsonData);
		p = json.readValue("p", int.class, jsonData);
		count = json.readValue("count", long.class, jsonData);
		r = json.readValue("r", int.class, jsonData);
		q1 = json.readValue("q1", int.class, jsonData);
		p1 = json.readValue("p1", int.class, jsonData);
		q = json.readValue("q", int.class, jsonData);
		r1 = json.readValue("r1", int.class, jsonData);
		index = json.readValue("index", int.class, jsonData);

		lost = json.readValue("lost", int.class, jsonData);
		hitcount = json.readValue("hitcount", int.class, jsonData);

		pos = json.readValue("pos", int.class, jsonData);
		flag = json.readValue("flag", int.class, jsonData);
		scenic = json.readValue("scenic", int.class, jsonData);

		x = json.readValue("x", int.class, jsonData);
		y1 = json.readValue("y1", int.class, jsonData);
		z1 = json.readValue("z1", int.class, jsonData);
		x1 = json.readValue("x1", int.class, jsonData);
		y = json.readValue("y", int.class, jsonData);
		z = json.readValue("z", int.class, jsonData);
		n = json.readValue("n", int.class, jsonData);
		s1 = json.readValue("s1", int.class, jsonData);

		s2 = json.readValue("s2", int.class, jsonData);
		s11 = json.readValue("s11", int.class, jsonData);
		s12 = json.readValue("s12", int.class, jsonData);
		sign = json.readValue("sign", int.class, jsonData);
		signb = json.readValue("signb", int.class, jsonData);

		counter = json.readValue("counter", int.class, jsonData);

		quotes = json.readValue("quotes", String.class, jsonData);

		mapper = json.readValue("mapper", int.class, jsonData);
		set = json.readValue("set", int.class, jsonData);

		hitdecider = json.readValue("hitdecider", int.class, jsonData);

		icount = json.readValue("icount", long.class, jsonData);
		target = json.readValue("target", long.class, jsonData);
		TimeOutFlag = json.readValue("TimeOutFlag", boolean.class, jsonData);
		motionFlag = json.readValue("motionFlag", boolean.class, jsonData);
		batIndex = json.readValue("batIndex", int.class, jsonData);
		constant = json.readValue("constant", int.class, jsonData);
		completedLevels = json
				.readValue("completedLevels", int.class, jsonData);
		num = json.readValue("num",int.class, jsonData);
		deltacount = json.readValue("deltacount", int.class,jsonData);
		prevTimerCount = json.readValue("prevTimerCount",long.class, jsonData);
	}


}
