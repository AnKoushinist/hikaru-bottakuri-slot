package twitter4j;

import java.util.Arrays;
import twitter4j.conf.Configuration;

final class IDsJSONImpl extends TwitterResponseImpl implements IDs {
    private static final long serialVersionUID = 6999637496007165672L;
    private long[] ids;
    private long nextCursor = -1;
    private long previousCursor = -1;

    IDsJSONImpl(HttpResponse httpResponse, Configuration configuration) {
        super(httpResponse);
        String asString = httpResponse.asString();
        init(asString);
        if (configuration.isJSONStoreEnabled()) {
            TwitterObjectFactory.clearThreadLocalMap();
            TwitterObjectFactory.registerJSONObject(this, asString);
        }
    }

    IDsJSONImpl(String str) {
        init(str);
    }

    private void init(String str) {
        JSONArray jSONArray;
        int i = 0;
        JSONObject jSONObject;
        try {
            if (str.startsWith("{")) {
                jSONObject = new JSONObject(str);
                JSONArray jSONArray2 = jSONObject.getJSONArray("ids");
                this.ids = new long[jSONArray2.length()];
                while (i < jSONArray2.length()) {
                    this.ids[i] = Long.parseLong(jSONArray2.getString(i));
                    i++;
                }
                this.previousCursor = ParseUtil.getLong("previous_cursor", jSONObject);
                this.nextCursor = ParseUtil.getLong("next_cursor", jSONObject);
                return;
            }
            jSONArray = new JSONArray(str);
            this.ids = new long[jSONArray.length()];
            while (i < jSONArray.length()) {
                this.ids[i] = Long.parseLong(jSONArray.getString(i));
                i++;
            }
        } catch (Throwable e) {
            throw new TwitterException("Twitter API returned malformed response: " + jSONArray, e);
        } catch (Throwable e2) {
            throw new TwitterException("Twitter API returned malformed response: " + jSONObject, e2);
        } catch (Exception e3) {
            throw new TwitterException(e3);
        }
    }

    public long[] getIDs() {
        return this.ids;
    }

    public boolean hasPrevious() {
        return 0 != this.previousCursor;
    }

    public long getPreviousCursor() {
        return this.previousCursor;
    }

    public boolean hasNext() {
        return 0 != this.nextCursor;
    }

    public long getNextCursor() {
        return this.nextCursor;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IDs)) {
            return false;
        }
        if (Arrays.equals(this.ids, ((IDs) obj).getIDs())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.ids != null ? Arrays.hashCode(this.ids) : 0;
    }

    public String toString() {
        return "IDsJSONImpl{ids=" + Arrays.toString(this.ids) + ", previousCursor=" + this.previousCursor + ", nextCursor=" + this.nextCursor + '}';
    }
}
