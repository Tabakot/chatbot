package ru.tab.chatbot.network;

import retrofit2.Call;
import retrofit2.http.*;
import ru.tab.chatbot.model.LoginBody;
import ru.tab.chatbot.model.Message;

import java.util.List;

public interface ApiService {
    @Headers("Content-Type: application/json")
    @GET("/message/{id}")
    Call<Message> getMessage(
            @Path("id") Long id);

    @Headers("Content-Type: application/json")
    @GET("/message/Get_Command")
    Call<Message> getCommand();

    @Headers("Content-Type: application/json")
    @GET("/message")
    Call<List<Message>> getMessages();

    @Headers("Content-Type: application/json")
    @DELETE("/message/{id}")
    Call<Void> deleteMessage(
            @Path("id") Long id);

    @Headers("Content-Type: application/json")
    @POST("/message")
    Call<Void> sendMessage(@Body Message message);

    @Headers("Content-Type: application/json")
    @POST("/loginPage")
    Call<Void> loginBot(@Header("Authorization") String auth, @Body LoginBody loginBody);
}
