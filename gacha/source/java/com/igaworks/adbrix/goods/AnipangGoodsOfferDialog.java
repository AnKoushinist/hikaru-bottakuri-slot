package com.igaworks.adbrix.goods;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import com.igaworks.adbrix.cpe.CPECompletionHandler;
import com.igaworks.adbrix.db.RealRewardDAO;
import com.igaworks.adbrix.util.AnipangDialogUtil;
import com.igaworks.core.IgawLogger;
import com.igaworks.dao.CPEPromotionImpressionDAO;
import com.igaworks.dao.tracking.TrackingActivitySQLiteDB;
import com.igaworks.impl.InternalAction;
import com.igaworks.util.CommonHelper;
import com.igaworks.util.image.ImageCacheFactory;
import com.igaworks.util.image.ImageDownloadAsyncCallback;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import jp.co.vaz.creator.hikaru.R;
import twitter4j.TwitterResponse;

public class AnipangGoodsOfferDialog extends Dialog implements OnDismissListener, GoodsOfferCallbackListener {
    private Context context;
    protected CPEPromotionImpressionDAO dao;
    private List<Bitmap> imgList;
    private GoodsOfferModel model;

    public AnipangGoodsOfferDialog(Context context, GoodsOfferModel goodsOfferModel) {
        super(context);
        this.context = context;
        requestWindowFeature(1);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.flags = 2;
        attributes.dimAmount = 0.7f;
        attributes.width = -1;
        attributes.height = -1;
        attributes.gravity = 17;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
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
            IgawLogger.Logging(context, "IGAW_QA", "AnipangGoodsOfferDialog", 3);
        } catch (Exception e) {
            IgawLogger.Logging(context, "IGAW_QA", "AnipangGoodsOfferDialog - error : " + e.getMessage(), 1);
        }
        setOnDismissListener(this);
    }

    @SuppressLint({"NewApi"})
    public View getRootView(Context context, GoodsOfferModel goodsOfferModel) {
        final Context context2;
        View frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 552, true), AnipangDialogUtil.convertPixelToDP(context, 822, false)));
        final View linearLayout = new LinearLayout(context);
        ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 546, true), AnipangDialogUtil.convertPixelToDP(context, 800, false), 83);
        layoutParams.leftMargin = AnipangDialogUtil.convertPixelToDP(context, 6, true);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(1);
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/popup_big_bg.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/popup_big_bg.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    AnipangGoodsOfferDialog.this.imgList.add(bitmap);
                    linearLayout.setBackgroundDrawable(new BitmapDrawable(bitmap));
                }
            });
        } else {
            context2 = context;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/popup_big_bg.png");
                    Handler handler = new Handler(context2.getMainLooper());
                    final LinearLayout linearLayout = linearLayout;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                AnipangGoodsOfferDialog.this.imgList.add(bitmapFromURL);
                                linearLayout.setBackgroundDrawable(new BitmapDrawable(bitmapFromURL));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        frameLayout = new ImageView(context);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 70, true), AnipangDialogUtil.convertPixelToDP(context, 70, true), 53));
        frameLayout.setScaleType(ScaleType.FIT_XY);
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_close.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_close.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    AnipangGoodsOfferDialog.this.imgList.add(bitmap);
                    frameLayout.setImageBitmap(bitmap);
                }
            });
        } else {
            context2 = context;
            final View view = frameLayout;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_close.png");
                    Handler handler = new Handler(context2.getMainLooper());
                    final ImageView imageView = view;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                AnipangGoodsOfferDialog.this.imgList.add(bitmapFromURL);
                                imageView.setImageBitmap(bitmapFromURL);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        addImpression(context);
        context2 = context;
        frameLayout.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                AnipangGoodsOfferAlertDialog anipangGoodsOfferAlertDialog = new AnipangGoodsOfferAlertDialog(context2, AnipangGoodsOfferDialog.this);
                LayoutParams layoutParams = new LayoutParams();
                layoutParams.copyFrom(anipangGoodsOfferAlertDialog.getWindow().getAttributes());
                layoutParams.width = -1;
                layoutParams.height = -2;
                anipangGoodsOfferAlertDialog.getWindow().setAttributes(layoutParams);
                anipangGoodsOfferAlertDialog.show();
            }
        });
        frameLayout.addView(linearLayout);
        frameLayout.addView(frameLayout);
        setOfferMainContents(context, linearLayout, goodsOfferModel);
        return frameLayout;
    }

    private void setOfferMainContents(Context context, LinearLayout linearLayout, GoodsOfferModel goodsOfferModel) {
        linearLayout.setGravity(1);
        linearLayout.setPadding(AnipangDialogUtil.convertPixelToDP(context, 14, true), AnipangDialogUtil.convertPixelToDP(context, 43, false), AnipangDialogUtil.convertPixelToDP(context, 20, true), 0);
        View linearLayout2 = new LinearLayout(context);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 512, true), AnipangDialogUtil.convertPixelToDP(context, 36, false)));
        linearLayout2.setGravity(17);
        linearLayout2.setPadding(0, 0, 0, 0);
        linearLayout2 = new ImageView(context);
        linearLayout2.setScaleType(ScaleType.FIT_XY);
        linearLayout2.addView(linearLayout2);
        linearLayout2 = new FrameLayout(context);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 512, true), AnipangDialogUtil.convertPixelToDP(context, 532, false));
        layoutParams.topMargin = AnipangDialogUtil.convertPixelToDP(context, 34, false);
        linearLayout2.setLayoutParams(layoutParams);
        final View imageView = new ImageView(context);
        imageView.setLayoutParams(new FrameLayout.LayoutParams(-1, AnipangDialogUtil.convertPixelToDP(context, 532, false)));
        imageView.setScaleType(ScaleType.FIT_XY);
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download(goodsOfferModel.getMainImg(), null, null, null, new ImageDownloadAsyncCallback(goodsOfferModel.getMainImg(), null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    AnipangGoodsOfferDialog.this.imgList.add(bitmap);
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
                                AnipangGoodsOfferDialog.this.imgList.add(bitmapFromURL);
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
        View linearLayout3 = new LinearLayout(context);
        ViewGroup.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 506, true), AnipangDialogUtil.convertPixelToDP(context, 140, false));
        layoutParams2.topMargin = AnipangDialogUtil.convertPixelToDP(context, 21, false);
        layoutParams2.bottomMargin = AnipangDialogUtil.convertPixelToDP(context, 40, false);
        linearLayout3.setLayoutParams(layoutParams2);
        linearLayout3.setGravity(1);
        switch (goodsOfferModel.getType()) {
            case TwitterResponse.READ /*1*/:
                linearLayout3.addView(getOfferView(context, linearLayout2));
                break;
            default:
                linearLayout3.addView(getOfferView(context, linearLayout2));
                break;
        }
        linearLayout.addView(linearLayout2);
        linearLayout.addView(linearLayout2);
        linearLayout.addView(linearLayout3);
    }

    private View getOfferView(Context context, ImageView imageView) {
        final Context context2;
        View frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 346, true), AnipangDialogUtil.convertPixelToDP(context, 94, false)));
        final View button = new Button(context);
        button.setLayoutParams(new LinearLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 346, true), AnipangDialogUtil.convertPixelToDP(context, 94, false), 17.0f));
        button.setTypeface(null, 1);
        button.setTextColor(-1);
        AnipangDialogUtil.setTextViewSize(context, button, 28);
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_big02.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_big02.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    AnipangGoodsOfferDialog.this.imgList.add(bitmap);
                    button.setBackgroundDrawable(new BitmapDrawable(bitmap));
                }
            });
        } else {
            context2 = context;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_big02.png");
                    Handler handler = new Handler(context2.getMainLooper());
                    final Button button = button;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                AnipangGoodsOfferDialog.this.imgList.add(bitmapFromURL);
                                button.setBackgroundDrawable(new BitmapDrawable(bitmapFromURL));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        frameLayout = new ImageView(context);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, R.styleable.AppCompatTheme_listMenuViewStyle, false), AnipangDialogUtil.convertPixelToDP(context, 32, false), 17));
        frameLayout.setScaleType(ScaleType.FIT_XY);
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_text02.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_text02.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    AnipangGoodsOfferDialog.this.imgList.add(bitmap);
                    frameLayout.setBackgroundDrawable(new BitmapDrawable(bitmap));
                }
            });
        } else {
            context2 = context;
            final View view = frameLayout;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_text02.png");
                    Handler handler = new Handler(context2.getMainLooper());
                    final ImageView imageView = view;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                AnipangGoodsOfferDialog.this.imgList.add(bitmapFromURL);
                                imageView.setBackgroundDrawable(new BitmapDrawable(bitmapFromURL));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        imageView.setLayoutParams(new LinearLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 346, false), AnipangDialogUtil.convertPixelToDP(context, 36, false)));
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            final ImageView imageView2 = imageView;
            CPECompletionHandler.getImageDownloader(context).download(this.model.getTitleImg(), null, null, null, new ImageDownloadAsyncCallback(this.model.getTitleImg(), null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    AnipangGoodsOfferDialog.this.imgList.add(bitmap);
                    imageView2.setBackgroundDrawable(new BitmapDrawable(bitmap));
                }
            });
        } else {
            context2 = context;
            final ImageView imageView3 = imageView;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL(AnipangGoodsOfferDialog.this.model.getTitleImg());
                    Handler handler = new Handler(context2.getMainLooper());
                    final ImageView imageView = imageView3;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                AnipangGoodsOfferDialog.this.imgList.add(bitmapFromURL);
                                imageView.setBackgroundDrawable(new BitmapDrawable(bitmapFromURL));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        frameLayout.addView(button);
        frameLayout.addView(frameLayout);
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
                r0 = com.igaworks.adbrix.goods.AnipangGoodsOfferDialog.this;	 Catch:{ Exception -> 0x0078 }
                r0 = r0.model;	 Catch:{ Exception -> 0x0078 }
                r0 = r0.isNoCondition();	 Catch:{ Exception -> 0x0078 }
                if (r0 == 0) goto L_0x005e;
            L_0x0023:
                r0 = r1;	 Catch:{ Exception -> 0x0078 }
                r0 = com.igaworks.adbrix.core.ADBrixHttpManager.getManager(r0);	 Catch:{ Exception -> 0x0078 }
                r1 = r1;	 Catch:{ Exception -> 0x0078 }
                r2 = r2.getAppkey();	 Catch:{ Exception -> 0x0078 }
                r3 = com.igaworks.adbrix.goods.AnipangGoodsOfferDialog.this;	 Catch:{ Exception -> 0x0078 }
                r3 = r3.model;	 Catch:{ Exception -> 0x0078 }
                r3 = r3.getRealRewardKey();	 Catch:{ Exception -> 0x0078 }
                r5 = r1;	 Catch:{ Exception -> 0x0078 }
                r5 = (android.app.Activity) r5;	 Catch:{ Exception -> 0x0078 }
                r0.startAndCompeteRealReward(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x0078 }
            L_0x0040:
                r0 = com.igaworks.adbrix.goods.AnipangGoodsOfferDialog.this;
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
                r3 = com.igaworks.adbrix.goods.AnipangGoodsOfferDialog.this;	 Catch:{ Exception -> 0x0078 }
                r3 = r3.model;	 Catch:{ Exception -> 0x0078 }
                r3 = r3.getRealRewardKey();	 Catch:{ Exception -> 0x0078 }
                r0.startRealReward(r1, r2, r3, r4);	 Catch:{ Exception -> 0x0078 }
                goto L_0x0040;
            L_0x0078:
                r0 = move-exception;
                r0.printStackTrace();	 Catch:{ all -> 0x0082 }
                r0 = com.igaworks.adbrix.goods.AnipangGoodsOfferDialog.this;
                r0.dismiss();
                goto L_0x0045;
            L_0x0082:
                r0 = move-exception;
                r1 = com.igaworks.adbrix.goods.AnipangGoodsOfferDialog.this;
                r1.dismiss();
                throw r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.igaworks.adbrix.goods.AnipangGoodsOfferDialog.14.onClick(android.view.View):void");
            }
        });
        return frameLayout;
    }

    private void addImpression(Context context) {
        if (!this.model.isTest()) {
            if (this.dao == null) {
                this.dao = CPEPromotionImpressionDAO.getInstance();
            }
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
