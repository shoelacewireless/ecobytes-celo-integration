package com.shoelacewireless.ecobytessdk.backend;

import android.content.Context;
import android.util.Log;

import com.shoelacewireless.ecobytessdk.R;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Helper class to setup Retrofit, either with a regular HTTP client or an unsafe client using
// a self-signed certificate, and to retrieve the API declared in BackendApi.java
public class SDKRetrofitService {

    private static String TAG = "RetrofitService";

    //Singleton
    private static SDKRetrofitService _instance = null;

    /**
     * Call to retrieve a {@link SDKRetrofitService} object.
     */
    public static SDKRetrofitService getService(Context appContext) {
        Log.d(TAG, "UI: Fetching RetrofitService");
        if (_instance == null) {
            synchronized (SDKRetrofitService.class) {
                if (_instance == null) {
                    _instance = new SDKRetrofitService(appContext);
                }
            }
        }
        return _instance;
    }

    private SDKBackendApi mApi = null;
//    private String mToken = "";

    private SDKRetrofitService(Context context) {
        mApi = initApi(context);
    }

    public SDKBackendApi getApi() { return mApi;}

//    public void setToken(String token) {
//        mToken = token;
//    }

    private SDKBackendApi initApi(Context context) {

//        OkHttpClient httpClient = sslContext == null ?
//                getSafeHttpClient(basicCredentials) :
//                getUnsafeHttpClient(sslContext, basicCredentials);

        OkHttpClient httpClient = getUnsafeHttpClient(context);

        return new Retrofit.Builder()
                .baseUrl(context.getString(R.string.BACKEND_API_URL))
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SDKBackendApi.class);
    }

    private OkHttpClient getSafeHttpClient(String credentials) {
        //Interceptor for including Basic Auth header in every request
        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request req = chain.request().newBuilder().addHeader("Authorization", credentials).build();
                    return chain.proceed(req);
                })
                .build();
    }

    //Unsafe httpclient that accepts a server using a self-signed certificate
    private OkHttpClient getUnsafeHttpClient(Context context) {

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        SSLContext sslContext = null;
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            InputStream certificate = context.getResources().openRawResource(R.raw.server);
            Certificate cert = cf.generateCertificate(certificate);
            certificate.close();

            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", cert);

            sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            sslContext.init(null, trustManagerFactory.getTrustManagers(), null);

            clientBuilder
            //Interceptor for including Basic Auth header in every request
//            .addInterceptor(chain -> {
//                    //TODO: Add token authorization
//                    Request req = chain.request().newBuilder().addHeader("Authorization", credentials).build();
//                    return chain.proceed(req);
//                })

                    .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustManagerFactory.getTrustManagers()[0]) //Overriding certificate verification for self-signed certificate
                    .hostnameVerifier((hostname, session) -> true); //Overriding hostname verification

            /*TODO: Remove from sdk?
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
                clientBuilder.addInterceptor(httpLoggingInterceptor);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clientBuilder.build();
    }
}