package jp.co.vaz.creator.share.twitter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import java.io.File;
import jp.co.vaz.creator.hikaru2.AppActivity;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TwitterOAuthActivity extends Activity {
    private static String c;
    private static String d;
    private Twitter a;
    private RequestToken b;
    private boolean e;
    private boolean f;

    public static void a(String str, String str2) {
        c = str;
        d = str2;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setVisible(false);
        this.a = a.a(this);
        this.e = false;
        this.f = true;
        c();
    }

    private void c() {
        new AsyncTask<Void, Void, String>(this) {
            final /* synthetic */ TwitterOAuthActivity a;

            {
                this.a = r1;
            }

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return a((Void[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                a((String) obj);
            }

            protected String a(Void... voidArr) {
                try {
                    this.a.b = this.a.a.getOAuthRequestToken("vazgametw://twitter");
                    return this.a.b.getAuthorizationURL();
                } catch (TwitterException e) {
                    Log.d(TwitterOAuthActivity.class.getSimpleName(), e.getMessage());
                    AppActivity.showToast("\u8a8d\u8a3c\u30da\u30fc\u30b8\u306b\u9077\u79fb\u3067\u304d\u307e\u305b\u3093\u3067\u3057\u305f");
                    this.a.d();
                    return null;
                }
            }

            protected void a(String str) {
                if (str != null) {
                    this.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                } else {
                    Log.d(TwitterOAuthActivity.class.getSimpleName(), "Couldn't get twitter oauth url, oauth is failed");
                }
            }
        }.execute(new Void[0]);
    }

    public void onNewIntent(Intent intent) {
        if (intent != null && intent.getData() != null && intent.getData().toString().startsWith("vazgametw://twitter")) {
            this.e = true;
            String queryParameter = intent.getData().getQueryParameter("oauth_verifier");
            new AsyncTask<String, Void, AccessToken>(this) {
                final /* synthetic */ TwitterOAuthActivity a;

                {
                    this.a = r1;
                }

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return a((String[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    a((AccessToken) obj);
                }

                protected AccessToken a(String... strArr) {
                    AccessToken accessToken = null;
                    if (strArr[0] != null) {
                        try {
                            accessToken = this.a.a.getOAuthAccessToken(this.a.b, strArr[0]);
                        } catch (TwitterException e) {
                            Log.d(TwitterOAuthActivity.class.getSimpleName(), e.getMessage());
                        }
                    }
                    return accessToken;
                }

                protected void a(AccessToken accessToken) {
                    this.a.a(accessToken);
                }
            }.execute(new String[]{queryParameter});
        }
    }

    private void a(AccessToken accessToken) {
        if (accessToken != null) {
            a.a(this, accessToken);
            e();
            return;
        }
        d();
    }

    private void d() {
        startActivity(new Intent(this, AppActivity.class));
        finish();
    }

    private void e() {
        new AsyncTask<Void, Void, Boolean>(this) {
            final /* synthetic */ TwitterOAuthActivity a;

            {
                this.a = r1;
            }

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return a((Void[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                a((Boolean) obj);
            }

            protected Boolean a(Void... voidArr) {
                try {
                    AppActivity.onShowIndicator(AppActivity.shareIndicator);
                    StatusUpdate statusUpdate = new StatusUpdate(TwitterOAuthActivity.c);
                    File file = new File(TwitterOAuthActivity.d);
                    if (file.exists()) {
                        statusUpdate.media(file);
                    } else {
                        statusUpdate.media(null);
                    }
                    this.a.a.updateStatus(statusUpdate);
                    return Boolean.valueOf(true);
                } catch (TwitterException e) {
                    e.printStackTrace();
                    return Boolean.valueOf(false);
                }
            }

            protected void a(Boolean bool) {
                AppActivity.onHideIndicator();
                if (bool.booleanValue()) {
                    AppActivity.showToast("\u30c4\u30a4\u30fc\u30c8\u5b8c\u4e86");
                    AppActivity.onShareApp();
                } else {
                    AppActivity.showToast("\u30c4\u30a4\u30fc\u30c8\u5931\u6557");
                }
                this.a.d();
            }
        }.execute(new Void[0]);
    }

    protected void onResume() {
        super.onResume();
        if (!(this.e || this.f)) {
            this.e = false;
            d();
        }
        this.f = false;
    }
}
