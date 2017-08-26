package jp.gmotech.smaad.video.ad;

import com.tapjoy.TJAdUnitConstants.String;
import java.io.File;
import java.io.StringReader;
import jp.gmotech.smaad.video.ad.a.h;
import jp.gmotech.smaad.video.ad.b.a;
import jp.gmotech.smaad.video.ad.b.b;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import twitter4j.HttpResponseCode;

class s implements h {
    final /* synthetic */ q a;

    s(q qVar) {
        this.a = qVar;
    }

    public void a(long j, String str, int i, String str2) {
        if (i == HttpResponseCode.OK) {
            p pVar = new p();
            pVar.a(this.a.c);
            String replace = str2.replace("&lt;", "<").replace("&gt;", ">");
            try {
                XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                newPullParser.setInput(new StringReader(replace));
                newPullParser.nextToken();
                for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.nextToken()) {
                    if (eventType == 2) {
                        replace = newPullParser.getName();
                        if ("AdId".equalsIgnoreCase(replace)) {
                            pVar.b(newPullParser.nextText());
                        } else if ("MediaFile".equalsIgnoreCase(replace)) {
                            if (newPullParser.nextToken() == 5) {
                                pVar.g(newPullParser.getText());
                            }
                        } else if ("portrait".equalsIgnoreCase(replace)) {
                            if (newPullParser.nextToken() == 5) {
                                pVar.e(newPullParser.getText());
                            }
                        } else if (String.LANDSCAPE.equalsIgnoreCase(replace)) {
                            if (newPullParser.nextToken() == 5) {
                                pVar.c(newPullParser.getText());
                            }
                        } else if ("landscape43".equalsIgnoreCase(replace)) {
                            if (newPullParser.nextToken() == 5) {
                                pVar.d(newPullParser.getText());
                            }
                        } else if ("portrait43".equalsIgnoreCase(replace)) {
                            if (newPullParser.nextToken() == 5) {
                                pVar.f(newPullParser.getText());
                            }
                        } else if ("MarketUrl".equalsIgnoreCase(replace)) {
                            if (newPullParser.nextToken() == 5) {
                                pVar.k(newPullParser.getText());
                            }
                        } else if ("ClickThrough".equalsIgnoreCase(replace)) {
                            pVar.l(newPullParser.nextText());
                        } else if ("UserPay".equalsIgnoreCase(replace)) {
                            pVar.m(newPullParser.nextText());
                        } else if ("Pay".equalsIgnoreCase(replace)) {
                            pVar.n(newPullParser.nextText());
                        } else if ("Tracking".equalsIgnoreCase(replace)) {
                            replace = newPullParser.getAttributeValue(0);
                            if (String.VIDEO_START.equalsIgnoreCase(replace)) {
                                pVar.i(newPullParser.nextText());
                            } else if (String.VIDEO_COMPLETE.equalsIgnoreCase(replace)) {
                                pVar.j(newPullParser.nextText());
                            }
                        }
                    }
                }
                if (this.a.v) {
                    this.a.p = 1;
                }
                replace = pVar.a(this.a.p);
                replace = this.a.c + replace.substring(replace.lastIndexOf("/") + 1);
                String str3 = b.a(this.a.b) + replace.split("_")[0] + replace.substring(replace.lastIndexOf("."));
                if (b.b(str3)) {
                    replace = (String) this.a.l.get(str3);
                    if (this.a.j.get(str3) == null) {
                        new File(str3).delete();
                        a.a("SmaAdVideoManager", "Mismatch between existences of video-info and video-file, no video-info");
                    } else {
                        a.a("SmaAdVideoManager", "Already video file in app");
                        return;
                    }
                }
                pVar.h(str3);
                if (!this.a.s || this.a.v) {
                    synchronized (q.u) {
                        this.a.b(pVar);
                    }
                    return;
                }
                this.a.a(pVar);
            } catch (Exception e) {
                a.a("SmaAdVideoManager", e.getMessage());
            }
        } else if (i != HttpResponseCode.NOT_FOUND) {
            a.a("SmaAdVideoManager", "[getAdInfo] http response error");
        } else if (this.a.t != null) {
            this.a.t.a(7);
        }
    }

    public void b(long j, String str, int i, String str2) {
        a.a("SmaAdVideoManager", "[getAdInfo] canceled");
    }
}
