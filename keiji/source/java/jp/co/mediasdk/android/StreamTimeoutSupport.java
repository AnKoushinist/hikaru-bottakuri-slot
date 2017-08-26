package jp.co.mediasdk.android;

public class StreamTimeoutSupport extends StreamListenerSupport {

    public interface TimeoutListener {
    }

    protected static class TimeoutDummyListener implements TimeoutListener {
        protected TimeoutDummyListener() {
        }
    }
}
