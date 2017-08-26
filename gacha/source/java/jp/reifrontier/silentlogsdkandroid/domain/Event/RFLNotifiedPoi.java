package jp.reifrontier.silentlogsdkandroid.domain.Event;

import com.google.a.a.c;

public class RFLNotifiedPoi {
    @c(a = "event_id")
    private Integer eventId;
    @c(a = "lat")
    private Double lat;
    @c(a = "lon")
    private Double lon;
    @c(a = "name")
    private String name;
    @c(a = "place_id")
    private String placeId;
    @c(a = "poi_id")
    private Integer poiId;
    @c(a = "poi_type")
    private Integer poiType;
    @c(a = "range_radius")
    private Float rangeRadius;
    @c(a = "timing_index")
    private Integer timingIndex;

    public RFLNotifiedPoi(Integer num, String str, Integer num2, Double d, Double d2, Float f, Integer num3, String str2, Integer num4) {
        this.poiId = num;
        this.poiType = num2;
        this.placeId = str;
        this.lat = d;
        this.lon = d2;
        this.rangeRadius = f;
        this.timingIndex = num3;
        this.name = str2;
        this.eventId = num4;
    }

    public Integer getPoiId() {
        return this.poiId;
    }

    public Integer getPoiType() {
        return this.poiType;
    }

    public Double getLat() {
        return this.lat;
    }

    public Double getLon() {
        return this.lon;
    }

    public Float getRangeRadius() {
        return this.rangeRadius;
    }

    public Integer getTimingIndex() {
        return this.timingIndex;
    }

    public String getName() {
        return this.name;
    }

    public Integer getEventId() {
        return this.eventId;
    }

    public String getPlaceId() {
        return this.placeId;
    }
}
