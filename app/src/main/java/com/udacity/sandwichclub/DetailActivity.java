package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        TextView alsoKnownAs = findViewById(R.id.also_known_tv);
        TextView origin = findViewById(R.id.origin_tv);
        TextView description= findViewById(R.id.description_tv);
        TextView ingredients= findViewById(R.id.ingredients_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];

        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();

        Picasso.with(this)
                .load(sandwich.getImage())
                .error(R.drawable.not_found)
                .into(ingredientsIv);


        setTitle(sandwich.getMainName());
        List<String> mKnownAs=sandwich.getAlsoKnownAs();
        if(mKnownAs.size()!=0){
            alsoKnownAs.setText(TextUtils.join(", ",mKnownAs));


        }
        else{
            alsoKnownAs.setText(R.string.knownas_error_message);
        }

        String mOrigin= sandwich.getPlaceOfOrigin();
        if(mOrigin.equals("")){
            origin.setText(R.string.origin_error_message);
        }
        else{
            origin.setText(mOrigin);
        }

        String mDescription= sandwich.getDescription();
        if(mDescription.equals("")){
            description.setText(R.string.detail_error_message);
        }else{
            description.setText(mDescription);
        }

        List<String> mIngredients= sandwich.getIngredients();
        if(mIngredients.size()!=0){
            ingredients.setText("*");
            ingredients.append(TextUtils.join("\n*",mIngredients));

        }
        else{
            alsoKnownAs.setText(R.string.detail_error_message);
        }


    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

    }
}
