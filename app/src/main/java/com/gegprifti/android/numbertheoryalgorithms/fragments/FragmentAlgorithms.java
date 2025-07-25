package com.gegprifti.android.numbertheoryalgorithms.fragments;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.gegprifti.android.numbertheoryalgorithms.R;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.FragmentBase;
import com.gegprifti.android.numbertheoryalgorithms.fragments.tabs.TabFragmentAlgorithms;


public class FragmentAlgorithms extends FragmentBase {
    private final static String TAG = FragmentAlgorithms.class.getSimpleName();

    // Define the parent fragment
    private TabFragmentAlgorithms tabFragmentAlgorithms;
    // public TabFragmentAlgorithms getFragmentTabAlgorithms () { return this.tabFragmentAlgorithms; }
    public void setFragmentTabAlgorithms (TabFragmentAlgorithms tabFragmentAlgorithms) { this.tabFragmentAlgorithms = tabFragmentAlgorithms; }

    // Important
    // All Fragment classes you create must have a public, no-arg constructor.
    // In general, the best practice is to simply never define any constructors at all and rely on Java to generate the default constructor for you.

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View inflater = null;
        try {
            inflater = layoutInflater.inflate(R.layout.fragment_algorithms, container, false);
            LinearLayout linearLayoutSimpleQuadraticForm = inflater.findViewById(R.id.LinearLayoutSimpleQuadraticForm);
            LinearLayout linearLayoutEuclideanAlgorithm = inflater.findViewById(R.id.LinearLayoutEuclideanAlgorithm);
            LinearLayout linearLayoutExtendedEuclideanAlgorithm = inflater.findViewById(R.id.LinearLayoutExtendedEuclideanAlgorithm);
            LinearLayout linearLayoutLinearCongruence = inflater.findViewById(R.id.LinearLayoutLinearCongruence);
            LinearLayout linearLayoutLinearCongruenceWithTwoVariables = inflater.findViewById(R.id.LinearLayoutLinearCongruenceWithTwoVariables);
            LinearLayout linearLayoutLinearDiophantineEquation = inflater.findViewById(R.id.LinearLayoutLinearDiophantineEquationInTwoVariables);
            LinearLayout linearLayoutTonelliShanksAlgorithm = inflater.findViewById(R.id.LinearLayoutTonelliShanksAlgorithm);
            LinearLayout linearLayoutModFactors = inflater.findViewById(R.id.LinearLayoutModFactors);
            LinearLayout linearLayoutPrimesList = inflater.findViewById(R.id.LinearLayoutPrimesList);

            // FragmentAlgorithms
            // This is the parent and since it is the first one, it is going by default to be displayed.

            // The order these are added in TabFragmentAlgorithms.setupSectionsStatePagerAdapter() defines the order they are displayed.

            // FragmentQuadraticForm
            linearLayoutSimpleQuadraticForm.setOnClickListener(view -> {
                if(tabFragmentAlgorithms != null) {
                    FragmentQuadraticForm fragmentQuadraticForm = (FragmentQuadraticForm) tabFragmentAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentQuadraticForm");
                    tabFragmentAlgorithms.SetFragment(fragmentQuadraticForm);
                }
            });

            // FragmentEuclideanAlgorithm
            linearLayoutEuclideanAlgorithm.setOnClickListener(view -> {
                if(tabFragmentAlgorithms != null) {
                    FragmentEuclideanAlgorithm fragmentEuclideanAlgorithm = (FragmentEuclideanAlgorithm) tabFragmentAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentEuclideanAlgorithm");
                    tabFragmentAlgorithms.SetFragment(fragmentEuclideanAlgorithm);
                }
            });
            // FragmentExtendedEuclideanAlgorithm
            linearLayoutExtendedEuclideanAlgorithm.setOnClickListener(view -> {
                if(tabFragmentAlgorithms != null) {
                    FragmentExtendedEuclideanAlgorithm fragmentExtendedEuclideanAlgorithm = (FragmentExtendedEuclideanAlgorithm) tabFragmentAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentExtendedEuclideanAlgorithm");
                    tabFragmentAlgorithms.SetFragment(fragmentExtendedEuclideanAlgorithm);
                }
            });
            // FragmentLinearCongruenceInOneVariable
            linearLayoutLinearCongruence.setOnClickListener(view -> {
                if(tabFragmentAlgorithms != null) {
                    FragmentLinearCongruenceInOneVariable fragmentLinearCongruenceInOneVariable = (FragmentLinearCongruenceInOneVariable) tabFragmentAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentLinearCongruenceInOneVariable");
                    tabFragmentAlgorithms.SetFragment(fragmentLinearCongruenceInOneVariable);
                }
            });
            // FragmentLinearCongruenceInTwoVariables
            linearLayoutLinearCongruenceWithTwoVariables.setOnClickListener(view -> {
                if(tabFragmentAlgorithms != null) {
                    FragmentLinearCongruenceInTwoVariables fragmentLinearCongruenceInTwoVariables = (FragmentLinearCongruenceInTwoVariables) tabFragmentAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentLinearCongruenceInTwoVariables");
                    tabFragmentAlgorithms.SetFragment(fragmentLinearCongruenceInTwoVariables);
                }
            });
            // FragmentLinearDiophantineEquationInTwoVariables
            linearLayoutLinearDiophantineEquation.setOnClickListener(view -> {
                if(tabFragmentAlgorithms != null) {
                    FragmentLinearDiophantineEquationInTwoVariables fragmentLinearDiophantineEquationInTwoVariables = (FragmentLinearDiophantineEquationInTwoVariables) tabFragmentAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentLinearDiophantineEquationInTwoVariables");
                    tabFragmentAlgorithms.SetFragment(fragmentLinearDiophantineEquationInTwoVariables);
                }
            });
            // FragmentTonelliShanksAlgorithm
            linearLayoutTonelliShanksAlgorithm.setOnClickListener(view -> {
                if(tabFragmentAlgorithms != null) {
                    FragmentTonelliShanksAlgorithm fragmentTonelliShanksAlgorithm = (FragmentTonelliShanksAlgorithm) tabFragmentAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentTonelliShanksAlgorithm");
                    tabFragmentAlgorithms.SetFragment(fragmentTonelliShanksAlgorithm);
                }
            });
            // FragmentModFactors
            linearLayoutModFactors.setOnClickListener(view -> {
                if(tabFragmentAlgorithms != null) {
                    FragmentModFactors fragmentModFactors = (FragmentModFactors) tabFragmentAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentModFactors");
                    tabFragmentAlgorithms.SetFragment(fragmentModFactors);
                }
            });
            // FragmentPrimesList
            linearLayoutPrimesList.setOnClickListener(view -> {
                if(tabFragmentAlgorithms != null) {
                    FragmentPrimesList fragmentPrimesList = (FragmentPrimesList) tabFragmentAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentPrimesList");
                    tabFragmentAlgorithms.SetFragment(fragmentPrimesList);
                }
            });
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
        return inflater;
    }

    //region MENU
    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        //try {
        //    menuInflater.inflate(R.menu.menu_to_inflate, menu);
        //} catch (Exception ex) {
        //    Log.e(TAG, "" + ex);
        //}
    }

    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // Handle menu item clicks here based on their ID.

        // If the menu item was not handled by this fragment, return false
        // so that the hosting Activity or other MenuProviders can handle it.
        return false;
    }
    //endregion MENU
}