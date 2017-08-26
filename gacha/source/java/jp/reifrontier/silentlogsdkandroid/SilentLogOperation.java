package jp.reifrontier.silentlogsdkandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import c.a.a;
import com.google.android.gms.common.ConnectionResult;
import com.raizlabs.android.dbflow.config.FlowConfig.Builder;
import com.raizlabs.android.dbflow.config.FlowManager;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import jp.reifrontier.silentlogsdkandroid.data.api.RFLAccountCreateCommand.AccountCreateResponse;
import jp.reifrontier.silentlogsdkandroid.data.api.RFLAuthCommand.AuthResponse;
import jp.reifrontier.silentlogsdkandroid.data.api.RFLGetProfileCommand.GetProfileResponse;
import jp.reifrontier.silentlogsdkandroid.data.daily.RFLDailyManager;
import jp.reifrontier.silentlogsdkandroid.data.daily.RFLDailyManager.RFLDailyManagerListener;
import jp.reifrontier.silentlogsdkandroid.domain.Response.RFLResponse.RFLResponseError;
import jp.reifrontier.silentlogsdkandroid.domain.Response.RFLResponse.RFLResponseSuccess;
import jp.reifrontier.silentlogsdkandroid.util.RFLConstants;
import jp.reifrontier.silentlogsdkandroid.util.RFLDateUtils;
import jp.reifrontier.silentlogsdkandroid.util.RFLLogTree;
import jp.reifrontier.silentlogsdkandroid.util.RFLUtils;
import jp.reifrontier.silentlogsdkandroid.util.RxBus;
import org.cocos2dx.lib.BuildConfig;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class SilentLogOperation implements RFLDailyManagerListener {
    public static int SILENT_LOG_SDK_GPS_PERMISSION = 15540;
    public static int SILENT_LOG_SDK_SDCARD_PERMISSION = 32409;
    private RxBus accountRxBus = null;
    private RxBus authRxBus = null;
    private Context context = null;
    private Boolean hasStartOperation = Boolean.valueOf(false);
    private Boolean hasStop = Boolean.valueOf(false);
    private SilentLogOperationListener mListener;
    private RxBus postDailyRxBus = null;
    private RxBus profileRxBus = null;
    private RFLDailyManager rflDailyManager = null;
    private SharedPreferences sharedPreferences = null;
    private String storeAccountId;
    private String storeBirthday;
    private String storeGender;
    private Boolean storeRegist = Boolean.valueOf(false);
    private CompositeSubscription subscriptionsAccount;
    private CompositeSubscription subscriptionsAuth;
    private CompositeSubscription subscriptionsPostDaily;
    private CompositeSubscription subscriptionsProfile;

    public interface SilentLogOperationListener {
        void connectApi(boolean z, String str);

        void endBackgroundLoadDaily(String str);

        void googleApiConnectionFailed(ConnectionResult connectionResult);

        void notifyDetectDriving(String str);

        void notifyEventAcquired(String str, Integer num);
    }

    public SilentLogOperation(Context context, SilentLogOperationListener silentLogOperationListener) {
        this.context = context;
        this.mListener = silentLogOperationListener;
        a.a(new RFLLogTree(context.getPackageName(), context));
        FlowManager.init(new Builder(context).build());
        this.hasStartOperation = Boolean.valueOf(false);
        this.hasStop = Boolean.valueOf(false);
        this.sharedPreferences = context.getSharedPreferences(String.format(Locale.JAPAN, "%s%s", new Object[]{RFLConstants.PREF_NAME, r0}), 0);
        if (!this.sharedPreferences.getBoolean(RFLConstants.KEY_FOR_FIRST_LAUNCH, false)) {
            Editor edit = this.sharedPreferences.edit();
            edit.putLong(RFLConstants.KEY_FOR_INSTALL_DATE, RFLDateUtils.getDayStartLong(new Date().getTime(), null));
            edit.putBoolean(RFLConstants.KEY_FOR_FIRST_LAUNCH, true);
            edit.putString(RFLConstants.KEY_FOR_UUID, UUID.randomUUID().toString());
            edit.putBoolean(RFLConstants.KEY_FOR_START_UODATES, true);
            edit.apply();
        }
        this.rflDailyManager = new RFLDailyManager(context, this);
        observeRxBus();
    }

    public void endBackgroundLoadDaily(String str) {
        if (this.mListener != null) {
            this.mListener.endBackgroundLoadDaily(str);
        }
    }

    public void notifyDetectDangerDrivingMessage(String str) {
        if (this.mListener != null) {
            this.mListener.notifyDetectDriving(str);
        }
    }

    public void googleApiConnectionFailed(ConnectionResult connectionResult) {
        if (this.mListener != null) {
            this.mListener.googleApiConnectionFailed(connectionResult);
        }
    }

    public void notifyEventAcquired(String str, Integer num) {
        if (this.mListener != null) {
            this.mListener.notifyEventAcquired(str, num);
        }
    }

    public String getVersion() {
        return BuildConfig.VERSION_NAME;
    }

    public void permissionResultUnderM(int i) {
        Editor edit = this.sharedPreferences.edit();
        if (i == SILENT_LOG_SDK_GPS_PERMISSION) {
            edit.putBoolean(RFLConstants.KEY_FOR_AUTH_GPS, true);
            edit.putBoolean(RFLConstants.KEY_FOR_AUTH_SDCARD, true);
            edit.apply();
            authComplete();
        }
    }

    public void permissionResult(int i, String[] strArr, int[] iArr) {
        Editor edit = this.sharedPreferences.edit();
        if (i == SILENT_LOG_SDK_GPS_PERMISSION && iArr[0] == 0) {
            edit.putBoolean(RFLConstants.KEY_FOR_AUTH_GPS, true);
            edit.putBoolean(RFLConstants.KEY_FOR_AUTH_SDCARD, true);
            edit.apply();
            authComplete();
        }
    }

    public void setDebug(boolean z) {
        Editor edit = this.sharedPreferences.edit();
        edit.putBoolean(RFLConstants.KEY_FOR_DEBUG_URL, z);
        edit.apply();
    }

    public void setClientIdAndSecret(String str, String str2) {
        Editor edit = this.sharedPreferences.edit();
        edit.putString(RFLConstants.KEY_FOR_CLIENT_ID, str);
        edit.putString(RFLConstants.KEY_FOR_CLIENT_SECRET, str2);
        edit.apply();
    }

    public Boolean isConnect() {
        return isConnectedApi();
    }

    public long getInstallDay() {
        return this.sharedPreferences.getLong(RFLConstants.KEY_FOR_INSTALL_DATE, 0);
    }

    public String getUserName() {
        return this.sharedPreferences.getString(RFLConstants.KEY_FOR_USER_NAME, BuildConfig.FLAVOR);
    }

    public int getDataSize() {
        return this.rflDailyManager.getDisplayDataListSize();
    }

    public Boolean connectApi(String str, String str2, String str3) {
        if (!hasSetClientId().booleanValue()) {
            return Boolean.valueOf(false);
        }
        if (str2 == null) {
            str2 = "male";
        } else if (!(str2.equals("male") || str2.equals("female"))) {
            str2 = "male";
        }
        if (str3 == null) {
            str3 = "19900101";
        } else if (str3.length() != 8) {
            str3 = "19900101";
        }
        observeRxBus();
        if (str == null) {
            createJDAccount();
            return Boolean.valueOf(true);
        }
        this.storeAccountId = str;
        this.storeGender = str2;
        this.storeBirthday = str3;
        String string = this.sharedPreferences.getString(RFLConstants.KEY_FOR_CLIENT_ID, BuildConfig.FLAVOR);
        string = String.format(Locale.JAPAN, "%s%s%s", new Object[]{str, string, RFLConstants.kSLSUserIDStaticDomain});
        String md5 = RFLUtils.getMD5(string);
        Editor edit = this.sharedPreferences.edit();
        edit.putString(RFLConstants.KEY_FOR_USER_NAME, string);
        edit.putString(RFLConstants.KEY_FOR_PASSWORD, md5);
        edit.apply();
        this.rflDailyManager.auth(this.authRxBus);
        return Boolean.valueOf(true);
    }

    public void onStart() {
        if (isAuthenticated().booleanValue()) {
            this.hasStartOperation = Boolean.valueOf(true);
            this.rflDailyManager.onStart();
        }
    }

    public void onResume() {
        if (isAuthenticated().booleanValue()) {
            this.rflDailyManager.onResume();
        }
    }

    public void onStop() {
        if (isAuthenticated().booleanValue()) {
            this.hasStop = Boolean.valueOf(true);
            this.rflDailyManager.onStop();
        }
    }

    public Boolean onRestart() {
        boolean z = isAuthenticated().booleanValue() && this.rflDailyManager.onRestart();
        return Boolean.valueOf(z);
    }

    public Boolean hasStartOperation() {
        return this.hasStartOperation;
    }

    public Boolean hasStop() {
        return this.hasStop;
    }

    public void startTracking() {
        this.rflDailyManager.restartTracking();
    }

    public void stopTracking() {
        this.rflDailyManager.stopTracking();
    }

    public void checkBackgroundDaily() {
        this.rflDailyManager.checkDailyTimer();
    }

    public String loadLocation() {
        return this.rflDailyManager.loadLocation();
    }

    public Observable<String> loadDailyFromDate(Date date) {
        if (isAuthenticated().booleanValue() && isConnectedApi().booleanValue()) {
            return this.rflDailyManager.loadDailyFromDate(date.getTime());
        }
        a.b("Operation not auth or connected", new Object[0]);
        return null;
    }

    public void saveActivityTag(String str, int i, int i2) {
        RFLEntityTag rFLEntityTag = new RFLEntityTag();
        rFLEntityTag.name = str;
        rFLEntityTag.fromDate = ((long) i) * 1000;
        rFLEntityTag.toDate = ((long) i2) * 1000;
        rFLEntityTag.save();
    }

    public void postDaily(Date date, boolean z) {
        TimeZone timeZone = TimeZone.getDefault();
        this.rflDailyManager.postDaily(this.postDailyRxBus, RFLDateUtils.getQueryDateLong(date.getTime(), timeZone.getID()), timeZone.getID(), z);
    }

    public void logoutAPI() {
        Editor edit = this.sharedPreferences.edit();
        edit.putString(RFLConstants.KEY_FOR_ACCESS_TOKEN, BuildConfig.FLAVOR);
        edit.apply();
    }

    public String getDataWithEventId(Integer num) {
        return this.rflDailyManager.getDataWithEventId(num);
    }

    public void postResponse(Integer num, Integer num2, String str) {
        if (str == null) {
            str = "touch";
        }
        this.rflDailyManager.postResponse(str, num, num2);
    }

    public void uploadForceTest() {
        Date date = new Date();
        TimeZone timeZone = TimeZone.getDefault();
        loadDailyFromDate(date).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(SilentLogOperation$$Lambda$1.lambdaFactory$(), SilentLogOperation$$Lambda$4.lambdaFactory$(), SilentLogOperation$$Lambda$5.lambdaFactory$(this, RFLDateUtils.getQueryDateLong(date.getTime(), timeZone.getID()), timeZone));
    }

    static /* synthetic */ void lambda$uploadForceTest$0(String str) {
    }

    static /* synthetic */ void lambda$uploadForceTest$1(Throwable th) {
    }

    private void observeRxBus() {
        observeAccountRxBus();
        observeAuthRxBus();
        observeProfileRxBus();
        observePostDailyRxBus();
    }

    private void authComplete() {
        Editor edit = this.sharedPreferences.edit();
        edit.putBoolean(RFLConstants.KEY_FOR_AUTH, true);
        edit.putLong(RFLConstants.KEY_FOR_INSTALL_DATE, RFLDateUtils.getDayStartLong(new Date().getTime(), null));
        edit.apply();
    }

    private Boolean isAuthenticated() {
        if (this.sharedPreferences.getBoolean(RFLConstants.KEY_FOR_AUTH, false)) {
            return Boolean.valueOf(true);
        }
        if (android.support.v4.app.a.a(this.context, "android.permission.ACCESS_FINE_LOCATION") != 0 || android.support.v4.app.a.a(this.context, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
            return Boolean.valueOf(false);
        }
        this.sharedPreferences.edit().putBoolean(RFLConstants.KEY_FOR_AUTH, true).apply();
        return Boolean.valueOf(true);
    }

    private Boolean isConnectedApi() {
        return Boolean.valueOf(!this.sharedPreferences.getString(RFLConstants.KEY_FOR_ACCESS_TOKEN, BuildConfig.FLAVOR).equals(BuildConfig.FLAVOR));
    }

    private Boolean hasSetClientId() {
        return Boolean.valueOf(!this.sharedPreferences.getString(RFLConstants.KEY_FOR_CLIENT_ID, BuildConfig.FLAVOR).equals(BuildConfig.FLAVOR));
    }

    private void createAccount() {
        this.storeRegist = Boolean.valueOf(true);
        String string = this.sharedPreferences.getString(RFLConstants.KEY_FOR_CLIENT_ID, BuildConfig.FLAVOR);
        String format = String.format(Locale.JAPAN, "%s%s%s", new Object[]{this.storeAccountId, string, RFLConstants.kSLSUserIDStaticDomain});
        String md5 = RFLUtils.getMD5(format);
        Editor edit = this.sharedPreferences.edit();
        edit.putString(RFLConstants.KEY_FOR_USER_NAME, format);
        edit.putString(RFLConstants.KEY_FOR_PASSWORD, md5);
        edit.apply();
        this.rflDailyManager.createAccount(this.accountRxBus, this.storeAccountId, format, md5, this.storeGender, this.storeBirthday, this.storeRegist);
    }

    private void createJDAccount() {
        this.storeRegist = Boolean.valueOf(false);
        String string = this.sharedPreferences.getString(RFLConstants.KEY_FOR_CLIENT_ID, BuildConfig.FLAVOR);
        String string2 = this.sharedPreferences.getString(RFLConstants.KEY_FOR_UUID, RFLConstants.kRFLAPI_H1_Parameter_Platform_AND);
        string2 = String.format(Locale.JAPAN, "%s%.0f", new Object[]{string2, Double.valueOf(((double) System.currentTimeMillis()) / 1000.0d)});
        String format = String.format(Locale.JAPAN, "%s%s%s", new Object[]{string2, string, RFLConstants.kSLSUserIDStaticDomain});
        String md5 = RFLUtils.getMD5(format);
        Editor edit = this.sharedPreferences.edit();
        edit.putString(RFLConstants.KEY_FOR_USER_NAME, format);
        edit.putString(RFLConstants.KEY_FOR_PASSWORD, md5);
        edit.apply();
        this.rflDailyManager.createAccount(this.accountRxBus, string, format, md5, "male", "19900101", this.storeRegist);
    }

    private void observeAccountRxBus() {
        if (this.accountRxBus != null) {
            this.accountRxBus = null;
        }
        this.accountRxBus = new RxBus();
        if (!(this.subscriptionsAccount == null || this.subscriptionsAccount.isUnsubscribed())) {
            this.subscriptionsAccount.unsubscribe();
            this.subscriptionsAccount = null;
        }
        this.subscriptionsAccount = new CompositeSubscription();
        ConnectableObservable publish = this.accountRxBus.toObserverable().publish();
        this.subscriptionsAccount.add(publish.subscribe(SilentLogOperation$$Lambda$6.lambdaFactory$(this)));
        this.subscriptionsAccount.add(publish.connect());
    }

    static /* synthetic */ void lambda$observeAccountRxBus$3(SilentLogOperation silentLogOperation, Object obj) {
        if (obj instanceof RFLResponseError) {
            silentLogOperation.postAccountError((RFLResponseError) obj);
        } else if (obj instanceof AccountCreateResponse) {
            silentLogOperation.accountCreateSuccess((AccountCreateResponse) obj);
        }
    }

    private void observeProfileRxBus() {
        if (this.profileRxBus != null) {
            this.profileRxBus = null;
        }
        this.profileRxBus = new RxBus();
        if (!(this.subscriptionsProfile == null || this.subscriptionsProfile.isUnsubscribed())) {
            this.subscriptionsProfile.unsubscribe();
            this.subscriptionsProfile = null;
        }
        this.subscriptionsProfile = new CompositeSubscription();
        ConnectableObservable publish = this.profileRxBus.toObserverable().publish();
        this.subscriptionsProfile.add(publish.subscribe(SilentLogOperation$$Lambda$7.lambdaFactory$(this)));
        this.subscriptionsProfile.add(publish.connect());
    }

    static /* synthetic */ void lambda$observeProfileRxBus$4(SilentLogOperation silentLogOperation, Object obj) {
        if (obj instanceof RFLResponseError) {
            silentLogOperation.getProfileError((RFLResponseError) obj);
        } else if (obj instanceof GetProfileResponse) {
            silentLogOperation.getProfileSuccess((GetProfileResponse) obj);
        }
    }

    private void observeAuthRxBus() {
        if (this.authRxBus != null) {
            this.authRxBus = null;
        }
        this.authRxBus = new RxBus();
        if (!(this.subscriptionsAuth == null || this.subscriptionsAuth.isUnsubscribed())) {
            this.subscriptionsAuth.unsubscribe();
            this.subscriptionsAuth = null;
        }
        this.subscriptionsAuth = new CompositeSubscription();
        ConnectableObservable publish = this.authRxBus.toObserverable().publish();
        this.subscriptionsAuth.add(publish.subscribe(SilentLogOperation$$Lambda$8.lambdaFactory$(this)));
        this.subscriptionsAuth.add(publish.connect());
    }

    static /* synthetic */ void lambda$observeAuthRxBus$5(SilentLogOperation silentLogOperation, Object obj) {
        if (obj instanceof RFLResponseError) {
            silentLogOperation.postAuthError((RFLResponseError) obj);
        } else if (obj instanceof AuthResponse) {
            silentLogOperation.authSuccess((AuthResponse) obj);
        }
    }

    private void observePostDailyRxBus() {
        if (this.postDailyRxBus != null) {
            this.postDailyRxBus = null;
        }
        this.postDailyRxBus = new RxBus();
        if (!(this.subscriptionsPostDaily == null || this.subscriptionsPostDaily.isUnsubscribed())) {
            this.subscriptionsPostDaily.unsubscribe();
            this.subscriptionsPostDaily = null;
        }
        this.subscriptionsPostDaily = new CompositeSubscription();
        ConnectableObservable publish = this.postDailyRxBus.toObserverable().publish();
        this.subscriptionsPostDaily.add(publish.subscribe(SilentLogOperation$$Lambda$9.lambdaFactory$(this)));
        this.subscriptionsPostDaily.add(publish.connect());
    }

    static /* synthetic */ void lambda$observePostDailyRxBus$6(SilentLogOperation silentLogOperation, Object obj) {
        if (obj instanceof RFLResponseError) {
            silentLogOperation.getPostDailyError((RFLResponseError) obj);
        } else if (obj instanceof RFLResponseSuccess) {
            a.a("Upload Daily Done", new Object[0]);
            RFLResponseSuccess rFLResponseSuccess = (RFLResponseSuccess) obj;
            silentLogOperation.rflDailyManager.updateEntity(rFLResponseSuccess.getDate(), rFLResponseSuccess.getTimezone());
        }
    }

    private void accountCreateSuccess(AccountCreateResponse accountCreateResponse) {
        Editor edit = this.sharedPreferences.edit();
        edit.putString(RFLConstants.KEY_FOR_ACCESS_TOKEN, accountCreateResponse.token);
        edit.apply();
        getProfile();
    }

    private void authSuccess(AuthResponse authResponse) {
        Editor edit = this.sharedPreferences.edit();
        edit.putString(RFLConstants.KEY_FOR_ACCESS_TOKEN, authResponse.token);
        edit.apply();
        getProfile();
    }

    private void getProfile() {
        this.rflDailyManager.getProfile(this.profileRxBus);
    }

    private void getProfileSuccess(GetProfileResponse getProfileResponse) {
        Editor edit = this.sharedPreferences.edit();
        edit.putString(RFLConstants.KEY_FOR_USER_NAME, getProfileResponse.username);
        edit.apply();
        if (this.mListener != null) {
            this.mListener.connectApi(true, null);
        }
    }

    private void postAccountError(RFLResponseError rFLResponseError) {
        String error = rFLResponseError.getError();
        a.b("account create Error:%s", rFLResponseError.getError());
        if (this.mListener != null) {
            this.mListener.connectApi(false, error);
        }
    }

    private void postAuthError(RFLResponseError rFLResponseError) {
        if (rFLResponseError.getError().equals("not_found")) {
            createAccount();
        } else {
            createJDAccount();
        }
    }

    private void getProfileError(RFLResponseError rFLResponseError) {
        if (rFLResponseError.getError().equals("invalid_token")) {
            this.rflDailyManager.auth(this.authRxBus);
        }
    }

    private void getPostDailyError(RFLResponseError rFLResponseError) {
        if (rFLResponseError.getError().equals("invalid_token")) {
            this.rflDailyManager.auth(this.authRxBus);
        }
    }
}
