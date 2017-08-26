package jp.reifrontier.silentlogsdkandroid.domain.Event;

import com.google.a.a.c;

public class RFLPoi {
    @c(a = "lat")
    private Double lat;
    @c(a = "lon")
    private Double lon;
    @c(a = "name")
    private RFLText name;
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

    public Integer getPoiId() {
        return this.poiId;
    }

    public String getPlaceId() {
        return this.placeId;
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

    public RFLText getName() {
        return this.name;
    }
}
