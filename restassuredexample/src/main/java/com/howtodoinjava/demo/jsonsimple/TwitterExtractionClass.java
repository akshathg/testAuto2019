package com.howtodoinjava.demo.jsonsimple;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;



public class TwitterExtractionClass {


    public static List<String> crawl() throws IOException {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey("s4Km6gAkcDTXes983vaX8eP3v");
        cb.setOAuthConsumerSecret("2dE1ecfcu34QwwTpx2iRoJLRN1yCskgczYrbUhNw4QK0uo3n7O");
        cb.setOAuthAccessToken("2880371635-Zu51wYpXub0swQhXQm3n0oBcEbexJIGEN88klhN");
        cb.setOAuthAccessTokenSecret("Y6suyTtwdkfJJGGiUkAoFc1GLDApbGt6b0ELSMkBYsChF");
        List<String> listOfTweets = new ArrayList<String>();

        Twitter twitter = new TwitterFactory(cb.build()).getInstance();
        // First param of Paging() is the page number, second is the number per
        // page (this is capped around 200 I think.
        ObjectMapper mapper=new ObjectMapper();

        Paging paging = new Paging(1,50);
        try {
            List<Status> statuses = twitter.getUserTimeline(
                    "stepin_forum", paging);

            System.out.println("[");

            int i=0;

            for (Status status : statuses) {
                try {
                    String tweet = mapper.writeValueAsString(status);
                    if(tweet.contains("retweetCount"))
                    {
                        listOfTweets.add(tweet);
                    }

                } catch (JsonProcessingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (TwitterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return listOfTweets;
    }

    public static void whenWriteStringUsingBufferedWritter_thenCorrect(String str)
            throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("tweets.txt"));
        writer.write(str);

        writer.close();
    }

    public static int[] methodMax()
    {
        List<String> listOfTweets = null;
        try {
            listOfTweets = TwitterExtractionClass.crawl();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<String,Object>();
        List<Integer> retweetCount = new ArrayList<Integer>();
        System.out.println("list :"+listOfTweets);


        for (String tweet : listOfTweets) {

            if(tweet.contains("retweetCount"))
            {
                Pattern p = Pattern.compile("\"retweetCount\":(\\d)");
                Matcher m = p.matcher(tweet);
                if(m.find())
                {
                    System.out.println("Group 1:"+m.group(1));
                    map.put(tweet,m.group(1));
                    retweetCount.add(Integer.parseInt( m.group(1)));
                }
            }
        }

        Collections.sort(retweetCount, Collections.reverseOrder());
        System.out.println("list :"+retweetCount);

        int highestCount = retweetCount.get(0);
        System.out.println("Highest retweetCount :"+highestCount);
        int values[] = new int[2];
        values[0]=retweetCount.get(0);
        values[1]=highestCount;
        return values;
    }

}