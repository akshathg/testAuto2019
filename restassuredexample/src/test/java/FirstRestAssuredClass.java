//package crawl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FirstRestAssuredClass {

    static public List<Map<String, Object>> crawl() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey("s4Km6gAkcDTXes983vaX8eP3v");
        cb.setOAuthConsumerSecret("2dE1ecfcu34QwwTpx2iRoJLRN1yCskgczYrbUhNw4QK0uo3n7O");
        cb.setOAuthAccessToken("2880371635-Zu51wYpXub0swQhXQm3n0oBcEbexJIGEN88klhN");
        cb.setOAuthAccessTokenSecret("Y6suyTtwdkfJJGGiUkAoFc1GLDApbGt6b0ELSMkBYsChF");

        Twitter twitter = new TwitterFactory(cb.build()).getInstance();
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        // First param of Paging() is the page number, second is the number per
        // page (this is capped around 200 I think.
        ObjectMapper mapper=new ObjectMapper();

        Paging paging = new Paging(1, 1);
        try {
            List<Status> statuses = twitter.getUserTimeline(
                    "stepin_forum", paging);
            for (Status status : statuses) {
                try {
                    System.out.println("tweet>>>>>>>>>>>>"+mapper.writeValueAsString(status));
                    String tweet=mapper.writeValueAsString(status);
                    Map<String,Object> map=mapper.readValue(tweet, Map.class);
                    list.add(map);
                } catch (JsonProcessingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (TwitterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
    public static void main(String[] args) {
        List<Map<String, Object>>list=FirstRestAssuredClass.crawl();
    }
}