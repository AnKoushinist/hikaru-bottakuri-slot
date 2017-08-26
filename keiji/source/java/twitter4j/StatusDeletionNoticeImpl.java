package twitter4j;

import java.io.Serializable;

class StatusDeletionNoticeImpl implements Serializable, StatusDeletionNotice {
    private static final long serialVersionUID = 9144204870473786368L;
    private final long statusId;
    private final long userId;

    StatusDeletionNoticeImpl(JSONObject jSONObject) {
        this.statusId = ParseUtil.getLong("id", jSONObject);
        this.userId = ParseUtil.getLong("user_id", jSONObject);
    }

    public long getStatusId() {
        return this.statusId;
    }

    public long getUserId() {
        return this.userId;
    }

    public int compareTo(StatusDeletionNotice statusDeletionNotice) {
        long statusId = this.statusId - statusDeletionNotice.getStatusId();
        if (statusId < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        if (statusId > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) statusId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        StatusDeletionNoticeImpl statusDeletionNoticeImpl = (StatusDeletionNoticeImpl) obj;
        if (this.statusId != statusDeletionNoticeImpl.statusId) {
            return false;
        }
        if (this.userId != statusDeletionNoticeImpl.userId) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((int) (this.statusId ^ (this.statusId >>> 32))) * 31) + ((int) (this.userId ^ (this.userId >>> 32)));
    }

    public String toString() {
        return "StatusDeletionNoticeImpl{statusId=" + this.statusId + ", userId=" + this.userId + '}';
    }
}
