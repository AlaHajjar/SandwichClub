package com.udacity.sandwichclub.utils;

import android.content.res.Resources;
import android.util.Log;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class JsonUtils {

    public static final String NAME_OBJECT="name";
    public static final String NAME_ARRAY="alsoKnownAs";
    public static final String ARRAY_INGREDIENTS="ingredients";
    public static final String PLACE_OF_ORIGIN="placeOfOrigin";
    public static final String DESCRIPTION="description";
    public static final String IMAGE="image";


    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject mainObj =new JSONObject(json);
            JSONObject name=mainObj.getJSONObject(NAME_OBJECT);
            String mMainName=name.getString("mainName");
            Log.v(JsonUtils.class.toString(),"mainName:" +mMainName);

            JSONArray JSONalsoKnownAs= name.getJSONArray(NAME_ARRAY);
            List<String> mAlsoKnownAs= new ArrayList<String>();

            for (int i=0; i<JSONalsoKnownAs.length(); i++){
                mAlsoKnownAs.add(JSONalsoKnownAs.getString(i));
                Log.v(JsonUtils.class.toString(),"AlsoKnownas "+i+" :" +mAlsoKnownAs.get(i));
            }





            String mPlace =mainObj.getString(PLACE_OF_ORIGIN);
            Log.v(JsonUtils.class.toString(),"Place of origin:" +mPlace);
             String mDescription=mainObj.getString(DESCRIPTION);
            Log.v(JsonUtils.class.toString(),"Description:" +mDescription);
            String mImage=mainObj.getString(IMAGE);
            Log.v(JsonUtils.class.toString(),"Link Image:" +mImage);



            JSONArray ingredients= mainObj.getJSONArray(ARRAY_INGREDIENTS);
            List<String> arrayIngredients= new ArrayList<String>();

            for (int i=0; i<ingredients.length(); i++){
                arrayIngredients.add(ingredients.getString(i));
                Log.v(JsonUtils.class.toString(),"Ingredient "+i+" :" +arrayIngredients.get(i));

            }

            return new Sandwich(mMainName,mAlsoKnownAs,mPlace,mDescription,mImage,arrayIngredients);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }
}
