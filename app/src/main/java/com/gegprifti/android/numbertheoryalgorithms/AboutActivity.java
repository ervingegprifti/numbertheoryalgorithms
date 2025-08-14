package com.gegprifti.android.numbertheoryalgorithms;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.gegprifti.android.numbertheoryalgorithms.fragments.DialogFragmentSS;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.UIHelper;
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity;
import com.google.android.material.appbar.MaterialToolbar;


public class AboutActivity extends AppCompatActivity {
    private final static String TAG = AboutActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_about);
        setSupportActionBar(toolbar);

        // Show the back arrow.
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        try {
            TextView textViewAppVersion = findViewById(R.id.TextViewAppVersion);
            TextView textViewPrivacyPolicy = findViewById(R.id.TextViewPrivacyPolicy);
            TextView textViewTermsAndConditions = findViewById(R.id.TextViewTermsAndConditions);
            TextView textViewAboutContact = findViewById(R.id.TextViewAboutContact);
            TextView textViewAboutAppWebsite = findViewById(R.id.TextViewAboutAppWebsite);
            TextView textViewAboutAppGitHub = findViewById(R.id.TextViewAboutAppGitHub);
            CardView cardViewShare = findViewById(R.id.CardViewShare);
            CardView cardViewRateThisApp = findViewById(R.id.CardViewRateThisApp);
            CardView cardViewSplashScreen = findViewById(R.id.CardViewSplashScreen);
            CardView cardViewThirdPartyLicenses = findViewById(R.id.CardViewThirdPartyLicenses);

            String versionName = UIHelper.getAppVersion(this);
            textViewAppVersion.setText(versionName);

            // Events
            cardViewShare.setOnClickListener(view -> {
                String shareAppSubject = getResources().getString(R.string.app_name);
                String shareAppUrl = "https://play.google.com/store/apps/details?id=com.gegprifti.android.numbertheoryalgorithms";
                String shareAppChooser = "Share " + shareAppSubject + " via";
                UIHelper.shareApp(this, shareAppSubject, shareAppUrl, shareAppChooser);
            });
            cardViewRateThisApp.setOnClickListener(view -> UIHelper.openWith(this, "https://play.google.com/store/apps/details?id=com.gegprifti.android.numbertheoryalgorithms"));
            cardViewSplashScreen.setOnClickListener(v -> {
                DialogFragmentSS dialogFragmentSS = new DialogFragmentSS();
                dialogFragmentSS.show(getSupportFragmentManager(), "splash_dialog");
            });
            cardViewThirdPartyLicenses.setOnClickListener(v -> {
                OssLicensesMenuActivity.setActivityTitle(getString(R.string.about_third_party_licenses));
                Intent intent = new Intent(this, OssLicensesMenuActivity.class);
                startActivity(intent);
            });
            textViewAboutContact.setOnClickListener(view -> UIHelper.sendEmail(this, "gegprifti.ervin@gmail.com", "Hi", ""));
            textViewAboutAppWebsite.setOnClickListener(view -> UIHelper.openWith(this, "https://ervingegprifti.github.io/numbertheoryalgorithms/"));
            textViewAboutAppGitHub.setOnClickListener(view -> UIHelper.openWith(this, "https://github.com/ervingegprifti/numbertheoryalgorithms/"));
            textViewPrivacyPolicy.setOnClickListener(view -> UIHelper.openWith(this, "https://ervingegprifti.github.io/numbertheoryalgorithms/privacy_policy/"));
            textViewTermsAndConditions.setOnClickListener(view -> UIHelper.openWith(this, "https://ervingegprifti.github.io/numbertheoryalgorithms/terms_and_conditions/"));
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Do not use android:parentActivityName=".MainActivity" in AndroidManifest
        // so when finishing this activity to return where we were.
        finish();
        return true;
    }
}