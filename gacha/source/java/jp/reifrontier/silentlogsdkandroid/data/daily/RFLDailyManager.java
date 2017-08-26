package jp.reifrontier.silentlogsdkandroid.data.daily;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import c.a.a;
import com.google.a.f;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.b;
import com.google.android.gms.common.api.GoogleApiClient.c;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.h;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.g;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import jp.co.vaz.creator.hikaru.R;
import jp.reifrontier.silentlogsdkandroid.RFLEntityDaily;
import jp.reifrontier.silentlogsdkandroid.RFLEntityDaily_Table;
import jp.reifrontier.silentlogsdkandroid.RFLEntityTag;
import jp.reifrontier.silentlogsdkandroid.RFLEntityTag_Table;
import jp.reifrontier.silentlogsdkandroid.data.activity.RFLActivityLogger;
import jp.reifrontier.silentlogsdkandroid.data.api.RFLAPIManager;
import jp.reifrontier.silentlogsdkandroid.data.location.RFLLocationLogger;
import jp.reifrontier.silentlogsdkandroid.domain.Daily.RFLActivity;
import jp.reifrontier.silentlogsdkandroid.domain.Daily.RFLActivity.RFL_ACTIVITY_TYPE;
import jp.reifrontier.silentlogsdkandroid.domain.Daily.RFLDaily;
import jp.reifrontier.silentlogsdkandroid.domain.Daily.RFLDailyDisplayData;
import jp.reifrontier.silentlogsdkandroid.domain.Daily.RFLRawActivity;
import jp.reifrontier.silentlogsdkandroid.domain.Daily.RFLRawActivity.RFL_RAW_ACTIVITY_TYPE;
import jp.reifrontier.silentlogsdkandroid.domain.Daily.RFLSegment;
import jp.reifrontier.silentlogsdkandroid.domain.Daily.RFLTag;
import jp.reifrontier.silentlogsdkandroid.domain.Daily.RFLTrackPoint;
import jp.reifrontier.silentlogsdkandroid.domain.Event.RFLEvent;
import jp.reifrontier.silentlogsdkandroid.domain.Event.RFLEventResponse;
import jp.reifrontier.silentlogsdkandroid.domain.Event.RFLNotifiedEvent;
import jp.reifrontier.silentlogsdkandroid.domain.Event.RFLNotifiedPoi;
import jp.reifrontier.silentlogsdkandroid.domain.Event.RFLPoi;
import jp.reifrontier.silentlogsdkandroid.domain.Event.RFLTiming;
import jp.reifrontier.silentlogsdkandroid.domain.Event.RFLTiming.RFL_TIMING;
import jp.reifrontier.silentlogsdkandroid.domain.Response.RFLResponse.RFLResponseError;
import jp.reifrontier.silentlogsdkandroid.domain.Response.RFLResponse.RFLResponseSuccess;
import jp.reifrontier.silentlogsdkandroid.util.RFLConstants;
import jp.reifrontier.silentlogsdkandroid.util.RFLDailyUtils;
import jp.reifrontier.silentlogsdkandroid.util.RFLDateUtils;
import jp.reifrontier.silentlogsdkandroid.util.RFLUtils;
import jp.reifrontier.silentlogsdkandroid.util.RxBus;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import twitter4j.TwitterResponse;

public class RFLDailyManager implements SensorEventListener, b, c, h<Status>, g {
    static final float ALPHA = 0.9f;
    static final float SI2G = 0.1019f;
    private ArrayList<float[]> _accelometers = new ArrayList();
    private boolean _bStepFlag;
    private long _nStepTimestamp = 0;
    private RFLEvent acquiredEvent = null;
    private RFLNotifiedPoi acquiredPoi = null;
    private int activeModeCount = 0;
    private Subscription activeModeSubscription = null;
    private long activeModeTime;
    private Subscription activitySubscription = null;
    private CompositeSubscription apiSubscriptions = null;
    private ArrayList<Integer> cancelEventList = null;
    private Location currentLocation;
    private Subscription dailyFactorySubscription = null;
    private ArrayList<RFLDailyDisplayData> displayDataList = null;
    private ArrayList<RFLEntityDaily> entityDailyList = null;
    private ArrayList<RFLEvent> eventSettingList = null;
    private CompositeSubscription eventSubscriptions = null;
    private RxBus getEventBus = null;
    private float[] gravity = new float[3];
    private long installDate;
    private boolean isActiveMode = false;
    private boolean isGyroActivated = false;
    private boolean isHighSpeed = false;
    private boolean isInMonitorActivity = false;
    private boolean isInPlace = false;
    private boolean isInRegion = false;
    private boolean isInitEvent = false;
    private boolean isPauseUpdate = true;
    private boolean isRestartUpdate = false;
    private boolean isSettingEvent = false;
    private boolean isStartedTimer = false;
    private Location lastLocationForEstimation;
    private Location lastPauseLocation;
    private ActivityDetectionBroadcastReceiver mBroadcastReceiver;
    private Context mContext = null;
    private GoogleApiClient mGoogleApiClient = null;
    private boolean mIsScreenOn = true;
    private float[] mLastDiff = new float[6];
    private float[] mLastDirections = new float[6];
    private float[][] mLastExtremes = new float[][]{new float[6], new float[6]};
    private int mLastMatch = -1;
    private float[] mLastValues = new float[6];
    private float mLimit = 10.0f;
    private RFLDailyManagerListener mListener;
    private LocationListener mLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            RFLDailyManager.this.rflLocationChanged(location);
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
        }

        public void onProviderEnabled(String str) {
        }

        public void onProviderDisabled(String str) {
        }
    };
    private LocationRequest mLocationRequest;
    private float[] mScale = new float[2];
    private float mYOffset;
    private Subscription midnightUploadSubscription = null;
    private Subscription monitorActivitySubscription = null;
    private int nearCount = 0;
    private boolean noAccelerometer = false;
    private long noCalcTime;
    private boolean noGyro = false;
    private double pastDistance = 0.0d;
    private ArrayList<Location> pauseLocList = null;
    private long pauseTime;
    private RxBus postAllRxBus = null;
    private RxBus postDailyRxBus = null;
    private ArrayList<RFLNotifiedPoi> registerPoiList = null;
    private RxBus responseBus = null;
    private CompositeSubscription responseSubscriptions = null;
    private RFLActivityLogger rflActivityLogger = null;
    private RFLAPIManager rflApiManager = null;
    private RFLLocationLogger rflLocationLogger = null;
    private BroadcastReceiver screenStatusReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
                RFLDailyManager.this.mIsScreenOn = false;
            }
            if (intent.getAction().equals("android.intent.action.SCREEN_ON")) {
                RFLDailyManager.this.mIsScreenOn = true;
            }
        }
    };
    private Sensor sensorAccelerometer = null;
    private Sensor sensorGyro = null;
    private SensorManager sensorManager = null;
    private SharedPreferences sharedPreferences = null;
    private int stepCount = 0;
    private CompositeSubscription uploadAllSubscriptions = null;

    public interface RFLDailyManagerListener {
        void endBackgroundLoadDaily(String str);

        void googleApiConnectionFailed(ConnectionResult connectionResult);

        void notifyDetectDangerDrivingMessage(String str);

        void notifyEventAcquired(String str, Integer num);
    }

    public class ActivityDetectionBroadcastReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            if (ActivityRecognitionResult.a(intent)) {
                ArrayList arrayList = (ArrayList) ActivityRecognitionResult.b(intent).a();
                if (RFLDailyManager.this.rflActivityLogger != null) {
                    RFLDailyManager.this.rflActivityLogger.didUpdateActivity(new RFLRawActivity(new Date().getTime(), TimeZone.getDefault().getID(), arrayList));
                }
            }
        }
    }

    public RFLDailyManager(Context context, RFLDailyManagerListener rFLDailyManagerListener) {
        this.mContext = context;
        this.sharedPreferences = context.getSharedPreferences(String.format(Locale.JAPAN, "%s%s", new Object[]{RFLConstants.PREF_NAME, context.getPackageName()}), 0);
        this.mListener = rFLDailyManagerListener;
        this.rflLocationLogger = new RFLLocationLogger();
        this.rflActivityLogger = new RFLActivityLogger();
        this.rflApiManager = new RFLAPIManager(context);
        this.mYOffset = ((float) 480) * 0.5f;
        this.mScale[0] = -((((float) 480) * 0.5f) * 0.05098581f);
        this.mScale[1] = -((((float) 480) * 0.5f) * 0.016666668f);
        this.sensorManager = (SensorManager) context.getSystemService("sensor");
        if (this.sharedPreferences.getBoolean(RFLConstants.KEY_FOR_NO_ACCELLO, false)) {
            this.noAccelerometer = true;
        } else if (this.sensorManager.getSensorList(1).size() > 0) {
            this.sensorAccelerometer = this.sensorManager.getDefaultSensor(1);
        } else {
            this.sharedPreferences.edit().putBoolean(RFLConstants.KEY_FOR_NO_ACCELLO, true).apply();
            this.noAccelerometer = true;
        }
        if (this.sharedPreferences.getBoolean(RFLConstants.KEY_FOR_NO_GYRO, false)) {
            this.noGyro = true;
        } else if (this.sensorManager.getSensorList(4).size() > 0) {
            this.sensorGyro = this.sensorManager.getDefaultSensor(4);
        } else {
            this.sharedPreferences.edit().putBoolean(RFLConstants.KEY_FOR_NO_GYRO, true).apply();
            this.noGyro = true;
        }
        if (this.sensorAccelerometer != null) {
            this.sensorManager.registerListener(this, this.sensorAccelerometer, 2);
        }
        if (this.sensorGyro != null) {
            this.sensorManager.registerListener(this, this.sensorGyro, 1);
        }
        if (this.noGyro && this.noAccelerometer && isAuthenticated().booleanValue()) {
            a.b("No Sensor", new Object[0]);
        }
        this.installDate = this.sharedPreferences.getLong(RFLConstants.KEY_FOR_INSTALL_DATE, RFLDateUtils.getDayStartLong(new Date().getTime(), null));
        createDailyDisplayDataList();
        this.isPauseUpdate = true;
        this.pauseLocList = new ArrayList();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        this.mContext.registerReceiver(this.screenStatusReceiver, intentFilter);
        buildGoogleApiClient(context);
    }

    public void onStart() {
        if (!isAuthenticated().booleanValue()) {
            return;
        }
        if (this.mGoogleApiClient.isConnected() || this.mGoogleApiClient.isConnecting()) {
            restartTracking();
        } else {
            connectClient();
        }
    }

    public void onResume() {
        if (isAuthenticated().booleanValue() && isTrackingOn()) {
            this.isStartedTimer = false;
            stopDailyFactoryTimer();
            stopMidnightUploadTimer();
        }
    }

    public boolean onRestart() {
        if (RFLDateUtils.isADayIsToday(((RFLDailyDisplayData) this.displayDataList.get(this.displayDataList.size() - 1)).getTimestamp())) {
            return false;
        }
        createDailyDisplayDataList();
        return true;
    }

    public void onStop() {
        if (isAuthenticated().booleanValue() && isTrackingOn() && !this.isStartedTimer) {
            this.isStartedTimer = true;
            startDailyFactoryTimer();
            startMidnightUploadTimer();
        }
    }

    public boolean isGoogleApiConnected() {
        return this.mGoogleApiClient.isConnected();
    }

    public void createAccount(RxBus rxBus, String str, String str2, String str3, String str4, String str5, Boolean bool) {
        this.rflApiManager.createAccount(rxBus, str, str2, str3, str4, str5, bool);
    }

    public void getProfile(RxBus rxBus) {
        this.rflApiManager.getProfile(rxBus);
    }

    public void auth(RxBus rxBus) {
        this.rflApiManager.authAccount(rxBus);
    }

    public void restartTracking() {
        this.mGoogleApiClient.disconnect();
        buildGoogleApiClient(this.mContext);
        this.sharedPreferences.edit().putBoolean(RFLConstants.KEY_FOR_START_UODATES, true).apply();
        connectClient();
    }

    public void stopTracking() {
        this.sharedPreferences.edit().putBoolean(RFLConstants.KEY_FOR_START_UODATES, false).apply();
        if (this.mGoogleApiClient.isConnected()) {
            pauseLocation();
        }
    }

    public void checkDailyTimer() {
        if (((long) ((int) (System.currentTimeMillis() - this.sharedPreferences.getLong(RFLConstants.KEY_FOR_LOAD_DAILY_TIME, 0)))) > 7200000) {
            loadDailyFromBackground();
            this.isStartedTimer = true;
            startDailyFactoryTimer();
            startMidnightUploadTimer();
        }
    }

    public int getDisplayDataListSize() {
        return this.displayDataList.size();
    }

    public void postDaily(RxBus rxBus, String str, String str2, boolean z) {
        RFLEntityDaily entity = getEntity(str, str2);
        if (!z) {
            z = entity.submit;
        }
        if (z) {
            this.rflApiManager.postDaily(rxBus, str, str2, RFLDateUtils.getGMTAPIDateLong(entity.timestamp, RFLConstants.kRFLDateTimeFormat_API_Param_Date), addTagListToDaily(entity));
        }
    }

    public void updateEntity(String str, String str2) {
        update(str, str2);
    }

    public Observable<String> loadDailyFromDate(long j) {
        long round = (long) Math.round((float) (((j - this.installDate) / 1000) / RFLConstants.kRFLOneDaySeconds));
        if (((long) this.displayDataList.size()) > round) {
            return Observable.defer(RFLDailyManager$$Lambda$2.lambdaFactory$(this, (RFLDailyDisplayData) this.displayDataList.get((int) round)));
        }
        a.b("RFLDailyManager loadDailyFromDate: Invalid request date:%s", new Date(j));
        if (!RFLDateUtils.isADayIsToday(j)) {
            return null;
        }
        createDailyDisplayDataList();
        return Observable.defer(RFLDailyManager$$Lambda$1.lambdaFactory$(this, (RFLDailyDisplayData) this.displayDataList.get(this.displayDataList.size() - 1)));
    }

    private void createDailyDisplayDataList() {
        this.displayDataList = new ArrayList();
        long dayStartLong = RFLDateUtils.getDayStartLong(new Date().getTime(), null);
        long j = this.installDate;
        int round = ((int) ((long) Math.round((float) (((dayStartLong - j) / 1000) / RFLConstants.kRFLOneDaySeconds)))) + 1;
        for (int i = 0; i < round; i++) {
            long j2 = (RFLConstants.kRFLOneDayMillSeconds * ((long) i)) + j;
            RFLDailyDisplayData rFLDailyDisplayData = new RFLDailyDisplayData();
            rFLDailyDisplayData.setTimestamp(j2);
            this.displayDataList.add(rFLDailyDisplayData);
        }
    }

    private synchronized void buildGoogleApiClient(Context context) {
        this.mGoogleApiClient = new GoogleApiClient.a(context).a(this).a(this).a(com.google.android.gms.location.h.a).a(com.google.android.gms.location.a.a).b();
    }

    private void connectClient() {
        this.mGoogleApiClient.connect();
    }

    private LocationRequest buildLocationRequest(boolean z) {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.a(RFLConstants.UPDATE_INTERVAL_IN_MILLISECONDS);
        locationRequest.b(RFLConstants.GPS_TIME);
        if (z) {
            locationRequest.a(100);
        } else {
            locationRequest.a(R.styleable.AppCompatTheme_buttonStyle);
        }
        return locationRequest;
    }

    private void startUpdates() {
        a.a("startUpdates priority BALANCED", new Object[0]);
        this.pauseLocList = new ArrayList();
        this.isPauseUpdate = false;
        this.isRestartUpdate = false;
        this.mLocationRequest = buildLocationRequest(false);
        startLocationUpdates();
        if (!this.isActiveMode) {
            ordinalActivityUpdate();
        }
        if (((long) ((int) (System.currentTimeMillis() - this.sharedPreferences.getLong(RFLConstants.KEY_FOR_LOAD_DAILY_TIME, 0)))) > 7200000) {
            loadDailyFromBackground();
        }
    }

    private void startLocationUpdates() {
        if (android.support.v4.app.a.a(this.mContext, "android.permission.ACCESS_FINE_LOCATION") == 0 || android.support.v4.app.a.a(this.mContext, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            com.google.android.gms.location.h.b.requestLocationUpdates(this.mGoogleApiClient, this.mLocationRequest, this);
        } else {
            a.b("Unable Start Location Updates", new Object[0]);
        }
    }

    private void stopLocationUpdates() {
        com.google.android.gms.location.h.b.removeLocationUpdates(this.mGoogleApiClient, this);
    }

    protected void startRflLocationManager() {
    }

    protected void stopRflLocationManager() {
    }

    private void startActivityUpdates(long j) {
        this.mBroadcastReceiver = new ActivityDetectionBroadcastReceiver();
        this.mContext.registerReceiver(this.mBroadcastReceiver, new IntentFilter(RFLConstants.ACTION_ACTIVITY_DETECTED));
        com.google.android.gms.location.a.b.requestActivityUpdates(this.mGoogleApiClient, j, getActivityDetectionPendingIntent()).setResultCallback(this);
    }

    private void activeActivityUpdate() {
        if (!this.noGyro || !this.noAccelerometer) {
            if (this.sharedPreferences.getBoolean(RFLConstants.KEY_FOR_NO_GYRO, true)) {
                a.b("This Device do not have Gyroscope", new Object[0]);
                startActivityEstimation(RFLConstants.DETECTION_INTERVAL_ACTIVE_MODE);
            } else if (isGoogleApiConnected()) {
                com.google.android.gms.location.a.b.removeActivityUpdates(this.mGoogleApiClient, getActivityDetectionPendingIntent());
                startActivityUpdates(RFLConstants.DETECTION_INTERVAL_ACTIVE_MODE);
            }
        }
    }

    private void ordinalActivityUpdate() {
        if (!this.noGyro || !this.noAccelerometer) {
            if (this.sharedPreferences.getBoolean(RFLConstants.KEY_FOR_NO_GYRO, true)) {
                a.b("This Device do not have Gyroscope", new Object[0]);
                startActivityEstimation(RFLConstants.DETECTION_INTERVAL_NO_ACTIVE_MODE);
                return;
            }
            com.google.android.gms.location.a.b.removeActivityUpdates(this.mGoogleApiClient, getActivityDetectionPendingIntent());
            startActivityUpdates(RFLConstants.DETECTION_INTERVAL_NO_ACTIVE_MODE);
        }
    }

    private void reduceActivityUpdate() {
        if (!this.noGyro || !this.noAccelerometer) {
            if (this.sharedPreferences.getBoolean(RFLConstants.KEY_FOR_NO_GYRO, true)) {
                stopActivityEstimation();
                return;
            }
            com.google.android.gms.location.a.b.removeActivityUpdates(this.mGoogleApiClient, getActivityDetectionPendingIntent());
            startActivityUpdates(RFLConstants.DETECTION_INTERVAL_PAUSE_MODE);
        }
    }

    private void startActivityEstimation(long j) {
        a.a("startUpdates with No Gyro", new Object[0]);
        stopActivityEstimation();
        this.activitySubscription = Observable.interval(j, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(RFLDailyManager$$Lambda$3.lambdaFactory$(this));
    }

    private void stopActivityEstimation() {
        if (this.activitySubscription != null && !this.activitySubscription.isUnsubscribed()) {
            this.activitySubscription.unsubscribe();
            this.activitySubscription = null;
        }
    }

    private void restartUpdates() {
        pauseLocation();
        a.a("restartUpdates priority HIGH", new Object[0]);
        this.pauseLocList = new ArrayList();
        this.isPauseUpdate = false;
        this.mLocationRequest = buildLocationRequest(true);
        startLocationUpdates();
        ordinalActivityUpdate();
        this.isRestartUpdate = true;
        Observable.timer(180000, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(RFLDailyManager$$Lambda$4.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$restartUpdates$3(RFLDailyManager rFLDailyManager, Long l) {
        if (rFLDailyManager.isRestartUpdate) {
            rFLDailyManager.pauseLocation();
            rFLDailyManager.startUpdates();
        }
    }

    private void pauseLocationUpdates(boolean z) {
        if (z) {
            pauseLocation();
        } else if (System.currentTimeMillis() - this.pauseTime > 300000) {
            pauseLocation();
        } else {
            this.nearCount = 0;
        }
    }

    private void pauseLocation() {
        a.a("Location Updates Pause", new Object[0]);
        this.pauseTime = System.currentTimeMillis();
        this.isPauseUpdate = true;
        this.isRestartUpdate = false;
        this.nearCount = 0;
        this.pastDistance = 0.0d;
        this.lastPauseLocation = null;
        this.pauseLocList = new ArrayList();
        stopLocationUpdates();
        reduceActivityUpdate();
        leftRegion();
    }

    public String loadLocation() {
        if (this.currentLocation != null) {
            a.a("RFLDailyManager current lat:%f lng:%f", Double.valueOf(this.currentLocation.getLatitude()), Double.valueOf(this.currentLocation.getLongitude()));
            return new f().a(new RFLTrackPoint(this.currentLocation));
        }
        a.b("RFLDailyManager current location is null.", new Object[0]);
        return null;
    }

    private Observable<String> loadDailyFromDB(RFLDailyDisplayData rFLDailyDisplayData) {
        return new RFLDailyLoader().getEntity(rFLDailyDisplayData.getTimestamp());
    }

    private void startMidnightUploadTimer() {
        observeMidnightBus();
        long time = RFLDateUtils.randomMidnight().getTime();
        long time2 = new Date().getTime();
        if (!RFLDateUtils.isATimeAfterB(time, time2)) {
            time += RFLConstants.kRFLOneDayMillSeconds;
        }
        time -= time2;
        prepareUploadList();
        if (this.entityDailyList.size() > 0) {
            this.midnightUploadSubscription = Observable.timer(time, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(RFLDailyManager$$Lambda$5.lambdaFactory$(this));
        }
    }

    private void observeMidnightBus() {
        if (this.midnightUploadSubscription != null && !this.midnightUploadSubscription.isUnsubscribed()) {
            this.midnightUploadSubscription.unsubscribe();
            this.midnightUploadSubscription = null;
        }
    }

    private void stopMidnightUploadTimer() {
        if (this.postAllRxBus != null) {
            this.postAllRxBus = null;
        }
        if (!(this.midnightUploadSubscription == null || this.midnightUploadSubscription.isUnsubscribed())) {
            this.midnightUploadSubscription.unsubscribe();
            this.midnightUploadSubscription = null;
        }
        if (this.uploadAllSubscriptions != null && !this.uploadAllSubscriptions.isUnsubscribed()) {
            this.uploadAllSubscriptions.unsubscribe();
            this.uploadAllSubscriptions = null;
        }
    }

    private void startDailyFactoryTimer() {
        a.a("startDailyFactoryTimer", new Object[0]);
        observeFactoryBus();
        this.dailyFactorySubscription = Observable.interval(2, TimeUnit.HOURS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(RFLDailyManager$$Lambda$6.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$startDailyFactoryTimer$5(RFLDailyManager rFLDailyManager, Long l) {
        if (rFLDailyManager.isPauseUpdate && rFLDailyManager.isTrackingOn()) {
            if (rFLDailyManager.mGoogleApiClient == null || !rFLDailyManager.mGoogleApiClient.isConnected()) {
                rFLDailyManager.restartTracking();
            } else {
                rFLDailyManager.startUpdates();
            }
        }
        rFLDailyManager.loadDailyFromBackground();
    }

    private void observeFactoryBus() {
        if (this.dailyFactorySubscription != null && !this.dailyFactorySubscription.isUnsubscribed()) {
            this.dailyFactorySubscription.unsubscribe();
            this.dailyFactorySubscription = null;
        }
    }

    private void observePostDailyBus() {
        if (this.postDailyRxBus != null) {
            this.postDailyRxBus = null;
        }
        this.postDailyRxBus = new RxBus();
        if (!(this.apiSubscriptions == null || this.apiSubscriptions.isUnsubscribed())) {
            this.apiSubscriptions.unsubscribe();
            this.apiSubscriptions = null;
        }
        this.apiSubscriptions = new CompositeSubscription();
        ConnectableObservable publish = this.postDailyRxBus.toObserverable().publish();
        this.apiSubscriptions.add(publish.subscribe(RFLDailyManager$$Lambda$7.lambdaFactory$(this)));
        this.apiSubscriptions.add(publish.connect());
    }

    static /* synthetic */ void lambda$observePostDailyBus$6(RFLDailyManager rFLDailyManager, Object obj) {
        if (obj instanceof RFLResponseError) {
            a.b("error request " + ((RFLResponseError) obj).getError(), new Object[0]);
        } else if (obj instanceof RFLResponseSuccess) {
            a.a("post Daily Done:", new Object[0]);
            RFLResponseSuccess rFLResponseSuccess = (RFLResponseSuccess) obj;
            rFLDailyManager.update(rFLResponseSuccess.getDate(), rFLResponseSuccess.getTimezone());
        }
    }

    private void stopDailyFactoryTimer() {
        if (this.postDailyRxBus != null) {
            this.postDailyRxBus = null;
        }
        if (!(this.apiSubscriptions == null || this.apiSubscriptions.isUnsubscribed())) {
            this.apiSubscriptions.unsubscribe();
            this.apiSubscriptions = null;
        }
        if (this.dailyFactorySubscription != null && !this.dailyFactorySubscription.isUnsubscribed()) {
            this.dailyFactorySubscription.unsubscribe();
            this.dailyFactorySubscription = null;
        }
    }

    private void loadDailyFromBackground() {
        if (isConnectedApi().booleanValue()) {
            observePostDailyBus();
            loadLastRflDisplayDate();
            this.sharedPreferences.edit().putLong(RFLConstants.KEY_FOR_LOAD_DAILY_TIME, new Date().getTime()).apply();
        }
    }

    private void loadLastRflDisplayDate() {
        RFLDailyDisplayData rFLDailyDisplayData = (RFLDailyDisplayData) this.displayDataList.get(this.displayDataList.size() - 1);
        a.a("loadDailyFromBackground:%s", RFLDateUtils.getQueryDateLong(rFLDailyDisplayData.getTimestamp(), null));
        loadDailyFromDB(rFLDailyDisplayData).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(RFLDailyManager$$Lambda$8.lambdaFactory$(this, rFLDailyDisplayData.getTimestamp()), RFLDailyManager$$Lambda$9.lambdaFactory$(), RFLDailyManager$$Lambda$10.lambdaFactory$());
        if (!RFLDateUtils.getQueryDateLong(new Date().getTime(), null).equals(RFLDateUtils.getQueryDateLong(rFLDailyDisplayData.getTimestamp(), null))) {
            Observable.timer(30000, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(RFLDailyManager$$Lambda$11.lambdaFactory$(this));
        }
    }

    static /* synthetic */ void lambda$loadLastRflDisplayDate$7(RFLDailyManager rFLDailyManager, long j, String str) {
        String queryDateLong = RFLDateUtils.getQueryDateLong(j, null);
        String id = TimeZone.getDefault().getID();
        RFLEntityDaily entity = rFLDailyManager.getEntity(queryDateLong, id);
        if (!entity.submit) {
            rFLDailyManager.rflApiManager.postDaily(rFLDailyManager.postDailyRxBus, queryDateLong, id, RFLDateUtils.getGMTAPIDateLong(entity.timestamp, RFLConstants.kRFLDateTimeFormat_API_Param_Date), rFLDailyManager.addTagListToDaily(entity));
        }
        if (rFLDailyManager.mListener != null) {
            rFLDailyManager.mListener.endBackgroundLoadDaily(entity.dailies);
        }
    }

    static /* synthetic */ void lambda$loadLastRflDisplayDate$8(Throwable th) {
    }

    static /* synthetic */ void lambda$loadLastRflDisplayDate$9() {
    }

    private void loadLatestRflDisplayData() {
        createDailyDisplayDataList();
        RFLDailyDisplayData rFLDailyDisplayData = (RFLDailyDisplayData) this.displayDataList.get(this.displayDataList.size() - 1);
        a.a("loadLatestDailyFromBackground:%s", RFLDateUtils.getQueryDateLong(rFLDailyDisplayData.getTimestamp(), null));
        loadDailyFromDB(rFLDailyDisplayData).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(RFLDailyManager$$Lambda$12.lambdaFactory$(this, rFLDailyDisplayData.getTimestamp()), RFLDailyManager$$Lambda$13.lambdaFactory$(), RFLDailyManager$$Lambda$14.lambdaFactory$());
    }

    static /* synthetic */ void lambda$loadLatestRflDisplayData$11(RFLDailyManager rFLDailyManager, long j, String str) {
        String queryDateLong = RFLDateUtils.getQueryDateLong(j, null);
        String id = TimeZone.getDefault().getID();
        RFLEntityDaily entity = rFLDailyManager.getEntity(queryDateLong, id);
        if (!entity.submit) {
            rFLDailyManager.rflApiManager.postDaily(rFLDailyManager.postDailyRxBus, queryDateLong, id, RFLDateUtils.getGMTAPIDateLong(entity.timestamp, RFLConstants.kRFLDateTimeFormat_API_Param_Date), rFLDailyManager.addTagListToDaily(entity));
        }
        if (rFLDailyManager.mListener != null) {
            rFLDailyManager.mListener.endBackgroundLoadDaily(entity.dailies);
        }
    }

    static /* synthetic */ void lambda$loadLatestRflDisplayData$12(Throwable th) {
    }

    static /* synthetic */ void lambda$loadLatestRflDisplayData$13() {
    }

    protected void prepareUploadList() {
        this.entityDailyList = new ArrayList(getNoSubmitEntities());
    }

    protected void uploadAllDaily() {
        if (this.postAllRxBus != null) {
            this.postAllRxBus = null;
        }
        this.postAllRxBus = new RxBus();
        if (!(this.uploadAllSubscriptions == null || this.uploadAllSubscriptions.isUnsubscribed())) {
            this.uploadAllSubscriptions.unsubscribe();
            this.uploadAllSubscriptions = null;
        }
        this.uploadAllSubscriptions = new CompositeSubscription();
        ConnectableObservable publish = this.postAllRxBus.toObserverable().publish();
        this.uploadAllSubscriptions.add(publish.subscribe(RFLDailyManager$$Lambda$15.lambdaFactory$(this)));
        this.uploadAllSubscriptions.add(publish.connect());
        postDailyMacro(this.postAllRxBus);
    }

    static /* synthetic */ void lambda$uploadAllDaily$14(RFLDailyManager rFLDailyManager, Object obj) {
        if (obj instanceof RFLResponseError) {
            a.b("error request:%s", ((RFLResponseError) obj).getError());
        } else if (obj instanceof RFLResponseSuccess) {
            a.a("post Daily Done:", new Object[0]);
            RFLResponseSuccess rFLResponseSuccess = (RFLResponseSuccess) obj;
            rFLDailyManager.update(rFLResponseSuccess.getDate(), rFLResponseSuccess.getTimezone());
        }
        rFLDailyManager.entityDailyList.remove(0);
        rFLDailyManager.postDailyMacro(rFLDailyManager.postAllRxBus);
    }

    protected void postDailyMacro(RxBus rxBus) {
        if (this.entityDailyList.size() > 0) {
            RFLEntityDaily rFLEntityDaily = (RFLEntityDaily) this.entityDailyList.get(0);
            String gMTAPIDateLong = RFLDateUtils.getGMTAPIDateLong(rFLEntityDaily.timestamp, RFLConstants.kRFLDateTimeFormat_API_Param_Date);
            String addTagListToDaily = addTagListToDaily(rFLEntityDaily);
            this.rflApiManager.postDaily(rxBus, rFLEntityDaily.date, rFLEntityDaily.timezone, gMTAPIDateLong, addTagListToDaily);
            return;
        }
        a.a("upload all daily done", new Object[0]);
        if (this.postAllRxBus != null) {
            this.postAllRxBus = null;
        }
        if (!(this.midnightUploadSubscription == null || this.midnightUploadSubscription.isUnsubscribed())) {
            this.midnightUploadSubscription.unsubscribe();
            this.midnightUploadSubscription = null;
        }
        if (this.uploadAllSubscriptions != null && !this.uploadAllSubscriptions.isUnsubscribed()) {
            this.uploadAllSubscriptions.unsubscribe();
            this.uploadAllSubscriptions = null;
        }
    }

    protected RFLEntityDaily getEntity(String str, String str2) {
        return (RFLEntityDaily) SQLite.select(new IProperty[0]).from(RFLEntityDaily.class).where(RFLEntityDaily_Table.date.eq(str)).and(RFLEntityDaily_Table.timezone.eq(str2)).querySingle();
    }

    public List<RFLEntityDaily> getNoSubmitEntities() {
        return SQLite.select(new IProperty[0]).from(RFLEntityDaily.class).where(RFLEntityDaily_Table.submit.eq(Boolean.valueOf(false))).queryList();
    }

    public List<RFLEntityDaily> getAllEntities() {
        return SQLite.select(new IProperty[0]).from(RFLEntityDaily.class).orderBy(RFLEntityDaily_Table.timestamp, false).queryList();
    }

    protected void update(String str, String str2) {
        RFLEntityDaily entity = getEntity(str, str2);
        entity.submit = true;
        entity.update();
    }

    public String addTagListToDaily(RFLEntityDaily rFLEntityDaily) {
        if (getDailyTagSize(new Date(rFLEntityDaily.timestamp)) <= 0) {
            return rFLEntityDaily.dailies;
        }
        Object obj = (RFLDaily) new f().a(rFLEntityDaily.dailies, new com.google.a.c.a<RFLDaily>() {
        }.getType());
        for (RFLSegment activities : obj.getSegments()) {
            for (RFLActivity rFLActivity : activities.getActivities()) {
                List arrayList = new ArrayList();
                for (RFLEntityTag rFLEntityTag : getActivityTagList(rFLActivity.getFromDate())) {
                    arrayList.add(new RFLTag(rFLEntityTag.name, "custom", "json"));
                }
                rFLActivity.setTagList(arrayList);
            }
        }
        return new com.google.a.g().a().b().a(obj);
    }

    public long getDailyTagSize(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        long time = instance.getTime().getTime();
        instance.set(11, 23);
        instance.set(12, 59);
        instance.set(13, 59);
        long time2 = instance.getTime().getTime();
        return SQLite.select(new IProperty[0]).from(RFLEntityTag.class).where(RFLEntityTag_Table.fromDate.between(time).and(Long.valueOf(time2))).count();
    }

    private List<RFLEntityTag> getActivityTagList(int i) {
        long j = ((long) i) * 1000;
        return SQLite.select(new IProperty[0]).from(RFLEntityTag.class).where(RFLEntityTag_Table.fromDate.eq(j)).queryList();
    }

    private Boolean isAuthenticated() {
        if (this.sharedPreferences.getBoolean(RFLConstants.KEY_FOR_AUTH, false)) {
            return Boolean.valueOf(true);
        }
        if (android.support.v4.app.a.a(this.mContext, "android.permission.ACCESS_FINE_LOCATION") == 0 && android.support.v4.app.a.a(this.mContext, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            this.sharedPreferences.edit().putBoolean(RFLConstants.KEY_FOR_AUTH, true).apply();
            return Boolean.valueOf(true);
        }
        a.b("RFLDailyManager not Authenticated", new Object[0]);
        return Boolean.valueOf(false);
    }

    private Boolean isConnectedApi() {
        return Boolean.valueOf(!this.sharedPreferences.getString(RFLConstants.KEY_FOR_ACCESS_TOKEN, BuildConfig.FLAVOR).equals(BuildConfig.FLAVOR));
    }

    private boolean isTrackingOn() {
        return this.sharedPreferences.getBoolean(RFLConstants.KEY_FOR_START_UODATES, true);
    }

    public void onConnected(Bundle bundle) {
        if (this.mGoogleApiClient.isConnected()) {
            startUpdates();
        }
    }

    public void onConnectionSuspended(int i) {
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (this.mListener != null) {
            this.mListener.googleApiConnectionFailed(connectionResult);
        }
    }

    public void onResult(Status status) {
    }

    private PendingIntent getActivityDetectionPendingIntent() {
        return PendingIntent.getBroadcast(this.mContext, 0, new Intent(RFLConstants.ACTION_ACTIVITY_DETECTED), 134217728);
    }

    public void onLocationChanged(Location location) {
        rflLocationChanged(location);
    }

    private void rflLocationChanged(Location location) {
        if (!isTrackingOn()) {
            this.currentLocation = location;
            pauseLocation();
        } else if (this.rflLocationLogger != null) {
            if (this.lastPauseLocation == null) {
                this.lastPauseLocation = location;
                this.currentLocation = location;
                this.rflLocationLogger.didUpdateLocation(location);
                this.pauseLocList.add(location);
            } else {
                this.rflLocationLogger.didUpdateLocation(location);
                this.currentLocation = location;
                this.pauseLocList.add(location);
                if (System.currentTimeMillis() - this.noCalcTime > 600000) {
                    this.noCalcTime = System.currentTimeMillis();
                    this.isHighSpeed = RFLDailyUtils.getSpeedBetweenAAndBLocation(this.lastPauseLocation, location) > 0.8d;
                }
                if (!this.isHighSpeed) {
                    detectPauseLocationUpdate(location);
                }
                this.pastDistance = RFLDailyUtils.getDistanceBetweenAAndB(this.lastPauseLocation, location);
                this.lastPauseLocation = location;
            }
            if (isConnectedApi().booleanValue()) {
                getEvent();
                monitorEvents();
            }
        } else {
            this.rflLocationLogger = new RFLLocationLogger();
            this.currentLocation = location;
        }
    }

    private void detectPauseLocationUpdate(Location location) {
        if (RFLDailyUtils.detectSamePlaceWithLocationAndDistance(this.lastPauseLocation, location, this.pastDistance)) {
            this.nearCount++;
        } else {
            this.nearCount--;
        }
        if (this.nearCount != 10) {
            return;
        }
        if (this.isRestartUpdate) {
            pauseLocationUpdates(true);
        } else if (this.isActiveMode) {
            restartUpdates();
        } else {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.rflActivityLogger.getStep(currentTimeMillis - 300000, currentTimeMillis) > 100) {
                restartUpdates();
            } else {
                pauseLocationUpdates(false);
            }
        }
    }

    protected void didUpdateActivityEstimation() {
        this.rflActivityLogger.didUpdateActivity(new RFLRawActivity(new Date().getTime(), TimeZone.getDefault().getID(), detectActivityType(RFL_RAW_ACTIVITY_TYPE.UNKNOWN)));
    }

    private RFL_RAW_ACTIVITY_TYPE detectActivityType(RFL_RAW_ACTIVITY_TYPE rfl_raw_activity_type) {
        if (this.lastLocationForEstimation == null) {
            this.lastLocationForEstimation = this.currentLocation;
        }
        if (this.isPauseUpdate) {
            return RFL_RAW_ACTIVITY_TYPE.STAY;
        }
        RFL_RAW_ACTIVITY_TYPE rfl_raw_activity_type2;
        double speedBetweenAAndBLocation = RFLDailyUtils.getSpeedBetweenAAndBLocation(this.lastLocationForEstimation, this.currentLocation);
        if (this.isActiveMode) {
            if (speedBetweenAAndBLocation < 0.2d) {
                rfl_raw_activity_type2 = RFL_RAW_ACTIVITY_TYPE.STAY;
            } else if (speedBetweenAAndBLocation < 1.9d) {
                rfl_raw_activity_type2 = RFL_RAW_ACTIVITY_TYPE.WALK;
            } else {
                rfl_raw_activity_type2 = RFL_RAW_ACTIVITY_TYPE.TRANSPORT;
            }
        } else if (speedBetweenAAndBLocation < 0.5d) {
            rfl_raw_activity_type2 = RFL_RAW_ACTIVITY_TYPE.STAY;
        } else {
            rfl_raw_activity_type2 = RFL_RAW_ACTIVITY_TYPE.TRANSPORT;
        }
        this.lastLocationForEstimation = this.currentLocation;
        return rfl_raw_activity_type2;
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case TwitterResponse.READ /*1*/:
                checkPedometer(sensorEvent);
                return;
            case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                setGyroscope(sensorEvent);
                return;
            default:
                return;
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    protected float[] lowPass(float[] fArr, float[] fArr2) {
        if (fArr2 == null) {
            return fArr;
        }
        for (int i = 0; i < fArr.length; i++) {
            fArr2[i] = fArr2[i] + (ALPHA * (fArr[i] - fArr2[i]));
        }
        return fArr2;
    }

    private void checkPedometer(SensorEvent sensorEvent) {
        Object obj;
        if (this._accelometers.size() == 0) {
            obj = (float[]) sensorEvent.values.clone();
        } else {
            obj = lowPass((float[]) sensorEvent.values.clone(), (float[]) ((float[]) this._accelometers.get(this._accelometers.size() - 1)).clone());
        }
        this._accelometers.add(obj);
        int size = this._accelometers.size();
        if (size >= 3) {
            float[] fArr = (float[]) this._accelometers.get(size - 2);
            float[] fArr2 = (float[]) this._accelometers.get(size - 3);
            float sqrt = ((float) Math.sqrt((double) ((obj[2] * obj[2]) + ((obj[0] * obj[0]) + (obj[1] * obj[1]))))) * SI2G;
            float sqrt2 = ((float) Math.sqrt((double) ((fArr[2] * fArr[2]) + ((fArr[0] * fArr[0]) + (fArr[1] * fArr[1]))))) * SI2G;
            sqrt2 = (sqrt2 * 2.0f) + (sqrt * 3.0f);
            sqrt2 = (sqrt2 + (((float) Math.sqrt((double) ((fArr2[2] * fArr2[2]) + ((fArr2[0] * fArr2[0]) + (fArr2[1] * fArr2[1]))))) * SI2G)) / 6.0f;
            if (this._bStepFlag) {
                long j = sensorEvent.timestamp - this._nStepTimestamp;
                if (j >= 1000000000) {
                    this._bStepFlag = false;
                    this._nStepTimestamp = 0;
                } else if (j > 150000000 && sqrt2 < ALPHA) {
                    didUpdatePedometer();
                    this._bStepFlag = false;
                    this._nStepTimestamp = sensorEvent.timestamp;
                }
            } else if (sqrt2 > 1.1f && sensorEvent.timestamp - this._nStepTimestamp > 180000000) {
                this._bStepFlag = true;
                this._nStepTimestamp = sensorEvent.timestamp;
            }
            this._accelometers.remove(0);
        }
    }

    private void setGyroscope(SensorEvent sensorEvent) {
        if (!this.isGyroActivated) {
            this.isGyroActivated = true;
            this.sharedPreferences.edit().putBoolean(RFLConstants.KEY_FOR_NO_GYRO, false).apply();
        }
    }

    private void didUpdatePedometer() {
        if (isAuthenticated().booleanValue()) {
            activeModeSwitch();
            this.rflActivityLogger.didUpdatePedometer(new Date().getTime());
            if (!this.isPauseUpdate || !isTrackingOn()) {
                return;
            }
            if (this.mGoogleApiClient == null || !this.mGoogleApiClient.isConnected()) {
                restartTracking();
            } else {
                startUpdates();
            }
        }
    }

    private void activeModeSwitch() {
        if (!this.isActiveMode) {
            if (this.activeModeTime == 0) {
                this.activeModeTime = System.currentTimeMillis();
            }
            if (System.currentTimeMillis() - this.activeModeTime < 90000) {
                this.activeModeCount++;
                if (this.activeModeCount > 3) {
                    this.isActiveMode = true;
                    this.activeModeTime = System.currentTimeMillis();
                    startActiveModeTimer();
                }
            }
        } else if (System.currentTimeMillis() - this.activeModeTime > 90000) {
            this.activeModeTime = System.currentTimeMillis();
            startActiveModeTimer();
        }
    }

    private void startActiveModeTimer() {
        if (!(this.activeModeSubscription == null || this.activeModeSubscription.isUnsubscribed())) {
            this.activeModeSubscription.unsubscribe();
            this.activeModeSubscription = null;
        }
        activeActivityUpdate();
        this.activeModeSubscription = Observable.timer(180000, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(RFLDailyManager$$Lambda$16.lambdaFactory$(this));
    }

    private void turnActiveModeOff() {
        this.activeModeTime = 0;
        this.activeModeCount = 0;
        this.isActiveMode = false;
        if (!this.isPauseUpdate) {
            ordinalActivityUpdate();
        }
    }

    private void observeGetEventBus() {
        if (this.getEventBus != null) {
            this.getEventBus = null;
        }
        this.getEventBus = new RxBus();
        if (!(this.eventSubscriptions == null || this.eventSubscriptions.isUnsubscribed())) {
            this.eventSubscriptions.unsubscribe();
            this.eventSubscriptions = null;
        }
        this.eventSubscriptions = new CompositeSubscription();
        ConnectableObservable publish = this.getEventBus.toObserverable().publish();
        this.eventSubscriptions.add(publish.subscribe(RFLDailyManager$$Lambda$17.lambdaFactory$(this)));
        this.eventSubscriptions.add(publish.connect());
    }

    static /* synthetic */ void lambda$observeGetEventBus$16(RFLDailyManager rFLDailyManager, Object obj) {
        if (obj instanceof RFLEventResponse) {
            obj = (RFLEventResponse) obj;
            rFLDailyManager.sharedPreferences.edit().putString(RFLConstants.KEY_FOR_RAW_EVENT, new f().a(obj)).apply();
            rFLDailyManager.initEvent();
        }
    }

    private void getEvent() {
        if (!this.isInPlace || !this.isInRegion) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.sharedPreferences.getLong(RFLConstants.KEY_FOR_GET_EVENT_TIME, 0) > 7200000) {
                this.cancelEventList = new ArrayList();
                this.isInitEvent = false;
                this.isSettingEvent = true;
                observeGetEventBus();
                this.sharedPreferences.edit().putLong(RFLConstants.KEY_FOR_GET_EVENT_TIME, currentTimeMillis).apply();
                this.rflApiManager.getEvent(this.getEventBus);
            } else if (!this.isInitEvent) {
                initEvent();
            }
        }
    }

    private void initEvent() {
        this.isInitEvent = true;
        String string = this.sharedPreferences.getString(RFLConstants.KEY_FOR_RAW_EVENT, null);
        if (string != null) {
            registerEvent((RFLEventResponse) new f().a(string, new com.google.a.c.a<RFLEventResponse>() {
            }.getType()));
            return;
        }
        this.isSettingEvent = false;
        this.sharedPreferences.edit().putLong(RFLConstants.KEY_FOR_GET_EVENT_TIME, 0).apply();
        getEvent();
    }

    private void registerEvent(RFLEventResponse rFLEventResponse) {
        this.eventSettingList = new ArrayList();
        this.registerPoiList = new ArrayList();
        this.isInPlace = false;
        this.isInRegion = false;
        this.isInMonitorActivity = false;
        this.sharedPreferences.edit().putLong(RFLConstants.KEY_FOR_MONITOR_REGION, 0).apply();
        this.sharedPreferences.edit().putLong(RFLConstants.KEY_FOR_MONITOR_PLACE, 0).apply();
        if (rFLEventResponse.getSchema().equals(RFLConstants.kRFLEventSchema)) {
            if (rFLEventResponse.getEvents() != null) {
                long currentTimeMillis = System.currentTimeMillis();
                f fVar = new f();
                for (RFLEvent rFLEvent : rFLEventResponse.getEvents()) {
                    if (rFLEvent.isMatchRange()) {
                        Object obj;
                        Iterator it;
                        Object obj2;
                        if (rFLEvent.isMatchDayOfWeek() && rFLEvent.isMatchPeriod()) {
                            String string = this.sharedPreferences.getString(String.format(Locale.JAPAN, "notified-event-%d", new Object[]{rFLEvent.getEventId()}), null);
                            if (string == null) {
                                obj = 1;
                            } else {
                                RFLTiming rFLTiming = (RFLTiming) rFLEvent.getTimings().get(0);
                                if (rFLTiming.getRepeat().intValue() > 0) {
                                    if (currentTimeMillis - ((RFLNotifiedEvent) fVar.a(string, new com.google.a.c.a<RFLNotifiedEvent>() {
                                    }.getType())).getTime() > ((long) rFLTiming.getRepeat().intValue()) * 1000) {
                                        obj = 1;
                                    }
                                }
                            }
                            if (!(obj == null || this.cancelEventList == null)) {
                                it = this.cancelEventList.iterator();
                                while (it.hasNext()) {
                                    if (rFLEvent.getEventId().equals((Integer) it.next())) {
                                        obj2 = null;
                                        break;
                                    }
                                }
                            }
                            obj2 = obj;
                            if (obj2 != null) {
                                this.eventSettingList.add(rFLEvent);
                            }
                        }
                        obj = null;
                        it = this.cancelEventList.iterator();
                        while (it.hasNext()) {
                            if (rFLEvent.getEventId().equals((Integer) it.next())) {
                                obj2 = null;
                                break;
                            }
                        }
                        obj2 = obj;
                        if (obj2 != null) {
                            this.eventSettingList.add(rFLEvent);
                        }
                    }
                }
            } else {
                a.b("event list is null", new Object[0]);
            }
            registerPOI();
        }
    }

    private void registerPOI() {
        if (this.eventSettingList.size() == 0) {
            this.isSettingEvent = false;
            return;
        }
        Iterator it = this.eventSettingList.iterator();
        while (it.hasNext()) {
            RFLEvent rFLEvent = (RFLEvent) it.next();
            Location centerLocation = rFLEvent.getCenterLocation();
            if (centerLocation != null && isInPlaceWithRange(centerLocation, rFLEvent.getCenterRange())) {
                for (RFLPoi rFLPoi : rFLEvent.getNotification().getPoiList()) {
                    if (!(rFLPoi.getLat() == null || rFLPoi.getLon() == null || rFLPoi.getRangeRadius() == null)) {
                        this.registerPoiList.add(new RFLNotifiedPoi(rFLPoi.getPoiId(), rFLPoi.getPlaceId(), rFLPoi.getPoiType(), rFLPoi.getLat(), rFLPoi.getLon(), rFLPoi.getRangeRadius(), rFLPoi.getTimingIndex(), rFLPoi.getName().getText(), rFLEvent.getEventId()));
                    }
                }
            }
        }
        this.isSettingEvent = false;
    }

    private void monitorEvents() {
        if (!this.isSettingEvent && this.registerPoiList != null && this.registerPoiList.size() != 0) {
            if (this.isInRegion && this.isInPlace) {
                if (!this.isInMonitorActivity) {
                    monitorActivity();
                }
            } else if (this.isInRegion && !this.isInPlace) {
                monitorPlace();
            } else if (this.isInRegion || !this.isInPlace) {
                monitorRegion();
            } else {
                this.isInRegion = false;
                this.isInPlace = false;
                monitorRegion();
            }
        }
    }

    private void monitorRegion() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.sharedPreferences.getLong(RFLConstants.KEY_FOR_MONITOR_REGION, 0) > TapjoyConstants.PAID_APP_TIME) {
            this.sharedPreferences.edit().putLong(RFLConstants.KEY_FOR_MONITOR_REGION, currentTimeMillis).apply();
            ArrayList arrayList = new ArrayList();
            Iterator it = this.registerPoiList.iterator();
            while (it.hasNext()) {
                RFLNotifiedPoi rFLNotifiedPoi = (RFLNotifiedPoi) it.next();
                arrayList.add(Boolean.valueOf(isInPlaceWithRange(createLocation(rFLNotifiedPoi.getLat().doubleValue(), rFLNotifiedPoi.getLon().doubleValue()), 10000.0f)));
            }
            if (arrayList.contains(Boolean.valueOf(true))) {
                this.isInRegion = true;
                monitorPlace();
                return;
            }
            leftRegion();
        }
    }

    private void monitorPlace() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.sharedPreferences.getLong(RFLConstants.KEY_FOR_MONITOR_PLACE, 0) > 60000) {
            this.sharedPreferences.edit().putLong(RFLConstants.KEY_FOR_MONITOR_PLACE, currentTimeMillis).apply();
            ArrayList arrayList = new ArrayList();
            Iterator it = this.registerPoiList.iterator();
            while (it.hasNext()) {
                RFLNotifiedPoi rFLNotifiedPoi = (RFLNotifiedPoi) it.next();
                arrayList.add(Boolean.valueOf(isInPlaceWithRange(createLocation(rFLNotifiedPoi.getLat().doubleValue(), rFLNotifiedPoi.getLon().doubleValue()), rFLNotifiedPoi.getRangeRadius().floatValue())));
            }
            if (arrayList.contains(Boolean.valueOf(true))) {
                this.isInPlace = true;
                monitorActivity();
                this.sharedPreferences.edit().putLong(RFLConstants.KEY_FOR_ENTER_PLACE, currentTimeMillis).apply();
                return;
            }
            leftPlace();
        }
    }

    private void monitorActivity() {
        float f = 1000.0f;
        Iterator it = this.registerPoiList.iterator();
        RFLNotifiedPoi rFLNotifiedPoi = null;
        while (it.hasNext()) {
            float f2;
            RFLNotifiedPoi rFLNotifiedPoi2 = (RFLNotifiedPoi) it.next();
            float distanceTo = this.currentLocation.distanceTo(createLocation(rFLNotifiedPoi2.getLat().doubleValue(), rFLNotifiedPoi2.getLon().doubleValue()));
            if (distanceTo < f) {
                f2 = distanceTo;
            } else {
                rFLNotifiedPoi2 = rFLNotifiedPoi;
                f2 = f;
            }
            f = f2;
            rFLNotifiedPoi = rFLNotifiedPoi2;
        }
        if (rFLNotifiedPoi != null && rFLNotifiedPoi.getRangeRadius().floatValue() >= f) {
            this.isInMonitorActivity = true;
            this.acquiredPoi = rFLNotifiedPoi;
            Iterator it2 = this.eventSettingList.iterator();
            while (it2.hasNext()) {
                RFLEvent rFLEvent = (RFLEvent) it2.next();
                if (rFLEvent.getEventId().equals(this.acquiredPoi.getEventId())) {
                    this.acquiredEvent = rFLEvent;
                    break;
                }
            }
            if (!(this.monitorActivitySubscription == null || this.monitorActivitySubscription.isUnsubscribed())) {
                this.monitorActivitySubscription.unsubscribe();
                this.monitorActivitySubscription = null;
            }
            this.monitorActivitySubscription = Observable.interval(30000, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(RFLDailyManager$$Lambda$18.lambdaFactory$(this));
        }
    }

    private void checkActivity() {
        if (this.acquiredEvent == null && this.acquiredPoi == null) {
            leftRegion();
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j = this.sharedPreferences.getLong(RFLConstants.KEY_FOR_ENTER_PLACE, 0);
        RFLEvent rFLEvent = this.acquiredEvent;
        if (currentTimeMillis - j > 600000) {
            if (!(this.monitorActivitySubscription == null || this.monitorActivitySubscription.isUnsubscribed())) {
                this.monitorActivitySubscription.unsubscribe();
                this.monitorActivitySubscription = null;
            }
            if (this.cancelEventList == null) {
                this.cancelEventList = new ArrayList();
            }
            this.cancelEventList.add(rFLEvent.getEventId());
            this.isSettingEvent = true;
            leftRegion();
            initEvent();
            return;
        }
        RFL_TIMING timing = ((RFLTiming) this.acquiredEvent.getTimings().get(0)).getEventActivity().getTiming();
        if (rFLEvent.isMatchTime()) {
            new RFLDailyLoader().getActivities(((RFLDailyDisplayData) this.displayDataList.get(this.displayDataList.size() - 1)).getTimestamp()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(RFLDailyManager$$Lambda$19.lambdaFactory$(this, timing), RFLDailyManager$$Lambda$20.lambdaFactory$(), RFLDailyManager$$Lambda$21.lambdaFactory$());
            return;
        }
        if (!(this.monitorActivitySubscription == null || this.monitorActivitySubscription.isUnsubscribed())) {
            this.monitorActivitySubscription.unsubscribe();
            this.monitorActivitySubscription = null;
        }
        if (this.cancelEventList == null) {
            this.cancelEventList = new ArrayList();
        }
        this.cancelEventList.add(rFLEvent.getEventId());
        this.isSettingEvent = true;
        leftRegion();
        initEvent();
    }

    static /* synthetic */ void lambda$checkActivity$18(RFLDailyManager rFLDailyManager, RFL_TIMING rfl_timing, List list) {
        RFLActivity rFLActivity = (RFLActivity) list.get(list.size() - 1);
        if (rFLActivity.getActivityType() == RFL_ACTIVITY_TYPE.STAY) {
            if (rfl_timing == RFL_TIMING.CHECK_STAY || rfl_timing == RFL_TIMING.CHECK_STAY_TRANS || rfl_timing == RFL_TIMING.CHECK_STAY_WALK || rfl_timing == RFL_TIMING.CHECK_ALL) {
                rFLDailyManager.sendNotification("sty");
            }
        } else if (rFLActivity.getActivityType() == RFL_ACTIVITY_TYPE.WALK) {
            if (rfl_timing == RFL_TIMING.CHECK_WALK || rfl_timing == RFL_TIMING.CHECK_WALK_TRANS || rfl_timing == RFL_TIMING.CHECK_STAY_WALK || rfl_timing == RFL_TIMING.CHECK_ALL) {
                rFLDailyManager.sendNotification("wlk");
            }
        } else if (rFLActivity.getActivityType() != RFL_ACTIVITY_TYPE.TRANSPORT) {
        } else {
            if (rfl_timing == RFL_TIMING.CHECK_TRANS || rfl_timing == RFL_TIMING.CHECK_WALK_TRANS || rfl_timing == RFL_TIMING.CHECK_STAY_TRANS || rfl_timing == RFL_TIMING.CHECK_ALL) {
                rFLDailyManager.sendNotification("trp");
            }
        }
    }

    static /* synthetic */ void lambda$checkActivity$19(Throwable th) {
    }

    static /* synthetic */ void lambda$checkActivity$20() {
    }

    private void sendNotification(String str) {
        if (!(this.monitorActivitySubscription == null || this.monitorActivitySubscription.isUnsubscribed())) {
            this.monitorActivitySubscription.unsubscribe();
            this.monitorActivitySubscription = null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        Integer offset = ((RFLTiming) this.acquiredEvent.getTimings().get(0)).getOffset();
        RFLEvent rFLEvent = this.acquiredEvent;
        String format = String.format(Locale.JAPAN, "notified-event-%d", new Object[]{rFLEvent.getEventId()});
        Object rFLNotifiedEvent = new RFLNotifiedEvent(currentTimeMillis, rFLEvent.getEventId(), true, false);
        f fVar = new f();
        this.sharedPreferences.edit().putString(format, fVar.a(rFLNotifiedEvent)).apply();
        this.sharedPreferences.edit().putString(RFLConstants.KEY_FOR_POI_MONITORED, fVar.a(this.acquiredPoi)).apply();
        Observable.timer(((long) offset.intValue()) * 1000, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(RFLDailyManager$$Lambda$22.lambdaFactory$(this, rFLEvent, this.acquiredPoi, str));
    }

    private void notifyOperation(RFLEvent rFLEvent, RFLNotifiedPoi rFLNotifiedPoi, String str) {
        rFLEvent.getNotification();
        try {
            String makeEventJsonForUser = makeEventJsonForUser(rFLEvent, rFLNotifiedPoi);
            if (this.mListener != null) {
                this.mListener.notifyEventAcquired(makeEventJsonForUser, rFLEvent.getEventId());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        int time = (int) (this.currentLocation.getTime() / 1000);
        postResponse("notify", rFLEvent.getEventId(), rFLNotifiedPoi.getPoiId(), Integer.valueOf(screenState()), Integer.valueOf(currentTimeMillis), Integer.valueOf(time), (float) this.currentLocation.getLatitude(), (float) this.currentLocation.getLongitude(), str, 0.4f);
        leftRegion();
        initEvent();
    }

    private void leftRegion() {
        this.isInitEvent = false;
        this.acquiredEvent = null;
        this.acquiredPoi = null;
        this.isInRegion = false;
        this.isInPlace = false;
    }

    private void leftPlace() {
        this.isInPlace = false;
    }

    private String makeEventJsonForUser(RFLEvent rFLEvent, RFLNotifiedPoi rFLNotifiedPoi) {
        JSONObject jSONObject;
        int i = 0;
        JSONObject jSONObject2 = new JSONObject();
        if (rFLEvent.getNotification().getMessageType().equals(String.URL)) {
            i = 1;
            jSONObject2.put(String.URL, rFLEvent.getNotification().getUrl());
        } else {
            String titleText = rFLEvent.getNotification().getTitleText();
            jSONObject = new JSONObject();
            jSONObject.put("default", titleText);
            jSONObject.put(Constants.LOCALE_JA, titleText);
            titleText = rFLEvent.getNotification().getBodyText();
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("default", titleText);
            jSONObject3.put(Constants.LOCALE_JA, titleText);
            jSONObject2.put(String.TITLE, jSONObject);
            jSONObject2.put("body", jSONObject3);
        }
        JSONObject jSONObject4 = new JSONObject();
        jSONObject4.put("poiId", rFLNotifiedPoi.getPoiId());
        jSONObject4.put("placeId", rFLNotifiedPoi.getPlaceId());
        jSONObject4.put("poiName", rFLNotifiedPoi.getName());
        jSONObject = new JSONObject();
        jSONObject.put("eventId", rFLEvent.getEventId());
        jSONObject.put(String.TRIGGERED_EVENT_NAME, rFLEvent.getEventName());
        jSONObject.put(String.MESSAGE, jSONObject2);
        jSONObject.put("messageType", i);
        jSONObject.put("poi", jSONObject4);
        return jSONObject.toString();
    }

    public String getDataWithEventId(Integer num) {
        int i = 1;
        String string = this.sharedPreferences.getString(String.format(Locale.JAPAN, "notified-event-%d", new Object[]{num}), null);
        if (string != null) {
            RFLNotifiedEvent rFLNotifiedEvent = (RFLNotifiedEvent) new f().a(string, new com.google.a.c.a<RFLNotifiedEvent>() {
            }.getType());
            if (rFLNotifiedEvent.isOpen() != null) {
                i = !rFLNotifiedEvent.isOpen().booleanValue() ? 1 : 0;
            }
        } else {
            i = 0;
        }
        if (i == 0) {
            return null;
        }
        RFLEvent rFLEvent;
        string = this.sharedPreferences.getString(RFLConstants.KEY_FOR_RAW_EVENT, null);
        if (string != null) {
            RFLEventResponse rFLEventResponse = (RFLEventResponse) new f().a(string, new com.google.a.c.a<RFLEventResponse>() {
            }.getType());
            if (rFLEventResponse.getSchema().equals(RFLConstants.kRFLEventSchema)) {
                for (RFLEvent rFLEvent2 : rFLEventResponse.getEvents()) {
                    if (rFLEvent2.getEventId().equals(num)) {
                        rFLEvent = rFLEvent2;
                        break;
                    }
                }
            }
        }
        rFLEvent = null;
        if (rFLEvent == null) {
            return null;
        }
        string = this.sharedPreferences.getString(RFLConstants.KEY_FOR_POI_MONITORED, null);
        if (string != null) {
            try {
                string = makeEventJsonForUser(rFLEvent, (RFLNotifiedPoi) new f().a(string, new com.google.a.c.a<RFLNotifiedPoi>() {
                }.getType()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.sharedPreferences.edit().putString(RFLConstants.KEY_FOR_POI_MONITORED, null).apply();
            return string;
        }
        string = null;
        this.sharedPreferences.edit().putString(RFLConstants.KEY_FOR_POI_MONITORED, null).apply();
        return string;
    }

    public void postResponse(String str, Integer num, Integer num2) {
        new RFLDailyLoader().getActivities(((RFLDailyDisplayData) this.displayDataList.get(this.displayDataList.size() - 1)).getTimestamp()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(RFLDailyManager$$Lambda$23.lambdaFactory$(this, str, num, num2), RFLDailyManager$$Lambda$24.lambdaFactory$(), RFLDailyManager$$Lambda$25.lambdaFactory$());
    }

    static /* synthetic */ void lambda$postResponse$22(RFLDailyManager rFLDailyManager, String str, Integer num, Integer num2, List list) {
        int time;
        float latitude;
        float f = 0.0f;
        RFLActivity rFLActivity = (RFLActivity) list.get(list.size() - 1);
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        if (rFLDailyManager.currentLocation != null) {
            time = (int) (rFLDailyManager.currentLocation.getTime() / 1000);
            latitude = (float) rFLDailyManager.currentLocation.getLatitude();
            f = (float) rFLDailyManager.currentLocation.getLongitude();
        } else {
            latitude = 0.0f;
            time = currentTimeMillis;
        }
        rFLDailyManager.postResponse(str, num, num2, Integer.valueOf(rFLDailyManager.screenState()), Integer.valueOf(currentTimeMillis), Integer.valueOf(time), latitude, f, rFLActivity.getActivityString(), 0.4f);
    }

    static /* synthetic */ void lambda$postResponse$23(Throwable th) {
    }

    static /* synthetic */ void lambda$postResponse$24() {
    }

    private Location createLocation(double d, double d2) {
        Location location = new Location("SilentLog");
        location.setLatitude(d);
        location.setLongitude(d2);
        return location;
    }

    private boolean isInPlaceWithRange(Location location, float f) {
        if (this.currentLocation != null && this.currentLocation.distanceTo(location) < f) {
            return true;
        }
        return false;
    }

    private int screenState() {
        int parseInt;
        int parseInt2;
        int parseInt3 = this.isStartedTimer ? 0 : Integer.parseInt("00001", 2);
        int parseInt4 = Integer.parseInt("00010", 2);
        if (this.mIsScreenOn) {
            parseInt = Integer.parseInt("00100", 2);
        } else {
            parseInt = 0;
        }
        if (this.mIsScreenOn) {
            parseInt2 = Integer.parseInt("10000", 2);
        } else {
            parseInt2 = 0;
        }
        return (((parseInt3 + parseInt4) + parseInt) + 0) + parseInt2;
    }

    private void observeResponseBus() {
        if (this.responseBus != null) {
            this.responseBus = null;
        }
        this.responseBus = new RxBus();
        if (!(this.responseSubscriptions == null || this.responseSubscriptions.isUnsubscribed())) {
            this.responseSubscriptions.unsubscribe();
            this.responseSubscriptions = null;
        }
        this.responseSubscriptions = new CompositeSubscription();
        ConnectableObservable publish = this.responseBus.toObserverable().publish();
        this.responseSubscriptions.add(publish.subscribe(RFLDailyManager$$Lambda$26.lambdaFactory$()));
        this.responseSubscriptions.add(publish.connect());
    }

    static /* synthetic */ void lambda$observeResponseBus$25(Object obj) {
    }

    private void postResponse(String str, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, float f, float f2, String str2, float f3) {
        observeResponseBus();
        this.rflApiManager.postResponse(this.responseBus, str, num, num2, num3, RFLUtils.getLang(), num4, num5, f, f2, str2, f3);
    }
}
