package com.naeemquddus.foodplaces;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

/**
 * Created by Naeem Quddus on 10/18/2014.
 */
public class YelpInterface {
    private static final String CONSUMER_KEY = "d4_ex4nkvhwIXr0ORRcwoA";
    private static final String CONSUMER_SECRET = "GCwyyg3N30ruj9JsG7fpzOLqKY0";
    private static final String TOKEN = "cIuOIoFwkzjCFui-7dsx52WAGJkEiiF0";
    private static final String TOKEN_SECRET = "u8m5h8dBhL6Ej-V1VsPZAVlcMis";

    OAuthService service;
    Token accessToken;

    public static void main(String[] args) {
        //for testing purposes
        System.out.println("Hello World!");
        YelpInterface yelp = new YelpInterface();
        yelp.search(30.2500,-97.7500,"asian restaurant");


    }

    public  YelpInterface () {
        service = new ServiceBuilder().provider(TwoStepOAuth.class).apiKey(CONSUMER_KEY).apiSecret(CONSUMER_SECRET).build();
        accessToken = new Token(TOKEN, TOKEN_SECRET);
    }


    public String search(double latitude, double longitude, String term) {
        OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
            request.addQuerystringParameter("term", term);
            request.addQuerystringParameter("ll", latitude+","+longitude);
            //request.addQuerystringParameter("limit", "39");
            request.addQuerystringParameter("radius_filter", String.valueOf(40000));

        System.out.println("Starting query");
        service.signRequest(this.accessToken, request);
        Response response = request.send();
        String jsonResponse =  response.getBody();

        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(jsonResponse);
        } catch (ParseException pe) {
            System.out.println("Error: could not parse JSON response:");
            System.out.println(jsonResponse);
        }

        JSONArray businesses = (JSONArray) json.get("businesses");
        if(businesses != null) {
            for(int i =0;i <businesses.size(); i++) {
                System.out.println(i + " :: " + businesses.get(i));
            }
        } else {
            System.out.println("Someting wong");
        }

            return "";
    }

}


/*
Consumer Key 	    d4_ex4nkvhwIXr0ORRcwoA
Consumer Secret 	GCwyyg3N30ruj9JsG7fpzOLqKY0
Token 	            cIuOIoFwkzjCFui-7dsx52WAGJkEiiF0
Token Secret 	    u8m5h8dBhL6Ej-V1VsPZAVlcMis
 */