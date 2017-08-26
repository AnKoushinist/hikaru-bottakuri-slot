package com.igaworks.adbrix.cpe.activitydialog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.m;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.igaworks.adbrix.cpe.CPECompletionHandler;
import com.igaworks.adbrix.util.CPEConstant;
import com.igaworks.impl.InternalAction;
import com.igaworks.util.CommonHelper;
import com.igaworks.util.image.ImageCacheFactory;
import com.igaworks.util.image.ImageDownloadAsyncCallback;

public final class PlaceSlideFragment extends m {
    private int campaignKey;
    private String imageUrl;
    private boolean isFullScreen = false;
    private int position;

    public static PlaceSlideFragment newInstance(String str, int i, int i2, boolean z) {
        PlaceSlideFragment placeSlideFragment = new PlaceSlideFragment();
        Bundle bundle = new Bundle();
        bundle.putString("imageUrl", str);
        bundle.putInt("campaignKey", i);
        bundle.putInt("position", i2);
        bundle.putBoolean("isFullScreen", z);
        placeSlideFragment.setArguments(bundle);
        return placeSlideFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.imageUrl = getArguments().getString("imageUrl");
        this.campaignKey = getArguments().getInt("campaignKey");
        this.position = getArguments().getInt("position");
        this.isFullScreen = getArguments().getBoolean("isFullScreen", false);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View linearLayout = new LinearLayout(getActivity());
        linearLayout.setLayoutParams(new LayoutParams(-1, -1));
        final View imageView = new ImageView(getActivity());
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
        ScaleType scaleType = ScaleType.FIT_XY;
        if (this.isFullScreen) {
            scaleType = ScaleType.FIT_CENTER;
        }
        try {
            imageView.setScaleType(scaleType);
            imageView.setLayoutParams(layoutParams);
            int convertPixelToDP = CPEConstant.convertPixelToDP(getActivity(), 1, true);
            imageView.setPadding(convertPixelToDP, convertPixelToDP, convertPixelToDP, convertPixelToDP);
            imageView.setBackgroundColor(-16777216);
            if (CommonHelper.CheckPermissionForCommonSDK(getActivity())) {
                final View view = imageView;
                CPECompletionHandler.getImageDownloader(getActivity()).download(this.imageUrl, imageView, null, null, new ImageDownloadAsyncCallback(this.imageUrl, imageView, ImageCacheFactory.getInstance().get("imagecache"), null) {
                    public void onResultCustom(Bitmap bitmap) {
                        try {
                            if (PlaceDetailsFragment.pdFragment != null) {
                                PlaceDetailsFragment.pdFragment.addUsingBitmap(bitmap);
                            }
                            view.setImageBitmap(bitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                    public void run() {
                        final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL(PlaceSlideFragment.this.imageUrl);
                        Handler handler = new Handler(PlaceSlideFragment.this.getActivity().getMainLooper());
                        final ImageView imageView = imageView;
                        handler.post(new Runnable() {
                            public void run() {
                                try {
                                    if (PlaceDetailsFragment.pdFragment != null) {
                                        PlaceDetailsFragment.pdFragment.addUsingBitmap(bitmapFromURL);
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
            if (this.isFullScreen) {
                imageView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        try {
                            if (FullScreenSlider.slider != null) {
                                FullScreenSlider.slider.finish();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                imageView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        try {
                            Intent intent = new Intent(PlaceSlideFragment.this.getActivity(), FullScreenSlider.class);
                            intent.putExtra("campaignKey", PlaceSlideFragment.this.campaignKey);
                            intent.putExtra("position", PlaceSlideFragment.this.position);
                            PlaceSlideFragment.this.startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            linearLayout.setGravity(17);
            linearLayout.addView(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return linearLayout;
    }

    public void onDestroyView() {
        super.onDestroyView();
    }
}
