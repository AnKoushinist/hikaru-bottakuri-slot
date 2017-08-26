package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.squareup.picasso.Picasso.LoadedFrom;

class ImageViewAction extends Action<ImageView> {
    Callback callback;

    ImageViewAction(Picasso picasso, ImageView imageView, Request request, boolean z, boolean z2, int i, Drawable drawable, String str, Object obj, Callback callback) {
        super(picasso, imageView, request, z, z2, i, drawable, str, obj);
        this.callback = callback;
    }

    public void complete(Bitmap bitmap, LoadedFrom loadedFrom) {
        if (bitmap == null) {
            throw new AssertionError(String.format("Attempted to complete action with no result!\n%s", new Object[]{this}));
        }
        ImageView imageView = (ImageView) this.target.get();
        if (imageView != null) {
            Bitmap bitmap2 = bitmap;
            LoadedFrom loadedFrom2 = loadedFrom;
            PicassoDrawable.setBitmap(imageView, this.picasso.context, bitmap2, loadedFrom2, this.noFade, this.picasso.indicatorsEnabled);
            if (this.callback != null) {
                this.callback.onSuccess();
            }
        }
    }

    public void error() {
        ImageView imageView = (ImageView) this.target.get();
        if (imageView != null) {
            if (this.errorResId != 0) {
                imageView.setImageResource(this.errorResId);
            } else if (this.errorDrawable != null) {
                imageView.setImageDrawable(this.errorDrawable);
            }
            if (this.callback != null) {
                this.callback.onError();
            }
        }
    }

    void cancel() {
        super.cancel();
        if (this.callback != null) {
            this.callback = null;
        }
    }
}
