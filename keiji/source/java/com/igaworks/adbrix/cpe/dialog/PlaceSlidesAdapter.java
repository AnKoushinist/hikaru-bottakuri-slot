package com.igaworks.adbrix.cpe.dialog;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ac;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.igaworks.adbrix.cpe.CPECompletionHandler;
import com.igaworks.adbrix.util.CPEConstant;
import com.igaworks.impl.InternalAction;
import com.igaworks.util.CommonHelper;
import com.igaworks.util.image.ImageCacheFactory;
import com.igaworks.util.image.ImageDownloadAsyncCallback;
import java.util.List;

public class PlaceSlidesAdapter extends ac {
    private Activity activity;
    private int campaignKey;
    private List<String> imageUrlList;
    private boolean isFullscreen;

    public PlaceSlidesAdapter(Activity activity, List<String> list, int i, boolean z) {
        this.imageUrlList = list;
        this.activity = activity;
        this.isFullscreen = z;
        this.campaignKey = i;
    }

    public int getCount() {
        return this.imageUrlList.size();
    }

    public Object instantiateItem(View view, final int i) {
        final View imageView = new ImageView(this.activity);
        imageView.setLayoutParams(new LayoutParams(-1, -1));
        int convertPixelToDP = CPEConstant.convertPixelToDP(this.activity, 1, true);
        imageView.setPadding(convertPixelToDP, convertPixelToDP, convertPixelToDP, convertPixelToDP);
        imageView.setBackgroundColor(-16777216);
        if (CommonHelper.CheckPermissionForCommonSDK(this.activity)) {
            CPECompletionHandler.getImageDownloader(this.activity).download((String) this.imageUrlList.get(i), null, null, null, new ImageDownloadAsyncCallback((String) this.imageUrlList.get(i), null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    if (PlaceDetailsLayout.pdLayout != null) {
                        PlaceDetailsLayout.pdLayout.addUsingBitmap(bitmap);
                    }
                    imageView.setImageBitmap(bitmap);
                }
            });
        } else {
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL((String) PlaceSlidesAdapter.this.imageUrlList.get(i));
                    Handler handler = new Handler(PlaceSlidesAdapter.this.activity.getMainLooper());
                    final ImageView imageView = imageView;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                if (PlaceDetailsLayout.pdLayout != null) {
                                    PlaceDetailsLayout.pdLayout.addUsingBitmap(bitmapFromURL);
                                }
                                imageView.setImageBitmap(bitmapFromURL);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        ((ViewPager) view).addView(imageView, 0);
        if (this.isFullscreen) {
            imageView.setScaleType(ScaleType.FIT_CENTER);
            imageView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (FullScreenSlider.slider != null) {
                        FullScreenSlider.slider.dismiss();
                    }
                }
            });
        } else {
            imageView.setScaleType(ScaleType.FIT_XY);
            imageView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    new FullScreenSlider(PlaceSlidesAdapter.this.activity, PlaceSlidesAdapter.this.activity, PlaceSlidesAdapter.this.campaignKey, 0).show();
                }
            });
        }
        return imageView;
    }

    public void destroyItem(View view, int i, Object obj) {
        ((ViewPager) view).removeView((View) obj);
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == ((View) obj);
    }

    public Parcelable saveState() {
        return null;
    }
}
