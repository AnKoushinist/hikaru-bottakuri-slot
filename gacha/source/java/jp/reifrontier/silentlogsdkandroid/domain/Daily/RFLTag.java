package jp.reifrontier.silentlogsdkandroid.domain.Daily;

import com.google.a.a.a;
import com.google.a.a.c;

public class RFLTag {
    @a
    @c(a = "fromDate")
    private int fromDate;
    @a
    @c(a = "tagName")
    private String tagName;
    @a
    @c(a = "tagOwner")
    private String tagOwner;
    @a
    @c(a = "tagType")
    private String tagType;
    @a
    @c(a = "toDate")
    private int toDate;

    public RFLTag(String str, String str2, String str3) {
        this.tagName = str;
        this.tagOwner = str2;
        this.tagType = str3;
    }

    public String getTagName() {
        return this.tagName;
    }

    public String getTagOwner() {
        return this.tagOwner;
    }

    public String getTagType() {
        return this.tagType;
    }
}
