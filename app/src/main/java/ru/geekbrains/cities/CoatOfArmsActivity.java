package ru.geekbrains.cities;

import static ru.geekbrains.cities.CoatOfArmsFragment.ARG_INDEX;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class CoatOfArmsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coat_of_arms);

        if (Utils.isLandscape(getResources())) {
            finish();
            return;
        }

        if (savedInstanceState == null)
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.coat_of_arms_fragment_container, CoatOfArmsFragment.newInstance(getIntent().getExtras().getInt(ARG_INDEX)))
                    .commit();
    }
}