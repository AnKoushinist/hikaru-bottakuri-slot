package jp.co.mediasdk.android;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.View;
import java.util.ArrayList;

public class ArrayUtilCastSupport extends ArrayUtilConcatSupport {
    public static Context[] a(ArrayList<Context> arrayList) {
        if (arrayList == null) {
            return null;
        }
        Context[] contextArr = new Context[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            contextArr[i] = (Context) arrayList.get(i);
        }
        return contextArr;
    }

    public static View[] b(ArrayList<View> arrayList) {
        if (arrayList == null) {
            return null;
        }
        View[] viewArr = new View[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            viewArr[i] = (View) arrayList.get(i);
        }
        return viewArr;
    }

    public static Activity[] c(ArrayList<Activity> arrayList) {
        if (arrayList == null) {
            return null;
        }
        Activity[] activityArr = new Activity[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            activityArr[i] = (Activity) arrayList.get(i);
        }
        return activityArr;
    }

    public static Application[] d(ArrayList<Application> arrayList) {
        if (arrayList == null) {
            return null;
        }
        Application[] applicationArr = new Application[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            applicationArr[i] = (Application) arrayList.get(i);
        }
        return applicationArr;
    }
}
