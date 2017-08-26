package com.igaworks.adbrix.viral;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.text.ClipboardManager;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.igaworks.adbrix.core.ADBrixHttpManager;
import com.igaworks.adbrix.cpe.CPECompletionHandler;
import com.igaworks.adbrix.cpe.ConditionChecker;
import com.igaworks.adbrix.db.ViralCPIDAO;
import com.igaworks.adbrix.interfaces.ADBrixHttpCallbackListener;
import com.igaworks.adbrix.model.ViralCPIModel;
import com.igaworks.adbrix.model.ViralUrlModel;
import com.igaworks.adbrix.util.ABLanguage;
import com.igaworks.adbrix.util.DialogUtil;
import com.igaworks.adbrix.viral.ViralCheckbox.ViralCheckboxClickListener;
import com.igaworks.core.IgawLogger;
import com.igaworks.core.RequestParameter;
import com.igaworks.impl.InternalAction;
import com.igaworks.util.CommonHelper;
import com.igaworks.util.image.ImageCacheFactory;
import com.igaworks.util.image.ImageDownloadAsyncCallback;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConnectCore;
import java.util.ArrayList;
import java.util.List;
import jp.co.vaz.creator.hikaru.R;
import twitter4j.HttpResponseCode;
import twitter4j.TwitterResponse;

public class ViralInfoDialog extends Dialog {
    private TextView completeCopyTv;
    private LayoutParams completeCopyTvParam;
    private Context context;
    private LinearLayout innerView;
    private boolean isPortrait = true;
    private LinearLayout mainContentsLl;
    private LinearLayout mainShareLl;
    private LayoutParams mainShareLlParam;
    private ViralCPIModel model;
    private LinearLayout noMoreLl;
    private FrameLayout rootFl;
    private TextView selectViralChannelTv;
    private LayoutParams selectViralChannelTvParam;
    private LinearLayout shareContentsAreaLl;
    private int type = 1;

    public ViralInfoDialog(Context context, ViralCPIModel viralCPIModel, int i) {
        super(context);
        this.model = viralCPIModel;
        this.context = context;
        this.type = i;
        requestWindowFeature(1);
        this.isPortrait = context.getResources().getConfiguration().orientation == 1;
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
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
        try {
            View linearLayout = new LinearLayout(context);
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
            linearLayout.setGravity(17);
            linearLayout.addView(getRootView(context));
            setContentView(linearLayout, layoutParams);
            IgawLogger.Logging(context, "IGAW_QA", "ViralInfoDialog", 3);
        } catch (Exception e) {
            IgawLogger.Logging(context, "IGAW_QA", "ViralInfoDialog - error : " + e.getMessage(), 1);
        }
    }

    public View getRootView(final Context context) {
        String str;
        ViewGroup.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2;
        int convertPixelToDP = DialogUtil.convertPixelToDP(context, 20, true);
        float[] fArr = new float[]{(float) convertPixelToDP, (float) convertPixelToDP, (float) convertPixelToDP, (float) convertPixelToDP, 0.0f, 0.0f, 0.0f, 0.0f};
        float[] fArr2 = new float[]{0.0f, 0.0f, 0.0f, 0.0f, (float) convertPixelToDP, (float) convertPixelToDP, (float) convertPixelToDP, (float) convertPixelToDP};
        int convertPixelToDP2 = DialogUtil.convertPixelToDP(context, 21, false);
        Drawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadii(fArr);
        if (this.model.getTopbarColorCode() == null || this.model.getTopbarColorCode().length() < 1) {
            str = "#363F4A";
        } else {
            str = this.model.getTopbarColorCode();
        }
        gradientDrawable.setColor(Color.parseColor(str));
        Drawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setCornerRadii(fArr2);
        gradientDrawable2.setColor(Color.parseColor("#B6BABB"));
        Drawable gradientDrawable3 = new GradientDrawable();
        gradientDrawable3.setCornerRadii(fArr2);
        gradientDrawable3.setColor(-1);
        Drawable gradientDrawable4 = new GradientDrawable();
        gradientDrawable4.setCornerRadius((float) DialogUtil.convertPixelToDP(context, 10, true));
        if (this.model.getCloseBtnColorCode() == null || this.model.getCloseBtnColorCode().length() < 1) {
            str = "#5B6A7C";
        } else {
            str = this.model.getCloseBtnColorCode();
        }
        gradientDrawable4.setColor(Color.parseColor(str));
        gradientDrawable4.setSize(DialogUtil.convertPixelToDP(context, 88, true), DialogUtil.convertPixelToDP(context, 60, false));
        if (this.isPortrait) {
            layoutParams = new LayoutParams(DialogUtil.convertPixelToDP(context, 600, true), DialogUtil.convertPixelToDP(context, 900, false));
            layoutParams2 = new FrameLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 600, true), DialogUtil.convertPixelToDP(context, 900, false), 83);
            gradientDrawable3.setSize(DialogUtil.convertPixelToDP(context, 600, true), DialogUtil.convertPixelToDP(context, 900, false));
        } else {
            layoutParams = new LayoutParams(DialogUtil.convertPixelToDP(context, 900, true), DialogUtil.convertPixelToDP(context, 600, false));
            layoutParams2 = new FrameLayout.LayoutParams(DialogUtil.convertPixelToDP(context, 900, true), DialogUtil.convertPixelToDP(context, 600, false), 83);
            gradientDrawable3.setSize(DialogUtil.convertPixelToDP(context, 900, true), DialogUtil.convertPixelToDP(context, 600, false));
        }
        this.rootFl = new FrameLayout(context);
        this.rootFl.setLayoutParams(layoutParams);
        this.mainContentsLl = new LinearLayout(context);
        this.mainContentsLl.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.mainContentsLl.setPadding(convertPixelToDP2, convertPixelToDP2, convertPixelToDP2, 0);
        this.mainContentsLl.setLayoutParams(layoutParams2);
        this.mainContentsLl.setOrientation(1);
        LinearLayout linearLayout = this.mainContentsLl;
        if (this.model.getViralInfoDialogBGColorCode() == null || this.model.getViralInfoDialogBGColorCode().length() < 1) {
            str = "#EEEEEE";
        } else {
            str = this.model.getViralInfoDialogBGColorCode();
        }
        linearLayout.setBackgroundColor(Color.parseColor(str));
        View linearLayout2 = new LinearLayout(context);
        linearLayout2.setLayoutParams(new LayoutParams(-1, DialogUtil.convertPixelToDP(context, R.styleable.AppCompatTheme_switchStyle, false)));
        linearLayout2.setGravity(17);
        linearLayout2.setBackgroundDrawable(gradientDrawable);
        linearLayout2.setTag("titleLl");
        View frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(new LayoutParams(-1, 0, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER));
        View linearLayout3 = new LinearLayout(context);
        linearLayout3.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        linearLayout3.setOrientation(1);
        linearLayout3.setBackgroundDrawable(gradientDrawable2);
        this.innerView = new LinearLayout(context);
        layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
        layoutParams2.bottomMargin = DialogUtil.convertPixelToDP(context, 4, false);
        layoutParams2.leftMargin = DialogUtil.convertPixelToDP(context, 2, false);
        layoutParams2.rightMargin = DialogUtil.convertPixelToDP(context, 2, false);
        this.innerView.setLayoutParams(layoutParams2);
        this.innerView.setOrientation(1);
        this.innerView.setBackgroundDrawable(gradientDrawable3);
        View linearLayout4 = new LinearLayout(context);
        linearLayout4.setLayoutParams(new LayoutParams(-1, DialogUtil.convertPixelToDP(context, 87, false)));
        linearLayout4.setOrientation(0);
        linearLayout4.setGravity(16);
        this.noMoreLl = new LinearLayout(context);
        this.noMoreLl.setLayoutParams(new LayoutParams(0, -2, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER));
        this.noMoreLl.setOrientation(0);
        this.noMoreLl.setGravity(16);
        final View viralCheckbox = new ViralCheckbox(context);
        layoutParams2 = new LayoutParams(DialogUtil.convertPixelToDP(context, 35, false), DialogUtil.convertPixelToDP(context, 35, false));
        layoutParams2.rightMargin = convertPixelToDP2;
        viralCheckbox.setLayoutParams(layoutParams2);
        viralCheckbox.getClass();
        viralCheckbox.setClickListener(new ViralCheckboxClickListener(viralCheckbox) {
            public void onClick(ViralCheckbox viralCheckbox) {
                super.onClick(viralCheckbox);
                if (viralCheckbox.isChecked()) {
                    ViralCPIDAO.getInstance().saveDoNotShow(context, ViralInfoDialog.this.model.getCampaignKey());
                } else {
                    ViralCPIDAO.getInstance().removeDoNotShow(context, ViralInfoDialog.this.model.getCampaignKey());
                }
            }

            public void onClick(View view) {
                onClick((ViralCheckbox) view);
            }
        });
        View textView = new TextView(context);
        ViewGroup.LayoutParams layoutParams3 = new LayoutParams(-2, -2);
        textView.setText("\ub354 \uc774\uc0c1 \ud655\uc778\ud558\uc9c0 \uc54a\uc74c");
        if (this.model.getNoMoreShowColorCode() == null || this.model.getNoMoreShowColorCode().length() < 1) {
            str = "#000000";
        } else {
            str = this.model.getNoMoreShowColorCode();
        }
        textView.setTextColor(Color.parseColor(str));
        DialogUtil.setTextViewSize(context, textView, 26);
        textView.setLayoutParams(layoutParams3);
        textView.setTypeface(null, 1);
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                viralCheckbox.setChecked(!viralCheckbox.isChecked());
            }
        });
        View button = new Button(context);
        button.setLayoutParams(new LayoutParams(DialogUtil.convertPixelToDP(context, 88, false), DialogUtil.convertPixelToDP(context, 60, false)));
        button.setPadding(0, 0, 0, 0);
        button.setText(ABLanguage.getInstance(context).getMessageByLocale(String.CLOSE));
        button.setGravity(17);
        button.setTypeface(null, 1);
        if (this.model.getCloseBtnTextColorCode() == null || this.model.getCloseBtnTextColorCode().length() < 1) {
            str = "#FFFFFF";
        } else {
            str = this.model.getCloseBtnTextColorCode();
        }
        button.setTextColor(Color.parseColor(str));
        DialogUtil.setTextViewSize(context, button, 28);
        button.setBackgroundDrawable(gradientDrawable4);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ViralInfoDialog.this.dismiss();
            }
        });
        this.noMoreLl.addView(viralCheckbox);
        this.noMoreLl.addView(textView);
        linearLayout4.addView(this.noMoreLl);
        linearLayout4.addView(button);
        frameLayout.addView(linearLayout3);
        frameLayout.addView(this.innerView);
        this.mainContentsLl.addView(linearLayout2);
        this.mainContentsLl.addView(frameLayout);
        this.mainContentsLl.addView(linearLayout4);
        switch (this.type) {
            case TwitterResponse.READ /*1*/:
                setMainInfoContents(context, this.innerView);
                break;
            case TwitterResponse.READ_WRITE /*2*/:
                setViralChannelContents(context, this.innerView);
                break;
            default:
                setMainInfoContents(context, this.innerView);
                break;
        }
        this.rootFl.addView(this.mainContentsLl);
        Animation scaleAnimation = new ScaleAnimation(0.0f, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER, 0.0f, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(300);
        scaleAnimation.setFillAfter(false);
        scaleAnimation.setInterpolator(new AccelerateInterpolator(2.0f));
        scaleAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                if (ViralInfoDialog.this.type == 2) {
                    ViralInfoDialog.this.addSharingView(context);
                }
            }
        });
        this.rootFl.startAnimation(scaleAnimation);
        return this.rootFl;
    }

    private void setMainInfoContents(Context context, LinearLayout linearLayout) {
        String str;
        ViewGroup.LayoutParams layoutParams;
        final Context context2;
        linearLayout.setGravity(1);
        View textView = new TextView(context);
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(DialogUtil.convertPixelToDP(context, 600, false), -2);
        CharSequence viralInfoTitle = (this.model.getViralInfoTitle() == null || this.model.getViralInfoTitle().length() < 1) ? "<strong>\ucd94\uac00<br/>\ud61c\ud0dd</strong>" : this.model.getViralInfoTitle();
        textView.setText(viralInfoTitle);
        textView.setGravity(17);
        textView.setTypeface(null, 1);
        if (this.model.getTopbarTitleTextColorCode() == null || this.model.getTopbarTitleTextColorCode().length() < 1) {
            str = "#FFFFFF";
        } else {
            str = this.model.getTopbarTitleTextColorCode();
        }
        textView.setTextColor(Color.parseColor(str));
        DialogUtil.setTextViewSize(context, textView, 38);
        textView.setLayoutParams(layoutParams2);
        textView.setMaxLines(2);
        ((ViewGroup) this.mainContentsLl.findViewWithTag("titleLl")).addView(textView);
        View frameLayout = new FrameLayout(context);
        final View linearLayout2 = new LinearLayout(context);
        linearLayout2.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        linearLayout2.setGravity(1);
        frameLayout = new TextView(context);
        str = (this.model.getRewardText() == null || this.model.getRewardText().length() < 1) ? "<strong>\ucd94\uac00<br/>\ud61c\ud0dd</strong>" : this.model.getRewardText();
        frameLayout.setText(Html.fromHtml(str));
        frameLayout.setTypeface(null, 1);
        frameLayout.setTextColor(Color.parseColor("#2a3d69"));
        DialogUtil.setTextViewSize(context, frameLayout, 26);
        frameLayout = new TextView(context);
        str = (this.model.getRewardDetailText() == null || this.model.getRewardDetailText().length() < 1) ? "\uce5c\uad6c\ub97c \ucd08\ub300\ud560 \ub54c\ub9c8\ub2e4<br/><font color=\"#62C3BD\"><big><u>\ubb34\ub8cc \uc544\uc774\ud15c</u></big></font>\uc744 \ub4dc\ub824\uc694~!" : this.model.getRewardDetailText();
        frameLayout.setText(Html.fromHtml(str));
        frameLayout.setTypeface(null, 1);
        frameLayout.setTextColor(-16777216);
        DialogUtil.setTextViewSize(context, frameLayout, 30);
        frameLayout = new TextView(context);
        if (this.model.getCheckRewardText() == null || this.model.getCheckRewardText().length() < 1) {
            str = "<font color=\"#9BABBB\">(\uc544\uc774\ud15c \uc9c0\uae09 \ub0b4\uc5ed\uc740 \ucc38\uc5ec\uc774\ub825\uc5d0\uc11c \ud655\uc778\uac00\ub2a5)</font>";
        } else {
            str = this.model.getCheckRewardText();
        }
        frameLayout.setText(Html.fromHtml(str));
        frameLayout.setTypeface(null, 1);
        frameLayout.setTextColor(Color.parseColor("#9ba9bc"));
        DialogUtil.setTextViewSize(context, frameLayout, 20);
        final View imageView = new ImageView(context);
        ViewGroup.LayoutParams layoutParams3 = new LayoutParams(DialogUtil.convertPixelToDP(context, 160, false), DialogUtil.convertPixelToDP(context, 160, false));
        imageView.setLayoutParams(layoutParams3);
        imageView.setScaleType(ScaleType.FIT_CENTER);
        frameLayout = new ViralRewardTextView(context);
        frameLayout.setTextColor(-1);
        frameLayout.setText(new StringBuilder(Operation.PLUS).append(this.model.getItemQuantity()).append(" ").append(this.model.getItemName()).toString());
        frameLayout.setTypeface(null, 1);
        frameLayout.invalidate();
        if (this.model.getItemURL() == null || this.model.getItemURL().length() < 1 || !this.model.getItemURL().startsWith("http")) {
            imageView.setVisibility(8);
            LayoutParams layoutParams4 = new LayoutParams(-2, -1);
            frameLayout.setLayoutParams(layoutParams4);
            frameLayout.setGravity(17);
            DialogUtil.setTextViewSize(context, frameLayout, 36);
        } else {
            imageView.setVisibility(0);
            layoutParams3 = new LayoutParams(-2, -2);
            frameLayout.setLayoutParams(layoutParams3);
            DialogUtil.setTextViewSize(context, frameLayout, 28);
            if (CommonHelper.CheckPermissionForCommonSDK(context)) {
                CPECompletionHandler.getImageDownloader(context).download(this.model.getItemURL(), null, null, null, new ImageDownloadAsyncCallback(this.model.getItemURL(), null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                    public void onResultCustom(Bitmap bitmap) {
                        imageView.setBackgroundDrawable(new BitmapDrawable(bitmap));
                    }
                });
                layoutParams = layoutParams3;
            } else {
                context2 = context;
                InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                    public void run() {
                        final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL(ViralInfoDialog.this.model.getItemURL());
                        Handler handler = new Handler(context2.getMainLooper());
                        final ImageView imageView = imageView;
                        handler.post(new Runnable() {
                            public void run() {
                                try {
                                    imageView.setBackgroundDrawable(new BitmapDrawable(bitmapFromURL));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
                layoutParams = layoutParams3;
            }
        }
        if (this.isPortrait) {
            ViewGroup.LayoutParams layoutParams5 = new LayoutParams(DialogUtil.convertPixelToDP(context, 468, true), DialogUtil.convertPixelToDP(context, 484, false));
            layoutParams5.topMargin = DialogUtil.convertPixelToDP(context, 45, false);
            frameLayout.setLayoutParams(layoutParams5);
            linearLayout2.setOrientation(1);
            frameLayout.setGravity(17);
            frameLayout.setGravity(17);
            layoutParams5 = new FrameLayout.LayoutParams(-1, -2);
            frameLayout.setPadding(DialogUtil.convertPixelToDP(context, 13, false), DialogUtil.convertPixelToDP(context, 13, false), 0, 0);
            frameLayout.setLayoutParams(layoutParams5);
            layoutParams5 = new LayoutParams(-1, -2);
            if (this.model.getItemURL() == null || this.model.getItemURL().length() < 1 || !this.model.getItemURL().startsWith("http")) {
                layoutParams5.topMargin = DialogUtil.convertPixelToDP(context, 140, false);
                layoutParams4.bottomMargin = DialogUtil.convertPixelToDP(context, 84, false);
            } else {
                layoutParams5.topMargin = DialogUtil.convertPixelToDP(context, 94, false);
            }
            frameLayout.setLayoutParams(layoutParams5);
            layoutParams = new LayoutParams(-1, -2);
            layoutParams.topMargin = DialogUtil.convertPixelToDP(context, 8, false);
            frameLayout.setLayoutParams(layoutParams);
            layoutParams3.topMargin = DialogUtil.convertPixelToDP(context, 30, false);
            if (CommonHelper.CheckPermissionForCommonSDK(context)) {
                CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/viral/cpi_dialog_box_01_new.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/viral/cpi_dialog_box_01_new.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                    public void onResultCustom(Bitmap bitmap) {
                        linearLayout2.setBackgroundDrawable(new BitmapDrawable(bitmap));
                    }
                });
            } else {
                context2 = context;
                InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                    public void run() {
                        final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/viral/cpi_dialog_box_01_new.png");
                        Handler handler = new Handler(context2.getMainLooper());
                        final LinearLayout linearLayout = linearLayout2;
                        handler.post(new Runnable() {
                            public void run() {
                                try {
                                    linearLayout.setBackgroundDrawable(new BitmapDrawable(bitmapFromURL));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
            }
            linearLayout2.addView(frameLayout);
            linearLayout2.addView(frameLayout);
            linearLayout2.addView(imageView);
            linearLayout2.addView(frameLayout);
        } else {
            layoutParams = new LayoutParams(DialogUtil.convertPixelToDP(context, 818, true), DialogUtil.convertPixelToDP(context, 234, false));
            layoutParams.topMargin = DialogUtil.convertPixelToDP(context, 20, false);
            frameLayout.setLayoutParams(layoutParams);
            linearLayout2.setOrientation(0);
            frameLayout.setGravity(17);
            frameLayout.setGravity(17);
            layoutParams = new FrameLayout.LayoutParams(DialogUtil.convertPixelToDP(context, HttpResponseCode.BAD_REQUEST, false), -2);
            frameLayout.setPadding(DialogUtil.convertPixelToDP(context, 13, false), DialogUtil.convertPixelToDP(context, 13, false), 0, 0);
            frameLayout.setLayoutParams(layoutParams);
            layoutParams = new LayoutParams(-1, -2);
            layoutParams.topMargin = DialogUtil.convertPixelToDP(context, 57, false);
            frameLayout.setLayoutParams(layoutParams);
            layoutParams = new LayoutParams(-1, -2);
            layoutParams.topMargin = DialogUtil.convertPixelToDP(context, 8, false);
            frameLayout.setLayoutParams(layoutParams);
            frameLayout = new LinearLayout(context);
            layoutParams = new LayoutParams(DialogUtil.convertPixelToDP(context, 388, false), -2);
            layoutParams.rightMargin = DialogUtil.convertPixelToDP(context, 67, false);
            frameLayout.setOrientation(1);
            frameLayout.setLayoutParams(layoutParams);
            layoutParams3.topMargin = DialogUtil.convertPixelToDP(context, 15, false);
            if (CommonHelper.CheckPermissionForCommonSDK(context)) {
                CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/viral/cpi_dialog_box_01_land_new.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/viral/cpi_dialog_box_01_land_new.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                    public void onResultCustom(Bitmap bitmap) {
                        linearLayout2.setBackgroundDrawable(new BitmapDrawable(bitmap));
                    }
                });
            } else {
                context2 = context;
                InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                    public void run() {
                        final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/viral/cpi_dialog_box_01_land_new.png");
                        Handler handler = new Handler(context2.getMainLooper());
                        final LinearLayout linearLayout = linearLayout2;
                        handler.post(new Runnable() {
                            public void run() {
                                try {
                                    linearLayout.setBackgroundDrawable(new BitmapDrawable(bitmapFromURL));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
            }
            View linearLayout3 = new LinearLayout(context);
            linearLayout3.setLayoutParams(new LayoutParams(-2, -1));
            linearLayout3.setOrientation(1);
            linearLayout3.setGravity(1);
            linearLayout3.addView(imageView);
            linearLayout3.addView(frameLayout);
            frameLayout.addView(frameLayout);
            frameLayout.addView(frameLayout);
            linearLayout2.addView(frameLayout);
            linearLayout2.addView(linearLayout3);
        }
        textView = new LinearLayout(context);
        textView.setLayoutParams(new LayoutParams(DialogUtil.convertPixelToDP(context, 600, false), -1));
        textView.setGravity(17);
        View button = new Button(context);
        button.setLayoutParams(new LayoutParams(DialogUtil.convertPixelToDP(context, 333, false), DialogUtil.convertPixelToDP(context, 80, false)));
        viralInfoTitle = (this.model.getViralConfirmBtnText() == null || this.model.getViralConfirmBtnText().length() < 1) ? "\ucd08\ub300\ud558\uae30" : this.model.getViralConfirmBtnText();
        button.setText(viralInfoTitle);
        button.setGravity(17);
        button.setTypeface(null, 1);
        button.setPadding(0, 0, 0, 0);
        if (this.model.getViralConfirmBtnTextColorCode() == null || this.model.getViralConfirmBtnTextColorCode().length() < 1) {
            str = "#FFFFFF";
        } else {
            str = this.model.getViralConfirmBtnTextColorCode();
        }
        button.setTextColor(Color.parseColor(str));
        DialogUtil.setTextViewSize(context, button, 34);
        context2 = context;
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                try {
                    ViralInfoDialog.this.dismiss();
                    ViralInfoDialog viralInfoDialog = new ViralInfoDialog(context2, ViralInfoDialog.this.model, 2);
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.copyFrom(viralInfoDialog.getWindow().getAttributes());
                    layoutParams.width = -1;
                    layoutParams.height = -2;
                    viralInfoDialog.getWindow().setAttributes(layoutParams);
                    viralInfoDialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Drawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius((float) DialogUtil.convertPixelToDP(context, 10, true));
        str = (this.model.getViralConfirmBtnColorCode() == null || this.model.getViralConfirmBtnColorCode().length() < 1) ? "#4b79cd" : this.model.getViralConfirmBtnColorCode();
        gradientDrawable.setColor(Color.parseColor(str));
        gradientDrawable.setSize(DialogUtil.convertPixelToDP(context, 333, true), DialogUtil.convertPixelToDP(context, 80, false));
        button.setBackgroundDrawable(gradientDrawable);
        textView.addView(button);
        frameLayout.addView(linearLayout2);
        frameLayout.addView(frameLayout);
        linearLayout.addView(frameLayout);
        linearLayout.addView(textView);
    }

    private void setViralChannelContents(final Context context, LinearLayout linearLayout) {
        String str;
        this.noMoreLl.setVisibility(4);
        linearLayout.setGravity(1);
        View textView = new TextView(context);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(DialogUtil.convertPixelToDP(context, 600, false), -2);
        CharSequence sharingTitle = (this.model.getSharingTitle() == null || this.model.getSharingTitle().length() < 1) ? "\ucd08\ub300\ud558\uae30" : this.model.getSharingTitle();
        textView.setText(sharingTitle);
        textView.setGravity(17);
        textView.setTypeface(null, 1);
        if (this.model.getTopbarTitleTextColorCode() == null || this.model.getTopbarTitleTextColorCode().length() < 1) {
            str = "#FFFFFF";
        } else {
            str = this.model.getTopbarTitleTextColorCode();
        }
        textView.setTextColor(Color.parseColor(str));
        DialogUtil.setTextViewSize(context, textView, 38);
        textView.setLayoutParams(layoutParams);
        ((ViewGroup) this.mainContentsLl.findViewWithTag("titleLl")).addView(textView);
        this.completeCopyTv = new TextView(context);
        this.completeCopyTvParam = new LayoutParams(-1, -2);
        this.completeCopyTv.setLayoutParams(this.completeCopyTvParam);
        this.completeCopyTv.setTypeface(null, 1);
        this.completeCopyTv.setTextColor(Color.parseColor("#4b79cd"));
        this.completeCopyTv.setGravity(17);
        DialogUtil.setTextViewSize(context, this.completeCopyTv, 40);
        this.shareContentsAreaLl = new LinearLayout(context);
        this.shareContentsAreaLl.setOrientation(1);
        this.selectViralChannelTv = new TextView(context);
        this.selectViralChannelTvParam = new LayoutParams(-1, -2);
        this.selectViralChannelTv.setLayoutParams(this.selectViralChannelTvParam);
        this.selectViralChannelTv.setTypeface(null, 1);
        this.selectViralChannelTv.setTextColor(-16777216);
        this.selectViralChannelTv.setGravity(17);
        DialogUtil.setTextViewSize(context, this.selectViralChannelTv, 30);
        this.mainShareLl = new LinearLayout(context);
        this.mainShareLlParam = new LayoutParams(-1, -1);
        this.mainShareLl.setLayoutParams(this.mainShareLlParam);
        this.mainShareLl.setGravity(17);
        this.mainShareLl.setOrientation(1);
        TextView textView2;
        ViewGroup.LayoutParams layoutParams2;
        if (this.isPortrait) {
            this.completeCopyTvParam.topMargin = DialogUtil.convertPixelToDP(context, 36, false);
            textView2 = this.completeCopyTv;
            if (this.model.getCompleteCopyText() == null || this.model.getCompleteCopyText().length() < 1) {
                str = "\ucd08\ub300 \uba54\uc138\uc9c0\uac00<br/>\ubcf5\uc0ac\ub418\uc5c8\uc2b5\ub2c8\ub2e4.";
            } else {
                str = ViralConstant.resetLineOfHtmlString(this.model.getCompleteCopyText(), 2);
            }
            textView2.setText(Html.fromHtml(str));
            layoutParams2 = new LayoutParams(DialogUtil.convertPixelToDP(context, 468, false), DialogUtil.convertPixelToDP(context, 484, false));
            layoutParams2.topMargin = DialogUtil.convertPixelToDP(context, 24, false);
            layoutParams2.bottomMargin = DialogUtil.convertPixelToDP(context, 24, false);
            this.shareContentsAreaLl.setLayoutParams(layoutParams2);
            this.selectViralChannelTvParam.topMargin = DialogUtil.convertPixelToDP(context, 46, false);
            textView2 = this.selectViralChannelTv;
            if (this.model.getSharingMessage() == null || this.model.getSharingMessage().length() < 1) {
                str = "\ub2e4\uc591\ud55c \uacf5\uc720 \ubc29\ubc95\uc73c\ub85c<br/>\uce5c\uad6c\uc5d0\uac8c \uc571\uc744 \uc18c\uac1c\ud558\uace0<br/>\ucd08\ub300\ud558\uc138\uc694.";
            } else {
                str = ViralConstant.resetLineOfHtmlString(this.model.getSharingMessage(), 3);
            }
            textView2.setText(Html.fromHtml(str));
            if (CommonHelper.CheckPermissionForCommonSDK(context)) {
                CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/viral/cpi_dialog_box_02.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/viral/cpi_dialog_box_02.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                    public void onResultCustom(Bitmap bitmap) {
                        ViralInfoDialog.this.shareContentsAreaLl.setBackgroundDrawable(new BitmapDrawable(bitmap));
                    }
                });
            } else {
                InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                    public void run() {
                        final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/viral/cpi_dialog_box_02.png");
                        new Handler(context.getMainLooper()).post(new Runnable() {
                            public void run() {
                                try {
                                    ViralInfoDialog.this.shareContentsAreaLl.setBackgroundDrawable(new BitmapDrawable(bitmapFromURL));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
            }
        } else {
            this.completeCopyTvParam.topMargin = DialogUtil.convertPixelToDP(context, 33, false);
            textView2 = this.completeCopyTv;
            str = (this.model.getCompleteCopyText() == null || this.model.getCompleteCopyText().length() < 1) ? "\ucd08\ub300 \uba54\uc138\uc9c0\uac00<br/>\ubcf5\uc0ac\ub418\uc5c8\uc2b5\ub2c8\ub2e4." : ViralConstant.resetLineOfHtmlString(this.model.getCompleteCopyText(), 1);
            textView2.setText(Html.fromHtml(str));
            layoutParams2 = new LayoutParams(DialogUtil.convertPixelToDP(context, 818, false), DialogUtil.convertPixelToDP(context, 256, false));
            layoutParams2.topMargin = DialogUtil.convertPixelToDP(context, 28, false);
            this.shareContentsAreaLl.setLayoutParams(layoutParams2);
            this.selectViralChannelTvParam.topMargin = DialogUtil.convertPixelToDP(context, 34, false);
            this.selectViralChannelTv.setMaxLines(2);
            textView2 = this.selectViralChannelTv;
            if (this.model.getSharingMessage() == null || this.model.getSharingMessage().length() < 1) {
                str = "\ub2e4\uc591\ud55c \uacf5\uc720 \ubc29\ubc95\uc73c\ub85c<br/>\uce5c\uad6c\uc5d0\uac8c \uc571\uc744 \uc18c\uac1c\ud558\uace0<br/>\ucd08\ub300\ud558\uc138\uc694.";
            } else {
                str = ViralConstant.resetLineOfHtmlString(this.model.getSharingMessage(), 2);
            }
            textView2.setText(Html.fromHtml(str));
            if (CommonHelper.CheckPermissionForCommonSDK(context)) {
                CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/viral/cpi_dialog_box_01_land.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/viral/cpi_dialog_box_01_land.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                    public void onResultCustom(Bitmap bitmap) {
                        ViralInfoDialog.this.shareContentsAreaLl.setBackgroundDrawable(new BitmapDrawable(bitmap));
                    }
                });
            } else {
                InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                    public void run() {
                        final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/viral/cpi_dialog_box_01_land.png");
                        new Handler(context.getMainLooper()).post(new Runnable() {
                            public void run() {
                                try {
                                    ViralInfoDialog.this.shareContentsAreaLl.setBackgroundDrawable(new BitmapDrawable(bitmapFromURL));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
            }
        }
        this.shareContentsAreaLl.addView(this.selectViralChannelTv);
        this.shareContentsAreaLl.addView(this.mainShareLl);
        this.innerView.addView(this.completeCopyTv);
        this.innerView.addView(this.shareContentsAreaLl);
    }

    @SuppressLint({"NewApi"})
    private void addSharingView(final Context context) {
        final Handler handler = new Handler();
        RequestParameter aTRequestParameter = RequestParameter.getATRequestParameter(context);
        String aDBrixUserInfo_Refusn = aTRequestParameter.getADBrixUserInfo_Refusn();
        if (aDBrixUserInfo_Refusn == null || aDBrixUserInfo_Refusn.length() <= 0) {
            IgawLogger.Logging(context, "IGAW_QA", "usn is null", 3);
            this.innerView.setGravity(17);
            this.innerView.removeView(this.shareContentsAreaLl);
            this.completeCopyTv.setText(ABLanguage.getInstance(context).getMessageByLocale("canNotParticipate"));
            return;
        }
        final View progressBar = new ProgressBar(context);
        progressBar.setLayoutParams(new LayoutParams(DialogUtil.convertPixelToDP(context, 80, false), DialogUtil.convertPixelToDP(context, 80, false)));
        this.mainShareLl.addView(progressBar);
        ADBrixHttpManager.getManager(context).getViralUrl(context, aTRequestParameter.getADBrixUserInfo_SubReferralKey(), this.model.getCampaignKey(), this.model.getConversionKey(), aDBrixUserInfo_Refusn, new ADBrixHttpCallbackListener<ViralUrlModel>() {
            public void onResponse(final ViralUrlModel viralUrlModel) {
                Handler handler = handler;
                final ProgressBar progressBar = progressBar;
                final Context context = context;
                handler.post(new Runnable() {
                    public void run() {
                        ViralInfoDialog.this.mainShareLl.removeView(progressBar);
                        if (viralUrlModel == null) {
                            ViralInfoDialog.this.innerView.setGravity(17);
                            ViralInfoDialog.this.innerView.removeView(ViralInfoDialog.this.shareContentsAreaLl);
                            ViralInfoDialog.this.completeCopyTv.setText(ABLanguage.getInstance(context).getMessageByLocale("canNotParticipate"));
                            IgawLogger.Logging(context, "IGAW_QA", "result is null", 3);
                        } else if (viralUrlModel.isResult()) {
                            if (!ViralInfoDialog.this.model.isIsTrackingURLSetting()) {
                                ViralInfoDialog.this.model.setViralMessage(new StringBuilder(String.valueOf(ViralInfoDialog.this.model.getViralMessage())).append("\n").append(viralUrlModel.getTrackingURL()).toString());
                                ViralInfoDialog.this.model.setIsTrackingURLSetting(true);
                            }
                            if (VERSION.SDK_INT < 11) {
                                ((ClipboardManager) context.getSystemService("clipboard")).setText(ViralInfoDialog.this.model.getViralMessage());
                            } else {
                                ((android.content.ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Copied Text", ViralInfoDialog.this.model.getViralMessage()));
                            }
                            ViralInfoDialog.this.addViralChannel(context, ViralInfoDialog.this.mainShareLl);
                        } else {
                            ViralInfoDialog.this.innerView.setGravity(17);
                            ViralInfoDialog.this.innerView.removeView(ViralInfoDialog.this.shareContentsAreaLl);
                            ViralInfoDialog.this.completeCopyTv.setText(ABLanguage.getInstance(context).getMessageByLocale("canNotParticipate"));
                            IgawLogger.Logging(context, "IGAW_QA", "result is false", 3);
                        }
                    }
                });
            }
        });
    }

    public void addViralChannel(Context context, LinearLayout linearLayout) {
        DialogUtil.convertPixelToDP(context, 20, false);
        List arrayList = new ArrayList();
        LinearLayout linearLayout2 = null;
        for (final String str : ViralConstant.SHARING_CHANNEL) {
            if (ConditionChecker.checkInstalled(context, str) || str.equals("sms") || str.equals("etc")) {
                int i = this.isPortrait ? 6 : 5;
                int i2 = this.isPortrait ? 3 : 5;
                if (arrayList.size() < i - 2 || str.equals("sms") || str.equals("etc")) {
                    ViewGroup.LayoutParams layoutParams;
                    LinearLayout linearLayout3;
                    if (linearLayout2 == null || linearLayout2.getChildCount() >= i2) {
                        View linearLayout4 = new LinearLayout(context);
                        if (this.isPortrait) {
                            layoutParams = new LayoutParams(DialogUtil.convertPixelToDP(context, 362, false), -2);
                            if (linearLayout.getChildCount() > 0) {
                                layoutParams.topMargin = DialogUtil.convertPixelToDP(context, 40, false);
                            }
                        } else {
                            layoutParams = new LayoutParams(DialogUtil.convertPixelToDP(context, 750, false), -2);
                            linearLayout4.setGravity(17);
                        }
                        linearLayout4.setLayoutParams(layoutParams);
                        linearLayout4.setOrientation(0);
                        linearLayout.addView(linearLayout4);
                        linearLayout3 = linearLayout4;
                    } else {
                        linearLayout3 = linearLayout2;
                    }
                    arrayList.add(str);
                    final View imageView = new ImageView(context);
                    if (this.isPortrait) {
                        layoutParams = new LayoutParams(DialogUtil.convertPixelToDP(context, 94, false), DialogUtil.convertPixelToDP(context, 94, false));
                    } else {
                        layoutParams = new LayoutParams(DialogUtil.convertPixelToDP(context, 84, false), DialogUtil.convertPixelToDP(context, 84, false));
                    }
                    if (linearLayout3.getChildCount() > 0) {
                        if (this.isPortrait) {
                            layoutParams.leftMargin = DialogUtil.convertPixelToDP(context, 40, false);
                        } else {
                            layoutParams.leftMargin = DialogUtil.convertPixelToDP(context, 50, false);
                        }
                    }
                    imageView.setLayoutParams(layoutParams);
                    imageView.setScaleType(ScaleType.FIT_XY);
                    final Context context2;
                    if (str.equals("sms")) {
                        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
                            CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/viral/ic_message.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/viral/ic_message.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                                public void onResultCustom(Bitmap bitmap) {
                                    imageView.setImageDrawable(ViralInfoDialog.this.getStateDrawable(bitmap));
                                }
                            });
                        } else {
                            context2 = context;
                            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                                public void run() {
                                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/viral/ic_message.png");
                                    Handler handler = new Handler(context2.getMainLooper());
                                    final ImageView imageView = imageView;
                                    handler.post(new Runnable() {
                                        public void run() {
                                            try {
                                                imageView.setImageDrawable(ViralInfoDialog.this.getStateDrawable(bitmapFromURL));
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            });
                        }
                        context2 = context;
                        imageView.setOnClickListener(new OnClickListener() {
                            public void onClick(View view) {
                                try {
                                    Intent intent = new Intent("android.intent.action.VIEW");
                                    intent.putExtra("sms_body", ViralInfoDialog.this.model.getViralMessage());
                                    intent.setType("vnd.android-dir/mms-sms");
                                    context2.startActivity(intent);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    } else if (str.equals("etc")) {
                        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
                            CPECompletionHandler.getImageDownloader(context).download("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/viral/ic_share.png", null, null, null, new ImageDownloadAsyncCallback("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/viral/ic_share.png", null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                                public void onResultCustom(Bitmap bitmap) {
                                    imageView.setImageDrawable(ViralInfoDialog.this.getStateDrawable(bitmap));
                                }
                            });
                        } else {
                            context2 = context;
                            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                                public void run() {
                                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL("http://static.adbrix.igaworks.com/adbrix_res/sdk_res/viral/ic_share.png");
                                    Handler handler = new Handler(context2.getMainLooper());
                                    final ImageView imageView = imageView;
                                    handler.post(new Runnable() {
                                        public void run() {
                                            try {
                                                imageView.setImageDrawable(ViralInfoDialog.this.getStateDrawable(bitmapFromURL));
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            });
                        }
                        context2 = context;
                        imageView.setOnClickListener(new OnClickListener() {
                            public void onClick(View view) {
                                try {
                                    Intent intent = new Intent("android.intent.action.SEND");
                                    intent.setType("text/plain");
                                    intent.putExtra("android.intent.extra.TEXT", ViralInfoDialog.this.model.getViralMessage());
                                    context2.startActivity(Intent.createChooser(intent, ABLanguage.getInstance(context2).getMessageByLocale("shareWith")));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    } else {
                        try {
                            imageView.setImageDrawable(getStateDrawable(drawableToBitmap(context.getPackageManager().getApplicationIcon(str))));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        context2 = context;
                        imageView.setOnClickListener(new OnClickListener() {
                            public void onClick(View view) {
                                try {
                                    Intent intent = new Intent("android.intent.action.SEND");
                                    intent.setType("text/plain");
                                    if (str.equals("com.facebook.katana")) {
                                        intent.putExtra("android.intent.extra.TEXT", "  ");
                                    } else {
                                        intent.putExtra("android.intent.extra.TEXT", ViralInfoDialog.this.model.getViralMessage());
                                    }
                                    intent.setPackage(str);
                                    intent.addFlags(805306368);
                                    context2.startActivity(intent);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    linearLayout3.addView(imageView);
                    linearLayout2 = linearLayout3;
                }
            }
        }
    }

    public Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    private Drawable getStateDrawable(Bitmap bitmap) {
        Drawable bitmapDrawable = new BitmapDrawable(bitmap);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_4444);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        Drawable bitmapDrawable2 = new BitmapDrawable(createBitmap);
        ColorMatrix colorMatrix2 = new ColorMatrix();
        colorMatrix2.setSaturation(0.0f);
        bitmapDrawable2.setColorFilter(new ColorMatrixColorFilter(colorMatrix2));
        Drawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, bitmapDrawable2);
        stateListDrawable.addState(new int[]{16842908}, bitmapDrawable2);
        stateListDrawable.addState(new int[0], bitmapDrawable);
        return stateListDrawable;
    }

    public void dismiss() {
        Animation scaleAnimation = new ScaleAnimation(TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER, 0.0f, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER, 0.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(300);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setInterpolator(new AccelerateInterpolator(2.0f));
        scaleAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                super.dismiss();
            }
        });
        this.rootFl.startAnimation(scaleAnimation);
    }
}
