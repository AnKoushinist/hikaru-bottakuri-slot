package jp.reifrontier.silentlogsdkandroid.data.api;

import c.a.a;
import com.google.a.f;
import java.io.IOException;
import jp.reifrontier.silentlogsdkandroid.domain.Event.RFLEventResponse;
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

public class RFLEventCommand {
    static final /* synthetic */ boolean $assertionsDisabled = (!RFLEventCommand.class.desiredAssertionStatus());
    private String accessToken;
    private boolean debug = false;
    private RxBus rxBus;

    public interface Event {
        @GET("event/setting")
        Observable<Response<RFLEventResponse>> get(@Query("access_token") String str);
    }

    public RFLEventCommand(RxBus rxBus, String str, boolean z) {
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
        ((Event) new Retrofit.Builder().baseUrl(str).client(build).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build().create(Event.class)).get(this.accessToken).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).flatMap(RFLEventCommand$$Lambda$1.lambdaFactory$()).subscribe(RFLEventCommand$$Lambda$2.lambdaFactory$(this), RFLEventCommand$$Lambda$3.lambdaFactory$(this), RFLEventCommand$$Lambda$4.lambdaFactory$());
    }

    static /* synthetic */ Observable lambda$execute$0(Response response) {
        if (response.isSuccessful()) {
            return Observable.just(response);
        }
        return Observable.error(new HttpException(response));
    }

    static /* synthetic */ void lambda$execute$1(RFLEventCommand rFLEventCommand, Response response) {
        if (rFLEventCommand.rxBus.hasObservers()) {
            rFLEventCommand.rxBus.send(response.body());
        }
    }

    static /* synthetic */ void lambda$execute$2(RFLEventCommand rFLEventCommand, Throwable th) {
        a.b("error:%s", th.getMessage());
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
            } else if (rFLEventCommand.rxBus.hasObservers()) {
                rFLEventCommand.rxBus.send(rFLResponseError);
            }
        }
    }

    static /* synthetic */ void lambda$execute$3() {
    }
}
