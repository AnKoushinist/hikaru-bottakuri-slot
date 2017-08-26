package jp.tjkapp.adfurikunsdk.moviereward;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONObject;

class AdInfo {
    public static final int ADTYPE_BANNER = 0;
    public static final int ADTYPE_INTERSTITIAL = 1;
    public static final int ADTYPE_NATIVE = 3;
    public static final int ADTYPE_OTHER = -1;
    public static final int ADTYPE_WALL = 2;
    public static final int AD_CHECK = 0;
    public static final int[] BANNER_KIND_BANNER = new int[]{WALL_TYPE_NONE, WALL_TYPE_URL, WALL_TYPE_HTML, BANNER_KIND_BANNER4, BANNER_KIND_BANNER5, BANNER_KIND_BANNER6, BANNER_KIND_BANNER7, BANNER_KIND_BANNER8};
    public static final int BANNER_KIND_BANNER1 = 0;
    public static final int BANNER_KIND_BANNER2 = 1;
    public static final int BANNER_KIND_BANNER3 = 3;
    public static final int BANNER_KIND_BANNER4 = 4;
    public static final int BANNER_KIND_BANNER5 = 5;
    public static final int BANNER_KIND_BANNER6 = 6;
    public static final int BANNER_KIND_BANNER7 = 7;
    public static final int BANNER_KIND_BANNER8 = 8;
    public static final int[] BANNER_KIND_INTERSTITIAL;
    public static final int BANNER_KIND_INTERSTITIAL1 = 9;
    public static final int[] BANNER_KIND_NATIVE;
    public static final int BANNER_KIND_NATIVE1 = 11;
    public static final int BANNER_KIND_UNKNOWN = -1;
    public static final int[] BANNER_KIND_WALL;
    public static final int BANNER_KIND_WALL1 = 10;
    public static final int NO_AD_CHECK = 1;
    public static final int WALL_TYPE_API = 2;
    public static final int WALL_TYPE_HTML = 3;
    public static final int WALL_TYPE_NONE = 0;
    public static final int WALL_TYPE_URL = 1;
    private long a;
    public ArrayList<AdInfoDetail> adInfoDetailArray;
    private HashMap<String, Integer> b;
    public int bannerKind;
    public String bgColor;
    private Random c;
    public long cycleTime;
    private int d;
    public boolean transAnimOff;

    static {
        int[] iArr = new int[WALL_TYPE_URL];
        iArr[WALL_TYPE_NONE] = BANNER_KIND_INTERSTITIAL1;
        BANNER_KIND_INTERSTITIAL = iArr;
        iArr = new int[WALL_TYPE_URL];
        iArr[WALL_TYPE_NONE] = BANNER_KIND_WALL1;
        BANNER_KIND_WALL = iArr;
        iArr = new int[WALL_TYPE_URL];
        iArr[WALL_TYPE_NONE] = BANNER_KIND_NATIVE1;
        BANNER_KIND_NATIVE = iArr;
    }

    public AdInfo() {
        this(null);
    }

    public AdInfo(AdInfo adInfo) {
        b();
        if (adInfo != null) {
            toCopy(adInfo);
        }
    }

    private void b() {
        this.a = 0;
        this.cycleTime = 30;
        this.bannerKind = BANNER_KIND_UNKNOWN;
        this.bgColor = "ffffff";
        this.transAnimOff = false;
        this.adInfoDetailArray = new ArrayList();
        this.b = new HashMap();
        this.c = new Random();
        this.d = WALL_TYPE_NONE;
    }

    public void toCopy(AdInfo adInfo) {
        this.a = adInfo.a;
        this.cycleTime = adInfo.cycleTime;
        this.bannerKind = adInfo.bannerKind;
        this.bgColor = adInfo.bgColor != null ? adInfo.bgColor : BuildConfig.FLAVOR;
        this.transAnimOff = adInfo.transAnimOff;
        this.adInfoDetailArray = new ArrayList();
        if (adInfo.adInfoDetailArray != null) {
            this.adInfoDetailArray.clear();
            this.adInfoDetailArray.addAll(adInfo.adInfoDetailArray);
        }
        if (adInfo.b != null && adInfo.b.size() > 0) {
            this.b.clear();
            this.b.putAll(adInfo.b);
        }
        this.c = adInfo.c;
        this.d = adInfo.d;
    }

    public void sortOnWeighting() {
        String currentCountryCode = Util.getCurrentCountryCode();
        if (!this.b.containsKey(currentCountryCode)) {
            currentCountryCode = Constants.LOCALE_EN;
        }
        Collections.sort(this.adInfoDetailArray, new Comparator<AdInfoDetail>(this) {
            final /* synthetic */ AdInfo b;

            public int compare(AdInfoDetail adInfoDetail, AdInfoDetail adInfoDetail2) {
                int i = AdInfo.BANNER_KIND_UNKNOWN;
                Integer num = (Integer) adInfoDetail.weight.get(currentCountryCode);
                Integer num2 = (Integer) adInfoDetail2.weight.get(currentCountryCode);
                if (num == null || num2 == null) {
                    if (num2 != null) {
                        i = AdInfo.WALL_TYPE_URL;
                    }
                    return i;
                } else if (num.equals(num2)) {
                    return AdInfo.WALL_TYPE_NONE;
                } else {
                    return num.intValue() > num2.intValue() ? AdInfo.BANNER_KIND_UNKNOWN : AdInfo.WALL_TYPE_URL;
                }
            }
        });
    }

    public static int getAdType(int i) {
        int i2;
        int i3 = WALL_TYPE_NONE;
        int[] iArr = BANNER_KIND_BANNER;
        int length = iArr.length;
        for (i2 = WALL_TYPE_NONE; i2 < length; i2 += WALL_TYPE_URL) {
            if (i == iArr[i2]) {
                return WALL_TYPE_NONE;
            }
        }
        iArr = BANNER_KIND_INTERSTITIAL;
        length = iArr.length;
        for (i2 = WALL_TYPE_NONE; i2 < length; i2 += WALL_TYPE_URL) {
            if (i == iArr[i2]) {
                return WALL_TYPE_URL;
            }
        }
        iArr = BANNER_KIND_WALL;
        length = iArr.length;
        for (i2 = WALL_TYPE_NONE; i2 < length; i2 += WALL_TYPE_URL) {
            if (i == iArr[i2]) {
                return WALL_TYPE_API;
            }
        }
        int[] iArr2 = BANNER_KIND_NATIVE;
        int length2 = iArr2.length;
        while (i3 < length2) {
            if (i == iArr2[i3]) {
                return WALL_TYPE_HTML;
            }
            i3 += WALL_TYPE_URL;
        }
        return BANNER_KIND_UNKNOWN;
    }

    public int getLocaleAdNum() {
        int i = WALL_TYPE_NONE;
        String currentCountryCode = Util.getCurrentCountryCode();
        if (this.b.containsKey(currentCountryCode)) {
            String str = currentCountryCode;
        } else {
            Object obj = Constants.LOCALE_EN;
        }
        if (this.b.containsKey(obj) && this.adInfoDetailArray != null) {
            Iterator it = this.adInfoDetailArray.iterator();
            while (it.hasNext()) {
                int i2;
                AdInfoDetail adInfoDetail = (AdInfoDetail) it.next();
                if (!adInfoDetail.weight.containsKey(obj) || "default".equals(adInfoDetail.adnetworkKey)) {
                    i2 = i;
                } else {
                    i2 = i + WALL_TYPE_URL;
                }
                i = i2;
            }
        }
        return i;
    }

    protected void a() {
        Iterator it = this.adInfoDetailArray.iterator();
        while (it.hasNext()) {
            AdInfoDetail adInfoDetail = (AdInfoDetail) it.next();
            for (String str : adInfoDetail.weight.keySet()) {
                try {
                    int intValue;
                    int intValue2 = ((Integer) adInfoDetail.weight.get(str)).intValue();
                    if (this.b.containsKey(str)) {
                        intValue = ((Integer) this.b.get(str)).intValue() + intValue2;
                    } else {
                        intValue = intValue2;
                    }
                    this.b.put(str, Integer.valueOf(intValue));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public AdInfoDetail getNextAdInfoDetail(boolean z) {
        AdInfoDetail adInfoDetail;
        if (z) {
            Object obj;
            String currentCountryCode = Util.getCurrentCountryCode();
            if (this.b.containsKey(currentCountryCode)) {
                String str = currentCountryCode;
            } else {
                obj = Constants.LOCALE_EN;
            }
            int intValue = this.b.containsKey(obj) ? ((Integer) this.b.get(obj)).intValue() : WALL_TYPE_NONE;
            if (intValue > 0 && this.adInfoDetailArray != null) {
                int nextInt = this.c.nextInt(intValue);
                Iterator it = this.adInfoDetailArray.iterator();
                int i = WALL_TYPE_NONE;
                while (it.hasNext()) {
                    int intValue2;
                    adInfoDetail = (AdInfoDetail) it.next();
                    if (adInfoDetail.weight.containsKey(obj)) {
                        intValue2 = ((Integer) adInfoDetail.weight.get(obj)).intValue() + i;
                    } else {
                        intValue2 = i;
                    }
                    if (intValue2 + BANNER_KIND_UNKNOWN >= nextInt) {
                        return adInfoDetail;
                    }
                    i = intValue2;
                }
            }
            return null;
        }
        ArrayList arrayList = this.adInfoDetailArray;
        int i2 = this.d;
        this.d = i2 + WALL_TYPE_URL;
        adInfoDetail = (AdInfoDetail) arrayList.get(i2);
        if (this.d < this.adInfoDetailArray.size()) {
            return adInfoDetail;
        }
        this.d = WALL_TYPE_NONE;
        return adInfoDetail;
    }

    public static AdInfo conversionToAdInfo(Context context, String str, String str2, boolean z) {
        LogUtil instance = LogUtil.getInstance(context);
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        AdInfo adInfo = new AdInfo();
        try {
            JSONObject jSONObject = new JSONObject(str2);
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str3 = (String) keys.next();
                if (ApiAccessUtil.WEBAPI_KEY_CYCLE_TIME.equals(str3)) {
                    adInfo.cycleTime = jSONObject.getLong(str3);
                } else if (ApiAccessUtil.WEBAPI_KEY_BANNER_KIND.equals(str3)) {
                    adInfo.bannerKind = jSONObject.getInt(str3);
                } else if (ApiAccessUtil.WEBAPI_KEY_BG_COLOR.equals(str3)) {
                    adInfo.bgColor = jSONObject.getString(str3);
                } else if (ApiAccessUtil.WEBAPI_KEY_TRANSITION_OFF.equals(str3)) {
                    adInfo.transAnimOff = jSONObject.getInt(str3) == WALL_TYPE_URL;
                } else if (ApiAccessUtil.WEBAPI_KEY_SETTINGS.equals(str3)) {
                    AdInfoDetail.getAdInfoDetail(context, str, adInfo, jSONObject.getString(str3), instance, z);
                }
            }
        } catch (Exception e) {
            instance.debug_e(Constants.TAG, "JSONException");
            instance.debug_e(Constants.TAG, e);
            adInfo = null;
        }
        return adInfo;
    }

    public void setExpirationMs(long j) {
        this.a = j;
    }

    public boolean isOverExpiration() {
        return new Date().getTime() >= this.a;
    }
}
