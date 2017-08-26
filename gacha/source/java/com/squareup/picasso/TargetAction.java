package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.squareup.picasso.Picasso.LoadedFrom;

final class TargetAction extends Action<Target> {
    TargetAction(Picasso picasso, Target target, Request request, boolean z, int i, Drawable drawable, String str, Object obj) {
        super(picasso, target, request, z, false, i, drawable, str, obj);
    }

    void complete(Bitmap bitmap, LoadedFrom loadedFrom) {
        if (bitmap == null) {
            throw new AssertionError(String.format("Attempted to complete action with no result!\n%s", new Object[]{this}));
        }
        Target target = (Target) getTarget();
        if (target != null) {
            target.onBitmapLoaded(bitmap, loadedFrom);
            if (bitmap.isRecycled()) {
                throw new IllegalStateException("Target callback must not recycle bitmap!");
            }
        }
    }

    void error() {
        Target target = (Target) getTarget();
        if (target == null) {
            return;
        }
        if (this.errorResId != 0) {
            target.onBitmapFailed(this.picasso.context.getResources().getDrawable(this.errorResId));
        } else {
            target.onBitmapFailed(this.errorDrawable);
        }
    }
}
