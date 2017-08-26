package twitter4j;

public interface PagableResponseList<T extends TwitterResponse> extends CursorSupport, ResponseList<T> {
    long getNextCursor();

    long getPreviousCursor();

    boolean hasNext();

    boolean hasPrevious();
}
