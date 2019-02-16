package com.inoek.apps.games.ballbounzreloaded;

import android.Manifest;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.games.AchievementsClient;
import com.google.android.gms.games.AnnotatedData;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesClient;
import com.google.android.gms.games.LeaderboardsClient;
import com.google.android.gms.games.achievement.Achievement;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.example.games.basegameutils.GameHelper;
import com.inoek.apps.games.ballbounzreloaded.BallBounzAndroid.MyGameCallBack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AndroidApplication implements MyGameCallBack,
		AdsController, GameHelper.GameHelperListener {
	// for testing ca-app-pub-3940256099942544/6300978111
	public static final String BANNER_AD_UNIT_ID = "ca-app-pub-5637966806573218/6663202812";
	public static final String INTERSTITIAL_AD_UNIT_ID = " ";
	ImageView imag;
	BroadcastReceiver mReceiver;
	View gameview;
	String rate;
	static BallBounzAndroid m;
	AdView bannerAd;
	InterstitialAd interAd;
	//public GameHelper gameHelper;
	final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;
	// tag for debug logging
	private static final String TAG = "TanC";
	// request codes we use when invoking an external activity
	private static final int RC_UNUSED = 5001;
	private static final int RC_SIGN_IN = 9001;
	// Client used to sign in with Google APIs
	private GoogleSignInClient mGoogleSignInClient;
	// Client variables
    private GamesClient mGamesClient;
	private AchievementsClient mAchievementsClient;
	private LeaderboardsClient mLeaderboardsClient;
	private String mEmailId, mName;
	private int achcount;
	private boolean achLoaded;
//ca-app-pub-5637966806573218~8399133922
	public MainActivity() {

	}

	private boolean isSignedIn() {
		return GoogleSignIn.getLastSignedInAccount(this) != null;
	}

	private void signInSilently() {
		Log.d(TAG, "signInSilently()");

		mGoogleSignInClient.silentSignIn().addOnCompleteListener(this,
				new OnCompleteListener<GoogleSignInAccount>() {
					@Override
					public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
						if (task.isSuccessful()) {
							Log.d(TAG, "signInSilently(): success");
							onConnected(task.getResult());
						} else {
							Log.d(TAG, "signInSilently(): failure", task.getException());
							onDisconnected();
						}
					}
				});
	}

	private void startSignInIntent() {
		startActivityForResult(mGoogleSignInClient.getSignInIntent(), RC_SIGN_IN);
	}

	private void onConnected(GoogleSignInAccount googleSignInAccount) {
		Log.d(TAG, "onConnected(): connected to Google APIs");
		mGamesClient = Games.getGamesClient(this, googleSignInAccount);
        mGamesClient.setViewForPopups(findViewById(android.R.id.content));
        mGamesClient.setGravityForPopups(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
		mAchievementsClient = Games.getAchievementsClient(this, googleSignInAccount);
		mLeaderboardsClient = Games.getLeaderboardsClient(this, googleSignInAccount);
		mEmailId = googleSignInAccount.getEmail();
		mName = googleSignInAccount.getId();
        achcount = 0;
        achLoaded = false;
        mAchievementsClient.load(true).addOnCompleteListener(new OnCompleteListener<AnnotatedData<AchievementBuffer>>() {
            @Override
            public void onComplete(@NonNull Task<AnnotatedData<AchievementBuffer>> task) {
                AchievementBuffer buff = task.getResult().get();
                Log.d("BUFF", "onComplete: ");
                int bufSize = buff.getCount();
                for (int i=0; i < bufSize; i++)  {
                    Achievement ach = buff.get(i);
                    String id = ach.getAchievementId();
                    if (ach.getState() == Achievement.STATE_UNLOCKED) {// the
                        // achievement
                        // ID string
                        achcount++;
                    }
                }
                buff.release();
                Toast.makeText(MainActivity.this,"Game data loaded..",Toast.LENGTH_LONG).show();
                achLoaded = true;
            }
        });

    }

	private void onDisconnected() {
		Log.d(TAG, "onDisconnected()");

		mAchievementsClient = null;
		mLeaderboardsClient = null;
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(
				WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		DisplayMetrics d = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(d);
		MobileAds.initialize(this, "ca-app-pub-5637966806573218~8399133922");
		m = new BallBounzAndroid(this);
		m.setMygamecallback(this);
		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.disableAudio = false;
		cfg.useAccelerometer = true;
		cfg.useCompass = true;
		achLoaded = false;
		setupads();
		RelativeLayout layout = new RelativeLayout(this);
		RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, d.heightPixels
						- d.heightPixels / 10);
		p.topMargin = d.heightPixels / 10;
		gameview = initializeForView(m, cfg);
		layout.addView(gameview, p);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		layout.addView(bannerAd, params);
        setContentView(layout);
/*		if (gameHelper == null) {
			gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
			gameHelper.enableDebugLog(true);
		}
		gameHelper.setup(this);*/
		// Create the client used to sign in to Google services.
		mGoogleSignInClient = GoogleSignIn.getClient(this,
				new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN).requestEmail().requestId().build());
		if (Build.VERSION.SDK_INT >= 23)
		{
			List<String> permissionsNeeded = new ArrayList<String>();
			final List<String> permissionsList = new ArrayList<String>();
			if (!addPermission(permissionsList, Manifest.permission.INTERNET))
				permissionsNeeded.add("Use Internet");
			if (!addPermission(permissionsList,
					Manifest.permission.ACCESS_NETWORK_STATE))
				permissionsNeeded.add("Access Network State");
			if (!addPermission(permissionsList, Manifest.permission.VIBRATE))
				permissionsNeeded.add("Vibration");

			if (permissionsList.size() > 0) {
				requestPermissions(
						permissionsList.toArray(new String[permissionsList
								.size()]),
						REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
				return;
			}
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode,
			String[] permissions, int[] grantResults) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS: {
			Map<String, Integer> perms = new HashMap<String, Integer>();
			// Initial
			perms.put(Manifest.permission.VIBRATE,
					PackageManager.PERMISSION_GRANTED);
			perms.put(Manifest.permission.ACCESS_NETWORK_STATE,
					PackageManager.PERMISSION_GRANTED);
			perms.put(Manifest.permission.INTERNET,
					PackageManager.PERMISSION_GRANTED);
			// Fill with results
			for (int i = 0; i < permissions.length; i++)
				perms.put(permissions[i], grantResults[i]);
			// Check for ACCESS_FINE_LOCATION
			if (perms.get(Manifest.permission.VIBRATE) == PackageManager.PERMISSION_GRANTED
					&& perms.get(Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED
					&& perms.get(Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED) {
				// All Permissions Granted
				Toast.makeText(MainActivity.this, "Enjoy Playing!!",
						Toast.LENGTH_SHORT).show();
			} else {
				// Permission Denied
				finish();
				showMessageOKCancel("Required Permissions Denied");
			}
		}
			break;
		default:
			super.onRequestPermissionsResult(requestCode, permissions,
					grantResults);
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	public void setupads() {
		bannerAd = new AdView(this);
		bannerAd.setVisibility(View.INVISIBLE);
		bannerAd.setBackgroundColor(Color.BLACK);
		bannerAd.setAdUnitId(BANNER_AD_UNIT_ID);
		bannerAd.setAdSize(AdSize.SMART_BANNER);
		interAd = new InterstitialAd(this);
		interAd.setAdUnitId(INTERSTITIAL_AD_UNIT_ID);
		AdRequest.Builder builder = new AdRequest.Builder();
		AdRequest ad = builder.build();
		interAd.loadAd(ad);
	}

	@Override
	public void onStartActivityA() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, FirstActivity.class);
		startActivity(intent);
	}

	@Override
	public void onStartActivityB() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showBannerAd() {
		// TODO Auto-generated method stub
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				bannerAd.setVisibility(View.VISIBLE);
				AdRequest.Builder builder = new AdRequest.Builder();
				AdRequest ad = builder.build();
				bannerAd.loadAd(ad);
			}
		});
	}

	@Override
	public void hideBannerAd() {
		// TODO Auto-generated method stub
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				bannerAd.setVisibility(View.INVISIBLE);
			}
		});
	}
	
	

	@Override
	public boolean isWifiConnected() {
		// TODO Auto-generated method stub
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return (ni != null && ni.isConnected());
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onStop() {
		super.onStop();
	//	gameHelper.onStop();
	}

	@Override
	public void onActivityResult(int request, int response, Intent data) {
		super.onActivityResult(request, response, data);
	//	gameHelper.onActivityResult(request, response, data);
		if (request == RC_SIGN_IN) {
			Task<GoogleSignInAccount> task =
					GoogleSignIn.getSignedInAccountFromIntent(data);

			try {
				GoogleSignInAccount account = task.getResult(ApiException.class);
				onConnected(account);
			} catch (ApiException apiException) {
				String message = apiException.getMessage();
				if (message == null || message.isEmpty()) {
					message = "There was an issue with signin";
				}

				onDisconnected();

				new AlertDialog.Builder(this)
						.setMessage(message)
						.setNeutralButton(android.R.string.ok, null)
						.show();
			}
		}
	}

	@Override
	public boolean getSignedInGPGS() {
		//return gameHelper.isSignedIn();
		return isSignedIn();
	}

	@Override
	public void loginGPGS() {
		try {
			runOnUiThread(new Runnable() {
				public void run() {
					//gameHelper.beginUserInitiatedSignIn();
					startSignInIntent();
				}
			});
		} catch (final Exception ex) {
			handleException(ex,ex.getMessage());
		}
	}

	@Override
	public void submitScoreGPGS(final long score) {
/*		if (getSignedInGPGS()) {
			Games.Leaderboards.submitScore(gameHelper.getApiClient(),
					"CgkIkv6WzaUcEAIQGg", score);
		} else {
			loginGPGS();
		}*/
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mLeaderboardsClient.submitScore(getString(R.string.leaderboard_id), score);
			}
		});
	}

	@Override
	public void unlockAchievementGPGS(final String achievementId) {
/*		if (getSignedInGPGS()) {
			Games.Achievements.unlock(gameHelper.getApiClient(), achievementId);
		} else {
			loginGPGS();
		}*/
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mAchievementsClient.unlock(achievementId);
			}
		});
	}

	@Override
	public void getLeaderboardGPGS() {
/*		if (gameHelper.isSignedIn()) {
			startActivityForResult(
					Games.Leaderboards.getLeaderboardIntent(
							gameHelper.getApiClient(), "CgkIkv6WzaUcEAIQGg"),
					100);
		} else if (!gameHelper.isConnecting()) {
			loginGPGS();
		}*/
		if(isSignedIn())
		{
			mLeaderboardsClient.getAllLeaderboardsIntent()
					.addOnSuccessListener(new OnSuccessListener<Intent>() {
						@Override
						public void onSuccess(Intent intent) {
							startActivityForResult(intent, RC_UNUSED);
						}
					})
					.addOnFailureListener(new OnFailureListener() {
						@Override
						public void onFailure(@NonNull Exception e) {
							handleException(e, "Exception in loading Leaderboard");
						}
					});
		}
		else
		{
			signInSilently();
		}
	}

	@Override
	public void getAchievementsGPGS() {
/*		if (gameHelper.isSignedIn()) {
			startActivityForResult(
					Games.Achievements.getAchievementsIntent(gameHelper
							.getApiClient()), 101);
		} else if (!gameHelper.isConnecting()) {
			loginGPGS();
		}*/
		if(isSignedIn())
		{
			mAchievementsClient.getAchievementsIntent()
					.addOnSuccessListener(new OnSuccessListener<Intent>() {
						@Override
						public void onSuccess(Intent intent) {
							startActivityForResult(intent, RC_UNUSED);
						}
					})
					.addOnFailureListener(new OnFailureListener() {
						@Override
						public void onFailure(@NonNull Exception e) {
							handleException(e, "Exeption in loading achievements");
						}
					});
		}
		else
		{
			signInSilently();
		}
	}

	@Override
	public void onSignInFailed() {
	}

	@Override
	public void onSignInSucceeded() {
	}

	@Override
	public void showInterstitialAd(final Runnable then) {
		// TODO Auto-generated method stub
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (then != null) {
					interAd.setAdListener(new AdListener() {
						@Override
						public void onAdClosed() {
							Gdx.app.postRunnable(then);
							AdRequest.Builder builder = new AdRequest.Builder();
							AdRequest ad = builder.build();
							interAd.loadAd(ad);
						}
					});
				}
				interAd.show();
			}
		});
	}

	@Override
	public boolean checkForFaceBook() {
		// TODO Auto-generated method stub
		try {
			this.getPackageManager().getPackageInfo("com.facebook.katana", 0);
			return true;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	private boolean addPermission(List<String> permissionsList,
			String permission) {
		if (Build.VERSION.SDK_INT >= 23) {
			if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
				permissionsList.add(permission);
				// Check for Rationale Option
				if (!shouldShowRequestPermissionRationale(permission))
					return false;
			}
		}
		return true;
	}

	private void showMessageOKCancel(String message) {
		new AlertDialog.Builder(MainActivity.this).setMessage(message).create()
				.show();
	}

	private void handleException(Exception e, String details) {
		int status = 0;

		if (e instanceof ApiException) {
			ApiException apiException = (ApiException) e;
			status = apiException.getStatusCode();
		}

		String message = details;

		new AlertDialog.Builder(MainActivity.this)
				.setMessage(message)
				.setNeutralButton(android.R.string.ok, null)
				.show();
	}

	@Override
	public int loadAchievementsDecideLevel() {
		// TODO Auto-generated method stub
/*		boolean fullLoad = false;
		long waitTime = 60L;
		PendingResult<LoadAchievementsResult> p = Games.Achievements.load(
				gameHelper.getApiClient(), fullLoad);
		Achievements.LoadAchievementsResult r = p.await(waitTime,
				TimeUnit.SECONDS);
		int status = r.getStatus().getStatusCode();
		if (status != GamesStatusCodes.STATUS_OK) {
			r.release();
			return -1; // Error Occurred
		}
		// process the loaded achievements
		AchievementBuffer buf = r.getAchievements();
		int bufSize = buf.getCount();
		//int count = 0;
		for (int i = 0; i < bufSize; i++) {
			Achievement ach = buf.get(i);
			// here you now have access to the achievement's data
			if (ach.getState() == Achievement.STATE_UNLOCKED) {// the
																// achievement
																// ID string
				count++;
			}
		}
		buf.close();
		r.release();*/
		if (achcount == 0)
			return 0;
		else
			return achcount;
	}

	@Override
	public void getPackageNameForFeedback() {
		// TODO Auto-generated method stub
		final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
		try {
		    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
		} catch (android.content.ActivityNotFoundException anfe) {
		    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
		}
	}

	@Override
	public boolean isBannerShowing() {
		// TODO Auto-generated method stub
		return bannerAd.isShown();
	}

    @Override
    public boolean areAchievementsLoaded() {
        return achLoaded;
    }

    @Override
    public void showInstructions() {
        Intent intent = new Intent(this, InstructionsVideoActivity.class);
        startActivity(intent);
    }

    @Override
    public void updateInstalled(boolean firstTimeInstalled) {
        SharedPreferences.Editor editor = getSharedPreferences("install", MODE_PRIVATE).edit();
        editor.putBoolean("firstTime", firstTimeInstalled);
        editor.apply();
    }

    @Override
    public boolean checkFirstTimeInstalled() {
        SharedPreferences prefs = getSharedPreferences("install", MODE_PRIVATE);
        boolean firstTime = prefs.getBoolean("firstTime", true);
        if(firstTime)
            return true;
        else
            return false;
    }

	@Override
	public String getEmailId() {
		return mEmailId;
	}

	@Override
	public String getName() {
		return mName;
	}

	@Override
	public void showNameEmailId(String email, String id) {
		// Uncomment this for testing only
		/*final String message = "Welcome "+email+"!!\n"+"Your Gamer ID: "+id;
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
			}
		});
*/
	}
}