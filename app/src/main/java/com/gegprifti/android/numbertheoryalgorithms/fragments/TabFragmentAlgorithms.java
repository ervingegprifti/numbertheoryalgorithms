package com.gegprifti.android.numbertheoryalgorithms.fragments;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gegprifti.android.numbertheoryalgorithms.R;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.SectionsPagerAdapter;


public class TabFragmentAlgorithms extends Fragment {
    private final static String TAG = TabFragmentAlgorithms.class.getSimpleName();

    private SectionsPagerAdapter sectionsPagerAdapter;
    public SectionsPagerAdapter getSectionsPagerAdapter() {
        return  sectionsPagerAdapter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater layoutInflater, final ViewGroup viewGroup, Bundle savedInstanceState) {
        View inflater = null;
        try {
            inflater = layoutInflater.inflate(R.layout.fragment_tab_algorithms, viewGroup, false);

            this.setupSectionsPagerAdapter();

            // Set the default
            if(sectionsPagerAdapter != null) {
                FragmentAlgorithms fragmentAlgorithms = (FragmentAlgorithms)sectionsPagerAdapter.getItemByName("FragmentAlgorithms");
                this.setFragment(fragmentAlgorithms);
            }

        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
        return inflater;
    }

    public void setFragment(Fragment fragment) {
        try {
            // FragmentManager fragmentManager = requireActivity().getSupportFragmentManager(); // Crash on some devices.
            // FragmentManager fragmentManager = requireActivity().getFragmentManager(); // This is to be used when called from an activity
            // Android Fragment no view found for ID? https://stackoverflow.com/questions/7508044/android-fragment-no-view-found-for-id
            FragmentManager fragmentManager = getChildFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            // Replace whatever is in the FrameLayoutFragmentTabAlgorithmsContainer view with this fragment,
            fragmentTransaction.replace(R.id.FrameLayoutFragmentTabAlgorithmsContainer, fragment);
            // Commit the transaction
            fragmentTransaction.commit();
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }

    // Populate fragments
    private void setupSectionsPagerAdapter() {
        try {
            // The order these are added defines the order they are displayed.
            sectionsPagerAdapter = new SectionsPagerAdapter(this);

            FragmentAlgorithms fragmentAlgorithms = new FragmentAlgorithms();
            fragmentAlgorithms.setFragmentTabAlgorithms(this); // Set the parent.
            sectionsPagerAdapter.addFragment(fragmentAlgorithms, "FragmentAlgorithms");

            FragmentBinaryQuadraticForm fragmentBinaryQuadraticForm = new FragmentBinaryQuadraticForm();
            fragmentBinaryQuadraticForm.setFragmentTabAlgorithms(this); // Set the parent
            sectionsPagerAdapter.addFragment(fragmentBinaryQuadraticForm, "FragmentBinaryQuadraticForm"); // Simple Quadratic Form

            FragmentEuclideanAlgorithm fragmentEuclideanAlgorithm = new FragmentEuclideanAlgorithm();
            fragmentEuclideanAlgorithm.setFragmentTabAlgorithms(this); // Set the parent
            sectionsPagerAdapter.addFragment(fragmentEuclideanAlgorithm, "FragmentEuclideanAlgorithm"); // Euclidean Algorithm

            FragmentExtendedEuclideanAlgorithm fragmentExtendedEuclideanAlgorithm = new FragmentExtendedEuclideanAlgorithm();
            fragmentExtendedEuclideanAlgorithm.setFragmentTabAlgorithms(this); // Set the parent
            sectionsPagerAdapter.addFragment(fragmentExtendedEuclideanAlgorithm, "FragmentExtendedEuclideanAlgorithm"); // Extended Euclidean Algorithm

            FragmentLinearCongruenceInOneVariable fragmentLinearCongruenceInOneVariable = new FragmentLinearCongruenceInOneVariable();
            fragmentLinearCongruenceInOneVariable.setFragmentTabAlgorithms(this); // Set the parent
            sectionsPagerAdapter.addFragment(fragmentLinearCongruenceInOneVariable, "FragmentLinearCongruenceInOneVariable"); // Linear Congruence

            FragmentLinearCongruenceInTwoVariables fragmentLinearCongruenceInTwoVariables = new FragmentLinearCongruenceInTwoVariables();
            fragmentLinearCongruenceInTwoVariables.setFragmentTabAlgorithms(this); // Set the parent
            sectionsPagerAdapter.addFragment(fragmentLinearCongruenceInTwoVariables, "FragmentLinearCongruenceInTwoVariables"); // Linear Congruence With TWO Variables

            FragmentLinearDiophantineEquationInTwoVariables fragmentLinearDiophantineEquationInTwoVariables = new FragmentLinearDiophantineEquationInTwoVariables();
            fragmentLinearDiophantineEquationInTwoVariables.setFragmentTabAlgorithms(this); // Set the parent
            sectionsPagerAdapter.addFragment(fragmentLinearDiophantineEquationInTwoVariables, "FragmentLinearDiophantineEquationInTwoVariables"); // Linear Diophantine Equation

            FragmentTonelliShanksAlgorithm fragmentTonelliShanksAlgorithm = new FragmentTonelliShanksAlgorithm();
            fragmentTonelliShanksAlgorithm.setFragmentTabAlgorithms(this); // Set the parent
            sectionsPagerAdapter.addFragment(fragmentTonelliShanksAlgorithm, "FragmentTonelliShanksAlgorithm"); // Tonelli Shanks Algorithm

            FragmentModFactors fragmentModFactors = new FragmentModFactors();
            fragmentModFactors.setFragmentTabAlgorithms(this); // Set the parent
            sectionsPagerAdapter.addFragment(fragmentModFactors, "FragmentModFactors"); // Mod Factors

            FragmentPrimesList fragmentPrimesList = new FragmentPrimesList();
            fragmentPrimesList.setFragmentTabAlgorithms(this); // Set the parent
            sectionsPagerAdapter.addFragment(fragmentPrimesList, "FragmentPrimesList"); // Primes List
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }

}