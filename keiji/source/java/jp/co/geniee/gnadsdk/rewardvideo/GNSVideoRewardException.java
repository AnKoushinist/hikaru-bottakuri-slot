package jp.co.geniee.gnadsdk.rewardvideo;

public class GNSVideoRewardException extends Exception {
    private String a;
    private String b;
    private int c;

    public GNSVideoRewardException(String str, int i) {
        this.a = str;
        this.c = i;
        this.b = a(i);
    }

    public GNSVideoRewardException(String str, int i, String str2) {
        this.a = str;
        this.c = i;
        this.b = str2;
    }

    public String a() {
        return this.a;
    }

    public String getMessage() {
        return this.b;
    }

    public int b() {
        return this.c;
    }

    private String a(int i) {
        if (i == 10401) {
            return "Network error.";
        }
        if (i == 10501) {
            return "Could not get Zone information.";
        }
        if (i == 10502) {
            return "Could not get Zone Adsource.";
        }
        if (i == 10531) {
            return "Video ad playback limit exceeded.";
        }
        if (i == 10511) {
            return "Video ad is not ready.";
        }
        if (i == 10521) {
            return "You are already playing.";
        }
        if (i == 10541) {
            return "Ad could not be acquired due to ad distribution ratio.";
        }
        if (i == 80001) {
            return "Failed to initialize ADNW.";
        }
        if (i == 80011) {
            return "ADNW stock outage occurred.";
        }
        if (i == 80021) {
            return "ADNW video ad playback failed.";
        }
        if (i == 80031) {
            return "ADNW video ad playback ended failing.";
        }
        if (i == 80041) {
            return "I failed to grant ADNW's reward to you.";
        }
        if (i == 81001) {
            return "ADNW could not be loaded within a certain period of time.";
        }
        if (i == 89001) {
            return "An error occurred in ADNW.";
        }
        if (i == 90001) {
            return "An error occurred.";
        }
        return "An exception error occurred.";
    }
}
