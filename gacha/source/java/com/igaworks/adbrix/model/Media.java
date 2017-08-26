package com.igaworks.adbrix.model;

public class Media {
    private Language Language;
    private String RewardIcon;
    private Theme Theme;

    public String getRewardIcon() {
        return this.RewardIcon;
    }

    public Theme getTheme() {
        return this.Theme;
    }

    public void setTheme(Theme theme) {
        this.Theme = theme;
    }

    public Language getLanguage() {
        return this.Language;
    }

    public void setLanguage(Language language) {
        this.Language = language;
    }
}
