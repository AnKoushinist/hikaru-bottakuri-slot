package com.squareup.picasso;

import android.content.Context;
import android.media.ExifInterface;
import android.net.Uri;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.RequestHandler.Result;
import twitter4j.TwitterResponse;

class FileRequestHandler extends ContentStreamRequestHandler {
    FileRequestHandler(Context context) {
        super(context);
    }

    public boolean canHandleRequest(Request request) {
        return "file".equals(request.uri.getScheme());
    }

    public Result load(Request request) {
        return new Result(decodeContentStream(request), LoadedFrom.DISK, getFileExifRotation(request.uri));
    }

    static int getFileExifRotation(Uri uri) {
        switch (new ExifInterface(uri.getPath()).getAttributeInt("Orientation", 1)) {
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                return 180;
            case AdInfo.BANNER_KIND_BANNER6 /*6*/:
                return 90;
            case AdInfo.BANNER_KIND_BANNER8 /*8*/:
                return 270;
            default:
                return 0;
        }
    }
}
