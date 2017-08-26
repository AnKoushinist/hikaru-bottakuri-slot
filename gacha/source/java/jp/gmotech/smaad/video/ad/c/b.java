package jp.gmotech.smaad.video.ad.c;

public enum b {
    SMAAD_VIDEO_AD("https://media.smaad.net/video/load_ad"),
    SMAAD_VIDEO_PING("https://media.smaad.net/video/ping");
    
    private final String c;

    private b(String str) {
        this.c = str;
    }

    public String a() {
        return this.c;
    }
}
