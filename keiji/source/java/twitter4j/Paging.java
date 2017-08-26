package twitter4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class Paging implements Serializable {
    static final String COUNT = "count";
    private static final HttpParameter[] NULL_PARAMETER_ARRAY = new HttpParameter[0];
    private static final List<HttpParameter> NULL_PARAMETER_LIST = new ArrayList(0);
    static final String PER_PAGE = "per_page";
    static final char[] S = new char[]{'s'};
    static final char[] SMCP = new char[]{'s', 'm', 'c', 'p'};
    private static final long serialVersionUID = -7226113618341047983L;
    private int count;
    private long maxId;
    private int page;
    private long sinceId;

    List<HttpParameter> asPostParameterList() {
        return asPostParameterList(SMCP, COUNT);
    }

    HttpParameter[] asPostParameterArray() {
        List asPostParameterList = asPostParameterList(SMCP, COUNT);
        if (asPostParameterList.size() == 0) {
            return NULL_PARAMETER_ARRAY;
        }
        return (HttpParameter[]) asPostParameterList.toArray(new HttpParameter[asPostParameterList.size()]);
    }

    List<HttpParameter> asPostParameterList(char[] cArr) {
        return asPostParameterList(cArr, COUNT);
    }

    List<HttpParameter> asPostParameterList(char[] cArr, String str) {
        List<HttpParameter> arrayList = new ArrayList(cArr.length);
        addPostParameter(cArr, 's', arrayList, "since_id", getSinceId());
        addPostParameter(cArr, 'm', arrayList, "max_id", getMaxId());
        addPostParameter(cArr, 'c', arrayList, str, (long) getCount());
        addPostParameter(cArr, 'p', arrayList, "page", (long) getPage());
        if (arrayList.size() == 0) {
            return NULL_PARAMETER_LIST;
        }
        return arrayList;
    }

    HttpParameter[] asPostParameterArray(char[] cArr, String str) {
        List arrayList = new ArrayList(cArr.length);
        addPostParameter(cArr, 's', arrayList, "since_id", getSinceId());
        addPostParameter(cArr, 'm', arrayList, "max_id", getMaxId());
        addPostParameter(cArr, 'c', arrayList, str, (long) getCount());
        addPostParameter(cArr, 'p', arrayList, "page", (long) getPage());
        if (arrayList.size() == 0) {
            return NULL_PARAMETER_ARRAY;
        }
        return (HttpParameter[]) arrayList.toArray(new HttpParameter[arrayList.size()]);
    }

    private void addPostParameter(char[] cArr, char c, List<HttpParameter> list, String str, long j) {
        Object obj = null;
        for (char c2 : cArr) {
            if (c2 == c) {
                obj = 1;
                break;
            }
        }
        if (obj == null && -1 != j) {
            throw new IllegalStateException("Paging parameter [" + str + "] is not supported with this operation.");
        } else if (-1 != j) {
            list.add(new HttpParameter(str, String.valueOf(j)));
        }
    }

    public Paging() {
        this.page = -1;
        this.count = -1;
        this.sinceId = -1;
        this.maxId = -1;
    }

    public Paging(int i) {
        this.page = -1;
        this.count = -1;
        this.sinceId = -1;
        this.maxId = -1;
        setPage(i);
    }

    public Paging(long j) {
        this.page = -1;
        this.count = -1;
        this.sinceId = -1;
        this.maxId = -1;
        setSinceId(j);
    }

    public Paging(int i, int i2) {
        this(i);
        setCount(i2);
    }

    public Paging(int i, long j) {
        this(i);
        setSinceId(j);
    }

    public Paging(int i, int i2, long j) {
        this(i, i2);
        setSinceId(j);
    }

    public Paging(int i, int i2, long j, long j2) {
        this(i, i2, j);
        setMaxId(j2);
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("page should be positive integer. passed:" + i);
        }
        this.page = i;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("count should be positive integer. passed:" + i);
        }
        this.count = i;
    }

    public Paging count(int i) {
        setCount(i);
        return this;
    }

    public long getSinceId() {
        return this.sinceId;
    }

    public void setSinceId(long j) {
        if (j < 1) {
            throw new IllegalArgumentException("since_id should be positive integer. passed:" + j);
        }
        this.sinceId = j;
    }

    public Paging sinceId(long j) {
        setSinceId(j);
        return this;
    }

    public long getMaxId() {
        return this.maxId;
    }

    public void setMaxId(long j) {
        if (j < 1) {
            throw new IllegalArgumentException("max_id should be positive integer. passed:" + j);
        }
        this.maxId = j;
    }

    public Paging maxId(long j) {
        setMaxId(j);
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Paging)) {
            return false;
        }
        Paging paging = (Paging) obj;
        if (this.count != paging.count) {
            return false;
        }
        if (this.maxId != paging.maxId) {
            return false;
        }
        if (this.page != paging.page) {
            return false;
        }
        if (this.sinceId != paging.sinceId) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((this.page * 31) + this.count) * 31) + ((int) (this.sinceId ^ (this.sinceId >>> 32)))) * 31) + ((int) (this.maxId ^ (this.maxId >>> 32)));
    }

    public String toString() {
        return "Paging{page=" + this.page + ", count=" + this.count + ", sinceId=" + this.sinceId + ", maxId=" + this.maxId + '}';
    }
}
