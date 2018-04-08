package idwall.desafio.crawler.models;


public class RequestSubreddits {

    private String subreddits;
    private Boolean sendTelegram;
    private Long phoneNumber;

    public String getSubreddits() {
        return subreddits;
    }

    public void setSubreddits(String subreddits) {
        this.subreddits = subreddits;
    }

    public Boolean getSendTelegram() {
        return sendTelegram;
    }

    public void setSendTelegram(Boolean sendTelegram) {
        this.sendTelegram = sendTelegram;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
