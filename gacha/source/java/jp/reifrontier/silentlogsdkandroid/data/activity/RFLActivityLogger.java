package jp.reifrontier.silentlogsdkandroid.data.activity;

import com.google.a.c.a;
import com.google.a.f;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import jp.reifrontier.silentlogsdkandroid.RFLEntityActivity;
import jp.reifrontier.silentlogsdkandroid.RFLEntityActivity_Table;
import jp.reifrontier.silentlogsdkandroid.RFLEntityPedometer;
import jp.reifrontier.silentlogsdkandroid.RFLEntityPedometer_Table;
import jp.reifrontier.silentlogsdkandroid.RFLEntityWalker;
import jp.reifrontier.silentlogsdkandroid.domain.Daily.RFLActivity;
import jp.reifrontier.silentlogsdkandroid.domain.Daily.RFLActivity.RFL_ACTIVITY_TYPE;
import jp.reifrontier.silentlogsdkandroid.domain.Daily.RFLRawActivity;
import jp.reifrontier.silentlogsdkandroid.domain.Daily.RFLRawActivity.RFL_RAW_ACTIVITY_TYPE;
import jp.reifrontier.silentlogsdkandroid.util.RFLDateUtils;
import org.cocos2dx.lib.BuildConfig;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import twitter4j.TwitterResponse;

public class RFLActivityLogger {

    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$jp$reifrontier$silentlogsdkandroid$domain$Daily$RFLRawActivity$RFL_RAW_ACTIVITY_TYPE = new int[RFL_RAW_ACTIVITY_TYPE.values().length];

        static {
            try {
                $SwitchMap$jp$reifrontier$silentlogsdkandroid$domain$Daily$RFLRawActivity$RFL_RAW_ACTIVITY_TYPE[RFL_RAW_ACTIVITY_TYPE.STAY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$jp$reifrontier$silentlogsdkandroid$domain$Daily$RFLRawActivity$RFL_RAW_ACTIVITY_TYPE[RFL_RAW_ACTIVITY_TYPE.WALK.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$jp$reifrontier$silentlogsdkandroid$domain$Daily$RFLRawActivity$RFL_RAW_ACTIVITY_TYPE[RFL_RAW_ACTIVITY_TYPE.TRANSPORT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public class ActivitySort implements Comparator<RFLRawActivity> {
        public int compare(RFLRawActivity rFLRawActivity, RFLRawActivity rFLRawActivity2) {
            return (int) (rFLRawActivity.getTimestamp() - rFLRawActivity2.getTimestamp());
        }
    }

    Observable<String> savePedometer(long j) {
        return Observable.create(RFLActivityLogger$$Lambda$1.lambdaFactory$(this, j)).subscribeOn(Schedulers.io());
    }

    static /* synthetic */ void lambda$savePedometer$0(RFLActivityLogger rFLActivityLogger, long j, Subscriber subscriber) {
        rFLActivityLogger.insertPedometer(j);
        subscriber.onNext(BuildConfig.FLAVOR);
        subscriber.onCompleted();
    }

    public void didUpdatePedometer(long j) {
        savePedometer(j).subscribe(new Subscriber<String>() {
            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void onNext(String str) {
            }
        });
    }

    Observable<String> saveActivity(RFLRawActivity rFLRawActivity) {
        return Observable.create(RFLActivityLogger$$Lambda$2.lambdaFactory$(this, rFLRawActivity)).subscribeOn(Schedulers.io());
    }

    static /* synthetic */ void lambda$saveActivity$1(RFLActivityLogger rFLActivityLogger, RFLRawActivity rFLRawActivity, Subscriber subscriber) {
        rFLActivityLogger.saveLogWithActivity(rFLRawActivity);
        subscriber.onNext(BuildConfig.FLAVOR);
        subscriber.onCompleted();
    }

    public void didUpdateActivity(RFLRawActivity rFLRawActivity) {
        saveActivity(rFLRawActivity).subscribe(new Subscriber<String>() {
            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void onNext(String str) {
            }
        });
    }

    public ArrayList<RFLActivity> getActivityList(Date date) {
        String id = TimeZone.getDefault().getID();
        RFLEntityActivity entityActivity = getEntityActivity(RFLDateUtils.getQueryDateLong(date.getTime(), id), id);
        ArrayList<RFLActivity> arrayList = new ArrayList();
        if (entityActivity != null) {
            arrayList.addAll(convertActivities((ArrayList) new f().a(entityActivity.detectedActivitites, new a<List<RFLRawActivity>>() {
            }.getType())));
        }
        return arrayList;
    }

    private void saveLogWithActivity(RFLRawActivity rFLRawActivity) {
        String queryDateLong = RFLDateUtils.getQueryDateLong(rFLRawActivity.getTimestamp(), rFLRawActivity.getTimezone());
        RFLEntityActivity entityActivity = getEntityActivity(queryDateLong, rFLRawActivity.getTimezone());
        if (entityActivity != null) {
            f fVar = new f();
            Object obj = (ArrayList) fVar.a(entityActivity.detectedActivitites, new a<List<RFLRawActivity>>() {
            }.getType());
            obj.add(rFLRawActivity);
            entityActivity.detectedActivitites = fVar.a(obj);
            entityActivity.update();
            return;
        }
        List arrayList = new ArrayList();
        arrayList.add(rFLRawActivity);
        insertActivity(queryDateLong, rFLRawActivity.getTimezone(), arrayList);
    }

    private RFLEntityActivity getEntityActivity(String str, String str2) {
        return (RFLEntityActivity) SQLite.select(new IProperty[0]).from(RFLEntityActivity.class).where(RFLEntityActivity_Table.date.eq(str)).and(RFLEntityActivity_Table.timezone.eq(str2)).querySingle();
    }

    protected void insertActivity(String str, String str2, List<RFLRawActivity> list) {
        String a = new f().a((Object) list);
        RFLEntityActivity rFLEntityActivity = new RFLEntityActivity();
        rFLEntityActivity.date = str;
        rFLEntityActivity.timezone = str2;
        rFLEntityActivity.detectedActivitites = a;
        rFLEntityActivity.submit = false;
        rFLEntityActivity.save();
    }

    public int getStep(long j, long j2) {
        return SQLite.select(new IProperty[0]).from(RFLEntityPedometer.class).where(RFLEntityPedometer_Table.timestamp.between(j).and(Long.valueOf(j2))).queryList().size();
    }

    private void insertWalker(int i) {
        c.a.a.a("insertWalker step:%d", Integer.valueOf(i));
        RFLEntityWalker rFLEntityWalker = new RFLEntityWalker();
        rFLEntityWalker.stepCount = i;
        rFLEntityWalker.timestamp = System.currentTimeMillis();
        rFLEntityWalker.save();
    }

    private void insertPedometer(long j) {
        RFLEntityPedometer rFLEntityPedometer = new RFLEntityPedometer();
        rFLEntityPedometer.timestamp = j;
        rFLEntityPedometer.save();
    }

    private List<RFLActivity> convertActivities(ArrayList<RFLRawActivity> arrayList) {
        int i;
        Collections.sort(arrayList, new ActivitySort());
        RFLRawActivity rFLRawActivity = (RFLRawActivity) arrayList.get(0);
        double floor = Math.floor((double) (RFLDateUtils.getDayStartLong(rFLRawActivity.getTimestamp(), rFLRawActivity.getTimezone()) / 1000));
        double floor2 = Math.floor(((double) RFLDateUtils.getDayEnd(rFLRawActivity.getTimestamp(), rFLRawActivity.getTimezone())) / 1000.0d);
        if (RFLDateUtils.isADayIsToday(rFLRawActivity.getTimestamp())) {
            floor2 = Math.floor(((double) System.currentTimeMillis()) / 1000.0d);
        }
        ArrayList arrayList2 = new ArrayList();
        for (i = 0; i < arrayList.size(); i++) {
            Object rFLActivity;
            rFLRawActivity = (RFLRawActivity) arrayList.get(i);
            double floor3 = Math.floor(((double) rFLRawActivity.getTimestamp()) / 1000.0d);
            if (i == 0) {
                floor3 = floor;
            }
            String timezone = rFLRawActivity.getTimezone();
            switch (AnonymousClass5.$SwitchMap$jp$reifrontier$silentlogsdkandroid$domain$Daily$RFLRawActivity$RFL_RAW_ACTIVITY_TYPE[rFLRawActivity.getType().ordinal()]) {
                case TwitterResponse.READ /*1*/:
                    rFLActivity = new RFLActivity((int) floor3, timezone, RFL_ACTIVITY_TYPE.STAY);
                    break;
                case TwitterResponse.READ_WRITE /*2*/:
                    rFLActivity = new RFLActivity((int) floor3, timezone, RFL_ACTIVITY_TYPE.WALK);
                    break;
                case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                    rFLActivity = new RFLActivity((int) floor3, timezone, RFL_ACTIVITY_TYPE.TRANSPORT);
                    break;
                default:
                    rFLActivity = null;
                    break;
            }
            if (rFLActivity != null) {
                arrayList2.add(rFLActivity);
            }
        }
        List arrayList3 = new ArrayList();
        RFLActivity rFLActivity2 = null;
        int i2 = 0;
        while (i2 < arrayList2.size()) {
            RFLActivity rFLActivity3 = (RFLActivity) arrayList2.get(i2);
            if (rFLActivity2 == null) {
                arrayList3.add(rFLActivity3);
            } else if (rFLActivity3.getActivityType() == rFLActivity2.getActivityType() || rFLActivity3.getFromDate() - rFLActivity2.getFromDate() <= 70) {
                rFLActivity3 = rFLActivity2;
            } else {
                arrayList3.add(rFLActivity3);
            }
            i2++;
            rFLActivity2 = rFLActivity3;
        }
        for (i2 = 0; i2 < arrayList3.size(); i2++) {
            rFLActivity3 = (RFLActivity) arrayList3.get(i2);
            i = (int) floor2;
            if (i2 != arrayList3.size() - 1) {
                i = ((RFLActivity) arrayList3.get(i2 + 1)).getFromDate();
            }
            rFLActivity3.setToDate(i);
        }
        Iterator it = arrayList3.iterator();
        while (it.hasNext()) {
            rFLActivity3 = (RFLActivity) it.next();
            rFLActivity3.setSteps(getStep(RFLDateUtils.getLocalDate((double) rFLActivity3.getFromDate(), rFLActivity3.getTimeZone()), RFLDateUtils.getLocalDate((double) rFLActivity3.getToDate(), rFLActivity3.getTimeZone())));
        }
        return arrayList3;
    }
}
