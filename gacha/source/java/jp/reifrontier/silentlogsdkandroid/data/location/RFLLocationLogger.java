package jp.reifrontier.silentlogsdkandroid.data.location;

import android.location.Location;
import c.a.a;
import com.google.a.f;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import jp.reifrontier.silentlogsdkandroid.RFLEntityTrackPoint;
import jp.reifrontier.silentlogsdkandroid.RFLEntityTrackPoint_Table;
import jp.reifrontier.silentlogsdkandroid.domain.Daily.RFLTrackPoint;
import jp.reifrontier.silentlogsdkandroid.util.RFLDateUtils;
import org.cocos2dx.lib.BuildConfig;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class RFLLocationLogger {

    public class TrackPointSort implements Comparator<RFLTrackPoint> {
        public int compare(RFLTrackPoint rFLTrackPoint, RFLTrackPoint rFLTrackPoint2) {
            return rFLTrackPoint.getTimestamp() - rFLTrackPoint2.getTimestamp();
        }
    }

    public void didUpdateLocation(Location location) {
        if (location.getAccuracy() > 2000.0f) {
            a.b("didUpdateLocation low accuracy:%.2f", Float.valueOf(location.getAccuracy()));
            return;
        }
        saveLocation(new RFLTrackPoint(location)).subscribe(new Subscriber<String>() {
            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void onNext(String str) {
            }
        });
    }

    Observable<String> saveLocation(RFLTrackPoint rFLTrackPoint) {
        return Observable.create(RFLLocationLogger$$Lambda$1.lambdaFactory$(this, rFLTrackPoint)).subscribeOn(Schedulers.io());
    }

    static /* synthetic */ void lambda$saveLocation$0(RFLLocationLogger rFLLocationLogger, RFLTrackPoint rFLTrackPoint, Subscriber subscriber) {
        rFLLocationLogger.saveLogWithTrackPoints(rFLTrackPoint);
        subscriber.onNext(BuildConfig.FLAVOR);
        subscriber.onCompleted();
    }

    public String getLocationEntity(Date date) {
        String id = TimeZone.getDefault().getID();
        RFLEntityTrackPoint entity = getEntity(RFLDateUtils.getQueryDateLong(date.getTime(), id), id);
        ArrayList arrayList = new ArrayList();
        if (entity != null) {
            arrayList.addAll((ArrayList) new f().a(entity.trackPoints, new com.google.a.c.a<List<RFLTrackPoint>>() {
            }.getType()));
        }
        Collections.sort(arrayList, new TrackPointSort());
        if (arrayList.size() <= 0) {
            return null;
        }
        return new f().a((RFLTrackPoint) arrayList.get(arrayList.size() - 1));
    }

    public ArrayList<RFLTrackPoint> getLocationList(Date date) {
        String id = TimeZone.getDefault().getID();
        RFLEntityTrackPoint entity = getEntity(RFLDateUtils.getQueryDateLong(date.getTime(), id), id);
        Object arrayList = new ArrayList();
        if (entity != null) {
            arrayList.addAll((ArrayList) new f().a(entity.trackPoints, new com.google.a.c.a<List<RFLTrackPoint>>() {
            }.getType()));
        }
        Collections.sort(arrayList, new TrackPointSort());
        return arrayList;
    }

    protected void saveLogWithTrackPoints(RFLTrackPoint rFLTrackPoint) {
        String queryDate = RFLDateUtils.getQueryDate((double) rFLTrackPoint.getTimestamp(), rFLTrackPoint.getTimezone());
        RFLEntityTrackPoint entity = getEntity(queryDate, rFLTrackPoint.getTimezone());
        if (entity != null) {
            f fVar = new f();
            Object obj = (ArrayList) fVar.a(entity.trackPoints, new com.google.a.c.a<List<RFLTrackPoint>>() {
            }.getType());
            obj.add(rFLTrackPoint);
            entity.trackPoints = fVar.a(obj);
            entity.update();
            return;
        }
        List arrayList = new ArrayList();
        arrayList.add(rFLTrackPoint);
        insert(queryDate, rFLTrackPoint.getTimezone(), arrayList);
    }

    protected void insert(String str, String str2, List<RFLTrackPoint> list) {
        String a = new f().a((Object) list);
        RFLEntityTrackPoint rFLEntityTrackPoint = new RFLEntityTrackPoint();
        rFLEntityTrackPoint.date = str;
        rFLEntityTrackPoint.timezone = str2;
        rFLEntityTrackPoint.trackPoints = a;
        rFLEntityTrackPoint.submit = false;
        rFLEntityTrackPoint.save();
    }

    protected RFLEntityTrackPoint getEntity(String str, String str2) {
        return (RFLEntityTrackPoint) SQLite.select(new IProperty[0]).from(RFLEntityTrackPoint.class).where(RFLEntityTrackPoint_Table.date.eq(str)).and(RFLEntityTrackPoint_Table.timezone.eq(str2)).querySingle();
    }
}
