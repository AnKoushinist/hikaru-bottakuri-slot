package twitter4j;

import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.tapjoy.TapjoyConstants;
import java.io.Serializable;
import java.net.Authenticator;
import java.net.Authenticator.RequestorType;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.cocos2dx.lib.BuildConfig;
import twitter4j.conf.ConfigurationContext;

class HttpClientImpl extends HttpClientBase implements Serializable, HttpResponseCode {
    private static final Map<HttpClientConfiguration, HttpClient> instanceMap = new HashMap(1);
    private static final Logger logger = Logger.getLogger(HttpClientImpl.class);
    private static final long serialVersionUID = -403500272719330534L;

    static {
        try {
            if (Integer.parseInt((String) Class.forName("android.os.Build$VERSION").getField("SDK").get(null)) < 8) {
                System.setProperty("http.keepAlive", TapjoyConstants.TJC_FALSE);
            }
        } catch (Exception e) {
        }
    }

    public HttpClientImpl() {
        super(ConfigurationContext.getInstance().getHttpClientConfiguration());
    }

    public HttpClientImpl(HttpClientConfiguration httpClientConfiguration) {
        super(httpClientConfiguration);
    }

    public static HttpClient getInstance(HttpClientConfiguration httpClientConfiguration) {
        HttpClient httpClient = (HttpClient) instanceMap.get(httpClientConfiguration);
        if (httpClient != null) {
            return httpClient;
        }
        httpClient = new HttpClientImpl(httpClientConfiguration);
        instanceMap.put(httpClientConfiguration, httpClient);
        return httpClient;
    }

    public HttpResponse get(String str) {
        return request(new HttpRequest(RequestMethod.GET, str, null, null, null));
    }

    public HttpResponse post(String str, HttpParameter[] httpParameterArr) {
        return request(new HttpRequest(RequestMethod.POST, str, httpParameterArr, null, null));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public twitter4j.HttpResponse handleRequest(twitter4j.HttpRequest r19) {
        /*
        r18 = this;
        r0 = r18;
        r2 = r0.CONF;
        r2 = r2.getHttpRetryCount();
        r8 = r2 + 1;
        r2 = 0;
        r3 = 0;
        r7 = r3;
    L_0x000d:
        if (r7 >= r8) goto L_0x02cc;
    L_0x000f:
        r6 = -1;
        r4 = 0;
        r3 = r19.getURL();	 Catch:{ all -> 0x0323 }
        r0 = r18;
        r9 = r0.getConnection(r3);	 Catch:{ all -> 0x0323 }
        r3 = 1;
        r9.setDoInput(r3);	 Catch:{ all -> 0x0323 }
        r0 = r18;
        r1 = r19;
        r0.setHeaders(r1, r9);	 Catch:{ all -> 0x0323 }
        r3 = r19.getMethod();	 Catch:{ all -> 0x0323 }
        r3 = r3.name();	 Catch:{ all -> 0x0323 }
        r9.setRequestMethod(r3);	 Catch:{ all -> 0x0323 }
        r3 = r19.getMethod();	 Catch:{ all -> 0x0323 }
        r5 = twitter4j.RequestMethod.POST;	 Catch:{ all -> 0x0323 }
        if (r3 != r5) goto L_0x0334;
    L_0x0039:
        r3 = r19.getParameters();	 Catch:{ all -> 0x0323 }
        r3 = twitter4j.HttpParameter.containsFile(r3);	 Catch:{ all -> 0x0323 }
        if (r3 == 0) goto L_0x0263;
    L_0x0043:
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0323 }
        r3.<init>();	 Catch:{ all -> 0x0323 }
        r5 = "----Twitter4J-upload";
        r3 = r3.append(r5);	 Catch:{ all -> 0x0323 }
        r10 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0323 }
        r3 = r3.append(r10);	 Catch:{ all -> 0x0323 }
        r3 = r3.toString();	 Catch:{ all -> 0x0323 }
        r5 = "Content-Type";
        r10 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0323 }
        r10.<init>();	 Catch:{ all -> 0x0323 }
        r11 = "multipart/form-data; boundary=";
        r10 = r10.append(r11);	 Catch:{ all -> 0x0323 }
        r10 = r10.append(r3);	 Catch:{ all -> 0x0323 }
        r10 = r10.toString();	 Catch:{ all -> 0x0323 }
        r9.setRequestProperty(r5, r10);	 Catch:{ all -> 0x0323 }
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0323 }
        r5.<init>();	 Catch:{ all -> 0x0323 }
        r10 = "--";
        r5 = r5.append(r10);	 Catch:{ all -> 0x0323 }
        r3 = r5.append(r3);	 Catch:{ all -> 0x0323 }
        r10 = r3.toString();	 Catch:{ all -> 0x0323 }
        r3 = 1;
        r9.setDoOutput(r3);	 Catch:{ all -> 0x0323 }
        r4 = r9.getOutputStream();	 Catch:{ all -> 0x0323 }
        r11 = new java.io.DataOutputStream;	 Catch:{ all -> 0x0136 }
        r11.<init>(r4);	 Catch:{ all -> 0x0136 }
        r12 = r19.getParameters();	 Catch:{ all -> 0x0136 }
        r13 = r12.length;	 Catch:{ all -> 0x0136 }
        r3 = 0;
        r5 = r3;
    L_0x0099:
        if (r5 >= r13) goto L_0x01ce;
    L_0x009b:
        r14 = r12[r5];	 Catch:{ all -> 0x0136 }
        r3 = r14.isFile();	 Catch:{ all -> 0x0136 }
        if (r3 == 0) goto L_0x016f;
    L_0x00a3:
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0136 }
        r3.<init>();	 Catch:{ all -> 0x0136 }
        r3 = r3.append(r10);	 Catch:{ all -> 0x0136 }
        r15 = "\r\n";
        r3 = r3.append(r15);	 Catch:{ all -> 0x0136 }
        r3 = r3.toString();	 Catch:{ all -> 0x0136 }
        r0 = r18;
        r0.write(r11, r3);	 Catch:{ all -> 0x0136 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0136 }
        r3.<init>();	 Catch:{ all -> 0x0136 }
        r15 = "Content-Disposition: form-data; name=\"";
        r3 = r3.append(r15);	 Catch:{ all -> 0x0136 }
        r15 = r14.getName();	 Catch:{ all -> 0x0136 }
        r3 = r3.append(r15);	 Catch:{ all -> 0x0136 }
        r15 = "\"; filename=\"";
        r3 = r3.append(r15);	 Catch:{ all -> 0x0136 }
        r15 = r14.getFile();	 Catch:{ all -> 0x0136 }
        r15 = r15.getName();	 Catch:{ all -> 0x0136 }
        r3 = r3.append(r15);	 Catch:{ all -> 0x0136 }
        r15 = "\"\r\n";
        r3 = r3.append(r15);	 Catch:{ all -> 0x0136 }
        r3 = r3.toString();	 Catch:{ all -> 0x0136 }
        r0 = r18;
        r0.write(r11, r3);	 Catch:{ all -> 0x0136 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0136 }
        r3.<init>();	 Catch:{ all -> 0x0136 }
        r15 = "Content-Type: ";
        r3 = r3.append(r15);	 Catch:{ all -> 0x0136 }
        r15 = r14.getContentType();	 Catch:{ all -> 0x0136 }
        r3 = r3.append(r15);	 Catch:{ all -> 0x0136 }
        r15 = "\r\n\r\n";
        r3 = r3.append(r15);	 Catch:{ all -> 0x0136 }
        r3 = r3.toString();	 Catch:{ all -> 0x0136 }
        r0 = r18;
        r0.write(r11, r3);	 Catch:{ all -> 0x0136 }
        r15 = new java.io.BufferedInputStream;	 Catch:{ all -> 0x0136 }
        r3 = r14.hasFileBody();	 Catch:{ all -> 0x0136 }
        if (r3 == 0) goto L_0x0156;
    L_0x0119:
        r3 = r14.getFileBody();	 Catch:{ all -> 0x0136 }
    L_0x011d:
        r15.<init>(r3);	 Catch:{ all -> 0x0136 }
        r3 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r3 = new byte[r3];	 Catch:{ all -> 0x0136 }
    L_0x0124:
        r14 = r15.read(r3);	 Catch:{ all -> 0x0136 }
        r16 = -1;
        r0 = r16;
        if (r14 == r0) goto L_0x0160;
    L_0x012e:
        r16 = 0;
        r0 = r16;
        r11.write(r3, r0, r14);	 Catch:{ all -> 0x0136 }
        goto L_0x0124;
    L_0x0136:
        r3 = move-exception;
        r5 = r2;
        r2 = r3;
        r3 = r4;
        r4 = r6;
    L_0x013b:
        r3.close();	 Catch:{ Exception -> 0x031b }
    L_0x013e:
        throw r2;	 Catch:{ IOException -> 0x013f }
    L_0x013f:
        r2 = move-exception;
        r6 = r4;
        r4 = r5;
    L_0x0142:
        r0 = r18;
        r3 = r0.CONF;
        r3 = r3.getHttpRetryCount();
        if (r7 != r3) goto L_0x0337;
    L_0x014c:
        r3 = new twitter4j.TwitterException;
        r4 = r2.getMessage();
        r3.<init>(r4, r2, r6);
        throw r3;
    L_0x0156:
        r3 = new java.io.FileInputStream;	 Catch:{ all -> 0x0136 }
        r14 = r14.getFile();	 Catch:{ all -> 0x0136 }
        r3.<init>(r14);	 Catch:{ all -> 0x0136 }
        goto L_0x011d;
    L_0x0160:
        r3 = "\r\n";
        r0 = r18;
        r0.write(r11, r3);	 Catch:{ all -> 0x0136 }
        r15.close();	 Catch:{ all -> 0x0136 }
    L_0x016a:
        r3 = r5 + 1;
        r5 = r3;
        goto L_0x0099;
    L_0x016f:
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0136 }
        r3.<init>();	 Catch:{ all -> 0x0136 }
        r3 = r3.append(r10);	 Catch:{ all -> 0x0136 }
        r15 = "\r\n";
        r3 = r3.append(r15);	 Catch:{ all -> 0x0136 }
        r3 = r3.toString();	 Catch:{ all -> 0x0136 }
        r0 = r18;
        r0.write(r11, r3);	 Catch:{ all -> 0x0136 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0136 }
        r3.<init>();	 Catch:{ all -> 0x0136 }
        r15 = "Content-Disposition: form-data; name=\"";
        r3 = r3.append(r15);	 Catch:{ all -> 0x0136 }
        r15 = r14.getName();	 Catch:{ all -> 0x0136 }
        r3 = r3.append(r15);	 Catch:{ all -> 0x0136 }
        r15 = "\"\r\n";
        r3 = r3.append(r15);	 Catch:{ all -> 0x0136 }
        r3 = r3.toString();	 Catch:{ all -> 0x0136 }
        r0 = r18;
        r0.write(r11, r3);	 Catch:{ all -> 0x0136 }
        r3 = "Content-Type: text/plain; charset=UTF-8\r\n\r\n";
        r0 = r18;
        r0.write(r11, r3);	 Catch:{ all -> 0x0136 }
        r3 = logger;	 Catch:{ all -> 0x0136 }
        r15 = r14.getValue();	 Catch:{ all -> 0x0136 }
        r3.debug(r15);	 Catch:{ all -> 0x0136 }
        r3 = r14.getValue();	 Catch:{ all -> 0x0136 }
        r14 = "UTF-8";
        r3 = r3.getBytes(r14);	 Catch:{ all -> 0x0136 }
        r11.write(r3);	 Catch:{ all -> 0x0136 }
        r3 = "\r\n";
        r0 = r18;
        r0.write(r11, r3);	 Catch:{ all -> 0x0136 }
        goto L_0x016a;
    L_0x01ce:
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0136 }
        r3.<init>();	 Catch:{ all -> 0x0136 }
        r3 = r3.append(r10);	 Catch:{ all -> 0x0136 }
        r5 = "--\r\n";
        r3 = r3.append(r5);	 Catch:{ all -> 0x0136 }
        r3 = r3.toString();	 Catch:{ all -> 0x0136 }
        r0 = r18;
        r0.write(r11, r3);	 Catch:{ all -> 0x0136 }
        r3 = "\r\n";
        r0 = r18;
        r0.write(r11, r3);	 Catch:{ all -> 0x0136 }
    L_0x01ed:
        r4.flush();	 Catch:{ all -> 0x0136 }
        r4.close();	 Catch:{ all -> 0x0136 }
        r5 = r4;
    L_0x01f4:
        r4 = new twitter4j.HttpResponseImpl;	 Catch:{ all -> 0x032a }
        r0 = r18;
        r3 = r0.CONF;	 Catch:{ all -> 0x032a }
        r4.<init>(r9, r3);	 Catch:{ all -> 0x032a }
        r6 = r9.getResponseCode();	 Catch:{ all -> 0x025d }
        r2 = logger;	 Catch:{ all -> 0x025d }
        r2 = r2.isDebugEnabled();	 Catch:{ all -> 0x025d }
        if (r2 == 0) goto L_0x029c;
    L_0x0209:
        r2 = logger;	 Catch:{ all -> 0x025d }
        r3 = "Response: ";
        r2.debug(r3);	 Catch:{ all -> 0x025d }
        r9 = r9.getHeaderFields();	 Catch:{ all -> 0x025d }
        r2 = r9.keySet();	 Catch:{ all -> 0x025d }
        r10 = r2.iterator();	 Catch:{ all -> 0x025d }
    L_0x021c:
        r2 = r10.hasNext();	 Catch:{ all -> 0x025d }
        if (r2 == 0) goto L_0x029c;
    L_0x0222:
        r2 = r10.next();	 Catch:{ all -> 0x025d }
        r2 = (java.lang.String) r2;	 Catch:{ all -> 0x025d }
        r3 = r9.get(r2);	 Catch:{ all -> 0x025d }
        r3 = (java.util.List) r3;	 Catch:{ all -> 0x025d }
        r11 = r3.iterator();	 Catch:{ all -> 0x025d }
    L_0x0232:
        r3 = r11.hasNext();	 Catch:{ all -> 0x025d }
        if (r3 == 0) goto L_0x021c;
    L_0x0238:
        r3 = r11.next();	 Catch:{ all -> 0x025d }
        r3 = (java.lang.String) r3;	 Catch:{ all -> 0x025d }
        if (r2 == 0) goto L_0x0296;
    L_0x0240:
        r12 = logger;	 Catch:{ all -> 0x025d }
        r13 = new java.lang.StringBuilder;	 Catch:{ all -> 0x025d }
        r13.<init>();	 Catch:{ all -> 0x025d }
        r13 = r13.append(r2);	 Catch:{ all -> 0x025d }
        r14 = ": ";
        r13 = r13.append(r14);	 Catch:{ all -> 0x025d }
        r3 = r13.append(r3);	 Catch:{ all -> 0x025d }
        r3 = r3.toString();	 Catch:{ all -> 0x025d }
        r12.debug(r3);	 Catch:{ all -> 0x025d }
        goto L_0x0232;
    L_0x025d:
        r2 = move-exception;
        r3 = r5;
        r5 = r4;
        r4 = r6;
        goto L_0x013b;
    L_0x0263:
        r3 = "Content-Type";
        r5 = "application/x-www-form-urlencoded";
        r9.setRequestProperty(r3, r5);	 Catch:{ all -> 0x0323 }
        r3 = r19.getParameters();	 Catch:{ all -> 0x0323 }
        r3 = twitter4j.HttpParameter.encodeParameters(r3);	 Catch:{ all -> 0x0323 }
        r5 = logger;	 Catch:{ all -> 0x0323 }
        r10 = "Post Params: ";
        r5.debug(r10, r3);	 Catch:{ all -> 0x0323 }
        r5 = "UTF-8";
        r3 = r3.getBytes(r5);	 Catch:{ all -> 0x0323 }
        r5 = "Content-Length";
        r10 = r3.length;	 Catch:{ all -> 0x0323 }
        r10 = java.lang.Integer.toString(r10);	 Catch:{ all -> 0x0323 }
        r9.setRequestProperty(r5, r10);	 Catch:{ all -> 0x0323 }
        r5 = 1;
        r9.setDoOutput(r5);	 Catch:{ all -> 0x0323 }
        r4 = r9.getOutputStream();	 Catch:{ all -> 0x0323 }
        r4.write(r3);	 Catch:{ all -> 0x0136 }
        goto L_0x01ed;
    L_0x0296:
        r12 = logger;	 Catch:{ all -> 0x025d }
        r12.debug(r3);	 Catch:{ all -> 0x025d }
        goto L_0x0232;
    L_0x029c:
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r6 < r2) goto L_0x02a8;
    L_0x02a0:
        r2 = 302; // 0x12e float:4.23E-43 double:1.49E-321;
        if (r6 == r2) goto L_0x02c8;
    L_0x02a4:
        r2 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r2 > r6) goto L_0x02c8;
    L_0x02a8:
        r2 = 420; // 0x1a4 float:5.89E-43 double:2.075E-321;
        if (r6 == r2) goto L_0x02be;
    L_0x02ac:
        r2 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r6 == r2) goto L_0x02be;
    L_0x02b0:
        r2 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        if (r6 < r2) goto L_0x02be;
    L_0x02b4:
        r0 = r18;
        r2 = r0.CONF;	 Catch:{ all -> 0x025d }
        r2 = r2.getHttpRetryCount();	 Catch:{ all -> 0x025d }
        if (r7 != r2) goto L_0x02cd;
    L_0x02be:
        r2 = new twitter4j.TwitterException;	 Catch:{ all -> 0x025d }
        r3 = r4.asString();	 Catch:{ all -> 0x025d }
        r2.<init>(r3, r4);	 Catch:{ all -> 0x025d }
        throw r2;	 Catch:{ all -> 0x025d }
    L_0x02c8:
        r5.close();	 Catch:{ Exception -> 0x0317, IOException -> 0x0320 }
    L_0x02cb:
        r2 = r4;
    L_0x02cc:
        return r2;
    L_0x02cd:
        r5.close();	 Catch:{ Exception -> 0x0319, IOException -> 0x0320 }
    L_0x02d0:
        r2 = r4;
    L_0x02d1:
        r3 = logger;	 Catch:{ InterruptedException -> 0x031e }
        r3 = r3.isDebugEnabled();	 Catch:{ InterruptedException -> 0x031e }
        if (r3 == 0) goto L_0x02de;
    L_0x02d9:
        if (r2 == 0) goto L_0x02de;
    L_0x02db:
        r2.asString();	 Catch:{ InterruptedException -> 0x031e }
    L_0x02de:
        r3 = logger;	 Catch:{ InterruptedException -> 0x031e }
        r4 = new java.lang.StringBuilder;	 Catch:{ InterruptedException -> 0x031e }
        r4.<init>();	 Catch:{ InterruptedException -> 0x031e }
        r5 = "Sleeping ";
        r4 = r4.append(r5);	 Catch:{ InterruptedException -> 0x031e }
        r0 = r18;
        r5 = r0.CONF;	 Catch:{ InterruptedException -> 0x031e }
        r5 = r5.getHttpRetryIntervalSeconds();	 Catch:{ InterruptedException -> 0x031e }
        r4 = r4.append(r5);	 Catch:{ InterruptedException -> 0x031e }
        r5 = " seconds until the next retry.";
        r4 = r4.append(r5);	 Catch:{ InterruptedException -> 0x031e }
        r4 = r4.toString();	 Catch:{ InterruptedException -> 0x031e }
        r3.debug(r4);	 Catch:{ InterruptedException -> 0x031e }
        r0 = r18;
        r3 = r0.CONF;	 Catch:{ InterruptedException -> 0x031e }
        r3 = r3.getHttpRetryIntervalSeconds();	 Catch:{ InterruptedException -> 0x031e }
        r3 = r3 * 1000;
        r4 = (long) r3;	 Catch:{ InterruptedException -> 0x031e }
        java.lang.Thread.sleep(r4);	 Catch:{ InterruptedException -> 0x031e }
    L_0x0312:
        r3 = r7 + 1;
        r7 = r3;
        goto L_0x000d;
    L_0x0317:
        r2 = move-exception;
        goto L_0x02cb;
    L_0x0319:
        r2 = move-exception;
        goto L_0x02d0;
    L_0x031b:
        r3 = move-exception;
        goto L_0x013e;
    L_0x031e:
        r3 = move-exception;
        goto L_0x0312;
    L_0x0320:
        r2 = move-exception;
        goto L_0x0142;
    L_0x0323:
        r3 = move-exception;
        r5 = r2;
        r2 = r3;
        r3 = r4;
        r4 = r6;
        goto L_0x013b;
    L_0x032a:
        r3 = move-exception;
        r4 = r6;
        r17 = r5;
        r5 = r2;
        r2 = r3;
        r3 = r17;
        goto L_0x013b;
    L_0x0334:
        r5 = r4;
        goto L_0x01f4;
    L_0x0337:
        r2 = r4;
        goto L_0x02d1;
        */
        throw new UnsupportedOperationException("Method not decompiled: twitter4j.HttpClientImpl.handleRequest(twitter4j.HttpRequest):twitter4j.HttpResponse");
    }

    private void setHeaders(HttpRequest httpRequest, HttpURLConnection httpURLConnection) {
        String authorizationHeader;
        if (logger.isDebugEnabled()) {
            logger.debug("Request: ");
            logger.debug(httpRequest.getMethod().name() + " ", httpRequest.getURL());
        }
        if (httpRequest.getAuthorization() != null) {
            authorizationHeader = httpRequest.getAuthorization().getAuthorizationHeader(httpRequest);
            if (authorizationHeader != null) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Authorization: ", authorizationHeader.replaceAll(".", Operation.MULTIPLY));
                }
                httpURLConnection.addRequestProperty("Authorization", authorizationHeader);
            }
        }
        if (httpRequest.getRequestHeaders() != null) {
            for (String authorizationHeader2 : httpRequest.getRequestHeaders().keySet()) {
                httpURLConnection.addRequestProperty(authorizationHeader2, (String) httpRequest.getRequestHeaders().get(authorizationHeader2));
                logger.debug(authorizationHeader2 + ": " + ((String) httpRequest.getRequestHeaders().get(authorizationHeader2)));
            }
        }
    }

    HttpURLConnection getConnection(String str) {
        HttpURLConnection httpURLConnection;
        if (isProxyConfigured()) {
            if (!(this.CONF.getHttpProxyUser() == null || this.CONF.getHttpProxyUser().equals(BuildConfig.FLAVOR))) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Proxy AuthUser: " + this.CONF.getHttpProxyUser());
                    logger.debug("Proxy AuthPassword: " + this.CONF.getHttpProxyPassword().replaceAll(".", Operation.MULTIPLY));
                }
                Authenticator.setDefault(new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        if (getRequestorType().equals(RequestorType.PROXY)) {
                            return new PasswordAuthentication(HttpClientImpl.this.CONF.getHttpProxyUser(), HttpClientImpl.this.CONF.getHttpProxyPassword().toCharArray());
                        }
                        return null;
                    }
                });
            }
            Proxy proxy = new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(this.CONF.getHttpProxyHost(), this.CONF.getHttpProxyPort()));
            if (logger.isDebugEnabled()) {
                logger.debug("Opening proxied connection(" + this.CONF.getHttpProxyHost() + ":" + this.CONF.getHttpProxyPort() + ")");
            }
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection(proxy);
        } else {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        }
        if (this.CONF.getHttpConnectionTimeout() > 0) {
            httpURLConnection.setConnectTimeout(this.CONF.getHttpConnectionTimeout());
        }
        if (this.CONF.getHttpReadTimeout() > 0) {
            httpURLConnection.setReadTimeout(this.CONF.getHttpReadTimeout());
        }
        httpURLConnection.setInstanceFollowRedirects(false);
        return httpURLConnection;
    }
}
