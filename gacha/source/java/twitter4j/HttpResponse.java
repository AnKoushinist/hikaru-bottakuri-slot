package twitter4j;

import com.d.a.a.c;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import twitter4j.conf.ConfigurationContext;

public abstract class HttpResponse {
    private static final Logger logger = Logger.getLogger(HttpResponseImpl.class);
    protected final HttpClientConfiguration CONF;
    protected InputStream is;
    private JSONObject json;
    private JSONArray jsonArray;
    protected String responseAsString;
    protected int statusCode;
    private boolean streamConsumed;

    public abstract void disconnect();

    public abstract String getResponseHeader(String str);

    public abstract Map<String, List<String>> getResponseHeaderFields();

    HttpResponse() {
        this.responseAsString = null;
        this.streamConsumed = false;
        this.json = null;
        this.jsonArray = null;
        this.CONF = ConfigurationContext.getInstance().getHttpClientConfiguration();
    }

    public HttpResponse(HttpClientConfiguration httpClientConfiguration) {
        this.responseAsString = null;
        this.streamConsumed = false;
        this.json = null;
        this.jsonArray = null;
        this.CONF = httpClientConfiguration;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public InputStream asStream() {
        if (!this.streamConsumed) {
            return this.is;
        }
        throw new IllegalStateException("Stream has already been consumed.");
    }

    public String asString() {
        InputStream asStream;
        Throwable e;
        InputStream inputStream;
        Throwable th;
        Object obj;
        if (this.responseAsString == null) {
            BufferedReader bufferedReader = null;
            try {
                asStream = asStream();
                if (asStream == null) {
                    if (asStream != null) {
                        try {
                            asStream.close();
                        } catch (IOException e2) {
                        }
                    }
                    if (null != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e3) {
                        }
                    }
                    disconnectForcibly();
                    return null;
                }
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(asStream, c.DEFAULT_CHARSET));
                    try {
                        StringBuilder stringBuilder = new StringBuilder();
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            stringBuilder.append(readLine).append("\n");
                        }
                        this.responseAsString = stringBuilder.toString();
                        logger.debug(this.responseAsString);
                        asStream.close();
                        this.streamConsumed = true;
                        if (asStream != null) {
                            try {
                                asStream.close();
                            } catch (IOException e4) {
                            }
                        }
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e5) {
                            }
                        }
                        disconnectForcibly();
                    } catch (IOException e6) {
                        e = e6;
                        InputStream inputStream2 = asStream;
                        BufferedReader bufferedReader2 = bufferedReader;
                        inputStream = inputStream2;
                        try {
                            throw new TwitterException(e.getMessage(), e);
                        } catch (Throwable th2) {
                            e = th2;
                            inputStream2 = inputStream;
                            bufferedReader = bufferedReader2;
                            asStream = inputStream2;
                            if (asStream != null) {
                                try {
                                    asStream.close();
                                } catch (IOException e7) {
                                }
                            }
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e8) {
                                }
                            }
                            disconnectForcibly();
                            throw e;
                        }
                    } catch (Throwable th3) {
                        e = th3;
                        if (asStream != null) {
                            asStream.close();
                        }
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        disconnectForcibly();
                        throw e;
                    }
                } catch (Throwable e9) {
                    th = e9;
                    inputStream = asStream;
                    obj = null;
                    e = th;
                    throw new TwitterException(e.getMessage(), e);
                } catch (Throwable e92) {
                    th = e92;
                    bufferedReader = null;
                    e = th;
                    if (asStream != null) {
                        asStream.close();
                    }
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    disconnectForcibly();
                    throw e;
                }
            } catch (Throwable e922) {
                obj = null;
                e = e922;
                Object obj2 = null;
                throw new TwitterException(e.getMessage(), e);
            } catch (Throwable e9222) {
                asStream = null;
                th = e9222;
                bufferedReader = null;
                e = th;
                if (asStream != null) {
                    asStream.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                disconnectForcibly();
                throw e;
            }
        }
        return this.responseAsString;
    }

    public JSONObject asJSONObject() {
        if (this.json == null) {
            Reader reader = null;
            try {
                if (this.responseAsString == null) {
                    reader = asReader();
                    this.json = new JSONObject(new JSONTokener(reader));
                } else {
                    this.json = new JSONObject(this.responseAsString);
                }
                if (this.CONF.isPrettyDebugEnabled()) {
                    logger.debug(this.json.toString(1));
                } else {
                    String str;
                    Logger logger = logger;
                    if (this.responseAsString != null) {
                        str = this.responseAsString;
                    } else {
                        str = this.json.toString();
                    }
                    logger.debug(str);
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                    }
                }
                disconnectForcibly();
            } catch (Throwable e2) {
                if (this.responseAsString == null) {
                    throw new TwitterException(e2.getMessage(), e2);
                }
                throw new TwitterException(e2.getMessage() + ":" + this.responseAsString, e2);
            } catch (Throwable th) {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e3) {
                    }
                }
                disconnectForcibly();
            }
        }
        return this.json;
    }

    public JSONArray asJSONArray() {
        if (this.jsonArray == null) {
            Reader reader = null;
            try {
                if (this.responseAsString == null) {
                    reader = asReader();
                    this.jsonArray = new JSONArray(new JSONTokener(reader));
                } else {
                    this.jsonArray = new JSONArray(this.responseAsString);
                }
                if (this.CONF.isPrettyDebugEnabled()) {
                    logger.debug(this.jsonArray.toString(1));
                } else {
                    String str;
                    Logger logger = logger;
                    if (this.responseAsString != null) {
                        str = this.responseAsString;
                    } else {
                        str = this.jsonArray.toString();
                    }
                    logger.debug(str);
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                    }
                }
                disconnectForcibly();
            } catch (Throwable e2) {
                if (logger.isDebugEnabled()) {
                    throw new TwitterException(e2.getMessage() + ":" + this.responseAsString, e2);
                }
                throw new TwitterException(e2.getMessage(), e2);
            } catch (Throwable th) {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e3) {
                    }
                }
                disconnectForcibly();
            }
        }
        return this.jsonArray;
    }

    public Reader asReader() {
        try {
            return new BufferedReader(new InputStreamReader(this.is, c.DEFAULT_CHARSET));
        } catch (UnsupportedEncodingException e) {
            return new InputStreamReader(this.is);
        }
    }

    private void disconnectForcibly() {
        try {
            disconnect();
        } catch (Exception e) {
        }
    }

    public String toString() {
        return "HttpResponse{statusCode=" + this.statusCode + ", responseAsString='" + this.responseAsString + '\'' + ", is=" + this.is + ", streamConsumed=" + this.streamConsumed + '}';
    }
}
