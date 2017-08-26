package jp.co.mediasdk.android;

public class StreamListenerSupport extends StreamBase {

    public interface CopyListener {
    }

    protected static class CopyDummyListener implements CopyListener {
        protected CopyDummyListener() {
        }
    }

    public interface ReadListener {
    }

    protected static class ReadDummyListener implements ReadListener {
        protected ReadDummyListener() {
        }
    }

    public interface SyncListener {
    }

    protected static class SyncDummyListener implements SyncListener {
        protected SyncDummyListener() {
        }
    }
}
