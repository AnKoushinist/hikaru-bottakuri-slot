package jp.tjkapp.adfurikunsdk.moviereward;

import android.app.Activity;
import android.util.Log;
import com.unity3d.player.UnityPlayer;
import java.util.HashMap;

public class AdfurikunMovieInterController {
    private static Activity a = null;
    private static HashMap<String, AdfurikunMovieInter> b = new HashMap();

    private AdfurikunMovieInterController() {
    }

    public static void initialize(final Activity activity, final String str) {
        a = activity;
        a.runOnUiThread(new Runnable() {
            public void run() {
                AdfurikunMovieInter adfurikunMovieInter = new AdfurikunMovieInter(str, activity);
                AdfurikunMovieInterController.b.put(str, adfurikunMovieInter);
                adfurikunMovieInter.setAdfurikunMovieInterListener(new AdfurikunMovieInterListener(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void onPrepareSuccess() {
                        UnityPlayer.UnitySendMessage("AdfurikunMovieInterstitialUtility", "MovieInterstitialCallback", AdfurikunMovieInterController.b("PrepareSuccess", str, "Not notify"));
                    }

                    public void onStartPlaying(final MovieInterData movieInterData) {
                        AdfurikunMovieInterController.a.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public void run() {
                                UnityPlayer.UnitySendMessage("AdfurikunMovieInterstitialUtility", "MovieInterstitialCallback", AdfurikunMovieInterController.b("StartPlaying", str, movieInterData.adnetworkKey));
                            }
                        });
                    }

                    public void onFinishedPlaying(final MovieInterData movieInterData) {
                        AdfurikunMovieInterController.a.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public void run() {
                                UnityPlayer.UnitySendMessage("AdfurikunMovieInterstitialUtility", "MovieInterstitialCallback", AdfurikunMovieInterController.b("FinishedPlaying", str, movieInterData.adnetworkKey));
                            }
                        });
                    }

                    public void onFailedPlaying(final MovieInterData movieInterData) {
                        AdfurikunMovieInterController.a.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public void run() {
                                UnityPlayer.UnitySendMessage("AdfurikunMovieInterstitialUtility", "MovieInterstitialCallback", AdfurikunMovieInterController.b("FailedPlaying", str, movieInterData.adnetworkKey));
                            }
                        });
                    }

                    public void onAdClose(final MovieInterData movieInterData) {
                        AdfurikunMovieInterController.a.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public void run() {
                                UnityPlayer.UnitySendMessage("AdfurikunMovieInterstitialUtility", "MovieInterstitialCallback", AdfurikunMovieInterController.b("AdClose", str, movieInterData.adnetworkKey));
                            }
                        });
                    }
                });
                AdfurikunMovieInterController.a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (((AdfurikunMovieInter) AdfurikunMovieInterController.b.get(str)) != null) {
                            AdfurikunMovieInterController.onResume();
                        }
                    }
                });
            }
        });
    }

    public static boolean isPrepared(String str) {
        if (b.containsKey(str)) {
            return ((AdfurikunMovieInter) b.get(str)).isPrepared();
        }
        return false;
    }

    public static void play(final String str) {
        if (b.containsKey(str)) {
            a.runOnUiThread(new Runnable() {
                public void run() {
                    ((AdfurikunMovieInter) AdfurikunMovieInterController.b.get(str)).play();
                }
            });
        }
    }

    public static void onResume() {
        if (!b.isEmpty()) {
            a.runOnUiThread(new Runnable() {
                public void run() {
                    for (AdfurikunMovieInter adfurikunMovieInter : AdfurikunMovieInterController.b.values()) {
                        if (adfurikunMovieInter != null) {
                            adfurikunMovieInter.onStart();
                            adfurikunMovieInter.onResume();
                        }
                    }
                }
            });
        }
    }

    public static void onPause() {
        if (!b.isEmpty()) {
            a.runOnUiThread(new Runnable() {
                public void run() {
                    for (AdfurikunMovieInter adfurikunMovieInter : AdfurikunMovieInterController.b.values()) {
                        if (adfurikunMovieInter != null) {
                            adfurikunMovieInter.onPause();
                            adfurikunMovieInter.onStop();
                        }
                    }
                }
            });
        }
    }

    public static void onDestroy() {
        if (!b.isEmpty()) {
            a.runOnUiThread(new Runnable() {
                public void run() {
                    for (AdfurikunMovieInter adfurikunMovieInter : AdfurikunMovieInterController.b.values()) {
                        if (adfurikunMovieInter != null) {
                            adfurikunMovieInter.onDestroy();
                        }
                    }
                    AdfurikunMovieInterController.b.clear();
                }
            });
        }
    }

    public static void mes(String str) {
        Log.d("test", str);
    }

    private static String b(String str, String str2, String str3) {
        return "stateName:" + str + ";appID:" + str2 + ";adnetworkKey:" + str3 + ";";
    }
}
