package idwall.desafio.crawler.services;

import idwall.desafio.crawler.models.RedditThread;
import idwall.desafio.crawler.models.SeleniumConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedditService {

    private RedditCrawler crawler;
    private WebDriver driver;

    @Autowired
    public RedditService(RedditCrawler crawler, SeleniumConfig config){
        this.crawler = crawler;
        System.setProperty(config.getDriverName(), config.getDriverPath());
    }

    /**
     * Builds a list of all popular threads in the specified subreddits
     *
     * @param subreddits
     * @return List of popular threads
     */
    public List<RedditThread> getPopularThreads(List<String> subreddits){

        if(driver == null){
            driver = new FirefoxDriver();
        }

        List<RedditThread> threads = new ArrayList<>();

        for(String subreddit : subreddits){
            List<RedditThread> popularThreads = crawler.crawl(subreddit, driver);
            threads.addAll(popularThreads);
        }

        closeDriver();

        return threads;
    }

    public void closeDriver(){
        driver.quit();
        driver = null;
    }
}
