package jp.tjkapp.adfurikunsdk.moviereward;

import android.app.Activity;
import android.os.Handler;
import java.util.ArrayList;
import java.util.LinkedList;
import jp.tjkapp.adfurikunsdk.moviereward.AdnetworkWorker.AdnetworkWorkerListener;

interface IMediator {
    void destroy();

    void init(Activity activity, String str, String str2, Handler handler, ArrayList<AdnetworkWorker> arrayList, LinkedList<AdnetworkWorker> linkedList, AdnetworkWorkerListener adnetworkWorkerListener, MovieMediater movieMediater);

    void setAdInfo(AdInfo adInfo);

    void setAdfurikunMovieListener(AdfurikunMovieListener adfurikunMovieListener);

    void setNeedNotify(boolean z);

    void start();

    void stop();
}
