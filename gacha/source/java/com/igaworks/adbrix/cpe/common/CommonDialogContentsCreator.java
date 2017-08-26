package com.igaworks.adbrix.cpe.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.net.Uri;
import android.os.Handler;
import android.util.Pair;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.igaworks.adbrix.core.ADBrixHttpManager;
import com.igaworks.adbrix.cpe.CPECompletionHandler;
import com.igaworks.adbrix.interfaces.ParticipationProgressCallbackListener;
import com.igaworks.adbrix.model.Media;
import com.igaworks.adbrix.model.Promotion;
import com.igaworks.adbrix.model.StepRewardModel;
import com.igaworks.adbrix.util.CPEConstant;
import com.igaworks.adbrix.util.DialogUtil;
import com.igaworks.core.DeviceIDManger;
import com.igaworks.core.DisplaySetter;
import com.igaworks.core.IgawLogger;
import com.igaworks.core.RequestParameter;
import com.igaworks.cpe.ConditionChecker;
import com.igaworks.dao.CPEImpressionDAOFactory;
import com.igaworks.dao.CoreIDDAO;
import com.igaworks.dao.NotAvailableCampaignDAO;
import com.igaworks.dao.tracking.TrackingActivitySQLiteDB;
import com.igaworks.impl.InternalAction;
import com.igaworks.model.ParticipationProgressModel;
import com.igaworks.model.ParticipationProgressResponseModel;
import com.igaworks.util.CommonHelper;
import com.igaworks.util.image.ImageCacheFactory;
import com.igaworks.util.image.ImageDownloadAsyncCallback;
import com.tapjoy.TapjoyConnectCore;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import jp.co.vaz.creator.hikaru.R;
import org.cocos2dx.lib.BuildConfig;

public abstract class CommonDialogContentsCreator implements ParticipationProgressCallbackListener {
    protected DialogActionListener actionListener;
    protected Activity activity;
    protected LinearLayout adImageSectionLl;
    protected int adImageSectionPadding;
    protected int adImageSectionTitleMargin;
    protected TextView adTitleTv;
    protected List<Integer> campaignKeys;
    protected SparseArray<LinearLayout> campaignThumbnails;
    protected ImageView closeBtnIv;
    private FrameLayout containerLayout;
    protected LinearLayout contentsMainLl;
    protected int contentsMainMargin;
    protected Context context;
    protected int currentCampaignKey;
    protected int currentSlideNo = -1;
    protected int dialogMainPadding;
    protected int dialogRound;
    protected int dividerSize;
    protected Handler handler;
    protected List<Integer> impressionAddedCampaign;
    protected TextView isCompleteTitleTv;
    protected boolean isPortrait;
    private OnClickListener landingBtnClickLisetner = new OnClickListener() {
        public void onClick(View view) {
            try {
                boolean z;
                if (ConditionChecker.checkInstalled(CommonDialogContentsCreator.this.context, ((Promotion) CommonDialogContentsCreator.this.promotions.get(CommonDialogContentsCreator.this.currentCampaignKey)).getTargetAppScheme())) {
                    CommonDialogContentsCreator.this.context.startActivity(CommonDialogContentsCreator.this.context.getPackageManager().getLaunchIntentForPackage(((Promotion) CommonDialogContentsCreator.this.promotions.get(CommonDialogContentsCreator.this.currentCampaignKey)).getTargetAppScheme()));
                } else {
                    Object stringBuilder;
                    RequestParameter aTRequestParameter = RequestParameter.getATRequestParameter(CommonDialogContentsCreator.this.context);
                    String googleAdId = CoreIDDAO.getInstance().getGoogleAdId(CommonDialogContentsCreator.this.context);
                    List<Pair> persistantDemoInfo_v2 = aTRequestParameter.getPersistantDemoInfo_v2();
                    String clickUrl = ((Promotion) CommonDialogContentsCreator.this.promotions.get(CommonDialogContentsCreator.this.currentCampaignKey)).getDisplay().getClickUrl();
                    String query = Uri.parse(clickUrl).getQuery();
                    if (persistantDemoInfo_v2 != null) {
                        String str;
                        for (Pair pair : persistantDemoInfo_v2) {
                            if (((String) pair.first).equals("userId")) {
                                str = (String) pair.second;
                                break;
                            }
                        }
                        str = null;
                        if (str == null) {
                            str = BuildConfig.FLAVOR;
                        }
                        if (query == null || query.length() <= 0) {
                            stringBuilder = new StringBuilder(String.valueOf(clickUrl)).append("?usn=").append(Uri.encode(str)).toString();
                        } else {
                            stringBuilder = new StringBuilder(String.valueOf(clickUrl)).append("&usn=").append(Uri.encode(str)).toString();
                        }
                    } else if (query == null || query.length() <= 0) {
                        stringBuilder = new StringBuilder(String.valueOf(clickUrl)).append("?usn=").toString();
                    } else {
                        stringBuilder = new StringBuilder(String.valueOf(clickUrl)).append("&usn=").toString();
                    }
                    String stringBuilder2 = new StringBuilder(String.valueOf(stringBuilder)).append("&agreement_key=").append(googleAdId).append("&src_appkey=").append(aTRequestParameter.getAppkey()).append("&r_key=").append(((Promotion) CommonDialogContentsCreator.this.promotions.get(CommonDialogContentsCreator.this.currentCampaignKey)).getDisplay().getSlide().getResourceKey()).toString();
                    IgawLogger.Logging(CommonDialogContentsCreator.this.context, "IGAW_QA", "Adbrix > promotion landing url : " + stringBuilder2, 3);
                    if (((Promotion) CommonDialogContentsCreator.this.promotions.get(CommonDialogContentsCreator.this.currentCampaignKey)).getDisplay().isIsMarketUrl()) {
                        CommonDialogContentsCreator.this.webview = new WebView(CommonDialogContentsCreator.this.context);
                        CommonDialogContentsCreator.this.webviewParam = new LayoutParams(-2, -2);
                        CommonDialogContentsCreator.this.webview.setVerticalScrollBarEnabled(false);
                        CommonDialogContentsCreator.this.webview.setHorizontalScrollBarEnabled(false);
                        CommonDialogContentsCreator.this.webview.setBackgroundColor(-1);
                        CommonDialogContentsCreator.this.webview.setWebViewClient(new IgawPromotionWebViewClient());
                        CommonDialogContentsCreator.this.webview.loadUrl(stringBuilder2);
                    } else {
                        CommonDialogContentsCreator.this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder2)));
                    }
                }
                Context context = CommonDialogContentsCreator.this.context;
                String str2 = "IGAW_QA";
                StringBuilder stringBuilder3 = new StringBuilder("Adbrix > actionListener is null : ");
                if (CommonDialogContentsCreator.this.actionListener == null) {
                    z = true;
                } else {
                    z = false;
                }
                IgawLogger.Logging(context, str2, stringBuilder3.append(z).toString(), 3);
                if (CommonDialogContentsCreator.this.actionListener != null) {
                    CommonDialogContentsCreator.this.actionListener.onPlayBtnClick();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    protected Media media;
    protected TextView missionTitleTv;
    protected TextView notAvailableTv;
    private OnClickListener onFailBtnClickListener = new OnClickListener() {
        public void onClick(View view) {
            Toast.makeText(CommonDialogContentsCreator.this.context, "\ucc38\uc5ec \uc815\ubcf4\ub97c \uac00\uc838\uc624\uc9c0 \ubabb\ud588\uc2b5\ub2c8\ub2e4. \uc7a0\uc2dc \ud6c4 \ub2e4\uc2dc \uc2dc\ub3c4\ud574 \uc8fc\uc138\uc694.", 0).show();
        }
    };
    protected boolean onGetProgressModel = false;
    private OnClickListener onReadyBtnClickListener = new OnClickListener() {
        public void onClick(View view) {
            Toast.makeText(CommonDialogContentsCreator.this.context, "\ucc38\uc5ec \uc815\ubcf4\ub97c \uac00\uc838\uc624\ub294 \uc911\uc785\ub2c8\ub2e4.", 0).show();
        }
    };
    protected ImageView playBtnIv;
    protected LinearLayout playBtnLl;
    protected int primaryCampaignKey;
    protected FrameLayout progressCircle;
    protected ParticipationProgressResponseModel progressModel;
    protected SparseArray<ParticipationProgressResponseModel> progressModels;
    protected SparseArray<Promotion> promotions;
    protected List<Integer> rCks;
    protected ImageView rewardIv;
    protected ShapeDrawable roundedActiveThumbSd;
    protected ShapeDrawable roundedInactiveThumbSd;
    protected Shape roundedThumbShape;
    protected boolean showIcon;
    protected FrameLayout slideArea;
    protected String spaceKey;
    protected int stepListColumnMargin;
    protected LinearLayout stepListLl;
    protected FrameLayout stepLoadingFl;
    protected LinearLayout stepRewardContainer;
    protected int stepRewardWidth;
    protected int thumbBorderWidth;
    protected int thumbnailArrowSize;
    protected int thumbnailItemMargin;
    protected int thumbnailItemSize;
    protected int thumbnailListPadding;
    protected HorizontalScrollView thumbnailListSv;
    protected WebView webview;
    protected LayoutParams webviewParam;
    protected int windowPadding;

    private class IgawPromotionWebViewClient extends WebViewClient {
        private IgawPromotionWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            IgawLogger.Logging(CommonDialogContentsCreator.this.context, "IGAW_QA", String.format("IgawPromotionWebViewClient >> shouldOverrideUrlLoading : %s", new Object[]{str}), 2, false);
            if (str.startsWith("http")) {
                return true;
            }
            CommonDialogContentsCreator.this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            return false;
        }
    }

    private class StepRewardListAdapter extends ArrayAdapter<StepRewardModel> {
        public int checkIvSize;
        private Context context;
        public int rowHeight;

        public StepRewardListAdapter(Context context, int i) {
            super(context, i);
            this.context = context;
            this.rowHeight = CPEConstant.convertPixelToDP(context, 39, false);
            this.checkIvSize = CPEConstant.convertPixelToDP(context, 30, false);
        }

        public int getCount() {
            return ((Promotion) CommonDialogContentsCreator.this.promotions.get(CommonDialogContentsCreator.this.currentCampaignKey)).getDisplay().getStepReward().size();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View linearLayout = new LinearLayout(this.context);
            try {
                linearLayout.setLayoutParams(new AbsListView.LayoutParams(-1, -2));
                ((LinearLayout) linearLayout).setOrientation(1);
                linearLayout.setBackgroundColor(Color.parseColor(i % 2 == 0 ? "#242d3e" : "#20293b"));
                View linearLayout2 = new LinearLayout(this.context);
                ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, this.rowHeight);
                linearLayout2.setGravity(17);
                int convertPixelToDP = CPEConstant.convertPixelToDP(this.context, 5, true);
                linearLayout2.setPadding(0, convertPixelToDP, 0, convertPixelToDP);
                linearLayout2.setLayoutParams(layoutParams);
                linearLayout2.setOrientation(0);
                View textView = new TextView(this.context);
                layoutParams = new LayoutParams(CommonDialogContentsCreator.this.missionTitleTv.getWidth(), -2);
                textView.setId(14745);
                textView.setLayoutParams(layoutParams);
                textView.setGravity(17);
                textView.setText(((StepRewardModel) ((Promotion) CommonDialogContentsCreator.this.promotions.get(CommonDialogContentsCreator.this.currentCampaignKey)).getDisplay().getStepReward().get(i)).getName());
                textView.setTextColor(Color.parseColor("#657084"));
                textView.setTypeface(null, 1);
                CPEConstant.setTextViewSize(this.context, textView, 15);
                View textView2 = new TextView(this.context);
                textView2.setLayoutParams(new LayoutParams(CommonDialogContentsCreator.this.rewardIv.getWidth(), -2));
                textView2.setGravity(17);
                textView2.setText(new StringBuilder(String.valueOf(((StepRewardModel) ((Promotion) CommonDialogContentsCreator.this.promotions.get(CommonDialogContentsCreator.this.currentCampaignKey)).getDisplay().getStepReward().get(i)).getReward())).toString());
                textView2.setTextColor(Color.parseColor("#657084"));
                textView2.setTypeface(null, 1);
                CPEConstant.setTextViewSize(this.context, textView2, 15);
                final View imageView = new ImageView(this.context);
                imageView.setLayoutParams(new LayoutParams(CommonDialogContentsCreator.this.isCompleteTitleTv.getWidth(), -1));
                imageView.setScaleType(ScaleType.FIT_CENTER);
                String missionCheckOff = CommonDialogContentsCreator.this.media.getTheme().getMissionCheckOff();
                if (((StepRewardModel) ((Promotion) CommonDialogContentsCreator.this.promotions.get(CommonDialogContentsCreator.this.currentCampaignKey)).getDisplay().getStepReward().get(i)).isComplete()) {
                    missionCheckOff = CommonDialogContentsCreator.this.media.getTheme().getMissionCheckOn();
                    textView.setTextColor(Color.parseColor("#ffffff"));
                    textView2.setTextColor(Color.parseColor("#ffffff"));
                }
                if (CommonHelper.CheckPermissionForCommonSDK(this.context)) {
                    final View view2 = imageView;
                    CPECompletionHandler.getImageDownloader(this.context).download(missionCheckOff, imageView, null, null, new ImageDownloadAsyncCallback(missionCheckOff, imageView, ImageCacheFactory.getInstance().get("imagecache"), null) {
                        public void onResultCustom(Bitmap bitmap) {
                            view2.setImageBitmap(bitmap);
                        }
                    });
                } else {
                    InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                        public void run() {
                            final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL(missionCheckOff);
                            Handler handler = new Handler(StepRewardListAdapter.this.context.getMainLooper());
                            final ImageView imageView = imageView;
                            handler.post(new Runnable() {
                                public void run() {
                                    try {
                                        imageView.setImageBitmap(bitmapFromURL);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    });
                }
                linearLayout2.addView(textView);
                linearLayout2.addView(textView2);
                linearLayout2.addView(imageView);
                ((LinearLayout) linearLayout).addView(linearLayout2);
                ((LinearLayout) linearLayout).addView(CommonDialogContentsCreator.this.getDividerView(1));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return linearLayout;
        }
    }

    public abstract void changePromotionContents();

    public abstract void finishDialog();

    public abstract void setSlideImageSection();

    public int getCurrentCampaignKey() {
        return this.currentCampaignKey;
    }

    protected CommonDialogContentsCreator(Context context, Activity activity, Media media, List<Integer> list, SparseArray<Promotion> sparseArray, boolean z, int i, int i2, String str, DialogActionListener dialogActionListener, Handler handler, boolean z2) {
        this.spaceKey = str;
        this.media = media;
        this.context = context;
        this.activity = activity;
        this.campaignKeys = list;
        this.promotions = sparseArray;
        this.impressionAddedCampaign = new ArrayList();
        this.isPortrait = z;
        this.currentCampaignKey = i;
        this.primaryCampaignKey = i2;
        this.handler = handler;
        this.actionListener = dialogActionListener;
        this.showIcon = z2;
        this.windowPadding = CPEConstant.convertPixelToDP(context, 10, true);
        this.dialogRound = CPEConstant.convertPixelToDP(context, 13, true);
        this.thumbBorderWidth = CPEConstant.convertPixelToDP(context, 4, true);
        this.roundedThumbShape = new RoundRectShape(new float[]{(float) this.dialogRound, (float) this.dialogRound, (float) this.dialogRound, (float) this.dialogRound, (float) this.dialogRound, (float) this.dialogRound, (float) this.dialogRound, (float) this.dialogRound}, null, null);
        this.roundedActiveThumbSd = new CustomShapeDrawable(this.roundedThumbShape, Color.parseColor("#dc1f38"), Color.parseColor("#dc1f38"), this.thumbBorderWidth);
        this.roundedInactiveThumbSd = new CustomShapeDrawable(this.roundedThumbShape, Color.parseColor("#d1d1d1"), Color.parseColor("#d1d1d1"), this.thumbBorderWidth, true);
        this.contentsMainMargin = CPEConstant.convertPixelToDP(context, 10, true);
        this.dialogMainPadding = CPEConstant.convertPixelToDP(context, 10, true);
        this.adImageSectionPadding = CPEConstant.convertPixelToDP(context, 4, true);
        this.adImageSectionTitleMargin = CPEConstant.convertPixelToDP(context, 4, true);
        this.thumbnailItemSize = CPEConstant.convertPixelToDP(context, 70, true);
        this.thumbnailItemMargin = CPEConstant.convertPixelToDP(context, 6, true);
        this.thumbnailListPadding = CPEConstant.convertPixelToDP(context, 10, true);
        this.stepRewardWidth = (int) (((double) Math.min(DisplaySetter.getDisplayXY(activity).heightPixels, DisplaySetter.getDisplayXY(activity).widthPixels)) * 0.45d);
        this.thumbnailArrowSize = CPEConstant.convertPixelToDP(context, 8, true);
        this.dividerSize = CPEConstant.convertPixelToDP(context, 1, true);
        if (i2 > 0) {
            this.currentCampaignKey = i2;
        }
    }

    public View getRootView() {
        this.containerLayout = new FrameLayout(this.context);
        try {
            if (this.isPortrait) {
                this.containerLayout.addView(getContainerOnPortrait());
            } else {
                this.containerLayout.addView(getContainerOnLandscape());
            }
            setCurrentCampaign(this.currentCampaignKey > 0 ? this.currentCampaignKey : ((Integer) this.campaignKeys.get(0)).intValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.containerLayout;
    }

    public View getContainerOnLandscape() {
        Drawable customShapeDrawable = new CustomShapeDrawable(new RoundRectShape(new float[]{(float) this.dialogRound, (float) this.dialogRound, 0.0f, 0.0f, 0.0f, 0.0f, (float) this.dialogRound, (float) this.dialogRound}, null, null), -1, -16777216, CPEConstant.convertPixelToDP(this.context, 1, true));
        View frameLayout = new FrameLayout(this.context);
        ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(CPEConstant.convertPixelToDP(this.context, 680, true), CPEConstant.convertPixelToDP(this.context, 438, true), 17);
        frameLayout.setLayoutParams(layoutParams);
        View linearLayout = new LinearLayout(this.context);
        ViewGroup.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(CPEConstant.convertPixelToDP(this.context, 655, true), CPEConstant.convertPixelToDP(this.context, 412, true), 17);
        linearLayout.setLayoutParams(layoutParams2);
        this.contentsMainLl = new LinearLayout(this.context);
        this.contentsMainLl.setLayoutParams(new LayoutParams(CPEConstant.convertPixelToDP(this.context, 655, true), -1));
        this.contentsMainLl.setOrientation(0);
        this.contentsMainLl.setGravity(17);
        View linearLayout2 = new LinearLayout(this.context);
        ViewGroup.LayoutParams layoutParams3 = new LayoutParams(CPEConstant.convertPixelToDP(this.context, 462, true), -1);
        linearLayout2.setOrientation(1);
        linearLayout2.setLayoutParams(layoutParams3);
        linearLayout2.setBackgroundDrawable(customShapeDrawable);
        this.adImageSectionLl = new LinearLayout(this.context);
        this.adImageSectionLl.setLayoutParams(new LayoutParams(-1, 0, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER));
        this.adImageSectionLl.setOrientation(1);
        this.adImageSectionLl.setBackgroundColor(0);
        this.adImageSectionLl.setPadding(this.dialogMainPadding, this.dialogMainPadding + this.adImageSectionPadding, this.dialogMainPadding, 0);
        setTitleAndAdSlideView();
        linearLayout2.addView(this.adImageSectionLl);
        this.thumbnailListSv = new HorizontalScrollView(this.context);
        this.thumbnailListSv.setHorizontalScrollBarEnabled(false);
        this.thumbnailListSv.setFillViewport(true);
        this.thumbnailListSv.setPadding(this.thumbnailListPadding, this.thumbnailListPadding / 4, this.thumbnailListPadding, this.thumbnailListPadding);
        this.thumbnailListSv.setLayoutParams(new LayoutParams(-1, -2));
        int convertPixelToDP = CPEConstant.convertPixelToDP(this.context, 38, true);
        int convertPixelToDP2 = CPEConstant.convertPixelToDP(this.context, 193, true);
        if (!this.showIcon || this.campaignKeys.size() <= 1) {
            layoutParams.height = ((CPEConstant.convertPixelToDP(this.context, 438, true) - this.thumbnailItemSize) - this.thumbnailArrowSize) - (this.thumbnailListPadding / 4);
            layoutParams2.height = ((CPEConstant.convertPixelToDP(this.context, 412, true) - this.thumbnailItemSize) - this.thumbnailArrowSize) - (this.thumbnailListPadding / 4);
        } else {
            setCampaignThumbnailListView();
        }
        linearLayout2.addView(this.thumbnailListSv);
        this.stepLoadingFl = new FrameLayout(this.context);
        this.stepLoadingFl.setLayoutParams(new LayoutParams(convertPixelToDP2, -1));
        if (CommonHelper.CheckPermissionForCommonSDK(this.activity)) {
            CPECompletionHandler.getImageDownloader(this.context).download(this.media.getTheme().getPlayBtnAreaBG(), null, null, null, new ImageDownloadAsyncCallback(this.media.getTheme().getPlayBtnAreaBG(), null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    try {
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        int convertPixelToDP = CPEConstant.convertPixelToDP(CommonDialogContentsCreator.this.context, 25, true);
                        Bitmap bitmap2 = bitmap;
                        while (height < convertPixelToDP) {
                            bitmap2 = Bitmap.createScaledBitmap(bitmap2, (width * convertPixelToDP) / height, convertPixelToDP, true);
                            width = bitmap2.getWidth();
                            height = bitmap2.getHeight();
                        }
                        CommonDialogContentsCreator.this.stepLoadingFl.setBackgroundDrawable(new RoundedRepeatShapDrawable(new RoundRectShape(new float[]{0.0f, 0.0f, (float) CommonDialogContentsCreator.this.dialogRound, (float) CommonDialogContentsCreator.this.dialogRound, (float) CommonDialogContentsCreator.this.dialogRound, (float) CommonDialogContentsCreator.this.dialogRound, 0.0f, 0.0f}, null, null), -1, -16777216, 0, bitmap2));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL(CommonDialogContentsCreator.this.media.getTheme().getPlayBtnAreaBG());
                    new Handler(CommonDialogContentsCreator.this.context.getMainLooper()).post(new Runnable() {
                        public void run() {
                            try {
                                Bitmap bitmap = bitmapFromURL;
                                int width = bitmapFromURL.getWidth();
                                int height = bitmapFromURL.getHeight();
                                int convertPixelToDP = CPEConstant.convertPixelToDP(CommonDialogContentsCreator.this.context, 25, true);
                                while (height < convertPixelToDP) {
                                    bitmap = Bitmap.createScaledBitmap(bitmapFromURL, (width * convertPixelToDP) / height, convertPixelToDP, true);
                                    width = bitmap.getWidth();
                                    height = bitmap.getHeight();
                                }
                                CommonDialogContentsCreator.this.stepLoadingFl.setBackgroundDrawable(new RoundedRepeatShapDrawable(new RoundRectShape(new float[]{0.0f, 0.0f, (float) CommonDialogContentsCreator.this.dialogRound, (float) CommonDialogContentsCreator.this.dialogRound, (float) CommonDialogContentsCreator.this.dialogRound, (float) CommonDialogContentsCreator.this.dialogRound, 0.0f, 0.0f}, null, null), -1, -16777216, 0, bitmap));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        this.notAvailableTv = new TextView(this.context);
        this.notAvailableTv.setLayoutParams(new FrameLayout.LayoutParams(convertPixelToDP2, -2, 17));
        this.notAvailableTv.setTextColor(-1);
        this.notAvailableTv.setGravity(17);
        this.notAvailableTv.setTextSize(2, 13.0f);
        this.stepRewardContainer = new LinearLayout(this.context);
        layoutParams = new LayoutParams(convertPixelToDP2, -1);
        this.stepRewardContainer.setOrientation(1);
        this.stepRewardContainer.setLayoutParams(layoutParams);
        this.stepRewardContainer.setPadding(0, convertPixelToDP, 0, 0);
        setRewardView();
        this.stepLoadingFl.addView(this.notAvailableTv);
        this.stepLoadingFl.addView(this.stepRewardContainer);
        this.contentsMainLl.addView(linearLayout2);
        this.contentsMainLl.addView(this.stepLoadingFl);
        this.closeBtnIv = new ImageView(this.context);
        int convertPixelToDP3 = CPEConstant.convertPixelToDP(this.context, 40, true);
        this.closeBtnIv.setLayoutParams(new FrameLayout.LayoutParams(convertPixelToDP3, convertPixelToDP3, 5));
        if (CommonHelper.CheckPermissionForCommonSDK(this.activity)) {
            CPECompletionHandler.getImageDownloader(this.context).download(this.media.getTheme().getCloseBtn(), null, null, this.progressCircle, new ImageDownloadAsyncCallback(this.media.getTheme().getCloseBtn(), null, ImageCacheFactory.getInstance().get("imagecache"), this.progressCircle) {
                public void onResultCustom(Bitmap bitmap) {
                    try {
                        CommonDialogContentsCreator.this.closeBtnIv.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL(CommonDialogContentsCreator.this.media.getTheme().getCloseBtn());
                    new Handler(CommonDialogContentsCreator.this.context.getMainLooper()).post(new Runnable() {
                        public void run() {
                            try {
                                CommonDialogContentsCreator.this.closeBtnIv.setImageBitmap(bitmapFromURL);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        this.closeBtnIv.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CommonDialogContentsCreator.this.finishDialog();
            }
        });
        linearLayout.addView(this.contentsMainLl);
        frameLayout.addView(linearLayout);
        frameLayout.addView(this.closeBtnIv);
        return frameLayout;
    }

    public View getContainerOnPortrait() {
        Drawable customShapeDrawable = new CustomShapeDrawable(new RoundRectShape(new float[]{(float) this.dialogRound, (float) this.dialogRound, (float) this.dialogRound, (float) this.dialogRound, 0.0f, 0.0f, 0.0f, 0.0f}, null, null), -1, -16777216, CPEConstant.convertPixelToDP(this.context, 1, false));
        View frameLayout = new FrameLayout(this.context);
        ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(CPEConstant.convertPixelToDP(this.context, 445, true), CPEConstant.convertPixelToDP(this.context, 600, false), 17);
        frameLayout.setLayoutParams(layoutParams);
        View linearLayout = new LinearLayout(this.context);
        ViewGroup.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(CPEConstant.convertPixelToDP(this.context, 415, true), CPEConstant.convertPixelToDP(this.context, 572, false), 17);
        linearLayout.setLayoutParams(layoutParams2);
        this.contentsMainLl = new LinearLayout(this.context);
        this.contentsMainLl.setLayoutParams(new LayoutParams(-1, -2));
        this.contentsMainLl.setOrientation(1);
        this.adImageSectionLl = new LinearLayout(this.context);
        this.adImageSectionLl.setLayoutParams(new LayoutParams(-1, -2));
        this.adImageSectionLl.setOrientation(1);
        this.adImageSectionLl.setBackgroundDrawable(customShapeDrawable);
        this.adImageSectionLl.setPadding(this.dialogMainPadding, this.dialogMainPadding, this.dialogMainPadding, this.dialogMainPadding);
        setTitleAndAdSlideView();
        this.contentsMainLl.addView(this.adImageSectionLl);
        this.stepLoadingFl = new FrameLayout(this.context);
        ViewGroup.LayoutParams layoutParams3 = new LayoutParams(-1, CPEConstant.convertPixelToDP(this.context, 178, false));
        this.stepLoadingFl.setLayoutParams(layoutParams3);
        this.notAvailableTv = new TextView(this.context);
        this.notAvailableTv.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 17));
        this.notAvailableTv.setTextColor(-1);
        this.notAvailableTv.setTextSize(2, 13.0f);
        this.stepRewardContainer = new LinearLayout(this.context);
        ViewGroup.LayoutParams layoutParams4 = new LayoutParams(-1, -1);
        this.stepRewardContainer.setOrientation(0);
        this.stepRewardContainer.setLayoutParams(layoutParams4);
        List stepReward = ((Promotion) this.promotions.get(this.currentCampaignKey)).getDisplay().getStepReward();
        setRewardView();
        this.stepLoadingFl.addView(this.notAvailableTv);
        this.stepLoadingFl.addView(this.stepRewardContainer);
        this.contentsMainLl.addView(this.stepLoadingFl);
        View linearLayout2 = new LinearLayout(this.context);
        ViewGroup.LayoutParams layoutParams5 = new LayoutParams(-1, -2);
        linearLayout2.setLayoutParams(layoutParams5);
        linearLayout2.setOrientation(0);
        linearLayout2.setPadding(this.thumbnailListPadding, this.thumbnailListPadding / 2, this.thumbnailListPadding, this.thumbnailListPadding);
        this.thumbnailListSv = new HorizontalScrollView(this.context);
        ViewGroup.LayoutParams layoutParams6 = new LayoutParams(-1, -1);
        this.thumbnailListSv.setHorizontalScrollBarEnabled(false);
        this.thumbnailListSv.setLayoutParams(layoutParams6);
        if (!this.showIcon || this.campaignKeys.size() <= 1) {
            if (stepReward.size() > 1) {
                layoutParams3.height += this.dialogRound;
            }
            if (CommonHelper.CheckPermissionForCommonSDK(this.activity)) {
                CPECompletionHandler.getImageDownloader(this.context).download(this.media.getTheme().getPlayBtnAreaBG(), null, null, null, new ImageDownloadAsyncCallback(this.media.getTheme().getPlayBtnAreaBG(), null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                    public void onResultCustom(Bitmap bitmap) {
                        try {
                            int width = bitmap.getWidth();
                            int height = bitmap.getHeight();
                            int convertPixelToDP = CPEConstant.convertPixelToDP(CommonDialogContentsCreator.this.context, 25, true);
                            Bitmap bitmap2 = bitmap;
                            while (height < convertPixelToDP) {
                                bitmap2 = Bitmap.createScaledBitmap(bitmap2, (width * convertPixelToDP) / height, convertPixelToDP, true);
                                width = bitmap2.getWidth();
                                height = bitmap2.getHeight();
                            }
                            CommonDialogContentsCreator.this.stepLoadingFl.setBackgroundDrawable(new RoundedRepeatShapDrawable(new RoundRectShape(new float[]{0.0f, 0.0f, 0.0f, 0.0f, (float) CommonDialogContentsCreator.this.dialogRound, (float) CommonDialogContentsCreator.this.dialogRound, (float) CommonDialogContentsCreator.this.dialogRound, (float) CommonDialogContentsCreator.this.dialogRound}, null, null), -1, 0, 0, bitmap2));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                    public void run() {
                        final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL(CommonDialogContentsCreator.this.media.getTheme().getPlayBtnAreaBG());
                        new Handler(CommonDialogContentsCreator.this.context.getMainLooper()).post(new Runnable() {
                            public void run() {
                                try {
                                    Bitmap bitmap = bitmapFromURL;
                                    int width = bitmapFromURL.getWidth();
                                    int height = bitmapFromURL.getHeight();
                                    int convertPixelToDP = CPEConstant.convertPixelToDP(CommonDialogContentsCreator.this.context, 25, true);
                                    while (height < convertPixelToDP) {
                                        bitmap = Bitmap.createScaledBitmap(bitmapFromURL, (width * convertPixelToDP) / height, convertPixelToDP, true);
                                        width = bitmap.getWidth();
                                        height = bitmap.getHeight();
                                    }
                                    CommonDialogContentsCreator.this.stepLoadingFl.setBackgroundDrawable(new RoundedRepeatShapDrawable(new RoundRectShape(new float[]{0.0f, 0.0f, 0.0f, 0.0f, (float) CommonDialogContentsCreator.this.dialogRound, (float) CommonDialogContentsCreator.this.dialogRound, (float) CommonDialogContentsCreator.this.dialogRound, (float) CommonDialogContentsCreator.this.dialogRound}, null, null), -1, 0, 0, bitmap));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
            }
            layoutParams.height = ((CPEConstant.convertPixelToDP(this.context, 600, true) - this.thumbnailItemSize) - this.thumbnailArrowSize) - (this.thumbnailListPadding / 2);
            layoutParams2.height = ((CPEConstant.convertPixelToDP(this.context, 572, true) - this.thumbnailItemSize) - this.thumbnailArrowSize) - (this.thumbnailListPadding / 2);
        } else {
            if (CommonHelper.CheckPermissionForCommonSDK(this.activity)) {
                CPECompletionHandler.getImageDownloader(this.context).download(this.media.getTheme().getPlayBtnAreaBG(), null, null, null, new ImageDownloadAsyncCallback(this.media.getTheme().getPlayBtnAreaBG(), null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                    public void onResultCustom(Bitmap bitmap) {
                        try {
                            int width = bitmap.getWidth();
                            int height = bitmap.getHeight();
                            int convertPixelToDP = CPEConstant.convertPixelToDP(CommonDialogContentsCreator.this.context, 25, true);
                            Bitmap bitmap2 = bitmap;
                            while (height < convertPixelToDP) {
                                bitmap2 = Bitmap.createScaledBitmap(bitmap2, (width * convertPixelToDP) / height, convertPixelToDP, true);
                                width = bitmap2.getWidth();
                                height = bitmap2.getHeight();
                            }
                            CommonDialogContentsCreator.this.stepLoadingFl.setBackgroundDrawable(new RoundedRepeatShapDrawable(new RectShape(), -1, 0, 0, bitmap2));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                    public void run() {
                        final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL(CommonDialogContentsCreator.this.media.getTheme().getPlayBtnAreaBG());
                        new Handler(CommonDialogContentsCreator.this.context.getMainLooper()).post(new Runnable() {
                            public void run() {
                                try {
                                    Bitmap bitmap = bitmapFromURL;
                                    int width = bitmapFromURL.getWidth();
                                    int height = bitmapFromURL.getHeight();
                                    int convertPixelToDP = CPEConstant.convertPixelToDP(CommonDialogContentsCreator.this.context, 25, true);
                                    while (height < convertPixelToDP) {
                                        bitmap = Bitmap.createScaledBitmap(bitmapFromURL, (width * convertPixelToDP) / height, convertPixelToDP, true);
                                        width = bitmap.getWidth();
                                        height = bitmap.getHeight();
                                    }
                                    CommonDialogContentsCreator.this.stepLoadingFl.setBackgroundDrawable(new RoundedRepeatShapDrawable(new RectShape(), -1, 0, 0, bitmap));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
            }
            linearLayout2.setBackgroundDrawable(new CustomShapeDrawable(new RoundRectShape(new float[]{0.0f, 0.0f, 0.0f, 0.0f, (float) this.dialogRound, (float) this.dialogRound, (float) this.dialogRound, (float) this.dialogRound}, null, null), -1, -16777216, CPEConstant.convertPixelToDP(this.context, 1, false)));
            layoutParams5.height = CPEConstant.convertPixelToDP(this.context, R.styleable.AppCompatTheme_buttonStyle, false);
            setCampaignThumbnailListView();
            linearLayout2.addView(this.thumbnailListSv);
            this.contentsMainLl.addView(linearLayout2);
        }
        this.closeBtnIv = new ImageView(this.context);
        int convertPixelToDP = CPEConstant.convertPixelToDP(this.context, 40, true);
        this.closeBtnIv.setLayoutParams(new FrameLayout.LayoutParams(convertPixelToDP, convertPixelToDP, 5));
        if (CommonHelper.CheckPermissionForCommonSDK(this.activity)) {
            CPECompletionHandler.getImageDownloader(this.context).download(this.media.getTheme().getCloseBtn(), null, null, this.progressCircle, new ImageDownloadAsyncCallback(this.media.getTheme().getCirclePlayBtn(), null, ImageCacheFactory.getInstance().get("imagecache"), this.progressCircle) {
                public void onResultCustom(Bitmap bitmap) {
                    try {
                        CommonDialogContentsCreator.this.closeBtnIv.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL(CommonDialogContentsCreator.this.media.getTheme().getCloseBtn());
                    new Handler(CommonDialogContentsCreator.this.context.getMainLooper()).post(new Runnable() {
                        public void run() {
                            try {
                                CommonDialogContentsCreator.this.closeBtnIv.setImageBitmap(bitmapFromURL);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        this.closeBtnIv.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CommonDialogContentsCreator.this.finishDialog();
            }
        });
        linearLayout.addView(this.contentsMainLl);
        frameLayout.addView(linearLayout);
        frameLayout.addView(this.closeBtnIv);
        return frameLayout;
    }

    private void setTitleAndAdSlideView() {
        this.adTitleTv = new TextView(this.context);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.bottomMargin = this.adImageSectionTitleMargin;
        this.adTitleTv.setLayoutParams(layoutParams);
        this.adTitleTv.setIncludeFontPadding(false);
        this.adTitleTv.setSingleLine(true);
        this.adTitleTv.setText(((Promotion) this.promotions.get(this.currentCampaignKey)).getDisplay() == null ? "\uc774\ubca4\ud2b8" : ((Promotion) this.promotions.get(this.currentCampaignKey)).getDisplay().getTitle());
        this.adTitleTv.setTypeface(null, 1);
        this.adTitleTv.setBackgroundColor(-1);
        this.adTitleTv.setTextColor(Color.parseColor("#000000"));
        this.adImageSectionLl.addView(this.adTitleTv);
        CPEConstant.setTextViewSize(this.context, this.adTitleTv, 18);
        this.adImageSectionLl.post(new Runnable() {
            public void run() {
                CommonDialogContentsCreator.this.setSlideImageSection();
            }
        });
    }

    protected View setRewardView() {
        ViewGroup.LayoutParams layoutParams;
        int i = 0;
        List stepReward = ((Promotion) this.promotions.get(this.currentCampaignKey)).getDisplay().getStepReward();
        this.stepRewardContainer.removeAllViews();
        this.stepListLl = new LinearLayout(this.context);
        if (this.isPortrait) {
            layoutParams = new LayoutParams(CPEConstant.convertPixelToDP(this.context, 264, true), -1);
            if ((!this.showIcon || this.campaignKeys.size() < 2) && stepReward.size() > 1) {
                layoutParams.bottomMargin = this.dialogRound;
            }
        } else {
            layoutParams = (!this.showIcon || this.campaignKeys.size() <= 1) ? new LayoutParams(-1, ((CPEConstant.convertPixelToDP(this.context, 274, true) - this.thumbnailItemSize) - this.thumbnailArrowSize) - this.thumbnailListPadding) : new LayoutParams(-1, CPEConstant.convertPixelToDP(this.context, 274, true));
        }
        this.stepListLl.setOrientation(1);
        this.stepListLl.setLayoutParams(layoutParams);
        if (stepReward == null || stepReward.size() < 1) {
            this.stepRewardContainer.setVisibility(0);
            this.notAvailableTv.setVisibility(8);
            if (!this.isPortrait) {
                this.stepRewardContainer.addView(this.stepListLl);
                View dividerView = getDividerView(this.isPortrait ? 0 : 1);
                dividerView.setVisibility(4);
                this.stepRewardContainer.addView(dividerView);
            }
            setNonRewardView(stepReward.size());
        } else {
            this.stepRewardContainer.addView(this.stepListLl);
            LinearLayout linearLayout = this.stepRewardContainer;
            if (!this.isPortrait) {
                i = 1;
            }
            linearLayout.addView(getDividerView(i));
            setMultiStepRewardView(stepReward.size());
        }
        return this.stepListLl;
    }

    private void setNonRewardView(int i) {
        this.stepRewardContainer.addView(getPlayBtnView(i));
    }

    private void setMultiStepRewardView(int i) {
        if (((Promotion) this.promotions.get(this.currentCampaignKey)).getDisplay().getStepReward().size() > 1) {
            setStepListView();
        } else {
            setOneStepView();
        }
        this.stepRewardContainer.addView(getPlayBtnView(i));
    }

    private View getPlayBtnView(int i) {
        int convertPixelToDP;
        ViewGroup.LayoutParams layoutParams;
        this.playBtnLl = new RepeatBGLinearLayout(this.context);
        String circlePlayBtn = this.media.getTheme().getCirclePlayBtn();
        if (this.isPortrait) {
            this.playBtnLl.setGravity(17);
            if (i > 0) {
                convertPixelToDP = CPEConstant.convertPixelToDP(this.context, 120, true);
                layoutParams = new LayoutParams(-1, -1);
            } else {
                convertPixelToDP = CPEConstant.convertPixelToDP(this.context, 140, true);
                layoutParams = new LayoutParams(-1, -1);
            }
        } else {
            ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-1, -1);
            circlePlayBtn = this.media.getTheme().getSquarePlayBtn();
            this.playBtnLl.setGravity(81);
            convertPixelToDP = CPEConstant.convertPixelToDP(this.context, this.isPortrait ? 5 : 10, true);
            this.playBtnLl.setPadding(convertPixelToDP, convertPixelToDP, convertPixelToDP, convertPixelToDP);
            convertPixelToDP = -1;
            layoutParams = layoutParams2;
        }
        this.playBtnLl.setLayoutParams(layoutParams);
        this.playBtnLl.setGravity(17);
        this.playBtnIv = new ImageView(this.context);
        this.playBtnIv.setLayoutParams(new LayoutParams(convertPixelToDP, convertPixelToDP));
        this.playBtnIv.setScaleType(ScaleType.FIT_CENTER);
        if (CommonHelper.CheckPermissionForCommonSDK(this.activity)) {
            CPECompletionHandler.getImageDownloader(this.context).download(circlePlayBtn, null, null, null, new ImageDownloadAsyncCallback(circlePlayBtn, null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    CommonDialogContentsCreator.this.playBtnIv.setImageBitmap(bitmap);
                }
            });
        } else {
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL(circlePlayBtn);
                    new Handler(CommonDialogContentsCreator.this.context.getMainLooper()).post(new Runnable() {
                        public void run() {
                            try {
                                CommonDialogContentsCreator.this.playBtnIv.setImageBitmap(bitmapFromURL);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        this.playBtnLl.addView(this.playBtnIv);
        return this.playBtnLl;
    }

    private void setOneStepView() {
        int convertPixelToDP;
        StepRewardModel stepRewardModel = (StepRewardModel) ((Promotion) this.promotions.get(this.currentCampaignKey)).getDisplay().getStepReward().get(0);
        int convertPixelToDP2 = CPEConstant.convertPixelToDP(this.context, this.isPortrait ? 18 : 18, true);
        int convertPixelToDP3 = CPEConstant.convertPixelToDP(this.context, 2, true);
        int convertPixelToDP4 = CPEConstant.convertPixelToDP(this.context, 10, true);
        int convertPixelToDP5 = CPEConstant.convertPixelToDP(this.context, 14, true);
        int convertPixelToDP6 = CPEConstant.convertPixelToDP(this.context, 20, true);
        int convertPixelToDP7 = CPEConstant.convertPixelToDP(this.context, this.isPortrait ? 38 : 38, true);
        View linearLayout = new LinearLayout(this.context);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(layoutParams);
        if (this.isPortrait) {
            linearLayout.setGravity(17);
            linearLayout.setPadding(convertPixelToDP4, convertPixelToDP4, convertPixelToDP4, convertPixelToDP4);
            convertPixelToDP = CPEConstant.convertPixelToDP(this.context, 180, true);
        } else {
            linearLayout.setGravity(1);
            linearLayout.setPadding(convertPixelToDP4, 0, convertPixelToDP4, 0);
            convertPixelToDP = CPEConstant.convertPixelToDP(this.context, 160, true);
        }
        Drawable customShapeDrawable = new CustomShapeDrawable(new RoundRectShape(new float[]{(float) convertPixelToDP2, (float) convertPixelToDP2, (float) convertPixelToDP2, (float) convertPixelToDP2, (float) convertPixelToDP2, (float) convertPixelToDP2, (float) convertPixelToDP2, (float) convertPixelToDP2}, null, null), Color.parseColor(this.media.getTheme().getFirstUnitBGColorForOneStep()), Color.parseColor(this.media.getTheme().getFirstUnitBGColorForOneStep()), 0);
        Drawable customShapeDrawable2 = new CustomShapeDrawable(new RoundRectShape(new float[]{(float) convertPixelToDP2, (float) convertPixelToDP2, (float) convertPixelToDP2, (float) convertPixelToDP2, (float) convertPixelToDP2, (float) convertPixelToDP2, (float) convertPixelToDP2, (float) convertPixelToDP2}, null, null), Color.parseColor(this.media.getTheme().getSecondUnitBGColorForOneStep()), Color.parseColor(this.media.getTheme().getSecondUnitBGColorForOneStep()), 0);
        Drawable customShapeDrawable3 = new CustomShapeDrawable(new RoundRectShape(new float[]{(float) convertPixelToDP2, (float) convertPixelToDP2, (float) convertPixelToDP2, (float) convertPixelToDP2, (float) convertPixelToDP2, (float) convertPixelToDP2, (float) convertPixelToDP2, (float) convertPixelToDP2}, null, null), Color.parseColor(this.media.getTheme().getRewardUnitBGColorForOneStep()), Color.parseColor(this.media.getTheme().getRewardUnitBGColorForOneStep()), 0);
        linearLayout = new TextView(this.context);
        layoutParams = new LayoutParams(convertPixelToDP, convertPixelToDP7);
        layoutParams.bottomMargin = convertPixelToDP3;
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setBackgroundDrawable(customShapeDrawable);
        linearLayout.setText(this.media.getLanguage().getFirstUnitForOneStep());
        linearLayout.setTypeface(linearLayout.getTypeface(), 1);
        linearLayout.setGravity(17);
        linearLayout.setTextColor(Color.parseColor("#3f292d"));
        linearLayout.setTextSize(2, 13.0f);
        final View imageView = new ImageView(this.context);
        layoutParams = new LayoutParams(convertPixelToDP5, convertPixelToDP5);
        layoutParams.bottomMargin = convertPixelToDP3;
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ScaleType.FIT_CENTER);
        if (CommonHelper.CheckPermissionForCommonSDK(this.activity)) {
            CPECompletionHandler.getImageDownloader(this.context).download(this.media.getTheme().getStepArrow(), null, null, null, new ImageDownloadAsyncCallback(this.media.getTheme().getStepArrow(), null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    imageView.setImageBitmap(bitmap);
                }
            });
        } else {
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL(CommonDialogContentsCreator.this.media.getTheme().getStepArrow());
                    Handler handler = new Handler(CommonDialogContentsCreator.this.context.getMainLooper());
                    final ImageView imageView = imageView;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                imageView.setImageBitmap(bitmapFromURL);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        linearLayout = new TextView(this.context);
        layoutParams = new LayoutParams(convertPixelToDP, convertPixelToDP7);
        layoutParams.bottomMargin = convertPixelToDP3;
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setBackgroundDrawable(customShapeDrawable2);
        linearLayout.setText(stepRewardModel.getName());
        linearLayout.setTypeface(linearLayout.getTypeface(), 1);
        linearLayout.setGravity(17);
        linearLayout.setTextColor(Color.parseColor("#3f292d"));
        linearLayout.setTextSize(2, 13.0f);
        final View imageView2 = new ImageView(this.context);
        layoutParams = new LayoutParams(convertPixelToDP5, convertPixelToDP5);
        layoutParams.bottomMargin = convertPixelToDP3;
        imageView2.setLayoutParams(layoutParams);
        imageView2.setScaleType(ScaleType.FIT_CENTER);
        if (CommonHelper.CheckPermissionForCommonSDK(this.activity)) {
            CPECompletionHandler.getImageDownloader(this.context).download(this.media.getTheme().getStepArrow(), null, null, null, new ImageDownloadAsyncCallback(this.media.getTheme().getStepArrow(), null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    imageView2.setImageBitmap(bitmap);
                }
            });
        } else {
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL(CommonDialogContentsCreator.this.media.getTheme().getStepArrow());
                    Handler handler = new Handler(CommonDialogContentsCreator.this.context.getMainLooper());
                    final ImageView imageView = imageView2;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                imageView.setImageBitmap(bitmapFromURL);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        View linearLayout2 = new LinearLayout(this.context);
        linearLayout2.setLayoutParams(new LayoutParams(convertPixelToDP, convertPixelToDP7));
        linearLayout2.setGravity(17);
        linearLayout2.setBackgroundDrawable(customShapeDrawable3);
        linearLayout2.setOrientation(0);
        linearLayout = new ImageView(this.context);
        linearLayout.setLayoutParams(new LayoutParams(convertPixelToDP6, convertPixelToDP6));
        if (CommonHelper.CheckPermissionForCommonSDK(this.context)) {
            CPECompletionHandler.getImageDownloader(this.context).download(ADBrixHttpManager.schedule.getSchedule().getMedia().getRewardIcon(), null, null, null, new ImageDownloadAsyncCallback(ADBrixHttpManager.schedule.getSchedule().getMedia().getRewardIcon(), null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    linearLayout.setImageBitmap(bitmap);
                }
            });
        } else {
            final View view = linearLayout;
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL(ADBrixHttpManager.schedule.getSchedule().getMedia().getRewardIcon());
                    Handler handler = new Handler(CommonDialogContentsCreator.this.context.getMainLooper());
                    final ImageView imageView = view;
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                imageView.setImageBitmap(bitmapFromURL);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        View textView = new TextView(this.context);
        textView.setText(" " + this.media.getLanguage().getRewardUnitForOneStep() + " " + stepRewardModel.getReward());
        textView.setTypeface(textView.getTypeface(), 1);
        textView.setTextColor(Color.parseColor("#3f292d"));
        textView.setTextSize(2, 13.0f);
        linearLayout2.addView(linearLayout);
        linearLayout2.addView(textView);
        linearLayout.addView(linearLayout);
        linearLayout.addView(imageView);
        linearLayout.addView(linearLayout);
        linearLayout.addView(imageView2);
        linearLayout.addView(linearLayout2);
        this.stepListLl.addView(linearLayout);
    }

    private void setStepListView() {
        ViewGroup.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2;
        ViewGroup.LayoutParams layoutParams3;
        ViewGroup.LayoutParams layoutParams4;
        int convertPixelToDP = CPEConstant.convertPixelToDP(this.context, 36, false);
        View linearLayout = new LinearLayout(this.context);
        linearLayout.setOrientation(0);
        linearLayout.setBackgroundColor(Color.parseColor("#182030"));
        linearLayout.setGravity(17);
        int convertPixelToDP2 = CPEConstant.convertPixelToDP(this.context, 5, true);
        linearLayout.setPadding(0, convertPixelToDP2, 0, convertPixelToDP2);
        this.stepListColumnMargin = CPEConstant.convertPixelToDP(this.context, 7, true);
        this.missionTitleTv = new TextView(this.context);
        this.missionTitleTv.setGravity(17);
        this.missionTitleTv.setText(this.media.getLanguage().getMission());
        this.missionTitleTv.setTextColor(Color.parseColor("#24e0f7"));
        this.missionTitleTv.setTypeface(this.missionTitleTv.getTypeface(), 1);
        CPEConstant.setTextViewSize(this.context, this.missionTitleTv, 15);
        int convertPixelToDP3 = CPEConstant.convertPixelToDP(this.context, 20, true);
        this.rewardIv = new ImageView(this.context);
        this.rewardIv.setScaleType(ScaleType.FIT_CENTER);
        if (CommonHelper.CheckPermissionForCommonSDK(this.context)) {
            CPECompletionHandler.getImageDownloader(this.context).download(ADBrixHttpManager.schedule.getSchedule().getMedia().getRewardIcon(), null, null, null, new ImageDownloadAsyncCallback(ADBrixHttpManager.schedule.getSchedule().getMedia().getRewardIcon(), null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                public void onResultCustom(Bitmap bitmap) {
                    try {
                        CommonDialogContentsCreator.this.rewardIv.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                public void run() {
                    final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL(ADBrixHttpManager.schedule.getSchedule().getMedia().getRewardIcon());
                    new Handler(CommonDialogContentsCreator.this.context.getMainLooper()).post(new Runnable() {
                        public void run() {
                            try {
                                CommonDialogContentsCreator.this.rewardIv.setImageBitmap(bitmapFromURL);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
        this.isCompleteTitleTv = new TextView(this.context);
        this.isCompleteTitleTv.setText(this.media.getLanguage().getIsComplete());
        this.isCompleteTitleTv.setTextColor(Color.parseColor("#ffffff"));
        this.isCompleteTitleTv.setTypeface(this.isCompleteTitleTv.getTypeface(), 1);
        this.isCompleteTitleTv.setGravity(17);
        CPEConstant.setTextViewSize(this.context, this.isCompleteTitleTv, 15);
        linearLayout.addView(this.missionTitleTv);
        linearLayout.addView(getDividerView(0));
        linearLayout.addView(this.rewardIv);
        linearLayout.addView(getDividerView(0));
        linearLayout.addView(this.isCompleteTitleTv);
        View listView = new ListView(this.context);
        listView.setLayoutParams(new LayoutParams(-1, -2));
        if (this.isPortrait) {
            layoutParams = new LayoutParams(CPEConstant.convertPixelToDP(this.context, 264, true), convertPixelToDP);
            layoutParams2 = new LayoutParams(CPEConstant.convertPixelToDP(this.context, 161, true), -2, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER);
            layoutParams3 = new LayoutParams(CPEConstant.convertPixelToDP(this.context, 50, true), convertPixelToDP3);
            layoutParams4 = new LayoutParams(CPEConstant.convertPixelToDP(this.context, 50, true), -2);
        } else {
            layoutParams = new LayoutParams(CPEConstant.convertPixelToDP(this.context, 193, true), convertPixelToDP);
            layoutParams2 = new LayoutParams(CPEConstant.convertPixelToDP(this.context, R.styleable.AppCompatTheme_switchStyle, true), -2, TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER);
            layoutParams3 = new LayoutParams(CPEConstant.convertPixelToDP(this.context, 40, true), convertPixelToDP3);
            layoutParams4 = new LayoutParams(CPEConstant.convertPixelToDP(this.context, 40, true), -2);
        }
        linearLayout.setLayoutParams(layoutParams);
        this.missionTitleTv.setLayoutParams(layoutParams2);
        this.rewardIv.setLayoutParams(layoutParams3);
        this.isCompleteTitleTv.setLayoutParams(layoutParams4);
        this.stepListLl.addView(linearLayout);
        this.stepListLl.addView(getDividerView(1));
        this.stepListLl.addView(listView);
        listView.setAdapter(new StepRewardListAdapter(this.context, 14745));
        listView.setDividerHeight(0);
    }

    private View getDividerView(int i) {
        ViewGroup.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2;
        ViewGroup.LayoutParams layoutParams3;
        if (i == 0) {
            layoutParams = new LayoutParams(-2, -1);
            layoutParams2 = new LayoutParams(CPEConstant.convertPixelToDP(this.context, 1, true), -1);
            layoutParams3 = new LayoutParams(CPEConstant.convertPixelToDP(this.context, 0, true), -1);
        } else {
            layoutParams = new LayoutParams(-1, -2);
            layoutParams2 = new LayoutParams(-1, CPEConstant.convertPixelToDP(this.context, 1, true));
            layoutParams3 = new LayoutParams(-1, CPEConstant.convertPixelToDP(this.context, 0, true));
        }
        View linearLayout = new LinearLayout(this.context);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(i);
        View imageView = new ImageView(this.context);
        imageView.setLayoutParams(layoutParams2);
        imageView.setImageDrawable(new ColorDrawable(Color.parseColor("#131924")));
        imageView.setScaleType(ScaleType.FIT_XY);
        View imageView2 = new ImageView(this.context);
        imageView2.setLayoutParams(layoutParams3);
        imageView2.setImageDrawable(new ColorDrawable(Color.parseColor("#344360")));
        imageView2.setScaleType(ScaleType.FIT_XY);
        linearLayout.addView(imageView);
        linearLayout.addView(imageView2);
        return linearLayout;
    }

    private void setCampaignThumbnailListView() {
        View linearLayout = new LinearLayout(this.context);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
        linearLayout.setOrientation(0);
        linearLayout.setBackgroundColor(-1);
        linearLayout.setLayoutParams(layoutParams);
        this.campaignThumbnails = new SparseArray();
        if (this.rCks == null || this.rCks.size() != this.campaignKeys.size()) {
            long nanoTime = System.nanoTime();
            this.rCks = new ArrayList();
            this.rCks.addAll(this.campaignKeys);
            this.rCks.remove(this.campaignKeys.indexOf(Integer.valueOf(this.currentCampaignKey)));
            Collections.shuffle(this.rCks, new Random(nanoTime));
            this.rCks.add(0, Integer.valueOf(this.currentCampaignKey));
        }
        int convertPixelToDP = CPEConstant.convertPixelToDP(this.context, 6, true);
        int convertPixelToDP2 = CPEConstant.convertPixelToDP(this.context, 8, false);
        for (Integer intValue : this.rCks) {
            int intValue2 = intValue.intValue();
            linearLayout = new LinearLayout(this.context);
            ViewGroup.LayoutParams layoutParams2 = new LayoutParams(this.thumbnailItemSize + convertPixelToDP, (this.thumbnailItemSize + convertPixelToDP) + convertPixelToDP2);
            linearLayout.setOrientation(1);
            linearLayout.setGravity(17);
            linearLayout.setLayoutParams(layoutParams2);
            linearLayout.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    int indexOfValue = CommonDialogContentsCreator.this.campaignThumbnails.indexOfValue((LinearLayout) view);
                    if (CommonDialogContentsCreator.this.campaignThumbnails.keyAt(indexOfValue) != CommonDialogContentsCreator.this.currentCampaignKey) {
                        CommonDialogContentsCreator.this.setCurrentCampaign(CommonDialogContentsCreator.this.campaignThumbnails.keyAt(indexOfValue));
                        CommonDialogContentsCreator.this.changePromotionContents();
                    }
                }
            });
            if (intValue2 != ((Integer) this.rCks.get(0)).intValue()) {
                if (this.isPortrait) {
                    layoutParams2.leftMargin = CPEConstant.convertPixelToDP(this.context, 6, true);
                } else {
                    layoutParams2.leftMargin = CPEConstant.convertPixelToDP(this.context, 12, true);
                }
            }
            final View imageView = new ImageView(this.context);
            layoutParams = new LayoutParams((int) (((float) convertPixelToDP2) * 1.3f), convertPixelToDP2);
            imageView.setId(18841);
            imageView.setLayoutParams(layoutParams);
            imageView.setScaleType(ScaleType.FIT_XY);
            if (CommonHelper.CheckPermissionForCommonSDK(this.context)) {
                CPECompletionHandler.getImageDownloader(this.context).download(this.media.getTheme().getSelectedAppArrow(), null, null, null, new ImageDownloadAsyncCallback(this.media.getTheme().getSelectedAppArrow(), null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                    public void onResultCustom(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                });
            } else {
                InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                    public void run() {
                        final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL(CommonDialogContentsCreator.this.media.getTheme().getSelectedAppArrow());
                        Handler handler = new Handler(CommonDialogContentsCreator.this.context.getMainLooper());
                        final ImageView imageView = imageView;
                        handler.post(new Runnable() {
                            public void run() {
                                try {
                                    imageView.setImageBitmap(bitmapFromURL);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
            }
            linearLayout = new LinearLayout(this.context);
            linearLayout.setLayoutParams(new LayoutParams(this.thumbnailItemSize + convertPixelToDP, this.thumbnailItemSize + convertPixelToDP));
            linearLayout.setId(22936);
            linearLayout.setGravity(17);
            final View imageView2 = new ImageView(this.context);
            layoutParams = new LayoutParams(this.thumbnailItemSize, this.thumbnailItemSize);
            imageView2.setId(22937);
            imageView2.setLayoutParams(layoutParams);
            imageView2.setScaleType(ScaleType.FIT_XY);
            if (CommonHelper.CheckPermissionForCommonSDK(this.context)) {
                CPECompletionHandler.getImageDownloader(this.context).download(((Promotion) this.promotions.get(intValue2)).getDisplay().getIcon().getResource(), null, null, null, new ImageDownloadAsyncCallback(((Promotion) this.promotions.get(intValue2)).getDisplay().getIcon().getResource(), null, ImageCacheFactory.getInstance().get("imagecache"), null) {
                    public void onResultCustom(Bitmap bitmap) {
                        imageView2.setImageBitmap(DialogUtil.getRoundedCornerBitmap(CommonDialogContentsCreator.this.context, bitmap, CommonDialogContentsCreator.this.thumbnailItemSize, CommonDialogContentsCreator.this.thumbnailItemSize));
                    }
                });
            } else {
                final int i = intValue2;
                InternalAction.NETWORK_EXECUTOR.execute(new Runnable() {
                    public void run() {
                        final Bitmap bitmapFromURL = CommonHelper.getBitmapFromURL(((Promotion) CommonDialogContentsCreator.this.promotions.get(i)).getDisplay().getIcon().getResource());
                        Handler handler = new Handler(CommonDialogContentsCreator.this.context.getMainLooper());
                        final ImageView imageView = imageView2;
                        handler.post(new Runnable() {
                            public void run() {
                                try {
                                    imageView.setImageBitmap(DialogUtil.getRoundedCornerBitmap(CommonDialogContentsCreator.this.context, bitmapFromURL, CommonDialogContentsCreator.this.thumbnailItemSize, CommonDialogContentsCreator.this.thumbnailItemSize));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
            }
            linearLayout.addView(imageView2);
            linearLayout.addView(imageView);
            linearLayout.addView(linearLayout);
            linearLayout.addView(linearLayout);
            this.campaignThumbnails.put(intValue2, linearLayout);
        }
        this.thumbnailListSv.addView(linearLayout);
    }

    public void setCurrentCampaign(int i) {
        this.currentCampaignKey = i;
        addImpression(this.context, this.currentCampaignKey, ((Promotion) this.promotions.get(this.currentCampaignKey)).getDisplay().getSlide().getResourceKey(), this.spaceKey);
        if (this.campaignThumbnails != null && this.campaignThumbnails.size() > 0) {
            for (Integer intValue : this.campaignKeys) {
                int intValue2 = intValue.intValue();
                if (intValue2 == i) {
                    ((ImageView) ((LinearLayout) this.campaignThumbnails.get(intValue2)).findViewById(22937)).setColorFilter(null);
                    ((LinearLayout) ((LinearLayout) this.campaignThumbnails.get(intValue2)).findViewById(22936)).setBackgroundDrawable(this.roundedActiveThumbSd);
                    ((ImageView) ((LinearLayout) this.campaignThumbnails.get(intValue2)).findViewById(18841)).setVisibility(0);
                    this.progressModel = null;
                    setPlayBtnClickListener();
                } else {
                    setGrayScale((ImageView) ((LinearLayout) this.campaignThumbnails.get(intValue2)).findViewById(22937));
                    ((LinearLayout) ((LinearLayout) this.campaignThumbnails.get(intValue2)).findViewById(22936)).setBackgroundDrawable(this.roundedInactiveThumbSd);
                    ((ImageView) ((LinearLayout) this.campaignThumbnails.get(intValue2)).findViewById(18841)).setVisibility(4);
                }
            }
        } else if (((Promotion) this.promotions.get(this.currentCampaignKey)).getDisplay() != null && (((Promotion) this.promotions.get(this.currentCampaignKey)).getDisplay().getStepReward() == null || ((Promotion) this.promotions.get(this.currentCampaignKey)).getDisplay().getStepReward().size() < 1)) {
            setPlayBtnClickListener();
        }
        if (this.contentsMainLl != null) {
            this.contentsMainLl.invalidate();
        }
    }

    private void addImpression(Context context, int i, int i2, String str) {
        if (!this.impressionAddedCampaign.contains(Integer.valueOf(i))) {
            try {
                Calendar.getInstance().getTime();
                TrackingActivitySQLiteDB.getInstance(context).setImpressionData(context, i, i2, str, CommonHelper.GetKSTCreateAtAsString(), null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                CPEImpressionDAOFactory.getImpressionDAO("impression", "session_count", 1).increaseImpressionData(context, 1, new StringBuilder(String.valueOf(i)).toString(), "session_count");
                String stringBuilder = new StringBuilder(String.valueOf(new Date().getTime())).toString();
                CPEImpressionDAOFactory.getImpressionDAO("impression", "last_imp_minute", 1).setImpressionData(context, 1, new StringBuilder(String.valueOf(i)).toString(), "last_imp_minute", stringBuilder);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.impressionAddedCampaign.add(Integer.valueOf(i));
        }
    }

    protected void setPlayBtnClickListener() {
        String str = null;
        try {
            RequestParameter aTRequestParameter = RequestParameter.getATRequestParameter(this.context);
            if (((Promotion) this.promotions.get(this.currentCampaignKey)).getDisplay().getStepReward().size() <= 0 || this.progressModel != null) {
                this.playBtnIv.setOnClickListener(this.landingBtnClickLisetner);
                return;
            }
            this.playBtnIv.setOnClickListener(this.onReadyBtnClickListener);
            if (this.progressModels != null && this.progressModels.indexOfKey(this.currentCampaignKey) > -1) {
                this.progressModel = (ParticipationProgressResponseModel) this.progressModels.get(this.currentCampaignKey);
                setProgressModel();
            } else if (!this.onGetProgressModel) {
                ADBrixHttpManager manager = ADBrixHttpManager.getManager(this.context);
                DeviceIDManger instance = DeviceIDManger.getInstance(this.context);
                try {
                    if (this.context != null) {
                        str = this.context.getSharedPreferences("persistantDemoForTracking", 0).getString("userId", null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                manager.getParticipationProgressForADBrix(aTRequestParameter, this.context, aTRequestParameter.getAppkey(), this.currentCampaignKey, instance.getAESPuid(this.context), str, this);
                addProgressCircle(this.context, this.stepLoadingFl);
                this.onGetProgressModel = true;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void setGrayScale(ImageView imageView) {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        imageView.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    }

    private void addProgressCircle(Context context, ViewGroup viewGroup) {
        try {
            if (!(this.progressCircle == null || this.stepLoadingFl == null)) {
                ((ViewGroup) this.progressCircle.getParent()).removeViewInLayout(this.progressCircle);
                this.progressCircle = null;
            }
            this.progressCircle = new FrameLayout(context);
            View progressBar = new ProgressBar(context);
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
            ViewGroup.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
            layoutParams2.gravity = 17;
            layoutParams.gravity = 17;
            progressBar.setLayoutParams(layoutParams2);
            this.progressCircle.setLayoutParams(layoutParams);
            this.progressCircle.addView(progressBar);
            viewGroup.addView(this.progressCircle);
        } catch (Exception e) {
        }
    }

    public void onResume() {
        String str = null;
        try {
            if (((Promotion) this.promotions.get(this.currentCampaignKey)).getDisplay().getStepReward().size() > 0 && !this.onGetProgressModel) {
                RequestParameter aTRequestParameter = RequestParameter.getATRequestParameter(this.context);
                ADBrixHttpManager manager = ADBrixHttpManager.getManager(this.context);
                DeviceIDManger instance = DeviceIDManger.getInstance(this.context);
                try {
                    if (this.context != null) {
                        str = this.context.getSharedPreferences("persistantDemoForTracking", 0).getString("userId", null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                manager.getParticipationProgressForADBrix(aTRequestParameter, this.context, aTRequestParameter.getAppkey(), this.currentCampaignKey, instance.getAESPuid(this.context), str, this);
                addProgressCircle(this.context, this.stepLoadingFl);
                this.onGetProgressModel = true;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void setProgressModel() {
        this.handler.post(new Runnable() {
            public void run() {
                int i = 0;
                try {
                    if (!(CommonDialogContentsCreator.this.progressCircle == null || CommonDialogContentsCreator.this.stepLoadingFl == null)) {
                        ViewParent parent = CommonDialogContentsCreator.this.progressCircle.getParent();
                        ((ViewGroup) parent).removeViewInLayout(CommonDialogContentsCreator.this.progressCircle);
                        CommonDialogContentsCreator.this.progressCircle = null;
                        ((View) parent).invalidate();
                    }
                    if (CommonDialogContentsCreator.this.progressModel == null || !CommonDialogContentsCreator.this.progressModel.isResult()) {
                        CommonDialogContentsCreator.this.stepRewardContainer.setVisibility(8);
                        CommonDialogContentsCreator.this.notAvailableTv.setVisibility(0);
                        if (CommonDialogContentsCreator.this.progressModel != null) {
                            CharSequence unknownError = CommonDialogContentsCreator.this.media.getLanguage().getUnknownError();
                            switch (CommonDialogContentsCreator.this.progressModel.getResultCode()) {
                                case 5302:
                                    unknownError = CommonDialogContentsCreator.this.media.getLanguage().getCanNotParticipate();
                                    break;
                                case 5303:
                                    unknownError = CommonDialogContentsCreator.this.media.getLanguage().getAnotherAppParticipate();
                                    break;
                            }
                            if (CommonDialogContentsCreator.this.notAvailableTv != null) {
                                CommonDialogContentsCreator.this.notAvailableTv.setText(unknownError);
                            }
                            NotAvailableCampaignDAO.getInstance().saveNotAvailableCampaign(CommonDialogContentsCreator.this.context, CommonDialogContentsCreator.this.currentCampaignKey);
                            return;
                        } else if (CommonDialogContentsCreator.this.notAvailableTv != null) {
                            CommonDialogContentsCreator.this.notAvailableTv.setText(CommonDialogContentsCreator.this.media.getLanguage().getUnknownError());
                            return;
                        } else {
                            return;
                        }
                    }
                    CommonDialogContentsCreator.this.stepRewardContainer.setVisibility(0);
                    CommonDialogContentsCreator.this.notAvailableTv.setVisibility(8);
                    if (!(CommonDialogContentsCreator.this.progressModel == null || CommonDialogContentsCreator.this.progressModel.getData() == null)) {
                        List<StepRewardModel> stepReward = ((Promotion) CommonDialogContentsCreator.this.promotions.get(CommonDialogContentsCreator.this.currentCampaignKey)).getDisplay().getStepReward();
                        for (StepRewardModel stepRewardModel : stepReward) {
                            for (ParticipationProgressModel conversionKey : CommonDialogContentsCreator.this.progressModel.getData()) {
                                if (conversionKey.getConversionKey() == stepRewardModel.getConversionKey()) {
                                    stepRewardModel.setComplete(true);
                                    i++;
                                    break;
                                }
                                stepRewardModel.setComplete(false);
                            }
                        }
                        if (stepReward.size() > 0 && i == stepReward.size()) {
                            CommonDialogContentsCreator.this.stepRewardContainer.setVisibility(8);
                            CommonDialogContentsCreator.this.notAvailableTv.setVisibility(0);
                            if (CommonDialogContentsCreator.this.notAvailableTv != null) {
                                CommonDialogContentsCreator.this.notAvailableTv.setText(CommonDialogContentsCreator.this.media.getLanguage().getAlreadyParticipated());
                            }
                            NotAvailableCampaignDAO.getInstance().saveNotAvailableCampaign(CommonDialogContentsCreator.this.context, CommonDialogContentsCreator.this.currentCampaignKey);
                        }
                    }
                    CommonDialogContentsCreator.this.setRewardView();
                    CommonDialogContentsCreator.this.setPlayBtnClickListener();
                } catch (Exception e) {
                    e.printStackTrace();
                    CommonDialogContentsCreator.this.finishDialog();
                }
            }
        });
    }

    public void setActionListener(DialogActionListener dialogActionListener) {
        this.actionListener = dialogActionListener;
    }

    public void callback(ParticipationProgressResponseModel participationProgressResponseModel) {
        int i = 0;
        try {
            this.onGetProgressModel = false;
            if (this.progressModels == null) {
                this.progressModels = new SparseArray();
            }
            this.progressModel = participationProgressResponseModel;
            this.progressModels.append(this.currentCampaignKey, participationProgressResponseModel);
            if (participationProgressResponseModel == null) {
                IgawLogger.Logging(this.context, "IGAW_QA", "Adbrix > get participation progress failed.", 3);
                this.playBtnIv.setOnClickListener(this.onFailBtnClickListener);
                return;
            }
            Context context = this.context;
            String str = "IGAW_QA";
            StringBuilder stringBuilder = new StringBuilder("Adbrix > get participation progress result size = ");
            if (participationProgressResponseModel.getData() != null) {
                i = participationProgressResponseModel.getData().size();
            }
            IgawLogger.Logging(context, str, stringBuilder.append(i).toString(), 3);
            setProgressModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
