package jp.reifrontier.silentlogsdkandroid;

import com.raizlabs.android.dbflow.structure.BaseModel;

public class RFLEntityDaily extends BaseModel {
    public String dailies;
    public String date;
    long id;
    public boolean submit;
    public long timestamp;
    public String timezone;
}
