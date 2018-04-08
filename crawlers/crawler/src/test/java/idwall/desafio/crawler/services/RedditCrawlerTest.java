package idwall.desafio.crawler.services;

import idwall.desafio.crawler.models.RedditThread;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class RedditCrawlerTest {

    private WebDriver driver;
    private List<WebElement> elements;
    private String CATS = "cats";

    private RedditCrawler crawler;

    @Before
    public void setUp(){
        driver = mock(WebDriver.class);

        WebElement div1 = mockWebElement("div1_id",
                "div1_title",
                "5001",
                "http://reddit.com/r/mocksubreddit1/link",
                "http://reddit.com/r/mocksubreddit1/comments"
        );

        WebElement div2 = mockWebElement("div2_id",
                "div2_title",
                "5002",
                "http://reddit.com/r/mocksubreddit2/link",
                "http://reddit.com/r/mocksubreddit2/comments"
        );

        elements = new ArrayList<>();
        elements.add(div1);
        elements.add(div2);

        when(driver.findElements(any())).thenReturn(elements);

        crawler = new RedditCrawler();
    }


    @Test
    public void crawlingTest(){

        RedditThread thread1 = mockThread(
                CATS,
                "div1_title",
                5001,
                "http://reddit.com/r/mocksubreddit1/link",
                "http://reddit.com/r/mocksubreddit1/comments"
        );

        RedditThread thread2 = mockThread(
                CATS,
                "div2_title",
                5002,
                "http://reddit.com/r/mocksubreddit2/link",
                "http://reddit.com/r/mocksubreddit2/comments"
        );

        List<RedditThread> result = crawler.crawl(CATS,driver);
        assertThat(crawler.crawl(CATS,driver), hasItems(thread1, thread2));
    }


    private RedditThread mockThread(String subreddit, String title, Integer upvotes, String link, String commentsLink){
        RedditThread thread = new RedditThread();

        thread.setSubreddit(subreddit);
        thread.setTitle(title);
        thread.setUpvotes(upvotes);
        thread.setThreadLink(link);
        thread.setCommentsLink(commentsLink);

        return thread;
    }

    private WebElement mockWebElement(String id, String title, String upvotes, String link, String commentsLink){
        WebElement mockDiv = mock(WebElement.class);
        WebElement mockDivTitle = mock(WebElement.class);
        WebElement mockDivComments = mock(WebElement.class);

        when(mockDivTitle.getText()).thenReturn(title);
        when(mockDivComments.getAttribute(anyString())).thenReturn(commentsLink);

        when(mockDiv.getAttribute("id")).thenReturn(id);
        when(mockDiv.getAttribute("data-score")).thenReturn(upvotes);
        when(mockDiv.getAttribute("data-permalink")).thenReturn(link);
        when(mockDiv.findElement(By.className("title"))).thenReturn(mockDivTitle);
        when(mockDiv.findElement(By.cssSelector("a[data-event-action='comments']"))).thenReturn(mockDivComments);

        return mockDiv;
    }
}

