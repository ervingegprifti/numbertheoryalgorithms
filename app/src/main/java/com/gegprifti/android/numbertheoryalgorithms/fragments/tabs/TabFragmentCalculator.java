package com.gegprifti.android.numbertheoryalgorithms.fragments.tabs;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gegprifti.android.numbertheoryalgorithms.progress.ProgressStatus;
import com.gegprifti.android.numbertheoryalgorithms.R;
import com.gegprifti.android.numbertheoryalgorithms.settings.SettingsActivity;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmName;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.settings.ClipboardButtonDisplay;
import com.gegprifti.android.numbertheoryalgorithms.settings.ControlDisplay;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.UIHelper;
import com.gegprifti.android.numbertheoryalgorithms.popups.PopupDocumentation;
import com.gegprifti.android.numbertheoryalgorithms.settings.UserSettings;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.FragmentBase;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.Callback;

import java.math.BigInteger;


public class TabFragmentCalculator extends FragmentBase implements Callback {
    private final static String TAG = TabFragmentCalculator.class.getSimpleName();

    static final BigInteger ZERO = BigInteger.ZERO;
    static final BigInteger ONE = BigInteger.ONE;
    static final BigInteger TWO = BigInteger.valueOf(2L);
    static final BigInteger THREE = BigInteger.valueOf(3L);
    static final BigInteger FIVE = BigInteger.valueOf(5L);
    BigInteger INTEGER_MAX_VALUE = new BigInteger(Integer.toString(Integer.MAX_VALUE));

    TextView textViewCalculatorLabelA;
    TextView textViewCalculatorLabelElasticA;
    TextView textViewCalculatorCopyA;
    TextView textViewCalculatorPasteA;
    TextView textViewCalculatorClearA;
    EditText editTextCalculatorA;
    TextView textViewCalculatorLabelB;
    TextView textViewCalculatorLabelElasticB;
    TextView textViewCalculatorCopyB;
    TextView textViewCalculatorPasteB;
    TextView textViewCalculatorClearB;
    EditText editTextCalculatorB;
    Button buttonCalculatorAddition;
    Button buttonCalculatorSubtraction;
    Button buttonCalculatorMultiplication;
    Button buttonCalculatorDivision;
    Button buttonCalculatorPower;
    Button buttonCalculatorRoot;
    Button buttonCalculatorGcd;
    Button buttonCalculatorLcm;
    Button buttonCalculatorMod;
    Button buttonCalculatorModInverse;
    Button buttonCalculatorIsProbablePrime;
    Button buttonCalculatorEulerPhi;
    Button buttonCalculatorFactorial;
    Button buttonCalculatorNextProbablePrime;
    Button buttonCalculatorNextProbableTwinPrime;
    TextView textViewCalculatorLabelResult1;
    TextView textViewCalculatorLabelElasticResult1;
    TextView textViewCalculatorCopyResult1;
    TextView textViewCalculatorClearResult1;
    EditText editTextCalculatorResult1;
    LinearLayout linearLayoutCalculatorResult2Labels;
    LinearLayout linearLayoutCalculatorResult2;
    TextView textViewCalculatorLabelResult2;
    TextView textViewCalculatorLabelElasticResult2;
    TextView textViewCalculatorCopyResult2;
    TextView textViewCalculatorClearResult2;
    EditText editTextCalculatorResult2;

    //region CREATE
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
		View inflater = null;
        try {
            inflater = layoutInflater.inflate(R.layout.fragment_tab_calculator, container, false);
            textViewCalculatorLabelA = inflater.findViewById(R.id.TextViewCalculatorLabelA);
            textViewCalculatorLabelElasticA = inflater.findViewById(R.id.TextViewCalculatorLabelElasticA);
            textViewCalculatorCopyA = inflater.findViewById(R.id.TextViewCalculatorCopyA);
            textViewCalculatorPasteA = inflater.findViewById(R.id.TextViewCalculatorPasteA);
            textViewCalculatorClearA = inflater.findViewById(R.id.TextViewCalculatorClearA);
            editTextCalculatorA = inflater.findViewById(R.id.EditTextCalculatorA);
            textViewCalculatorLabelB = inflater.findViewById(R.id.TextViewCalculatorLabelB);
            textViewCalculatorLabelElasticB = inflater.findViewById(R.id.TextViewCalculatorLabelElasticB);
            textViewCalculatorCopyB = inflater.findViewById(R.id.TextViewCalculatorCopyB);
            textViewCalculatorPasteB = inflater.findViewById(R.id.TextViewCalculatorPasteB);
            textViewCalculatorClearB = inflater.findViewById(R.id.TextViewCalculatorClearB);
            editTextCalculatorB = inflater.findViewById(R.id.EditTextCalculatorB);
            buttonCalculatorAddition = inflater.findViewById(R.id.ButtonCalculatorAddition);
            buttonCalculatorSubtraction = inflater.findViewById(R.id.ButtonCalculatorSubtraction);
            buttonCalculatorMultiplication = inflater.findViewById(R.id.ButtonCalculatorMultiplication);
            buttonCalculatorDivision = inflater.findViewById(R.id.ButtonCalculatorDivision);
            buttonCalculatorPower = inflater.findViewById(R.id.ButtonCalculatorPower);
            buttonCalculatorRoot = inflater.findViewById(R.id.ButtonCalculatorRoot);
            buttonCalculatorGcd = inflater.findViewById(R.id.ButtonCalculatorGcd);
            buttonCalculatorLcm = inflater.findViewById(R.id.ButtonCalculatorLcm);
            buttonCalculatorMod = inflater.findViewById(R.id.ButtonCalculatorMod);
            buttonCalculatorModInverse = inflater.findViewById(R.id.ButtonCalculatorModInverse);
            buttonCalculatorIsProbablePrime = inflater.findViewById(R.id.ButtonCalculatorIsProbablePrime);
            buttonCalculatorEulerPhi = inflater.findViewById(R.id.ButtonCalculatorEulerPhi);
            buttonCalculatorFactorial = inflater.findViewById(R.id.ButtonCalculatorFactorial);
            buttonCalculatorNextProbablePrime = inflater.findViewById(R.id.ButtonCalculatorNextProbablePrime);
            buttonCalculatorNextProbableTwinPrime = inflater.findViewById(R.id.ButtonCalculatorNextProbableTwinPrime);
            textViewCalculatorLabelResult1 = inflater.findViewById(R.id.TextViewCalculatorLabelResult1);
            textViewCalculatorLabelElasticResult1 = inflater.findViewById(R.id.TextViewCalculatorLabelElasticResult1);
            textViewCalculatorCopyResult1 = inflater.findViewById(R.id.TextViewCalculatorCopyResult1);
            textViewCalculatorClearResult1 = inflater.findViewById(R.id.TextViewCalculatorClearResult1);
            editTextCalculatorResult1 = inflater.findViewById(R.id.EditTextCalculatorResult1);
            linearLayoutCalculatorResult2Labels = inflater.findViewById(R.id.LinearLayoutCalculatorResult2Labels);
            linearLayoutCalculatorResult2 = inflater.findViewById(R.id.LinearLayoutCalculatorResult2);
            textViewCalculatorLabelResult2 = inflater.findViewById(R.id.TextViewCalculatorLabelResult2);
            textViewCalculatorLabelElasticResult2 = inflater.findViewById(R.id.TextViewCalculatorLabelElasticResult2);
            textViewCalculatorCopyResult2 = inflater.findViewById(R.id.TextViewCalculatorCopyResult2);
            textViewCalculatorClearResult2 = inflater.findViewById(R.id.TextViewCalculatorClearResult2);
            editTextCalculatorResult2 = inflater.findViewById(R.id.EditTextCalculatorResult2);

            // Input filter integer only
            editTextCalculatorA.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextCalculatorB.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});

            // Default UI status
            // Check if this is the first time the user is using this.
            if (UserSettings.getFirstTimeCalculator(requireContext())) {
                String rsa_100_pq = "1522605027922533360535618378132637429718068114961380688657908494580122963258952897654000350692006139";
                String rsa_100_p = "37975227936943673922808872755445627854565536638199";
                String rsa_100_q = "40094690950920881030683735292761468389214899724061";
                editTextCalculatorA.setText(rsa_100_p);
                editTextCalculatorB.setText(rsa_100_q);
                // Reset the last button clicked.
                ResetAllAndSelectButtonClicked(null);
                UIHelper.HideSoftKeyBoard(requireActivity());
                editTextCalculatorA.clearFocus();
                editTextCalculatorB.clearFocus();
                // Run example
                OnButtonMultiplication(container, false);
            }

            // Events
            buttonCalculatorAddition.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonAddition(container, true);
                }
            });
            buttonCalculatorSubtraction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { OnButtonSubtraction(container, true); }
            });
            buttonCalculatorMultiplication.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { OnButtonMultiplication(container, true); }
            });
            buttonCalculatorDivision.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { OnButtonDivision(container, true); }
            });
            buttonCalculatorPower.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { OnButtonPower(container, true); }
            });
            buttonCalculatorRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonRoot(container, true);
                }
            });
            buttonCalculatorIsProbablePrime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { OnButtonIsProbablePrime(container, true); }
            });
            buttonCalculatorEulerPhi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { OnButtonEulerPhi(container, true); }
            });
            buttonCalculatorFactorial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { OnButtonFactorial(container, true); }
            });
            buttonCalculatorNextProbablePrime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { OnButtonNextProbablePrime(container, true); }
            });
            buttonCalculatorNextProbableTwinPrime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { OnButtonNextProbableTwinPrime(container, true); }
            });
            buttonCalculatorGcd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { OnButtonGcd(container, true); }
            });
            buttonCalculatorLcm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { OnButtonLcm(container, true); }
            });
            buttonCalculatorMod.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { OnButtonMod(container, true); }
            });
            buttonCalculatorModInverse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { OnButtonModInverse(container, true); }
            });
            textViewCalculatorCopyA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.CopyEditText(requireContext(), editTextCalculatorA);
                    ResetAllAndSelectClipboardButtonClicked(textViewCalculatorCopyA);
                }
            });
            textViewCalculatorCopyB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.CopyEditText(requireContext(), editTextCalculatorB);
                    ResetAllAndSelectClipboardButtonClicked(textViewCalculatorCopyB);
                }
            });
            textViewCalculatorCopyResult1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.CopyEditText(requireContext(), editTextCalculatorResult1);
                    ResetAllAndSelectClipboardButtonClicked(textViewCalculatorCopyResult1);
                }
            });
            textViewCalculatorCopyResult2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.CopyEditText(requireContext(), editTextCalculatorResult2);
                    ResetAllAndSelectClipboardButtonClicked(textViewCalculatorCopyResult2);
                }
            });
            textViewCalculatorPasteA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.PasteEditText(requireContext(), editTextCalculatorA);
                    ResetAllAndSelectClipboardButtonClicked(textViewCalculatorPasteA);
                }
            });
            textViewCalculatorPasteB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.PasteEditText(requireContext(), editTextCalculatorB);
                    ResetAllAndSelectClipboardButtonClicked(textViewCalculatorPasteB);
                }
            });
            textViewCalculatorClearA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.ClearEditText(requireContext(), editTextCalculatorA);
                    ResetAllAndSelectClipboardButtonClicked(textViewCalculatorClearA);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            textViewCalculatorClearB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.ClearEditText(requireContext(), editTextCalculatorB);
                    ResetAllAndSelectClipboardButtonClicked(textViewCalculatorClearB);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            textViewCalculatorClearResult1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.ClearEditText(requireContext(), editTextCalculatorResult1);
                    Result1Reset();
                    ResetAllAndSelectClipboardButtonClicked(textViewCalculatorClearResult1);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            textViewCalculatorClearResult2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.ClearEditText(requireContext(), editTextCalculatorResult2);
                    Result2Reset();
                    ResetAllAndSelectClipboardButtonClicked(textViewCalculatorClearResult2);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            editTextCalculatorA.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    String calculatorLabelA = "a" + UIHelper.GetNrOfDigits(s.toString());
                    textViewCalculatorLabelA.setText(calculatorLabelA);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            editTextCalculatorB.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    String calculatorLabelB = "b" + UIHelper.GetNrOfDigits(s.toString());
                    textViewCalculatorLabelB.setText(calculatorLabelB);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });

        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }

        return inflater;
    }
    //endregion CREATE


    //region MENU
    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        try {
            menuInflater.inflate(R.menu.menu_fragment_tab_calculator, menu);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }

    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        try {
            // Handle menu item clicks here based on their ID.
            int id = menuItem.getItemId();
            if (id == R.id.action_main_settings) {
                Intent intentSettingsActivity = new Intent(requireContext(), SettingsActivity.class);
                startActivity(intentSettingsActivity);
                return true;
            } else if (id == R.id.menu_calculator_documentation) {
                PopupDocumentation popupDocumentation = new PopupDocumentation(requireActivity(), requireContext(), PopupDocumentation.CALCULATOR_PDF);
                popupDocumentation.Show();
                return true;
            } else if (id == R.id.menu_rsa_100) {
                String rsa_100_pq = "1522605027922533360535618378132637429718068114961380688657908494580122963258952897654000350692006139";
                String rsa_100_p = "37975227936943673922808872755445627854565536638199";
                editTextCalculatorA.setText(rsa_100_pq);
                editTextCalculatorB.setText(rsa_100_p);
                // a: 37975227936943673922808872755445627854565536638199
                // b: 40094690950920881030683735292761468389214899724061
                // Result: 1522605027922533360535618378132637429718068114961380688657908494580122963258952897654000350692006139
                // Reset the last button clicked.
                ResetAllAndSelectButtonClicked(null);
                UIHelper.HideSoftKeyBoard(requireActivity());
                editTextCalculatorA.clearFocus();
                editTextCalculatorB.clearFocus();
                // Reset result
                ResultReset();
                Result2SetVisibilityToGone();
                return true;
            } else if (id == R.id.menu_rsa_576) {
                String rsa_576_pq = "188198812920607963838697239461650439807163563379417382700763356422988859715234665485319060606504743045317388011303396716199692321205734031879550656996221305168759307650257059";
                String rsa_576_p ="398075086424064937397125500550386491199064362342526708406385189575946388957261768583317";
                editTextCalculatorA.setText(rsa_576_pq);
                editTextCalculatorB.setText(rsa_576_p);
                // a: 398075086424064937397125500550386491199064362342526708406385189575946388957261768583317
                // b: 472772146107435302536223071973048224632914695302097116459852171130520711256363590397527
                // Result: 188198812920607963838697239461650439807163563379417382700763356422988859715234665485319060606504743045317388011303396716199692321205734031879550656996221305168759307650257059
                // Reset the last button clicked.
                ResetAllAndSelectButtonClicked(null);
                UIHelper.HideSoftKeyBoard(requireActivity());
                editTextCalculatorA.clearFocus();
                editTextCalculatorB.clearFocus();
                // Reset result
                ResultReset();
                Result2SetVisibilityToGone();
                return true;
            } else if (id == R.id.menu_rsa_768) {
                String rsa_768_pq = "1230186684530117755130494958384962720772853569595334792197322452151726400507263657518745202199786469389956474942774063845925192557326303453731548268507917026122142913461670429214311602221240479274737794080665351419597459856902143413";
                String rsa_768_p = "33478071698956898786044169848212690817704794983713768568912431388982883793878002287614711652531743087737814467999489";
                editTextCalculatorA.setText(rsa_768_pq);
                editTextCalculatorB.setText(rsa_768_p);
                // a: 33478071698956898786044169848212690817704794983713768568912431388982883793878002287614711652531743087737814467999489
                // b: 36746043666799590428244633799627952632279158164343087642676032283815739666511279233373417143396810270092798736308917
                // Result: 1230186684530117755130494958384962720772853569595334792197322452151726400507263657518745202199786469389956474942774063845925192557326303453731548268507917026122142913461670429214311602221240479274737794080665351419597459856902143413
                // Reset the last button clicked.
                ResetAllAndSelectButtonClicked(null);
                UIHelper.HideSoftKeyBoard(requireActivity());
                editTextCalculatorA.clearFocus();
                editTextCalculatorB.clearFocus();
                // Reset result
                ResultReset();
                Result2SetVisibilityToGone();
                return true;
            } else if (id == R.id.menu_rsa_1536) {
                String rsa_1536_pq ="1847699703211741474306835620200164403018549338663410171471785774910651696711161249859337684305435744585616061544571794052229717732524660960646946071249623720442022269756756687378427562389508764678440933285157496578843415088475528298186726451339863364931908084671990431874381283363502795470282653297802934916155811881049844908319545009848393775227257052578591944993870073695755688436933812779613089230392569695253261620823676490316036551371447913932347169566988069";
                editTextCalculatorA.setText(rsa_1536_pq);
                editTextCalculatorB.setText("");
                // a:
                // b:
                // Result: 1847699703211741474306835620200164403018549338663410171471785774910651696711161249859337684305435744585616061544571794052229717732524660960646946071249623720442022269756756687378427562389508764678440933285157496578843415088475528298186726451339863364931908084671990431874381283363502795470282653297802934916155811881049844908319545009848393775227257052578591944993870073695755688436933812779613089230392569695253261620823676490316036551371447913932347169566988069
                // Reset the last button clicked.
                ResetAllAndSelectButtonClicked(null);
                UIHelper.HideSoftKeyBoard(requireActivity());
                editTextCalculatorA.clearFocus();
                editTextCalculatorB.clearFocus();
                // Reset result
                ResultReset();
                Result2SetVisibilityToGone();
                return true;
            } else if (id == R.id.menu_rsa_2048) {
                String rsa_2048_pq = "25195908475657893494027183240048398571429282126204032027777137836043662020707595556264018525880784406918290641249515082189298559149176184502808489120072844992687392807287776735971418347270261896375014971824691165077613379859095700097330459748808428401797429100642458691817195118746121515172654632282216869987549182422433637259085141865462043576798423387184774447920739934236584823824281198163815010674810451660377306056201619676256133844143603833904414952634432190114657544454178424020924616515723350778707749817125772467962926386356373289912154831438167899885040445364023527381951378636564391212010397122822120720357";
                editTextCalculatorA.setText(rsa_2048_pq);
                editTextCalculatorB.setText("");
                // a:
                // b:
                // Result: 25195908475657893494027183240048398571429282126204032027777137836043662020707595556264018525880784406918290641249515082189298559149176184502808489120072844992687392807287776735971418347270261896375014971824691165077613379859095700097330459748808428401797429100642458691817195118746121515172654632282216869987549182422433637259085141865462043576798423387184774447920739934236584823824281198163815010674810451660377306056201619676256133844143603833904414952634432190114657544454178424020924616515723350778707749817125772467962926386356373289912154831438167899885040445364023527381951378636564391212010397122822120720357
                // Reset the last button clicked.
                ResetAllAndSelectButtonClicked(null);
                UIHelper.HideSoftKeyBoard(requireActivity());
                editTextCalculatorA.clearFocus();
                editTextCalculatorB.clearFocus();
                // Reset result
                ResultReset();
                Result2SetVisibilityToGone();
                return true;
            } else if (id == R.id.menu_more_rsa) {
                UIHelper.openWith(requireContext(), "https://en.wikipedia.org/wiki/RSA_Factoring_Challenge");
                return true;
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }

        // If the menu item was not handled by this fragment, return false
        // so that the hosting Activity or other MenuProviders can handle it.
        return false;
    }
    //endregion MENU


    @Override
    public void onResume() {
        super.onResume();
        this.refreshSmallerClipboardButtons();
        this.refreshSmallerControls();
    }


    //region Display
    private void refreshSmallerClipboardButtons() {
        try {
            boolean biggerClipboardButtons = UserSettings.GetBiggerClipboardButtons(requireContext());

            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewCalculatorCopyA, biggerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewCalculatorPasteA, biggerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewCalculatorClearA, biggerClipboardButtons);
            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewCalculatorCopyB, biggerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewCalculatorPasteB, biggerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewCalculatorClearB, biggerClipboardButtons);
            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewCalculatorCopyResult1, biggerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewCalculatorClearResult1, biggerClipboardButtons);
            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewCalculatorCopyResult2, biggerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewCalculatorClearResult2, biggerClipboardButtons);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void refreshSmallerControls() {
        try {
            boolean biggerControls = UserSettings.GetBiggerControls(requireContext());

            // Label
            ControlDisplay.SetInputLabelFontSize(textViewCalculatorLabelA, biggerControls);
            ControlDisplay.SetInputLabelFontSize(textViewCalculatorLabelElasticA, biggerControls);
            // Input
            ControlDisplay.SetInputFontSize(editTextCalculatorA, biggerControls);
            // Label
            ControlDisplay.SetInputLabelFontSize(textViewCalculatorLabelB, biggerControls);
            ControlDisplay.SetInputLabelFontSize(textViewCalculatorLabelElasticB, biggerControls);
            // Input
            ControlDisplay.SetInputFontSize(editTextCalculatorB, biggerControls);
            // Buttons
            ControlDisplay.SetButtonFontSize(buttonCalculatorAddition, biggerControls);
            ControlDisplay.SetButtonFontSize(buttonCalculatorSubtraction, biggerControls);
            ControlDisplay.SetButtonFontSize(buttonCalculatorMultiplication, biggerControls);
            ControlDisplay.SetButtonFontSize(buttonCalculatorDivision, biggerControls);
            ControlDisplay.SetButtonFontSize(buttonCalculatorPower, biggerControls);
            ControlDisplay.SetButtonFontSize(buttonCalculatorRoot, biggerControls);
            ControlDisplay.SetButtonFontSize(buttonCalculatorGcd, biggerControls);
            ControlDisplay.SetButtonFontSize(buttonCalculatorLcm, biggerControls);
            ControlDisplay.SetButtonFontSize(buttonCalculatorMod, biggerControls);
            ControlDisplay.SetButtonFontSize(buttonCalculatorModInverse, biggerControls);
            ControlDisplay.SetButtonFontSize(buttonCalculatorIsProbablePrime, biggerControls);
            ControlDisplay.SetButtonFontSize(buttonCalculatorEulerPhi, biggerControls);
            ControlDisplay.SetButtonFontSize(buttonCalculatorFactorial, biggerControls);
            ControlDisplay.SetButtonFontSize(buttonCalculatorNextProbablePrime, biggerControls);
            ControlDisplay.SetButtonFontSize(buttonCalculatorNextProbableTwinPrime, biggerControls);
            // Label
            ControlDisplay.SetInputLabelFontSize(textViewCalculatorLabelResult1, biggerControls);
            ControlDisplay.SetInputLabelFontSize(textViewCalculatorLabelElasticResult1, biggerControls);
            // Output
            ControlDisplay.SetOutputFontSize(editTextCalculatorResult1, biggerControls);
            // Label
            ControlDisplay.SetInputLabelFontSize(textViewCalculatorLabelResult2, biggerControls);
            ControlDisplay.SetInputLabelFontSize(textViewCalculatorLabelElasticResult2, biggerControls);
            // Output
            ControlDisplay.SetOutputFontSize(editTextCalculatorResult2, biggerControls);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    //endregion Display


    //region Callback
    @Override
    public void callbackResult(AlgorithmName algorithmName, Object result, ProgressStatus progressStatus) {
        // This is to prevent the error: Non-fatal Exception: java.lang.IllegalStateException: Fragment FragmentPrimesList{94d7331} (36e8cdd6-9d00-4c2a-bd07-ab5550e2c88b) not attached to a context.
        // java.lang.IllegalStateException: Fragment not attached to Activity -> https://stackoverflow.com/questions/28672883/java-lang-illegalstateexception-fragment-not-attached-to-activity
        Activity activity = getActivity();
        if (activity == null || !this.isAdded()) {
            return;
        }
        // Label
        String labelResultText = requireContext().getResources().getString(R.string.result);
        String labelResult1 = labelResultText;
        String labelResult2 = labelResultText;
        // Result
        String result1 = null;
        String result2 = null;
        //
        switch (algorithmName) {
            case CALCULATOR_ADDITION:
                labelResult1 = requireContext().getResources().getString(R.string.calculator_addition_label_result1) + UIHelper.GetNrOfDigits((String)result);
                result1 = (String)result;
                break;
            case CALCULATOR_SUBTRACTION:
                labelResult1 = requireContext().getResources().getString(R.string.calculator_subtraction_label_result1) + UIHelper.GetNrOfDigits((String)result);
                result1 = (String)result;
                break;
            case CALCULATOR_MULTIPLICATION :
                labelResult1 = requireContext().getResources().getString(R.string.calculator_multiplication_label_result1) + UIHelper.GetNrOfDigits((String)result);
                result1 = (String)result;
                break;
            case CALCULATOR_DIVISION :
                String[] quotient_remainder = new String[] {null, null};
                if (result != null && !(((String)result).isEmpty())) {
                    quotient_remainder = ((String)result).split("_"); // quotient_remainder
                }
                // Result 1
                labelResult1 = requireContext().getResources().getString(R.string.calculator_division_label_result1) + UIHelper.GetNrOfDigits(quotient_remainder[0]);
                result1 = quotient_remainder[0];
                // Result 2
                labelResult2 = requireContext().getResources().getString(R.string.calculator_division_label_result2) + UIHelper.GetNrOfDigits(quotient_remainder[1]);
                result2 = quotient_remainder[1];
                break;
            case CALCULATOR_POWER :
                labelResult1 = requireContext().getResources().getString(R.string.calculator_power_label_result1) + UIHelper.GetNrOfDigits((String)result);
                result1 = (String)result;
                break;
            case CALCULATOR_ROOT :
                String[] root_remainder = new String[] {null, null};
                if (result != null && !(((String)result).isEmpty())) {
                    root_remainder = ((String)result).split("_"); // root_remainder
                }
                // Result 1
                labelResult1 = requireContext().getResources().getString(R.string.calculator_root_label_result1) + UIHelper.GetNrOfDigits(root_remainder[0]);
                result1 = root_remainder[0];
                // Result 2
                labelResult2 = requireContext().getResources().getString(R.string.calculator_root_label_result2) + UIHelper.GetNrOfDigits(root_remainder[1]);
                result2 = root_remainder[1];
                break;
            case CALCULATOR_GCD :
                labelResult1 = requireContext().getResources().getString(R.string.calculator_gcd_label_result1) + UIHelper.GetNrOfDigits((String)result);
                result1 = (String)result;
                break;
            case CALCULATOR_LCM :
                labelResult1 = requireContext().getResources().getString(R.string.calculator_lcm_label_result1) + UIHelper.GetNrOfDigits((String)result);
                result1 = (String)result;
                break;
            case CALCULATOR_MOD :
                labelResult1 = requireContext().getResources().getString(R.string.calculator_mod_label_result1) + UIHelper.GetNrOfDigits((String)result);
                result1 = (String)result;
                break;
            case CALCULATOR_MOD_INVERSE :
                labelResult1 = requireContext().getResources().getString(R.string.calculator_mod_inverse_label_result1) + UIHelper.GetNrOfDigits((String)result);
                result1 = (String)result;
                break;
            case CALCULATOR_IS_PROBABLE_PRIME :
                labelResult1 = requireContext().getResources().getString(R.string.calculator_is_a_prime);
                if(result.equals("1")) {
                    result = requireContext().getResources().getString(R.string.calculator_a_is_prime_yes);
                } else {
                    result = requireContext().getResources().getString(R.string.calculator_a_is_prime_no);
                }
                result1 = (String)result;
                break;
            case CALCULATOR_EULER_PHI :
                labelResult1 = requireContext().getResources().getString(R.string.calculator_euler_phi_of_a) + UIHelper.GetNrOfDigits((String)result);
                result1 = (String)result;
                break;
            case CALCULATOR_FACTORIAL :
                labelResult1 = requireContext().getResources().getString(R.string.calculator_a_factorial) + UIHelper.GetNrOfDigits((String)result);
                result1 = (String)result;
                break;
            case CALCULATOR_NEXT_PROBABLE_PRIME:
                labelResult1 = requireContext().getResources().getString(R.string.calculator_next_prime_label_result1) + UIHelper.GetNrOfDigits((String)result);
                result1 = (String)result;
                break;
            case CALCULATOR_NEXT_PROBABLE_TWIN_PRIME_PAIR :
                String[] twin_prime_pair = new String[] {null, null};
                if (result != null && !(((String)result).isEmpty())) {
                    twin_prime_pair = ((String)result).split("_"); // prime1_prime2
                }
                // Result 1
                labelResult1 = requireContext().getResources().getString(R.string.calculator_next_twin_prime_label_result1) + UIHelper.GetNrOfDigits((String)twin_prime_pair[0]);
                result1 = twin_prime_pair[0];
                // Result 2
                labelResult2 = requireContext().getResources().getString(R.string.calculator_next_twin_prime_label_result2) + UIHelper.GetNrOfDigits((String)twin_prime_pair[1]);
                result2 = twin_prime_pair[1];
                break;
            default:
                break;
        }
        // Check if canceled
        if (progressStatus == ProgressStatus.CANCELED) {
            String resultCanceledText = requireContext().getResources().getString(R.string.canceled);
            result1 = resultCanceledText;
            result2 = resultCanceledText;
        }
        // Label
        textViewCalculatorLabelResult1.setText(labelResult1);
        textViewCalculatorLabelResult2.setText(labelResult2);
        // Result
        editTextCalculatorResult1.setText(result1);
        editTextCalculatorResult2.setText(result2);
    }
    //endregion Callback


    //region BUTTON ACTIONS
    private void OnButtonAddition(ViewGroup container, boolean displayProgressDialog) {
        try  {
            // Check.
            if(UIHelper.checkInputMustBeNumber(requireContext(), editTextCalculatorA, textViewCalculatorLabelA, textViewCalculatorLabelElasticA, "a")) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), editTextCalculatorB, textViewCalculatorLabelB, textViewCalculatorLabelElasticB, "b")) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextCalculatorA.getText().toString());
            BigInteger b = new BigInteger(editTextCalculatorB.getText().toString());

            // Reset result
            ResultReset();
            Result2SetVisibilityToGone();

            // Before action performing.
            BeforeActionPerforming(buttonCalculatorAddition);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_ADDITION, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            progressManager.startWork(container, algorithmParameters, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void OnButtonSubtraction(ViewGroup container, boolean displayProgressDialog) {
        try {
            // Check.
            if(UIHelper.checkInputMustBeNumber(requireContext(), editTextCalculatorA, textViewCalculatorLabelA, textViewCalculatorLabelElasticA, "a")) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), editTextCalculatorB, textViewCalculatorLabelB, textViewCalculatorLabelElasticB, "b")) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextCalculatorA.getText().toString());
            BigInteger b = new BigInteger(editTextCalculatorB.getText().toString());

            // Reset result
            ResultReset();
            Result2SetVisibilityToGone();

            // Before action performing.
            BeforeActionPerforming(buttonCalculatorSubtraction);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_SUBTRACTION, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            progressManager.startWork(container, algorithmParameters, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void OnButtonMultiplication(ViewGroup container, boolean displayProgressDialog) {
        try {
            // Check.
            if(UIHelper.checkInputMustBeNumber(requireContext(), editTextCalculatorA, textViewCalculatorLabelA, textViewCalculatorLabelElasticA, "a")) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), editTextCalculatorB, textViewCalculatorLabelB, textViewCalculatorLabelElasticB, "b")) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextCalculatorA.getText().toString());
            BigInteger b = new BigInteger(editTextCalculatorB.getText().toString());

            // Reset result
            ResultReset();
            Result2SetVisibilityToGone();

            // Before action performing.
            BeforeActionPerforming(buttonCalculatorMultiplication);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_MULTIPLICATION, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            progressManager.startWork(container, algorithmParameters, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void OnButtonDivision(ViewGroup container, boolean displayProgressDialog) {
        try {
            // Check.
            if(UIHelper.checkInputMustBeNumber(requireContext(), editTextCalculatorA, textViewCalculatorLabelA, textViewCalculatorLabelElasticA, "a")) {
                return;
            }
            if(UIHelper.checkInputMustBeOtherThanZero(requireContext(), editTextCalculatorB, textViewCalculatorLabelB, textViewCalculatorLabelElasticB, "b")) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextCalculatorA.getText().toString());
            BigInteger b = new BigInteger(editTextCalculatorB.getText().toString());

            // Reset result
            ResultReset();
            Result2SetVisibilityToVisible();

            // Before action performing.
            BeforeActionPerforming(buttonCalculatorDivision);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_DIVISION, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            progressManager.startWork(container, algorithmParameters, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void OnButtonPower(ViewGroup container, boolean displayProgressDialog) {
        try {
            // Check.
            if(UIHelper.checkInputMustBeNumber(requireContext(), editTextCalculatorA, textViewCalculatorLabelA, textViewCalculatorLabelElasticA, "a")) {
                return;
            }
            if(UIHelper.checkInputMustBeBetweenMinMaxInclusive(requireContext(), editTextCalculatorB, textViewCalculatorLabelB, textViewCalculatorLabelElasticB, "b", ZERO, INTEGER_MAX_VALUE)) {
                return;
            }

            // Get numbers
            final BigInteger a = new BigInteger(editTextCalculatorA.getText().toString());
            final BigInteger b = new BigInteger(editTextCalculatorB.getText().toString());

            // Reset result
            ResultReset();
            Result2SetVisibilityToGone();

            // Before action performing.
            BeforeActionPerforming(buttonCalculatorPower);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_POWER, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            progressManager.startWork(container, algorithmParameters, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }



    private void OnButtonRoot(ViewGroup container, boolean displayProgressDialog) {
        try {
            // Check.
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), editTextCalculatorA, textViewCalculatorLabelA, textViewCalculatorLabelElasticA, "a", ZERO)) {
                return;
            }
            if(UIHelper.checkInputMustBeBetweenMinMaxInclusive(requireContext(), editTextCalculatorB, textViewCalculatorLabelB, textViewCalculatorLabelElasticB, "b", ONE, INTEGER_MAX_VALUE)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextCalculatorA.getText().toString());
            BigInteger b = new BigInteger(editTextCalculatorB.getText().toString());

            // Reset result
            ResultReset();
            Result2SetVisibilityToVisible();

            // Before action performing.
            BeforeActionPerforming(buttonCalculatorRoot);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_ROOT, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            progressManager.startWork(container, algorithmParameters, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void OnButtonGcd(ViewGroup container, boolean displayProgressDialog) {
        try {
            // Check.
            if(UIHelper.checkInputMustBeNumber(requireContext(), editTextCalculatorA, textViewCalculatorLabelA, textViewCalculatorLabelElasticA, "a")) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), editTextCalculatorB, textViewCalculatorLabelB, textViewCalculatorLabelElasticB, "b")) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextCalculatorA.getText().toString());
            BigInteger b = new BigInteger(editTextCalculatorB.getText().toString());

            // Reset result
            ResultReset();
            Result2SetVisibilityToGone();

            // Before action performing.
            BeforeActionPerforming(buttonCalculatorGcd);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_GCD, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            progressManager.startWork(container, algorithmParameters, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void OnButtonLcm(ViewGroup container, boolean displayProgressDialog) {
        try {
            // Check.
            if(UIHelper.checkInputMustBeNumber(requireContext(), editTextCalculatorA, textViewCalculatorLabelA, textViewCalculatorLabelElasticA, "a")) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), editTextCalculatorB, textViewCalculatorLabelB, textViewCalculatorLabelElasticB, "b")) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextCalculatorA.getText().toString());
            BigInteger b = new BigInteger(editTextCalculatorB.getText().toString());

            if (a.compareTo(ZERO) == 0 && b.compareTo(ZERO) == 0) {
                UIHelper.CustomToastLight(requireContext(), "Not both a and b can be zero!");
                return;
            }

            // Reset result
            ResultReset();
            Result2SetVisibilityToGone();

            // Before action performing.
            BeforeActionPerforming(buttonCalculatorLcm);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_LCM, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            progressManager.startWork(container, algorithmParameters, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void OnButtonMod(ViewGroup container, boolean displayProgressDialog) {
        try {
            // Check.
            if(UIHelper.checkInputMustBeNumber(requireContext(), editTextCalculatorA, textViewCalculatorLabelA, textViewCalculatorLabelElasticA, "a")) {
                return;
            }
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), editTextCalculatorB, textViewCalculatorLabelB, textViewCalculatorLabelElasticB, "b", ONE)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextCalculatorA.getText().toString());
            BigInteger b = new BigInteger(editTextCalculatorB.getText().toString());

            // Reset result
            ResultReset();
            Result2SetVisibilityToGone();

            // Before action performing.
            BeforeActionPerforming(buttonCalculatorMod);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_MOD, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            progressManager.startWork(container, algorithmParameters, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void OnButtonModInverse(ViewGroup container, boolean displayProgressDialog) {
        try {
            // Check.
            if(UIHelper.checkInputMustBeNumber(requireContext(), editTextCalculatorA, textViewCalculatorLabelA, textViewCalculatorLabelElasticA, "a")) {
                return;
            }
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), editTextCalculatorB, textViewCalculatorLabelB, textViewCalculatorLabelElasticB, "b", ONE)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextCalculatorA.getText().toString());
            BigInteger b = new BigInteger(editTextCalculatorB.getText().toString());

            // Reset result
            ResultReset();
            Result2SetVisibilityToGone();

            // Before action performing.
            BeforeActionPerforming(buttonCalculatorModInverse);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_MOD_INVERSE, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            progressManager.startWork(container, algorithmParameters, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void OnButtonIsProbablePrime(ViewGroup container, boolean displayProgressDialog) {
        try {
            // Check.
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), editTextCalculatorA, textViewCalculatorLabelA, textViewCalculatorLabelElasticA, "a", TWO)) {
                return;
            }
            if (UIHelper.checkInputMustBeFilled(requireContext(), editTextCalculatorB, textViewCalculatorLabelB, textViewCalculatorLabelElasticB, textViewCalculatorLabelB.getText().toString(), "Must specify the certainty in input <b>%s</b>")) {
                return;
            }
            if(UIHelper.checkInputMustBeBetweenMinMaxInclusive(requireContext(), editTextCalculatorB, textViewCalculatorLabelB, textViewCalculatorLabelElasticB, "b", ONE, INTEGER_MAX_VALUE)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextCalculatorA.getText().toString());
            BigInteger b = new BigInteger(editTextCalculatorB.getText().toString());

            // Reset result
            ResultReset();
            Result2SetVisibilityToGone();

            // Before action performing.
            BeforeActionPerforming(buttonCalculatorIsProbablePrime);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_IS_PROBABLE_PRIME, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            progressManager.startWork(container, algorithmParameters, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void OnButtonEulerPhi(ViewGroup container, boolean displayProgressDialog) {
        try {
            // Check.
            if (UIHelper.checkInputMustBeGreaterThanMin(requireContext(), editTextCalculatorA, textViewCalculatorLabelA, textViewCalculatorLabelElasticA, "a", ZERO)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextCalculatorA.getText().toString());

            // Reset result
            ResultReset();
            Result2SetVisibilityToGone();

            // Before action performing.
            BeforeActionPerforming(buttonCalculatorEulerPhi);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_EULER_PHI, this);
            algorithmParameters.setInput1(a);
            progressManager.startWork(container, algorithmParameters, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void OnButtonFactorial(ViewGroup container, boolean displayProgressDialog) {
        try {
            // Check.
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), editTextCalculatorA, textViewCalculatorLabelA, textViewCalculatorLabelElasticA, "a", ZERO)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextCalculatorA.getText().toString());

            // Reset result
            ResultReset();
            Result2SetVisibilityToGone();

            // Before action performing.
            BeforeActionPerforming(buttonCalculatorFactorial);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_FACTORIAL, this);
            algorithmParameters.setInput1(a);
            progressManager.startWork(container, algorithmParameters, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void OnButtonNextProbablePrime(ViewGroup container, boolean displayProgressDialog) {
        try {
            // Check.
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), editTextCalculatorA, textViewCalculatorLabelA, textViewCalculatorLabelElasticA, "a", TWO)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextCalculatorA.getText().toString());

            // Reset result
            ResultReset();
            Result2SetVisibilityToGone();

            // Before action performing.
            BeforeActionPerforming(buttonCalculatorNextProbablePrime);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_NEXT_PROBABLE_PRIME, this);
            algorithmParameters.setInput1(a);
            progressManager.startWork(container, algorithmParameters, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void OnButtonNextProbableTwinPrime(ViewGroup container, boolean displayProgressDialog) {
        try {
            // Make the second result gone.
            Result2SetVisibilityToGone();

            // (3, 5), (5, 7), (11, 13), (17, 19), (29, 31), (41, 43), (59, 61), (71, 73), (101, 103), (107, 109), (137, 139), ...

            // Check.
            if (UIHelper.checkInputMustBeNumber(requireContext(), editTextCalculatorA, textViewCalculatorLabelA, textViewCalculatorLabelElasticA, "a")) {
                return;
            }

            // Get the input numbers.
            BigInteger a = new BigInteger(editTextCalculatorA.getText().toString());

            // Reset result
            ResultReset();
            // Make the second result visible.
            Result2SetVisibilityToVisible();

            // Before action performing.
            BeforeActionPerforming(buttonCalculatorNextProbableTwinPrime);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_NEXT_PROBABLE_TWIN_PRIME_PAIR, this);
            algorithmParameters.setInput1(a);
            progressManager.startWork(container, algorithmParameters, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    //endregion Button actions


    //region RESULT
    private void BeforeActionPerforming(Button button) {
        // Hide the keyboard.
        UIHelper.HideSoftKeyBoard(requireActivity());
        // Clear the focus.
        editTextCalculatorA.clearFocus();
        editTextCalculatorB.clearFocus();
        // Select the last button clicked.
        ResetAllAndSelectButtonClicked(button);
    }
    private void ResetAllAndSelectClipboardButtonClicked(TextView textView) {
        // Reset the last clipboard clicked.
        textViewCalculatorCopyA.setSelected(false);
        textViewCalculatorPasteA.setSelected(false);
        textViewCalculatorClearA.setSelected(false);
        textViewCalculatorCopyB.setSelected(false);
        textViewCalculatorPasteB.setSelected(false);
        textViewCalculatorClearB.setSelected(false);
        textViewCalculatorCopyResult1.setSelected(false);
        textViewCalculatorClearResult1.setSelected(false);
        textViewCalculatorCopyResult2.setSelected(false);
        textViewCalculatorClearResult2.setSelected(false);
        // Select he last clipboard clicked.
        if (textView != null) {
            textView.setSelected(true);
        }
    }
    private void ResetAllAndSelectButtonClicked(Button button) {
        // Reset the last button clicked.
        buttonCalculatorAddition.setSelected(false);
        buttonCalculatorSubtraction.setSelected(false);
        buttonCalculatorMultiplication.setSelected(false);
        buttonCalculatorDivision.setSelected(false);
        buttonCalculatorPower.setSelected(false);
        buttonCalculatorRoot.setSelected(false);
        buttonCalculatorGcd.setSelected(false);
        buttonCalculatorLcm.setSelected(false);
        buttonCalculatorMod.setSelected(false);
        buttonCalculatorModInverse.setSelected(false);
        buttonCalculatorIsProbablePrime.setSelected(false);
        buttonCalculatorEulerPhi.setSelected(false);
        buttonCalculatorFactorial.setSelected(false);
        buttonCalculatorNextProbablePrime.setSelected(false);
        buttonCalculatorNextProbableTwinPrime.setSelected(false);
        // Select he last button clicked.
        if (button != null) {
            button.setSelected(true);
        }
    }
    private void ResultReset() {
        // Reset the last clipboard clicked.
        ResetAllAndSelectClipboardButtonClicked(null);
        // Reset the last button clicked.
        ResetAllAndSelectButtonClicked(null);
        // Reset the Result1
        Result1Reset();
        // Reset the Result2
        Result2Reset();
    }
    private void Result1Reset() {
        // Reset the Result1
        textViewCalculatorLabelResult1.setText(R.string.result);
        editTextCalculatorResult1.setText("");
    }
    private void Result2Reset() {
        // Reset the Result2
        textViewCalculatorLabelResult2.setText(R.string.result);
        editTextCalculatorResult2.setText("");
    }
    private void Result2SetVisibilityToGone() {
        linearLayoutCalculatorResult2Labels.setVisibility(View.GONE);
        linearLayoutCalculatorResult2.setVisibility(View.GONE);
    }
    private void Result2SetVisibilityToVisible() {
        linearLayoutCalculatorResult2Labels.setVisibility(View.VISIBLE);
        linearLayoutCalculatorResult2.setVisibility(View.VISIBLE);
    }
    //endregion RESULT

}