package jp.reifrontier.silentlogsdkandroid.data.api;

import c.a.a;
import com.google.a.f;
import java.io.IOException;
import jp.reifrontier.silentlogsdkandroid.domain.Response.RFLResponse.RFLResponseError;
import jp.reifrontier.silentlogsdkandroid.domain.Response.RFLResponse.RFLResponseSuccess;
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
import retrofit2.http.Path;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RFLPostDailyCommand {
    static final /* synthetic */ boolean $assertionsDisabled = (!RFLPostDailyCommand.class.desiredAssertionStatus());
    private String accessToken;
    private String date;
    private boolean debug = false;
    private String requestDate;
    private RxBus rxBus;
    private String storyline;
    private String timezone;

    public interface PostDaily {
        @POST("user/storyline/daily/{requestDate}")
        @FormUrlEncoded
        Observable<Response<ResponseBody>> post(@Path("requestDate") String str, @Field("access_token") String str2, @Field("storyline") String str3);
    }

    public RFLPostDailyCommand(RxBus rxBus, String str, String str2, String str3, String str4, String str5, boolean z) {
        this.rxBus = rxBus;
        this.accessToken = str;
        this.requestDate = str2;
        this.storyline = str3;
        this.date = str4;
        this.timezone = str5;
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
        ((PostDaily) new Retrofit.Builder().baseUrl(str).client(build).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build().create(PostDaily.class)).post(this.requestDate, this.accessToken, this.storyline).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).flatMap(RFLPostDailyCommand$$Lambda$1.lambdaFactory$()).subscribe(RFLPostDailyCommand$$Lambda$2.lambdaFactory$(this), RFLPostDailyCommand$$Lambda$3.lambdaFactory$(this), RFLPostDailyCommand$$Lambda$4.lambdaFactory$());
    }

    static /* synthetic */ Observable lambda$execute$0(Response response) {
        if (response.isSuccessful()) {
            return Observable.just(response);
        }
        return Observable.error(new HttpException(response));
    }

    static /* synthetic */ void lambda$execute$1(RFLPostDailyCommand rFLPostDailyCommand, Response response) {
        if (rFLPostDailyCommand.rxBus.hasObservers()) {
            rFLPostDailyCommand.rxBus.send(new RFLResponseSuccess(rFLPostDailyCommand.date, rFLPostDailyCommand.timezone));
        }
    }

    static /* synthetic */ void lambda$execute$2(RFLPostDailyCommand rFLPostDailyCommand, Throwable th) {
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
            } else if (rFLPostDailyCommand.rxBus.hasObservers()) {
                rFLPostDailyCommand.rxBus.send(rFLResponseError);
            }
        }
    }

    static /* synthetic */ void lambda$execute$3() {
    }
}
