package com.squareup.picasso;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import com.applovin.sdk.AppLovinEventTypes;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.RequestHandler.Result;
import java.io.InputStream;

class ContentStreamRequestHandler extends RequestHandler {
    final Context context;

    ContentStreamRequestHandler(Context context) {
        this.context = context;
    }

    public boolean canHandleRequest(Request request) {
        return AppLovinEventTypes.USER_VIEWED_CONTENT.equals(request.uri.getScheme());
    }

    public Result load(Request request) {
        return new Result(decodeContentStream(request), LoadedFrom.DISK);
    }

    protected Bitmap decodeContentStream(Request request) {
        InputStream inputStream = null;
        ContentResolver contentResolver = this.context.getContentResolver();
        Options createBitmapOptions = RequestHandler.createBitmapOptions(request);
        if (RequestHandler.requiresInSampleSize(createBitmapOptions)) {
            try {
                inputStream = contentResolver.openInputStream(request.uri);
                BitmapFactory.decodeStream(inputStream, null, createBitmapOptions);
                RequestHandler.calculateInSampleSize(request.targetWidth, request.targetHeight, createBitmapOptions, request);
            } finally {
                Utils.closeQuietly(inputStream);
            }
        }
        InputStream openInputStream = contentResolver.openInputStream(request.uri);
        try {
            Bitmap decodeStream = BitmapFactory.decodeStream(openInputStream, null, createBitmapOptions);
            return decodeStream;
        } finally {
            Utils.closeQuietly(openInputStream);
        }
    }
}
