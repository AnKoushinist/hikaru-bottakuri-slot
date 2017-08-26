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

public final class RFLAccountCreateCommand {
    static final /* synthetic */ boolean $assertionsDisabled = (!RFLAccountCreateCommand.class.desiredAssertionStatus());
    private String birthday;
    private String client_id;
    private String client_secret;
    private boolean debug = false;
    private String device_token;
    private String extra_key;
    private String gender;
    private String install_date;
    private String password;
    private String private_key;
    private RxBus rxBus;
    private String username;

    public interface AccountCreate {
        @POST("{auth}/{create}")
        @FormUrlEncoded
        Observable<Response<AccountCreateResponse>> postA(@Path("auth") String str, @Path("create") String str2, @Field("client_id") String str3, @Field("client_secret") String str4, @Field("device_token") String str5, @Field("platform") String str6, @Field("time_zone") String str7, @Field("lang") String str8, @Field("username") String str9, @Field("password") String str10, @Field("gender") String str11, @Field("birthday") String str12, @Field("install_date") String str13, @Field("private_key") String str14, @Field("extra_key") String str15);
    }

    public static class AccountCreateResponse {
        @c(a = "access_token")
        public final String token;
        @c(a = "user_id")
        public final String user_id;

        public AccountCreateResponse(String str, String str2) {
            this.token = str;
            this.user_id = str2;
        }
    }

    public RFLAccountCreateCommand(RxBus rxBus, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, boolean z) {
        this.rxBus = rxBus;
        this.device_token = str;
        this.username = str2;
        this.password = str3;
        this.gender = str4;
        this.birthday = str5;
        this.install_date = str6;
        this.private_key = str7;
        this.extra_key = str8;
        this.client_id = str9;
        this.client_secret = str10;
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
        ((AccountCreate) new Retrofit.Builder().baseUrl(str).client(build).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build().create(AccountCreate.class)).postA(RFLConstants.kRFLAPI_H1_URL_Authorize, RFLConstants.kRFLAPI_H1_URL_Account_Create, this.client_id, this.client_secret, this.device_token, RFLConstants.kRFLAPI_H1_Parameter_Platform_AND, TimeZone.getDefault().getID(), RFLUtils.getLang(), this.username, this.password, this.gender, this.birthday, this.install_date, this.private_key, this.extra_key).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).flatMap(RFLAccountCreateCommand$$Lambda$1.lambdaFactory$()).subscribe(RFLAccountCreateCommand$$Lambda$4.lambdaFactory$(this), RFLAccountCreateCommand$$Lambda$5.lambdaFactory$(this), RFLAccountCreateCommand$$Lambda$6.lambdaFactory$());
    }

    static /* synthetic */ Observable lambda$execute$0(Response response) {
        if (response.isSuccessful()) {
            return Observable.just(response);
        }
        return Observable.error(new HttpException(response));
    }

    static /* synthetic */ void lambda$execute$1(RFLAccountCreateCommand rFLAccountCreateCommand, Response response) {
        if (rFLAccountCreateCommand.rxBus.hasObservers()) {
            rFLAccountCreateCommand.rxBus.send(response.body());
        }
    }

    static /* synthetic */ void lambda$execute$2(RFLAccountCreateCommand rFLAccountCreateCommand, Throwable th) {
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
            } else if (rFLAccountCreateCommand.rxBus.hasObservers()) {
                rFLAccountCreateCommand.rxBus.send(rFLResponseError);
            }
        }
    }

    static /* synthetic */ void lambda$execute$3() {
    }
}
