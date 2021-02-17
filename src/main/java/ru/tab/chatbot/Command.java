package ru.tab.chatbot;

import retrofit2.Call;
import retrofit2.Response;
import ru.tab.chatbot.model.Message;
import ru.tab.chatbot.network.ApiFactory;
import ru.tab.chatbot.network.ApiFactoryHabr;

import java.util.List;
import java.util.Random;

public class Command {

    public static void sendMessage(Message message) {
        Call<Void> sendMessageCall = ApiFactory.getService().sendMessage(message);
        try {
            Response<Void> response = sendMessageCall.execute();
            System.out.println("Code: " + response.code()
                    + "\nMessage: " + message.getText());
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    public static List<Message> getMessages() {
        Call<List<Message>> messageCall = ApiFactory.getService().getMessages();
        try {
            Response<List<Message>> response = messageCall.execute();
            return response.body();
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
        return null;
    }

    public static Message getMessage(Long id) {
        Call<Message> messageCall = ApiFactory.getService().getMessage(id);
        try {
            Response<Message> response = messageCall.execute();
            return response.body();
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
        return new Message();
    }

    public static Message getCommand() {
        Call<Message> messageCall = ApiFactory.getService().getCommand();
        try {
            Response<Message> response = messageCall.execute();
            return response.body();
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
        return new Message();
    }

    public static void deleteMessage(Long id) {
        Call<Void> deleteMessageCall = ApiFactory.getService().deleteMessage(id);
        try {
            Message deleteMessage = getMessage(id);
            Response<Void> response = deleteMessageCall.execute();
            System.out.println("Code: " + response.code()
                    + "\n" + "Autor: " + deleteMessage.getAutor().getUsername()
                    +  " Delete message: " + deleteMessage.getText());
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    public static boolean getHabrPost(String url, int id) {
        Call<Void> habrCall = ApiFactoryHabr.getService().getPost(url, id);
        try {
            Response<Void> response = habrCall.execute();
            if (response.code() == 200)
                return true;
            else
                return false;
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
        return false;
    }

    public static String generateUrl() {
        Random random = new Random();
        String url = "post";
        int id;
        do {
            id = random.nextInt(490200);
        } while (!getHabrPost(url, id));
        return "http://habr.com/" + url + "/" + id;
    }

    public static void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
