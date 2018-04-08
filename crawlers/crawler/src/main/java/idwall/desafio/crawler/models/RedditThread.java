package idwall.desafio.crawler.models;

import java.util.Objects;

public class RedditThread {

    private String subreddit;
    private Integer upvotes;
    private String title;
    private String threadLink;
    private String commentsLink;

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public Integer getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThreadLink() {
        return threadLink;
    }

    public void setThreadLink(String threadLink) {
        this.threadLink = threadLink;
    }

    public String getCommentsLink() {
        return commentsLink;
    }

    public void setCommentsLink(String commentsLink) {
        this.commentsLink = commentsLink;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Subreddit: ");
        sb.append(subreddit);
        sb.append("\n\n");

        sb.append("Título: ");
        sb.append(title);
        sb.append("\n\n");

        sb.append("Upvotes: ");
        sb.append(upvotes);
        sb.append("\n\n");

        sb.append("Link Thread: ");
        sb.append(threadLink);
        sb.append("\n\n");

        sb.append("Link Comentários: ");
        sb.append(commentsLink);

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RedditThread thread = (RedditThread) o;
        return subreddit.equals(thread.subreddit) &&
               upvotes.equals(thread.upvotes) &&
               title.equals(thread.title) &&
               threadLink.equals(thread.threadLink) &&
               commentsLink.equals(thread.commentsLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subreddit, title);
    }
}
