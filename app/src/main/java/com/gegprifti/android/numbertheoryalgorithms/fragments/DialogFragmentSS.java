package com.gegprifti.android.numbertheoryalgorithms.fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.gegprifti.android.numbertheoryalgorithms.R;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.UIHelper;


public class DialogFragmentSS extends DialogFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make this dialog full screen.
        setStyle(DialogFragment.STYLE_NORMAL, R.style.style_full_screen_dialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.splash_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayout linearLayoutSplashScreen = view.findViewById(R.id.LinearLayoutSplashScreen);
        linearLayoutSplashScreen.setOnClickListener(v -> dismiss());
    }

    @Override
    public void onStart() {
        super.onStart();
        // Make the dialog full-screen
        Dialog dialog = getDialog();
        if (dialog != null) {
            UIHelper.setFullScreenImmersive(dialog);
            UIHelper.setEdgeToEdgeFullscreen(dialog);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        Dialog dialog = getDialog();
        if (dialog != null) {
            UIHelper.exitFullScreen(dialog);
        }
    }
}