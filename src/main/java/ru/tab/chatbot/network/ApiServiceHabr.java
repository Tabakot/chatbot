package ru.tab.chatbot.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ApiServiceHabr {
    @Headers("Content-Type: application/json")
    @GET("ru/{url}/{id}")
    Call<Void> getPost(
            @Path("url") String url,
            @Path("id") int id);
}
