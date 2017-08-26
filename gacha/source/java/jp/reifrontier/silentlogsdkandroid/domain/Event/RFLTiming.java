package jp.reifrontier.silentlogsdkandroid.domain.Event;

import com.google.a.a.c;

public class RFLTiming {
    @c(a = "activity")
    private RFLEventActivity eventActivity;
    @c(a = "offset")
    private Integer offset;
    @c(a = "repeat")
    private Integer repeat;
    @c(a = "screen_unlock")
    private RFLScreenState screenState;

    public class RFLEventActivity {
        @c(a = "sty")
        private Integer sty;
        @c(a = "trp")
        private Integer trp;
        @c(a = "wlk")
        private Integer walk;

        public RFL_TIMING getTiming() {
            if (this.sty.intValue() == 1 && this.walk.intValue() == 1 && this.trp.intValue() == 1) {
                return RFL_TIMING.CHECK_ALL;
            }
            if (this.sty.intValue() == 1 && this.walk.intValue() == 0 && this.trp.intValue() == 0) {
                return RFL_TIMING.CHECK_STAY;
            }
            if (this.sty.intValue() == 1 && this.walk.intValue() == 1 && this.trp.intValue() == 0) {
                return RFL_TIMING.CHECK_STAY_WALK;
            }
            if (this.sty.intValue() == 1 && this.walk.intValue() == 0 && this.trp.intValue() == 1) {
                return RFL_TIMING.CHECK_STAY_TRANS;
            }
            if (this.sty.intValue() == 0 && this.walk.intValue() == 1 && this.trp.intValue() == 0) {
                return RFL_TIMING.CHECK_WALK;
            }
            if (this.sty.intValue() == 0 && this.walk.intValue() == 1 && this.trp.intValue() == 1) {
                return RFL_TIMING.CHECK_WALK_TRANS;
            }
            if (this.sty.intValue() == 0 && this.walk.intValue() == 0 && this.trp.intValue() == 1) {
                return RFL_TIMING.CHECK_TRANS;
            }
            return RFL_TIMING.CHECK_NONE;
        }
    }

    public class RFLScreenState {
        @c(a = "_NG_")
        private Integer NG;
        @c(a = "on")
        private Integer on;

        public Integer getNG() {
            return this.NG;
        }

        public Integer getOn() {
            return this.on;
        }
    }

    public enum RFL_TIMING {
        CHECK_NONE,
        CHECK_STAY,
        CHECK_STAY_WALK,
        CHECK_STAY_TRANS,
        CHECK_WALK,
        CHECK_WALK_TRANS,
        CHECK_TRANS,
        CHECK_ALL
    }

    public RFLEventActivity getEventActivity() {
        return this.eventActivity;
    }

    public Integer getOffset() {
        return this.offset;
    }

    public Integer getRepeat() {
        return this.repeat;
    }

    public RFLScreenState getScreenState() {
        return this.screenState;
    }
}
