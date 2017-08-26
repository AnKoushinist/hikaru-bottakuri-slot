package com.tapjoy;

import com.tapjoy.internal.ct;
import java.io.Serializable;

public class TJPlacementData implements Serializable {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private int f;
    private String g;
    private int h;
    private boolean i;
    private String j;
    private boolean k;
    private String l;
    private String m;
    private boolean n = true;
    private boolean o = false;

    public TJPlacementData(String str, String str2) {
        setKey(str);
        updateUrl(str2);
    }

    public TJPlacementData(String str, String str2, String str3) {
        setBaseURL(str);
        setHttpResponse(str2);
        this.l = str3;
        this.n = false;
    }

    public void resetPlacementRequestData() {
        setHttpResponse(null);
        setHttpStatusCode(0);
        setRedirectURL(null);
        setHasProgressSpinner(false);
        setPrerenderingRequested(false);
        setPreloadDisabled(false);
        setContentViewId(null);
    }

    public String getCallbackID() {
        return this.l;
    }

    public boolean isBaseActivity() {
        return this.n;
    }

    public void setKey(String str) {
        this.a = str;
    }

    public void setBaseURL(String str) {
        this.c = str;
    }

    public void setMediationURL(String str) {
        this.d = str;
    }

    public void setHttpResponse(String str) {
        this.e = str;
    }

    public void setHttpStatusCode(int i) {
        this.f = i;
    }

    public void setPlacementName(String str) {
        this.g = str;
    }

    public void setViewType(int i) {
        this.h = i;
    }

    public void setRedirectURL(String str) {
        this.j = str;
    }

    public void setHasProgressSpinner(boolean z) {
        this.i = z;
    }

    public void setContentViewId(String str) {
        this.m = str;
    }

    public String getUrl() {
        return this.b;
    }

    public String getKey() {
        return this.a;
    }

    public String getBaseURL() {
        return this.c;
    }

    public String getMediationURL() {
        return this.d;
    }

    public String getHttpResponse() {
        return this.e;
    }

    public int getHttpStatusCode() {
        return this.f;
    }

    public String getPlacementName() {
        return this.g;
    }

    public int getViewType() {
        return this.h;
    }

    public String getRedirectURL() {
        return this.j;
    }

    public String getContentViewId() {
        return this.m;
    }

    public boolean hasProgressSpinner() {
        return this.i;
    }

    public void setPreloadDisabled(boolean z) {
        this.o = z;
    }

    public boolean isPreloadDisabled() {
        return this.o;
    }

    public boolean isPrerenderingRequested() {
        return this.k;
    }

    public void setPrerenderingRequested(boolean z) {
        this.k = z;
    }

    public void updateUrl(String str) {
        this.b = str;
        if (!ct.c(str)) {
            setBaseURL(str.substring(0, str.indexOf(47, str.indexOf("//") + 3)));
        }
    }
}
