package com.gegprifti.android.numbertheoryalgorithms.common;


import android.util.TypedValue;
import android.widget.TextView;


public class ClipboardButtonDisplay {
    private static final int SMALLER_CLIPBOARD_BUTTON_FONT_SIZE = 8;
    private static final int BIGGER_CLIPBOARD_BUTTON_FONT_SIZE = 14;

    public static void SetClipboardButtonFontSize (TextView control, Boolean smaller) {
        if (smaller) {
            control.setTextSize(TypedValue.COMPLEX_UNIT_SP, SMALLER_CLIPBOARD_BUTTON_FONT_SIZE);
        } else {
            control.setTextSize(TypedValue.COMPLEX_UNIT_SP, BIGGER_CLIPBOARD_BUTTON_FONT_SIZE);
        }
    }

}