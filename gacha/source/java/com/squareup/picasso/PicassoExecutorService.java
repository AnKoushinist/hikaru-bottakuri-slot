package com.squareup.picasso;

import android.net.NetworkInfo;
import com.squareup.picasso.Picasso.Priority;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import jp.co.vaz.creator.hikaru.R;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;
import twitter4j.TwitterResponse;

class PicassoExecutorService extends ThreadPoolExecutor {
    private static final int DEFAULT_THREAD_COUNT = 3;

    private static final class PicassoFutureTask extends FutureTask<BitmapHunter> implements Comparable<PicassoFutureTask> {
        private final BitmapHunter hunter;

        public PicassoFutureTask(BitmapHunter bitmapHunter) {
            super(bitmapHunter, null);
            this.hunter = bitmapHunter;
        }

        public int compareTo(PicassoFutureTask picassoFutureTask) {
            Priority priority = this.hunter.getPriority();
            Priority priority2 = picassoFutureTask.hunter.getPriority();
            return priority == priority2 ? this.hunter.sequence - picassoFutureTask.hunter.sequence : priority2.ordinal() - priority.ordinal();
        }
    }

    PicassoExecutorService() {
        super(DEFAULT_THREAD_COUNT, DEFAULT_THREAD_COUNT, 0, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new PicassoThreadFactory());
    }

    void adjustThreadCount(NetworkInfo networkInfo) {
        if (networkInfo == null || !networkInfo.isConnectedOrConnecting()) {
            setThreadCount(DEFAULT_THREAD_COUNT);
            return;
        }
        switch (networkInfo.getType()) {
            case TwitterResponse.NONE /*0*/:
                switch (networkInfo.getSubtype()) {
                    case TwitterResponse.READ /*1*/:
                    case TwitterResponse.READ_WRITE /*2*/:
                        setThreadCount(1);
                        return;
                    case DEFAULT_THREAD_COUNT /*3*/:
                    case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                    case AdInfo.BANNER_KIND_BANNER5 /*5*/:
                    case AdInfo.BANNER_KIND_BANNER6 /*6*/:
                    case Constants.MOVIE_REWARD_TYPE /*12*/:
                        setThreadCount(2);
                        return;
                    case R.styleable.Toolbar_subtitleTextAppearance /*13*/:
                    case Constants.MOVIE_INTER_TYPE /*14*/:
                    case R.styleable.Toolbar_titleMarginStart /*15*/:
                        setThreadCount(DEFAULT_THREAD_COUNT);
                        return;
                    default:
                        setThreadCount(DEFAULT_THREAD_COUNT);
                        return;
                }
            case TwitterResponse.READ /*1*/:
            case AdInfo.BANNER_KIND_BANNER6 /*6*/:
            case AdInfo.BANNER_KIND_INTERSTITIAL1 /*9*/:
                setThreadCount(4);
                return;
            default:
                setThreadCount(DEFAULT_THREAD_COUNT);
                return;
        }
    }

    private void setThreadCount(int i) {
        setCorePoolSize(i);
        setMaximumPoolSize(i);
    }

    public Future<?> submit(Runnable runnable) {
        Object picassoFutureTask = new PicassoFutureTask((BitmapHunter) runnable);
        execute(picassoFutureTask);
        return picassoFutureTask;
    }
}
