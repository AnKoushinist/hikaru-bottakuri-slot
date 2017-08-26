package twitter4j.management;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.ReflectionException;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.OpenDataException;
import javax.management.openmbean.OpenMBeanAttributeInfoSupport;
import javax.management.openmbean.OpenMBeanConstructorInfoSupport;
import javax.management.openmbean.OpenMBeanInfoSupport;
import javax.management.openmbean.OpenMBeanOperationInfoSupport;
import javax.management.openmbean.OpenMBeanParameterInfoSupport;
import javax.management.openmbean.OpenType;
import javax.management.openmbean.SimpleType;
import javax.management.openmbean.TabularDataSupport;
import javax.management.openmbean.TabularType;

public class APIStatisticsOpenMBean implements DynamicMBean {
    private static final String[] ITEM_DESCRIPTIONS = new String[]{"The method name", "The number of times this method has been called", "The number of calls that failed", "The total amount of time spent invoking this method in milliseconds", "The average amount of time spent invoking this method in milliseconds"};
    private static final String[] ITEM_NAMES = new String[]{"methodName", "callCount", "errorCount", "totalTime", "avgTime"};
    private static final OpenType[] ITEM_TYPES = new OpenType[]{SimpleType.STRING, SimpleType.LONG, SimpleType.LONG, SimpleType.LONG, SimpleType.LONG};
    private final APIStatisticsMBean API_STATISTICS;
    private final TabularType API_STATISTICS_TYPE;
    private final CompositeType METHOD_STATS_TYPE;

    public APIStatisticsOpenMBean(APIStatistics aPIStatistics) {
        this.API_STATISTICS = aPIStatistics;
        try {
            this.METHOD_STATS_TYPE = new CompositeType("method statistics", "method statistics", ITEM_NAMES, ITEM_DESCRIPTIONS, ITEM_TYPES);
            this.API_STATISTICS_TYPE = new TabularType("API statistics", "list of methods", this.METHOD_STATS_TYPE, new String[]{"methodName"});
        } catch (OpenDataException e) {
            throw new RuntimeException(e);
        }
    }

    public MBeanInfo getMBeanInfo() {
        r7 = new OpenMBeanAttributeInfoSupport[5];
        OpenMBeanConstructorInfoSupport[] openMBeanConstructorInfoSupportArr = new OpenMBeanConstructorInfoSupport[1];
        OpenMBeanOperationInfoSupport[] openMBeanOperationInfoSupportArr = new OpenMBeanOperationInfoSupport[1];
        MBeanNotificationInfo[] mBeanNotificationInfoArr = new MBeanNotificationInfo[0];
        r7[0] = new OpenMBeanAttributeInfoSupport("callCount", "Total number of API calls", SimpleType.LONG, true, false, false);
        r7[1] = new OpenMBeanAttributeInfoSupport("errorCount", "The number of failed API calls", SimpleType.LONG, true, false, false);
        r7[2] = new OpenMBeanAttributeInfoSupport("averageTime", "Average time spent invoking any API method", SimpleType.LONG, true, false, false);
        r7[3] = new OpenMBeanAttributeInfoSupport("totalTime", "Average time spent invoking any API method", SimpleType.LONG, true, false, false);
        r7[4] = new OpenMBeanAttributeInfoSupport("statisticsTable", "Table of statisics for all API methods", this.API_STATISTICS_TYPE, true, false, false);
        openMBeanConstructorInfoSupportArr[0] = new OpenMBeanConstructorInfoSupport("APIStatisticsOpenMBean", "Constructs an APIStatisticsOpenMBean instance", new OpenMBeanParameterInfoSupport[0]);
        openMBeanOperationInfoSupportArr[0] = new OpenMBeanOperationInfoSupport("reset", "reset the statistics", new OpenMBeanParameterInfoSupport[0], SimpleType.VOID, 0);
        return new OpenMBeanInfoSupport(getClass().getName(), "API Statistics Open MBean", r7, openMBeanConstructorInfoSupportArr, openMBeanOperationInfoSupportArr, mBeanNotificationInfoArr);
    }

    public synchronized TabularDataSupport getStatistics() {
        TabularDataSupport tabularDataSupport;
        tabularDataSupport = new TabularDataSupport(this.API_STATISTICS_TYPE);
        for (InvocationStatistics invocationStatistics : this.API_STATISTICS.getInvocationStatistics()) {
            try {
                tabularDataSupport.put(new CompositeDataSupport(this.METHOD_STATS_TYPE, ITEM_NAMES, new Object[]{invocationStatistics.getName(), Long.valueOf(invocationStatistics.getCallCount()), Long.valueOf(invocationStatistics.getErrorCount()), Long.valueOf(invocationStatistics.getTotalTime()), Long.valueOf(invocationStatistics.getAverageTime())}));
            } catch (OpenDataException e) {
                throw new RuntimeException(e);
            }
        }
        return tabularDataSupport;
    }

    public void reset() {
        this.API_STATISTICS.reset();
    }

    public Object getAttribute(String str) {
        if (str.equals("statisticsTable")) {
            return getStatistics();
        }
        if (str.equals("callCount")) {
            return Long.valueOf(this.API_STATISTICS.getCallCount());
        }
        if (str.equals("errorCount")) {
            return Long.valueOf(this.API_STATISTICS.getErrorCount());
        }
        if (str.equals("totalTime")) {
            return Long.valueOf(this.API_STATISTICS.getTotalTime());
        }
        if (str.equals("averageTime")) {
            return Long.valueOf(this.API_STATISTICS.getAverageTime());
        }
        throw new AttributeNotFoundException("Cannot find " + str + " attribute ");
    }

    public AttributeList getAttributes(String[] strArr) {
        AttributeList attributeList = new AttributeList();
        if (strArr.length != 0) {
            for (String str : strArr) {
                try {
                    attributeList.add(new Attribute(str, getAttribute(str)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return attributeList;
    }

    public Object invoke(String str, Object[] objArr, String[] strArr) {
        if (str.equals("reset")) {
            reset();
            return "Statistics reset";
        }
        throw new ReflectionException(new NoSuchMethodException(str), "Cannot find the operation " + str);
    }

    public void setAttribute(Attribute attribute) {
        throw new AttributeNotFoundException("No attributes can be set in this MBean");
    }

    public AttributeList setAttributes(AttributeList attributeList) {
        return new AttributeList();
    }
}
