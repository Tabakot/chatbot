package ru.tab.chatbot.network;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Base64;

public class ApiFactory {

    private static final String ROOT_URL = "http://localhost:8080";

    static Retrofit buildRetrofit() {
        byte[] encodedBytes = Base64.getEncoder().encode("test_bot:bot".getBytes());
        final String auth = "Basic " + new String(encodedBytes);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Authorization", auth)
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });
        OkHttpClient okHttpClient = httpClient.build();
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static ApiService getService() {
        return buildRetrofit().create(ApiService.class);
    }
}