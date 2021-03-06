package com.igaworks.util.bolts_task;

import com.tapjoy.TapjoyConstants;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

final class BoltsExecutors {
    private static final BoltsExecutors INSTANCE = new BoltsExecutors();
    private final ExecutorService background;
    private final Executor immediate;
    private final ScheduledExecutorService scheduled;

    private static class ImmediateExecutor implements Executor {
        private ThreadLocal<Integer> executionDepth;

        private ImmediateExecutor() {
            this.executionDepth = new ThreadLocal();
        }

        private int incrementDepth() {
            Integer num = (Integer) this.executionDepth.get();
            if (num == null) {
                num = Integer.valueOf(0);
            }
            int intValue = num.intValue() + 1;
            this.executionDepth.set(Integer.valueOf(intValue));
            return intValue;
        }

        private int decrementDepth() {
            Integer num = (Integer) this.executionDepth.get();
            if (num == null) {
                num = Integer.valueOf(0);
            }
            int intValue = num.intValue() - 1;
            if (intValue == 0) {
                this.executionDepth.remove();
            } else {
                this.executionDepth.set(Integer.valueOf(intValue));
            }
            return intValue;
        }

        public void execute(Runnable runnable) {
            if (incrementDepth() <= 15) {
                try {
                    runnable.run();
                } catch (Throwable th) {
                    decrementDepth();
                }
            } else {
                BoltsExecutors.background().execute(runnable);
            }
            decrementDepth();
        }
    }

    private static boolean isAndroidRuntime() {
        String property = System.getProperty("java.runtime.name");
        if (property == null) {
            return false;
        }
        return property.toLowerCase(Locale.US).contains(TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
    }

    private BoltsExecutors() {
        ExecutorService newCachedThreadPool;
        if (isAndroidRuntime()) {
            newCachedThreadPool = AndroidExecutors.newCachedThreadPool();
        } else {
            newCachedThreadPool = Executors.newCachedThreadPool();
        }
        this.background = newCachedThreadPool;
        this.scheduled = Executors.newSingleThreadScheduledExecutor();
        this.immediate = new ImmediateExecutor();
    }

    public static ExecutorService background() {
        return INSTANCE.background;
    }

    static ScheduledExecutorService scheduled() {
        return INSTANCE.scheduled;
    }

    static Executor immediate() {
        return INSTANCE.immediate;
    }
}
