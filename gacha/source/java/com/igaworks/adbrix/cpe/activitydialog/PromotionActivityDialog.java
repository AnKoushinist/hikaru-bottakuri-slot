package com.igaworks.adbrix.cpe.activitydialog;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.j;
import android.support.v4.app.r;
import android.util.SparseArray;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;
import com.igaworks.adbrix.core.ADBrixHttpManager;
import com.igaworks.adbrix.cpe.common.DialogActionListener;
import com.igaworks.adbrix.cpe.common.FragmentActivityDialogContentsCreator;
import com.igaworks.adbrix.interfaces.ADBrixCallbackListener;
import com.igaworks.adbrix.interfaces.PromotionActionListener;
import com.igaworks.adbrix.model.Language;
import com.igaworks.adbrix.model.Media;
import com.igaworks.adbrix.model.Promotion;
import com.igaworks.adbrix.model.Theme;
import com.igaworks.adbrix.util.CPEConstant;
import com.igaworks.core.IgawLogger;
import java.util.List;

public class PromotionActivityDialog extends j implements DialogActionListener {
    public static boolean isActive = false;
    public static ADBrixCallbackListener onPlayBtnClickListener;
    public static PromotionActionListener promotionActionListener;
    public static PromotionActivityDialog promotionDialog;
    private List<Integer> campaignKeys;
    private LayoutParams containerParam;
    public FragmentActivityDialogContentsCreator contentsProvider;
    private int currentCampaignKey;
    private int currentSlideNo;
    private boolean isPortrait;
    private Media media;
    private int primaryCampaignKey;
    private SparseArray<Promotion> promotions;
    private String spaceKey;
    protected int windowPadding;

    protected void onCreate(Bundle bundle) {
        boolean z = true;
        super.onCreate(bundle);
        try {
            promotionDialog = this;
            this.media = ADBrixHttpManager.schedule.getSchedule().getMedia();
            if (this.media == null) {
                this.media = new Media();
            }
            if (this.media.getLanguage() == null) {
                this.media.setLanguage(new Language());
            }
            if (this.media.getTheme() == null) {
                this.media.setTheme(new Theme());
            }
            if (bundle != null) {
                this.currentCampaignKey = bundle.getInt("currentCampaignKey");
                this.currentSlideNo = bundle.getInt("slideNo", -1);
            } else {
                this.primaryCampaignKey = getIntent().getIntExtra("primaryCampaignKey", 0);
            }
            this.spaceKey = getIntent().getStringExtra("spaceKey");
            this.campaignKeys = getIntent().getIntegerArrayListExtra("campaignKeys");
            if (this.campaignKeys == null || this.campaignKeys.size() < 1) {
                finish();
                return;
            }
            this.promotions = new SparseArray();
            for (Promotion promotion : ADBrixHttpManager.schedule.getSchedule().getPromotions()) {
                if (this.campaignKeys.contains(Integer.valueOf(promotion.getCampaignKey()))) {
                    this.promotions.put(promotion.getCampaignKey(), promotion);
                }
            }
            requestWindowFeature(1);
            this.windowPadding = CPEConstant.convertPixelToDP(this, 10, true);
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.flags = 2;
            attributes.dimAmount = 0.7f;
            attributes.width = -1;
            attributes.height = -1;
            attributes.gravity = 17;
            getWindow().setAttributes(attributes);
            getWindow().setSoftInputMode(16);
            getWindow().getDecorView().setBackgroundColor(0);
            getWindow().getDecorView().setPadding(0, 0, 0, 0);
            getWindow().setFormat(1);
            getWindow().addFlags(4096);
            getWindow().setGravity(17);
            if (getResources().getConfiguration().orientation == 2) {
                this.isPortrait = false;
            } else {
                this.isPortrait = true;
            }
            IgawLogger.Logging(this, "IGAW_QA", String.format("Promotion Dialog Open : primary campaign key = %d, current campaign key = %d, slide no = %d", new Object[]{Integer.valueOf(this.primaryCampaignKey), Integer.valueOf(this.currentCampaignKey), Integer.valueOf(this.currentSlideNo)}), 3);
            if (this.contentsProvider == null) {
                Media media = this.media;
                List list = this.campaignKeys;
                SparseArray sparseArray = this.promotions;
                boolean z2 = this.isPortrait;
                int i = this.currentCampaignKey;
                int i2 = this.primaryCampaignKey;
                String str = this.spaceKey;
                Handler handler = new Handler();
                if (bundle != null) {
                    z = false;
                }
                this.contentsProvider = FragmentActivityDialogContentsCreator.getInstance(this, this, media, list, sparseArray, z2, i, i2, str, this, handler, z, true);
            }
            this.containerParam = new LayoutParams(-1, -1);
            addContentView(this.contentsProvider.getRootView(), this.containerParam);
        } catch (Exception e) {
            finish();
            e.printStackTrace();
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        try {
            bundle.putInt("currentCampaignKey", this.contentsProvider.getCurrentCampaignKey());
            if (!(PlaceDetailsFragment.pdFragment == null || PlaceDetailsFragment.pdFragment.mPager == null)) {
                bundle.putInt("slideNo", PlaceDetailsFragment.pdFragment.mPager.getCurrentItem());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onSaveInstanceState(bundle);
    }

    protected void onResume() {
        super.onResume();
        isActive = true;
        if (this.contentsProvider == null) {
            this.contentsProvider = FragmentActivityDialogContentsCreator.getInstance(this, this, this.media, this.campaignKeys, this.promotions, this.isPortrait, this.currentCampaignKey, this.primaryCampaignKey, this.spaceKey, this, new Handler(), true, true);
        } else {
            this.contentsProvider.setActionListener(this);
        }
        this.contentsProvider.onResume();
    }

    protected void onPause() {
        super.onPause();
        isActive = false;
    }

    public void finishDialog() {
        if (promotionActionListener != null) {
            promotionActionListener.onHideDialog();
        }
        finish();
    }

    public void setSlideArea(int i, int i2) {
        r a = getSupportFragmentManager().a();
        a.a(6553, PlaceDetailsFragment.newInstance(i, i2, false));
        a.a(4099);
        a.b();
    }

    public void onPlayBtnClick() {
        try {
            if (promotionActionListener != null) {
                promotionActionListener.onPlayButtonClick();
            }
            if (onPlayBtnClickListener != null) {
                onPlayBtnClickListener.run();
            }
        } catch (Exception e) {
        }
    }
}
