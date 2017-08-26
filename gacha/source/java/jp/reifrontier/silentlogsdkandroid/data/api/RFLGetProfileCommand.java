package jp.reifrontier.silentlogsdkandroid.data.api;

import c.a.a;
import com.google.a.a.c;
import com.google.a.f;
import java.io.IOException;
import java.util.List;
import jp.reifrontier.silentlogsdkandroid.domain.Response.RFLResponse.RFLResponseError;
import jp.reifrontier.silentlogsdkandroid.util.RFLConstants;
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
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RFLGetProfileCommand {
    static final /* synthetic */ boolean $assertionsDisabled = (!RFLGetProfileCommand.class.desiredAssertionStatus());
    private String accessToken;
    private boolean debug = false;
    private RxBus rxBus;

    public interface GetProfile {
        @GET("user/profile")
        Observable<Response<GetProfileResponse>> get(@Query("access_token") String str, @Query("private_key") String str2);
    }

    public static class GetProfileResponse {
        @c(a = "private")
        public final PrivateResponse privateKey;
        @c(a = "profile")
        public final ProfileResponse profile;
        @c(a = "userId")
        public final String userId;
        @c(a = "username")
        public final String username;

        public GetProfileResponse(String str, ProfileResponse profileResponse, String str2, PrivateResponse privateResponse) {
            this.username = str;
            this.profile = profileResponse;
            this.userId = str2;
            this.privateKey = privateResponse;
        }
    }

    public static class PrivateResponse {
        @c(a = "idfas")
        public final List<String> idfas;
        @c(a = "last_read_date")
        public final String last_read_date;
        @c(a = "payment_info")
        public final String payment_info;
        @c(a = "premium_limit")
        public final String premium_limit;
        @c(a = "regist_user")
        public final boolean regist_user;

        public PrivateResponse(String str, String str2, boolean z, String str3, List<String> list) {
            this.last_read_date = str;
            this.premium_limit = str2;
            this.regist_user = z;
            this.payment_info = str3;
            this.idfas = list;
        }
    }

    public static class ProfileResponse {
        @c(a = "birthday")
        public final String birthday;
        @c(a = "firstDate")
        public final String firstDate;
        @c(a = "gender")
        public final String gender;

        public ProfileResponse(String str, String str2, String str3) {
            this.firstDate = str;
            this.gender = str2;
            this.birthday = str3;
        }
    }

    public RFLGetProfileCommand(RxBus rxBus, String str, boolean z) {
        this.rxBus = rxBus;
        this.accessToken = str;
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
        ((GetProfile) new Retrofit.Builder().baseUrl(str).client(build).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build().create(GetProfile.class)).get(this.accessToken, RFLConstants.kRFLAPI_H1_Parameter_Private).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).flatMap(RFLGetProfileCommand$$Lambda$1.lambdaFactory$()).subscribe(RFLGetProfileCommand$$Lambda$2.lambdaFactory$(this), RFLGetProfileCommand$$Lambda$3.lambdaFactory$(this), RFLGetProfileCommand$$Lambda$4.lambdaFactory$());
    }

    static /* synthetic */ Observable lambda$execute$0(Response response) {
        if (response.isSuccessful()) {
            return Observable.just(response);
        }
        return Observable.error(new HttpException(response));
    }

    static /* synthetic */ void lambda$execute$1(RFLGetProfileCommand rFLGetProfileCommand, Response response) {
        if (rFLGetProfileCommand.rxBus.hasObservers()) {
            rFLGetProfileCommand.rxBus.send(response.body());
        }
    }

    static /* synthetic */ void lambda$execute$2(RFLGetProfileCommand rFLGetProfileCommand, Throwable th) {
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
            } else if (rFLGetProfileCommand.rxBus.hasObservers()) {
                rFLGetProfileCommand.rxBus.send(rFLResponseError);
            }
        }
    }

    static /* synthetic */ void lambda$execute$3() {
    }
}
