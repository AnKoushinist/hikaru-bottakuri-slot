package jp.reifrontier.silentlogsdkandroid;

import com.raizlabs.android.dbflow.runtime.BaseContentProvider.PropertyConverter;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.language.property.BaseProperty;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.language.property.IntProperty;
import com.raizlabs.android.dbflow.sql.language.property.LongProperty;
import com.tapjoy.TapjoyConstants;
import twitter4j.TwitterResponse;

public final class RFLEntityWalker_Table {
    public static final PropertyConverter PROPERTY_CONVERTER = new PropertyConverter() {
        public IProperty fromName(String str) {
            return RFLEntityWalker_Table.getProperty(str);
        }
    };
    public static final LongProperty id = new LongProperty(RFLEntityWalker.class, "id");
    public static final IntProperty stepCount = new IntProperty(RFLEntityWalker.class, "stepCount");
    public static final LongProperty timestamp = new LongProperty(RFLEntityWalker.class, TapjoyConstants.TJC_TIMESTAMP);

    public static final IProperty[] getAllColumnProperties() {
        return new IProperty[]{id, timestamp, stepCount};
    }

    public static BaseProperty getProperty(String str) {
        String quoteIfNeeded = QueryBuilder.quoteIfNeeded(str);
        Object obj = -1;
        switch (quoteIfNeeded.hashCode()) {
            case 2964037:
                if (quoteIfNeeded.equals("`id`")) {
                    obj = null;
                    break;
                }
                break;
            case 1000276586:
                if (quoteIfNeeded.equals("`timestamp`")) {
                    obj = 1;
                    break;
                }
                break;
            case 1526432637:
                if (quoteIfNeeded.equals("`stepCount`")) {
                    obj = 2;
                    break;
                }
                break;
        }
        switch (obj) {
            case TwitterResponse.NONE /*0*/:
                return id;
            case TwitterResponse.READ /*1*/:
                return timestamp;
            case TwitterResponse.READ_WRITE /*2*/:
                return stepCount;
            default:
                throw new IllegalArgumentException("Invalid column name passed. Ensure you are calling the correct table's column");
        }
    }
}
