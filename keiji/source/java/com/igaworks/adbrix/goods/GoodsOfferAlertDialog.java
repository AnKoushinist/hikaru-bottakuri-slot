package com.igaworks.adbrix.goods;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.igaworks.adbrix.cpe.CPECompletionHandler;
import com.igaworks.adbrix.util.DialogUtil;
import com.igaworks.impl.InternalAction;
import com.igaworks.util.CommonHelper;
import com.igaworks.util.image.ImageCacheFactory;
import com.igaworks.util.image.ImageDownloadAsyncCallback;
import java.util.ArrayList;
import java.util.List;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.HttpResponseCode;
import twitter4j.TwitterResponse;

public class GoodsOfferAlertDialog extends Dialog {
    private String btnText;
    private List<Bitmap> imgList;
    private String infoText;
    private GoodsOfferCallbackListener listener;
    private int type;

    public GoodsOfferAlertDialog(Context context, int i, GoodsOfferCallbackListener goodsOfferCallbackListener) {
        super(context);
        requestWindowFeature(1);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.flags = 2;
        attributes.dimAmount = 0.7f;
        attributes.width = -1;
        attributes.height = -1;
        attributes.gravity = 17;
        getWindow().setAttributes(attributes);
        getWindow().getDecorView().setBackgroundColor(0);
        getWindow().setFormat(1);
        getWindow().addFlags(4096);
        getWindow().setFlags(1024, 1024);
        getWindow().setGravity(17);
        getWindow().setSoftInputMode(38);
        this.imgList = new ArrayList();
        this.listener = goodsOfferCallbackListener;
        this.type = i;
        switch (i) {
            case TwitterResponse.READ /*1*/:
                this.infoText = "\uc9c0\uae08 \uc54c\ub9bc\ucc3d\uc744 \ub2eb\uc73c\uc2dc\uba74<br/>\ubcf8 \uc774\ubca4\ud2b8\ub97c \ucc38\uc5ec\ud560 \uc218 \uc5c6\uac8c \ub429\ub2c8\ub2e4.<br/><br/>\ub2e8 \ud55c\ubc88\uc758 \uae30\ud68c!<br/>\uc9c0\uae08 \ubc14\ub85c \ucc38\uc5ec\ud558\uc2dc\uaca0\uc2b5\ub2c8\uae4c?";
                this.btnText = "\ucc38\uc5ec\ud558\uae30";
                break;
            case TwitterResponse.READ_WRITE /*2*/:
                this.infoText = "\uc9c0\uae08 \uc54c\ub9bc\ucc3d\uc744 \ub2eb\uc73c\uc2dc\uba74<br/>\uc774\ubca4\ud2b8 \ub2f9\ucca8\uc774 \ucde8\uc18c\ub429\ub2c8\ub2e4.<br/><br/>\uad50\ud658\ucfe0\ud3f0\uc744 \uc804\uc1a1\ubc1b\uc744<br/>\uc774\uba54\uc77c \uc8fc\uc18c\ub97c \uc785\ub825\ud558\uc2dc\uaca0\uc2b5\ub2c8\uae4c?";
                this.btnText = "\uc785\ub825\ud558\uae30";
                break;
        }
        View linearLayout = new LinearLayout(context);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        linearLayout.setGravity(17);
        linearLayout.addView(getRootView(context));
        setCancelable(false);
        setContentView(linearLayout, layoutParams);
    }

    @SuppressLint({"NewApi"})
    public View getRootView(Context context) {
        View frameLayout = new FrameLayout(context);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 558, true), DialogUtil.convertPixelToDP(context, 521, false));
        layoutParams.leftMargin = DialogUtil.convertPixelToDP(context, 20, true);
        frameLayout.setLayoutParams(layoutParams);
        frameLayout = new LinearLayout(context);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 540, true), DialogUtil.convertPixelToDP(context, HttpResponseCode.INTERNAL_SERVER_ERROR, false), 83));
        frameLayout.setOrientation(1);
        Drawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius((float) DialogUtil.convertPixelToDP(context, 20, true));
        gradientDrawable.setColor(-1);
        gradientDrawable.setSize(DialogUtil.convertPixelToDP(context, 540, true), DialogUtil.convertPixelToDP(context, HttpResponseCode.INTERNAL_SERVER_ERROR, false));
        frameLayout.setBackgroundDrawable(gradientDrawable);
        final View imageView = new ImageView(context);
        imageView.setLayoutParams(new FrameLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 61, true), DialogUtil.convertPixelToDP(context, 61, false), 53));
        imageView.setScaleType(ScaleType.FIT_XY);
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/btn_close_large.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/btn_close_large.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    GoodsOfferAlertDialog.this.imgList.add(bitmap);
                    imageView.setImageBitmap(bitmap);
                }
            });
        } else {
            final Context context2 = context;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/btn_close_large.png");
                    Handler handler = new Handler(context2.getMainLooper());
                    final ImageView imageView = imageView;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                GoodsOfferAlertDialog.this.imgList.add(bitmapFromURL);
                                imageView.setImageBitmap(bitmapFromURL);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        imageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                GoodsOfferAlertDialog.this.dismiss();
            }
        });
        frameLayout.addView(frameLayout);
        frameLayout.addView(imageView);
        setOfferMainContents(context, frameLayout);
        return frameLayout;
    }

    private void setOfferMainContents(Context context, LinearLayout linearLayout) {
        linearLayout.setGravity(1);
        View linearLayout2 = new LinearLayout(context);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 540, false), DialogUtil.convertPixelToDP(context, R.styleable.AppCompatTheme_ratingBarStyleSmall, false)));
        linearLayout2.setGravity(17);
        final View imageView = new ImageView(context);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 84, false), DialogUtil.convertPixelToDP(context, 48, false)));
        imageView.setScaleType(ScaleType.FIT_XY);
        linearLayout2.addView(imageView);
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/title_04.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/title_04.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    GoodsOfferAlertDialog.this.imgList.add(bitmap);
                    imageView.setImageBitmap(bitmap);
                }
            });
        } else {
            final Context context2 = context;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/title_04.png");
                    Handler handler = new Handler(context2.getMainLooper());
                    final ImageView imageView = imageView;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                GoodsOfferAlertDialog.this.imgList.add(bitmapFromURL);
                                imageView.setImageBitmap(bitmapFromURL);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        linearLayout2 = new ImageView(context);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 540, false), DialogUtil.convertPixelToDP(context, 2, false)));
        linearLayout2.setImageDrawable(new ColorDrawable(Color.parseColor("#b8bbc2")));
        linearLayout2 = new ScrollView(context);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 540, false), DialogUtil.convertPixelToDP(context, 250, false)));
        linearLayout2.setFillViewport(true);
        linearLayout2.setFadingEdgeLength(0);
        View linearLayout3 = new LinearLayout(context);
        linearLayout3.setLayoutParams(new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 540, false), -1));
        linearLayout3.setGravity(17);
        View textView = new TextView(context);
        textView.setLayoutParams(new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 540, false), -2));
        textView.setGravity(17);
        textView.setTypeface(null, 1);
        textView.setTextColor(Color.parseColor("#3c3f45"));
        DialogUtil.setTextViewSize(context, textView, 28);
        textView.setText(Html.fromHtml(this.infoText));
        linearLayout3.addView(textView);
        linearLayout2.addView(linearLayout3);
        linearLayout2 = new ImageView(context);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, HttpResponseCode.GATEWAY_TIMEOUT, false), DialogUtil.convertPixelToDP(context, 2, false)));
        linearLayout2.setImageDrawable(new ColorDrawable(Color.parseColor("#b8bbc2")));
        linearLayout2 = new LinearLayout(context);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 540, false), DialogUtil.convertPixelToDP(context, 140, false)));
        linearLayout2.setGravity(17);
        linearLayout2.setOrientation(0);
        imageView = new Button(context);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 232, false), DialogUtil.convertPixelToDP(context, 90, false));
        layoutParams.rightMargin = DialogUtil.convertPixelToDP(context, 18, true);
        imageView.setLayoutParams(layoutParams);
        imageView.setGravity(17);
        imageView.setText(this.btnText);
        imageView.setTypeface(null, 1);
        imageView.setTextColor(-1);
        DialogUtil.setTextViewSize(context, imageView, 40);
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/btn_default_02.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/btn_default_02.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    GoodsOfferAlertDialog.this.imgList.add(bitmap);
                    imageView.setBackgroundDrawable(new BitmapDrawable(bitmap));
                }
            });
        } else {
            context2 = context;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/btn_default_02.png");
                    Handler handler = new Handler(context2.getMainLooper());
                    final Button button = imageView;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                GoodsOfferAlertDialog.this.imgList.add(bitmapFromURL);
                                button.setBackgroundDrawable(new BitmapDrawable(bitmapFromURL));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        imageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                GoodsOfferAlertDialog.this.dismiss();
                if (GoodsOfferAlertDialog.this.listener != null) {
                    GoodsOfferAlertDialog.this.listener.run(true);
                }
            }
        });
        final View button = new Button(context);
        button.setLayoutParams(new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 232, false), DialogUtil.convertPixelToDP(context, 90, false)));
        button.setGravity(17);
        button.setText("\ub2eb\uae30");
        button.setTypeface(null, 1);
        button.setTextColor(-1);
        DialogUtil.setTextViewSize(context, button, 40);
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/btn_default_02.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/btn_default_02.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    GoodsOfferAlertDialog.this.imgList.add(bitmap);
                    button.setBackgroundDrawable(new BitmapDrawable(bitmap));
                }
            });
        } else {
            context2 = context;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/btn_default_02.png");
                    Handler handler = new Handler(context2.getMainLooper());
                    final Button button = button;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                GoodsOfferAlertDialog.this.imgList.add(bitmapFromURL);
                                button.setBackgroundDrawable(new BitmapDrawable(bitmapFromURL));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                GoodsOfferAlertDialog.this.dismiss();
                if (GoodsOfferAlertDialog.this.listener != null) {
                    GoodsOfferAlertDialog.this.listener.run(false);
                }
            }
        });
        linearLayout2.addView(imageView);
        linearLayout2.addView(button);
        linearLayout.addView(linearLayout2);
        linearLayout.addView(linearLayout2);
        linearLayout.addView(linearLayout2);
        linearLayout.addView(linearLayout2);
        linearLayout.addView(linearLayout2);
    }
}
