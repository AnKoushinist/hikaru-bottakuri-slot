package com.squareup.picasso;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.RequestHandler.Result;
import java.io.InputStream;

class AssetRequestHandler extends RequestHandler {
    protected static final String ANDROID_ASSET = "android_asset";
    private static final int ASSET_PREFIX_LENGTH = "file:///android_asset/".length();
    private final AssetManager assetManager;

    public AssetRequestHandler(Context context) {
        this.assetManager = context.getAssets();
    }

    public boolean canHandleRequest(Request request) {
        Uri uri = request.uri;
        if ("file".equals(uri.getScheme()) && !uri.getPathSegments().isEmpty() && ANDROID_ASSET.equals(uri.getPathSegments().get(0))) {
            return true;
        }
        return false;
    }

    public Result load(Request request) {
        return new Result(decodeAsset(request, request.uri.toString().substring(ASSET_PREFIX_LENGTH)), LoadedFrom.DISK);
    }

    Bitmap decodeAsset(Request request, String str) {
        InputStream inputStream = null;
        Options createBitmapOptions = RequestHandler.createBitmapOptions(request);
        if (RequestHandler.requiresInSampleSize(createBitmapOptions)) {
            try {
                inputStream = this.assetManager.open(str);
                BitmapFactory.decodeStream(inputStream, null, createBitmapOptions);
                RequestHandler.calculateInSampleSize(request.targetWidth, request.targetHeight, createBitmapOptions, request);
            } finally {
                Utils.closeQuietly(inputStream);
            }
        }
        inputStream = this.assetManager.open(str);
        try {
            Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, null, createBitmapOptions);
            return decodeStream;
        } finally {
            Utils.closeQuietly(inputStream);
        }
    }
}
