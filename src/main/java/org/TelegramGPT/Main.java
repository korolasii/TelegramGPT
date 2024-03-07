package org.TelegramGPT;




import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;

public class Main extends TelegramLongPollingBot {

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            botsApi.registerBot(new Main());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        String userMessageText = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        if (userMessageText.equals("/start")){
            sendMessage.setText("Добро пожаловать! Напишите запрос.");
        } else {
            String response = null;
            try {
                response = ApiGPT.main(userMessageText);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (response.startsWith("Error")) {
                sendMessage.setText("Произошла ошибка при обработке запроса. Пожалуйста, попробуйте еще раз позже.");
            } else {
                sendMessage.setText("Ответ: " + response);
            }
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "OPENTESTAICHATGPT_bot";
    }

    @Override
    public String getBotToken() {

        return "6972470428:AAFH_cOp7rJrmuwdVJUqhU4wYf_6b3b9VlA";
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }


    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }
}