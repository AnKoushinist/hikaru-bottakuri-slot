package com.raizlabs.android.dbflow.sql.language;

import com.raizlabs.android.dbflow.sql.SQLiteType;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import java.util.ArrayList;
import java.util.List;
import org.cocos2dx.lib.BuildConfig;

public class Method extends Property {
    private final IProperty methodProperty;
    private List<String> operationsList;
    private final List<IProperty> propertyList;

    public static class Cast {
        private final IProperty property;

        private Cast(IProperty iProperty) {
            this.property = iProperty;
        }

        public IProperty as(SQLiteType sQLiteType) {
            Property property = new Property(this.property.getTable(), this.property.getNameAlias().newBuilder().shouldAddIdentifierToAliasName(false).as(sQLiteType.name()).build());
            return new Method("CAST", property);
        }
    }

    public static Method avg(IProperty... iPropertyArr) {
        return new Method("AVG", iPropertyArr);
    }

    public static Method count(IProperty... iPropertyArr) {
        return new Method("COUNT", iPropertyArr);
    }

    public static Method group_concat(IProperty... iPropertyArr) {
        return new Method("GROUP_CONCAT", iPropertyArr);
    }

    public static Method max(IProperty... iPropertyArr) {
        return new Method("MAX", iPropertyArr);
    }

    public static Method min(IProperty... iPropertyArr) {
        return new Method("MIN", iPropertyArr);
    }

    public static Method sum(IProperty... iPropertyArr) {
        return new Method("SUM", iPropertyArr);
    }

    public static Method total(IProperty... iPropertyArr) {
        return new Method("TOTAL", iPropertyArr);
    }

    public static Cast cast(IProperty iProperty) {
        return new Cast(iProperty);
    }

    public Method(IProperty... iPropertyArr) {
        this(null, iPropertyArr);
    }

    public Method(String str, IProperty... iPropertyArr) {
        super(null, (String) null);
        this.propertyList = new ArrayList();
        this.operationsList = new ArrayList();
        this.methodProperty = new Property(null, NameAlias.rawBuilder(str).build());
        if (iPropertyArr.length == 0) {
            this.propertyList.add(Property.ALL_PROPERTY);
            return;
        }
        for (IProperty addProperty : iPropertyArr) {
            addProperty(addProperty);
        }
    }

    public Method plus(IProperty iProperty) {
        return append(iProperty, Operation.PLUS);
    }

    public Method minus(IProperty iProperty) {
        return append(iProperty, Operation.MINUS);
    }

    public Method addProperty(IProperty iProperty) {
        if (this.propertyList.size() == 1 && this.propertyList.get(0) == Property.ALL_PROPERTY) {
            this.propertyList.remove(0);
        }
        return append(iProperty, ",");
    }

    public Method append(IProperty iProperty, String str) {
        this.propertyList.add(iProperty);
        this.operationsList.add(str);
        return this;
    }

    protected List<IProperty> getPropertyList() {
        return this.propertyList;
    }

    public NameAlias getNameAlias() {
        if (this.nameAlias == null) {
            String query = this.methodProperty.getQuery();
            if (query == null) {
                query = BuildConfig.FLAVOR;
            }
            String str = query + "(";
            List propertyList = getPropertyList();
            for (int i = 0; i < propertyList.size(); i++) {
                IProperty iProperty = (IProperty) propertyList.get(i);
                if (i > 0) {
                    str = str + " " + ((String) this.operationsList.get(i)) + " ";
                }
                str = str + iProperty.toString();
            }
            this.nameAlias = NameAlias.rawBuilder(str + ")").build();
        }
        return this.nameAlias;
    }
}
