package com.gegprifti.android.numbertheoryalgorithms.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.lifecycle.Lifecycle;
import androidx.preference.PreferenceFragmentCompat;
import com.gegprifti.android.numbertheoryalgorithms.R;
import com.gegprifti.android.numbertheoryalgorithms.settings.UserSettings;


public class FragmentSettings extends PreferenceFragmentCompat implements MenuProvider {
    private final static String TAG = FragmentSettings.class.getSimpleName();

    private String rootKey;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupMenuProvider();
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        try {
            this.rootKey = rootKey;
            setPreferencesFromResource(R.xml.preferences, this.rootKey);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }

    //region MENU
    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        try {
            menuInflater.inflate(R.menu.menu_fragment_settings, menu);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }

    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        try {
            // Handle menu item clicks here based on their ID.
            int id = menuItem.getItemId();
            if (id == R.id.settings_menu_reset_to_default) {
                UserSettings.resetToDefault(requireContext());
                setPreferencesFromResource(R.xml.preferences, this.rootKey);
                return true;
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }

        // If the menu item was not handled by this fragment, return false
        // so that the hosting Activity or other MenuProviders can handle it.
        return false;
    }
    //endregion MENU

    /**
     * Essential for integrating Fragment's menu contributions with
     * the hosting Activity's menu system using the, lifecycle-aware MenuProvider API.
     */
    private void setupMenuProvider() {
        // Get the MenuHost from the activity
        MenuHost menuHost = requireActivity();
        // Add this Fragment as the MenuProvider
        menuHost.addMenuProvider(this, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }
}