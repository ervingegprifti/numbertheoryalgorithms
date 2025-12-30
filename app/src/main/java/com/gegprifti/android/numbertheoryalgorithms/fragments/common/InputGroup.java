package com.gegprifti.android.numbertheoryalgorithms.fragments.common;


import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;


public class InputGroup {
    public final boolean isCompactInputView;
    public final TextView expandedLabel;
    public final String expandedLabelText;
    public final TextView expandedLabelElastic;
    public final EditText expandedInput;
    public final TextView compactLabel;
    public final EditText compactInput;


    private InputGroup(Builder builder) {
        this.isCompactInputView = builder.isCompactInputView;
        this.expandedLabel = builder.expandedLabel;
        this.expandedLabelText = builder.expandedLabelText;
        this.expandedLabelElastic = builder.expandedLabelElastic;
        this.expandedInput = builder.expandedInput;
        this.compactLabel = builder.compactLabel;
        this.compactInput = builder.compactInput;
    }


    public static class Builder {
        private boolean isCompactInputView = false;
        private TextView expandedLabel;
        private String expandedLabelText;
        private TextView expandedLabelElastic;
        private EditText expandedInput;
        private TextView compactLabel;
        private EditText compactInput;

        public Builder setIsCompactInputView(boolean isCompactInputView) {
            this.isCompactInputView = isCompactInputView;
            return this;
        }

        public Builder setExpandedControls(@NonNull TextView label, @NonNull String labelText, @NonNull TextView labelElastic, @NonNull EditText inputExpanded) {
            this.expandedLabel = label;
            this.expandedLabelText = labelText;
            this.expandedLabelElastic = labelElastic;
            this.expandedInput = inputExpanded;
            return this;
        }

        public Builder setCompactControls(TextView labelCompact, EditText inputCompact) {
            this.compactLabel = labelCompact;
            this.compactInput = inputCompact;
            return this;
        }

        public InputGroup build() {
            // Validate and ensure required fields are not null.
            if (expandedLabel == null) {
                throw new IllegalStateException("label cannot be null");
            }
            if (expandedLabelText == null) {
                throw new IllegalStateException("labelText cannot be null");
            }
            if (expandedLabelElastic == null) {
                throw new IllegalStateException("labelElastic cannot be null");
            }
            if (expandedInput == null) {
                throw new IllegalStateException("input cannot be null");
            }
            //if (labelCompact == null) { // TODO +++ check null when implemented.
            //    throw new IllegalStateException("labelCompact cannot be null");
            //}
            //if (inputCompact == null) { // TODO +++ check null when implemented.
            //    throw new IllegalStateException("inputCompact cannot be null");
            //}

            return new InputGroup(this);
        }
    }

    public boolean isInputEmpty() {
        boolean inputExpandedIsEmpty = TextUtils.isEmpty(expandedInput.getText().toString().trim());
        boolean inputCompactIsEmpty = TextUtils.isEmpty(compactInput.getText().toString().trim());
        return inputExpandedIsEmpty || inputCompactIsEmpty;
    }
}