package com.gegprifti.android.numbertheoryalgorithms.fragments.common;


import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;


public class InputGroup {
    public final boolean isCompactInputView;
    public final TextView label;
    public final String labelText;
    public final TextView labelElastic;
    public final EditText input;
    public final TextView labelCompact;
    public final EditText inputCompact;


    private InputGroup(Builder builder) {
        this.isCompactInputView = builder.isCompactInputView;
        this.label = builder.label;
        this.labelText = builder.labelText;
        this.labelElastic = builder.labelElastic;
        this.input = builder.input;
        this.labelCompact = builder.labelCompact;
        this.inputCompact = builder.inputCompact;
    }


    public static class Builder {
        private boolean isCompactInputView = false;
        private TextView label;
        private String labelText;
        private TextView labelElastic;
        private EditText input;
        private TextView labelCompact;
        private EditText inputCompact;

        public Builder setIsCompactInputView(boolean isCompactInputView) {
            this.isCompactInputView = isCompactInputView;
            return this;
        }

        public Builder setLabel(@NonNull TextView label, @NonNull String labelText, @NonNull TextView labelElastic) {
            this.label = label;
            this.labelText = labelText;
            this.labelElastic = labelElastic;
            return this;
        }

        public Builder setInput(@NonNull EditText input) {
            this.input = input;
            return this;
        }

        public Builder setCompactControls(TextView labelCompact, EditText inputCompact) {
            this.labelCompact = labelCompact;
            this.inputCompact = inputCompact;
            return this;
        }

        public InputGroup build() {
            // Validate and ensure required fields are not null.
            if (label == null) {
                throw new IllegalStateException("label cannot be null");
            }
            if (labelText == null) {
                throw new IllegalStateException("labelText cannot be null");
            }
            if (labelElastic == null) {
                throw new IllegalStateException("labelElastic cannot be null");
            }
            if (input == null) {
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
}