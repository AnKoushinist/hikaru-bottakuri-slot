package com.igaworks.adbrix.interfaces;

import android.app.Activity;
import com.igaworks.interfaces.CommonInterface;

public interface ADBrixInterface extends CommonInterface {
    void buy(String str, String str2);

    void firstTimeExperience(String str, String str2);

    void retention(String str, String str2);

    void showRealRewardNotice(Activity activity);

    void showViralCPINotice(Activity activity);
}
