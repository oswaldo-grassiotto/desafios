package idwall.desafio.crawler.bots;

import idwall.desafio.crawler.services.RedditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

@Component
public class TelegramBotInitializer implements CommandLineRunner {

    private RedditService reddit;

    @Autowired
    public TelegramBotInitializer(RedditService reddit){
        this.reddit = reddit;
    }

    @Override
    public void run(String... args) throws Exception {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new TelegramBot(reddit));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
