package com.howtodoinjava.demo.jsonsimple;


import com.howtodoinjava.demo.jsonsimple.TwitterExtractionClass;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import twitter4j.TwitterException;

public class FileWriteDemo
{
    @SuppressWarnings("unchecked")
    public static void main( String[] args )
    {

        TwitterExtractionClass ob = new TwitterExtractionClass();

        JSONObject detail = new JSONObject();

        int values[]=ob.methodMax();
        detail.put("top_retweet_count", ""+values[0]);
        detail.put("top_like_count", ""+values[1]);


        JSONObject eobj = new JSONObject();
        eobj.put("response", detail);


        //Add to list
        JSONArray elist = new JSONArray();
        elist.add(eobj);


        //Write JSON file
        try (FileWriter file = new FileWriter("response.json")) {

            file.write(elist.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}