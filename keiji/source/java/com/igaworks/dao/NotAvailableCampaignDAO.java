package com.igaworks.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.Collection;

public class NotAvailableCampaignDAO {
    private static NotAvailableCampaignDAO singleton;
    private Editor notAvailablecampaignEditor;
    private SharedPreferences notAvailablecampaignSP;

    private NotAvailableCampaignDAO() {
    }

    public static NotAvailableCampaignDAO getInstance() {
        if (singleton == null) {
            singleton = new NotAvailableCampaignDAO();
        }
        return singleton;
    }

    private SharedPreferences getSharedPreference(Context context) {
        if (this.notAvailablecampaignSP == null) {
            this.notAvailablecampaignSP = context.getSharedPreferences("not_available_campaign_sp", 0);
        }
        return this.notAvailablecampaignSP;
    }

    private Editor getEditor(Context context) {
        if (this.notAvailablecampaignEditor == null) {
            this.notAvailablecampaignEditor = getSharedPreference(context).edit();
        }
        return this.notAvailablecampaignEditor;
    }

    public void saveNotAvailableCampaign(final Context context, final int i) {
        new Thread(new Runnable() {
            public void run() {
                NotAvailableCampaignDAO.this.getEditor(context).putInt(new StringBuilder(String.valueOf(i)).toString(), i);
                NotAvailableCampaignDAO.this.getEditor(context).commit();
            }
        }).start();
    }

    public Collection<Integer> getNotAvailableCampaign(Context context) {
        return getSharedPreference(context).getAll().values();
    }
}
