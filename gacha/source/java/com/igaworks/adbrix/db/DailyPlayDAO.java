package com.igaworks.adbrix.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import com.igaworks.adbrix.core.ADBrixHttpManager;
import com.igaworks.adbrix.model.DailyPlay;
import com.igaworks.adbrix.model.RetryCompleteConversion;
import com.igaworks.core.IgawLogger;
import com.igaworks.core.RequestParameter;
import com.igaworks.util.bolts_task.Task;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.cocos2dx.lib.BuildConfig;

public class DailyPlayDAO {
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.KOREA);
    private static DailyPlayDAO singleton;
    boolean isChecking;
    int lastConversionKey;

    private DailyPlayDAO() {
        this.isChecking = false;
        this.lastConversionKey = -1;
        this.isChecking = false;
    }

    public static DailyPlayDAO getInstance() {
        if (singleton == null) {
            singleton = new DailyPlayDAO();
        }
        return singleton;
    }

    private SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences("daily_play_sp", 0);
    }

    private Editor getEditor(Context context) {
        return getSharedPreference(context).edit();
    }

    public void saveLastConversionDateTime(final Context context) {
        Task.BACKGROUND_EXECUTOR.execute(new Runnable() {
            public void run() {
                Editor access$0 = DailyPlayDAO.this.getEditor(context);
                access$0.putString("previous_date", DailyPlayDAO.sdf.format(new Date()));
                access$0.commit();
            }
        });
    }

    public void setLatestConversionKey(final Context context, final int i) {
        this.lastConversionKey = i;
        Task.BACKGROUND_EXECUTOR.execute(new Runnable() {
            public void run() {
                Editor access$0 = DailyPlayDAO.this.getEditor(context);
                access$0.putInt("LastConversionKey", i);
                access$0.commit();
            }
        });
    }

    public int getLatestConversionKey(Context context) {
        if (this.lastConversionKey != -1) {
            return this.lastConversionKey;
        }
        return getSharedPreference(context).getInt("LastConversionKey", -1);
    }

    public void setPendingConversionKey(final Context context, final int i) {
        Task.BACKGROUND_EXECUTOR.execute(new Runnable() {
            public void run() {
                Editor access$0 = DailyPlayDAO.this.getEditor(context);
                access$0.putInt("PendingConversionKey", i);
                access$0.commit();
            }
        });
    }

    public int getPendingConversionKey(Context context) {
        return getSharedPreference(context).getInt("PendingConversionKey", -1);
    }

    public void setPlayTime(final Context context, final int i) {
        Task.BACKGROUND_EXECUTOR.execute(new Runnable() {
            public void run() {
                Editor access$0 = DailyPlayDAO.this.getEditor(context);
                access$0.putInt("RequiredPlayTime", i);
                access$0.commit();
            }
        });
    }

    public int getPlayTime(Context context) {
        return getSharedPreference(context).getInt("RequiredPlayTime", Constants.IP_RETRY_TIME);
    }

    public void setWaitingTime(final Context context, final int i) {
        Task.BACKGROUND_EXECUTOR.execute(new Runnable() {
            public void run() {
                Editor access$0 = DailyPlayDAO.this.getEditor(context);
                access$0.putInt("waiting_time", i);
                access$0.commit();
            }
        });
    }

    public int getWaitingTime(Context context) {
        return getSharedPreference(context).getInt("waiting_time", -1);
    }

    public boolean canJoinCampaignToday(Context context) {
        try {
            if (this.isChecking) {
                Log.d("IGAW_QA", "DailyPlay Skip >> Multiple trigger");
                return false;
            }
            this.isChecking = true;
            int waitingTime = getWaitingTime(context);
            String string = getSharedPreference(context).getString("previous_date", BuildConfig.FLAVOR);
            boolean z;
            int i;
            if (string.equals(BuildConfig.FLAVOR)) {
                waitingTime = (int) RequestParameter.getATRequestParameter(context).getReferralKey();
                if (waitingTime > 0) {
                    ArrayList conversionCache = RequestParameter.getATRequestParameter(context).getConversionCache();
                    int size = conversionCache.size();
                    if (size > 0) {
                        List dailyPlay = ADBrixHttpManager.schedule.getSchedule().getReEngagement().getDailyPlay();
                        int size2 = dailyPlay.size();
                        if (size2 > 0) {
                            ArrayList arrayList = new ArrayList();
                            int i2 = waitingTime;
                            z = true;
                            while (z) {
                                for (i = 0; i < size2; i++) {
                                    if (((DailyPlay) dailyPlay.get(i)).getParentConversionKey() == i2) {
                                        waitingTime = ((DailyPlay) dailyPlay.get(i)).getConversionKey();
                                        arrayList.add(Integer.valueOf(waitingTime));
                                        i2 = waitingTime;
                                        z = true;
                                        break;
                                    }
                                }
                                z = false;
                            }
                            size2 = arrayList.size();
                            if (size2 > 0) {
                                int i3 = 0;
                                i = -1;
                                while (i3 < size2) {
                                    int intValue = ((Integer) arrayList.get(i3)).intValue();
                                    for (i2 = 0; i2 < size; i2++) {
                                        waitingTime = ((Integer) conversionCache.get(i2)).intValue();
                                        if (intValue == waitingTime) {
                                            break;
                                        }
                                    }
                                    waitingTime = i;
                                    i3++;
                                    i = waitingTime;
                                }
                            } else {
                                i = -1;
                            }
                            if (i != -1) {
                                Log.d("IGAW_QA", "DailyPlay Recover >> Last conversion Key = " + i);
                                setLatestConversionKey(context, i);
                                this.isChecking = false;
                                return true;
                            }
                            Log.d("IGAW_QA", "DailyPlay Skip >> First day app launches, recoverLastConversionKey is null ");
                            saveLastConversionDateTime(context);
                            this.isChecking = false;
                            return false;
                        }
                        Log.d("IGAW_QA", "DailyPlay Skip >> Campaign Size = " + dailyPlay.size());
                        saveLastConversionDateTime(context);
                        this.isChecking = false;
                        return false;
                    }
                    Log.d("IGAW_QA", "DailyPlay Skip >> First day app launches, empty conversion cache" + waitingTime);
                    saveLastConversionDateTime(context);
                    this.isChecking = false;
                    return false;
                }
                Log.d("IGAW_QA", "DailyPlay Skip >> Referrer: " + waitingTime);
                this.isChecking = false;
                return false;
            }
            if (waitingTime > 0) {
                try {
                    Date date = new Date();
                    Date parse = sdf.parse(string);
                    long time = date.getTime();
                    long time2 = parse.getTime() + ((long) waitingTime);
                    z = time > time2;
                    IgawLogger.Logging(context, "IGAW_QA", "Now: " + time + " > ExpectingTime: " + time2 + " >> " + z, 3);
                    if (z) {
                        setWaitingTime(context, -1);
                    }
                    if (!z) {
                        IgawLogger.Logging(context, "IGAW_QA", "Skip DailyPlayCP >> Waiting time not expire", 3, false);
                        saveLastConversionDateTime(context);
                    }
                    this.isChecking = false;
                    return z;
                } catch (Exception e) {
                    Log.e("IGAW_QA", "Error: " + e.toString());
                }
            }
            Calendar instance = Calendar.getInstance();
            Calendar instance2 = Calendar.getInstance();
            instance.setTime(sdf.parse(string));
            if (instance2.get(5) == instance.get(5) && instance2.get(2) == instance.get(2) && instance2.get(1) == instance.get(1)) {
                IgawLogger.Logging(context, "IGAW_QA", "Skip DailyPlayCP >> Same day", 3, false);
                saveLastConversionDateTime(context);
                this.isChecking = false;
                return false;
            }
            waitingTime = getPendingConversionKey(context);
            if (waitingTime > 0) {
                if (RequestParameter.getATRequestParameter(context).getConversionCache().contains(Integer.valueOf(waitingTime))) {
                    setPendingConversionKey(context, -1);
                    i = -1;
                } else {
                    i = waitingTime;
                }
                for (RetryCompleteConversion conversionKey : ConversionDAOForRetryCompletion.getDAO(context).getRetryConversions()) {
                    if (conversionKey.getConversionKey() == i) {
                        z = true;
                        break;
                    }
                }
                z = false;
                if (z) {
                    saveLastConversionDateTime(context);
                    IgawLogger.Logging(context, "IGAW_QA", "Skip DailyPlayCP >> Pending CK is on-retry ", 3, false);
                    this.isChecking = false;
                    return false;
                }
                setPendingConversionKey(context, -1);
            }
            this.isChecking = false;
            return true;
        } catch (ParseException e2) {
            Log.e("IGAW_QA", "DailyPlayDAO >> canJoinCampaignToday Error: " + e2.getMessage());
            return false;
        } finally {
            this.isChecking = false;
        }
    }

    public void setLastOnStartSessionDateTime(Context context) {
        Editor editor = getEditor(context);
        editor.putString("lastOnStartSessionTime", sdf.format(new Date()));
        editor.commit();
    }

    public String getLastOnStartSessionDateTime(Context context) {
        return getSharedPreference(context).getString("lastOnStartSessionTime", BuildConfig.FLAVOR);
    }
}
