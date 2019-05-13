package com.ww.common.utils;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.CoreConnectionPNames;

import java.security.KeyStore;

@SuppressWarnings("deprecation")
public class HttpClientHelper {
	 
    private static HttpClient httpClient;
 
    private HttpClientHelper() {
    }
 
	public static synchronized HttpClient getHttpClient() {
 
        if (null == httpClient) {
            // 初始化工作
            try {
                KeyStore trustStore = KeyStore.getInstance(KeyStore
                        .getDefaultType());
                trustStore.load(null, null);
                SSLSocketFactory sf = new SSLSocketFactoryEx(trustStore);
                sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  //允许所有主机的验证
 
                // 设置http https支持
                SchemeRegistry schReg = new SchemeRegistry();
                schReg.register(new Scheme("http", PlainSocketFactory
                        .getSocketFactory(), 80));
                schReg.register(new Scheme("https", sf, 443));
 
                ClientConnectionManager conManager = new ThreadSafeClientConnManager(schReg);
 
                
                httpClient = new DefaultHttpClient(conManager);
                httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,180000);//连接时间
                httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,180000);
            } catch (Exception e) {
                e.printStackTrace();
                return new DefaultHttpClient();
            }
        }
        return httpClient;
    }
 
}
 
