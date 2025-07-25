package com.gegprifti.android.numbertheoryalgorithms.common;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import androidx.fragment.app.FragmentActivity;
import com.gegprifti.android.numbertheoryalgorithms.R;


/**
 * Displays a full-screen splash screen using a PopupWindow.
 */
public final class PopupSplashScreen {
    private final static String TAG = PopupSplashScreen.class.getSimpleName();

    private final FragmentActivity fragmentActivity;
    private final Context context;
    private PopupWindow popupWindow;

    public PopupSplashScreen(final FragmentActivity fragmentActivity, final Context context) {
        this.fragmentActivity = fragmentActivity;
        this.context = context;
    }

    /**
     * Shows the splash screen as a full-screen popup.
     */
    public void Show() {
        try {
            LayoutInflater layoutInflater = (LayoutInflater)fragmentActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // Inflate the view from a predefined XML layout
            @SuppressLint("InflateParams") View viewSplashScreenDialog = layoutInflater.inflate(R.layout.popup_splash_screen, null, false);
            LinearLayout linearLayoutSplashScreen = viewSplashScreenDialog.findViewById(R.id.LinearLayoutSplashScreen);

            // Create the PopupWindow to fill the entire screen
            popupWindow = new PopupWindow(viewSplashScreenDialog, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true); // true for focusable initially, can be adjusted

            // Set initial focus and touchability.
            // Setting focusable to false initially can sometimes allow underlying views to receive touches
            // but for a splash screen, you usually want it to capture all touches.
            // Setting it to true then updating is a common pattern to ensure it takes focus.
            popupWindow.setFocusable(false); // Temporarily false
            popupWindow.setOutsideTouchable(true); // Allows tapping outside to dismiss (if not full screen)
            popupWindow.update();

            // Show the popup window at the center of the screen
            popupWindow.showAtLocation(viewSplashScreenDialog, Gravity.CENTER, 0, 0);

            // Apply full-screen immersive mode to the Activity's window.
            // The PopupWindow will then naturally occupy this full-screen space.
            Helper.setFullScreenImmersive(fragmentActivity);

            // Make the popup window focusable after showing it, ensuring it handles input.
            popupWindow.setFocusable(true);
            popupWindow.update();

            // Events
            linearLayoutSplashScreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });

            // Add a listener for when the popup window is dismissed.
            // This is crucial to revert the Activity's window from full-screen mode.
            popupWindow.setOnDismissListener(() -> {
                Helper.exitFullScreen(fragmentActivity);
            });

        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }

}