package com.squareup.picasso;

import android.app.Notification;
import android.app.NotificationManager;
import android.appwidget.AppWidgetManager;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import com.squareup.picasso.Picasso.LoadedFrom;

abstract class RemoteViewsAction extends Action<RemoteViewsTarget> {
    final RemoteViews remoteViews;
    private RemoteViewsTarget target;
    final int viewId;

    static class AppWidgetAction extends RemoteViewsAction {
        private final int[] appWidgetIds;

        /* bridge */ /* synthetic */ Object getTarget() {
            return super.getTarget();
        }

        AppWidgetAction(Picasso picasso, Request request, RemoteViews remoteViews, int i, int[] iArr, boolean z, int i2, String str, Object obj) {
            super(picasso, request, remoteViews, i, i2, z, str, obj);
            this.appWidgetIds = iArr;
        }

        void update() {
            AppWidgetManager.getInstance(this.picasso.context).updateAppWidget(this.appWidgetIds, this.remoteViews);
        }
    }

    static class NotificationAction extends RemoteViewsAction {
        private final Notification notification;
        private final int notificationId;

        /* bridge */ /* synthetic */ Object getTarget() {
            return super.getTarget();
        }

        NotificationAction(Picasso picasso, Request request, RemoteViews remoteViews, int i, int i2, Notification notification, boolean z, int i3, String str, Object obj) {
            super(picasso, request, remoteViews, i, i3, z, str, obj);
            this.notificationId = i2;
            this.notification = notification;
        }

        void update() {
            ((NotificationManager) Utils.getService(this.picasso.context, "notification")).notify(this.notificationId, this.notification);
        }
    }

    static class RemoteViewsTarget {
        final RemoteViews remoteViews;
        final int viewId;

        RemoteViewsTarget(RemoteViews remoteViews, int i) {
            this.remoteViews = remoteViews;
            this.viewId = i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            RemoteViewsTarget remoteViewsTarget = (RemoteViewsTarget) obj;
            if (this.viewId == remoteViewsTarget.viewId && this.remoteViews.equals(remoteViewsTarget.remoteViews)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (this.remoteViews.hashCode() * 31) + this.viewId;
        }
    }

    abstract void update();

    RemoteViewsAction(Picasso picasso, Request request, RemoteViews remoteViews, int i, int i2, boolean z, String str, Object obj) {
        super(picasso, null, request, z, false, i2, null, str, obj);
        this.remoteViews = remoteViews;
        this.viewId = i;
    }

    void complete(Bitmap bitmap, LoadedFrom loadedFrom) {
        this.remoteViews.setImageViewBitmap(this.viewId, bitmap);
        update();
    }

    public void error() {
        if (this.errorResId != 0) {
            setImageResource(this.errorResId);
        }
    }

    RemoteViewsTarget getTarget() {
        if (this.target == null) {
            this.target = new RemoteViewsTarget(this.remoteViews, this.viewId);
        }
        return this.target;
    }

    void setImageResource(int i) {
        this.remoteViews.setImageViewResource(this.viewId, i);
        update();
    }
}
