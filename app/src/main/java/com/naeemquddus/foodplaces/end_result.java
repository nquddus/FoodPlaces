package com.naeemquddus.foodplaces;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class end_result extends Activity {
    ArrayList<String> selections;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        selections = new ArrayList<String>();
        String query  = "";
        for(String s : selections){
            query+=s + " ";
        }
     //   ArrayList<Restaurant> results = getRestaurant(query.substring(0, query.length()-1));
        setContentView(R.layout.activity_end_result);
    }

    //public ArrayList<Restaurant> getRestaurant(String fullQuery){
    //    YelpInterface yelp = new YelpInterface();
    //    return yelp.search(0,0, fullQuery);
    //}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.end_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
