package com.tapjoy.mediation;

import android.content.Context;
import android.os.Bundle;

public interface TJCustomPlacement {
    void requestContentWithCustomPlacementParams(Context context, TJCustomPlacementListener tJCustomPlacementListener, Bundle bundle);

    void showContent();
}
