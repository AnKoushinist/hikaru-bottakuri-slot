package com.vungle.publisher.inject;

import dagger.Module;

@Module
/* compiled from: vungle */
public class EndpointModule {
    public String a = "https://api.vungle.com/api/v4/";
    public String b = "https://ingest.vungle.com/";

    public EndpointModule setVungleBaseUrl(String str) {
        this.a = str;
        return this;
    }

    public EndpointModule setIngestBaseUrl(String str) {
        this.b = str;
        return this;
    }
}
