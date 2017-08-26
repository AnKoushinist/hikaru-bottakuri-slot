package com.igaworks.adbrix.json;

import com.igaworks.adbrix.model.ViralInfoModel;
import com.igaworks.adbrix.model.ViralUrlModel;
import org.json.JSONObject;

public class JSON2ViralConverter {
    public static ViralInfoModel convert2ViralInfo(String str) {
        try {
            ViralInfoModel viralInfoModel = new ViralInfoModel();
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("IsTest")) {
                viralInfoModel.setTest(jSONObject.getBoolean("IsTest"));
            }
            if (jSONObject.has("Result")) {
                viralInfoModel.setResult(jSONObject.getBoolean("Result"));
            }
            if (jSONObject.has("ResultCode")) {
                viralInfoModel.setResultCode(jSONObject.getInt("ResultCode"));
            }
            if (jSONObject.has("ResultMsg")) {
                viralInfoModel.setResultMsg(jSONObject.getString("ResultMsg"));
            }
            if (jSONObject.has("ImageURL")) {
                viralInfoModel.setImageURL(jSONObject.getString("ImageURL"));
            }
            if (jSONObject.has("ItemName")) {
                viralInfoModel.setItemName(jSONObject.getString("ItemName"));
            }
            if (!jSONObject.has("ItemQuantity")) {
                return viralInfoModel;
            }
            viralInfoModel.setItemQuantity(jSONObject.getString("ItemQuantity"));
            return viralInfoModel;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ViralUrlModel convert2ViralUrl(String str) {
        try {
            ViralUrlModel viralUrlModel = new ViralUrlModel();
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("IsTest")) {
                viralUrlModel.setTest(jSONObject.getBoolean("IsTest"));
            }
            if (jSONObject.has("Result")) {
                viralUrlModel.setResult(jSONObject.getBoolean("Result"));
            }
            if (jSONObject.has("ResultCode")) {
                viralUrlModel.setResultCode(jSONObject.getInt("ResultCode"));
            }
            if (jSONObject.has("ResultMsg")) {
                viralUrlModel.setResultMsg(jSONObject.getString("ResultMsg"));
            }
            if (!jSONObject.has("TrackingURL")) {
                return viralUrlModel;
            }
            viralUrlModel.setTrackingURL(jSONObject.getString("TrackingURL"));
            return viralUrlModel;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
