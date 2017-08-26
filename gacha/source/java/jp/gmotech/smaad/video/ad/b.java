package jp.gmotech.smaad.video.ad;

public interface b {
    void onSmaAdEndcardClosed();

    void onSmaAdVideoComplete(String str);

    void onSmaAdVideoError(int i);

    void onSmaAdVideoInitEnd();

    void onSmaAdVideoInitError(int i);

    void onSmaAdVideoReady();

    void onSmaAdVideoStart();

    void onSmaAdVideoStop();
}
