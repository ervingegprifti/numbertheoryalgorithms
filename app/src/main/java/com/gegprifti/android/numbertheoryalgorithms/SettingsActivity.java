package com.gegprifti.android.numbertheoryalgorithms;


import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.gegprifti.android.numbertheoryalgorithms.fragments.FragmentSettings;
import com.google.android.material.appbar.MaterialToolbar;


public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_settings);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container, new FragmentSettings())
                .commit();
    }


    @Override
    public boolean onSupportNavigateUp() {
        // Do not use android:parentActivityName=".MainActivity" in AndroidManifest
        // so when finishing this activity to return where we were.
        finish();
        return true;
    }
}