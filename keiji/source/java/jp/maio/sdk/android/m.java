package jp.maio.sdk.android;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class m implements OnTouchListener {
    final /* synthetic */ l a;

    m(l lVar) {
        this.a = lVar;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return motionEvent.getAction() == 2;
    }
}
