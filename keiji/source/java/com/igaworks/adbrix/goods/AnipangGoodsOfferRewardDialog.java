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
import com.igaworks.adbrix.model.RealRewardResultModel;
import com.igaworks.adbrix.util.AnipangDialogUtil;
import com.igaworks.adbrix.util.AutoResizeTextView;
import com.igaworks.impl.InternalAction;
import com.igaworks.util.CommonHelper;
import com.igaworks.util.image.ImageCacheFactory;
import com.igaworks.util.image.ImageDownloadAsyncCallback;
import java.util.ArrayList;
import java.util.List;

public class AnipangGoodsOfferRewardDialog extends Dialog {
    private List<Bitmap> imgList;
    private RealRewardResultModel model;

    public AnipangGoodsOfferRewardDialog(Context context, RealRewardResultModel realRewardResultModel) {
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
        this.model = realRewardResultModel;
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
        layoutParams = new FrameLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 452, true), AnipangDialogUtil.convertPixelToDP(context, 435, false), 83);
        linearLayout.setPadding(0, 0, 0, 0);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(1);
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/popup_small_bg.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/popup_small_bg.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    AnipangGoodsOfferRewardDialog.this.imgList.add(bitmap);
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
                                AnipangGoodsOfferRewardDialog.this.imgList.add(bitmapFromURL);
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
                    AnipangGoodsOfferRewardDialog.this.imgList.add(bitmap);
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
                                AnipangGoodsOfferRewardDialog.this.imgList.add(bitmapFromURL);
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
                AnipangGoodsOfferRewardDialog.this.dismiss();
            }
        });
        frameLayout.addView(linearLayout);
        frameLayout.addView(imageView);
        setOfferMainContents(context, linearLayout);
        return frameLayout;
    }

    private void setOfferMainContents(Context context, LinearLayout linearLayout) {
        linearLayout.setGravity(1);
        linearLayout.setPadding(AnipangDialogUtil.convertPixelToDP(context, 11, true), AnipangDialogUtil.convertPixelToDP(context, 28, false), AnipangDialogUtil.convertPixelToDP(context, 16, true), 0);
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
                    AnipangGoodsOfferRewardDialog.this.imgList.add(bitmap);
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
                                AnipangGoodsOfferRewardDialog.this.imgList.add(bitmapFromURL);
                                imageView.setImageBitmap(bitmapFromURL);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        linearLayout.addView(linearLayout2);
        linearLayout.addView(getSuccessRewardView(context));
        View frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 308, true), AnipangDialogUtil.convertPixelToDP(context, 94, false)));
        imageView = new Button(context);
        imageView.setLayoutParams(new FrameLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 308, false), AnipangDialogUtil.convertPixelToDP(context, 94, false), 17));
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_small.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_small.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    AnipangGoodsOfferRewardDialog.this.imgList.add(bitmap);
                    imageView.setBackgroundDrawable(new BitmapDrawable(bitmap));
                }
            });
        } else {
            context2 = context;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_small.png");
                    Handler handler = new Handler(context2.getMainLooper());
                    final Button button = imageView;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                AnipangGoodsOfferRewardDialog.this.imgList.add(bitmapFromURL);
                                button.setBackgroundDrawable(new BitmapDrawable(bitmapFromURL));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        final View imageView2 = new ImageView(context);
        imageView2.setLayoutParams(new FrameLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 56, false), AnipangDialogUtil.convertPixelToDP(context, 32, false), 17));
        imageView2.setScaleType(ScaleType.FIT_XY);
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_text01.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_text01.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    AnipangGoodsOfferRewardDialog.this.imgList.add(bitmap);
                    imageView2.setBackgroundDrawable(new BitmapDrawable(bitmap));
                }
            });
        } else {
            context2 = context;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/goods/anipang/btn_text01.png");
                    Handler handler = new Handler(context2.getMainLooper());
                    final ImageView imageView = imageView2;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                AnipangGoodsOfferRewardDialog.this.imgList.add(bitmapFromURL);
                                imageView.setBackgroundDrawable(new BitmapDrawable(bitmapFromURL));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        frameLayout.addView(imageView);
        frameLayout.addView(imageView2);
        linearLayout.addView(frameLayout);
        if (this.model.isResult()) {
            setSuccessClickLisetener(context, imageView);
        } else {
            setFailClickListener(context, imageView);
        }
    }

    private View getSuccessRewardView(final Context context) {
        View linearLayout = new LinearLayout(context);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 308, true), AnipangDialogUtil.convertPixelToDP(context, 192, false));
        layoutParams.topMargin = AnipangDialogUtil.convertPixelToDP(context, 34, false);
        linearLayout.setPadding(0, 0, 0, 0);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setGravity(1);
        linearLayout.setOrientation(1);
        final View imageView = new ImageView(context);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 133, true), AnipangDialogUtil.convertPixelToDP(context, 133, false)));
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            CPECompletionHandler.getImageDownloader(context).download(this.model.getRewardImage(), null, null, null, new ImageDownloadAsyncCallback(this.model.getRewardImage(), null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    AnipangGoodsOfferRewardDialog.this.imgList.add(bitmap);
                    imageView.setImageBitmap(bitmap);
                }
            });
        } else {
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL(AnipangGoodsOfferRewardDialog.this.model.getRewardImage());
                    Handler handler = new Handler(context.getMainLooper());
                    final ImageView imageView = imageView;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                AnipangGoodsOfferRewardDialog.this.imgList.add(bitmapFromURL);
                                imageView.setImageBitmap(bitmapFromURL);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        View autoResizeTextView = new AutoResizeTextView(context);
        ViewGroup.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(AnipangDialogUtil.convertPixelToDP(context, 308, true), AnipangDialogUtil.convertPixelToDP(context, 55, false));
        layoutParams2.topMargin = AnipangDialogUtil.convertPixelToDP(context, 4, false);
        autoResizeTextView.setLayoutParams(layoutParams2);
        autoResizeTextView.setPadding(0, 0, 0, AnipangDialogUtil.convertPixelToDP(context, 4, false));
        autoResizeTextView.setGravity(1);
        autoResizeTextView.setTypeface(null, 1);
        autoResizeTextView.setTextColor(Color.parseColor("#697a7f"));
        autoResizeTextView.setMinTextSize(AnipangDialogUtil.calNormPixel(context, 14, false));
        autoResizeTextView.setMaxTextSize(AnipangDialogUtil.calNormPixel(context, 28, false));
        AnipangDialogUtil.setTextViewSize(context, autoResizeTextView, 28);
        autoResizeTextView.setText(Html.fromHtml(this.model.getRewardName()));
        linearLayout.addView(imageView);
        linearLayout.addView(autoResizeTextView);
        return linearLayout;
    }

    private void setSuccessClickLisetener(Context context, Button button) {
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                AnipangGoodsOfferRewardDialog.this.dismiss();
            }
        });
    }

    private void setFailClickListener(Context context, Button button) {
        if (this.model.getStatusCodes() == 572) {
            button.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    AnipangGoodsOfferRewardDialog.this.dismiss();
                }
            });
        } else {
            button.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    AnipangGoodsOfferRewardDialog.this.dismiss();
                }
            });
        }
    }
}
