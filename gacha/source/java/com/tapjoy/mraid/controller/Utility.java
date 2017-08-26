package com.tapjoy.mraid.controller;

import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.CalendarContract.Events;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyLog;
import com.tapjoy.mraid.view.MraidView;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@TargetApi(14)
public class Utility extends Abstract {
    private Assets c;
    private Display d;
    private MraidLocation e;
    private Network f;
    private MraidSensor g;

    public Utility(MraidView mraidView, Context context) {
        super(mraidView, context);
        this.c = new Assets(mraidView, context);
        this.d = new Display(mraidView, context);
        this.e = new MraidLocation(mraidView, context);
        this.f = new Network(mraidView, context);
        this.g = new MraidSensor(mraidView, context);
        mraidView.addJavascriptInterface(this.c, "MRAIDAssetsControllerBridge");
        mraidView.addJavascriptInterface(this.d, "MRAIDDisplayControllerBridge");
        mraidView.addJavascriptInterface(this.e, "MRAIDLocationControllerBridge");
        mraidView.addJavascriptInterface(this.f, "MRAIDNetworkControllerBridge");
        mraidView.addJavascriptInterface(this.g, "MRAIDSensorControllerBridge");
    }

    public void init(float f) {
        boolean z = false;
        StringBuilder append = new StringBuilder("window.mraidview.fireChangeEvent({ state: 'default', network: '").append(this.f.getNetwork()).append("', size: ").append(this.d.getSize()).append(", placement: '").append(this.a.getPlacementType()).append("', maxSize: ").append(this.d.getMaxSize()).append(",expandProperties: ").append(this.d.getMaxSize()).append(", screenSize: ").append(this.d.getScreenSize()).append(", defaultPosition: { x:").append((int) (((float) this.a.getLeft()) / f)).append(", y: ").append((int) (((float) this.a.getTop()) / f)).append(", width: ").append((int) (((float) this.a.getWidth()) / f)).append(", height: ").append((int) (((float) this.a.getHeight()) / f)).append(" }, orientation:").append(this.d.getOrientation()).append(",");
        String str = "supports: [ 'level-1', 'level-2', 'screen', 'orientation', 'network'";
        boolean z2 = this.e.allowLocationServices() && (this.b.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0 || this.b.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0);
        if (z2) {
            str = str + ", 'location'";
        }
        if (this.b.checkCallingOrSelfPermission("android.permission.SEND_SMS") == 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            str = str + ", 'sms'";
        }
        if (this.b.checkCallingOrSelfPermission("android.permission.CALL_PHONE") == 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            str = str + ", 'phone'";
        }
        if (this.b.checkCallingOrSelfPermission("android.permission.WRITE_CALENDAR") == 0 && this.b.checkCallingOrSelfPermission("android.permission.READ_CALENDAR") == 0 && VERSION.SDK_INT >= 14) {
            z = true;
        }
        if (z) {
            str = str + ", 'calendar'";
        }
        str = (((str + ", 'video'") + ", 'audio'") + ", 'map'") + ", 'email' ]";
        TapjoyLog.d("MRAID Utility", "getSupports: " + str);
        str = append.append(str).append(",viewable:true });").toString();
        TapjoyLog.d("MRAID Utility", "init: injection: " + str);
        this.a.injectMraidJavaScript(str);
        fireReadyEvent();
        fireViewableChange(true);
    }

    public void fireReadyEvent() {
        this.a.injectMraidJavaScript("mraid.signalReady();");
    }

    public void fireViewableChange(boolean z) {
        this.a.injectMraidJavaScript("window.mraidview.fireChangeEvent({viewable:" + z + "});");
    }

    @JavascriptInterface
    public void sendSMS(String str, String str2) {
        TapjoyLog.d("MRAID Utility", "sendSMS: recipient: " + str + " body: " + str2);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.putExtra("address", str);
        intent.putExtra("sms_body", str2);
        intent.setType("vnd.android-dir/mms-sms");
        intent.addFlags(268435456);
        this.b.startActivity(intent);
    }

    @JavascriptInterface
    public void sendMail(String str, String str2, String str3) {
        TapjoyLog.d("MRAID Utility", "sendMail: recipient: " + str + " subject: " + str2 + " body: " + str3);
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("plain/text");
        intent.putExtra("android.intent.extra.EMAIL", new String[]{str});
        intent.putExtra("android.intent.extra.SUBJECT", str2);
        intent.putExtra("android.intent.extra.TEXT", str3);
        intent.addFlags(268435456);
        this.b.startActivity(intent);
    }

    @JavascriptInterface
    public void makeCall(String str) {
        String str2;
        TapjoyLog.d("MRAID Utility", "makeCall: number: " + str);
        if (TextUtils.isEmpty(str)) {
            str2 = null;
        } else {
            StringBuilder stringBuilder = new StringBuilder("tel:");
            stringBuilder.append(str);
            str2 = stringBuilder.toString();
        }
        if (str2 == null) {
            this.a.raiseError("Bad Phone Number", "makeCall");
            return;
        }
        Intent intent = new Intent("android.intent.action.CALL", Uri.parse(str2.toString()));
        intent.addFlags(268435456);
        this.b.startActivity(intent);
    }

    @JavascriptInterface
    public void mraidCreateEvent(String str) {
        String optString;
        JSONException jSONException;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6 = null;
        if (VERSION.SDK_INT >= 14) {
            Calendar instance = Calendar.getInstance();
            Calendar instance2 = Calendar.getInstance();
            try {
                JSONObject jSONObject;
                JSONObject jSONObject2 = new JSONObject(str);
                JSONObject jSONObject3 = jSONObject2.getJSONObject(String.VIDEO_START);
                JSONObject optJSONObject = jSONObject2.optJSONObject("end");
                if (optJSONObject == null) {
                    jSONObject = jSONObject3;
                } else {
                    jSONObject = optJSONObject;
                }
                String string = jSONObject2.getString("description");
                try {
                    optString = jSONObject2.optString("location");
                } catch (JSONException e) {
                    jSONException = e;
                    str2 = str6;
                    str3 = str6;
                    str4 = string;
                    str5 = str6;
                    jSONException.printStackTrace();
                    this.a.getContext().startActivity(new Intent("android.intent.action.INSERT").setData(Events.CONTENT_URI).putExtra("beginTime", instance.getTimeInMillis()).putExtra("endTime", instance2.getTimeInMillis()).putExtra(String.TITLE, str2).putExtra("description", str4).putExtra("eventLocation", str3).putExtra("eventStatus", str5));
                }
                try {
                    String optString2 = jSONObject2.optString("summary");
                    try {
                        str6 = jSONObject2.optString("status");
                    } catch (JSONException e2) {
                        jSONException = e2;
                        str2 = optString2;
                        str3 = optString;
                        str4 = string;
                        str5 = str6;
                        jSONException.printStackTrace();
                        this.a.getContext().startActivity(new Intent("android.intent.action.INSERT").setData(Events.CONTENT_URI).putExtra("beginTime", instance.getTimeInMillis()).putExtra("endTime", instance2.getTimeInMillis()).putExtra(String.TITLE, str2).putExtra("description", str4).putExtra("eventLocation", str3).putExtra("eventStatus", str5));
                    }
                    try {
                        instance.set(jSONObject3.getInt("year"), jSONObject3.getInt("month"), jSONObject3.getInt("day"), jSONObject3.getInt("hour"), jSONObject3.getInt("min"));
                        instance2.set(jSONObject.getInt("year"), jSONObject.getInt("month"), jSONObject.getInt("day"), jSONObject.getInt("hour"), jSONObject.getInt("min"));
                        str5 = str6;
                        str2 = optString2;
                        str3 = optString;
                        str4 = string;
                    } catch (JSONException e22) {
                        jSONException = e22;
                        str2 = optString2;
                        str3 = optString;
                        str4 = string;
                        str5 = str6;
                        jSONException.printStackTrace();
                        this.a.getContext().startActivity(new Intent("android.intent.action.INSERT").setData(Events.CONTENT_URI).putExtra("beginTime", instance.getTimeInMillis()).putExtra("endTime", instance2.getTimeInMillis()).putExtra(String.TITLE, str2).putExtra("description", str4).putExtra("eventLocation", str3).putExtra("eventStatus", str5));
                    }
                } catch (JSONException e222) {
                    jSONException = e222;
                    str2 = str6;
                    str3 = optString;
                    str4 = string;
                    str5 = str6;
                    jSONException.printStackTrace();
                    this.a.getContext().startActivity(new Intent("android.intent.action.INSERT").setData(Events.CONTENT_URI).putExtra("beginTime", instance.getTimeInMillis()).putExtra("endTime", instance2.getTimeInMillis()).putExtra(String.TITLE, str2).putExtra("description", str4).putExtra("eventLocation", str3).putExtra("eventStatus", str5));
                }
            } catch (JSONException e2222) {
                jSONException = e2222;
                str2 = str6;
                str3 = str6;
                str4 = str6;
                str5 = str6;
                jSONException.printStackTrace();
                this.a.getContext().startActivity(new Intent("android.intent.action.INSERT").setData(Events.CONTENT_URI).putExtra("beginTime", instance.getTimeInMillis()).putExtra("endTime", instance2.getTimeInMillis()).putExtra(String.TITLE, str2).putExtra("description", str4).putExtra("eventLocation", str3).putExtra("eventStatus", str5));
            }
            this.a.getContext().startActivity(new Intent("android.intent.action.INSERT").setData(Events.CONTENT_URI).putExtra("beginTime", instance.getTimeInMillis()).putExtra("endTime", instance2.getTimeInMillis()).putExtra(String.TITLE, str2).putExtra("description", str4).putExtra("eventLocation", str3).putExtra("eventStatus", str5));
        }
    }

    @JavascriptInterface
    public void createEvent(String str, String str2, String str3) {
        Cursor query;
        TapjoyLog.d("MRAID Utility", "createEvent: date: " + str + " title: " + str2 + " body: " + str3);
        ContentResolver contentResolver = this.b.getContentResolver();
        String[] strArr = new String[]{"_id", "displayName", "_sync_account"};
        if (Integer.parseInt(VERSION.SDK) == 8) {
            query = contentResolver.query(Uri.parse("content://com.android.calendar/calendars"), strArr, null, null, null);
        } else {
            query = contentResolver.query(Uri.parse("content://calendar/calendars"), strArr, null, null, null);
        }
        if (query == null || !(query == null || query.moveToFirst())) {
            Toast.makeText(this.b, "No calendar account found", 1).show();
            if (query != null) {
                query.close();
                return;
            }
            return;
        }
        if (query.getCount() == 1) {
            a(query.getInt(0), str, str2, str3);
        } else {
            List arrayList = new ArrayList();
            for (int i = 0; i < query.getCount(); i++) {
                Map hashMap = new HashMap();
                hashMap.put("ID", query.getString(0));
                hashMap.put("NAME", query.getString(1));
                hashMap.put("EMAILID", query.getString(2));
                arrayList.add(hashMap);
                query.moveToNext();
            }
            Builder builder = new Builder(this.b);
            builder.setTitle("Choose Calendar to save event to");
            final List list = arrayList;
            final String str4 = str;
            final String str5 = str2;
            final String str6 = str3;
            builder.setSingleChoiceItems(new SimpleAdapter(this.b, arrayList, 17367053, new String[]{"NAME", "EMAILID"}, new int[]{16908308, 16908309}), -1, new OnClickListener(this) {
                final /* synthetic */ Utility e;

                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.e.a(Integer.parseInt((String) ((Map) list.get(i)).get("ID")), str4, str5, str6);
                    dialogInterface.cancel();
                }
            });
            builder.create().show();
        }
        query.close();
    }

    private void a(int i, String str, String str2, String str3) {
        Uri insert;
        ContentResolver contentResolver = this.b.getContentResolver();
        long parseLong = Long.parseLong(str);
        long j = 3600000 + parseLong;
        ContentValues contentValues = new ContentValues();
        contentValues.put("calendar_id", Integer.valueOf(i));
        contentValues.put(String.TITLE, str2);
        contentValues.put("description", str3);
        contentValues.put("dtstart", Long.valueOf(parseLong));
        contentValues.put("hasAlarm", Integer.valueOf(1));
        contentValues.put("dtend", Long.valueOf(j));
        if (Integer.parseInt(VERSION.SDK) == 8) {
            insert = contentResolver.insert(Uri.parse("content://com.android.calendar/events"), contentValues);
        } else {
            insert = contentResolver.insert(Uri.parse("content://calendar/events"), contentValues);
        }
        if (insert != null) {
            parseLong = Long.parseLong(insert.getLastPathSegment());
            contentValues = new ContentValues();
            contentValues.put("event_id", Long.valueOf(parseLong));
            contentValues.put(String.METHOD, Integer.valueOf(1));
            contentValues.put("minutes", Integer.valueOf(15));
            if (Integer.parseInt(VERSION.SDK) == 8) {
                contentResolver.insert(Uri.parse("content://com.android.calendar/reminders"), contentValues);
            } else {
                contentResolver.insert(Uri.parse("content://calendar/reminders"), contentValues);
            }
        }
        Toast.makeText(this.b, "Event added to calendar", 0).show();
    }

    public String copyTextFromJarIntoAssetDir(String str, String str2) {
        return this.c.copyTextFromJarIntoAssetDir(str, str2);
    }

    public void setMaxSize(int i, int i2) {
        this.d.setMaxSize(i, i2);
    }

    public String writeToDiskWrap(InputStream inputStream, String str, boolean z, String str2, String str3) {
        return this.c.writeToDiskWrap(inputStream, str, z, str2, str3);
    }

    @JavascriptInterface
    public void activate(String str) {
        TapjoyLog.d("MRAID Utility", "activate: " + str);
        if (str.equalsIgnoreCase(Defines.Events.NETWORK_CHANGE)) {
            this.f.startNetworkListener();
        } else if (this.e.allowLocationServices() && str.equalsIgnoreCase(Defines.Events.LOCATION_CHANGE)) {
            this.e.startLocationListener();
        } else if (str.equalsIgnoreCase(Defines.Events.SHAKE)) {
            this.g.startShakeListener();
        } else if (str.equalsIgnoreCase(Defines.Events.TILT_CHANGE)) {
            this.g.startTiltListener();
        } else if (str.equalsIgnoreCase(Defines.Events.HEADING_CHANGE)) {
            this.g.startHeadingListener();
        } else if (str.equalsIgnoreCase(Defines.Events.ORIENTATION_CHANGE)) {
            this.d.startConfigurationListener();
        }
    }

    @JavascriptInterface
    public void deactivate(String str) {
        TapjoyLog.d("MRAID Utility", "deactivate: " + str);
        if (str.equalsIgnoreCase(Defines.Events.NETWORK_CHANGE)) {
            this.f.stopNetworkListener();
        } else if (str.equalsIgnoreCase(Defines.Events.LOCATION_CHANGE)) {
            this.e.stopAllListeners();
        } else if (str.equalsIgnoreCase(Defines.Events.SHAKE)) {
            this.g.stopShakeListener();
        } else if (str.equalsIgnoreCase(Defines.Events.TILT_CHANGE)) {
            this.g.stopTiltListener();
        } else if (str.equalsIgnoreCase(Defines.Events.HEADING_CHANGE)) {
            this.g.stopHeadingListener();
        } else if (str.equalsIgnoreCase(Defines.Events.ORIENTATION_CHANGE)) {
            this.d.stopConfigurationListener();
        }
    }

    public void deleteOldAds() {
        this.c.deleteOldAds();
    }

    public void stopAllListeners() {
        try {
            this.c.stopAllListeners();
            this.d.stopAllListeners();
            this.e.stopAllListeners();
            this.f.stopAllListeners();
            this.g.stopAllListeners();
        } catch (Exception e) {
        }
    }

    @JavascriptInterface
    public void showAlert(String str) {
        TapjoyLog.e("MRAID Utility", str);
    }
}
