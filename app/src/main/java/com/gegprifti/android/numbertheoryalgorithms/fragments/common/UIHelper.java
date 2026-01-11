package com.gegprifti.android.numbertheoryalgorithms.fragments.common;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.IBinder;
import android.os.VibrationEffect;
import android.os.Vibrator;
import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.FragmentActivity;

import android.os.VibratorManager;
import android.text.Html;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.gegprifti.android.numbertheoryalgorithms.R;
import com.gegprifti.android.numbertheoryalgorithms.settings.UserSettings;
import java.math.BigInteger;
import java.util.Locale;


public final class UIHelper {
    private final static String TAG = UIHelper.class.getSimpleName();


    //region Full Screen
    public static  void setFullScreenImmersive(@NonNull Activity activity) {
        Window window = activity.getWindow();
        if (window == null) {
            return;
        }
        setFullScreenImmersive(window);
    }


    public static void setFullScreenImmersive(@NonNull FragmentActivity fragmentActivity) {
        Window window = fragmentActivity.getWindow();
        if (window == null) {
            return;
        }
        setFullScreenImmersive(window);
    }


    public static void setFullScreenImmersive(@NonNull Dialog dialog) {
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        setFullScreenImmersive(window);
    }


    /**
     * Sets the given Window to full-screen immersive mode.
     * This hides the system bars (status bar and navigation bar).
     *
     * @param window The Window to apply full-screen mode to.
     */
    public static void setFullScreenImmersive(@NonNull Window window) {
        //
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // Tell the system that we want to handle the insets ourselves.
        // This ensures the content extends behind the system bars, allowing for true full-screen.
        WindowCompat.setDecorFitsSystemWindows(window, false);
        // Get the WindowInsetsControllerCompat for the window.
        // This controller provides APIs to manage system bars.
        WindowInsetsControllerCompat windowInsetsControllerCompat = WindowCompat.getInsetsController(window, window.getDecorView());
        // Hide specific system bars for full screen immersive mode.
        // WindowInsetsCompat.Type.systemBars() includes both status and navigation bars.
        windowInsetsControllerCompat.hide(WindowInsetsCompat.Type.systemBars());
        // Set behavior for when the system bars are hidden.
        // BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE: bars will temporarily show when user swipes from the edge.
        // insetsController.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        windowInsetsControllerCompat.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_DEFAULT);
        //
        window.setBackgroundDrawableResource(android.R.color.transparent);
    }


    public static void setEdgeToEdgeFullscreen(@NonNull Dialog dialog) {
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        setEdgeToEdgeFullscreen(window);
    }


    public static void setEdgeToEdgeFullscreen(@NonNull Window window) {
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View decorView = window.getDecorView();
        ViewCompat.setOnApplyWindowInsetsListener(decorView, (v, insets) -> {
            // Optional: apply padding or margin here based on insets
            Insets statusBars = insets.getInsets(WindowInsetsCompat.Type.statusBars());
            // Add padding so content doesn't go under status bar.
            v.setPadding(0, statusBars.top, 0, 0);
            return insets;
        });
    }


    public static void exitFullScreen(@NonNull Activity activity) {
        Window window = activity.getWindow();
        if (window == null) {
            return;
        }
        exitFullScreen(window);
    }


    public static void exitFullScreen(@NonNull FragmentActivity fragmentActivity) {
        Window window = fragmentActivity.getWindow();
        if (window == null) {
            return;
        }
        exitFullScreen(window);
    }


    public static void exitFullScreen(@NonNull Dialog dialog) {
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        exitFullScreen(window);
    }


    public static void exitFullScreen(@NonNull Window window) {
        WindowCompat.setDecorFitsSystemWindows(window, true); // Let system manage insets again
        WindowInsetsControllerCompat windowInsetsControllerCompat = WindowCompat.getInsetsController(window, window.getDecorView());
        windowInsetsControllerCompat.show(WindowInsetsCompat.Type.systemBars());
        windowInsetsControllerCompat.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_DEFAULT);
    }
    //endregion Full Screen


    //region Toasts
    public static void showCustomToastError(Context context, String message) {
        showCustomToastError(context, message, Toast.LENGTH_LONG);
    }
    public static void showCustomToastError(Context context, String message, int toastLength) {
        try {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            @SuppressLint("InflateParams") View layout = inflater.inflate(R.layout.custom_toast_error, null, false);
            TextView text = layout.findViewById(R.id.text);
            text.setText(Html.fromHtml(message, Html.FROM_HTML_MODE_LEGACY));
            Toast toast = new Toast(context.getApplicationContext());
            toast.setGravity(Gravity.BOTTOM, 0, 200); // CENTER_VERTICAL
            toast.setDuration(toastLength); // Toast.LENGTH_SHORT or Toast.LENGTH_LONG
            toast.setView(layout);
            toast.show();
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    public static void showCustomToastLight(Context context, String message) {
        showCustomToastLight(context, message, Toast.LENGTH_LONG);
    }
    public static void showCustomToastLight(Context context, String message, int toastLength) {
        try {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            @SuppressLint("InflateParams") View layout = inflater.inflate(R.layout.custom_toast_light, null, false);
            TextView text = layout.findViewById(R.id.text);
            text.setText(Html.fromHtml(message, Html.FROM_HTML_MODE_LEGACY));
            Toast toast = new Toast(context.getApplicationContext());
            toast.setGravity(Gravity.BOTTOM, 0, 200); // CENTER_VERTICAL
            toast.setDuration(toastLength); // Toast.LENGTH_SHORT or Toast.LENGTH_LONG
            toast.setView(layout);
            toast.show();
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    public static void showCustomToastDark(Context context, String message) {
        showCustomToastDark(context, message, Toast.LENGTH_LONG);
    }
    public static void showCustomToastDark(Context context, String message, int toastLength) {
        try {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            @SuppressLint("InflateParams") View layout = inflater.inflate(R.layout.custom_toast_dark, null, false);
            TextView text = layout.findViewById(R.id.text);
            text.setText(Html.fromHtml(message, Html.FROM_HTML_MODE_LEGACY));
            Toast toast = new Toast(context.getApplicationContext());
            toast.setGravity(Gravity.BOTTOM, 0, 200); // CENTER_VERTICAL
            toast.setDuration(Toast.LENGTH_LONG); // Toast.LENGTH_SHORT or Toast.LENGTH_LONG
            toast.setView(layout);
            toast.show();
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    //endregion Toasts


    public static void hideSoftKeyBoard(FragmentActivity fragmentActivity) {
        // This is to dismiss the keyboard
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) fragmentActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if(inputMethodManager == null) {
                return;
            }
            if(inputMethodManager.isAcceptingText()) {
                // verify if the soft keyboard is open
                View currentView = fragmentActivity.getCurrentFocus();
                if(currentView  != null) {
                    IBinder iBinder = currentView.getWindowToken();
                    if(iBinder != null) {
                        inputMethodManager.hideSoftInputFromWindow(iBinder, 0);
                    }
                }
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    //region Copy Paste Clear
    /**
     * Copy the text from the editText into clipboard.
     *
     * @param context
     * @param editText
     */
    public static void copyTextFromEditTextIntoClipboard(Context context, EditText editText) {
        try {
            // Source: https://developer.android.com/guide/topics/text/copy-paste.html
            String textToCopy = "";
            if (editText.getText() != null && !editText.getText().toString().isEmpty()) {
                textToCopy = editText.getText().toString();
            }
            ClipboardManager clipboardManager = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
            if(clipboardManager == null) {
                return;
            }
            ClipData clipData = ClipData.newPlainText("CopiedText", textToCopy);
            if(clipData == null) {
                return;
            }
            clipboardManager.setPrimaryClip(clipData);
            editText.clearFocus();
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    /**
     * Copy this text into clipboard.
     *
     * @param context
     * @param textToCopyToClipboard
     */
    public static void copyTextIntoClipboard(Context context, String textToCopyToClipboard) {
        try {
            vibrateOnButtonTap(context);
            // Source: https://developer.android.com/guide/topics/text/copy-paste.html
            if(textToCopyToClipboard.isEmpty()) {
                showCustomToastLight(context, "Nothing to copy", Toast.LENGTH_SHORT);
            } else {
                ClipboardManager clipboardManager = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                if(clipboardManager == null) {
                    return;
                }
                ClipData clipData = ClipData.newPlainText("CopiedText", textToCopyToClipboard);
                if(clipData == null) {
                    return;
                }
                clipboardManager.setPrimaryClip(clipData);
                showCustomToastLight(context, textToCopyToClipboard + " copied", Toast.LENGTH_SHORT);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    /**
     * Paste the text from the clipboard into the EditText.
     *
     * @param context
     * @param editText
     */
    public static void pasteTextFromClipboardIntoEditText(Context context, EditText editText) {
        try {
            // Source: https://developer.android.com/guide/topics/text/copy-paste.html
            ClipboardManager clipboardManager = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboardManager == null) {
                return;
            }
            ClipData clipData = clipboardManager.getPrimaryClip();
            if (clipData == null) {
                return;
            }
            ClipData.Item item = clipData.getItemAt(0);
            if(item == null) {
                return;
            }

            // Examines the item on the clipboard. If getText() does not return null, the clip item contains the text.
            if (!(clipboardManager.hasPrimaryClip())) {

            } else if (!(clipboardManager.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN))) {

            } else {
                // Clipboard contains plain text.
                String pasteData = "";
                if (item.getText() != null && !item.getText().toString().isEmpty()) {
                    pasteData = item.getText().toString();
                }
                editText.setText(pasteData);
            }
            editText.clearFocus();
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    /**
     * This is to clear the text of the editText
     *
     * @param context
     * @param editText
     */
    public static void clearEditText(Context context, EditText editText) {
        try {
            editText.setText("");
            editText.requestFocus();
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    //endregion Copy Paste Clear


    /**
     * Validate BigInteger number
     *
     * @param str
     * @return
     */
    private static boolean isBigInteger(String str) {
        try {
            BigInteger bigInteger = new BigInteger(str);
            return true;
        } catch(NumberFormatException nfe) {
            return false;
        }
    }


    /**
     * Get the app version.
     *
     * @param context
     * @return
     */
    public static String getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if(packageInfo != null) {
                return packageInfo.versionName;
            } else {
                return null;
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
            return null;
        }
    }


    /**
     * Used to start an action view intent
     *
     * @param context
     * @param url
     */
    public static void openWith(Context context, String url) {
        try {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            context.startActivity(intent);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    public static void sendEmail(Context context, String recipient, String subject, String body) {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient});
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        sendIntent.putExtra(Intent.EXTRA_TEXT, body);
        try {
            // Create a chooser to let the user pick an app.
            context.startActivity(Intent.createChooser(sendIntent, "Send email using..."));
        } catch (Exception ex) {
            Log.e(TAG, "Error sharing content: " + ex.getMessage(), ex);
        }
    }


    public static void shareApp(Context context, String shareAppSubject, String shareAppUrl, String shareAppChooser) {
        try {
            // https://developer.android.com/guide/components/intents-filters.html
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareAppSubject);
            intent.putExtra(android.content.Intent.EXTRA_TEXT, shareAppUrl);
            // Verify that the intent will resolve to an activity
            if(intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(Intent.createChooser(intent, shareAppChooser));
            } else {
                showCustomToastLight(context, "There is no application to share this kind of information");
            }
        } catch(Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    public  static void vibrateOnButtonTap(Context context) {
        try {
            boolean vibrateOnClipboardButtonClick = UserSettings.getVibrateOnClipboardButtonClick(context);
            if (vibrateOnClipboardButtonClick) {
                vibrate(context);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    /**
     * Vibrates the device for a short duration.
     * <p>
     * Make sure to add the following permission to your `AndroidManifest.xml` file: <br>
     * Requires: &lt;uses-permission android:name="android.permission.VIBRATE" /&gt;
     *
     * @param context The Context used to access system services.
     */
    private static  void vibrate(Context context) {
        VibratorManager vibratorManager = (VibratorManager) context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE);
        if (vibratorManager != null) {
            Vibrator vibrator = vibratorManager.getDefaultVibrator();
            vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK));
        }
    }


    public static String getNrOfDigits(String value) {
        String result = "";
        try {
            // Validate BigInteger
            if(UIHelper.isBigInteger(value)) {
                BigInteger bi = new BigInteger(value);
                bi = bi.abs(); // Make sure there are non negative numbers

                int nrOfDigits = bi.toString().length();

                if(nrOfDigits == 1) {
                    result = " (" + nrOfDigits + " digit)";
                } else {
                    result = " (" + nrOfDigits + " digits)";
                }
            } else {
                result = "";
            }
            return result;
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
            return result;
        }
    }


    public static void displayNormal(InputGroup inputGroup) {
        inputGroup.expandedLabel.setBackgroundResource(R.drawable.label_input_bg);
        inputGroup.expandedLabelElastic.setBackgroundResource(R.drawable.label_elastic_bg);
        inputGroup.expandedInput.setBackgroundResource(R.drawable.edittext_input_bg);
        if (inputGroup.compactLabel != null) { // TODO +++ remove the check later when implemented for all.
            inputGroup.compactLabel.setBackgroundResource(R.drawable.label_input_compact_bg);
        }
        if (inputGroup.compactInput != null) { // TODO +++ remove the check later when implemented for all.
            inputGroup.compactInput.setBackgroundResource(R.drawable.edittext_input_compact_bg);
        }
    }


    public static void displayError(InputGroup inputGroup) {
        inputGroup.expandedLabel.setBackgroundResource(R.drawable.label_input_error_bg);
        inputGroup.expandedLabelElastic.setBackgroundResource(R.drawable.label_elastic_error_bg);
        inputGroup.expandedInput.setBackgroundResource(R.drawable.edittext_input_error_bg);
        if (inputGroup.compactLabel != null) { // TODO +++ remove the check later when implemented for all.
            inputGroup.compactLabel.setBackgroundResource(R.drawable.label_input_error_compact_bg);
        }
        if (inputGroup.compactInput != null) { // TODO +++ remove the check later when implemented for all.
            inputGroup.compactInput.setBackgroundResource(R.drawable.edittext_input_error_compact_bg);
        }
        if (inputGroup.isCompactInputView && inputGroup.compactInput != null) { // TODO +++ remove the check later when implemented for all.
            inputGroup.compactInput.requestFocus();
        } else {
            inputGroup.expandedInput.requestFocus();
        }
    }


    public static void displayTheErrorMessage(Context  context, String errorMessage){
        if (!UIHelper.isNullOrEmpty(errorMessage)) {
            UIHelper.showCustomToastError(context, errorMessage);
        }
    }


    public static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }


    public static boolean checkInputMustBeFilled(Context context, InputGroup inputGroup) {
        TextView label = inputGroup.expandedLabel;
        String labelText = inputGroup.expandedLabelText;
        TextView labelElastic = inputGroup.expandedLabelElastic;
        EditText editText = inputGroup.expandedInput;

        String errorMessageFormat = "The input <b>%s</b> must be filled.";
        return checkInputMustBeFilled(context, inputGroup, errorMessageFormat);
    }


    /**
     * <p>Check the input must be filled.
     * <p><b>checkInputMustBeFilled</b>
     * @param context The context.
     * @param inputGroup
     * @param errorMessageFormat The error message format.
     * @return true if the check failed, false otherwise.
     */
    public static boolean checkInputMustBeFilled(Context context, InputGroup inputGroup, String errorMessageFormat) {
        TextView label = inputGroup.expandedLabel;
        String labelText = inputGroup.expandedLabelText;
        TextView labelElastic = inputGroup.expandedLabelElastic;
        EditText editText = inputGroup.expandedInput;

        boolean checkFailed;
        String errorMessage = "";
        String stringValue = editText.getText().toString();
        // Check.
        if(UIHelper.isNullOrEmpty(stringValue)) {
            errorMessage = String.format(Locale.getDefault(), errorMessageFormat,  labelText);
            displayError(inputGroup);
            checkFailed = true;
        } else {
            displayNormal(inputGroup);
            checkFailed = false;
        }
        // Notify before return.
        UIHelper.displayTheErrorMessage(context, errorMessage);
        // Return
        return checkFailed;
    }


    /**
     * <p>Check the input must be a number.
     * <p><b>checkInputMustBeFilled<br>
     * checkInputMustBeNumber</b>
     * @param context The context.
     * @param inputGroup
     * @return true if the check failed, false otherwise.
     */
    public static boolean checkInputMustBeNumber(Context context, InputGroup inputGroup) {
        TextView label = inputGroup.expandedLabel;
        String labelText = inputGroup.expandedLabelText;
        TextView labelElastic = inputGroup.expandedLabelElastic;
        EditText editText = inputGroup.expandedInput;

        boolean checkFailed;
        String errorMessage = "";
        String stringValue = editText.getText().toString();
        // Check.
        if (UIHelper.checkInputMustBeFilled(context, inputGroup)) {
            return true;
        }
        // Check.
        boolean isValueANumber = false;
        try {
            BigInteger value = new BigInteger(stringValue);
            // Just do some math
            BigInteger result = value.add(BigInteger.ONE);
            isValueANumber = true;
        } catch (Exception ignored) { }
        if(isValueANumber) {
            displayNormal(inputGroup);
            checkFailed = false;
        } else {
            errorMessage = String.format(Locale.getDefault(), "The value of <b>%s</b> must be a number.", labelText);
            displayError(inputGroup);
            checkFailed = true;
        }
        // Notify before return.
        UIHelper.displayTheErrorMessage(context, errorMessage);
        // Return.
        return checkFailed;
    }


    /**
     * <p>Check the input must be a prime number.
     * <p><b>checkInputMustBeFilled<br>
     * checkInputMustBeNumber<br>
     * checkInputMustBePrimeNumber</b>
     * @param context The context.
     * @param inputGroup
     * @return true if the check failed, false otherwise.
     */
    public static boolean checkInputMustBePrimeNumber(Context context, InputGroup inputGroup) {
        TextView label = inputGroup.expandedLabel;
        String labelText = inputGroup.expandedLabelText;
        TextView labelElastic = inputGroup.expandedLabelElastic;
        EditText editText = inputGroup.expandedInput;

        boolean checkFailed;
        String errorMessage = "";
        String stringValue = editText.getText().toString();
        // Check.
        if (UIHelper.checkInputMustBeNumber(context, inputGroup)) {
            return true;
        }
        // Check.
        BigInteger value = new BigInteger(stringValue);
        if(value.isProbablePrime(10)) {
            displayNormal(inputGroup);
            checkFailed = false;
        } else {
            errorMessage = String.format(Locale.getDefault(), "The value of <b>%s</b> must be a prime number.", labelText);
            displayError(inputGroup);
            checkFailed = true;
        }
        // Notify before return.
        UIHelper.displayTheErrorMessage(context, errorMessage);
        // Return.
        return checkFailed;
    }


    /**
     * <p>Check the input must be a number other than zero.
     * <p><b>checkInputMustBeFilled<br>
     * checkInputMustBeNumber<br>
     * checkInputMustBeOtherThanZero</b>
     * @param context The context.
     * @param inputGroup
     * @return true if the check failed, false otherwise.
     */
    public static boolean checkInputMustBeOtherThanZero(Context context, InputGroup inputGroup) {
        TextView label = inputGroup.expandedLabel;
        String labelText = inputGroup.expandedLabelText;
        TextView labelElastic = inputGroup.expandedLabelElastic;
        EditText editText = inputGroup.expandedInput;

        boolean checkFailed;
        String errorMessage = "";
        String stringValue = editText.getText().toString();
        // Check.
        if (UIHelper.checkInputMustBeNumber(context, inputGroup)) {
            return true;
        }
        // Check.
        BigInteger value = new BigInteger(stringValue);
        if(value.equals(BigInteger.ZERO)) {
            errorMessage = String.format(Locale.getDefault(), "The value of <b>%s</b> must be other than <b>0</b>.", labelText);
            displayError(inputGroup);
            checkFailed = true;
        } else {
            displayNormal(inputGroup);
            checkFailed = false;
        }
        // Notify before return.
        UIHelper.displayTheErrorMessage(context, errorMessage);
        // Return.
        return checkFailed;
    }


    /**
     * <p>Check the input must be a number between min value and max value inclusive.
     * <p><b>checkInputMustBeFilled<br>
     * checkInputMustBeNumber<br>
     * checkInputMustBeBetweenMinMaxInclusive</b>
     * @param context The context.
     * @param inputGroup
     * @param minValue The min value.
     * @param maxValue The max value.
     * @return true if the check failed, false otherwise.
     */
    public static boolean checkInputMustBeBetweenMinMaxInclusive(Context context, InputGroup inputGroup, BigInteger minValue, BigInteger maxValue) {
        TextView label = inputGroup.expandedLabel;
        String labelText = inputGroup.expandedLabelText;
        TextView labelElastic = inputGroup.expandedLabelElastic;
        EditText editText = inputGroup.expandedInput;

        boolean checkFailed;
        String errorMessage = "";
        String stringValue = editText.getText().toString();
        // Check.
        if (UIHelper.checkInputMustBeNumber(context, inputGroup)) {
            return true;
        }
        // Check.
        BigInteger value = new BigInteger(stringValue);
        if(value.compareTo(minValue) < 0 || value.compareTo(maxValue) > 0) {
            errorMessage = String.format(Locale.getDefault(), "The value of <b>%s</b> must be between <b>%s</b> and <b>%s</b> inclusive.", labelText, minValue, maxValue);
            displayError(inputGroup);
            checkFailed = true;
        } else {
            displayNormal(inputGroup);
            checkFailed = false;
        }
        // Notify before return.
        UIHelper.displayTheErrorMessage(context, errorMessage);
        // Return.
        return checkFailed;
    }


    /**
     * <p>Check the input must be a number greater than or equal to min value.
     * <p><b>checkInputMustBeFilled<br>
     * checkInputMustBeNumber<br>
     * checkInputMustBeGreaterThanOrEqualToMin</b>
     * @param context The context.
     * @param inputGroup
     * @param minValue The min value.
     * @return true if the check failed, false otherwise.
     */
    public static boolean checkInputMustBeGreaterThanOrEqualToMin(Context context, InputGroup inputGroup, BigInteger minValue) {
        TextView label = inputGroup.expandedLabel;
        String labelText = inputGroup.expandedLabelText;
        TextView labelElastic = inputGroup.expandedLabelElastic;
        EditText editText = inputGroup.expandedInput;

        boolean checkFailed;
        String errorMessage = "";
        String stringValue = editText.getText().toString();
        // Check.
        if (UIHelper.checkInputMustBeNumber(context, inputGroup)) {
            return true;
        }
        // Check.
        BigInteger value = new BigInteger(stringValue);
        if(value.compareTo(minValue) < 0) {
            errorMessage = String.format(Locale.getDefault(), "The value of <b>%s</b> must be greater than or equal to <b>%s</b>.", labelText, minValue);
            displayError(inputGroup);
            checkFailed = true;
        } else {
            displayNormal(inputGroup);
            checkFailed = false;
        }
        // Notify before return.
        UIHelper.displayTheErrorMessage(context, errorMessage);
        // Return.
        return checkFailed;
    }


    /**
     * <p>Check the input must be a number greater than the min value.
     * <p><b>checkInputMustBeFilled<br>
     * checkInputMustBeNumber<br>
     * checkInputMustBeGreaterThanMin</b>
     * @param context The context.
     * @param inputGroup
     * @param minValue The min value.
     * @return true if the check failed, false otherwise.
     */
    public static boolean checkInputMustBeGreaterThanMin(Context context, InputGroup inputGroup, BigInteger minValue) {
        TextView label = inputGroup.expandedLabel;
        String labelText = inputGroup.expandedLabelText;
        TextView labelElastic = inputGroup.expandedLabelElastic;
        EditText editText = inputGroup.expandedInput;

        boolean checkFailed;
        String errorMessage = "";
        String stringValue = editText.getText().toString();
        // Check.
        if (UIHelper.checkInputMustBeNumber(context, inputGroup)) {
            return true;
        }
        // Check.
        BigInteger number = new BigInteger(stringValue);
        if(number.compareTo(minValue) <= 0) {
            errorMessage = String.format(Locale.getDefault(), "The value of <b>%s</b> must be greater than <b>%s</b>.", labelText, minValue);
            displayError(inputGroup);
            checkFailed = true;
        } else {
            displayNormal(inputGroup);
            checkFailed = false;
        }
        // Notify before return.
        UIHelper.displayTheErrorMessage(context, errorMessage);
        // Return.
        return checkFailed;
    }



    public static InputFilter inputFilterIntegerOnly = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            if(dest.length() > 0) {
                if(dstart == 0) {
                    if (source.toString().equals("-")) {
                        if(dest.charAt(0) == '-') {
                            return "";
                        }
                        return null;
                    }
                } else if (source.toString().equals("-")) {
                    return "";
                }
            }
            return null;
        }
    };


    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        // Return px
        return dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        // Return dp
        return px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static String removeLastChar(String s) {
        return (s == null || s.isEmpty()) ? null : (s.substring(0, s.length() - 1));
    }

    public static String removeCharAtIndex(String str, int index) {
        return new StringBuilder(str).deleteCharAt(index).toString();
    }

    public static int countTheNumberOfChar(String text, char characterToCount) {
        int characterCounter = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == characterToCount) {
                characterCounter++;
            }
        }
        return characterCounter;
    }
}