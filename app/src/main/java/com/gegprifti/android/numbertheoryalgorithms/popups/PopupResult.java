package com.gegprifti.android.numbertheoryalgorithms.popups;


import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.gegprifti.android.numbertheoryalgorithms.R;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.UIHelper;


public final class PopupResult {
    private final static String TAG = PopupResult.class.getSimpleName();

    final FragmentActivity fragmentActivity;
    final Context context;
    final String title;
    final Editable editableResult;
    private LinearLayout parentResultLinearLayoutGridContainer;
    private LinearLayout popupResultLinearLayoutGridContainer;
    final LinearLayout resultLinearLayoutGrid;
    private PopupWindow popupWindow;

    public PopupResult(FragmentActivity fragmentActivity, Context context, String title, Editable editableResult) {
        this(fragmentActivity, context, title, editableResult, null);
    }
    public PopupResult(FragmentActivity fragmentActivity, Context context, String title, LinearLayout resultLinearLayoutGrid) {
        this(fragmentActivity, context, title, null, resultLinearLayoutGrid);

    }
    public PopupResult(FragmentActivity fragmentActivity, Context context, String title, Editable editableResult, LinearLayout resultLinearLayoutGrid) {
        this.fragmentActivity = fragmentActivity;
        this.context = context;
        this.title = title;
        this.editableResult = editableResult;
        this.resultLinearLayoutGrid = resultLinearLayoutGrid;

        // Get the parent of resultLinearLayoutGrid.
        if (resultLinearLayoutGrid != null && resultLinearLayoutGrid.getParent() instanceof LinearLayout) {
            parentResultLinearLayoutGridContainer = (LinearLayout) resultLinearLayoutGrid.getParent();
        } else {
            parentResultLinearLayoutGridContainer = null;
        }
    }

    public void show() {
        try {
            LayoutInflater layoutInflater = (LayoutInflater)fragmentActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // Inflate the view from a predefined XML layout
            @SuppressLint("InflateParams") View viewFragmentResult = layoutInflater.inflate(R.layout.popup_result, null, false);

            LinearLayout linearLayoutMainResultContainer = viewFragmentResult.findViewById(R.id.LinearLayoutMainResultContainer);
            //TextView textViewBack = viewFragmentResult.findViewById(R.id.TextViewBack);
            ImageButton ImageButtonBack = viewFragmentResult.findViewById(R.id.ImageButtonBack);
            TextView textViewTitle = viewFragmentResult.findViewById(R.id.TextViewTitle);
            // Text result
            ScrollView scrollViewResultTextContained = viewFragmentResult.findViewById(R.id.ScrollViewResultTextContained);
            TextView editTextResult = viewFragmentResult.findViewById(R.id.EditTextResult);
            // Grid result
            popupResultLinearLayoutGridContainer = viewFragmentResult.findViewById(R.id.PopupResultLinearLayoutGridContainer);

            textViewTitle.setText(title);

            if (editableResult == null) {
                scrollViewResultTextContained.setVisibility(View.GONE);
            } else {
                scrollViewResultTextContained.setVisibility(View.VISIBLE);
                editTextResult.setText(editableResult);
            }

            if (parentResultLinearLayoutGridContainer != null && popupResultLinearLayoutGridContainer != null && resultLinearLayoutGrid != null) {
                parentResultLinearLayoutGridContainer.removeView(resultLinearLayoutGrid);
                popupResultLinearLayoutGridContainer.setVisibility(View.VISIBLE);
                popupResultLinearLayoutGridContainer.addView(resultLinearLayoutGrid);
            } else {
                popupResultLinearLayoutGridContainer.setVisibility(View.GONE);
            }

            // Create the PopupWindow to fill the entire screen
            popupWindow = new PopupWindow(viewFragmentResult, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true); // true for focusable initially, can be adjusted

            // Set initial focus and touchability.
            // Setting focusable to false initially can sometimes allow underlying views to receive touches
            // but for a splash screen, you usually want it to capture all touches.
            // Setting it to true then updating is a common pattern to ensure it takes focus.
            popupWindow.setFocusable(false); // Temporarily false
            popupWindow.setOutsideTouchable(true); // Allows tapping outside to dismiss (if not full screen)
            popupWindow.update();

            // Show the popup window at the center of the screen
            popupWindow.showAtLocation(viewFragmentResult, Gravity.CENTER, 0, 0);

            // Apply full-screen immersive mode to the Activity's window.
            // The PopupWindow will then naturally occupy this full-screen space.
            UIHelper.setFullScreenImmersive(fragmentActivity);

            // Make the popup window focusable after showing it, ensuring it handles input.
            popupWindow.setFocusable(true);
            popupWindow.update();

            // Events
            ImageButtonBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    // Display results back in parent linear layout.
                    if (parentResultLinearLayoutGridContainer != null && popupResultLinearLayoutGridContainer != null && resultLinearLayoutGrid != null) {
                        popupResultLinearLayoutGridContainer.removeView(resultLinearLayoutGrid);
                        parentResultLinearLayoutGridContainer.addView(resultLinearLayoutGrid);
                    }
                    UIHelper.exitFullScreen(fragmentActivity);
                }
            });
        } catch (Exception ex) {
            Log.e(TAG, "", ex);
        }
    }
}