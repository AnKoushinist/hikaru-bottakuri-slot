package com.squareup.picasso;

import android.graphics.Bitmap;
import com.squareup.picasso.Picasso.LoadedFrom;

class FetchAction extends Action<Object> {
    private final Object target = new Object();

    FetchAction(Picasso picasso, Request request, boolean z, String str, Object obj) {
        super(picasso, null, request, z, false, 0, null, str, obj);
    }

    void complete(Bitmap bitmap, LoadedFrom loadedFrom) {
    }

    public void error() {
    }

    Object getTarget() {
        return this.target;
    }
}
