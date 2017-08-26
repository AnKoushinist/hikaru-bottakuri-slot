package a.a.a.a.a;

import android.util.Log;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

/* compiled from: LogCatAppender */
public class a extends AppenderSkeleton {
    protected Layout a;

    public a(Layout layout, Layout layout2) {
        this.a = layout2;
        setLayout(layout);
    }

    public a(Layout layout) {
        this(layout, new PatternLayout("%c"));
    }

    public a() {
        this(new PatternLayout("%m%n"));
    }

    protected void append(LoggingEvent loggingEvent) {
        switch (loggingEvent.getLevel().toInt()) {
            case 5000:
                if (loggingEvent.getThrowableInformation() != null) {
                    Log.v(a().format(loggingEvent), getLayout().format(loggingEvent), loggingEvent.getThrowableInformation().getThrowable());
                    return;
                } else {
                    Log.v(a().format(loggingEvent), getLayout().format(loggingEvent));
                    return;
                }
            case 10000:
                if (loggingEvent.getThrowableInformation() != null) {
                    Log.d(a().format(loggingEvent), getLayout().format(loggingEvent), loggingEvent.getThrowableInformation().getThrowable());
                    return;
                } else {
                    Log.d(a().format(loggingEvent), getLayout().format(loggingEvent));
                    return;
                }
            case 20000:
                if (loggingEvent.getThrowableInformation() != null) {
                    Log.i(a().format(loggingEvent), getLayout().format(loggingEvent), loggingEvent.getThrowableInformation().getThrowable());
                    return;
                } else {
                    Log.i(a().format(loggingEvent), getLayout().format(loggingEvent));
                    return;
                }
            case 30000:
                if (loggingEvent.getThrowableInformation() != null) {
                    Log.w(a().format(loggingEvent), getLayout().format(loggingEvent), loggingEvent.getThrowableInformation().getThrowable());
                    return;
                } else {
                    Log.w(a().format(loggingEvent), getLayout().format(loggingEvent));
                    return;
                }
            case 40000:
                if (loggingEvent.getThrowableInformation() != null) {
                    Log.e(a().format(loggingEvent), getLayout().format(loggingEvent), loggingEvent.getThrowableInformation().getThrowable());
                    return;
                } else {
                    Log.e(a().format(loggingEvent), getLayout().format(loggingEvent));
                    return;
                }
            case 50000:
                if (loggingEvent.getThrowableInformation() != null) {
                    Log.wtf(a().format(loggingEvent), getLayout().format(loggingEvent), loggingEvent.getThrowableInformation().getThrowable());
                    return;
                } else {
                    Log.wtf(a().format(loggingEvent), getLayout().format(loggingEvent));
                    return;
                }
            default:
                return;
        }
    }

    public void close() {
    }

    public boolean requiresLayout() {
        return true;
    }

    public Layout a() {
        return this.a;
    }
}
