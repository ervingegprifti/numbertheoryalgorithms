package com.gegprifti.android.numbertheoryalgorithms;


import com.gegprifti.android.numbertheoryalgorithms.settings.UserSettings;
import android.content.Intent;
import android.os.Bundle;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.TabPagerAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import androidx.core.splashscreen.SplashScreen;
import androidx.viewpager2.widget.ViewPager2;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    private ViewPager2 viewPager2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Call before super.onCreate(savedInstanceState);
        SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        // Change the main background color.
        // If edge-to-edge fullscreen then this is the status bar color also.
        // View rootView = findViewById(R.id.main_content);
        // rootView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRed));

        // For tabs
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL); // To distribute the Tabs to all the of the width of your screen you need to set MATCH_PARENT on the TabLayout xml component
        // The pager widget, which handles animation and allows swiping horizontally to access previous and next wizard steps.
        viewPager2 = findViewById(R.id.viewPager2);
        // The pager adapter, which provides the pages to the view pager widget.
        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(this);
        viewPager2.setAdapter(tabPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> {
                    String tabTitle = tabPagerAdapter.getPageTitle(position);
                    tab.setText(tabTitle);
        }).attach();

        // Start with the custom default tab CALCULATOR = 0, ALGORITHMS = 1
        //TabLayout.Tab tabToSelect = tabLayout.getTabAt(1);
        //if(tabToSelect != null) {
        //    tabToSelect.select();
        //}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        try {
            // Handle menu item clicks here based on their ID.
            int id = item.getItemId();
            if (id == R.id.action_main_about) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;
            } else if (id == R.id.action_main_settings) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }

        return super.onOptionsItemSelected(item);
    }


    // Called when returning to the activity.
    @Override
    public void onResume() {
        super.onResume();
        refreshTabSwipeGestures();
    }


    private void refreshTabSwipeGestures() {
        if (this.viewPager2 != null) {
            boolean tabSwipeGesturesEnabled = this.viewPager2.isUserInputEnabled();
            boolean tabSwipeGestures = UserSettings.getTabSwipeGestures(this);
            if (tabSwipeGesturesEnabled && !tabSwipeGestures) {
                this.viewPager2.setUserInputEnabled(false);
            } else if (!tabSwipeGesturesEnabled && tabSwipeGestures) {
                this.viewPager2.setUserInputEnabled(true);
            }
        }
    }
}