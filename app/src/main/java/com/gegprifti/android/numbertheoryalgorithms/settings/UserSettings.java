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
    // First time.
    private static final String FIRST_TIME_CALCULATOR = "FIRST_TIME_CALCULATOR";
    private static final String FIRST_TIME_QF = "FIRST_TIME_QF";
    private static final String FIRST_TIME_EA = "FIRST_TIME_EA";
    private static final String FIRST_TIME_EEA = "FIRST_TIME_EEA";
    private static final String FIRST_TIME_LDE2V = "FIRST_TIME_LDE2V";
    private static final String FIRST_TIME_LC1V = "FIRST_TIME_LC1V";
    private static final String FIRST_TIME_LC2V = "FIRST_TIME_LC2V";
    private static final String FIRST_TIME_TSH = "FIRST_TIME_TSH";
    private static final String FIRST_TIME_MF = "FIRST_TIME_MF";
    private static final String FIRST_TIME_PL = "FIRST_TIME_PL";


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

    private static SharedPreferences GetSharedPreferences(Context context) {
        return (context == null) ? null : PreferenceManager.getDefaultSharedPreferences(context);
    }

    private static boolean GetBoolean (Context context, String key) {
        SharedPreferences sharedPreferences = UserSettings.GetSharedPreferences(context);
        if (sharedPreferences == null) {
            return false;
        } else {
            return sharedPreferences.getBoolean(key, false);
        }
    }
    private static void SetBoolean (Context context, String key, boolean value) {
        SharedPreferences sharedPreferences = UserSettings.GetSharedPreferences(context);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(key, value);
            editor.apply();
        }
    }

    private static boolean getFirstTime (Context context, String key) {
        SharedPreferences sharedPreferences = UserSettings.GetSharedPreferences(context);
        if (sharedPreferences == null) {
            return false;
        } else {
            if(sharedPreferences.contains(key)) {
                boolean firstTime = GetBoolean(context, key);
                if (firstTime) {
                    SetBoolean(context, key, false);
                    return true;
                }
            } else {
                // Since the key does not exist we set it to true, indicating that this is the first time of its use.
                SetBoolean(context, key, true);
            }
        }
        return GetBoolean(context, key);
    }

    // First time.
    public static boolean getFirstTimeCalculator (Context context) {
        return getFirstTime(context, FIRST_TIME_CALCULATOR);
    }
    public static boolean getFirstTimeQF (Context context) {
        return getFirstTime(context, FIRST_TIME_QF);
    }
    public static boolean getFirstTimeEA (Context context) {
        return getFirstTime(context, FIRST_TIME_EA);
    }
    public static boolean getFirstTimeEEA (Context context) {
        return getFirstTime(context, FIRST_TIME_EEA);
    }
    public static boolean getFirstTimeLDE2V (Context context) {
        return getFirstTime(context, FIRST_TIME_LDE2V);
    }
    public static boolean getFirstTimeLC1V (Context context) {
        return getFirstTime(context, FIRST_TIME_LC1V);
    }
    public static boolean getFirstTimeLC2V (Context context) {
        return getFirstTime(context, FIRST_TIME_LC2V);
    }
    public static boolean getFirstTimeTSH (Context context) {
        return getFirstTime(context, FIRST_TIME_TSH);
    }
    public static boolean getFirstTimeMF (Context context) {
        return getFirstTime(context, FIRST_TIME_MF);
    }
    public static boolean getFirstTimePL (Context context) {
        return getFirstTime(context, FIRST_TIME_PL);
    }

    // Clipboard Buttons Configuration.
    public static boolean GetVibrateOnClipboardButtonClick (Context context) {
        return GetBoolean(context, VIBRATE_ON_CLIPBOARD_BUTTON_CLICK);
    }
    public static boolean GetNotifyOnClipboardButtonClick (Context context) {
        return GetBoolean(context, NOTIFY_ON_CLIPBOARD_BUTTON_CLICK);
    }

    // UI Configuration.
    public static boolean GetBiggerControls(Context context) {
        return GetBoolean(context, BIGGER_CONTROLS);
    }
    public static boolean GetBiggerClipboardButtons(Context context) {
        return GetBoolean(context, BIGGER_CLIPBOARD_BUTTONS);
    }
    public static boolean GetBiggerResultDisplay(Context context) {
        return GetBoolean(context, BIGGER_RESULT_DISPLAY);
    }
    public static boolean GetSquareResultDisplay (Context context) {
        return GetBoolean(context, SQUARE_RESULT_DISPLAY);
    }


    // Misc Configurations.
    public static boolean GetTabSwipeGestures(Context context) {
        return GetBoolean(context, TAB_SWIPE_GESTURES);
    }
    public static boolean GetHideExampleButtons (Context context) {
        return GetBoolean(context, HIDE_EXAMPLE_BUTTONS);
    }

    // Quadratic Form Menu.
    public static boolean GetQFIncludeTrivialSolutions(Context context) {
        return GetBoolean(context, SQF_INCLUDE_TRIVIAL_SOLUTIONS);
    }
    public static void SetQFIncludeTrivialSolutions(Context context, boolean value) {
        SetBoolean(context, SQF_INCLUDE_TRIVIAL_SOLUTIONS, value);
    }
    public static boolean GetQFIncludeOnlyPositiveSolutions(Context context) {
        return GetBoolean(context, SQF_INCLUDE_ONLY_POSITIVE_SOLUTIONS);
    }
    public static void SetQFIncludeOnlyPositiveSolutions(Context context, boolean value) {
        SetBoolean(context, SQF_INCLUDE_ONLY_POSITIVE_SOLUTIONS, value);
    }
    public static boolean GetQFIncludeOnlyNegativeSolutions(Context context) {
        return GetBoolean(context, SQF_INCLUDE_ONLY_NEGATIVE_SOLUTIONS);
    }
    public static void SetQFIncludeOnlyNegativeSolutions(Context context, boolean value) {
        SetBoolean(context, SQF_INCLUDE_ONLY_NEGATIVE_SOLUTIONS, value);
    }

    // Reset to default.
    public static void ResetToDefault(Context context) {
        // First time.
        SetBoolean(context, FIRST_TIME_CALCULATOR, true);
        SetBoolean(context, FIRST_TIME_QF, true);
        SetBoolean(context, FIRST_TIME_EA, true);
        SetBoolean(context, FIRST_TIME_EEA, true);
        SetBoolean(context, FIRST_TIME_LDE2V, true);
        SetBoolean(context, FIRST_TIME_LC1V, true);
        SetBoolean(context, FIRST_TIME_LC2V, true);
        SetBoolean(context, FIRST_TIME_TSH, true);
        SetBoolean(context, FIRST_TIME_MF, true);
        SetBoolean(context, FIRST_TIME_PL, true);

        // Clipboard Buttons Configuration.
        SetBoolean(context, VIBRATE_ON_CLIPBOARD_BUTTON_CLICK, false);
        SetBoolean(context, NOTIFY_ON_CLIPBOARD_BUTTON_CLICK, false);

        // UI Configuration.
        SetBoolean(context, BIGGER_CONTROLS, false);
        SetBoolean(context, BIGGER_CLIPBOARD_BUTTONS, false);
        SetBoolean(context, BIGGER_RESULT_DISPLAY, false);
        SetBoolean(context, SQUARE_RESULT_DISPLAY, false);

        // Misc Configurations.
        SetBoolean(context, TAB_SWIPE_GESTURES, false);
        SetBoolean(context, HIDE_EXAMPLE_BUTTONS, false);

        // Quadratic Form Menu.
        SetBoolean(context, SQF_INCLUDE_TRIVIAL_SOLUTIONS, false);
        SetBoolean(context, SQF_INCLUDE_ONLY_POSITIVE_SOLUTIONS, false);
        SetBoolean(context, SQF_INCLUDE_ONLY_NEGATIVE_SOLUTIONS, false);
    }

}