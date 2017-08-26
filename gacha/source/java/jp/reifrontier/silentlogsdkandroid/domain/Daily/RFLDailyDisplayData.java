package jp.reifrontier.silentlogsdkandroid.domain.Daily;

public class RFLDailyDisplayData {
    private RFLDaily daily;
    private long timestamp;

    public RFLDaily getDaily() {
        return this.daily;
    }

    public void setDaily(RFLDaily rFLDaily) {
        this.daily = rFLDaily;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }
}
