package idwall.desafio.crawler.services;

import idwall.desafio.crawler.models.RedditThread;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Service
public class TelegramService {

    public void send(List<RedditThread> threads, long phoneNumber){
        throw new NotImplementedException();
    }
}
