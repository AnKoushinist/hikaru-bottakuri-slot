package com.igaworks.commerce.core;

import android.content.Context;
import com.igaworks.core.IgawLogger;
import com.igaworks.core.RequestParameter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CommerceRequestParameter {
    private static RequestParameter parameter;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);

    public static String getCommerceEventParameter(Context context, String str, boolean z, List<String> list) {
        if (parameter == null) {
            parameter = RequestParameter.getATRequestParameter(context);
        }
        try {
            JSONObject jSONObject = new JSONObject(parameter.getTrackingParameterForADBrix(context, null, null, null, str, z));
            JSONArray jSONArray = new JSONArray();
            for (String jSONObject2 : list) {
                try {
                    jSONArray.put(new JSONObject(jSONObject2));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            jSONObject.put("commerce_event_info", jSONArray);
            IgawLogger.Logging(context, "IGAW_QA", "CommerceParameter > commerce event Parameter : " + jSONObject.toString(), 3);
            return jSONObject.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return "{}";
        }
    }
}
