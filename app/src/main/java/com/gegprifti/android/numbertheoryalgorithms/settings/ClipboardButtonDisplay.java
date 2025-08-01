package com.gegprifti.android.numbertheoryalgorithms.settings;


import android.util.TypedValue;
import android.widget.TextView;


public class ClipboardButtonDisplay {
    private static final int SMALLER_CLIPBOARD_BUTTON_FONT_SIZE = 14;
    private static final int BIGGER_CLIPBOARD_BUTTON_FONT_SIZE = 16;


    public static void setClipboardButtonFontSize(TextView control, Boolean bigger) {
        if (bigger) {
            control.setTextSize(TypedValue.COMPLEX_UNIT_SP, BIGGER_CLIPBOARD_BUTTON_FONT_SIZE);
        } else {
            control.setTextSize(TypedValue.COMPLEX_UNIT_SP, SMALLER_CLIPBOARD_BUTTON_FONT_SIZE);
        }
    }
}