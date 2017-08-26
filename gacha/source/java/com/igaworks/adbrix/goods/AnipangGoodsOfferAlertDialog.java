package com.igaworks.adbrix.goods;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
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
import com.igaworks.adbrix.cpe.CPECompletionHandler;
import com.igaworks.adbrix.util.AnipangDialogUtil;
import com.igaworks.adbrix.util.AutoResizeTextView;
import com.igaworks.impl.InternalAction;
import com.igaworks.util.CommonHelper;
import com.igaworks.util.image.ImageCacheFactory;
import com.igaworks.util.image.ImageDownloadAsyncCallback;
import java.util.ArrayList;
import java.util.List;

public class AnipangGoodsOfferAlertDialog extends Dialog {
    private String btnText;
    private List<Bitmap> imgList;
    private String infoText;
    private GoodsOfferCallbackListener listener;

    public AnipangGoodsOfferAlertDialog(Context context, GoodsOfferCallbackListener goodsOfferCallbackListener) {
        super(context);
        requestWindowFeature(1);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.flags = 2;
        attributes.dimAmount = 0.7f;
        attributes.width = -1;
        attributes.height = -1;
        attributes.gravity = 17;
        getWindow().setAttributes(attributes);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().getDecorView().setBackgroundColor(0);
        getWindow().setFormat(1);
        getWindow().addFlags(4096);
        getWindow().setFlags(1024, 1024);
        getWindow().setGravity(17);
        getWindow().setSoftInputMode(38);
        this.imgList = new ArrayList();
        this.listener = goodsOfferCallbackListener;
        this.infoText = "\uc815\ub9d0\ub85c \uc774\ubca4\ud2b8 \ud31d\uc5c5\uc744 \ub2eb\uc73c\uc2dc\uaca0\uc5b4\uc694?<br/>\uc624\ub298 \ud558\ub8e8\ub294 \uc774\ubca4\ud2b8 \ud31d\uc5c5\uc774<br/>\ub728\uc9c0 \uc54a\uc2b5\ub2c8\ub2e4.";
        this.btnText = "\ucc38\uc5ec\ud558\uae30";
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
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 468, true), AnipangDialogUtil.convertPixelToDP(context, 459, false));
        layoutParams.leftMargin = AnipangDialogUtil.convertPixelToDP(context, 16, true);
        frameLayout.setLayoutParams(layoutParams);
        final View linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 452, true), AnipangDialogUtil.convertPixelToDP(context, 435, false), 83));
        linearLayout.setOrientation(1);
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/popup_small_bg.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/popup_small_bg.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    AnipangGoodsOfferAlertDialog.this.imgList.add(bitmap);
                    linearLayout.setBackgroundDrawable(new BitmapDrawable(bitmap));
                }
            });
        } else {
            final Context context2 = context;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/popup_small_bg.png");
                    Handler handler = new Handler(context2.getMainLooper());
                    final LinearLayout linearLayout = linearLayout;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                AnipangGoodsOfferAlertDialog.this.imgList.add(bitmapFromURL);
                                linearLayout.setBackgroundDrawable(new BitmapDrawable(bitmapFromURL));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        final View imageView = new ImageView(context);
        imageView.setLayoutParams(new FrameLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 70, true), AnipangDialogUtil.convertPixelToDP(context, 70, true), 53));
        imageView.setScaleType(ScaleType.FIT_XY);
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_close.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_close.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    AnipangGoodsOfferAlertDialog.this.imgList.add(bitmap);
                    imageView.setImageBitmap(bitmap);
                }
            });
        } else {
            context2 = context;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_close.png");
                    Handler handler = new Handler(context2.getMainLooper());
                    final ImageView imageView = imageView;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                AnipangGoodsOfferAlertDialog.this.imgList.add(bitmapFromURL);
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
                AnipangGoodsOfferAlertDialog.this.dismiss();
            }
        });
        frameLayout.addView(linearLayout);
        frameLayout.addView(imageView);
        setOfferConfirmContents(context, linearLayout);
        return frameLayout;
    }

    private void setOfferConfirmContents(Context context, LinearLayout linearLayout) {
        linearLayout.setGravity(1);
        linearLayout.setPadding(AnipangDialogUtil.convertPixelToDP(context, 11, false), AnipangDialogUtil.convertPixelToDP(context, 28, true), AnipangDialogUtil.convertPixelToDP(context, 16, false), 0);
        View linearLayout2 = new LinearLayout(context);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 66, true), AnipangDialogUtil.convertPixelToDP(context, 36, false)));
        linearLayout2.setGravity(17);
        final View imageView = new ImageView(context);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 66, true), AnipangDialogUtil.convertPixelToDP(context, 36, false)));
        imageView.setScaleType(ScaleType.FIT_XY);
        linearLayout2.addView(imageView);
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/title_02.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/title_02.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    AnipangGoodsOfferAlertDialog.this.imgList.add(bitmap);
                    imageView.setImageBitmap(bitmap);
                }
            });
        } else {
            final Context context2 = context;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/title_02.png");
                    Handler handler = new Handler(context2.getMainLooper());
                    final ImageView imageView = imageView;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                AnipangGoodsOfferAlertDialog.this.imgList.add(bitmapFromURL);
                                imageView.setImageBitmap(bitmapFromURL);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        linearLayout2 = new LinearLayout(context);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 360, false), AnipangDialogUtil.convertPixelToDP(context, 192, false));
        layoutParams.topMargin = AnipangDialogUtil.convertPixelToDP(context, 34, false);
        linearLayout2.setPadding(0, 0, 0, 0);
        linearLayout2.setLayoutParams(layoutParams);
        linearLayout2.setGravity(17);
        View autoResizeTextView = new AutoResizeTextView(context);
        ViewGroup.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 360, false), AnipangDialogUtil.convertPixelToDP(context, 188, false));
        layoutParams2.topMargin = AnipangDialogUtil.convertPixelToDP(context, 4, false);
        autoResizeTextView.setLayoutParams(layoutParams2);
        autoResizeTextView.setGravity(17);
        autoResizeTextView.setTypeface(null, 1);
        autoResizeTextView.setTextColor(Color.parseColor("#697a7f"));
        autoResizeTextView.setMinTextSize(AnipangDialogUtil.calNormPixel(context, 22, false));
        autoResizeTextView.setMaxTextSize(AnipangDialogUtil.calNormPixel(context, 28, false));
        AnipangDialogUtil.setTextViewSize(context, autoResizeTextView, 28);
        autoResizeTextView.setText(Html.fromHtml(this.infoText));
        linearLayout2.addView(autoResizeTextView);
        linearLayout2 = new LinearLayout(context);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 360, false), AnipangDialogUtil.convertPixelToDP(context, 94, false)));
        linearLayout2.setGravity(17);
        linearLayout2.setOrientation(0);
        linearLayout2 = new FrameLayout(context);
        layoutParams = new LinearLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 174, true), AnipangDialogUtil.convertPixelToDP(context, 94, false));
        layoutParams.rightMargin = AnipangDialogUtil.convertPixelToDP(context, 14, true);
        linearLayout2.setLayoutParams(layoutParams);
        imageView = new Button(context);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 174, false), AnipangDialogUtil.convertPixelToDP(context, 94, false)));
        imageView.setGravity(17);
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_no_normal.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_no_normal.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    AnipangGoodsOfferAlertDialog.this.imgList.add(bitmap);
                    imageView.setBackgroundDrawable(new BitmapDrawable(bitmap));
                }
            });
        } else {
            context2 = context;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_no_normal.png");
                    Handler handler = new Handler(context2.getMainLooper());
                    final Button button = imageView;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                AnipangGoodsOfferAlertDialog.this.imgList.add(bitmapFromURL);
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
                AnipangGoodsOfferAlertDialog.this.dismiss();
                if (AnipangGoodsOfferAlertDialog.this.listener != null) {
                    AnipangGoodsOfferAlertDialog.this.listener.run(true);
                }
            }
        });
        final View imageView2 = new ImageView(context);
        imageView2.setLayoutParams(new FrameLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 76, false), AnipangDialogUtil.convertPixelToDP(context, 32, false), 17));
        imageView2.setScaleType(ScaleType.FIT_XY);
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_text03.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_text03.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    AnipangGoodsOfferAlertDialog.this.imgList.add(bitmap);
                    imageView2.setBackgroundDrawable(new BitmapDrawable(bitmap));
                }
            });
        } else {
            context2 = context;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_text03.png");
                    Handler handler = new Handler(context2.getMainLooper());
                    final ImageView imageView = imageView2;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                AnipangGoodsOfferAlertDialog.this.imgList.add(bitmapFromURL);
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
        linearLayout2.addView(imageView2);
        linearLayout2 = new FrameLayout(context);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 174, true), AnipangDialogUtil.convertPixelToDP(context, 94, false)));
        imageView = new Button(context);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 174, false), AnipangDialogUtil.convertPixelToDP(context, 94, false)));
        imageView.setGravity(17);
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_yes_normal.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_yes_normal.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    AnipangGoodsOfferAlertDialog.this.imgList.add(bitmap);
                    imageView.setBackgroundDrawable(new BitmapDrawable(bitmap));
                }
            });
        } else {
            context2 = context;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_yes_normal.png");
                    Handler handler = new Handler(context2.getMainLooper());
                    final Button button = imageView;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                AnipangGoodsOfferAlertDialog.this.imgList.add(bitmapFromURL);
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
                AnipangGoodsOfferAlertDialog.this.dismiss();
                if (AnipangGoodsOfferAlertDialog.this.listener != null) {
                    AnipangGoodsOfferAlertDialog.this.listener.run(false);
                }
            }
        });
        imageView2 = new ImageView(context);
        imageView2.setLayoutParams(new FrameLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 26, false), AnipangDialogUtil.convertPixelToDP(context, 32, false), 17));
        imageView2.setScaleType(ScaleType.FIT_XY);
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_text04.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_text04.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    AnipangGoodsOfferAlertDialog.this.imgList.add(bitmap);
                    imageView2.setBackgroundDrawable(new BitmapDrawable(bitmap));
                }
            });
        } else {
            context2 = context;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_text04.png");
                    Handler handler = new Handler(context2.getMainLooper());
                    final ImageView imageView = imageView2;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                AnipangGoodsOfferAlertDialog.this.imgList.add(bitmapFromURL);
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
        linearLayout2.addView(imageView2);
        linearLayout2.addView(linearLayout2);
        linearLayout2.addView(linearLayout2);
        linearLayout.addView(linearLayout2);
        linearLayout.addView(linearLayout2);
        linearLayout.addView(linearLayout2);
    }
}
