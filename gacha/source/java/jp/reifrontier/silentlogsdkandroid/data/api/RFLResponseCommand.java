package jp.reifrontier.silentlogsdkandroid.data.api;

import c.a.a;
import com.google.a.f;
import java.io.IOException;
import jp.reifrontier.silentlogsdkandroid.domain.Response.RFLResponse.RFLResponseError;
import jp.reifrontier.silentlogsdkandroid.util.RFLConstants;
import jp.reifrontier.silentlogsdkandroid.util.RxBus;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RFLResponseCommand {
    static final /* synthetic */ boolean $assertionsDisabled = (!RFLResponseCommand.class.desiredAssertionStatus());
    private String accessToken;
    private String act;
    private Integer date;
    private boolean debug = false;
    private Integer eventId;
    private String lang;
    private float lat;
    private Integer locDate;
    private float lon;
    private String notificationType;
    private Integer poiId;
    private RxBus rxBus;
    private Integer screen;
    private float wspeed;

    interface PostResponse {
        @POST("event/response")
        @FormUrlEncoded
        Observable<Response<ResponseBody>> post(@Field("access_token") String str, @Field("notification_type") String str2, @Field("event_id") Integer num, @Field("poi_id") Integer num2, @Field("platform") String str3, @Field("screen") Integer num3, @Field("lang") String str4, @Field("date") Integer num4, @Field("loc_date") Integer num5, @Field("lat") float f, @Field("lon") float f2, @Field("act") String str5, @Field("wspeed") float f3);
    }

    public RFLResponseCommand(RxBus rxBus, String str, String str2, Integer num, Integer num2, Integer num3, String str3, Integer num4, Integer num5, float f, float f2, String str4, float f3, boolean z) {
        this.rxBus = rxBus;
        this.accessToken = str;
        this.notificationType = str2;
        this.eventId = num;
        this.poiId = num2;
        this.screen = num3;
        this.lang = str3;
        this.date = num4;
        this.locDate = num5;
        this.lat = f;
        this.lon = f2;
        this.act = str4;
        this.wspeed = f3;
        this.debug = z;
    }

    public void execute() {
        Interceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(Level.NONE);
        OkHttpClient build = new Builder().addInterceptor(httpLoggingInterceptor).build();
        String str = RFLConstants.kRFLAPI_H1_PRODUCTION_URL_Base;
        if (this.debug) {
            str = RFLConstants.kRFLAPI_H1_STAGING_URL_Base;
        }
        ((PostResponse) new Retrofit.Builder().baseUrl(str).client(build).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build().create(PostResponse.class)).post(this.accessToken, this.notificationType, this.eventId, this.poiId, "and", this.screen, this.lang, this.date, this.locDate, this.lat, this.lon, this.act, this.wspeed).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).flatMap(RFLResponseCommand$$Lambda$1.lambdaFactory$()).subscribe(RFLResponseCommand$$Lambda$2.lambdaFactory$(this), RFLResponseCommand$$Lambda$3.lambdaFactory$(this), RFLResponseCommand$$Lambda$4.lambdaFactory$());
    }

    static /* synthetic */ Observable lambda$execute$0(Response response) {
        if (response.isSuccessful()) {
            return Observable.just(response);
        }
        return Observable.error(new HttpException(response));
    }

    static /* synthetic */ void lambda$execute$1(RFLResponseCommand rFLResponseCommand, Response response) {
        if (rFLResponseCommand.rxBus.hasObservers()) {
            rFLResponseCommand.rxBus.send(response.body());
        }
    }

    static /* synthetic */ void lambda$execute$2(RFLResponseCommand rFLResponseCommand, Throwable th) {
        if (th instanceof HttpException) {
            String str = null;
            try {
                str = ((HttpException) th).response().errorBody().string();
            } catch (IOException e) {
                a.b("Error reading errorBody from response", new Object[0]);
            }
            RFLResponseError rFLResponseError = (RFLResponseError) new f().a(str, new com.google.a.c.a<RFLResponseError>() {
            }.getType());
            if (!$assertionsDisabled && rFLResponseError == null) {
                throw new AssertionError();
            } else if (rFLResponseCommand.rxBus.hasObservers()) {
                rFLResponseCommand.rxBus.send(rFLResponseError);
            }
        }
    }

    static /* synthetic */ void lambda$execute$3() {
    }
}
