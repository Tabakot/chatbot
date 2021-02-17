package ru.tab.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.tab.chatbot.model.Autor;
import ru.tab.chatbot.model.Message;

import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class ChatbotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatbotApplication.class, args);

		Autor bot = new Autor();
		bot.setUsername("test_bot");
		bot.setEmail("example@mail.com");

		/* <%---Run bot---%> */
		HashSet<String> responseUrl = new HashSet<String>();
		Message messageBot = new Message();
		messageBot.setAutor(bot);
		Message command = Command.getCommand();
		Long currentCommandId = -1L;
		boolean run = true;
		while (run) {
			if (command.getId() != null) {
				if (command.getId() > currentCommandId) {
					switch (command.getText()) {
						case ("/getpost"):
							String response = Command.generateUrl();
							while (responseUrl.contains(response + command.getAutor().getUsername())) {
								response = Command.generateUrl();
								System.out.println("New url: " + response);
							}
							responseUrl.add(response + command.getAutor().getUsername());
							messageBot.setText(response);
							Command.sendMessage(messageBot);
							break;
						case ("/delete"):
							List<Message> messages = Command.getMessages();
							for (Message message:messages) {
								Command.deleteMessage(message.getId());
							}
							break;
						case ("/q"):
							run = false;
							break;
						case ("/help"):
							messageBot.setText("'/getpost' to get Habr post");
							Command.sendMessage(messageBot);
							messageBot.setText("'/delete' to delete all messages");
							Command.sendMessage(messageBot);
							messageBot.setText("'/q' to stop the bot");
							Command.sendMessage(messageBot);
							break;
						default:
							messageBot.setText("'" + command.getText() + "': command not found");
							Command.sendMessage(messageBot);
							messageBot.setText("Enter '/help' for a list of chatbot commands");
							Command.sendMessage(messageBot);
							break;
					}
					currentCommandId = command.getId();
				}
			}
			command = Command.getCommand();
			Command.pause(250);
		}
	}
}
