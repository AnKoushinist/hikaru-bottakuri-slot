package twitter4j;

class HashtagEntityJSONImpl extends EntityIndex implements HashtagEntity, SymbolEntity {
    private static final long serialVersionUID = -5317828991902848906L;
    private String text;

    HashtagEntityJSONImpl(JSONObject jSONObject) {
        init(jSONObject);
    }

    HashtagEntityJSONImpl(int i, int i2, String str) {
        setStart(i);
        setEnd(i2);
        this.text = str;
    }

    HashtagEntityJSONImpl() {
    }

    private void init(JSONObject jSONObject) {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("indices");
            setStart(jSONArray.getInt(0));
            setEnd(jSONArray.getInt(1));
            if (!jSONObject.isNull("text")) {
                this.text = jSONObject.getString("text");
            }
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public String getText() {
        return this.text;
    }

    public int getStart() {
        return super.getStart();
    }

    public int getEnd() {
        return super.getEnd();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HashtagEntityJSONImpl hashtagEntityJSONImpl = (HashtagEntityJSONImpl) obj;
        if (this.text != null) {
            if (this.text.equals(hashtagEntityJSONImpl.text)) {
                return true;
            }
        } else if (hashtagEntityJSONImpl.text == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.text != null ? this.text.hashCode() : 0;
    }

    public String toString() {
        return "HashtagEntityJSONImpl{text='" + this.text + '\'' + '}';
    }
}
