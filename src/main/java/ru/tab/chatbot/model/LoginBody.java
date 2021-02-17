package ru.tab.chatbot.model;

import com.google.gson.annotations.SerializedName;

public class LoginBody {
    @SerializedName("username")
    public String username;
    @SerializedName("password")
    public String password;
}