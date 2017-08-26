package jp.reifrontier.silentlogsdkandroid;

import com.raizlabs.android.dbflow.runtime.BaseContentProvider.PropertyConverter;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.language.property.BaseProperty;
import com.raizlabs.android.dbflow.sql.language.property.DoubleProperty;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.language.property.LongProperty;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.unity3d.ads.metadata.MediationMetaData;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;
import twitter4j.TwitterResponse;

public final class RFLEntityTag_Table {
    public static final PropertyConverter PROPERTY_CONVERTER = new PropertyConverter() {
        public IProperty fromName(String str) {
            return RFLEntityTag_Table.getProperty(str);
        }
    };
    public static final Property<String> date = new Property(RFLEntityTag.class, "date");
    public static final LongProperty fromDate = new LongProperty(RFLEntityTag.class, "fromDate");
    public static final LongProperty id = new LongProperty(RFLEntityTag.class, "id");
    public static final DoubleProperty latitude = new DoubleProperty(RFLEntityTag.class, "latitude");
    public static final DoubleProperty longitude = new DoubleProperty(RFLEntityTag.class, "longitude");
    public static final Property<String> name = new Property(RFLEntityTag.class, MediationMetaData.KEY_NAME);
    public static final LongProperty toDate = new LongProperty(RFLEntityTag.class, "toDate");

    public static final IProperty[] getAllColumnProperties() {
        return new IProperty[]{id, date, fromDate, toDate, latitude, longitude, name};
    }

    public static BaseProperty getProperty(String str) {
        String quoteIfNeeded = QueryBuilder.quoteIfNeeded(str);
        Object obj = -1;
        switch (quoteIfNeeded.hashCode()) {
            case -1630231416:
                if (quoteIfNeeded.equals("`fromDate`")) {
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
            case -1441983787:
                if (quoteIfNeeded.equals("`name`")) {
                    obj = 6;
                    break;
                }
                break;
            case -1374048169:
                if (quoteIfNeeded.equals("`toDate`")) {
                    obj = 3;
                    break;
                }
                break;
            case -745261839:
                if (quoteIfNeeded.equals("`longitude`")) {
                    obj = 5;
                    break;
                }
                break;
            case 2964037:
                if (quoteIfNeeded.equals("`id`")) {
                    obj = null;
                    break;
                }
                break;
            case 919883028:
                if (quoteIfNeeded.equals("`latitude`")) {
                    obj = 4;
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
                return fromDate;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                return toDate;
            case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                return latitude;
            case AdInfo.BANNER_KIND_BANNER5 /*5*/:
                return longitude;
            case AdInfo.BANNER_KIND_BANNER6 /*6*/:
                return name;
            default:
                throw new IllegalArgumentException("Invalid column name passed. Ensure you are calling the correct table's column");
        }
    }
}
