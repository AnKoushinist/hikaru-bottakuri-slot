package jp.tjkapp.adfurikunsdk.moviereward;

import android.app.Activity;

public class AdfurikunMovieInter extends AdfurikunMovie {
    private AdfurikunMovieInterListener e;

    public /* bridge */ /* synthetic */ boolean isPrepared() {
        return super.isPrepared();
    }

    public /* bridge */ /* synthetic */ boolean isTestMode() {
        return super.isTestMode();
    }

    public /* bridge */ /* synthetic */ void onDestroy() {
        super.onDestroy();
    }

    public /* bridge */ /* synthetic */ void onPause() {
        super.onPause();
    }

    public /* bridge */ /* synthetic */ void onResume() {
        super.onResume();
    }

    public /* bridge */ /* synthetic */ void onStart() {
        super.onStart();
    }

    public /* bridge */ /* synthetic */ void onStop() {
        super.onStop();
    }

    public AdfurikunMovieInter(String str, Activity activity) {
        super(str, activity);
    }

    public void setAdfurikunMovieInterListener(AdfurikunMovieInterListener adfurikunMovieInterListener) {
        this.e = adfurikunMovieInterListener;
        this.c.setAdfurikunMovieListener(adfurikunMovieInterListener);
    }

    public synchronized void play() {
        if (this.c.mPrevPlaying) {
            this.d.detail_e(Constants.TAG, "Play\u306e\u9023\u7d9a\u5b9f\u884c\u306f\u7981\u6b62\uff01");
        } else {
            AdnetworkWorker adnetworkWorker = null;
            try {
                if (!isPrepared()) {
                    throw new IllegalStateException("\u52d5\u753b\u306e\u6e96\u5099\u304c\u3067\u304d\u3066\u3044\u307e\u305b\u3093\u3002");
                } else if (a()) {
                    this.c.mPrevPlaying = true;
                    adnetworkWorker = this.c.getPlayableWorker();
                    this.d.debug_i(Constants.TAG, "[" + this.a + "] \u518d\u751f\u958b\u59cb: " + adnetworkWorker.getAdnetworkKey());
                    adnetworkWorker.play(this.c);
                } else {
                    throw new IllegalStateException("\u30cd\u30c3\u30c8\u30ef\u30fc\u30af\u306b\u63a5\u7d9a\u3057\u3066\u3044\u307e\u305b\u3093\u3002");
                }
            } catch (Exception e) {
                this.d.debug_w(Constants.TAG, e.getMessage());
                this.c.mPrevPlaying = false;
                if (this.e != null) {
                    this.e.onFailedPlaying((MovieInterData) (adnetworkWorker != null ? adnetworkWorker.getMovieData() : new MovieData("Unknown", "Play error.")));
                }
            }
        }
    }
}
