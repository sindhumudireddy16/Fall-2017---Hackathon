package com.umkc.medspace.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.umkc.medspace.BuildConfig;
import com.umkc.medspace.Constants.Constants;
import com.umkc.medspace.Singleton;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    public static final String API_BASE_URL = Constants.BASE_URL;

    private static Retrofit retrofit = null;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    //    public static <S> S createService(Class<S> serviceClass) {
//        return createService(serviceClass, null);
//    }
    public static <S> S createService(Class<S> serviceClass, final Context ctx) {
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.interceptors().add(logging);
        }
        if (ctx != null) {
            httpClient.interceptors().add(new RequestInterceptor((ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE), ctx));
        }

        if (retrofit == null) {
            retrofit = builder.client(httpClient.connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build()).build();
        }

        return retrofit.create(serviceClass);
    }

    public static Retrofit parseError() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }

    public static class RequestInterceptor implements Interceptor {

        final ConnectivityManager connectivityManager;
        Context mCtx;

        //@Inject
        public RequestInterceptor(ConnectivityManager connectivityManager, Context ctx) {
            this.connectivityManager = connectivityManager;
            this.mCtx = ctx;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            if (!isConnected()) {
//                throw new OfflineException("no internet");
                ((Activity) mCtx).runOnUiThread(new Runnable() {
                    public void run() {
//                                Toast.makeText(mCtx, "Please check internet connection", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            Request original = chain.request();
            Request.Builder requestBuilder;
            requestBuilder = original.newBuilder()
                    .header("Authorization", Singleton.getPref(Constants.ACCESS_TOKEN, mCtx));

            Request request = requestBuilder.build();
            Response response = chain.proceed(request);
            if (response.code() == 401 && mCtx != null) {
                LocalBroadcastManager.getInstance(mCtx).sendBroadcast(new Intent("unAuthorized"));
            } else if (response.code() == 500 && mCtx != null) {
                ((Activity) mCtx).runOnUiThread(new Runnable() {
                    public void run() {
//                        Toast.makeText(mCtx, "Internal Server Error", Toast.LENGTH_SHORT).show();
                        Log.i("Server","Internal Server Error");
                    }
                });
            }
//
            return response;
//            Request.Builder r = chain.request().newBuilder();
//            return chain.proceed(r.build());
        }

        protected boolean isConnected() {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnectedOrConnecting();
        }
    }
}
