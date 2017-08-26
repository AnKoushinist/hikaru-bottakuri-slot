package twitter4j;

import com.unity3d.ads.metadata.MediationMetaData;

public class TimeZoneJSONImpl implements TimeZone {
    private static final long serialVersionUID = 81958969762484144L;
    private final String NAME;
    private final String TZINFO_NAME;
    private final int UTC_OFFSET;

    TimeZoneJSONImpl(JSONObject jSONObject) {
        try {
            this.UTC_OFFSET = ParseUtil.getInt("utc_offset", jSONObject);
            this.NAME = jSONObject.getString(MediationMetaData.KEY_NAME);
            this.TZINFO_NAME = jSONObject.getString("tzinfo_name");
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public String getName() {
        return this.NAME;
    }

    public String tzinfoName() {
        return this.TZINFO_NAME;
    }

    public int utcOffset() {
        return this.UTC_OFFSET;
    }
}
