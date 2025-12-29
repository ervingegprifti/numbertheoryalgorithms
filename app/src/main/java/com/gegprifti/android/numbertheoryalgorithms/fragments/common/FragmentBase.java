package com.gegprifti.android.numbertheoryalgorithms.fragments.common;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
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
import java.util.HashMap;


public abstract class FragmentBase extends Fragment implements MenuProvider {
    protected static final BigInteger ZERO = BigInteger.ZERO;
    protected static final BigInteger ONE = BigInteger.ONE;
    protected ProgressManager progressManager;
    // Map each view to its own GestureDetector
    private final HashMap<View, GestureDetector> gestureMap = new HashMap<>();


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
            textValue = editText.getText().toString();
        }
        try {
            BigInteger value = new BigInteger(textValue);
            value = value.subtract(ONE);
            String newTextValue = value.toString();
            editText.setText(newTextValue);
        } catch (NumberFormatException ex) {
            UIHelper.showCustomToastError(requireContext(), "Input value not a number");
        }
    }


    /**
     * Decrease the integer value by one.
     * @param editText The edit text to decrease the integer value by one.
     * @param minValue The minimum value that the number should not go below.
     */
    protected void decreaseByOne(EditText editText, BigInteger minValue) {
        String textValue = editText.getText().toString();
        if (textValue.isEmpty()) {
            editText.setText("0");
            textValue = editText.getText().toString();
        }
        try {
            BigInteger value = new BigInteger(textValue);
            if (value.compareTo(minValue) <= 0) {
                return;
            }
            value = value.subtract(ONE);
            String newTextValue = value.toString();
            editText.setText(newTextValue);
        } catch (NumberFormatException ex) {
            UIHelper.showCustomToastError(requireContext(), "Input value not a number");
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
            textValue = editText.getText().toString();
        }
        try {
            BigInteger value = new BigInteger(textValue);
            value = value.add(ONE);
            String newTextValue = value.toString();
            editText.setText(newTextValue);
        } catch (NumberFormatException ex) {
            UIHelper.showCustomToastError(requireContext(), "Input value not a number");
        }
    }


    /**
     * Increase the integer value by one.
     * @param editText The edit text to increase the integer value by one.
     * @param maxValue The maximum value that the number should not go beyond.
     */
    protected void increaseByOne(EditText editText, BigInteger maxValue) {
        String textValue = editText.getText().toString();
        if (textValue.isEmpty()) {
            editText.setText("0");
            textValue = editText.getText().toString();
        }
        try {
            BigInteger value = new BigInteger(textValue);
            if (value.compareTo(maxValue) >= 0) {
                return;
            }
            value = value.add(ONE);
            String newTextValue = value.toString();
            editText.setText(newTextValue);
        } catch (NumberFormatException ex) {
            UIHelper.showCustomToastError(requireContext(), "Input value not a number");
        }
    }


    /**
     * Assigns a view to handle a double-tap event.
     * <p>
     * Usage example (call in onCreateView):
     * <pre>
     * <code>initDoubleTapDetector(editTextResult1);</code>
     * <code>initDoubleTapDetector(editTextResult2);</code>
     * </pre>
     *
     * @param view The view to attach the double-tap detector to.
     */
    @SuppressLint("ClickableViewAccessibility")
    protected void initDoubleTapDetector(View view) {
        GestureDetector detector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(@NonNull MotionEvent e) {
                fireOnDoubleTap(view);
                return true;
            }

            @Override
            public boolean onDown(@NonNull MotionEvent e) {
                // required for gestures to work
                return true;
            }
        });

        view.setOnTouchListener((v, event) -> detector.onTouchEvent(event));
        gestureMap.put(view, detector);
    }


    /**
     * Called when a double-tap event is detected.
     * <p>
     * Override this method in child fragments to handle double-tap actions.
     * <p>
     * Example usage in a child fragment:
     * <pre>
     * {@code
     * @Override
     * protected void fireOnDoubleTap(View view) {
     *     if (view == editTextResult1) {
     *         ...
     *     } else if (view == editTextResult2) {
     *         ...
     *     }
     * }
     * }
     * </pre>
     *
     * @param view The view that was double-tapped.
     */
    protected void fireOnDoubleTap(View view) { }
}
