package com.gegprifti.android.numbertheoryalgorithms.fragments.common;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import com.gegprifti.android.numbertheoryalgorithms.progress.ProgressManager;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Locale;


public abstract class FragmentBase extends Fragment implements MenuProvider {
    protected static final BigInteger ONE = BigInteger.ONE;
    protected ProgressManager progressManager;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressManager = new ProgressManager(requireActivity());
        setupMenuProvider();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // To prevent leaks! Cancel any running task when the view is destroyed.
        if (progressManager != null) {
            progressManager.cancel();
        }
    }

    public abstract void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater);


    public abstract boolean onMenuItemSelected(@NonNull MenuItem menuItem);


    /**
     * Essential for integrating Fragment's menu contributions with
     * the hosting Activity's menu system using the lifecycle-aware MenuProvider API.
     */
    private void setupMenuProvider() {
        // Get the MenuHost from the activity
        MenuHost menuHost = requireActivity();
        // Add this Fragment as the MenuProvider
        menuHost.addMenuProvider(this, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }


    /**
     * Decrease the integer value by one.
     * @param editText The edit text to decrease the integer value by one.
     */
    protected void decreaseByOne(EditText editText) {
        String textValue = editText.getText().toString();
        if (textValue.isEmpty()) {
            editText.setText("0");
        } else {
            try {
                BigInteger value = new BigInteger(textValue);
                value = value.subtract(ONE);
                NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
                String formatted = numberFormat.format(value);
                editText.setText(formatted);
            } catch (NumberFormatException ex) {
                UIHelper.showCustomToastError(requireContext(), "Input value not a number");
            }
        }
    }


    /**
     * Increase the integer value by one.
     * @param editText The edit text to increase the integer value by one.
     */
    protected void increaseByOne(EditText editText) {
        String textValue = editText.getText().toString();
        if (textValue.isEmpty()) {
            editText.setText("0");
        } else {
            try {
                BigInteger value = new BigInteger(textValue);
                value = value.add(ONE);
                NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
                String formatted = numberFormat.format(value);
                editText.setText(formatted);
            } catch (NumberFormatException ex) {
                UIHelper.showCustomToastError(requireContext(), "Input value not a number");
            }
        }
    }
}
