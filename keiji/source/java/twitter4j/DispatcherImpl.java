package twitter4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import twitter4j.conf.Configuration;

final class DispatcherImpl implements Dispatcher {
    private final ExecutorService executorService;

    public DispatcherImpl(final Configuration configuration) {
        this.executorService = Executors.newFixedThreadPool(configuration.getAsyncNumThreads(), new ThreadFactory() {
            int count = 0;

            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                Object[] objArr = new Object[1];
                int i = this.count;
                this.count = i + 1;
                objArr[0] = Integer.valueOf(i);
                thread.setName(String.format("Twitter4J Async Dispatcher[%d]", objArr));
                thread.setDaemon(configuration.isDaemonEnabled());
                return thread;
            }
        });
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                DispatcherImpl.this.executorService.shutdown();
            }
        });
    }

    public synchronized void invokeLater(Runnable runnable) {
        this.executorService.execute(runnable);
    }

    public synchronized void shutdown() {
        this.executorService.shutdown();
    }
}
