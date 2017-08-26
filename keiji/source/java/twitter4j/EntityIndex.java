package twitter4j;

import java.io.Serializable;

abstract class EntityIndex implements Serializable, Comparable<EntityIndex> {
    private static final long serialVersionUID = 3757474748266170719L;
    private int end = -1;
    private int start = -1;

    EntityIndex() {
    }

    public int compareTo(EntityIndex entityIndex) {
        long j = (long) (this.start - entityIndex.start);
        if (j < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) j;
    }

    void setStart(int i) {
        this.start = i;
    }

    void setEnd(int i) {
        this.end = i;
    }

    int getStart() {
        return this.start;
    }

    int getEnd() {
        return this.end;
    }
}
