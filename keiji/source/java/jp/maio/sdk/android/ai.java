package jp.maio.sdk.android;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Future;

class ai extends ArrayList {
    ai() {
    }

    public void a() {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((Future) it.next()).get();
        }
    }
}
