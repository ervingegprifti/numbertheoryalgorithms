package com.gegprifti.android.numbertheoryalgorithms.common;


import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ControlDisplay {
    private static final int SMALLER_INPUT_LABEL_FONT_SIZE = 14;
    private static final int SMALLER_INPUT_FONT_SIZE = 14;
    private static final int SMALLER_BUTTON_FONT_SIZE = 14;
    private static final int SMALLER_OUTPUT_FONT_SIZE = 14;

    private static final int BIGGER_INPUT_LABEL_FONT_SIZE = 16;
    private static final int BIGGER_INPUT_FONT_SIZE = 20;
    private static final int BIGGER_BUTTON_FONT_SIZE = 16;
    private static final int BIGGER_OUTPUT_FONT_SIZE = 20;

    public static void SetInputLabelFontSize (TextView control, Boolean smallerFontSize) {
        if (smallerFontSize) {
            SetFontSize(control, SMALLER_INPUT_LABEL_FONT_SIZE);
        } else {
            SetFontSize(control, BIGGER_INPUT_LABEL_FONT_SIZE);
        }
    }
    public static void SetInputFontSize (EditText control, Boolean smallerFontSize) {
        if (smallerFontSize) {
            SetFontSize(control, SMALLER_INPUT_FONT_SIZE);
        } else {
            SetFontSize(control, BIGGER_INPUT_FONT_SIZE);
        }
    }
    public static void SetButtonFontSize (Button control, Boolean smallerFontSize) {
        if (smallerFontSize) {
            SetFontSize(control, SMALLER_BUTTON_FONT_SIZE);
        } else {
            SetFontSize(control, BIGGER_BUTTON_FONT_SIZE);
        }
    }
    public static void SetOutputFontSize (EditText control, Boolean smallerFontSize) {
        if (smallerFontSize) {
            SetFontSize(control, SMALLER_OUTPUT_FONT_SIZE);
        } else {
            SetFontSize(control, BIGGER_OUTPUT_FONT_SIZE);
        }
    }

    private static void SetFontSize (View view, int fontSize) {
        if (view instanceof TextView) {
            TextView control = (TextView) view;
            control.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
        }
        if (view instanceof EditText) {
            EditText control = (EditText) view;
            control.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
        }
        if (view instanceof Button) {
            Button control = (Button) view;
            control.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
        }
    }
}