package roadtrippr.roadtrippr;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;

public class SettingsActivity extends AppCompatActivity {

    Button saveButton;
    MultiAutoCompleteTextView favRestaurants, favTypes, noRestaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Hide soft keyboard by default
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        saveButton = (Button) findViewById(R.id.saveButton);

        final SharedPreferences sharedPref = getSharedPreferences("roadtrippr.roadtrippr", Context.MODE_PRIVATE);
        String favRestaurantsString = sharedPref.getString("favRestaurants", "");
        String favRestaurantsTypesString = sharedPref.getString("favRestaurantsTypes", "");
        String noRestaurantsString = sharedPref.getString("noRestaurants", "");

        favRestaurants = (MultiAutoCompleteTextView)findViewById(R.id.restaurants_field);
        favTypes = (MultiAutoCompleteTextView)findViewById(R.id.restaurant_types_field);
        noRestaurants = (MultiAutoCompleteTextView)findViewById(R.id.unacceptable_restaurants_field);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.restaurants, android.R.layout.simple_list_item_1);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this, R.array.restaurants_types, android.R.layout.simple_list_item_1);

        favRestaurants.setAdapter(adapter);
        favRestaurants.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        favTypes.setAdapter(adapter2);
        favTypes.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        noRestaurants.setAdapter(adapter);
        noRestaurants.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = favRestaurants.getText().toString();
                String str2 = favTypes.getText().toString();
                String str3 = noRestaurants.getText().toString();
                Log.i("Input: ", str1);

                sharedPref.edit().putString("favRestaurants", str1).apply();
                sharedPref.edit().putString("favRestaurantsTypes", str2).apply();
                sharedPref.edit().putString("noRestaurants", str3).apply();

            }
        });

        if (favRestaurantsString != "") {
            favRestaurants.setText(favRestaurantsString);
        }

        if (favRestaurantsTypesString != "") {
            favTypes.setText(favRestaurantsTypesString);
        }

        if (noRestaurantsString != "") {
            noRestaurants.setText(noRestaurantsString);
        }

    }
}
