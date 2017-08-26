package com.glossomads.b;

public class b extends Exception {
    public static int a = 0;
    public static int b = 1;
    public static int c = 2;
    public static int d = 3;
    public static int e = 4;
    public static int f = 5;
    public static int g = 6;
    public static int h = 7;
    private String i;
    private String j;
    private int k;

    public b(int i) {
        this.k = i;
        if (this.k == a) {
            this.i = "ads are not ready for zone";
        } else if (this.k == b) {
            this.i = "can't access url on an end card.";
        } else if (this.k == c) {
            this.i = "network is offline";
        } else if (this.k == d) {
            this.i = "movie file can't be played.";
        } else if (this.k == e) {
            this.i = "can not display the end card because it does not finish loading webView.";
        } else if (this.k == f) {
            this.i = "can't access on an store url.";
        } else if (this.k == g) {
            this.i = "file is not movie file.";
        } else if (this.k == h) {
            this.i = "local file is still not downloaded.";
        }
    }

    public b(int i, String str) {
        this.k = i;
        this.j = str;
        if (this.k == b) {
            this.i = "can't access url on an end card.";
        } else if (this.k == e) {
            this.i = "can not display the end card because it does not finish loading webView.";
        } else if (this.k == f) {
            this.i = "can't access on an store url.";
        }
    }

    public String a() {
        return this.i;
    }

    public int b() {
        return this.k;
    }

    public String c() {
        return this.j;
    }
}
