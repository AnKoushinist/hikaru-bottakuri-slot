package twitter4j;

import com.tapjoy.TJAdUnitConstants.String;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class OEmbedRequest implements Serializable {
    private static final long serialVersionUID = 7454130135274547901L;
    private Align align = Align.NONE;
    private boolean hideMedia = true;
    private boolean hideThread = true;
    private String lang;
    private int maxWidth;
    private boolean omitScript = false;
    private String[] related = new String[0];
    private final long statusId;
    private final String url;

    public enum Align {
        LEFT,
        CENTER,
        RIGHT,
        NONE
    }

    public OEmbedRequest(long j, String str) {
        this.statusId = j;
        this.url = str;
    }

    public void setMaxWidth(int i) {
        this.maxWidth = i;
    }

    public OEmbedRequest MaxWidth(int i) {
        this.maxWidth = i;
        return this;
    }

    public void setHideMedia(boolean z) {
        this.hideMedia = z;
    }

    public OEmbedRequest HideMedia(boolean z) {
        this.hideMedia = z;
        return this;
    }

    public void setHideThread(boolean z) {
        this.hideThread = z;
    }

    public OEmbedRequest HideThread(boolean z) {
        this.hideThread = z;
        return this;
    }

    public void setOmitScript(boolean z) {
        this.omitScript = z;
    }

    public OEmbedRequest omitScript(boolean z) {
        this.omitScript = z;
        return this;
    }

    public void setAlign(Align align) {
        this.align = align;
    }

    public OEmbedRequest align(Align align) {
        this.align = align;
        return this;
    }

    public void setRelated(String[] strArr) {
        this.related = strArr;
    }

    public OEmbedRequest related(String[] strArr) {
        this.related = strArr;
        return this;
    }

    public void setLang(String str) {
        this.lang = str;
    }

    public OEmbedRequest lang(String str) {
        this.lang = str;
        return this;
    }

    HttpParameter[] asHttpParameterArray() {
        List arrayList = new ArrayList(12);
        appendParameter("id", this.statusId, arrayList);
        appendParameter(String.URL, this.url, arrayList);
        appendParameter("maxwidth", (long) this.maxWidth, arrayList);
        arrayList.add(new HttpParameter("hide_media", this.hideMedia));
        arrayList.add(new HttpParameter("hide_thread", this.hideThread));
        arrayList.add(new HttpParameter("omit_script", this.omitScript));
        arrayList.add(new HttpParameter("align", this.align.name().toLowerCase()));
        if (this.related.length > 0) {
            appendParameter("related", StringUtil.join(this.related), arrayList);
        }
        appendParameter("lang", this.lang, arrayList);
        return (HttpParameter[]) arrayList.toArray(new HttpParameter[arrayList.size()]);
    }

    private void appendParameter(String str, String str2, List<HttpParameter> list) {
        if (str2 != null) {
            list.add(new HttpParameter(str, str2));
        }
    }

    private void appendParameter(String str, long j, List<HttpParameter> list) {
        if (0 <= j) {
            list.add(new HttpParameter(str, String.valueOf(j)));
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OEmbedRequest oEmbedRequest = (OEmbedRequest) obj;
        if (this.hideMedia != oEmbedRequest.hideMedia) {
            return false;
        }
        if (this.hideThread != oEmbedRequest.hideThread) {
            return false;
        }
        if (this.maxWidth != oEmbedRequest.maxWidth) {
            return false;
        }
        if (this.omitScript != oEmbedRequest.omitScript) {
            return false;
        }
        if (this.statusId != oEmbedRequest.statusId) {
            return false;
        }
        if (this.align != oEmbedRequest.align) {
            return false;
        }
        if (this.lang == null ? oEmbedRequest.lang != null : !this.lang.equals(oEmbedRequest.lang)) {
            return false;
        }
        if (!Arrays.equals(this.related, oEmbedRequest.related)) {
            return false;
        }
        if (this.url != null) {
            if (this.url.equals(oEmbedRequest.url)) {
                return true;
            }
        } else if (oEmbedRequest.url == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i;
        int i2 = 1;
        int i3 = 0;
        int hashCode = ((((this.url != null ? this.url.hashCode() : 0) + (((int) (this.statusId ^ (this.statusId >>> 32))) * 31)) * 31) + this.maxWidth) * 31;
        if (this.hideMedia) {
            i = 1;
        } else {
            i = 0;
        }
        hashCode = (i + hashCode) * 31;
        if (this.hideThread) {
            i = 1;
        } else {
            i = 0;
        }
        i = (i + hashCode) * 31;
        if (!this.omitScript) {
            i2 = 0;
        }
        i2 = (i + i2) * 31;
        if (this.align != null) {
            i = this.align.hashCode();
        } else {
            i = 0;
        }
        i2 = (i + i2) * 31;
        if (this.related != null) {
            i = Arrays.hashCode(this.related);
        } else {
            i = 0;
        }
        i = (i + i2) * 31;
        if (this.lang != null) {
            i3 = this.lang.hashCode();
        }
        return i + i3;
    }

    public String toString() {
        Object obj;
        StringBuilder append = new StringBuilder().append("OEmbedRequest{statusId=").append(this.statusId).append(", url='").append(this.url).append('\'').append(", maxWidth=").append(this.maxWidth).append(", hideMedia=").append(this.hideMedia).append(", hideThread=").append(this.hideThread).append(", omitScript=").append(this.omitScript).append(", align=").append(this.align).append(", related=");
        if (this.related == null) {
            obj = null;
        } else {
            obj = Arrays.asList(this.related);
        }
        return append.append(obj).append(", lang='").append(this.lang).append('\'').append('}').toString();
    }
}
