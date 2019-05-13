package com.ww.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;



public class HttpsUtils {

    private static HttpClient client = HttpClientHelper.getHttpClient();

    private static Logger logger = LoggerFactory.getLogger(HttpsUtils.class);

    public static String get(String url) throws IOException {
        return get(url, null);
    }

    /**
     * 模拟GET请求
     * @param url
     * @throws IOException
     * @throws
     */
    public static String get(String url, Header header) throws IOException {
        logger.info("GET: {}", url);
        if (header != null) {
            logger.info("header: {}", header);
        }
        HttpResponse response = null;
        String data = null;
        try {
            HttpGet get = new HttpGet(url);
            if (header != null) {
                get.setHeader(header);
            }
            response = client.execute(get);
            logger.info("status: {}", response.getStatusLine().toString());
            HttpEntity entity = response.getEntity();
            BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
            StringBuffer out = new StringBuffer();
            String buffer = null;
            while ((buffer = reader.readLine()) != null) {
                out.append(buffer);
            }
            data = out.toString();
            logger.info("GET: {} >>> data:{}", url, data);
            get.abort();
        } finally {
            if (response != null) {
                response.getEntity().getContent().close();
            }
        }
        return data;
    }

    public static String initGetParams(String ...input) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < input.length; i+=2) {
            try {
                result.add(input[i] + "=" + URLEncoder.encode(input[i+1], "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                //skip
            }
        }
        return StringUtils.join(result, "&");
    }

    @SuppressWarnings({"rawtypes", "unchecked", "resource"})
    public static String post(String targetURL, Object data)
            throws UnsupportedEncodingException, IOException,
            ClientProtocolException {
        if (StringUtils.isEmpty(targetURL)) {
            return null;
        }
        DefaultHttpClient httpClient = null;
        HttpPost post = null;
        try {
            logger.info("--to-xdgc-targetURL:" + targetURL);

            ObjectMapper jsonOM = new ObjectMapper();
            String reqPkg = jsonOM.writeValueAsString(data);
            logger.info("--to-xdgc-reqpkg:" + reqPkg);

            // 封装请求数据
            List<NameValuePair> paramList = new ArrayList<NameValuePair>();
            Map dataMap = null;
            if (data instanceof String) {
                NameValuePair param = new BasicNameValuePair("k",
                        String.valueOf(data));
                paramList.add(param);
            } else if (data instanceof Map) {
                dataMap = (HashMap) data;
            } else {
//                dataMap = BeanUtils.describe(data);
            }
            // 转换KV
            if (dataMap != null) {
                Iterator<String> ite = dataMap.keySet().iterator();
                while (ite.hasNext()) {
                    String k = ite.next();
                    Object v = dataMap.get(k);
                    if (v == null) {
                        continue;
                    }
                    NameValuePair param = new BasicNameValuePair(k, String.valueOf(v));
                    paramList.add(param);
                }
            }

            if (paramList.isEmpty()) {
                NameValuePair param = new BasicNameValuePair("k", "1");
                paramList.add(param);
            }

            // 初始化HTTP连接
            httpClient = new DefaultHttpClient();
            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, 30000);
            HttpConnectionParams.setSoTimeout(httpParams, 120000);
            httpClient.setParams(httpParams);

            // 初始化POST方式
            post = new HttpPost(targetURL);
            // 设置消息体
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList,
                    "UTF-8");
            post.setEntity(entity);

            // 发送消息
            HttpResponse response = httpClient.execute(post);
            int respCode = response.getStatusLine().getStatusCode();
            if (respCode >= 100 && respCode < 300) {
                // 消息应答
                HttpEntity respBody = response.getEntity();
                String respPkg = EntityUtils.toString(respBody, "UTF-8");
                logger.info("--to-xdgc-resppkg:" + respPkg);
                return respPkg;
            } else {
                logger.info("--to-xdgc-res statusLine:" + response.getStatusLine());
                return null;
            }
        } catch (Exception e) {
            logger.error("访问服务异常BY-KV", e);
            return null;
        } finally {
            if (post != null) {
                post.releaseConnection();
            }
        }
    }

    public static String post(String targetURL, JSONObject requestBody) throws IOException {
        DefaultHttpClient httpClient = null;
        HttpPost post = null;
        try {
            logger.info("--to-xdgc-targetURL:" + targetURL);

            String jsonStr = JSONObject.toJSONString(requestBody);
            logger.info("--to-xdgc-reqpkg:" + jsonStr);
            // 初始化HTTP连接
            httpClient = new DefaultHttpClient();
            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, 30000);
            HttpConnectionParams.setSoTimeout(httpParams, 120000);
            httpClient.setParams(httpParams);

            // 初始化POST方式
            post = new HttpPost(targetURL);
            StringEntity entity = new StringEntity(jsonStr, ContentType.APPLICATION_JSON);
            post.setEntity(entity);

            // 发送消息
            HttpResponse response = httpClient.execute(post);
            int respCode = response.getStatusLine().getStatusCode();
            if (respCode >= 100 && respCode < 300) {
                // 消息应答
                HttpEntity respBody = response.getEntity();
                String respPkg = EntityUtils.toString(respBody, "UTF-8");
                logger.info("--to-xdgc-resppkg:" + respPkg);
                return respPkg;
            } else {
                logger.info("--to-xdgc-res statusLine:" + response.getStatusLine());
                return null;
            }
        } catch (Exception e) {
            logger.error("访问服务异常BY-KV", e);
            return null;
        } finally {
            if (post != null) {
                post.releaseConnection();
            }
        }
    }

    public static Map<String, Object> initPostParams(String ...input) {
        Map<String, Object> result = new HashMap<>();
        for (int i = 0; i < input.length; i+=2) {
            result.put(input[i], input[i+1]);
        }
        return result;
    }

    public static CloseableHttpClient getHttpClient(String targetURL) {
        if (StringUtils.isEmpty(targetURL)) targetURL = "http:";
        int p = targetURL.indexOf(":");
        if (p < 4) return HttpClients.createDefault();
        String schema = targetURL.substring(0, p).toLowerCase();
        if (schema.startsWith("https")) {
            try {
                SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                    //信任证书
                    public boolean isTrusted(X509Certificate[] chain,
                                             String authType) throws CertificateException {
                        return true;
                    }
                }).build();
                return HttpClients.custom().setHostnameVerifier(new X509HostnameVerifier() {
                    //信任主机
                    public boolean verify(String arg0, SSLSession arg1) {
                        return true;
                    }

                    public void verify(String arg0, SSLSocket arg1)
                            throws IOException {
                    }

                    public void verify(String arg0, X509Certificate arg1)
                            throws SSLException {
                    }

                    public void verify(String arg0, String[] arg1, String[] arg2)
                            throws SSLException {
                    }
                }).setSSLContext(sslContext).build();
            } catch (Exception e) {
                logger.error("创建HTTPS连接异常.", e);
                return HttpClients.createDefault();
            }
        } else {
            return HttpClients.createDefault();
        }
    }


}