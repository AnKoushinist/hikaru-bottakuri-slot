package com.igaworks.adbrix.cpe;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.CountDownTimer;
import android.support.v4.app.ad.d;
import android.text.Html;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.igaworks.core.IgawConstant;
import com.igaworks.util.IgawStyler;

public class CPEProgressBarHandler {
    private static Toast popupToast;

    class AnonymousClass1 extends CountDownTimer {
        AnonymousClass1(long j, long j2) {
            super(j, j2);
        }

        public void onTick(long j) {
            CPEProgressBarHandler.popupToast.show();
        }

        public void onFinish() {
            CPEProgressBarHandler.popupToast.show();
        }
    }

    public static void makeToastPopup(Context context, String str, String str2, int i, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        View linearLayout;
        LayoutParams layoutParams;
        View imageView;
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        int height = defaultDisplay.getHeight();
        if (IgawStyler.toastPopup.toast_width == 0) {
            i4 = (int) (((double) width) * 0.8d);
        } else {
            i4 = (int) (((double) width) * (((double) IgawStyler.toastPopup.toast_width) * 0.01d));
        }
        if (IgawStyler.toastPopup.positionX == 0) {
            width = 0;
        } else {
            width = IgawStyler.toastPopup.positionX;
        }
        if (IgawStyler.toastPopup.positionY == 0) {
            i5 = 30;
        } else {
            i5 = IgawStyler.toastPopup.positionY;
        }
        if (IgawStyler.toastPopup.alpha == -1) {
            i6 = 50;
        } else {
            i6 = IgawStyler.toastPopup.alpha;
        }
        if (IgawStyler.toastPopup.toast_duration != 0) {
            i3 = IgawStyler.toastPopup.toast_duration - 3000;
            if (i3 <= 0) {
                i3 = 0;
            }
        }
        if (IgawStyler.toastPopup.title_gravity == 0) {
            i7 = 3;
        } else {
            i7 = IgawStyler.toastPopup.title_gravity;
        }
        if (IgawStyler.toastPopup.popup_message_height == 0) {
            height = (int) (((double) height) * 0.15d);
        } else {
            height = (int) (((double) height) * (((double) IgawStyler.toastPopup.popup_message_height) * 0.01d));
        }
        Drawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(-16777216);
        gradientDrawable.setAlpha(i6);
        gradientDrawable.setSize(i4, -2);
        gradientDrawable.setCornerRadius(5.0f);
        View linearLayout2 = new LinearLayout(context);
        linearLayout2.setOrientation(1);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(i4, -2));
        linearLayout2.setPadding(5, 5, 5, 5);
        Drawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setShape(0);
        gradientDrawable2.setColor(-16777216);
        gradientDrawable2.setAlpha(100);
        gradientDrawable2.setSize(i4, -2);
        gradientDrawable2.setCornerRadius(5.0f);
        linearLayout2.setBackgroundDrawable(gradientDrawable2);
        if (!(str == null || str.length() <= 0 || str.equals("null"))) {
            linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(1);
            layoutParams = new LinearLayout.LayoutParams(i4, -2);
            linearLayout.setPadding(3, 3, 3, 0);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setBackgroundDrawable(gradientDrawable);
            layoutParams = new LinearLayout.LayoutParams(i4, -2);
            View textView = new TextView(context);
            textView.setLayoutParams(layoutParams);
            textView.setTextColor(-1);
            textView.setText(str);
            textView.setGravity(i7 | 16);
            textView.setPadding(5, 3, 5, 3);
            linearLayout.addView(textView);
            imageView = new ImageView(context);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(i4, 1));
            imageView.setBackgroundColor(-7829368);
            linearLayout.addView(imageView);
            linearLayout2.addView(linearLayout);
        }
        imageView = new LinearLayout(context);
        imageView.setOrientation(0);
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(i4, -2);
        imageView.setPadding(3, 3, 3, 3);
        imageView.setLayoutParams(layoutParams2);
        imageView.setBackgroundDrawable(gradientDrawable);
        linearLayout = new TextView(context);
        layoutParams = new LinearLayout.LayoutParams(-1, -2);
        linearLayout.setBackgroundDrawable(gradientDrawable);
        linearLayout.setGravity(51);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setPadding(10, 10, 10, 10);
        linearLayout.setTextColor(-1);
        linearLayout.setText(str2);
        imageView.addView(linearLayout);
        linearLayout2.addView(imageView);
        if (i > 1) {
            imageView = new LinearLayout(context);
            layoutParams2 = new LinearLayout.LayoutParams(i4, -2);
            imageView.setLayoutParams(layoutParams2);
            imageView.setPadding(0, 7, 0, 0);
            View progressBar = new ProgressBar(context, null, 16842872);
            progressBar.setLayoutParams(layoutParams2);
            progressBar.setMax(i);
            progressBar.setProgress(i2);
            imageView.addView(progressBar);
            imageView.setBackgroundDrawable(gradientDrawable);
            linearLayout2.addView(imageView);
            IgawConstant igawConstant = new IgawConstant();
            LayoutParams layoutParams3 = new LinearLayout.LayoutParams(i4, -2);
            View textView2 = new TextView(context);
            textView2.setLayoutParams(layoutParams3);
            textView2.setPadding(0, 3, 0, 0);
            textView2.setTextColor(-16777216);
            textView2.setText(igawConstant.process + ((i2 * 100) / i) + " %" + igawConstant.complete);
            textView2.setGravity(5);
            linearLayout2.addView(textView2);
        }
        if (popupToast == null) {
            popupToast = new Toast(context);
            popupToast.setGravity(80, width, i5);
        }
        popupToast.setView(linearLayout2);
        popupToast.show();
        new AnonymousClass1((long) i3, 100).start();
    }

    public static void setNotification(Context context, String str, String str2) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        d b = new d(context).a(Html.fromHtml(str)).b(Html.fromHtml(str2)).a(context.getApplicationInfo().icon).b(true);
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        launchIntentForPackage.setFlags(603979776);
        b.a(PendingIntent.getActivity(context, 0, launchIntentForPackage, 268435456));
        Notification a = b.a();
        a.flags |= 16;
        a.defaults |= 1;
        a.defaults |= 2;
        notificationManager.notify(0, a);
    }
}
