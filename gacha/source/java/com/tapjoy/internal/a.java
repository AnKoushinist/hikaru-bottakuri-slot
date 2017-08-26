package com.tapjoy.internal;

import android.app.Notification;
import android.app.Notification.BigPictureStyle;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;

public final class a {
    private static final f a;

    public static class a {
        public int a;
        public CharSequence b;
        public PendingIntent c;
    }

    public static abstract class l {
        d d;
        public CharSequence e;
        CharSequence f;
        boolean g = false;
    }

    public static class b extends l {
        Bitmap a;
        Bitmap b;
        boolean c;
    }

    public static class c extends l {
        public CharSequence a;
    }

    public static class d {
        Context a;
        public CharSequence b;
        public CharSequence c;
        public PendingIntent d;
        PendingIntent e;
        RemoteViews f;
        public Bitmap g;
        CharSequence h;
        int i;
        int j;
        boolean k;
        l l;
        CharSequence m;
        int n;
        int o;
        boolean p;
        ArrayList q = new ArrayList();
        public Notification r = new Notification();

        public d(Context context) {
            this.a = context;
            this.r.when = System.currentTimeMillis();
            this.r.audioStreamType = -1;
            this.j = 0;
        }

        public final d a(l lVar) {
            if (this.l != lVar) {
                this.l = lVar;
                if (this.l != null) {
                    l lVar2 = this.l;
                    if (lVar2.d != this) {
                        lVar2.d = this;
                        if (lVar2.d != null) {
                            lVar2.d.a(lVar2);
                        }
                    }
                }
            }
            return this;
        }
    }

    public static class e extends l {
        ArrayList a = new ArrayList();
    }

    public interface f {
        Notification a(d dVar);
    }

    static class g implements f {
        g() {
        }

        public Notification a(d dVar) {
            Notification notification = dVar.r;
            notification.setLatestEventInfo(dVar.a, dVar.b, dVar.c, dVar.d);
            if (dVar.j > 0) {
                notification.flags |= 128;
            }
            return notification;
        }
    }

    static class h extends g {
        h() {
        }

        public final Notification a(d dVar) {
            Notification notification = dVar.r;
            notification.setLatestEventInfo(dVar.a, dVar.b, dVar.c, dVar.d);
            Context context = dVar.a;
            CharSequence charSequence = dVar.b;
            CharSequence charSequence2 = dVar.c;
            PendingIntent pendingIntent = dVar.d;
            PendingIntent pendingIntent2 = dVar.e;
            notification.setLatestEventInfo(context, charSequence, charSequence2, pendingIntent);
            notification.fullScreenIntent = pendingIntent2;
            if (dVar.j > 0) {
                notification.flags |= 128;
            }
            return notification;
        }
    }

    static class i implements f {
        i() {
        }

        public final Notification a(d dVar) {
            boolean z;
            Context context = dVar.a;
            Notification notification = dVar.r;
            CharSequence charSequence = dVar.b;
            CharSequence charSequence2 = dVar.c;
            CharSequence charSequence3 = dVar.h;
            RemoteViews remoteViews = dVar.f;
            int i = dVar.i;
            PendingIntent pendingIntent = dVar.d;
            PendingIntent pendingIntent2 = dVar.e;
            Bitmap bitmap = dVar.g;
            Builder lights = new Builder(context).setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteViews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS);
            if ((notification.flags & 2) != 0) {
                z = true;
            } else {
                z = false;
            }
            lights = lights.setOngoing(z);
            if ((notification.flags & 8) != 0) {
                z = true;
            } else {
                z = false;
            }
            lights = lights.setOnlyAlertOnce(z);
            if ((notification.flags & 16) != 0) {
                z = true;
            } else {
                z = false;
            }
            Builder deleteIntent = lights.setAutoCancel(z).setDefaults(notification.defaults).setContentTitle(charSequence).setContentText(charSequence2).setContentInfo(charSequence3).setContentIntent(pendingIntent).setDeleteIntent(notification.deleteIntent);
            if ((notification.flags & 128) != 0) {
                z = true;
            } else {
                z = false;
            }
            return deleteIntent.setFullScreenIntent(pendingIntent2, z).setLargeIcon(bitmap).setNumber(i).getNotification();
        }
    }

    static class j implements f {
        j() {
        }

        public final Notification a(d dVar) {
            boolean z;
            Context context = dVar.a;
            Notification notification = dVar.r;
            CharSequence charSequence = dVar.b;
            CharSequence charSequence2 = dVar.c;
            CharSequence charSequence3 = dVar.h;
            RemoteViews remoteViews = dVar.f;
            int i = dVar.i;
            PendingIntent pendingIntent = dVar.d;
            PendingIntent pendingIntent2 = dVar.e;
            Bitmap bitmap = dVar.g;
            int i2 = dVar.n;
            int i3 = dVar.o;
            boolean z2 = dVar.p;
            Builder lights = new Builder(context).setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteViews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS);
            if ((notification.flags & 2) != 0) {
                z = true;
            } else {
                z = false;
            }
            lights = lights.setOngoing(z);
            if ((notification.flags & 8) != 0) {
                z = true;
            } else {
                z = false;
            }
            lights = lights.setOnlyAlertOnce(z);
            if ((notification.flags & 16) != 0) {
                z = true;
            } else {
                z = false;
            }
            Builder deleteIntent = lights.setAutoCancel(z).setDefaults(notification.defaults).setContentTitle(charSequence).setContentText(charSequence2).setContentInfo(charSequence3).setContentIntent(pendingIntent).setDeleteIntent(notification.deleteIntent);
            if ((notification.flags & 128) != 0) {
                z = true;
            } else {
                z = false;
            }
            return deleteIntent.setFullScreenIntent(pendingIntent2, z).setLargeIcon(bitmap).setNumber(i).setProgress(i2, i3, z2).getNotification();
        }
    }

    static class k implements f {
        k() {
        }

        public final Notification a(d dVar) {
            b bVar = new b(dVar.a, dVar.r, dVar.b, dVar.c, dVar.h, dVar.f, dVar.i, dVar.d, dVar.e, dVar.g, dVar.n, dVar.o, dVar.p, dVar.k, dVar.j, dVar.m);
            Iterator it = dVar.q.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                bVar.a.addAction(aVar.a, aVar.b, aVar.c);
            }
            if (dVar.l != null) {
                CharSequence charSequence;
                boolean z;
                CharSequence charSequence2;
                if (dVar.l instanceof c) {
                    c cVar = (c) dVar.l;
                    charSequence = cVar.e;
                    z = cVar.g;
                    charSequence2 = cVar.f;
                    BigTextStyle bigText = new BigTextStyle(bVar.a).setBigContentTitle(charSequence).bigText(cVar.a);
                    if (z) {
                        bigText.setSummaryText(charSequence2);
                    }
                } else if (dVar.l instanceof e) {
                    e eVar = (e) dVar.l;
                    bVar.a(eVar.e, eVar.g, eVar.f, eVar.a);
                } else if (dVar.l instanceof b) {
                    b bVar2 = (b) dVar.l;
                    charSequence = bVar2.e;
                    z = bVar2.g;
                    charSequence2 = bVar2.f;
                    Bitmap bitmap = bVar2.a;
                    Bitmap bitmap2 = bVar2.b;
                    boolean z2 = bVar2.c;
                    BigPictureStyle bigPicture = new BigPictureStyle(bVar.a).setBigContentTitle(charSequence).bigPicture(bitmap);
                    if (z2) {
                        bigPicture.bigLargeIcon(bitmap2);
                    }
                    if (z) {
                        bigPicture.setSummaryText(charSequence2);
                    }
                }
            }
            return bVar.a.build();
        }
    }

    static {
        if (VERSION.SDK_INT >= 16) {
            a = new k();
        } else if (VERSION.SDK_INT >= 14) {
            a = new j();
        } else if (VERSION.SDK_INT >= 11) {
            a = new i();
        } else if (VERSION.SDK_INT >= 9) {
            a = new h();
        } else {
            a = new g();
        }
    }
}
