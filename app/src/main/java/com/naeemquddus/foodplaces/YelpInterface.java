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

/**
 * Created by Naeem Quddus on 10/18/2014.
 */
public class YelpInterface {
    OAuthService service;
    Token accessToken;

    public static void main(String[] args) {
        //for testing purposes
        System.out.println("Hello World!");
    }

    public  YelpInterface () {
        
    }


    public String search(double latitude, double longitude) {
        return "";
    }

}


/*
Consumer Key 	    d4_ex4nkvhwIXr0ORRcwoA
Consumer Secret 	GCwyyg3N30ruj9JsG7fpzOLqKY0
Token 	            cIuOIoFwkzjCFui-7dsx52WAGJkEiiF0
Token Secret 	    u8m5h8dBhL6Ej-V1VsPZAVlcMis
 */