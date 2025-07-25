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
import com.gegprifti.android.numbertheoryalgorithms.fragments.tabs.FragmentTabAlgorithms;
import com.gegprifti.android.numbertheoryalgorithms.R;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.FragmentBase;


public class FragmentAlgorithms extends FragmentBase {
    private final static String TAG = FragmentAlgorithms.class.getSimpleName();

    // Define the parent fragment
    private FragmentTabAlgorithms fragmentTabAlgorithms;
    // public FragmentTabAlgorithms getFragmentTabAlgorithms () { return this.fragmentTabAlgorithms; }
    public void setFragmentTabAlgorithms (FragmentTabAlgorithms fragmentTabAlgorithms) { this.fragmentTabAlgorithms = fragmentTabAlgorithms; }

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

            // The order these are added in FragmentTabAlgorithms.setupSectionsStatePagerAdapter() defines the order they are displayed.

            // FragmentQuadraticForm
            linearLayoutSimpleQuadraticForm.setOnClickListener(view -> {
                if(fragmentTabAlgorithms != null) {
                    FragmentQuadraticForm fragmentQuadraticForm = (FragmentQuadraticForm)fragmentTabAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentQuadraticForm");
                    fragmentTabAlgorithms.SetFragment(fragmentQuadraticForm);
                }
            });

            // FragmentEuclideanAlgorithm
            linearLayoutEuclideanAlgorithm.setOnClickListener(view -> {
                if(fragmentTabAlgorithms != null) {
                    FragmentEuclideanAlgorithm fragmentEuclideanAlgorithm = (FragmentEuclideanAlgorithm)fragmentTabAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentEuclideanAlgorithm");
                    fragmentTabAlgorithms.SetFragment(fragmentEuclideanAlgorithm);
                }
            });
            // FragmentExtendedEuclideanAlgorithm
            linearLayoutExtendedEuclideanAlgorithm.setOnClickListener(view -> {
                if(fragmentTabAlgorithms != null) {
                    FragmentExtendedEuclideanAlgorithm fragmentExtendedEuclideanAlgorithm = (FragmentExtendedEuclideanAlgorithm)fragmentTabAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentExtendedEuclideanAlgorithm");
                    fragmentTabAlgorithms.SetFragment(fragmentExtendedEuclideanAlgorithm);
                }
            });
            // FragmentLinearCongruenceInOneVariable
            linearLayoutLinearCongruence.setOnClickListener(view -> {
                if(fragmentTabAlgorithms != null) {
                    FragmentLinearCongruenceInOneVariable fragmentLinearCongruenceInOneVariable = (FragmentLinearCongruenceInOneVariable)fragmentTabAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentLinearCongruenceInOneVariable");
                    fragmentTabAlgorithms.SetFragment(fragmentLinearCongruenceInOneVariable);
                }
            });
            // FragmentLinearCongruenceInTwoVariables
            linearLayoutLinearCongruenceWithTwoVariables.setOnClickListener(view -> {
                if(fragmentTabAlgorithms != null) {
                    FragmentLinearCongruenceInTwoVariables fragmentLinearCongruenceInTwoVariables = (FragmentLinearCongruenceInTwoVariables)fragmentTabAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentLinearCongruenceInTwoVariables");
                    fragmentTabAlgorithms.SetFragment(fragmentLinearCongruenceInTwoVariables);
                }
            });
            // FragmentLinearDiophantineEquationInTwoVariables
            linearLayoutLinearDiophantineEquation.setOnClickListener(view -> {
                if(fragmentTabAlgorithms != null) {
                    FragmentLinearDiophantineEquationInTwoVariables fragmentLinearDiophantineEquationInTwoVariables = (FragmentLinearDiophantineEquationInTwoVariables)fragmentTabAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentLinearDiophantineEquationInTwoVariables");
                    fragmentTabAlgorithms.SetFragment(fragmentLinearDiophantineEquationInTwoVariables);
                }
            });
            // FragmentTonelliShanksAlgorithm
            linearLayoutTonelliShanksAlgorithm.setOnClickListener(view -> {
                if(fragmentTabAlgorithms != null) {
                    FragmentTonelliShanksAlgorithm fragmentTonelliShanksAlgorithm = (FragmentTonelliShanksAlgorithm)fragmentTabAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentTonelliShanksAlgorithm");
                    fragmentTabAlgorithms.SetFragment(fragmentTonelliShanksAlgorithm);
                }
            });
            // FragmentModFactors
            linearLayoutModFactors.setOnClickListener(view -> {
                if(fragmentTabAlgorithms != null) {
                    FragmentModFactors fragmentModFactors = (FragmentModFactors)fragmentTabAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentModFactors");
                    fragmentTabAlgorithms.SetFragment(fragmentModFactors);
                }
            });
            // FragmentPrimesList
            linearLayoutPrimesList.setOnClickListener(view -> {
                if(fragmentTabAlgorithms != null) {
                    FragmentPrimesList fragmentPrimesList = (FragmentPrimesList)fragmentTabAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentPrimesList");
                    fragmentTabAlgorithms.SetFragment(fragmentPrimesList);
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