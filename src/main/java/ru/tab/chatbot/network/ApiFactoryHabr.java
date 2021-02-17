package ru.tab.chatbot.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactoryHabr {

    private static final String HABR_URL = "http://habr.com/";

    static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(HABR_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiServiceHabr getService() {
        return buildRetrofit().create(ApiServiceHabr.class);
    }
}