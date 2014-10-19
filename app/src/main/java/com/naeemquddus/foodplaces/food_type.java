package com.naeemquddus.foodplaces;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class food_type extends Activity {
    ArrayList<Option> btnList;
    LinearLayout linear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        createFoodList();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_type);
        createButtonList();
    }
    public void createFoodList()
    {
        btnList = new ArrayList<Option>();
        String[] foodCategories = {
                "Asian",
                "European",
                "Mexican",
                "Don't care"
        };
        for(String s : foodCategories){
            //Perform yelp lookup here
            //if yelp lookup returned > 1 result, instantiate new button
            btnList.add(new Option(s));
        }
    }


    public void createButtonList()
    {
        linear = (LinearLayout) findViewById(R.id.food_list);
        for (int i = 0; i < btnList.size(); i++) {
            btnList.get(i).button = new Button(this);
            btnList.get(i).button.setHeight(50);
            btnList.get(i).button.setWidth(50);
            btnList.get(i).button.setTag(i);
            btnList.get(i).button.setOnClickListener(click_handler);
            linear.addView(btnList.get(i).button);
        }

    }
    View.OnClickListener click_handler = new View.OnClickListener() {
        public void onClick(View v) {
            for(Option opt : btnList)
            {
                if(opt.button.getTag().equals(v.getTag()))
                {
                    opt.votes++;
                    break;
                }
            }
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.food_type, menu);
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

    private class Option{
        boolean appears;
        String name;
        Button button;
        int votes;
        public Option(String n)
        {
            appears=false;
            votes = 0;
            name = n;
        }
    }
}
