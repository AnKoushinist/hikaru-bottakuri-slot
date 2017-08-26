package com.tapjoy.mediation;

public interface TJCustomPlacementListener {
    void onCustomPlacementContentDismiss();

    void onCustomPlacementContentShown();

    void onCustomPlacementFailure(Error error);

    void onCustomPlacementLoad();

    void onCustomPlacementReward(String str, int i);
}
