package com.inoek.apps.games.ballbounzreloaded;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.inoek.apps.games.ballbounzreloaded.data.AchievementsTracker;
import com.inoek.apps.games.ballbounzreloaded.data.GameData;
import com.inoek.apps.games.ballbounzreloaded.data.ModeClass;
import com.inoek.apps.games.ballbounzreloaded.data.PlayedClass;
import com.inoek.apps.games.ballbounzreloaded.data.Preferences;
import com.inoek.apps.games.ballbounzreloaded.data.gameclass;
import com.inoek.apps.games.ballbounzreloaded.data.gamename;
import com.inoek.apps.games.ballbounzreloaded.data.levelDecider;

public class Save {
	public static GameData gd,gdOffline;
	public static gameclass gc,gcOffline;
	public static gamename gname,gnameOffline;
	public static Preferences pref,prefOffline;
	public static levelDecider leveldecider,leveldeciderOffline;
	public static PlayedClass pl,plOffline;
	public static AchievementsTracker at,atOffline;
	public static ModeClass mc;


	//This function is for all

	public static void saveMode(ModeClass mcl)
	{
		Json json = new Json();
		mc = mcl;
		FileHandle file = Gdx.files.local("data/ModeTracker.dat");
		file.writeString(Base64Coder.encodeString(json.toJson(mc)), false);
	}

	public static ModeClass loadMode() {
		if (!ModeFileExists()) {
			initMode();
			return mc;
		}
		Json json = new Json();
		FileHandle file = Gdx.files.local(("data/ModeTracker.dat"));
		mc = json.fromJson(ModeClass.class,
				Base64Coder.decodeString(file.readString()));
		return mc;
	}

	public static boolean ModeFileExists() {
		FileHandle f = Gdx.files.local("data/ModeTracker.dat");
		return f.exists();
	}

	public static void initMode() {
		mc = new ModeClass();
		saveMode(mc);
	}

	//These are applicable to player playing online

	public static void saveAchievementsTrack(AchievementsTracker atr)
	{
		Json json = new Json();
		at = atr;
		FileHandle file = Gdx.files.local("data/achievementsTracker.dat");
		file.writeString(Base64Coder.encodeString(json.toJson(at)), false);
	}

	public static void savepreferences(Preferences pf) {
		Json json = new Json();
		pref = pf;
		FileHandle file = Gdx.files.local("data/preference.dat");
		file.writeString(Base64Coder.encodeString(json.toJson(pref)), false);
	}

	public static void save(GameData gd1) {
		Json json = new Json();
		gd = gd1;
		FileHandle file = Gdx.files.local("data/players.dat");
		file.writeString(Base64Coder.encodeString(json.toJson(gd)), false);
	}

	public static void savename(gamename gn1) {
		Json json = new Json();
		gname = gn1;
		FileHandle file = Gdx.files.local("data/tapdaballname.dat");
		file.writeString(Base64Coder.encodeString(json.toJson(gname)), false);
	}

	public static void save1(gameclass gc1) {
		Json json = new Json();
		gc = gc1;
		FileHandle file = Gdx.files.local("data/gameprogress.dat");
		file.writeString(Base64Coder.encodeString(json.toJson(gc)), false);

	}

	public static void SaveLevelDecider(levelDecider ld) {
		Json json = new Json();
		leveldecider = ld;
		FileHandle file = Gdx.files.local("data/levelDecider.dat");
		file.writeString(Base64Coder.encodeString(json.toJson(leveldecider)),
				false);
	}

	public static void SavePlayedClass(PlayedClass plc) {
		Json json = new Json();
		pl = plc;
		FileHandle file = Gdx.files.local("data/playfile.dat");
		file.writeString(Base64Coder.encodeString(json.toJson(pl)),
				false);
	}

	public static Preferences loadpreferences() {
		if (!PreferenceFileExists()) {
			initpref();
			return pref;
		}
		Json json = new Json();
		FileHandle file = Gdx.files.local(("data/preference.dat"));
		pref = json.fromJson(Preferences.class,
				Base64Coder.decodeString(file.readString()));
		return pref;
	}

	public static GameData load() {
		if (!SaveFileExists()) {
			init();
			return gd;
		}
		Json json = new Json();
		FileHandle file = Gdx.files.local("data/players.dat");
		gd = json.fromJson(GameData.class,
				Base64Coder.decodeString(file.readString()));
		return gd;
	}

	public static gamename loadname() {
		if (!SaveFileExists2()) {
			initname();
			return gname;
		}
		Json json = new Json();
		FileHandle file = Gdx.files.local("data/tapdaballname.dat");
		gname = json.fromJson(gamename.class,
				Base64Coder.decodeString(file.readString()));
		return gname;
	}

	public static gameclass load1() {
		if (!SaveFileExists1()) {
			init1();
			return gc;
		}
		Json json = new Json();
		FileHandle file = Gdx.files.local("data/gameprogress.dat");
		gc = json.fromJson(gameclass.class,Base64Coder.decodeString(file.readString()));
		return gc;

	}

	public static levelDecider loadLevelDecider() {
		if (!levelDeciderFileExists()) {
			initlevelDecider();
			return leveldecider;
		}
		Json json = new Json();
		FileHandle file = Gdx.files.local("data/levelDecider.dat");
		leveldecider = json.fromJson(levelDecider.class,
				Base64Coder.decodeString(file.readString()));
		return leveldecider;
	}

	public static PlayedClass loadPlayerData() {
		if (!PlayedFileExists()) {
			initPlayedFile();
			return pl;
		}
		Json json = new Json();
		FileHandle file = Gdx.files.local("data/playfile.dat");
		pl = json.fromJson(PlayedClass.class,
				Base64Coder.decodeString(file.readString()));
		return pl;
	}

	public static AchievementsTracker loadAchievemntsData() {
		if (!AchievementFileExists()) {
			initAchievementsFile();
			return at;
		}
		Json json = new Json();
		FileHandle file = Gdx.files.local("data/achievementsTracker.dat");
		at = json.fromJson(AchievementsTracker.class,Base64Coder.decodeString(file.readString()));
		return at;
	}


	public static void delete1() {
		if (SaveFileExists1()) {
			FileHandle file = Gdx.files.local("data/gameprogress.dat");
			file.delete();
		}
	}

	public static boolean PreferenceFileExists() {
		FileHandle f = Gdx.files.local(("data/preference.dat"));
		return f.exists();
	}

	public static boolean SaveFileExists() {
		FileHandle f = Gdx.files.local("data/players.dat");
		return f.exists();
	}

	public static boolean levelDeciderFileExists() {
		FileHandle f = Gdx.files.local("data/levelDecider.dat");
		return f.exists();
	}

	public static boolean AchievementFileExists() {
		FileHandle f = Gdx.files.local("data/achievementsTracker.dat");
		return f.exists();
	}

	public static boolean PlayedFileExists() {
		FileHandle f = Gdx.files.local("data/playfile.dat");
		return f.exists();
	}

	public static void init() {
		gd = new GameData();
		gd.init();
		save(gd);
	}

	public static boolean SaveFileExists1() {
		FileHandle f = Gdx.files.local("data/gameprogress.dat");
		return f.exists();
	}

	public static boolean SaveFileExists2() {
		FileHandle f = Gdx.files.local("data/tapdaballname.dat");
		return f.exists();
	}

	public static void init1() {
		gc = new gameclass();
		gc.gameClassInitOnline();
		save1(gc);
	}

	public static void initpref() {
		pref = new Preferences();
		savepreferences(pref);
	}

	public static void initname() {
		gname = new gamename();
		savename(gname);
	}

	public static void initlevelDecider() {
		leveldecider = new levelDecider();
		SaveLevelDecider(leveldecider);
	}

	public static void initPlayedFile(){
		pl = new PlayedClass();
		SavePlayedClass(pl);
	}

	public static void initAchievementsFile(){
		at = new AchievementsTracker();
		saveAchievementsTrack(at);
	}


	//These are applicable to player playing offline

	public static void saveAchievementsTrackOffline(AchievementsTracker atr)
	{
		Json json = new Json();
		atOffline = atr;
		FileHandle file = Gdx.files.local("data/achievementsTrackerOffline.dat");
		file.writeString(Base64Coder.encodeString(json.toJson(atOffline)), false);
	}

	public static void savepreferencesOffline(Preferences pf) {
		Json json = new Json();
		prefOffline = pf;
		FileHandle file = Gdx.files.local("data/preferenceOffline.dat");
		file.writeString(Base64Coder.encodeString(json.toJson(prefOffline)), false);
	}

	public static void saveOffline(GameData gd1) {
		Json json = new Json();
		gdOffline = gd1;
		FileHandle file = Gdx.files.local("data/playersOffline.dat");
		file.writeString(Base64Coder.encodeString(json.toJson(gdOffline)), false);
	}

	public static void savenameOffline(gamename gn1) {
		Json json = new Json();
		gnameOffline = gn1;
		FileHandle file = Gdx.files.local("data/tapdaballnameOffline.dat");
		file.writeString(Base64Coder.encodeString(json.toJson(gnameOffline)), false);
	}

	public static void save1Offline(gameclass gc1) {
		Json json = new Json();
		gcOffline = gc1;
		FileHandle file = Gdx.files.local("data/gameprogressOffline.dat");
		file.writeString(Base64Coder.encodeString(json.toJson(gcOffline)), false);

	}

	public static void SaveLevelDeciderOffline(levelDecider ld) {
		Json json = new Json();
		leveldeciderOffline = ld;
		FileHandle file = Gdx.files.local("data/levelDeciderOffline.dat");
		file.writeString(Base64Coder.encodeString(json.toJson(leveldeciderOffline)),
				false);
	}

	public static void SavePlayedClassOffline(PlayedClass plc) {
		Json json = new Json();
		plOffline = plc;
		FileHandle file = Gdx.files.local("data/playfileOffline.dat");
		file.writeString(Base64Coder.encodeString(json.toJson(plOffline)),
				false);
	}

	public static Preferences loadpreferencesOffline() {
		if (!PreferenceFileExistsOffline()) {
			initprefOffline();
			return prefOffline;
		}
		Json json = new Json();
		FileHandle file = Gdx.files.local(("data/preferenceOffline.dat"));
		prefOffline = json.fromJson(Preferences.class,
				Base64Coder.decodeString(file.readString()));
		return prefOffline;
	}

	public static GameData loadOffline() {
		if (!SaveFileExistsOffline()) {
			initOffline();
			return gdOffline;
		}
		Json json = new Json();
		FileHandle file = Gdx.files.local("data/playersOffline.dat");
		gdOffline = json.fromJson(GameData.class,
				Base64Coder.decodeString(file.readString()));
		return gdOffline;
	}

	public static gamename loadnameOffline() {
		if (!SaveFileExists2Offline()) {
			initnameOffline();
			return gnameOffline;
		}
		Json json = new Json();
		FileHandle file = Gdx.files.local("data/tapdaballnameOffline.dat");
		gnameOffline = json.fromJson(gamename.class,
				Base64Coder.decodeString(file.readString()));
		return gnameOffline;
	}

	public static gameclass load1Offline() {
		if (!SaveFileExists1Offline()) {
			init1Offline();
			return gcOffline;
		}
		Json json = new Json();
		FileHandle file = Gdx.files.local("data/gameprogressOffline.dat");
		gcOffline = json.fromJson(gameclass.class,Base64Coder.decodeString(file.readString()));
		return gcOffline;

	}

	public static levelDecider loadLevelDeciderOffline() {
		if (!levelDeciderFileExistsOffline()) {
			initlevelDeciderOffline();
			return leveldeciderOffline;
		}
		Json json = new Json();
		FileHandle file = Gdx.files.local("data/levelDeciderOffline.dat");
		leveldeciderOffline = json.fromJson(levelDecider.class,
				Base64Coder.decodeString(file.readString()));
		return leveldeciderOffline;
	}

	public static PlayedClass loadPlayerDataOffline() {
		if (!PlayedFileExistsOffline()) {
			initPlayedFileOffline();
			return plOffline;
		}
		Json json = new Json();
		FileHandle file = Gdx.files.local("data/playfileOffline.dat");
		plOffline = json.fromJson(PlayedClass.class,
				Base64Coder.decodeString(file.readString()));
		return plOffline;
	}

	public static AchievementsTracker loadAchievemntsDataOffline() {
		if (!AchievementFileExistsOffline()) {
			initAchievementsFileOffline();
			return atOffline;
		}
		Json json = new Json();
		FileHandle file = Gdx.files.local("data/achievementsTrackerOffline.dat");
		atOffline = json.fromJson(AchievementsTracker.class,Base64Coder.decodeString(file.readString()));
		return atOffline;
	}


	public static void delete1Offline() {
		if (SaveFileExists1Offline()) {
			FileHandle file = Gdx.files.local("data/gameprogressOffline.dat");
			file.delete();
		}
	}

	public static boolean PreferenceFileExistsOffline() {
		FileHandle f = Gdx.files.local(("data/preferenceOffline.dat"));
		return f.exists();
	}

	public static boolean SaveFileExistsOffline() {
		FileHandle f = Gdx.files.local("data/playersOffline.dat");
		return f.exists();
	}

	public static boolean levelDeciderFileExistsOffline() {
		FileHandle f = Gdx.files.local("data/levelDeciderOffline.dat");
		return f.exists();
	}

	public static boolean AchievementFileExistsOffline() {
		FileHandle f = Gdx.files.local("data/achievementsTrackerOffline.dat");
		return f.exists();
	}

	public static boolean PlayedFileExistsOffline() {
		FileHandle f = Gdx.files.local("data/playfileOffline.dat");
		return f.exists();
	}

	public static void initOffline() {
		gdOffline = new GameData();
		gdOffline.init();
		saveOffline(gdOffline);
	}

	public static boolean SaveFileExists1Offline() {
		FileHandle f = Gdx.files.local("data/gameprogressOffline.dat");
		return f.exists();
	}

	public static boolean SaveFileExists2Offline() {
		FileHandle f = Gdx.files.local("data/tapdaballnameOffline.dat");
		return f.exists();
	}

	public static void init1Offline() {
		gcOffline = new gameclass();
		gcOffline.gameClassInitOffline();
		save1Offline(gcOffline);
	}

	public static void initprefOffline() {
		prefOffline = new Preferences();
		savepreferencesOffline(prefOffline);
	}

	public static void initnameOffline() {
		gnameOffline = new gamename();
		savenameOffline(gnameOffline);
	}

	public static void initlevelDeciderOffline() {
		leveldeciderOffline = new levelDecider();
		SaveLevelDeciderOffline(leveldeciderOffline);
	}

	public static void initPlayedFileOffline(){
		plOffline = new PlayedClass();
		SavePlayedClassOffline(plOffline);
	}

	public static void initAchievementsFileOffline(){
		atOffline = new AchievementsTracker();
		saveAchievementsTrackOffline(atOffline);
	}
}
