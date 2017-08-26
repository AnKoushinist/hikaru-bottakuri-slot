package com.igaworks.impl;

import android.util.Log;
import com.igaworks.dao.ReferralInfoDAO;

public class ReferrerThread extends Thread {
    private volatile boolean stop = false;

    public void run() {
        Log.i("IGAW_QA", "ReferrerThread has started ");
        while (!this.stop) {
            try {
                if (ReferralInfoDAO.getOnReceiveReferralFlag(CommonFrameworkImpl.getContext()) || CommonFrameworkImpl.parameter.getReferralKey() == -1 || CommonFrameworkImpl.parameter.getADBrixUserNo() < 1) {
                    InternalAction.getInstance().referrerCallForAdbrix(CommonFrameworkImpl.getContext(), CommonFrameworkImpl.isTest, CommonFrameworkImpl.parameter, CommonFrameworkImpl.httpManager);
                    Thread.sleep(30000);
                } else {
                    requestStop();
                }
            } catch (Exception e) {
                Log.e("IGAW_QA", "ReferrerThread Error: " + e.getMessage());
                requestStop();
            }
        }
        if (this.stop) {
            Log.i("IGAW_QA", "ReferrerThread stopped");
        }
    }

    public void requestStop() {
        this.stop = true;
    }
}
