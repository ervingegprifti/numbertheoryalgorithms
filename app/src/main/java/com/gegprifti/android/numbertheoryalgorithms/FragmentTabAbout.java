package com.gegprifti.android.numbertheoryalgorithms;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.gegprifti.android.numbertheoryalgorithms.common.Helper;
import com.gegprifti.android.numbertheoryalgorithms.common.PopupSplashScreen;


public class FragmentTabAbout extends Fragment {
    private final static String TAG = "TabAbout";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View inflater = null;
        try {
            inflater = layoutInflater.inflate(R.layout.fragment_tab_about, container, false);
            final TextView textViewAppVersion = inflater.findViewById(R.id.TextViewAppVersion);
            final TextView textViewPrivacyPolicy = inflater.findViewById(R.id.TextViewPrivacyPolicy);
            final TextView textViewTermsAndConditions = inflater.findViewById(R.id.TextViewTermsAndConditions);
            final TextView textViewAboutContact = inflater.findViewById(R.id.TextViewAboutContact);
            final TextView textViewAboutAppWebsite = inflater.findViewById(R.id.TextViewAboutAppWebsite);
            final TextView textViewAboutAppGitHub = inflater.findViewById(R.id.TextViewAboutAppGitHub);
            final CardView cardViewShare = inflater.findViewById(R.id.CardViewShare);
            final CardView cardViewRateThisApp = inflater.findViewById(R.id.CardViewRateThisApp);
            final CardView cardViewSplashScreen = inflater.findViewById(R.id.CardViewSplashScreen);
            final TextView textViewAndroidPdfViewerProject = inflater.findViewById(R.id.TextViewAndroidPdfViewerProject);
            final TextView textViewAndroidPdfViewerLicense = inflater.findViewById(R.id.TextViewAndroidPdfViewerLicense);

            String versionName = Helper.GetAppVersionName(requireContext());
            textViewAppVersion.setText(versionName);

            // Events
            cardViewShare.setOnClickListener(view -> {
                String shareAppSubject = getResources().getString(R.string.app_name);
                String shareAppUrl = "https://play.google.com/store/apps/details?id=com.gegprifti.android.numbertheoryalgorithms";
                String shareAppChooser = "Share " + shareAppSubject + " via";
                Helper.shareApp(requireContext(), shareAppSubject, shareAppUrl, shareAppChooser);
            });
            cardViewRateThisApp.setOnClickListener(view -> Helper.openWith(requireContext(), "https://play.google.com/store/apps/details?id=com.gegprifti.android.numbertheoryalgorithms"));
            cardViewSplashScreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupSplashScreen popupSplashScreen = new PopupSplashScreen(requireActivity(), requireContext());
                    popupSplashScreen.Show();
                }
            });
            textViewAboutContact.setOnClickListener(view -> Helper.sendEmail(requireContext(), "gegprifti.ervin@gmail.com", "Hi", ""));
            textViewAboutAppWebsite.setOnClickListener(view -> Helper.openWith(requireContext(), "https://ervingegprifti.github.io/numbertheoryalgorithms/"));
            textViewAboutAppGitHub.setOnClickListener(view -> Helper.openWith(requireContext(), "https://github.com/ervingegprifti/numbertheoryalgorithms/"));
            textViewPrivacyPolicy.setOnClickListener(view -> Helper.openWith(requireContext(), "https://ervingegprifti.github.io/numbertheoryalgorithms/privacy_policy/"));
            textViewTermsAndConditions.setOnClickListener(view -> Helper.openWith(requireContext(), "https://ervingegprifti.github.io/numbertheoryalgorithms/terms_and_conditions/"));
            //
            textViewAndroidPdfViewerProject.setOnClickListener(view -> Helper.openWith(requireContext(), "https://github.com/barteksc/AndroidPdfViewer"));
            textViewAndroidPdfViewerLicense.setOnClickListener(view -> Helper.openWith(requireContext(), "https://github.com/barteksc/AndroidPdfViewer/blob/master/LICENSE"));
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }

        return inflater;
    }
}