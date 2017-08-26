package c.a;

import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.cocos2dx.lib.BuildConfig;
import org.jetbrains.annotations.NonNls;

/* compiled from: Timber */
public final class a {
    static volatile b[] a = b;
    private static final b[] b = new b[0];
    private static final List<b> c = new ArrayList();
    private static final b d = new b() {
        public void v(String str, Object... objArr) {
            for (b v : a.a) {
                v.v(str, objArr);
            }
        }

        public void v(Throwable th, String str, Object... objArr) {
            for (b v : a.a) {
                v.v(th, str, objArr);
            }
        }

        public void d(String str, Object... objArr) {
            for (b d : a.a) {
                d.d(str, objArr);
            }
        }

        public void d(Throwable th, String str, Object... objArr) {
            for (b d : a.a) {
                d.d(th, str, objArr);
            }
        }

        public void i(String str, Object... objArr) {
            for (b i : a.a) {
                i.i(str, objArr);
            }
        }

        public void i(Throwable th, String str, Object... objArr) {
            for (b i : a.a) {
                i.i(th, str, objArr);
            }
        }

        public void w(String str, Object... objArr) {
            for (b w : a.a) {
                w.w(str, objArr);
            }
        }

        public void w(Throwable th, String str, Object... objArr) {
            for (b w : a.a) {
                w.w(th, str, objArr);
            }
        }

        public void e(String str, Object... objArr) {
            for (b e : a.a) {
                e.e(str, objArr);
            }
        }

        public void e(Throwable th, String str, Object... objArr) {
            for (b e : a.a) {
                e.e(th, str, objArr);
            }
        }

        public void wtf(String str, Object... objArr) {
            for (b wtf : a.a) {
                wtf.wtf(str, objArr);
            }
        }

        public void wtf(Throwable th, String str, Object... objArr) {
            for (b wtf : a.a) {
                wtf.wtf(th, str, objArr);
            }
        }

        public void log(int i, String str, Object... objArr) {
            for (b log : a.a) {
                log.log(i, str, objArr);
            }
        }

        public void log(int i, Throwable th, String str, Object... objArr) {
            for (b log : a.a) {
                log.log(i, th, str, objArr);
            }
        }

        protected void log(int i, String str, String str2, Throwable th) {
            throw new AssertionError("Missing override for log method.");
        }
    };

    /* compiled from: Timber */
    public static abstract class b {
        final ThreadLocal<String> explicitTag = new ThreadLocal();

        protected abstract void log(int i, String str, String str2, Throwable th);

        String getTag() {
            String str = (String) this.explicitTag.get();
            if (str != null) {
                this.explicitTag.remove();
            }
            return str;
        }

        public void v(String str, Object... objArr) {
            prepareLog(2, null, str, objArr);
        }

        public void v(Throwable th, String str, Object... objArr) {
            prepareLog(2, th, str, objArr);
        }

        public void d(String str, Object... objArr) {
            prepareLog(3, null, str, objArr);
        }

        public void d(Throwable th, String str, Object... objArr) {
            prepareLog(3, th, str, objArr);
        }

        public void i(String str, Object... objArr) {
            prepareLog(4, null, str, objArr);
        }

        public void i(Throwable th, String str, Object... objArr) {
            prepareLog(4, th, str, objArr);
        }

        public void w(String str, Object... objArr) {
            prepareLog(5, null, str, objArr);
        }

        public void w(Throwable th, String str, Object... objArr) {
            prepareLog(5, th, str, objArr);
        }

        public void e(String str, Object... objArr) {
            prepareLog(6, null, str, objArr);
        }

        public void e(Throwable th, String str, Object... objArr) {
            prepareLog(6, th, str, objArr);
        }

        public void wtf(String str, Object... objArr) {
            prepareLog(7, null, str, objArr);
        }

        public void wtf(Throwable th, String str, Object... objArr) {
            prepareLog(7, th, str, objArr);
        }

        public void log(int i, String str, Object... objArr) {
            prepareLog(i, null, str, objArr);
        }

        public void log(int i, Throwable th, String str, Object... objArr) {
            prepareLog(i, th, str, objArr);
        }

        protected boolean isLoggable(int i) {
            return true;
        }

        private void prepareLog(int i, Throwable th, String str, Object... objArr) {
            if (isLoggable(i)) {
                String str2;
                if (str == null || str.length() != 0) {
                    str2 = str;
                } else {
                    str2 = null;
                }
                if (str2 != null) {
                    if (objArr.length > 0) {
                        str2 = String.format(str2, objArr);
                    }
                    if (th != null) {
                        str2 = str2 + "\n" + getStackTraceString(th);
                    }
                } else if (th != null) {
                    str2 = getStackTraceString(th);
                } else {
                    return;
                }
                log(i, getTag(), str2, th);
            }
        }

        private String getStackTraceString(Throwable th) {
            Writer stringWriter = new StringWriter(256);
            PrintWriter printWriter = new PrintWriter(stringWriter, false);
            th.printStackTrace(printWriter);
            printWriter.flush();
            return stringWriter.toString();
        }
    }

    /* compiled from: Timber */
    public static class a extends b {
        private static final Pattern ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$");
        private static final int CALL_STACK_INDEX = 5;
        private static final int MAX_LOG_LENGTH = 4000;

        protected String createStackElementTag(StackTraceElement stackTraceElement) {
            String className = stackTraceElement.getClassName();
            Matcher matcher = ANONYMOUS_CLASS.matcher(className);
            if (matcher.find()) {
                className = matcher.replaceAll(BuildConfig.FLAVOR);
            }
            return className.substring(className.lastIndexOf(46) + 1);
        }

        final String getTag() {
            String tag = super.getTag();
            if (tag != null) {
                return tag;
            }
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            if (stackTrace.length > CALL_STACK_INDEX) {
                return createStackElementTag(stackTrace[CALL_STACK_INDEX]);
            }
            throw new IllegalStateException("Synthetic stacktrace didn't have enough elements: are you using proguard?");
        }

        protected void log(int i, String str, String str2, Throwable th) {
            if (str2.length() >= MAX_LOG_LENGTH) {
                int i2 = 0;
                int length = str2.length();
                while (i2 < length) {
                    int min;
                    int indexOf = str2.indexOf(10, i2);
                    if (indexOf == -1) {
                        indexOf = length;
                    }
                    while (true) {
                        min = Math.min(indexOf, i2 + MAX_LOG_LENGTH);
                        String substring = str2.substring(i2, min);
                        if (i == 7) {
                            Log.wtf(str, substring);
                        } else {
                            Log.println(i, str, substring);
                        }
                        if (min >= indexOf) {
                            break;
                        }
                        i2 = min;
                    }
                    i2 = min + 1;
                }
            } else if (i == 7) {
                Log.wtf(str, str2);
            } else {
                Log.println(i, str, str2);
            }
        }
    }

    public static void a(@NonNls String str, Object... objArr) {
        d.i(str, objArr);
    }

    public static void b(@NonNls String str, Object... objArr) {
        d.e(str, objArr);
    }

    public static void a(b bVar) {
        if (bVar == null) {
            throw new NullPointerException("tree == null");
        } else if (bVar == d) {
            throw new IllegalArgumentException("Cannot plant Timber into itself.");
        } else {
            synchronized (c) {
                c.add(bVar);
                a = (b[]) c.toArray(new b[c.size()]);
            }
        }
    }
}
