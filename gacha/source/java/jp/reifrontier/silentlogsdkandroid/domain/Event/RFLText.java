package jp.reifrontier.silentlogsdkandroid.domain.Event;

import com.google.a.a.c;
import jp.reifrontier.silentlogsdkandroid.util.RFLUtils;

public class RFLText {
    @c(a = "default")
    private String defaultText;
    @c(a = "ja")
    private String jaText;

    public String getText() {
        if (!RFLUtils.isJaLocale()) {
            return this.defaultText;
        }
        if (this.jaText != null) {
            return this.jaText;
        }
        return this.defaultText;
    }
}
