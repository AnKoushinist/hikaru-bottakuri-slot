package com.igaworks.dao;

import android.content.Context;
import android.content.SharedPreferences;

public class CohortDAO {
    public static String cohort1;
    public static String cohort2;
    public static String cohort3;

    public static SharedPreferences getCohortSP(Context context) {
        return context.getSharedPreferences("cohorts", 0);
    }

    public static String getCohort(Context context, String str) {
        if (str.equals("custom_cohort_1") && cohort1 != null && cohort1.length() > 0) {
            return cohort1;
        }
        if (str.equals("custom_cohort_2") && cohort2 != null && cohort2.length() > 0) {
            return cohort2;
        }
        if (str.equals("custom_cohort_3") && cohort3 != null && cohort3.length() > 0) {
            return cohort3;
        }
        String string = getCohortSP(context).getString(str, null);
        if (str.equals("custom_cohort_1")) {
            cohort1 = string;
            return string;
        } else if (str.equals("custom_cohort_2")) {
            cohort2 = string;
            return string;
        } else if (!str.equals("custom_cohort_3")) {
            return string;
        } else {
            cohort3 = string;
            return string;
        }
    }
}
