package com.inoek.apps.games.ballbounzreloaded;

public interface AdsController {
	public void showBannerAd();
	public void showInterstitialAd (Runnable then);
	public void hideBannerAd();
	public boolean isWifiConnected();
	public boolean checkForFaceBook();
	public boolean getSignedInGPGS();
	public void loginGPGS();
	public void submitScoreGPGS(long score);
	public void unlockAchievementGPGS(String achievementId);
	public void getLeaderboardGPGS();
	public void getAchievementsGPGS();
	public int loadAchievementsDecideLevel();
	public void getPackageNameForFeedback();
	public boolean isBannerShowing();
	public boolean areAchievementsLoaded();
	public void showInstructions();
	public void updateInstalled(boolean firstTimeInstalled);
	public boolean checkFirstTimeInstalled();
	public String getEmailId();
	public String getName();
	public void showNameEmailId(String email, String id);
}
