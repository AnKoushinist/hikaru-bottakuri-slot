package jp.tjkapp.adfurikunsdk.moviereward;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

class AdfurikunMovie {
    protected String a;
    protected Activity b;
    protected MovieMediater c = new MovieMediater(this.b, this.a, FileUtil.getUserAgent(this.b));
    protected LogUtil d;

    public AdfurikunMovie(String str, Activity activity) {
        this.a = str;
        this.b = activity;
        this.d = LogUtil.getInstance(activity.getApplicationContext());
    }

    public boolean isPrepared() {
        return !this.c.getPlayableList().isEmpty();
    }

    protected boolean a() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.b.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public boolean isTestMode() {
        return this.c.isTestMode();
    }

    public void onStart() {
        this.d.debug(Constants.TAG, "onStart()");
        this.c.start();
    }

    public void onResume() {
        this.d.debug(Constants.TAG, "onResume()");
        this.c.resume();
    }

    public void onPause() {
        this.d.debug(Constants.TAG, "onPause()");
        this.c.pause();
    }

    public void onStop() {
        this.d.debug(Constants.TAG, "onStop()");
        this.c.stop();
    }

    public void onDestroy() {
        this.d.debug(Constants.TAG, "onDestroy()");
        try {
            this.c.setAdfurikunMovieListener(null);
            this.c.destroy();
            this.c = null;
        } catch (Exception e) {
        }
    }
}
