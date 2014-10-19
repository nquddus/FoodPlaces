package com.naeemquddus.foodplaces;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;


public class end_result extends Activity {
    ArrayList<String> selections;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            selections = extras.getStringArrayList("prev_list");
        }
        else{
            selections = new ArrayList<String>();
        }
        String query  = "";
        for(String s : selections){
            query+=s + " ";
        }
        query+="restaurant";
        ArrayList<Restaurant> results = getRestaurant(query);

        if(results.size() > 0)
        {
            Restaurant selectedRestaurant = results.get(0);
            showResult(selectedRestaurant);
        }
        else{
            throwError();
        }

        setContentView(R.layout.activity_end_result);
    }

    public void showResult(Restaurant selectedRestaurant){
        TextView name_text = (TextView) findViewById(R.id.end_text_name);
        name_text.setText(selectedRestaurant.getName());

        TextView date_text = (TextView) findViewById(R.id.end_text_date);
        date_text.setText(selectedRestaurant.getName());

        TextView rating_text = (TextView) findViewById(R.id.end_text_rating);
        rating_text.setText(selectedRestaurant.getRating());

        TextView address_text = (TextView) findViewById(R.id.end_text_address);
        address_text.setText(selectedRestaurant.getAddress());

        TextView description_text = (TextView) findViewById(R.id.end_text_description);
        description_text.setText(selectedRestaurant.getDescription());

        TextView phone_text = (TextView) findViewById(R.id.end_text_phone);
        phone_text.setText(selectedRestaurant.getNumber());

        ImageView img = (ImageView) findViewById(R.id.end_image);
        setImage(img, selectedRestaurant.getUrl());
    }


    public void setImage(ImageView img, String urlString){
        try {
            URL url = new URL(urlString);
            HttpGet httpRequest;

            httpRequest = new HttpGet(url.toURI());

            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(httpRequest);

            HttpEntity entity = response.getEntity();
            BufferedHttpEntity b_entity = new BufferedHttpEntity(entity);
            InputStream input = b_entity.getContent();

            Bitmap bitmap = BitmapFactory.decodeStream(input);

            img.setImageBitmap(bitmap);

        } catch (Exception ex) {
            throwError();
        }
    }

    public ArrayList<Restaurant> getRestaurant(String fullQuery){
        YelpInterface yelp = new YelpInterface();
        return yelp.search(main_menu.latitudeField,main_menu.longitudeField, fullQuery);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.end_result, menu);
        return true;
    }

    public void throwError()
    {
        TextView text = (TextView) findViewById(R.id.end_text_name);
        text.setText("ERROR");
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
