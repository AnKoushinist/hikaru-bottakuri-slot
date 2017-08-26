package jp.reifrontier.silentlogsdkandroid.data.api;

import c.a.a;
import com.google.a.a.c;
import com.google.a.f;
import java.io.IOException;
import java.util.TimeZone;
import jp.reifrontier.silentlogsdkandroid.domain.Response.RFLResponse.RFLResponseError;
import jp.reifrontier.silentlogsdkandroid.util.RFLConstants;
import jp.reifrontier.silentlogsdkandroid.util.RFLUtils;
import jp.reifrontier.silentlogsdkandroid.util.RxBus;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
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

public class RFLAuthCommand {
    static final /* synthetic */ boolean $assertionsDisabled = (!RFLAuthCommand.class.desiredAssertionStatus());
    private String client_id;
    private String client_secret;
    private boolean debug = false;
    private String device_token;
    private String password;
    private RxBus rxBus;
    private String username;

    public interface Auth {
        @POST("{auth}")
        @FormUrlEncoded
        Observable<Response<AuthResponse>> postA(@Path("auth") String str, @Field("client_id") String str2, @Field("client_secret") String str3, @Field("device_token") String str4, @Field("platform") String str5, @Field("time_zone") String str6, @Field("lang") String str7, @Field("username") String str8, @Field("password") String str9);
    }

    public static class AuthResponse {
        @c(a = "access_token")
        public final String token;
        @c(a = "user_id")
        public final String user_id;

        public AuthResponse(String str, String str2) {
            this.token = str;
            this.user_id = str2;
        }
    }

    public RFLAuthCommand(RxBus rxBus, String str, String str2, String str3, String str4, String str5, boolean z) {
        this.rxBus = rxBus;
        this.device_token = str;
        this.client_id = str2;
        this.client_secret = str3;
        this.username = str4;
        this.password = str5;
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
        ((Auth) new Retrofit.Builder().baseUrl(str).client(build).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build().create(Auth.class)).postA(RFLConstants.kRFLAPI_H1_URL_Authorize, this.client_id, this.client_secret, this.device_token, RFLConstants.kRFLAPI_H1_Parameter_Platform_AND, TimeZone.getDefault().getID(), RFLUtils.getLang(), this.username, this.password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).flatMap(RFLAuthCommand$$Lambda$1.lambdaFactory$()).subscribe(RFLAuthCommand$$Lambda$2.lambdaFactory$(this), RFLAuthCommand$$Lambda$3.lambdaFactory$(this), RFLAuthCommand$$Lambda$4.lambdaFactory$());
    }

    static /* synthetic */ Observable lambda$execute$0(Response response) {
        if (response.isSuccessful()) {
            return Observable.just(response);
        }
        return Observable.error(new HttpException(response));
    }

    static /* synthetic */ void lambda$execute$1(RFLAuthCommand rFLAuthCommand, Response response) {
        if (rFLAuthCommand.rxBus.hasObservers()) {
            rFLAuthCommand.rxBus.send(response.body());
        }
    }

    static /* synthetic */ void lambda$execute$2(RFLAuthCommand rFLAuthCommand, Throwable th) {
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
            } else if (rFLAuthCommand.rxBus.hasObservers()) {
                rFLAuthCommand.rxBus.send(rFLResponseError);
            }
        }
    }

    static /* synthetic */ void lambda$execute$3() {
    }
}
