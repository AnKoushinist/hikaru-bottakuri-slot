package jp.co.vaz.creator.share.twitter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.util.Log;
import java.io.File;
import jp.co.vaz.creator.hikaru2.AppActivity;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.HttpResponseCode;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/* compiled from: TwitterUtils */
public class a {
    public static void a(final String str, final String str2, final AppActivity appActivity) {
        if (d(appActivity)) {
            AppActivity.onShowIndicator(AppActivity.shareIndicator);
            new AsyncTask<String, Void, Integer>() {
                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return a((String[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    a((Integer) obj);
                }

                protected Integer a(String... strArr) {
                    try {
                        StatusUpdate statusUpdate = new StatusUpdate(strArr[0]);
                        File file = new File(str2);
                        if (file.exists()) {
                            statusUpdate.media(file);
                        } else {
                            statusUpdate.media(null);
                        }
                        a.a(appActivity).updateStatus(statusUpdate);
                        return Integer.valueOf(HttpResponseCode.OK);
                    } catch (TwitterException e) {
                        e.printStackTrace();
                        return Integer.valueOf(e.getErrorCode());
                    }
                }

                protected void a(Integer num) {
                    AppActivity appActivity = appActivity;
                    AppActivity.onHideIndicator();
                    Log.d("Twitter\u30b7\u30a7\u30a2", "\u30a8\u30e9\u30fc\u30b3\u30fc\u30c9: " + String.valueOf(num));
                    switch (num.intValue()) {
                        case R.styleable.AppCompatTheme_colorButtonNormal /*89*/:
                            a.c(appActivity);
                            Intent intent = new Intent(appActivity, TwitterOAuthActivity.class);
                            TwitterOAuthActivity.a(str, str2);
                            appActivity.startActivity(intent);
                            return;
                        case 187:
                            appActivity = appActivity;
                            AppActivity.showToast("\u540c\u3058\u6587\u9762\u306f\u30c4\u30a4\u30fc\u30c8\u3067\u304d\u307e\u305b\u3093");
                            return;
                        case HttpResponseCode.OK /*200*/:
                            appActivity = appActivity;
                            AppActivity.onShareApp();
                            appActivity = appActivity;
                            AppActivity.showToast("\u30c4\u30a4\u30fc\u30c8\u5b8c\u4e86");
                            return;
                        default:
                            appActivity = appActivity;
                            AppActivity.showToast("\u30c4\u30a4\u30fc\u30c8\u5931\u6557");
                            return;
                    }
                }
            }.execute(new String[]{str});
            return;
        }
        Intent intent = new Intent(appActivity, TwitterOAuthActivity.class);
        TwitterOAuthActivity.a(str, str2);
        appActivity.startActivity(intent);
    }

    public static Twitter a(Context context) {
        Twitter instance = new TwitterFactory().getInstance();
        instance.setOAuthConsumer("4tJ8SE7ZMn9MrsTtuqFsOjugs", "UkW5lmZfVA0CeHdBJL3gegkmi5m8Sl04jXssUqaGu9cQF2BqDL");
        instance.setOAuthAccessToken(null);
        if (d(context)) {
            instance.setOAuthAccessToken(b(context));
        }
        return instance;
    }

    public static void a(Context context, AccessToken accessToken) {
        Editor edit = context.getSharedPreferences("twitter_access_token", 0).edit();
        edit.putString("token", accessToken.getToken());
        edit.putString("token_secret", accessToken.getTokenSecret());
        edit.commit();
    }

    public static AccessToken b(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("twitter_access_token", 0);
        String string = sharedPreferences.getString("token", null);
        String string2 = sharedPreferences.getString("token_secret", null);
        if (string == null || string2 == null) {
            return null;
        }
        return new AccessToken(string, string2);
    }

    public static void c(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("twitter_access_token", 0);
        String string = sharedPreferences.getString("token", null);
        String string2 = sharedPreferences.getString("token_secret", null);
        if (string != null && string2 != null) {
            sharedPreferences.edit().clear().commit();
        }
    }

    public static boolean d(Context context) {
        Log.d(a.class.getSimpleName(), "method is called that hasAccessToken");
        return b(context) != null;
    }
}
