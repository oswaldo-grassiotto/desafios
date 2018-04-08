package idwall.desafio.crawler.bots;

import idwall.desafio.crawler.models.RedditThread;
import idwall.desafio.crawler.services.RedditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.List;

public class TelegramBot extends TelegramLongPollingBot {

    private final String BOT_TOKEN = "504128560:AAHEseDS-PjvdtK8eNRYaR_lMvSBQ4HjDrk";
    private final String BOT_USERNAME = "desidwall";

    private RedditService reddit;

    public TelegramBot(RedditService reddit){
        this.reddit = reddit;
    }

    @Override
    public void onUpdateReceived(Update update) {
        processUpdate(update);
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        for(Update update : updates){
            processUpdate(update);
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    private void processUpdate(Update update){

        Long chatId = null;
        String text = null;

        if (update.hasMessage() && update.getMessage().hasText()) {
            chatId = update.getMessage().getChatId();
            text = update.getMessage().getText();

        } else {
            return;
        }

        if(!text.contains("/NadaPraFazer") &&
                !text.contains("/nadaprafazer")){
            return;
        }

        try {
            SendMessage message = createSendMessage(chatId, "Buscando posts no reddit, por favor aguarde...");
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        text = text.split(" ")[1];

        List<String> subreddits = Arrays.asList(text.split(";"));
        List<RedditThread> threads = reddit.getPopularThreads(subreddits);

        try {
            for(RedditThread thread : threads) {

                SendMessage message = createSendMessage(chatId, thread.toString());
                execute(message);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private SendMessage createSendMessage(Long chatId, String message){
        return new SendMessage()
                .setChatId(chatId)
                .setText(message);
    }
}
