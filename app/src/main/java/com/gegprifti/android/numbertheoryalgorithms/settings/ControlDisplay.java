package com.gegprifti.android.numbertheoryalgorithms.settings;


import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ControlDisplay {
    private static final int SMALLER_INPUT_LABEL_FONT_SIZE = 14;
    private static final int SMALLER_INPUT_FONT_SIZE = 14;
    private static final int SMALLER_BUTTON_FONT_SIZE = 14;
    private static final int SMALLER_CLIPBOARD_BUTTON_FONT_SIZE = 14;
    private static final int SMALLER_OUTPUT_FONT_SIZE = 14;

    private static final int BIGGER_INPUT_LABEL_FONT_SIZE = 16;
    private static final int BIGGER_INPUT_FONT_SIZE = 20;
    private static final int BIGGER_BUTTON_FONT_SIZE = 16;
    private static final int BIGGER_CLIPBOARD_BUTTON_FONT_SIZE = 16;
    private static final int BIGGER_OUTPUT_FONT_SIZE = 20;


    public static void setInputLabelFontSize(TextView control, Boolean biggerFontSize) {
        if (biggerFontSize) {
            setFontSize(control, BIGGER_INPUT_LABEL_FONT_SIZE);
        } else {
            setFontSize(control, SMALLER_INPUT_LABEL_FONT_SIZE);
        }
    }
    public static void setInputFontSize(EditText control, Boolean biggerFontSize) {
        if (biggerFontSize) {
            setFontSize(control, BIGGER_INPUT_FONT_SIZE);
        } else {
            setFontSize(control, SMALLER_INPUT_FONT_SIZE);
        }
    }
    public static void setButtonFontSize(Button control, Boolean biggerFontSize) {
        if (biggerFontSize) {
            setFontSize(control, BIGGER_BUTTON_FONT_SIZE);
        } else {
            setFontSize(control, SMALLER_BUTTON_FONT_SIZE);
        }
    }
    public static void setClipboardButtonFontSize(TextView control, Boolean biggerFontSize) {
        if (biggerFontSize) {
            setFontSize(control, BIGGER_CLIPBOARD_BUTTON_FONT_SIZE);
        } else {
            setFontSize(control, SMALLER_CLIPBOARD_BUTTON_FONT_SIZE);
        }
    }
    public static void setOutputFontSize(EditText control, Boolean biggerFontSize) {
        if (biggerFontSize) {
            setFontSize(control, BIGGER_OUTPUT_FONT_SIZE);
        } else {
            setFontSize(control, SMALLER_OUTPUT_FONT_SIZE);
        }
    }


    private static void setFontSize(View view, int fontSize) {
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