package com.squareup.picasso;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Video.Thumbnails;
import com.applovin.sdk.AppLovinEventTypes;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.RequestHandler.Result;
import com.unity3d.ads.adunit.AdUnitActivity;

class MediaStoreRequestHandler extends ContentStreamRequestHandler {
    private static final String[] CONTENT_ORIENTATION = new String[]{AdUnitActivity.EXTRA_ORIENTATION};

    enum PicassoKind {
        MICRO(3, 96, 96),
        MINI(1, 512, 384),
        FULL(2, -1, -1);
        
        final int androidKind;
        final int height;
        final int width;

        private PicassoKind(int i, int i2, int i3) {
            this.androidKind = i;
            this.width = i2;
            this.height = i3;
        }
    }

    MediaStoreRequestHandler(Context context) {
        super(context);
    }

    public boolean canHandleRequest(Request request) {
        Uri uri = request.uri;
        return AppLovinEventTypes.USER_VIEWED_CONTENT.equals(uri.getScheme()) && "media".equals(uri.getAuthority());
    }

    public Result load(Request request) {
        int i = 1;
        ContentResolver contentResolver = this.context.getContentResolver();
        int exifOrientation = getExifOrientation(contentResolver, request.uri);
        String type = contentResolver.getType(request.uri);
        int i2 = (type == null || !type.startsWith("video/")) ? 0 : 1;
        if (request.hasSize()) {
            PicassoKind picassoKind = getPicassoKind(request.targetWidth, request.targetHeight);
            if (i2 == 0 && picassoKind == PicassoKind.FULL) {
                return new Result(decodeContentStream(request), LoadedFrom.DISK, exifOrientation);
            }
            Bitmap thumbnail;
            long parseId = ContentUris.parseId(request.uri);
            Options createBitmapOptions = RequestHandler.createBitmapOptions(request);
            createBitmapOptions.inJustDecodeBounds = true;
            RequestHandler.calculateInSampleSize(request.targetWidth, request.targetHeight, picassoKind.width, picassoKind.height, createBitmapOptions, request);
            if (i2 != 0) {
                if (picassoKind != PicassoKind.FULL) {
                    i = picassoKind.androidKind;
                }
                thumbnail = Thumbnails.getThumbnail(contentResolver, parseId, i, createBitmapOptions);
            } else {
                thumbnail = Images.Thumbnails.getThumbnail(contentResolver, parseId, picassoKind.androidKind, createBitmapOptions);
            }
            if (thumbnail != null) {
                return new Result(thumbnail, LoadedFrom.DISK, exifOrientation);
            }
        }
        return new Result(decodeContentStream(request), LoadedFrom.DISK, exifOrientation);
    }

    static PicassoKind getPicassoKind(int i, int i2) {
        if (i <= PicassoKind.MICRO.width && i2 <= PicassoKind.MICRO.height) {
            return PicassoKind.MICRO;
        }
        if (i > PicassoKind.MINI.width || i2 > PicassoKind.MINI.height) {
            return PicassoKind.FULL;
        }
        return PicassoKind.MINI;
    }

    static int getExifOrientation(ContentResolver contentResolver, Uri uri) {
        Cursor query;
        Cursor cursor;
        Throwable th;
        try {
            query = contentResolver.query(uri, CONTENT_ORIENTATION, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        int i = query.getInt(0);
                        if (query == null) {
                            return i;
                        }
                        query.close();
                        return i;
                    }
                } catch (RuntimeException e) {
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    return 0;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            return 0;
        } catch (RuntimeException e2) {
            cursor = null;
            if (cursor != null) {
                cursor.close();
            }
            return 0;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }
}
