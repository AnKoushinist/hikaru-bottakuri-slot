package jp.tjkapp.adfurikunsdk.moviereward;

import android.app.Activity;
import android.util.Log;
import com.unity3d.player.UnityPlayer;
import java.util.HashMap;

public class AdfurikunMovieRewardController {
    private static Activity a = null;
    private static HashMap<String, AdfurikunMovieReward> b = new HashMap();

    private AdfurikunMovieRewardController() {
    }

    public static void initialize(final Activity activity, final String str) {
        a = activity;
        a.runOnUiThread(new Runnable() {
            public void run() {
                AdfurikunMovieReward adfurikunMovieReward = new AdfurikunMovieReward(str, activity);
                AdfurikunMovieRewardController.b.put(str, adfurikunMovieReward);
                adfurikunMovieReward.setAdfurikunMovieRewardListener(new AdfurikunMovieRewardListener(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void onPrepareSuccess() {
                        UnityPlayer.UnitySendMessage("AdfurikunMovieRewardUtility", "MovieRewardCallback", AdfurikunMovieRewardController.b("PrepareSuccess", str, "Not notify"));
                    }

                    public void onStartPlaying(final MovieRewardData movieRewardData) {
                        AdfurikunMovieRewardController.a.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public void run() {
                                UnityPlayer.UnitySendMessage("AdfurikunMovieRewardUtility", "MovieRewardCallback", AdfurikunMovieRewardController.b("StartPlaying", str, movieRewardData.adnetworkKey));
                            }
                        });
                    }

                    public void onFinishedPlaying(final MovieRewardData movieRewardData) {
                        AdfurikunMovieRewardController.a.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public void run() {
                                UnityPlayer.UnitySendMessage("AdfurikunMovieRewardUtility", "MovieRewardCallback", AdfurikunMovieRewardController.b("FinishedPlaying", str, movieRewardData.adnetworkKey));
                            }
                        });
                    }

                    public void onFailedPlaying(final MovieRewardData movieRewardData) {
                        AdfurikunMovieRewardController.a.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public void run() {
                                UnityPlayer.UnitySendMessage("AdfurikunMovieRewardUtility", "MovieRewardCallback", AdfurikunMovieRewardController.b("FailedPlaying", str, movieRewardData.adnetworkKey));
                            }
                        });
                    }

                    public void onAdClose(final MovieRewardData movieRewardData) {
                        AdfurikunMovieRewardController.a.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public void run() {
                                UnityPlayer.UnitySendMessage("AdfurikunMovieRewardUtility", "MovieRewardCallback", AdfurikunMovieRewardController.b("AdClose", str, movieRewardData.adnetworkKey));
                            }
                        });
                    }
                });
                AdfurikunMovieRewardController.a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (((AdfurikunMovieReward) AdfurikunMovieRewardController.b.get(str)) != null) {
                            AdfurikunMovieRewardController.onResume();
                        }
                    }
                });
            }
        });
    }

    public static boolean isPrepared(String str) {
        if (b.containsKey(str)) {
            return ((AdfurikunMovieReward) b.get(str)).isPrepared();
        }
        return false;
    }

    public static void play(final String str) {
        if (b.containsKey(str)) {
            a.runOnUiThread(new Runnable() {
                public void run() {
                    ((AdfurikunMovieReward) AdfurikunMovieRewardController.b.get(str)).play();
                }
            });
        }
    }

    public static void onResume() {
        if (!b.isEmpty()) {
            a.runOnUiThread(new Runnable() {
                public void run() {
                    for (AdfurikunMovieReward adfurikunMovieReward : AdfurikunMovieRewardController.b.values()) {
                        if (adfurikunMovieReward != null) {
                            adfurikunMovieReward.onStart();
                            adfurikunMovieReward.onResume();
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
                    for (AdfurikunMovieReward adfurikunMovieReward : AdfurikunMovieRewardController.b.values()) {
                        if (adfurikunMovieReward != null) {
                            adfurikunMovieReward.onPause();
                            adfurikunMovieReward.onStop();
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
                    for (AdfurikunMovieReward adfurikunMovieReward : AdfurikunMovieRewardController.b.values()) {
                        if (adfurikunMovieReward != null) {
                            adfurikunMovieReward.onDestroy();
                        }
                    }
                    AdfurikunMovieRewardController.b.clear();
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
