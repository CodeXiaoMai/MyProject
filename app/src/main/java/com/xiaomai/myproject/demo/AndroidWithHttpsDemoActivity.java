
package com.xiaomai.myproject.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;

import com.xiaomai.myproject.MyApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * Created by XiaoMai on 2016/12/29 15:08.
 */
public class AndroidWithHttpsDemoActivity extends Activity {

    private static final String TAG = "AndroidWithHttps";

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d(TAG, "handleMessage: " + msg.obj.toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://app.hskaoyan.com");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setConnectTimeout(3000);
                    httpURLConnection.setReadTimeout(3000);
                    httpURLConnection.setRequestMethod("POST");
                    if (httpURLConnection instanceof HttpsURLConnection) {
                        // 有证书Start
                        // SSLContext sslContext = getSSLContext(getFileName());
                        // if (sslContext != null) {
                        // SSLSocketFactory sslSocketFactory =
                        // sslContext.getSocketFactory();
                        // ((HttpsURLConnection) httpURLConnection)
                        // .setSSLSocketFactory(sslSocketFactory);
                        // }
                        // 有证书End
                        // 没有证书Start
                        SSLContext sslContext = getSSLContext();
                        if (sslContext != null) {
                            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
                            ((HttpsURLConnection) httpURLConnection)
                                    .setSSLSocketFactory(sslSocketFactory);
                            ((HttpsURLConnection) httpURLConnection)
                                    .setHostnameVerifier(hostnameVerifier);
                        }
                        // 没有证书End
                    }
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(
                                new InputStreamReader(inputStream));
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuilder.append(line);
                        }
                        inputStream.close();
                        bufferedReader.close();
                        Message message = new Message();
                        message.obj = stringBuilder.toString();
                        handler.sendMessage(message);
                    }
                    httpURLConnection.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    /**
     * 有安全证书的SSLContext </br>
     * 我们把Https的证书放在assets目录下，然后通过流加载：
     * 
     * @return
     */
    public static SSLContext getSSLContext(String fileName) {
        try {
            // 生成SSLContext对象
            SSLContext sslContext = SSLContext.getInstance("TLS");
            // 从assets中加载证书
            InputStream inStream = MyApplication.getContext().getAssets().open(fileName);

            // 证书工厂
            CertificateFactory cerFactory = CertificateFactory.getInstance("X.509");
            Certificate cer = cerFactory.generateCertificate(inStream);

            // 密钥库
            KeyStore kStore = KeyStore.getInstance("PKCS12");
            kStore.load(null, null);
            kStore.setCertificateEntry("trust", cer);// 加载证书到密钥库中

            // 密钥管理器
            KeyManagerFactory keyFactory = KeyManagerFactory
                    .getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyFactory.init(kStore, null);// 加载密钥库到管理器

            // 信任管理器
            TrustManagerFactory tFactory = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tFactory.init(kStore);// 加载密钥库到信任管理器

            // 初始化
            sslContext.init(keyFactory.getKeyManagers(), tFactory.getTrustManagers(),
                    new SecureRandom());
            return sslContext;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 没有安全证书的SSLContext</br>
     * 不需要证书的请求，需要两个对象，一个是SSLContext,另一个是HostnameVerifier，顾名思义就是主机名称匹配的意思
     * 
     * @return
     */
    public static SSLContext getSSLContext() {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
            // 只需要传一个null证书管理器和一个默认的信任管理器即可。
            sslContext.init(null, new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            }, new SecureRandom());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sslContext;
    }

    /**
     * 下面再来看HostnameVerifier，既然是主机名称校验，那我们直接通过：
     */
    private static HostnameVerifier hostnameVerifier = new HostnameVerifier() {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    /**
     * 证书的名称
     * 
     * @return
     */
    @NonNull
    private static String getFileName() {
        return "srca.cer";
    }
}
