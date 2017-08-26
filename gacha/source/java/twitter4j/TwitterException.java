package twitter4j;

import com.tapjoy.TJAdUnitConstants.String;
import java.io.IOException;
import java.util.List;
import org.cocos2dx.lib.BuildConfig;

public class TwitterException extends Exception implements HttpResponseCode, TwitterResponse {
    private static final String[] FILTER = new String[]{"twitter4j"};
    private static final long serialVersionUID = 6006561839051121336L;
    private int errorCode;
    private String errorMessage;
    private ExceptionDiagnosis exceptionDiagnosis;
    private boolean nested;
    private HttpResponse response;
    private int statusCode;

    public TwitterException(String str, Throwable th) {
        super(str, th);
        this.statusCode = -1;
        this.errorCode = -1;
        this.exceptionDiagnosis = null;
        this.errorMessage = null;
        this.nested = false;
        decode(str);
    }

    public TwitterException(String str) {
        this(str, (Throwable) null);
    }

    public TwitterException(Exception exception) {
        this(exception.getMessage(), (Throwable) exception);
        if (exception instanceof TwitterException) {
            ((TwitterException) exception).setNested();
        }
    }

    public TwitterException(String str, HttpResponse httpResponse) {
        this(str);
        this.response = httpResponse;
        this.statusCode = httpResponse.getStatusCode();
    }

    public TwitterException(String str, Exception exception, int i) {
        this(str, (Throwable) exception);
        this.statusCode = i;
    }

    public String getMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.errorMessage == null || this.errorCode == -1) {
            stringBuilder.append(super.getMessage());
        } else {
            stringBuilder.append("message - ").append(this.errorMessage).append("\n");
            stringBuilder.append("code - ").append(this.errorCode).append("\n");
        }
        if (this.statusCode != -1) {
            return getCause(this.statusCode) + "\n" + stringBuilder.toString();
        }
        return stringBuilder.toString();
    }

    private void decode(String str) {
        if (str != null && str.startsWith("{")) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (!jSONObject.isNull("errors")) {
                    jSONObject = jSONObject.getJSONArray("errors").getJSONObject(0);
                    this.errorMessage = jSONObject.getString(String.MESSAGE);
                    this.errorCode = ParseUtil.getInt("code", jSONObject);
                }
            } catch (JSONException e) {
            }
        }
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getResponseHeader(String str) {
        if (this.response != null) {
            List list = (List) this.response.getResponseHeaderFields().get(str);
            if (list.size() > 0) {
                return (String) list.get(0);
            }
        }
        return null;
    }

    public RateLimitStatus getRateLimitStatus() {
        if (this.response == null) {
            return null;
        }
        return JSONImplFactory.createRateLimitStatusFromResponseHeader(this.response);
    }

    public int getAccessLevel() {
        return ParseUtil.toAccessLevel(this.response);
    }

    public int getRetryAfter() {
        if (this.statusCode == HttpResponseCode.BAD_REQUEST) {
            RateLimitStatus rateLimitStatus = getRateLimitStatus();
            if (rateLimitStatus != null) {
                return rateLimitStatus.getSecondsUntilReset();
            }
            return -1;
        } else if (this.statusCode != HttpResponseCode.ENHANCE_YOUR_CLAIM) {
            return -1;
        } else {
            try {
                String responseHeader = this.response.getResponseHeader("Retry-After");
                if (responseHeader != null) {
                    return Integer.valueOf(responseHeader).intValue();
                }
                return -1;
            } catch (NumberFormatException e) {
                return -1;
            }
        }
    }

    public boolean isCausedByNetworkIssue() {
        return getCause() instanceof IOException;
    }

    public boolean exceededRateLimitation() {
        return (this.statusCode == HttpResponseCode.BAD_REQUEST && getRateLimitStatus() != null) || this.statusCode == HttpResponseCode.ENHANCE_YOUR_CLAIM || this.statusCode == HttpResponseCode.TOO_MANY_REQUESTS;
    }

    public boolean resourceNotFound() {
        return this.statusCode == HttpResponseCode.NOT_FOUND;
    }

    public String getExceptionCode() {
        return getExceptionDiagnosis().asHexString();
    }

    private ExceptionDiagnosis getExceptionDiagnosis() {
        if (this.exceptionDiagnosis == null) {
            this.exceptionDiagnosis = new ExceptionDiagnosis(this, FILTER);
        }
        return this.exceptionDiagnosis;
    }

    void setNested() {
        this.nested = true;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public boolean isErrorMessageAvailable() {
        return this.errorMessage != null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TwitterException twitterException = (TwitterException) obj;
        if (this.errorCode != twitterException.errorCode) {
            return false;
        }
        if (this.nested != twitterException.nested) {
            return false;
        }
        if (this.statusCode != twitterException.statusCode) {
            return false;
        }
        if (this.errorMessage == null ? twitterException.errorMessage != null : !this.errorMessage.equals(twitterException.errorMessage)) {
            return false;
        }
        if (this.exceptionDiagnosis == null ? twitterException.exceptionDiagnosis != null : !this.exceptionDiagnosis.equals(twitterException.exceptionDiagnosis)) {
            return false;
        }
        if (this.response != null) {
            if (this.response.equals(twitterException.response)) {
                return true;
            }
        } else if (twitterException.response == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int i2 = ((this.statusCode * 31) + this.errorCode) * 31;
        if (this.exceptionDiagnosis != null) {
            hashCode = this.exceptionDiagnosis.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.response != null) {
            hashCode = this.response.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.errorMessage != null) {
            hashCode = this.errorMessage.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + i2) * 31;
        if (this.nested) {
            i = 1;
        }
        return hashCode + i;
    }

    public String toString() {
        String str;
        StringBuilder append = new StringBuilder().append(getMessage());
        if (this.nested) {
            str = BuildConfig.FLAVOR;
        } else {
            str = "\nRelevant discussions can be found on the Internet at:\n\thttp://www.google.co.jp/search?q=" + getExceptionDiagnosis().getStackLineHashAsHex() + " or\n\thttp://www.google.co.jp/search?q=" + getExceptionDiagnosis().getLineNumberHashAsHex();
        }
        append = append.append(str).append("\nTwitterException{");
        if (this.nested) {
            str = BuildConfig.FLAVOR;
        } else {
            str = "exceptionCode=[" + getExceptionCode() + "], ";
        }
        return append.append(str).append("statusCode=").append(this.statusCode).append(", message=").append(this.errorMessage).append(", code=").append(this.errorCode).append(", retryAfter=").append(getRetryAfter()).append(", rateLimitStatus=").append(getRateLimitStatus()).append(", version=").append(Version.getVersion()).append('}').toString();
    }

    private static String getCause(int i) {
        String str;
        switch (i) {
            case HttpResponseCode.NOT_MODIFIED /*304*/:
                str = "There was no new data to return.";
                break;
            case HttpResponseCode.BAD_REQUEST /*400*/:
                str = "The request was invalid. An accompanying error message will explain why. This is the status code will be returned during version 1.0 rate limiting(https://dev.twitter.com/pages/rate-limiting). In API v1.1, a request without authentication is considered invalid and you will get this response.";
                break;
            case HttpResponseCode.UNAUTHORIZED /*401*/:
                str = "Authentication credentials (https://dev.twitter.com/pages/auth) were missing or incorrect. Ensure that you have set valid consumer key/secret, access token/secret, and the system clock is in sync.";
                break;
            case HttpResponseCode.FORBIDDEN /*403*/:
                str = "The request is understood, but it has been refused. An accompanying error message will explain why. This code is used when requests are being denied due to update limits (https://support.twitter.com/articles/15364-about-twitter-limits-update-api-dm-and-following).";
                break;
            case HttpResponseCode.NOT_FOUND /*404*/:
                str = "The URI requested is invalid or the resource requested, such as a user, does not exists. Also returned when the requested format is not supported by the requested method.";
                break;
            case HttpResponseCode.NOT_ACCEPTABLE /*406*/:
                str = "Returned by the Search API when an invalid format is specified in the request.\nReturned by the Streaming API when one or more of the parameters are not suitable for the resource. The track parameter, for example, would throw this error if:\n The track keyword is too long or too short.\n The bounding box specified is invalid.\n No predicates defined for filtered resource, for example, neither track nor follow parameter defined.\n Follow userid cannot be read.";
                break;
            case HttpResponseCode.ENHANCE_YOUR_CLAIM /*420*/:
                str = "Returned by the Search and Trends API when you are being rate limited (https://dev.twitter.com/docs/rate-limiting).\nReturned by the Streaming API:\n Too many login attempts in a short period of time.\n Running too many copies of the same application authenticating with the same account name.";
                break;
            case HttpResponseCode.UNPROCESSABLE_ENTITY /*422*/:
                str = "Returned when an image uploaded to POST account/update_profile_banner(https://dev.twitter.com/docs/api/1/post/account/update_profile_banner) is unable to be processed.";
                break;
            case HttpResponseCode.TOO_MANY_REQUESTS /*429*/:
                str = "Returned in API v1.1 when a request cannot be served due to the application's rate limit having been exhausted for the resource. See Rate Limiting in API v1.1.(https://dev.twitter.com/docs/rate-limiting/1.1)";
                break;
            case HttpResponseCode.INTERNAL_SERVER_ERROR /*500*/:
                str = "Something is broken. Please post to the group (https://dev.twitter.com/docs/support) so the Twitter team can investigate.";
                break;
            case HttpResponseCode.BAD_GATEWAY /*502*/:
                str = "Twitter is down or being upgraded.";
                break;
            case HttpResponseCode.SERVICE_UNAVAILABLE /*503*/:
                str = "The Twitter servers are up, but overloaded with requests. Try again later.";
                break;
            case HttpResponseCode.GATEWAY_TIMEOUT /*504*/:
                str = "The Twitter servers are up, but the request couldn't be serviced due to some failure within our stack. Try again later.";
                break;
            default:
                str = BuildConfig.FLAVOR;
                break;
        }
        return i + ":" + str;
    }
}
