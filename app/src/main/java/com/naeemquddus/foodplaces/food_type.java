package com.naeemquddus.foodplaces;

import android.app.Activity;
import android.content.Intent;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class food_type extends Activity {
    ArrayList<String> selections;
    ArrayList<Option> btnList;
    LinearLayout linear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            selections = extras.getStringArrayList("prev_list");
            // and get whatever type user account id is
        }
        else{
            selections = new ArrayList<String>();
        }
        createFoodList();
        setContentView(R.layout.activity_food_type);
        createButtonList();
        findViewById(R.id.submit).getBackground().setColorFilter(new LightingColorFilter(0xFF000000, 0xFF013B59));
    }
    public void createFoodList()
    {
        btnList = new ArrayList<Option>();
        String[] foodCategories = getFoodCategories();
        for(String s : foodCategories){

            //Perform yelp lookup here
            //if yelp lookup returned > 1 result, instantiate new button

            btnList.add(new Option(s));
        }
    }


    public String[] getFoodCategories()
    {
        switch(selections.size())
        {
            case 0:
                return new String[]{
                        "Asian",
                        "European",
                        "American",
                        "Mexican",
                        "Don't care"
                };
            case 1:
                if(selections.get(0).equals("Asian")){
                    return new String[]{
                            "Japanese",
                            "Chinese",
                            "Korean",
                            "Vietnamese",
                            "Mongolian",
                            "Indian",
                            "Thai",
                            "Don't care"
                    };
                }
                else if(selections.get(0).equals("European")){
                    return new String[]{
                            "Spanish",
                            "Italian",
                            "French",
                            "German",
                            "Irish",
                            "British",
                            "Polish"
                    };
                }
                else if(selections.get(0).equals("Mexican")){
                    return new String[]{
                            "Tacos",
                            "Burritos",
                            "Fajitas",
                            "Quesadillas",
                            "Don't care"
                    };
                }
                else if(selections.get(0).equals("American")){
                    return new String[]{
                            "Burgers",
                            "Fries",
                            "Steak",
                            "Seafood",
                            "Don't care"
                    };
                }
        }
        return new String[] {"You shouldn't see this."};
    }


    public void createButtonList()
    {
        linear = (LinearLayout) findViewById(R.id.food_list);
        for (int i = 0; i < btnList.size(); i++) {
            btnList.get(i).button = new Button(this);
            btnList.get(i).button.setText(btnList.get(i).name);
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
                    opt.button.setText(opt.name + " " + opt.votes);
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

    public void advanceFoodType(View view)
    {
        String selectedOption = "";
        int highest_votes = 0;
        for(Option opt : btnList)
        {
            if(!opt.name.equals("Don't care") && opt.votes > highest_votes)
            {
                selectedOption = opt.name;
                highest_votes = opt.votes;
            }
        }
        Intent intent;
        ArrayList<String> selectionsClone = new ArrayList<String>();
        for(String s:selections)
        {
            selectionsClone.add(s);
        }
        if(highest_votes == 0)
        {
            intent = new Intent(this, end_result.class);
        }
        else if(selections.size() >= 1){
            intent = new Intent(this, end_result.class);
            selectionsClone.add(selectedOption);
        }
        else{
            intent = new Intent(this, food_type.class);
            selectionsClone.add(selectedOption);
        }
        intent.putStringArrayListExtra("prev_list", selectionsClone);
        startActivity(intent);
        overridePendingTransition(R.anim.open_next, R.anim.close_main);
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
        String name;
        Button button;
        int votes;
        public Option(String n)
        {
            votes = 0;
            name = n;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition (R.anim.open_main, R.anim.close_next);
    }
}
