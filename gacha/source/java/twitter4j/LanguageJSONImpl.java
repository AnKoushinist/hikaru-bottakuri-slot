package twitter4j;

import com.unity3d.ads.metadata.MediationMetaData;
import twitter4j.api.HelpResources.Language;
import twitter4j.conf.Configuration;

public class LanguageJSONImpl implements Language {
    private String code;
    private String name;
    private String status;

    LanguageJSONImpl(JSONObject jSONObject) {
        init(jSONObject);
    }

    private void init(JSONObject jSONObject) {
        try {
            this.name = jSONObject.getString(MediationMetaData.KEY_NAME);
            this.code = jSONObject.getString("code");
            this.status = jSONObject.getString("status");
        } catch (Throwable e) {
            throw new TwitterException(e.getMessage() + ":" + jSONObject.toString(), e);
        }
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public String getStatus() {
        return this.status;
    }

    static ResponseList<Language> createLanguageList(HttpResponse httpResponse, Configuration configuration) {
        return createLanguageList(httpResponse.asJSONArray(), httpResponse, configuration);
    }

    static ResponseList<Language> createLanguageList(JSONArray jSONArray, HttpResponse httpResponse, Configuration configuration) {
        if (configuration.isJSONStoreEnabled()) {
            TwitterObjectFactory.clearThreadLocalMap();
        }
        try {
            int length = jSONArray.length();
            ResponseList<Language> responseListImpl = new ResponseListImpl(length, httpResponse);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                LanguageJSONImpl languageJSONImpl = new LanguageJSONImpl(jSONObject);
                responseListImpl.add(languageJSONImpl);
                if (configuration.isJSONStoreEnabled()) {
                    TwitterObjectFactory.registerJSONObject(languageJSONImpl, jSONObject);
                }
            }
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.registerJSONObject(responseListImpl, jSONArray);
            }
            return responseListImpl;
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }
}
