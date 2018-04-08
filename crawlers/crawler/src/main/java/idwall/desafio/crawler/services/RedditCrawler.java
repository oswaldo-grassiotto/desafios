package idwall.desafio.crawler.services;

import idwall.desafio.crawler.models.RedditThread;
import idwall.desafio.crawler.models.SeleniumConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RedditCrawler {

    /**
     * Browses the specified subreddit and returns a list of popular threads
     *
     * @param subreddit
     * @return List containing popular threads
     */
    public List<RedditThread> crawl(String subreddit, WebDriver driver){

        String url = String.format("http://reddit.com/r/%s/top", subreddit);
        driver.get(url);
        List<WebElement> threadDivs = driver.findElements(By.cssSelector("[id*=thing_t3_]"));

        List<RedditThread> threads = new ArrayList<>();

        for(WebElement threadDiv : threadDivs){
            Integer upvotes = Integer.parseInt(threadDiv.getAttribute("data-score"));

            if(upvotes < 5000)
                continue;

            if(threadDiv.getAttribute("class").contains("promoted"))
                continue;

            String id = threadDiv.getAttribute("id");

            RedditThread popularThread = new RedditThread();

            popularThread.setSubreddit(subreddit);
            popularThread.setUpvotes(upvotes);
            popularThread.setThreadLink(threadDiv.getAttribute("data-url"));

            popularThread.setTitle(threadDiv.findElement(By.className("title")).getText());
            popularThread.setCommentsLink(threadDiv.findElement(By.cssSelector("a[data-event-action='comments']")).getAttribute("href"));


            threads.add(popularThread);
        }

        return threads;
    }
}
