package com.gegprifti.android.numbertheoryalgorithms;


import com.gegprifti.android.numbertheoryalgorithms.common.UserSettings;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;


public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity";
    private ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Splash Screen
        setTheme(R.style.MainAppThemeNoActionBar);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set Status Bar Color Programmatically (for API 21 and above)
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));


        // Display the long title here
        this.setTitle(getResources().getString(R.string.app_name_title));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setLogo(R.drawable.toolbar_logo);
        toolbar.setSubtitle(R.string.app_name_subtitle);

        // For tabs
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL); // To distribute the Tabs to all the of the width of your screen you need to set MATCH_PARENT on the TabLayout xml component
        // The pager widget, which handles animation and allows swiping horizontally to access previous and next wizard steps.
        viewPager2 = findViewById(R.id.viewPager2);
        // The pager adapter, which provides the pages to the view pager widget.
        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(this);
        viewPager2.setAdapter(tabPagerAdapter);

        // new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText(tabPagerAdapter.getPageTitle(position))).attach();
        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> {
                    String tabTitle = tabPagerAdapter.getPageTitle(position);
                    tab.setText(tabTitle);
        }).attach();

        // Start with the custom default tab CALCULATOR = 0, ALGORITHMS = 1, ABOUT = 2
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_main_settings) {
            Intent intentSettingsActivity = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intentSettingsActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
        refreshDisableTabSwiping();
    }

    private void refreshDisableTabSwiping() {
        if (this.viewPager2 != null) {
            boolean isUserInputEnabled = this.viewPager2.isUserInputEnabled();
            boolean disableTabSwiping = UserSettings.GetDisableTabSwiping(this);
            if (isUserInputEnabled && disableTabSwiping) {
                this.viewPager2.setUserInputEnabled(false);
            } else if (!isUserInputEnabled && !disableTabSwiping) {
                this.viewPager2.setUserInputEnabled(true);
            }
        }
    }

}