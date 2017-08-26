package com.igaworks.adbrix.json;

import android.content.Context;
import com.igaworks.adbrix.db.ScheduleDAO;
import com.igaworks.adbrix.model.RealRewardResultModel;
import com.igaworks.adbrix.model.ScheduleContainer;
import com.igaworks.core.IgawLogger;
import com.igaworks.gson.Gson;
import com.igaworks.model.ParticipationProgressModel;
import com.igaworks.model.ParticipationProgressResponseModel;
import java.util.ArrayList;
import java.util.List;
import org.cocos2dx.lib.GameControllerDelegate;
import org.json.JSONArray;
import org.json.JSONObject;

public class JSON2ScheduleConverter {

    class AnonymousClass1 implements Runnable {
        private final /* synthetic */ Context val$context;
        private final /* synthetic */ String val$json;

        AnonymousClass1(Context context, String str) {
            this.val$context = context;
            this.val$json = str;
        }

        public void run() {
            ScheduleDAO.getInstance().saveSchedule(this.val$context, this.val$json);
        }
    }

    public static ScheduleContainer json2ScheduleV2(Context context, String str) {
        Exception e;
        if (str == null) {
            return null;
        }
        ScheduleContainer scheduleContainer;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("ResultCode")) {
                int i = jSONObject.getInt("ResultCode");
                if (i == GameControllerDelegate.THUMBSTICK_LEFT_Y) {
                    IgawLogger.Logging(context, "IGAW_QA", "ADBrixTracer, schedule received : result code = 1001, load local schedule.", 3, true);
                    return ScheduleDAO.getInstance().getSchedule(context);
                } else if (i != 1) {
                    IgawLogger.Logging(context, "IGAW_QA", "ADBrixTracer, schedule received : result code invalid. result code = " + i, 3, true);
                    return null;
                }
            }
            if (!jSONObject.has("Data")) {
                return null;
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject("Data");
            if (jSONObject2 != null) {
                new Thread(new AnonymousClass1(context, str)).start();
                scheduleContainer = (ScheduleContainer) new Gson().fromJson(jSONObject2.toString(), ScheduleContainer.class);
                try {
                    if (!jSONObject2.has("Schedule")) {
                        return null;
                    }
                    jSONObject2.getJSONObject("Schedule").has("Engagement");
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    return scheduleContainer;
                }
            }
            scheduleContainer = null;
            return scheduleContainer;
        } catch (Exception e3) {
            Exception exception = e3;
            scheduleContainer = null;
            e = exception;
            e.printStackTrace();
            return scheduleContainer;
        }
    }

    public static ParticipationProgressResponseModel json2ParticipationProgressModel(String str) {
        Exception e;
        if (str == null) {
            return null;
        }
        ParticipationProgressResponseModel participationProgressResponseModel;
        try {
            JSONObject jSONObject = new JSONObject(str);
            participationProgressResponseModel = new ParticipationProgressResponseModel();
            try {
                if (jSONObject.has("Result")) {
                    participationProgressResponseModel.setResult(jSONObject.getBoolean("Result"));
                }
                if (jSONObject.has("ResultCode")) {
                    participationProgressResponseModel.setResultCode(jSONObject.getInt("ResultCode"));
                }
                if (jSONObject.has("ResultMsg")) {
                    participationProgressResponseModel.setResultMessage(jSONObject.getString("ResultMsg"));
                }
                if (!jSONObject.has("Data") || jSONObject.isNull("Data")) {
                    return participationProgressResponseModel;
                }
                JSONArray jSONArray = jSONObject.getJSONArray("Data");
                if (jSONArray == null) {
                    return participationProgressResponseModel;
                }
                List arrayList = new ArrayList();
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    if (jSONObject2.has("ConversionKey")) {
                        arrayList.add(new ParticipationProgressModel(jSONObject2.getInt("ConversionKey")));
                    }
                }
                participationProgressResponseModel.setData(arrayList);
                return participationProgressResponseModel;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            participationProgressResponseModel = null;
            e = exception;
            e.printStackTrace();
            return participationProgressResponseModel;
        }
    }

    public static RealRewardResultModel json2RealReward(String str) {
        Exception e;
        if (str == null) {
            return null;
        }
        RealRewardResultModel realRewardResultModel;
        try {
            JSONObject jSONObject = new JSONObject(str);
            realRewardResultModel = new RealRewardResultModel();
            try {
                if (jSONObject.has("Result")) {
                    realRewardResultModel.setResult(jSONObject.getBoolean("Result"));
                }
                if (jSONObject.has("ResultCode")) {
                    realRewardResultModel.setResultCode(jSONObject.getInt("ResultCode"));
                }
                if (jSONObject.has("ResultMsg")) {
                    realRewardResultModel.setResultMessage(jSONObject.getString("ResultMsg"));
                }
                if (!jSONObject.has("Data") || jSONObject.isNull("Data")) {
                    return realRewardResultModel;
                }
                JSONObject jSONObject2 = jSONObject.getJSONObject("Data");
                if (jSONObject2 == null) {
                    return realRewardResultModel;
                }
                if (jSONObject2.has("SessionNo") && !jSONObject2.isNull("SessionNo")) {
                    realRewardResultModel.setSessionNo(jSONObject2.getLong("SessionNo"));
                }
                if (jSONObject2.has("SuccessMsg") && !jSONObject2.isNull("SuccessMsg")) {
                    realRewardResultModel.setSuccessMsg(jSONObject2.getString("SuccessMsg"));
                }
                if (jSONObject2.has("FailMsg") && !jSONObject2.isNull("FailMsg")) {
                    realRewardResultModel.setFailMsg(jSONObject2.getString("FailMsg"));
                }
                if (jSONObject2.has("RewardName") && !jSONObject2.isNull("RewardName")) {
                    realRewardResultModel.setRewardName(jSONObject2.getString("RewardName"));
                }
                if (jSONObject2.has("RewardQuantity") && !jSONObject2.isNull("RewardQuantity")) {
                    realRewardResultModel.setRewardQuantity(jSONObject2.getInt("RewardQuantity"));
                }
                if (jSONObject2.has("RewardImage") && !jSONObject2.isNull("RewardImage")) {
                    realRewardResultModel.setRewardImage(jSONObject2.getString("RewardImage"));
                }
                if (jSONObject2.has("StatusCodes") && !jSONObject2.isNull("StatusCodes")) {
                    realRewardResultModel.setStatusCodes(jSONObject2.getInt("StatusCodes"));
                }
                if (!jSONObject2.has("Type") || jSONObject2.isNull("Type")) {
                    return realRewardResultModel;
                }
                realRewardResultModel.setType(jSONObject2.getString("Type"));
                return realRewardResultModel;
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return realRewardResultModel;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            realRewardResultModel = null;
            e = exception;
            e.printStackTrace();
            return realRewardResultModel;
        }
    }
}
