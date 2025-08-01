package com.gegprifti.android.numbertheoryalgorithms.settings;


import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;


/**
 * A helper class to ease user preferences.
 * For consistency in every SwitchPreferenceCompat the default value should be false.
 *
 * @see <a href="https://developer.android.com/guide/topics/ui/settings/use-saved-values">Use saved values </a>
 */
public class UserSettings {
    // Clipboard Buttons Configuration.
    private static final String VIBRATE_ON_CLIPBOARD_BUTTON_CLICK = "VIBRATE_ON_CLIPBOARD_BUTTON_CLICK";
    private static final String NOTIFY_ON_CLIPBOARD_BUTTON_CLICK = "NOTIFY_ON_CLIPBOARD_BUTTON_CLICK";


    // UI Configuration.
    private static final String BIGGER_CONTROLS = "BIGGER_CONTROLS";
    private static final String BIGGER_CLIPBOARD_BUTTONS = "BIGGER_CLIPBOARD_BUTTONS";
    private static final String BIGGER_RESULT_DISPLAY = "BIGGER_RESULT_DISPLAY";
    private static final String SQUARE_RESULT_DISPLAY = "SQUARE_RESULT_DISPLAY";

    // Misc Configurations.
    private static final String TAB_SWIPE_GESTURES = "TAB_SWIPE_GESTURES";
    private static final String HIDE_EXAMPLE_BUTTONS = "HIDE_EXAMPLE_BUTTONS";

    // Quadratic Form Menu.
    private static final String SQF_INCLUDE_TRIVIAL_SOLUTIONS = "SQF_INCLUDE_TRIVIAL_SOLUTIONS";
    private static final String SQF_INCLUDE_ONLY_POSITIVE_SOLUTIONS = "SQF_INCLUDE_ONLY_POSITIVE_SOLUTIONS";
    private static final String SQF_INCLUDE_ONLY_NEGATIVE_SOLUTIONS = "SQF_INCLUDE_ONLY_NEGATIVE_SOLUTIONS";

    private static SharedPreferences getSharedPreferences(Context context) {
        return (context == null) ? null : PreferenceManager.getDefaultSharedPreferences(context);
    }


    private static boolean getBoolean(Context context, String key) {
        SharedPreferences sharedPreferences = UserSettings.getSharedPreferences(context);
        if (sharedPreferences == null) {
            return false;
        } else {
            return sharedPreferences.getBoolean(key, false);
        }
    }


    private static void setBoolean(Context context, String key, boolean value) {
        SharedPreferences sharedPreferences = UserSettings.getSharedPreferences(context);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(key, value);
            editor.apply();
        }
    }


    // Clipboard Buttons Configuration.
    public static boolean getVibrateOnClipboardButtonClick(Context context) {
        return getBoolean(context, VIBRATE_ON_CLIPBOARD_BUTTON_CLICK);
    }


    public static boolean getNotifyOnClipboardButtonClick(Context context) {
        return getBoolean(context, NOTIFY_ON_CLIPBOARD_BUTTON_CLICK);
    }


    // UI Configuration.
    public static boolean getBiggerControls(Context context) {
        return getBoolean(context, BIGGER_CONTROLS);
    }


    public static boolean getBiggerClipboardButtons(Context context) {
        return getBoolean(context, BIGGER_CLIPBOARD_BUTTONS);
    }


    public static boolean getBiggerResultDisplay(Context context) {
        return getBoolean(context, BIGGER_RESULT_DISPLAY);
    }


    public static boolean getSquareResultDisplay(Context context) {
        return getBoolean(context, SQUARE_RESULT_DISPLAY);
    }


    // Misc Configurations.
    public static boolean getTabSwipeGestures(Context context) {
        return getBoolean(context, TAB_SWIPE_GESTURES);
    }


    public static boolean getHideExampleButtons(Context context) {
        return getBoolean(context, HIDE_EXAMPLE_BUTTONS);
    }


    // Quadratic Form Menu.
    public static boolean getQFIncludeTrivialSolutions(Context context) {
        return getBoolean(context, SQF_INCLUDE_TRIVIAL_SOLUTIONS);
    }


    public static void setQFIncludeTrivialSolutions(Context context, boolean value) {
        setBoolean(context, SQF_INCLUDE_TRIVIAL_SOLUTIONS, value);
    }


    public static boolean getQFIncludeOnlyPositiveSolutions(Context context) {
        return getBoolean(context, SQF_INCLUDE_ONLY_POSITIVE_SOLUTIONS);
    }


    public static void setQFIncludeOnlyPositiveSolutions(Context context, boolean value) {
        setBoolean(context, SQF_INCLUDE_ONLY_POSITIVE_SOLUTIONS, value);
    }


    public static boolean getQFIncludeOnlyNegativeSolutions(Context context) {
        return getBoolean(context, SQF_INCLUDE_ONLY_NEGATIVE_SOLUTIONS);
    }


    public static void setQFIncludeOnlyNegativeSolutions(Context context, boolean value) {
        setBoolean(context, SQF_INCLUDE_ONLY_NEGATIVE_SOLUTIONS, value);
    }


    // Reset to default.
    public static void resetToDefault(Context context) {
        // Clipboard Buttons Configuration.
        setBoolean(context, VIBRATE_ON_CLIPBOARD_BUTTON_CLICK, false);
        setBoolean(context, NOTIFY_ON_CLIPBOARD_BUTTON_CLICK, false);

        // UI Configuration.
        setBoolean(context, BIGGER_CONTROLS, false);
        setBoolean(context, BIGGER_CLIPBOARD_BUTTONS, false);
        setBoolean(context, BIGGER_RESULT_DISPLAY, false);
        setBoolean(context, SQUARE_RESULT_DISPLAY, false);

        // Misc Configurations.
        setBoolean(context, TAB_SWIPE_GESTURES, false);
        setBoolean(context, HIDE_EXAMPLE_BUTTONS, false);

        // Quadratic Form Menu.
        setBoolean(context, SQF_INCLUDE_TRIVIAL_SOLUTIONS, false);
        setBoolean(context, SQF_INCLUDE_ONLY_POSITIVE_SOLUTIONS, false);
        setBoolean(context, SQF_INCLUDE_ONLY_NEGATIVE_SOLUTIONS, false);
    }
}