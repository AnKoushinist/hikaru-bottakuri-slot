package com.igaworks.adbrix.goods;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.igaworks.adbrix.core.ADBrixHttpManager;
import com.igaworks.adbrix.cpe.CPECompletionHandler;
import com.igaworks.adbrix.db.RealRewardDAO;
import com.igaworks.adbrix.util.ADBrixUtil;
import com.igaworks.adbrix.util.DialogUtil;
import com.igaworks.core.DeviceIDManger;
import com.igaworks.core.IgawLogger;
import com.igaworks.core.RequestParameter;
import com.igaworks.dao.tracking.TrackingActivitySQLiteDB;
import com.igaworks.impl.InternalAction;
import com.igaworks.util.CommonHelper;
import com.igaworks.util.image.ImageCacheFactory;
import com.igaworks.util.image.ImageDownloadAsyncCallback;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.HttpResponseCode;
import twitter4j.TwitterResponse;

public class GoodsOfferDialog extends Dialog implements OnDismissListener, GoodsOfferCallbackListener {
    private Context context;
    private List<Bitmap> imgList;
    private GoodsOfferModel model;

    public GoodsOfferDialog(Context context, GoodsOfferModel goodsOfferModel) {
        super(context);
        this.context = context;
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
        this.model = goodsOfferModel;
        try {
            View linearLayout = new LinearLayout(context);
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            linearLayout.setGravity(17);
            linearLayout.addView(getRootView(context, goodsOfferModel));
            setCancelable(false);
            setContentView(linearLayout, layoutParams);
        } catch (Exception e) {
            IgawLogger.Logging(context, "IGAW_QA", "GoodsOfferDialog - error : " + e.getMessage(), 1);
        }
        setOnDismissListener(this);
    }

    @SuppressLint({"NewApi"})
    public View getRootView(Context context, GoodsOfferModel goodsOfferModel) {
        View frameLayout = new FrameLayout(context);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 620, true), DialogUtil.convertPixelToDP(context, 821, false));
        layoutParams.leftMargin = DialogUtil.convertPixelToDP(context, 20, true);
        frameLayout.setLayoutParams(layoutParams);
        frameLayout = new LinearLayout(context);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 600, true), DialogUtil.convertPixelToDP(context, 800, false), 83));
        frameLayout.setOrientation(1);
        Drawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius((float) DialogUtil.convertPixelToDP(context, 20, true));
        gradientDrawable.setColor(-1);
        gradientDrawable.setSize(DialogUtil.convertPixelToDP(context, 600, true), DialogUtil.convertPixelToDP(context, 800, false));
        frameLayout.setBackgroundDrawable(gradientDrawable);
        final View imageView = new ImageView(context);
        imageView.setLayoutParams(new FrameLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 68, true), DialogUtil.convertPixelToDP(context, 68, false), 53));
        imageView.setScaleType(ScaleType.FIT_XY);
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/btn_close_large.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/btn_close_large.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    GoodsOfferDialog.this.imgList.add(bitmap);
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
                                GoodsOfferDialog.this.imgList.add(bitmapFromURL);
                                imageView.setImageBitmap(bitmapFromURL);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        switch (goodsOfferModel.getType()) {
            case TwitterResponse.READ /*1*/:
                addImpression(context);
                context2 = context;
                imageView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        GoodsOfferAlertDialog goodsOfferAlertDialog = new GoodsOfferAlertDialog(context2, 1, GoodsOfferDialog.this);
                        LayoutParams layoutParams = new LayoutParams();
                        layoutParams.copyFrom(goodsOfferAlertDialog.getWindow().getAttributes());
                        layoutParams.width = -1;
                        layoutParams.height = -2;
                        goodsOfferAlertDialog.getWindow().setAttributes(layoutParams);
                        goodsOfferAlertDialog.show();
                    }
                });
                break;
            case TwitterResponse.READ_WRITE /*2*/:
                context2 = context;
                imageView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        GoodsOfferAlertDialog goodsOfferAlertDialog = new GoodsOfferAlertDialog(context2, 2, GoodsOfferDialog.this);
                        LayoutParams layoutParams = new LayoutParams();
                        layoutParams.copyFrom(goodsOfferAlertDialog.getWindow().getAttributes());
                        layoutParams.width = -1;
                        layoutParams.height = -2;
                        goodsOfferAlertDialog.getWindow().setAttributes(layoutParams);
                        goodsOfferAlertDialog.show();
                    }
                });
                break;
            default:
                imageView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        GoodsOfferDialog.this.dismiss();
                    }
                });
                break;
        }
        frameLayout.addView(frameLayout);
        frameLayout.addView(imageView);
        setOfferMainContents(context, frameLayout, goodsOfferModel);
        Animation translateAnimation = new TranslateAnimation((float) (DialogUtil.convertPixelToDP(context, 720, true) * -1), 0.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(500);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator(new DecelerateInterpolator());
        translateAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
            }
        });
        frameLayout.startAnimation(translateAnimation);
        return frameLayout;
    }

    private void setOfferMainContents(Context context, LinearLayout linearLayout, GoodsOfferModel goodsOfferModel) {
        linearLayout.setGravity(1);
        View linearLayout2 = new LinearLayout(context);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 600, false), DialogUtil.convertPixelToDP(context, R.styleable.AppCompatTheme_ratingBarStyleSmall, false)));
        linearLayout2.setGravity(17);
        linearLayout2 = new ImageView(context);
        linearLayout2.setScaleType(ScaleType.FIT_XY);
        linearLayout2.addView(linearLayout2);
        linearLayout2 = new ImageView(context);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 600, false), DialogUtil.convertPixelToDP(context, 2, false)));
        linearLayout2.setImageDrawable(new ColorDrawable(Color.parseColor("#b8bbc2")));
        linearLayout2 = new FrameLayout(context);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 600, false), DialogUtil.convertPixelToDP(context, 390, false)));
        final View imageView = new ImageView(context);
        imageView.setLayoutParams(new FrameLayout.LayoutParams(-1, DialogUtil.convertPixelToDP(context, 390, false)));
        imageView.setScaleType(ScaleType.FIT_XY);
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download(goodsOfferModel.getMainImg(), null, null, null, new ImageDownloadAsyncCallback(goodsOfferModel.getMainImg(), null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    GoodsOfferDialog.this.imgList.add(bitmap);
                    imageView.setBackgroundDrawable(new BitmapDrawable(bitmap));
                }
            });
        } else {
            final GoodsOfferModel goodsOfferModel2 = goodsOfferModel;
            final Context context2 = context;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL(goodsOfferModel2.getMainImg());
                    Handler handler = new Handler(context2.getMainLooper());
                    final ImageView imageView = imageView;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                GoodsOfferDialog.this.imgList.add(bitmapFromURL);
                                imageView.setBackgroundDrawable(new BitmapDrawable(bitmapFromURL));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        linearLayout2.addView(imageView);
        View imageView2 = new ImageView(context);
        imageView2.setLayoutParams(new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 600, false), DialogUtil.convertPixelToDP(context, 2, false)));
        imageView2.setImageDrawable(new ColorDrawable(Color.parseColor("#b8bbc2")));
        View scrollView = new ScrollView(context);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 600, false), DialogUtil.convertPixelToDP(context, 160, false)));
        scrollView.setFillViewport(true);
        scrollView.setFadingEdgeLength(0);
        View linearLayout3 = new LinearLayout(context);
        linearLayout3.setLayoutParams(new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 600, false), -1));
        linearLayout3.setGravity(17);
        int convertPixelToDP = DialogUtil.convertPixelToDP(context, 30, false);
        linearLayout3.setPadding(0, convertPixelToDP, 0, convertPixelToDP);
        View textView = new TextView(context);
        textView.setLayoutParams(new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 600, false), -2));
        textView.setGravity(17);
        textView.setTypeface(null, 1);
        textView.setTextColor(Color.parseColor("#3c3f45"));
        DialogUtil.setTextViewSize(context, textView, 28);
        textView.setText(Html.fromHtml(goodsOfferModel.getMidText()));
        linearLayout3.addView(textView);
        scrollView.addView(linearLayout3);
        linearLayout3 = new ImageView(context);
        linearLayout3.setLayoutParams(new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 560, false), DialogUtil.convertPixelToDP(context, 2, false)));
        linearLayout3.setImageDrawable(new ColorDrawable(Color.parseColor("#b8bbc2")));
        textView = new LinearLayout(context);
        textView.setLayoutParams(new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 600, false), DialogUtil.convertPixelToDP(context, 140, false)));
        textView.setGravity(17);
        switch (goodsOfferModel.getType()) {
            case TwitterResponse.READ /*1*/:
                textView.addView(getOfferView(context, linearLayout2));
                break;
            case TwitterResponse.READ_WRITE /*2*/:
                textView.addView(getRedeemView(context, linearLayout2));
                break;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                textView.addView(getFailView(context, linearLayout2, linearLayout2));
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                textView.addView(getSuccessView(context, linearLayout2));
                break;
            default:
                textView.addView(getOfferView(context, linearLayout2));
                break;
        }
        linearLayout.addView(linearLayout2);
        linearLayout.addView(linearLayout2);
        linearLayout.addView(linearLayout2);
        linearLayout.addView(imageView2);
        linearLayout.addView(scrollView);
        linearLayout.addView(linearLayout3);
        linearLayout.addView(textView);
    }

    private View getOfferView(Context context, ImageView imageView) {
        final Context context2;
        final View button = new Button(context);
        button.setLayoutParams(new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 346, false), DialogUtil.convertPixelToDP(context, 90, false)));
        button.setGravity(17);
        button.setText("\ucc38\uc5ec\ud558\uae30");
        button.setTypeface(null, 1);
        button.setTextColor(-1);
        DialogUtil.setTextViewSize(context, button, 40);
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/btn_default_large.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/btn_default_large.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    GoodsOfferDialog.this.imgList.add(bitmap);
                    button.setBackgroundDrawable(new BitmapDrawable(bitmap));
                }
            });
        } else {
            context2 = context;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/btn_default_large.png");
                    Handler handler = new Handler(context2.getMainLooper());
                    final Button button = button;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                GoodsOfferDialog.this.imgList.add(bitmapFromURL);
                                button.setBackgroundDrawable(new BitmapDrawable(bitmapFromURL));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        imageView.setLayoutParams(new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 258, false), DialogUtil.convertPixelToDP(context, 48, false)));
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            final ImageView imageView2 = imageView;
            CPECompletionHandler.getImageDownloader(context).download(this.model.getTitleImg(), null, null, null, new ImageDownloadAsyncCallback(this.model.getTitleImg(), null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    GoodsOfferDialog.this.imgList.add(bitmap);
                    imageView2.setBackgroundDrawable(new BitmapDrawable(bitmap));
                }
            });
        } else {
            context2 = context;
            final ImageView imageView3 = imageView;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL(GoodsOfferDialog.this.model.getTitleImg());
                    Handler handler = new Handler(context2.getMainLooper());
                    final ImageView imageView = imageView3;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                GoodsOfferDialog.this.imgList.add(bitmapFromURL);
                                imageView.setBackgroundDrawable(new BitmapDrawable(bitmapFromURL));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        context2 = context;
        button.setOnClickListener(new OnClickListener() {
            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onClick(android.view.View r7) {
                /*
                r6 = this;
                r0 = r1;	 Catch:{ Exception -> 0x0078 }
                r2 = com.igaworks.core.RequestParameter.getATRequestParameter(r0);	 Catch:{ Exception -> 0x0078 }
                r0 = r2.getPersistantDemoInfo_v2();	 Catch:{ Exception -> 0x0078 }
                r4 = 0;
                if (r0 == 0) goto L_0x0017;
            L_0x000d:
                r3 = r0.iterator();	 Catch:{ Exception -> 0x0078 }
            L_0x0011:
                r0 = r3.hasNext();	 Catch:{ Exception -> 0x0078 }
                if (r0 != 0) goto L_0x0046;
            L_0x0017:
                r0 = com.igaworks.adbrix.goods.GoodsOfferDialog.this;	 Catch:{ Exception -> 0x0078 }
                r0 = r0.model;	 Catch:{ Exception -> 0x0078 }
                r0 = r0.isNoCondition();	 Catch:{ Exception -> 0x0078 }
                if (r0 == 0) goto L_0x005e;
            L_0x0023:
                r0 = r1;	 Catch:{ Exception -> 0x0078 }
                r0 = com.igaworks.adbrix.core.ADBrixHttpManager.getManager(r0);	 Catch:{ Exception -> 0x0078 }
                r1 = r1;	 Catch:{ Exception -> 0x0078 }
                r2 = r2.getAppkey();	 Catch:{ Exception -> 0x0078 }
                r3 = com.igaworks.adbrix.goods.GoodsOfferDialog.this;	 Catch:{ Exception -> 0x0078 }
                r3 = r3.model;	 Catch:{ Exception -> 0x0078 }
                r3 = r3.getRealRewardKey();	 Catch:{ Exception -> 0x0078 }
                r5 = r1;	 Catch:{ Exception -> 0x0078 }
                r5 = (android.app.Activity) r5;	 Catch:{ Exception -> 0x0078 }
                r0.startAndCompeteRealReward(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x0078 }
            L_0x0040:
                r0 = com.igaworks.adbrix.goods.GoodsOfferDialog.this;
                r0.dismiss();
            L_0x0045:
                return;
            L_0x0046:
                r0 = r3.next();	 Catch:{ Exception -> 0x0078 }
                r0 = (android.util.Pair) r0;	 Catch:{ Exception -> 0x0078 }
                r1 = r0.first;	 Catch:{ Exception -> 0x0078 }
                r1 = (java.lang.String) r1;	 Catch:{ Exception -> 0x0078 }
                r5 = "userId";
                r1 = r1.equals(r5);	 Catch:{ Exception -> 0x0078 }
                if (r1 == 0) goto L_0x0011;
            L_0x0058:
                r0 = r0.second;	 Catch:{ Exception -> 0x0078 }
                r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0078 }
                r4 = r0;
                goto L_0x0017;
            L_0x005e:
                r0 = r1;	 Catch:{ Exception -> 0x0078 }
                r0 = com.igaworks.adbrix.core.ADBrixHttpManager.getManager(r0);	 Catch:{ Exception -> 0x0078 }
                r1 = r1;	 Catch:{ Exception -> 0x0078 }
                r2 = r2.getAppkey();	 Catch:{ Exception -> 0x0078 }
                r3 = com.igaworks.adbrix.goods.GoodsOfferDialog.this;	 Catch:{ Exception -> 0x0078 }
                r3 = r3.model;	 Catch:{ Exception -> 0x0078 }
                r3 = r3.getRealRewardKey();	 Catch:{ Exception -> 0x0078 }
                r0.startRealReward(r1, r2, r3, r4);	 Catch:{ Exception -> 0x0078 }
                goto L_0x0040;
            L_0x0078:
                r0 = move-exception;
                r0.printStackTrace();	 Catch:{ all -> 0x0082 }
                r0 = com.igaworks.adbrix.goods.GoodsOfferDialog.this;
                r0.dismiss();
                goto L_0x0045;
            L_0x0082:
                r0 = move-exception;
                r1 = com.igaworks.adbrix.goods.GoodsOfferDialog.this;
                r1.dismiss();
                throw r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.igaworks.adbrix.goods.GoodsOfferDialog.13.onClick(android.view.View):void");
            }
        });
        return button;
    }

    private View getRedeemView(Context context, ImageView imageView) {
        final Context context2;
        View linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, DialogUtil.convertPixelToDP(context, 86, false)));
        linearLayout.setOrientation(0);
        final View editText = new EditText(context);
        editText.setLayoutParams(new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 434, false), DialogUtil.convertPixelToDP(context, 86, false)));
        int convertPixelToDP = DialogUtil.convertPixelToDP(context, 20, true);
        editText.setPadding(convertPixelToDP, convertPixelToDP, convertPixelToDP, convertPixelToDP);
        editText.setHint("\uc774\uba54\uc77c\uc744 \uc785\ub825\ud558\uc138\uc694.");
        editText.setTypeface(null, 1);
        editText.setTextColor(Color.parseColor("#3c3f45"));
        editText.setSingleLine();
        editText.setInputType(33);
        editText.setFilters(new InputFilter[]{new LengthFilter(96)});
        DialogUtil.setTextViewSize(context, editText, 30);
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/img_email_input_large.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/img_email_input_large.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    GoodsOfferDialog.this.imgList.add(bitmap);
                    editText.setBackgroundDrawable(new BitmapDrawable(bitmap));
                }
            });
        } else {
            context2 = context;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/img_email_input_large.png");
                    Handler handler = new Handler(context2.getMainLooper());
                    final EditText editText = editText;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                GoodsOfferDialog.this.imgList.add(bitmapFromURL);
                                editText.setBackgroundDrawable(new BitmapDrawable(bitmapFromURL));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        linearLayout = new Button(context);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 126, false), DialogUtil.convertPixelToDP(context, 86, false)));
        linearLayout.setGravity(17);
        linearLayout.setText("\uc804\uc1a1");
        linearLayout.setTypeface(null, 1);
        linearLayout.setTextColor(-1);
        DialogUtil.setTextViewSize(context, linearLayout, 40);
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/btn_send_large.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/btn_send_large.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    GoodsOfferDialog.this.imgList.add(bitmap);
                    linearLayout.setBackgroundDrawable(new BitmapDrawable(bitmap));
                }
            });
        } else {
            context2 = context;
            final View view = linearLayout;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/btn_send_large.png");
                    Handler handler = new Handler(context2.getMainLooper());
                    final Button button = view;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                GoodsOfferDialog.this.imgList.add(bitmapFromURL);
                                button.setBackgroundDrawable(new BitmapDrawable(bitmapFromURL));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        linearLayout.addView(editText);
        linearLayout.addView(linearLayout);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 366, false), DialogUtil.convertPixelToDP(context, 48, false)));
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            final ImageView imageView2 = imageView;
            CPECompletionHandler.getImageDownloader(context).download(this.model.getTitleImg(), null, null, null, new ImageDownloadAsyncCallback(this.model.getTitleImg(), null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    GoodsOfferDialog.this.imgList.add(bitmap);
                    imageView2.setBackgroundDrawable(new BitmapDrawable(bitmap));
                }
            });
        } else {
            context2 = context;
            final ImageView imageView3 = imageView;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL(GoodsOfferDialog.this.model.getTitleImg());
                    Handler handler = new Handler(context2.getMainLooper());
                    final ImageView imageView = imageView3;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                GoodsOfferDialog.this.imgList.add(bitmapFromURL);
                                imageView.setBackgroundDrawable(new BitmapDrawable(bitmapFromURL));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        context2 = context;
        linearLayout.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                try {
                    RequestParameter aTRequestParameter = RequestParameter.getATRequestParameter(context2);
                    String editable = editText.getText().toString();
                    if (ADBrixUtil.validateEmailFormat(editable)) {
                        ADBrixHttpManager.getManager(context2).redeemRealReward(context2, aTRequestParameter.getAppkey(), GoodsOfferDialog.this.model.getRealRewardKey(), RealRewardDAO.getInstance().getSessionNo(context2, GoodsOfferDialog.this.model.getRealRewardKey()), editable);
                        GoodsOfferDialog.this.dismiss();
                        return;
                    }
                    Toast.makeText(context2, "\uc785\ub825\ud558\uc2e0 \uc774\uba54\uc77c \uc8fc\uc18c\ub294 \uc62c\ubc14\ub978 \ud615\uc2dd\uc774 \uc544\ub2d9\ub2c8\ub2e4. \ud655\uc778 \ud6c4 \ub2e4\uc2dc \uc2dc\ub3c4\ud574 \uc8fc\uc138\uc694.", 0).show();
                } catch (Exception e) {
                    IgawLogger.Logging(context2, "IGAW_QA", "GoodsOfferDialog sendBtn - error : " + e.getMessage(), 1);
                }
            }
        });
        return linearLayout;
    }

    private View getFailView(Context context, ImageView imageView, FrameLayout frameLayout) {
        final View button = new Button(context);
        button.setLayoutParams(new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 346, false), DialogUtil.convertPixelToDP(context, 90, false)));
        button.setGravity(17);
        button.setText("\ud655\uc778");
        button.setTypeface(null, 1);
        button.setTextColor(-1);
        DialogUtil.setTextViewSize(context, button, 40);
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/btn_default_large.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/btn_default_large.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    GoodsOfferDialog.this.imgList.add(bitmap);
                    button.setBackgroundDrawable(new BitmapDrawable(bitmap));
                }
            });
        } else {
            final Context context2 = context;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/btn_default_large.png");
                    Handler handler = new Handler(context2.getMainLooper());
                    final Button button = button;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                GoodsOfferDialog.this.imgList.add(bitmapFromURL);
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
                GoodsOfferDialog.this.dismiss();
            }
        });
        final View imageView2 = new ImageView(context);
        imageView2.setLayoutParams(new FrameLayout.LayoutParams(-1, DialogUtil.convertPixelToDP(context, 390, false)));
        imageView2.setScaleType(ScaleType.FIT_XY);
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/img_cover.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/img_cover.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    GoodsOfferDialog.this.imgList.add(bitmap);
                    imageView2.setBackgroundDrawable(new BitmapDrawable(bitmap));
                }
            });
        } else {
            context2 = context;
            final View view = imageView2;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/img_cover.png");
                    Handler handler = new Handler(context2.getMainLooper());
                    final ImageView imageView = view;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                GoodsOfferDialog.this.imgList.add(bitmapFromURL);
                                imageView.setBackgroundDrawable(new BitmapDrawable(bitmapFromURL));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        imageView2 = new ImageView(context);
        imageView2.setLayoutParams(new FrameLayout.LayoutParams(DialogUtil.convertPixelToDP(context, HttpResponseCode.NOT_FOUND, false), DialogUtil.convertPixelToDP(context, R.styleable.AppCompatTheme_checkboxStyle, false), 17));
        imageView2.setScaleType(ScaleType.FIT_XY);
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/img_sorry_large.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/img_sorry_large.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    GoodsOfferDialog.this.imgList.add(bitmap);
                    imageView2.setBackgroundDrawable(new BitmapDrawable(bitmap));
                }
            });
        } else {
            context2 = context;
            view = imageView2;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/img_sorry_large.png");
                    Handler handler = new Handler(context2.getMainLooper());
                    final ImageView imageView = view;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                GoodsOfferDialog.this.imgList.add(bitmapFromURL);
                                imageView.setBackgroundDrawable(new BitmapDrawable(bitmapFromURL));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        imageView.setLayoutParams(new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 366, false), DialogUtil.convertPixelToDP(context, 48, false)));
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            final ImageView imageView3 = imageView;
            CPECompletionHandler.getImageDownloader(context).download(this.model.getTitleImg(), null, null, null, new ImageDownloadAsyncCallback(this.model.getTitleImg(), null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    GoodsOfferDialog.this.imgList.add(bitmap);
                    imageView3.setBackgroundDrawable(new BitmapDrawable(bitmap));
                }
            });
        } else {
            context2 = context;
            final ImageView imageView4 = imageView;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL(GoodsOfferDialog.this.model.getTitleImg());
                    Handler handler = new Handler(context2.getMainLooper());
                    final ImageView imageView = imageView4;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                GoodsOfferDialog.this.imgList.add(bitmapFromURL);
                                imageView.setBackgroundDrawable(new BitmapDrawable(bitmapFromURL));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        frameLayout.addView(imageView2);
        frameLayout.addView(imageView2);
        return button;
    }

    private View getSuccessView(Context context, ImageView imageView) {
        final View button = new Button(context);
        button.setLayoutParams(new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 346, false), DialogUtil.convertPixelToDP(context, 90, false)));
        button.setGravity(17);
        button.setText("\ud655\uc778");
        button.setTypeface(null, 1);
        button.setTextColor(-1);
        DialogUtil.setTextViewSize(context, button, 40);
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/btn_default_large.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/btn_default_large.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    GoodsOfferDialog.this.imgList.add(bitmap);
                    button.setBackgroundDrawable(new BitmapDrawable(bitmap));
                }
            });
        } else {
            final Context context2 = context;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/btn_default_large.png");
                    Handler handler = new Handler(context2.getMainLooper());
                    final Button button = button;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                GoodsOfferDialog.this.imgList.add(bitmapFromURL);
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
                GoodsOfferDialog.this.dismiss();
            }
        });
        imageView.setLayoutParams(new LinearLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 366, false), DialogUtil.convertPixelToDP(context, 48, false)));
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            final ImageView imageView2 = imageView;
            CPECompletionHandler.getImageDownloader(context).download(this.model.getTitleImg(), null, null, null, new ImageDownloadAsyncCallback(this.model.getTitleImg(), null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    GoodsOfferDialog.this.imgList.add(bitmap);
                    imageView2.setBackgroundDrawable(new BitmapDrawable(bitmap));
                }
            });
        } else {
            context2 = context;
            final ImageView imageView3 = imageView;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL(GoodsOfferDialog.this.model.getTitleImg());
                    Handler handler = new Handler(context2.getMainLooper());
                    final ImageView imageView = imageView3;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                GoodsOfferDialog.this.imgList.add(bitmapFromURL);
                                imageView.setBackgroundDrawable(new BitmapDrawable(bitmapFromURL));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        return button;
    }

    private void addImpression(Context context) {
        if (!this.model.isTest()) {
            Calendar.getInstance().getTime();
            TrackingActivitySQLiteDB.getInstance(context).setImpressionData(context, this.model.getConversionKey(), -1, null, CommonHelper.GetKSTCreateAtAsString(), null, null);
        }
    }

    public void run(boolean z) {
        if (!z) {
            RealRewardDAO.getInstance().clearRetryRedeemCache(this.context, this.model.getRealRewardKey());
            RealRewardDAO.getInstance().clearRetryCompleteCache(this.context, this.model.getRealRewardKey());
            RealRewardDAO.getInstance().clearSessions(this.context, this.model.getRealRewardKey());
            if (this.model.isDailyEvent()) {
                RealRewardDAO.getInstance().saveDailyCompletion(this.context, this.model.getRealRewardKey());
            } else {
                RealRewardDAO.getInstance().saveCompletedRealRewardKey(this.context, this.model.getRealRewardKey());
            }
            dismiss();
        } else if (this.model.getType() == 1) {
            try {
                String str;
                RequestParameter aTRequestParameter = RequestParameter.getATRequestParameter(getContext());
                DeviceIDManger.getInstance(this.context);
                List<Pair> persistantDemoInfo_v2 = aTRequestParameter.getPersistantDemoInfo_v2();
                if (persistantDemoInfo_v2 != null) {
                    for (Pair pair : persistantDemoInfo_v2) {
                        if (((String) pair.first).equals("userId")) {
                            str = (String) pair.second;
                            break;
                        }
                    }
                }
                str = null;
                ADBrixHttpManager.getManager(this.context).startRealReward(getContext(), aTRequestParameter.getAppkey(), this.model.getRealRewardKey(), str);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                dismiss();
            }
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (this.imgList != null) {
            for (Bitmap bitmap : this.imgList) {
                if (!(bitmap == null || bitmap.isRecycled())) {
                    bitmap.recycle();
                }
            }
        }
    }
}
