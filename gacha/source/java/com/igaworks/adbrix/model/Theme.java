package com.igaworks.adbrix.model;

public class Theme {
    private String CirclePlayBtn;
    private String CloseBtn;
    private String FirstUnitBGColorForOneStep;
    private String MissionCheckOff;
    private String MissionCheckOn;
    private String PlayBtnAreaBG;
    private String RewardUnitBGColorForOneStep;
    private String SecondUnitBGColorForOneStep;
    private String SelectedAppArrow;
    private String SlideLeftBtn;
    private String SlideRightBtn;
    private String SquarePlayBtn;
    private String StepArrow;

    public String getCirclePlayBtn() {
        if (this.CirclePlayBtn == null) {
            this.CirclePlayBtn = "http://static.adbrix.igaworks.com/adbrix_res/sdk_res/play_bt_circle.png";
        }
        return this.CirclePlayBtn;
    }

    public String getSquarePlayBtn() {
        if (this.SquarePlayBtn == null) {
            this.SquarePlayBtn = "http://static.adbrix.igaworks.com/adbrix_res/sdk_res/play_bt_square.png";
        }
        return this.SquarePlayBtn;
    }

    public String getMissionCheckOff() {
        if (this.MissionCheckOff == null) {
            this.MissionCheckOff = "http://static.adbrix.igaworks.com/adbrix_res/sdk_res/mission_check_off.png";
        }
        return this.MissionCheckOff;
    }

    public String getMissionCheckOn() {
        if (this.MissionCheckOn == null) {
            this.MissionCheckOn = "http://static.adbrix.igaworks.com/adbrix_res/sdk_res/mission_check_on.png";
        }
        return this.MissionCheckOn;
    }

    public String getPlayBtnAreaBG() {
        if (this.PlayBtnAreaBG == null) {
            this.PlayBtnAreaBG = "http://static.adbrix.igaworks.com/adbrix_res/sdk_res/bg_pt.png";
        }
        return this.PlayBtnAreaBG;
    }

    public String getSlideLeftBtn() {
        if (this.SlideLeftBtn == null) {
            this.SlideLeftBtn = "http://static.adbrix.igaworks.com/adbrix_res/sdk_res/img_slide_left.png";
        }
        return this.SlideLeftBtn;
    }

    public String getSlideRightBtn() {
        if (this.SlideRightBtn == null) {
            this.SlideRightBtn = "http://static.adbrix.igaworks.com/adbrix_res/sdk_res/img_slide_right.png";
        }
        return this.SlideRightBtn;
    }

    public String getStepArrow() {
        if (this.StepArrow == null) {
            this.StepArrow = "http://static.adbrix.igaworks.com/adbrix_res/sdk_res/step_arrow.png";
        }
        return this.StepArrow;
    }

    public String getCloseBtn() {
        if (this.CloseBtn == null) {
            this.CloseBtn = "http://static.adbrix.igaworks.com/adbrix_res/sdk_res/popup_close_bt.png";
        }
        return this.CloseBtn;
    }

    public String getSelectedAppArrow() {
        if (this.SelectedAppArrow == null) {
            this.SelectedAppArrow = "http://static.adbrix.igaworks.com/adbrix_res/sdk_res/app_select_arrow.png";
        }
        return this.SelectedAppArrow;
    }

    public String getFirstUnitBGColorForOneStep() {
        if (this.FirstUnitBGColorForOneStep == null) {
            this.FirstUnitBGColorForOneStep = "#24e6e8";
        }
        return this.FirstUnitBGColorForOneStep;
    }

    public String getSecondUnitBGColorForOneStep() {
        if (this.SecondUnitBGColorForOneStep == null) {
            this.SecondUnitBGColorForOneStep = "#24e6e8";
        }
        return this.SecondUnitBGColorForOneStep;
    }

    public String getRewardUnitBGColorForOneStep() {
        if (this.RewardUnitBGColorForOneStep == null) {
            this.RewardUnitBGColorForOneStep = "#fbd348";
        }
        return this.RewardUnitBGColorForOneStep;
    }
}
