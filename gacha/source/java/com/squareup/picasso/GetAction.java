package com.squareup.picasso;

import android.graphics.Bitmap;
import com.squareup.picasso.Picasso.LoadedFrom;

class GetAction extends Action<Void> {
    GetAction(Picasso picasso, Request request, boolean z, String str, Object obj) {
        super(picasso, null, request, z, false, 0, null, str, obj);
    }

    void complete(Bitmap bitmap, LoadedFrom loadedFrom) {
    }

    public void error() {
    }
}
