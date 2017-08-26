package jp.reifrontier.silentlogsdkandroid;

import com.raizlabs.android.dbflow.runtime.BaseContentProvider.PropertyConverter;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.language.property.BaseProperty;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.language.property.LongProperty;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.tapjoy.TapjoyConstants;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;
import twitter4j.TwitterResponse;

public final class RFLEntityActivity_Table {
    public static final PropertyConverter PROPERTY_CONVERTER = new PropertyConverter() {
        public IProperty fromName(String str) {
            return RFLEntityActivity_Table.getProperty(str);
        }
    };
    public static final Property<String> date = new Property(RFLEntityActivity.class, "date");
    public static final Property<String> detectedActivitites = new Property(RFLEntityActivity.class, "detectedActivitites");
    public static final LongProperty id = new LongProperty(RFLEntityActivity.class, "id");
    public static final Property<Boolean> submit = new Property(RFLEntityActivity.class, "submit");
    public static final Property<String> timezone = new Property(RFLEntityActivity.class, TapjoyConstants.TJC_DEVICE_TIMEZONE);

    public static final IProperty[] getAllColumnProperties() {
        return new IProperty[]{id, date, timezone, detectedActivitites, submit};
    }

    public static BaseProperty getProperty(String str) {
        String quoteIfNeeded = QueryBuilder.quoteIfNeeded(str);
        Object obj = -1;
        switch (quoteIfNeeded.hashCode()) {
            case -2061723928:
                if (quoteIfNeeded.equals("`submit`")) {
                    obj = 4;
                    break;
                }
                break;
            case -1623973081:
                if (quoteIfNeeded.equals("`timezone`")) {
                    obj = 2;
                    break;
                }
                break;
            case -1451212270:
                if (quoteIfNeeded.equals("`date`")) {
                    obj = 1;
                    break;
                }
                break;
            case -495097857:
                if (quoteIfNeeded.equals("`detectedActivitites`")) {
                    obj = 3;
                    break;
                }
                break;
            case 2964037:
                if (quoteIfNeeded.equals("`id`")) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case TwitterResponse.NONE /*0*/:
                return id;
            case TwitterResponse.READ /*1*/:
                return date;
            case TwitterResponse.READ_WRITE /*2*/:
                return timezone;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                return detectedActivitites;
            case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                return submit;
            default:
                throw new IllegalArgumentException("Invalid column name passed. Ensure you are calling the correct table's column");
        }
    }
}
