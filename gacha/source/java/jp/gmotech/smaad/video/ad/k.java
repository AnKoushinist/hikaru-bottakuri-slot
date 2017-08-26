package jp.gmotech.smaad.video.ad;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.JsResult;
import jp.gmotech.smaad.video.ad.b.a;

class k implements OnClickListener {
    final /* synthetic */ JsResult a;
    final /* synthetic */ j b;

    k(j jVar, JsResult jsResult) {
        this.b = jVar;
        this.a = jsResult;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        a.a("SmaAdVideoActivity", "onclick");
        this.a.confirm();
    }
}
