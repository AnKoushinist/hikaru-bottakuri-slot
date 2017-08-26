package jp.reifrontier.silentlogsdkandroid.data.daily;

import android.location.Location;
import android.text.format.DateFormat;
import com.google.a.c.a;
import com.google.a.g;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import jp.reifrontier.silentlogsdkandroid.RFLEntityDaily;
import jp.reifrontier.silentlogsdkandroid.RFLEntityDaily_Table;
import jp.reifrontier.silentlogsdkandroid.data.activity.RFLActivityLogger;
import jp.reifrontier.silentlogsdkandroid.data.location.RFLLocationLogger;
import jp.reifrontier.silentlogsdkandroid.domain.Daily.RFLActivity;
import jp.reifrontier.silentlogsdkandroid.domain.Daily.RFLActivity.RFL_ACTIVITY_TYPE;
import jp.reifrontier.silentlogsdkandroid.domain.Daily.RFLDaily;
import jp.reifrontier.silentlogsdkandroid.domain.Daily.RFLSegment;
import jp.reifrontier.silentlogsdkandroid.domain.Daily.RFLTrackPoint;
import jp.reifrontier.silentlogsdkandroid.util.RFLDailyUtils;
import jp.reifrontier.silentlogsdkandroid.util.RFLDateUtils;
import rx.Observable;

public class RFLDailyLoader {
    private String loadDaily(long j) {
        Date date = new Date(j);
        String id = TimeZone.getDefault().getID();
        String queryDateLong = RFLDateUtils.getQueryDateLong(j, id);
        RFLEntityDaily entity = getEntity(queryDateLong, id);
        ArrayList localActivities = getLocalActivities(date);
        String a;
        if (entity != null) {
            Object obj = (RFLDaily) new g().a().b().a(entity.dailies, new a<RFLDaily>() {
            }.getType());
            if (localActivities.size() <= 0) {
                return new g().a().b().a(obj);
            }
            if (obj.getSegments().size() > 0) {
                if (!RFLDailyUtils.isNeedUpdate((double) ((RFLActivity) localActivities.get(localActivities.size() - 1)).getToDate(), (double) obj.getLastActivity().getToDate()).booleanValue()) {
                    return new g().a().b().a(obj);
                }
                a = new g().a().b().a(createDailyFromActivities(localActivities, j));
                entity.dailies = a;
                entity.timestamp = j;
                entity.submit = false;
                entity.update();
                return a;
            }
            a = new g().a().b().a(createDailyFromActivities(localActivities, j));
            entity.dailies = a;
            entity.timestamp = j;
            entity.submit = false;
            entity.update();
            return a;
        } else if (localActivities.size() > 0) {
            r3 = createDailyFromActivities(localActivities, j);
            a = new g().a().b().a(r3);
            insert(queryDateLong, id, a, r3.getDate());
            return a;
        } else {
            r3 = createEmptyDaily(j, id);
            a = new g().a().b().a(r3);
            insert(queryDateLong, id, a, r3.getDate());
            return a;
        }
    }

    private List<RFLActivity> loadRough(long j) {
        return createRoughActivities(getLocalActivities(new Date(j)));
    }

    public Observable<String> getEntity(long j) {
        return Observable.defer(RFLDailyLoader$$Lambda$1.lambdaFactory$(this, j));
    }

    public Observable<List<RFLActivity>> getActivities(long j) {
        return Observable.defer(RFLDailyLoader$$Lambda$2.lambdaFactory$(this, j));
    }

    private RFLDaily createEmptyDaily(long j, String str) {
        RFLDaily rFLDaily = new RFLDaily((int) (j / 1000), str);
        rFLDaily.setSegments(new ArrayList());
        return rFLDaily;
    }

    private ArrayList<RFLActivity> getLocalActivities(Date date) {
        return fillTrackPointsWithRFLActivities(new RFLActivityLogger().getActivityList(date), new RFLLocationLogger().getLocationList(date));
    }

    private ArrayList<RFLActivity> fillTrackPointsWithRFLActivities(ArrayList<RFLActivity> arrayList, List<RFLTrackPoint> list) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            RFLActivity rFLActivity = (RFLActivity) it.next();
            List arrayList2 = new ArrayList();
            for (RFLTrackPoint rFLTrackPoint : list) {
                if (RFLDateUtils.isATimeBetweenBAndC((double) rFLTrackPoint.getTimestamp(), (double) rFLActivity.getFromDate(), (double) rFLActivity.getToDate())) {
                    arrayList2.add(rFLTrackPoint);
                }
            }
            rFLActivity.setTrackPoints(arrayList2);
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            RFLActivity rFLActivity2 = (RFLActivity) it2.next();
            rFLActivity2.setTrackPoints(omitTracker(rFLActivity2.getTrackPoints()));
        }
        return arrayList;
    }

    private List<RFLTrackPoint> omitTracker(List<RFLTrackPoint> list) {
        if (list.size() < 3) {
            return list;
        }
        double speedBetweenAAndB = (RFLDailyUtils.getSpeedBetweenAAndB((RFLTrackPoint) list.get(0), (RFLTrackPoint) list.get(list.size() - 1)) * 3600.0d) / 1000.0d;
        ArrayList arrayList = new ArrayList(list);
        int i = 0;
        while (i + 2 < arrayList.size()) {
            RFLTrackPoint rFLTrackPoint = (RFLTrackPoint) arrayList.get(i);
            RFLTrackPoint rFLTrackPoint2 = (RFLTrackPoint) arrayList.get(i + 1);
            RFLTrackPoint rFLTrackPoint3 = (RFLTrackPoint) arrayList.get(i + 2);
            double speedBetweenAAndB2 = RFLDailyUtils.getSpeedBetweenAAndB(rFLTrackPoint, rFLTrackPoint2);
            double d = (3600.0d * speedBetweenAAndB2) / 1000.0d;
            double speedBetweenAAndB3 = (RFLDailyUtils.getSpeedBetweenAAndB(rFLTrackPoint2, rFLTrackPoint3) * 3600.0d) / 1000.0d;
            if (speedBetweenAAndB2 == 0.0d && rFLTrackPoint.getTimestamp() == rFLTrackPoint2.getTimestamp()) {
                arrayList.remove(rFLTrackPoint2);
            } else {
                speedBetweenAAndB2 = RFLDailyUtils.getAngleBetweenAAndBAndC(rFLTrackPoint, rFLTrackPoint2, rFLTrackPoint3);
                if (speedBetweenAAndB2 > 177.0d) {
                    arrayList.remove(rFLTrackPoint2);
                } else {
                    if (speedBetweenAAndB2 < 50.0d) {
                        if (d > 8.0d && speedBetweenAAndB3 > 8.0d) {
                            arrayList.remove(rFLTrackPoint2);
                        } else if (d > 5.0d * speedBetweenAAndB || speedBetweenAAndB3 > 5.0d * speedBetweenAAndB) {
                            arrayList.remove(rFLTrackPoint2);
                        }
                    } else if (d > 5.0d * speedBetweenAAndB || speedBetweenAAndB3 > 5.0d * speedBetweenAAndB) {
                        arrayList.remove(rFLTrackPoint2);
                    }
                    i++;
                }
            }
        }
        return arrayList;
    }

    private ArrayList<RFLActivity> omitTrackerForActivities(ArrayList<RFLActivity> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            RFLActivity rFLActivity = (RFLActivity) arrayList.get(i);
            rFLActivity.setTrackPoints(omitTrackerBySpeed(rFLActivity.getTrackPoints(), rFLActivity.getActivityType()));
        }
        return arrayList;
    }

    private List<RFLTrackPoint> omitTrackerBySpeed(List<RFLTrackPoint> list, RFL_ACTIVITY_TYPE rfl_activity_type) {
        if (list.size() < 3) {
            return list;
        }
        RFLTrackPoint rFLTrackPoint = (RFLTrackPoint) list.get(0);
        RFLTrackPoint rFLTrackPoint2 = (RFLTrackPoint) list.get(list.size() - 1);
        double speedBetweenAAndB = (RFLDailyUtils.getSpeedBetweenAAndB(rFLTrackPoint, rFLTrackPoint2) * 3600.0d) / 1000.0d;
        double d = speedBetweenAAndB / 5000.0d;
        double d2 = 5.0d * speedBetweenAAndB;
        if (speedBetweenAAndB < 6.0d && rfl_activity_type == RFL_ACTIVITY_TYPE.WALK) {
            speedBetweenAAndB = 4.0d / 5000.0d;
            d = 9.0d;
        } else if (speedBetweenAAndB >= 6.0d || rfl_activity_type != RFL_ACTIVITY_TYPE.TRANSPORT) {
            speedBetweenAAndB = d;
            d = d2;
        } else {
            speedBetweenAAndB = 50.0d / 5000.0d;
            d = 200.0d;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            RFLTrackPoint rFLTrackPoint3;
            RFLTrackPoint rFLTrackPoint4;
            RFLTrackPoint rFLTrackPoint5 = (RFLTrackPoint) list.get(i);
            if (i != 0) {
                rFLTrackPoint3 = (RFLTrackPoint) list.get(i - 1);
            } else {
                rFLTrackPoint3 = null;
            }
            if (i != list.size() - 1) {
                rFLTrackPoint4 = (RFLTrackPoint) list.get(i + 1);
            } else {
                rFLTrackPoint4 = null;
            }
            double speedBetweenAAndB2;
            if (rFLTrackPoint3 != null && rFLTrackPoint5 != null && rFLTrackPoint4 != null) {
                speedBetweenAAndB2 = RFLDailyUtils.getSpeedBetweenAAndB(rFLTrackPoint3, rFLTrackPoint5);
                speedBetweenAAndB2 = (speedBetweenAAndB2 * 3600.0d) / 1000.0d;
                double speedBetweenAAndB3 = (RFLDailyUtils.getSpeedBetweenAAndB(rFLTrackPoint5, rFLTrackPoint4) * 3600.0d) / 1000.0d;
                double speedBetweenAAndB4 = (RFLDailyUtils.getSpeedBetweenAAndB(rFLTrackPoint3, rFLTrackPoint4) * 3600.0d) / 1000.0d;
                if (speedBetweenAAndB2 > speedBetweenAAndB && speedBetweenAAndB3 > speedBetweenAAndB && speedBetweenAAndB4 > speedBetweenAAndB && speedBetweenAAndB2 < 1000.0d && speedBetweenAAndB3 < 1000.0d && speedBetweenAAndB2 < r6 && speedBetweenAAndB3 < r6) {
                    arrayList.add(rFLTrackPoint5);
                }
            } else if (rFLTrackPoint3 != null && rFLTrackPoint5 != null) {
                speedBetweenAAndB2 = (RFLDailyUtils.getSpeedBetweenAAndB(rFLTrackPoint3, rFLTrackPoint5) * 3600.0d) / 1000.0d;
                if (speedBetweenAAndB2 > speedBetweenAAndB && speedBetweenAAndB2 < 1000.0d && speedBetweenAAndB2 < r6) {
                    arrayList.add(rFLTrackPoint5);
                }
            } else if (!(rFLTrackPoint3 != null || rFLTrackPoint5 == null || rFLTrackPoint4 == null)) {
                speedBetweenAAndB2 = (RFLDailyUtils.getSpeedBetweenAAndB(rFLTrackPoint5, rFLTrackPoint4) * 3600.0d) / 1000.0d;
                if (speedBetweenAAndB2 > speedBetweenAAndB && speedBetweenAAndB2 < 1000.0d && speedBetweenAAndB2 < r6) {
                    arrayList.add(rFLTrackPoint5);
                }
            }
        }
        if (arrayList.size() == 0) {
            arrayList.add(rFLTrackPoint);
            arrayList.add(rFLTrackPoint2);
        }
        return arrayList;
    }

    private RFLDaily createDailyFromActivities(ArrayList<RFLActivity> arrayList, long j) {
        ArrayList omitStayTrackPoint = omitStayTrackPoint(filterByCompress(filterBySpeed(omitTrackerForActivities(filterByCompress(filterByInvalidMoveContinuous(filterByCompress(filterBySpeed(omitTrackerForActivities(filterByCompress(filterByInvalidStayContinuous(filterByCompress(filterBySpeed(omitTrackerForActivities(filterByCompress(filterBySpeed(arrayList))))))))))))))));
        List arrayList2 = new ArrayList();
        Iterator it = omitStayTrackPoint.iterator();
        while (it.hasNext()) {
            RFLActivity rFLActivity = (RFLActivity) it.next();
            List arrayList3 = new ArrayList();
            arrayList3.add(rFLActivity);
            arrayList2.add(new RFLSegment(arrayList3));
        }
        RFLDaily rFLDaily = new RFLDaily((int) (j / 1000), TimeZone.getDefault().getID());
        rFLDaily.setSegments(arrayList2);
        return rFLDaily;
    }

    protected ArrayList<RFLActivity> filterByNoTrackPoint(ArrayList<RFLActivity> arrayList) {
        ArrayList<RFLActivity> arrayList2 = new ArrayList();
        RFLActivity rFLActivity = null;
        int i = 0;
        while (i < arrayList.size()) {
            RFLActivity rFLActivity2 = (RFLActivity) arrayList.get(i);
            if (rFLActivity == null) {
                arrayList2.add(rFLActivity2);
            } else if (rFLActivity2.getTrackPoints().size() == 0) {
                rFLActivity.setToDate(rFLActivity2.getToDate());
                rFLActivity.setSteps(rFLActivity2.getSteps() + rFLActivity.getSteps());
                rFLActivity.setTrackPoints(new ArrayList(rFLActivity.getTrackPoints()));
                rFLActivity2 = rFLActivity;
            } else {
                arrayList2.add(rFLActivity2);
            }
            i++;
            rFLActivity = rFLActivity2;
        }
        return arrayList2;
    }

    protected ArrayList<RFLActivity> filterByCompress(ArrayList<RFLActivity> arrayList) {
        ArrayList<RFLActivity> arrayList2 = new ArrayList();
        RFLActivity rFLActivity = null;
        int i = 0;
        while (i < arrayList.size()) {
            RFLActivity rFLActivity2 = (RFLActivity) arrayList.get(i);
            if (rFLActivity == null) {
                arrayList2.add(rFLActivity2);
            } else if (rFLActivity2.getActivityType() == rFLActivity.getActivityType()) {
                rFLActivity.setToDate(rFLActivity2.getToDate());
                rFLActivity.setSteps(rFLActivity.getSteps() + rFLActivity2.getSteps());
                List arrayList3 = new ArrayList(rFLActivity.getTrackPoints());
                arrayList3.addAll(rFLActivity2.getTrackPoints());
                rFLActivity.setTrackPoints(arrayList3);
                rFLActivity2 = rFLActivity;
            } else {
                arrayList2.add(rFLActivity2);
            }
            i++;
            rFLActivity = rFLActivity2;
        }
        return arrayList2;
    }

    protected ArrayList<RFLActivity> filterBySpeed(ArrayList<RFLActivity> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            RFLActivity rFLActivity;
            RFLActivity rFLActivity2;
            RFLActivity rFLActivity3 = (RFLActivity) arrayList.get(i);
            if (i != 0) {
                rFLActivity = (RFLActivity) arrayList.get(i - 1);
            } else {
                rFLActivity = null;
            }
            if (i != arrayList.size() - 1) {
                rFLActivity2 = (RFLActivity) arrayList.get(i + 1);
            } else {
                rFLActivity2 = null;
            }
            double toDate = (double) (rFLActivity3.getToDate() - rFLActivity3.getFromDate());
            if (toDate > 10.0d) {
                int distance = rFLActivity3.getDistance();
                double d = ((double) distance) / toDate;
                double d2 = (3600.0d * d) / 1000.0d;
                double steps = (double) rFLActivity3.getSteps();
                double d3 = 0.0d;
                if (steps > 0.0d) {
                    d3 = ((double) distance) / steps;
                }
                if (d < 0.05d) {
                    rFLActivity3.setActivityType(RFL_ACTIVITY_TYPE.STAY);
                } else if (d < 0.3d) {
                    if (steps > 0.3d * toDate) {
                        rFLActivity3.setActivityType(RFL_ACTIVITY_TYPE.WALK);
                    } else {
                        rFLActivity3.setActivityType(RFL_ACTIVITY_TYPE.STAY);
                    }
                } else if (rFLActivity3.getActivityType() == RFL_ACTIVITY_TYPE.WALK) {
                    if (d2 > 7.0d) {
                        rFLActivity3.setActivityType(RFL_ACTIVITY_TYPE.TRANSPORT);
                    }
                } else if (rFLActivity3.getActivityType() == RFL_ACTIVITY_TYPE.TRANSPORT) {
                    if (rFLActivity != null && rFLActivity.getTrackPoints().size() > 0 && rFLActivity2 != null && rFLActivity2.getTrackPoints().size() > 0) {
                        RFLTrackPoint rFLTrackPoint = (RFLTrackPoint) rFLActivity.getTrackPoints().get(0);
                        RFLTrackPoint rFLTrackPoint2 = (RFLTrackPoint) rFLActivity2.getTrackPoints().get(rFLActivity2.getTrackPoints().size() - 1);
                        d3 = (RFLDailyUtils.getSpeedBetweenAAndB(rFLTrackPoint, rFLTrackPoint2) * 3600.0d) / 1000.0d;
                        double tpDistanceBetweenAAndB = RFLDailyUtils.getTpDistanceBetweenAAndB(rFLTrackPoint, rFLTrackPoint2) / (((double) rFLActivity2.getSteps()) + (((double) rFLActivity.getSteps()) + steps));
                        if (d3 < 7.0d) {
                            if (d2 > d3) {
                                if (d2 < 7.0d) {
                                    rFLActivity3.setActivityType(RFL_ACTIVITY_TYPE.WALK);
                                } else if (d2 < 10.0d && tpDistanceBetweenAAndB < 4.0d) {
                                    rFLActivity3.setActivityType(RFL_ACTIVITY_TYPE.WALK);
                                }
                            } else if (d2 < 4.0d) {
                                rFLActivity3.setActivityType(RFL_ACTIVITY_TYPE.WALK);
                            } else if (tpDistanceBetweenAAndB < 3.0d) {
                                rFLActivity3.setActivityType(RFL_ACTIVITY_TYPE.WALK);
                            }
                        }
                    } else if (d3 > 0.0d && d2 < 6.0d) {
                        rFLActivity3.setActivityType(RFL_ACTIVITY_TYPE.WALK);
                    }
                } else if (d3 > 0.0d) {
                    if (d > 0.0d && d < 3.2d) {
                        rFLActivity3.setActivityType(RFL_ACTIVITY_TYPE.WALK);
                    } else if (d > 3.2d) {
                        rFLActivity3.setActivityType(RFL_ACTIVITY_TYPE.TRANSPORT);
                    }
                } else if (d >= 2.5d) {
                    rFLActivity3.setActivityType(RFL_ACTIVITY_TYPE.TRANSPORT);
                }
            }
        }
        return arrayList;
    }

    protected ArrayList<RFLActivity> filterByActivityConnection(ArrayList<RFLActivity> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            RFLActivity rFLActivity;
            RFLActivity rFLActivity2;
            RFLActivity rFLActivity3 = (RFLActivity) arrayList.get(i);
            if (i != 0) {
                rFLActivity = (RFLActivity) arrayList.get(i - 1);
            } else {
                rFLActivity = null;
            }
            if (i != arrayList.size() - 1) {
                rFLActivity2 = (RFLActivity) arrayList.get(i + 1);
            } else {
                rFLActivity2 = null;
            }
            if (rFLActivity3 != null && rFLActivity3.getTrackPoints().size() > 0 && rFLActivity != null && rFLActivity.getTrackPoints().size() > 0 && rFLActivity2 != null && rFLActivity2.getTrackPoints().size() > 0) {
                RFLTrackPoint rFLTrackPoint = (RFLTrackPoint) rFLActivity.getTrackPoints().get(0);
                RFLTrackPoint rFLTrackPoint2 = (RFLTrackPoint) rFLActivity.getTrackPoints().get(rFLActivity.getTrackPoints().size() - 1);
                RFLTrackPoint rFLTrackPoint3 = (RFLTrackPoint) rFLActivity3.getTrackPoints().get(0);
                RFLTrackPoint rFLTrackPoint4 = (RFLTrackPoint) rFLActivity2.getTrackPoints().get(0);
                RFLTrackPoint rFLTrackPoint5 = (RFLTrackPoint) rFLActivity2.getTrackPoints().get(rFLActivity2.getTrackPoints().size() - 1);
                double tpDistanceBetweenAAndB = RFLDailyUtils.getTpDistanceBetweenAAndB((RFLTrackPoint) rFLActivity3.getTrackPoints().get(rFLActivity3.getTrackPoints().size() - 1), rFLTrackPoint4);
                if (tpDistanceBetweenAAndB > 10000.0d) {
                    rFLActivity.setSteps(rFLActivity.getSteps() + rFLActivity3.getSteps());
                    rFLActivity.setToDate(rFLActivity3.getToDate());
                    arrayList.remove(rFLActivity3);
                } else if (rFLActivity3.getActivityType() == RFL_ACTIVITY_TYPE.STAY) {
                    if ((RFLDailyUtils.getTpDistanceBetweenAAndB(rFLTrackPoint2, rFLTrackPoint3) + tpDistanceBetweenAAndB) / 2.0d > 10.0d * RFLDailyUtils.getTpDistanceBetweenAAndB(rFLTrackPoint, rFLTrackPoint5)) {
                        rFLActivity.setSteps(rFLActivity.getSteps() + rFLActivity3.getSteps());
                        rFLActivity.setToDate(rFLActivity3.getToDate());
                        arrayList.remove(rFLActivity3);
                    }
                }
            }
        }
        return arrayList;
    }

    private ArrayList<RFLActivity> filterByInvalidStayContinuous(ArrayList<RFLActivity> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            int size;
            RFLActivity rFLActivity;
            RFLActivity rFLActivity2;
            RFLActivity rFLActivity3 = (RFLActivity) arrayList.get(i);
            if (i != 0) {
                RFLActivity rFLActivity4 = (RFLActivity) arrayList.get(i - 1);
                size = rFLActivity4.getTrackPoints().size();
                rFLActivity = size == 0 ? null : rFLActivity4;
            } else {
                size = 0;
                rFLActivity = null;
            }
            if (i != arrayList.size() - 1) {
                rFLActivity4 = (RFLActivity) arrayList.get(i + 1);
                rFLActivity2 = rFLActivity4.getTrackPoints().size() == 0 ? null : rFLActivity4;
            } else {
                rFLActivity2 = null;
            }
            if (!(rFLActivity == null || rFLActivity2 == null || !rFLActivity3.getActivityType().equals(RFL_ACTIVITY_TYPE.STAY))) {
                int toDate = rFLActivity3.getToDate() - rFLActivity3.getFromDate();
                if (RFLDailyUtils.getTpDistanceBetweenAAndB((RFLTrackPoint) rFLActivity.getTrackPoints().get(size - 1), (RFLTrackPoint) rFLActivity2.getTrackPoints().get(0)) > 300.0d) {
                    rFLActivity3.setActivityType(rFLActivity2.getActivityType());
                }
            }
        }
        return arrayList;
    }

    private ArrayList<RFLActivity> filterByInvalidMoveContinuous(ArrayList<RFLActivity> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            int size;
            RFLActivity rFLActivity;
            RFLActivity rFLActivity2;
            RFLActivity rFLActivity3 = (RFLActivity) arrayList.get(i);
            if (i != 0) {
                RFLActivity rFLActivity4 = (RFLActivity) arrayList.get(i - 1);
                size = rFLActivity4.getTrackPoints().size();
                rFLActivity = size == 0 ? null : rFLActivity4;
            } else {
                size = 0;
                rFLActivity = null;
            }
            if (i != arrayList.size() - 1) {
                rFLActivity4 = (RFLActivity) arrayList.get(i + 1);
                rFLActivity2 = rFLActivity4.getTrackPoints().size() == 0 ? null : rFLActivity4;
            } else {
                rFLActivity2 = null;
            }
            int size2 = rFLActivity3.getTrackPoints().size();
            if (!(rFLActivity == null || rFLActivity2 == null || rFLActivity3.getActivityType().equals(RFL_ACTIVITY_TYPE.STAY))) {
                RFLTrackPoint rFLTrackPoint = (RFLTrackPoint) rFLActivity.getTrackPoints().get(size - 1);
                RFLTrackPoint rFLTrackPoint2 = (RFLTrackPoint) rFLActivity3.getTrackPoints().get(0);
                if (RFLDailyUtils.getTpDistanceBetweenAAndB(rFLTrackPoint, rFLTrackPoint2) > RFLDailyUtils.getTpDistanceBetweenAAndB((RFLTrackPoint) rFLActivity3.getTrackPoints().get(size2 - 1), (RFLTrackPoint) rFLActivity2.getTrackPoints().get(0))) {
                    List arrayList2 = new ArrayList();
                    arrayList2.addAll(rFLActivity.getTrackPoints());
                    arrayList2.add(rFLTrackPoint2);
                    rFLActivity.setTrackPoints(arrayList2);
                }
            }
        }
        return arrayList;
    }

    protected ArrayList<RFLActivity> omitStayTrackPoint(ArrayList<RFLActivity> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            RFLActivity rFLActivity;
            RFLActivity rFLActivity2 = (RFLActivity) arrayList.get(i);
            if (i != 0) {
                rFLActivity = (RFLActivity) arrayList.get(i - 1);
                if (rFLActivity.getTrackPoints().size() == 0) {
                    rFLActivity = null;
                }
            } else {
                rFLActivity = null;
            }
            List arrayList2;
            if (rFLActivity2.getActivityType().equals(RFL_ACTIVITY_TYPE.STAY)) {
                arrayList2 = new ArrayList();
                arrayList2.add((RFLTrackPoint) rFLActivity2.getTrackPoints().get(0));
                rFLActivity2.setTrackPoints(arrayList2);
            } else if (rFLActivity != null && rFLActivity.getActivityType().equals(RFL_ACTIVITY_TYPE.STAY)) {
                RFLTrackPoint rFLTrackPoint = (RFLTrackPoint) rFLActivity.getTrackPoints().get(0);
                if (RFLDailyUtils.getTpDistanceBetweenAAndB(rFLTrackPoint, (RFLTrackPoint) rFLActivity2.getTrackPoints().get(0)) < 500.0d) {
                    arrayList2 = new ArrayList();
                    arrayList2.add(rFLTrackPoint);
                    arrayList2.addAll(rFLActivity2.getTrackPoints());
                    rFLActivity2.setTrackPoints(arrayList2);
                }
            }
        }
        return arrayList;
    }

    private List<RFLActivity> createRoughActivities(ArrayList<RFLActivity> arrayList) {
        return filterByCompress(filterBySpeed(arrayList));
    }

    protected void insert(String str, String str2, String str3, int i) {
        RFLEntityDaily rFLEntityDaily = new RFLEntityDaily();
        rFLEntityDaily.date = str;
        rFLEntityDaily.timezone = str2;
        rFLEntityDaily.dailies = str3;
        rFLEntityDaily.submit = false;
        rFLEntityDaily.timestamp = ((long) i) * 1000;
        rFLEntityDaily.save();
    }

    protected RFLEntityDaily getEntity(String str, String str2) {
        return (RFLEntityDaily) SQLite.select(new IProperty[0]).from(RFLEntityDaily.class).where(RFLEntityDaily_Table.date.eq(str)).and(RFLEntityDaily_Table.timezone.eq(str2)).querySingle();
    }

    protected void logActPrimitive(List<RFLActivity> list) {
        c.a.a.a("----------Log Primitive Activity------------", new Object[0]);
        for (RFLActivity rFLActivity : list) {
            Date date = new Date(((long) rFLActivity.getFromDate()) * 1000);
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            String str = (String) DateFormat.format("MM-dd HH:mm", instance);
            instance.setTime(new Date(((long) rFLActivity.getToDate()) * 1000));
            String str2 = (String) DateFormat.format("MM-dd HH:mm", instance);
            c.a.a.a("primitive from:%s to:%s", str, str2);
        }
    }

    protected void logAct(List<RFLActivity> list, boolean z) {
        c.a.a.a("----------Log Activity------------", new Object[0]);
        for (RFLActivity rFLActivity : list) {
            double distance = ((((double) rFLActivity.getDistance()) / ((double) (rFLActivity.getToDate() - rFLActivity.getFromDate()))) * 3600.0d) / 1000.0d;
            Date date = new Date(((long) rFLActivity.getFromDate()) * 1000);
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            String str = (String) DateFormat.format("MM-dd HH:mm", instance);
            instance.setTime(new Date(((long) rFLActivity.getToDate()) * 1000));
            String str2 = (String) DateFormat.format("HH:mm", instance);
            double distance2 = ((double) rFLActivity.getDistance()) / ((double) rFLActivity.getSteps());
            c.a.a.a("master from:%s - %s steps:%d step dis:%d m 1\u6b69:%.2f m time:%.0f \u6642\u901f:%.2f km/h tp:%d type:%s", str, str2, Integer.valueOf(rFLActivity.getSteps()), Integer.valueOf(rFLActivity.getDistance()), Double.valueOf(distance2), Double.valueOf(r4), Double.valueOf(distance), Integer.valueOf(rFLActivity.getTrackPoints().size()), rFLActivity.getActivityString());
            if (z) {
                for (RFLTrackPoint rFLTrackPoint : rFLActivity.getTrackPoints()) {
                    Calendar instance2 = Calendar.getInstance();
                    instance2.setTime(new Date(((long) rFLTrackPoint.getTimestamp()) * 1000));
                    str = (String) DateFormat.format("MM-dd HH:mm", instance2);
                    c.a.a.a("master latLng:%f,%f time:%s ac:%f", Double.valueOf(rFLTrackPoint.getLatitude()), Double.valueOf(rFLTrackPoint.getLongitude()), str, Float.valueOf(rFLTrackPoint.getAccuracy()));
                }
            }
        }
    }

    private ArrayList<RFLActivity> omitStayTrackPointWithContinuousLocations(List<RFLActivity> list) {
        ArrayList<RFLActivity> arrayList = new ArrayList(list);
        for (int i = 0; i < arrayList.size(); i++) {
            List arrayList2 = new ArrayList();
            RFLActivity rFLActivity = (RFLActivity) arrayList.get(i);
            if (rFLActivity.getActivityType() == RFL_ACTIVITY_TYPE.STAY) {
                RFLActivity rFLActivity2;
                RFLActivity rFLActivity3;
                RFLTrackPoint rFLTrackPoint;
                RFLTrackPoint rFLTrackPoint2;
                List<RFLTrackPoint> trackPoints = rFLActivity.getTrackPoints();
                if (i != 0) {
                    rFLActivity2 = (RFLActivity) arrayList.get(i - 1);
                } else {
                    rFLActivity2 = null;
                }
                if (i != arrayList.size() - 1) {
                    rFLActivity3 = (RFLActivity) arrayList.get(i + 1);
                } else {
                    rFLActivity3 = null;
                }
                if (rFLActivity2 == null || rFLActivity3 == null) {
                    if (rFLActivity2 != null) {
                        if (rFLActivity2.getTrackPoints().size() > 0) {
                            rFLTrackPoint = null;
                            rFLTrackPoint2 = (RFLTrackPoint) rFLActivity2.getTrackPoints().get(rFLActivity2.getTrackPoints().size() - 1);
                        }
                    } else if (rFLActivity3 != null && rFLActivity3.getTrackPoints().size() > 0) {
                        rFLTrackPoint = (RFLTrackPoint) rFLActivity3.getTrackPoints().get(0);
                        rFLTrackPoint2 = null;
                    }
                    rFLTrackPoint = null;
                    rFLTrackPoint2 = null;
                } else {
                    if (rFLActivity2.getTrackPoints().size() > 0) {
                        rFLTrackPoint = (RFLTrackPoint) rFLActivity2.getTrackPoints().get(rFLActivity2.getTrackPoints().size() - 1);
                    } else {
                        rFLTrackPoint = null;
                    }
                    if (rFLActivity3.getTrackPoints().size() > 0) {
                        rFLTrackPoint2 = rFLTrackPoint;
                        rFLTrackPoint = (RFLTrackPoint) rFLActivity3.getTrackPoints().get(0);
                    } else {
                        rFLTrackPoint2 = rFLTrackPoint;
                        rFLTrackPoint = null;
                    }
                }
                for (RFLTrackPoint rFLTrackPoint3 : trackPoints) {
                    if (isMatchForLocations(rFLTrackPoint2, rFLTrackPoint, rFLTrackPoint3)) {
                        arrayList2.add(rFLTrackPoint3);
                        break;
                    }
                }
                if (arrayList2.size() == 0) {
                    arrayList2.add(trackPoints.get(0));
                }
                rFLActivity.setTrackPoints(arrayList2);
            }
        }
        return arrayList;
    }

    private boolean isMatchForLocations(RFLTrackPoint rFLTrackPoint, RFLTrackPoint rFLTrackPoint2, RFLTrackPoint rFLTrackPoint3) {
        Location location = new Location("silentlog_android");
        location.setLatitude(rFLTrackPoint3.getLatitude());
        location.setLongitude(rFLTrackPoint3.getLongitude());
        Location location2;
        if (rFLTrackPoint != null && rFLTrackPoint2 != null) {
            location2 = new Location("silentlog_android");
            location2.setLatitude(rFLTrackPoint.getLatitude());
            location2.setLongitude(rFLTrackPoint.getLongitude());
            Location location3 = new Location("silentlog_android");
            location3.setLatitude(rFLTrackPoint2.getLatitude());
            location3.setLongitude(rFLTrackPoint2.getLongitude());
            float distanceTo = location2.distanceTo(location3) * 2.0f;
            if (location.distanceTo(location2) < distanceTo || location.distanceTo(location3) < distanceTo) {
                return true;
            }
        } else if (rFLTrackPoint != null) {
            location2 = new Location("silentlog_android");
            location2.setLatitude(rFLTrackPoint.getLatitude());
            location2.setLongitude(rFLTrackPoint.getLongitude());
            if (location.distanceTo(location2) >= 1000.0f) {
                return false;
            }
            return true;
        } else if (rFLTrackPoint2 != null) {
            location2 = new Location("silentlog_android");
            location2.setLatitude(rFLTrackPoint2.getLatitude());
            location2.setLongitude(rFLTrackPoint2.getLongitude());
            if (location.distanceTo(location2) >= 1000.0f) {
                return false;
            }
            return true;
        }
        return false;
    }
}
