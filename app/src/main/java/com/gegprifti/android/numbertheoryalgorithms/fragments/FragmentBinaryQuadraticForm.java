package com.gegprifti.android.numbertheoryalgorithms.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.InputGroup;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.UIHelper;
import com.gegprifti.android.numbertheoryalgorithms.grid.CellUI;
import com.gegprifti.android.numbertheoryalgorithms.grid.Grid;
import com.gegprifti.android.numbertheoryalgorithms.grid.GridAdapter;
import com.gegprifti.android.numbertheoryalgorithms.progress.ProgressStatus;
import com.gegprifti.android.numbertheoryalgorithms.R;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmName;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.settings.ControlDisplay;
import com.gegprifti.android.numbertheoryalgorithms.popups.PopupResult;
import com.gegprifti.android.numbertheoryalgorithms.grid.Cell;
import com.gegprifti.android.numbertheoryalgorithms.settings.UserSettings;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.FragmentBase;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.Callback;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public class FragmentBinaryQuadraticForm extends FragmentBase implements Callback {
    private final static String TAG = FragmentBinaryQuadraticForm.class.getSimpleName();
    // Navigation controls
    TextView textViewBackToAlgorithms;
    TextView textViewTitle;
    TextView textViewInputToggle;
    // Cache view state
    boolean isCompactInputView = false;
    // All inputs
    LinearLayout linearLayoutInputInputView;
    // Expanded input view
    LinearLayout linearLayoutExpandedInputView;
    LinearLayout linearLayoutExpandedInputA;
    TextView textViewLabelA;
    TextView textViewLabelElasticA;
    TextView textViewMinusA;
    TextView textViewPlusA;
    TextView textViewCopyA;
    TextView textViewPasteA;
    TextView textViewClearA;
    EditText editTextA;
    LinearLayout linearLayoutExpandedInputB;
    TextView textViewLabelB;
    TextView textViewLabelElasticB;
    TextView textViewMinusB;
    TextView textViewPlusB;
    TextView textViewCopyB;
    TextView textViewPasteB;
    TextView textViewClearB;
    EditText editTextB;
    LinearLayout linearLayoutExpandedInputC;
    TextView textViewLabelC;
    TextView textViewLabelElasticC;
    TextView textViewMinusC;
    TextView textViewPlusC;
    TextView textViewCopyC;
    TextView textViewPasteC;
    TextView textViewClearC;
    EditText editTextC;
    LinearLayout linearLayoutExpandedInputD;
    TextView textViewLabelD;
    TextView textViewLabelElasticD;
    TextView textViewMinusD;
    TextView textViewPlusD;
    TextView textViewCopyD;
    TextView textViewPasteD;
    TextView textViewClearD;
    EditText editTextD;
    LinearLayout linearLayoutExpandedInputE;
    TextView textViewLabelE;
    TextView textViewLabelElasticE;
    TextView textViewMinusE;
    TextView textViewPlusE;
    TextView textViewCopyE;
    TextView textViewPasteE;
    TextView textViewClearE;
    EditText editTextE;
    LinearLayout linearLayoutExpandedInputF;
    TextView textViewLabelF;
    TextView textViewLabelElasticF;
    TextView textViewMinusF;
    TextView textViewPlusF;
    TextView textViewCopyF;
    TextView textViewPasteF;
    TextView textViewClearF;
    EditText editTextF;
    // Compact input view
    LinearLayout linearLayoutCompactInputView;
    LinearLayout linearLayoutCompactInputA;
    TextView textViewLabelCompactA;
    EditText editTextCompactA;
    TextView textViewMinusCompactA;
    TextView textViewPlusCompactA;
    TextView textViewCopyCompactA;
    TextView textViewPasteCompactA;
    TextView textViewClearCompactA;
    LinearLayout linearLayoutCompactInputB;
    TextView textViewLabelCompactB;
    EditText editTextCompactB;
    TextView textViewMinusCompactB;
    TextView textViewPlusCompactB;
    TextView textViewCopyCompactB;
    TextView textViewPasteCompactB;
    TextView textViewClearCompactB;
    LinearLayout linearLayoutCompactInputC;
    TextView textViewLabelCompactC;
    EditText editTextCompactC;
    TextView textViewMinusCompactC;
    TextView textViewPlusCompactC;
    TextView textViewCopyCompactC;
    TextView textViewPasteCompactC;
    TextView textViewClearCompactC;
    LinearLayout linearLayoutCompactInputD;
    TextView textViewLabelCompactD;
    EditText editTextCompactD;
    TextView textViewMinusCompactD;
    TextView textViewPlusCompactD;
    TextView textViewCopyCompactD;
    TextView textViewPasteCompactD;
    TextView textViewClearCompactD;
    LinearLayout linearLayoutCompactInputE;
    TextView textViewLabelCompactE;
    EditText editTextCompactE;
    TextView textViewMinusCompactE;
    TextView textViewPlusCompactE;
    TextView textViewCopyCompactE;
    TextView textViewPasteCompactE;
    TextView textViewClearCompactE;
    LinearLayout linearLayoutCompactInputF;
    TextView textViewLabelCompactF;
    EditText editTextCompactF;
    TextView textViewMinusCompactF;
    TextView textViewPlusCompactF;
    TextView textViewCopyCompactF;
    TextView textViewPasteCompactF;
    TextView textViewClearCompactF;
    // Run buttons
    Button buttonRunExampleToggle;
    Button buttonRun;
    Button buttonRun1;
    Button buttonRun2;
    LinearLayout linearLayoutFModMContainer;
    Button buttonMMinus;
    Button buttonM;
    Button buttonMPlus;
    Button buttonRMinus;
    Button buttonR;
    Button buttonRPlus;
    // Result controls
    TextView textViewLabelResult;
    TextView textViewLabelElasticResult;
    TextView textViewExpandResult;
    TextView textViewCopyResult;
    TextView textViewClearResult;
    LinearLayout linearLayoutResultContainer;
    EditText editTextResult;
    LinearLayout linearLayoutResultGridContainer1;
    LinearLayout linearLayoutStaticColumnHeader1;
    ListView listViewResult1;
    LinearLayout linearLayoutResultGridContainer2;
    LinearLayout linearLayoutStaticRowHeader2;
    LinearLayout linearLayoutStaticColumnHeaderOrigin;
    LinearLayout linearLayoutStaticColumnHeader2;
    ListView listViewResult2;
    ListView listViewRowHeaderResult2;
    // Menu
    MenuItem menuItemIncludeTrivialSolutions;
    MenuItem menuItemIncludeOnlyPositiveSolutions;
    MenuItem menuItemIncludeOnlyNegativeSolutions;
    // Flags to prevent recursive updates
    AtomicBoolean isUpdatingEditTextA = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextCompactA = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextB = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextCompactB = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextC = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextCompactC = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextD = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextCompactD = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextE = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextCompactE = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextF = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextCompactF = new AtomicBoolean(false);

    private enum LastRun {
        RUN,
        RUN1,
        RUN2
    }
    private LastRun lastRun = LastRun.RUN;

    // Define the parent fragment
    private TabFragmentAlgorithms tabFragmentAlgorithms;
    //public TabFragmentAlgorithms getFragmentTabAlgorithms() { return tabFragmentAlgorithms; }
    public void setFragmentTabAlgorithms(TabFragmentAlgorithms tabFragmentAlgorithms) {
        this.tabFragmentAlgorithms = tabFragmentAlgorithms;
    }

    // Important
    // All Fragment classes you create must have a public, no-arg constructor.
    // In general, the best practice is to simply never define any constructors at all and rely on Java to generate the default constructor for you.


    //region CREATE
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View inflater = null;
        try {
            inflater = layoutInflater.inflate(R.layout.fragment_binary_quadratic_form, container, false);
            // Navigation controls
            this.textViewBackToAlgorithms = inflater.findViewById(R.id.TextViewBackToAlgorithms);
            this.textViewTitle = inflater.findViewById(R.id.TextViewTitle);
            this.textViewInputToggle = inflater.findViewById(R.id.TextViewInputToggle);
            // All inputs
            linearLayoutInputInputView = inflater.findViewById(R.id.LinearLayoutInputInputView);
            // Expanded input view
            linearLayoutExpandedInputView = inflater.findViewById(R.id.LinearLayoutExpandedInputView);
            linearLayoutExpandedInputA = inflater.findViewById(R.id.LinearLayoutExpandedInputA);
            textViewLabelA = inflater.findViewById(R.id.TextViewLabelA);
            textViewLabelElasticA = inflater.findViewById(R.id.TextViewLabelElasticA);
            textViewMinusA = inflater.findViewById(R.id.TextViewMinusA);
            textViewPlusA = inflater.findViewById(R.id.TextViewPlusA);
            textViewCopyA = inflater.findViewById(R.id.TextViewCopyA);
            textViewPasteA = inflater.findViewById(R.id.TextViewPasteA);
            textViewClearA = inflater.findViewById(R.id.TextViewClearA);
            editTextA = inflater.findViewById(R.id.EditTextA);
            linearLayoutExpandedInputB = inflater.findViewById(R.id.LinearLayoutExpandedInputB);
            textViewLabelB = inflater.findViewById(R.id.TextViewLabelB);
            textViewLabelElasticB = inflater.findViewById(R.id.TextViewLabelElasticB);
            textViewMinusB = inflater.findViewById(R.id.TextViewMinusB);
            textViewPlusB = inflater.findViewById(R.id.TextViewPlusB);
            textViewCopyB = inflater.findViewById(R.id.TextViewCopyB);
            textViewPasteB = inflater.findViewById(R.id.TextViewPasteB);
            textViewClearB = inflater.findViewById(R.id.TextViewClearB);
            editTextB = inflater.findViewById(R.id.EditTextB);
            linearLayoutExpandedInputC = inflater.findViewById(R.id.LinearLayoutExpandedInputC);
            textViewLabelC = inflater.findViewById(R.id.TextViewLabelC);
            textViewLabelElasticC = inflater.findViewById(R.id.TextViewLabelElasticC);
            textViewMinusC = inflater.findViewById(R.id.TextViewMinusC);
            textViewPlusC = inflater.findViewById(R.id.TextViewPlusC);
            textViewCopyC = inflater.findViewById(R.id.TextViewCopyC);
            textViewPasteC = inflater.findViewById(R.id.TextViewPasteC);
            textViewClearC = inflater.findViewById(R.id.TextViewClearC);
            editTextC = inflater.findViewById(R.id.EditTextC);
            linearLayoutExpandedInputD = inflater.findViewById(R.id.LinearLayoutExpandedInputD);
            textViewLabelD = inflater.findViewById(R.id.TextViewLabelD);
            textViewLabelElasticD = inflater.findViewById(R.id.TextViewLabelElasticD);
            textViewMinusD = inflater.findViewById(R.id.TextViewMinusD);
            textViewPlusD = inflater.findViewById(R.id.TextViewPlusD);
            textViewCopyD = inflater.findViewById(R.id.TextViewCopyD);
            textViewPasteD = inflater.findViewById(R.id.TextViewPasteD);
            textViewClearD = inflater.findViewById(R.id.TextViewClearD);
            editTextD = inflater.findViewById(R.id.EditTextD);
            linearLayoutExpandedInputE = inflater.findViewById(R.id.LinearLayoutExpandedInputE);
            textViewLabelE = inflater.findViewById(R.id.TextViewLabelE);
            textViewLabelElasticE = inflater.findViewById(R.id.TextViewLabelElasticE);
            textViewMinusE = inflater.findViewById(R.id.TextViewMinusE);
            textViewPlusE = inflater.findViewById(R.id.TextViewPlusE);
            textViewCopyE = inflater.findViewById(R.id.TextViewCopyE);
            textViewPasteE = inflater.findViewById(R.id.TextViewPasteE);
            textViewClearE = inflater.findViewById(R.id.TextViewClearE);
            editTextE = inflater.findViewById(R.id.EditTextE);
            linearLayoutExpandedInputF = inflater.findViewById(R.id.LinearLayoutExpandedInputF);
            textViewLabelF = inflater.findViewById(R.id.TextViewLabelF);
            textViewLabelElasticF = inflater.findViewById(R.id.TextViewLabelElasticF);
            textViewMinusF = inflater.findViewById(R.id.TextViewMinusF);
            textViewPlusF = inflater.findViewById(R.id.TextViewPlusF);
            textViewCopyF = inflater.findViewById(R.id.TextViewCopyF);
            textViewPasteF = inflater.findViewById(R.id.TextViewPasteF);
            textViewClearF = inflater.findViewById(R.id.TextViewClearF);
            editTextF = inflater.findViewById(R.id.EditTextF);
            // Compact input view
            linearLayoutCompactInputView = inflater.findViewById(R.id.LinearLayoutCompactInputView);
            linearLayoutCompactInputA = inflater.findViewById(R.id.LinearLayoutCompactInputA);
            textViewLabelCompactA = inflater.findViewById(R.id.TextViewLabelCompactA);
            editTextCompactA = inflater.findViewById(R.id.EditTextCompactA);
            textViewMinusCompactA = inflater.findViewById(R.id.TextViewMinusCompactA);
            textViewPlusCompactA = inflater.findViewById(R.id.TextViewPlusCompactA);
            textViewCopyCompactA = inflater.findViewById(R.id.TextViewCopyCompactA);
            textViewPasteCompactA = inflater.findViewById(R.id.TextViewPasteCompactA);
            textViewClearCompactA = inflater.findViewById(R.id.TextViewClearCompactA);
            linearLayoutCompactInputB = inflater.findViewById(R.id.LinearLayoutCompactInputB);
            textViewLabelCompactB = inflater.findViewById(R.id.TextViewLabelCompactB);
            editTextCompactB = inflater.findViewById(R.id.EditTextCompactB);
            textViewMinusCompactB = inflater.findViewById(R.id.TextViewMinusCompactB);
            textViewPlusCompactB = inflater.findViewById(R.id.TextViewPlusCompactB);
            textViewCopyCompactB = inflater.findViewById(R.id.TextViewCopyCompactB);
            textViewPasteCompactB = inflater.findViewById(R.id.TextViewPasteCompactB);
            textViewClearCompactB = inflater.findViewById(R.id.TextViewClearCompactB);
            linearLayoutCompactInputC = inflater.findViewById(R.id.LinearLayoutCompactInputC);
            textViewLabelCompactC = inflater.findViewById(R.id.TextViewLabelCompactC);
            editTextCompactC = inflater.findViewById(R.id.EditTextCompactC);
            textViewMinusCompactC = inflater.findViewById(R.id.TextViewMinusCompactC);
            textViewPlusCompactC = inflater.findViewById(R.id.TextViewPlusCompactC);
            textViewCopyCompactC = inflater.findViewById(R.id.TextViewCopyCompactC);
            textViewPasteCompactC = inflater.findViewById(R.id.TextViewPasteCompactC);
            textViewClearCompactC = inflater.findViewById(R.id.TextViewClearCompactC);
            linearLayoutCompactInputD = inflater.findViewById(R.id.LinearLayoutCompactInputD);
            textViewLabelCompactD = inflater.findViewById(R.id.TextViewLabelCompactD);
            editTextCompactD = inflater.findViewById(R.id.EditTextCompactD);
            textViewMinusCompactD = inflater.findViewById(R.id.TextViewMinusCompactD);
            textViewPlusCompactD = inflater.findViewById(R.id.TextViewPlusCompactD);
            textViewCopyCompactD = inflater.findViewById(R.id.TextViewCopyCompactD);
            textViewPasteCompactD = inflater.findViewById(R.id.TextViewPasteCompactD);
            textViewClearCompactD = inflater.findViewById(R.id.TextViewClearCompactD);
            linearLayoutCompactInputE = inflater.findViewById(R.id.LinearLayoutCompactInputE);
            textViewLabelCompactE = inflater.findViewById(R.id.TextViewLabelCompactE);
            editTextCompactE = inflater.findViewById(R.id.EditTextCompactE);
            textViewMinusCompactE = inflater.findViewById(R.id.TextViewMinusCompactE);
            textViewPlusCompactE = inflater.findViewById(R.id.TextViewPlusCompactE);
            textViewCopyCompactE = inflater.findViewById(R.id.TextViewCopyCompactE);
            textViewPasteCompactE = inflater.findViewById(R.id.TextViewPasteCompactE);
            textViewClearCompactE = inflater.findViewById(R.id.TextViewClearCompactE);
            linearLayoutCompactInputF = inflater.findViewById(R.id.LinearLayoutCompactInputF);
            textViewLabelCompactF = inflater.findViewById(R.id.TextViewLabelCompactF);
            editTextCompactF = inflater.findViewById(R.id.EditTextCompactF);
            textViewMinusCompactF = inflater.findViewById(R.id.TextViewMinusCompactF);
            textViewPlusCompactF = inflater.findViewById(R.id.TextViewPlusCompactF);
            textViewCopyCompactF = inflater.findViewById(R.id.TextViewCopyCompactF);
            textViewPasteCompactF = inflater.findViewById(R.id.TextViewPasteCompactF);
            textViewClearCompactF = inflater.findViewById(R.id.TextViewClearCompactF);
            // Run buttons
            this.buttonRunExampleToggle = inflater.findViewById(R.id.ButtonRunExampleToggle);
            this.buttonRun = inflater.findViewById(R.id.ButtonRun);
            this.buttonRun1 = inflater.findViewById(R.id.ButtonRun1);
            this.buttonRun2 = inflater.findViewById(R.id.ButtonRun2);
            this.buttonMMinus = inflater.findViewById(R.id.ButtonMMinus);
            this.buttonM = inflater.findViewById(R.id.ButtonM);
            this.buttonMPlus = inflater.findViewById(R.id.ButtonMPlus);
            this.buttonRMinus = inflater.findViewById(R.id.ButtonRMinus);
            this.buttonR = inflater.findViewById(R.id.ButtonR);
            this.buttonRPlus = inflater.findViewById(R.id.ButtonRPlus);
            // Result controls
            this.textViewLabelResult = inflater.findViewById(R.id.TextViewLabelResult);
            this.textViewLabelElasticResult = inflater.findViewById(R.id.TextViewLabelElasticResult);
            this.textViewExpandResult = inflater.findViewById(R.id.TextViewExpandResult);
            this.textViewCopyResult = inflater.findViewById(R.id.TextViewCopyResult);
            this.textViewClearResult = inflater.findViewById(R.id.TextViewClearResult);
            this.linearLayoutResultContainer = inflater.findViewById(R.id.LinearLayoutResultContainer);
            this.editTextResult = inflater.findViewById(R.id.EditTextResult);
            this.linearLayoutResultGridContainer1 = inflater.findViewById(R.id.LinearLayoutResultGridContainer1);
            this.linearLayoutFModMContainer = inflater.findViewById(R.id.LinearLayoutFModMContainer);
            this.linearLayoutStaticColumnHeader1 = inflater.findViewById(R.id.LinearLayoutStaticColumnHeader1);
            this.listViewResult1 = inflater.findViewById(R.id.ListViewResult1);
            this.linearLayoutResultGridContainer2 = inflater.findViewById(R.id.LinearLayoutResultGridContainer2);
            this.linearLayoutStaticRowHeader2 = inflater.findViewById(R.id.LinearLayoutStaticRowHeader2);
            this.linearLayoutStaticColumnHeaderOrigin = inflater.findViewById(R.id.LinearLayoutStaticColumnHeaderOrigin);
            this.linearLayoutStaticColumnHeader2 = inflater.findViewById(R.id.LinearLayoutStaticColumnHeader2);
            this.listViewResult2 = inflater.findViewById(R.id.ListViewResult2);
            this.listViewRowHeaderResult2 = inflater.findViewById(R.id.ListViewRowHeaderResult2);


            // Constrain expanded input
            editTextA.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextB.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextC.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextD.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextE.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextF.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            // Constrain compact input
            editTextCompactA.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextCompactB.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextCompactC.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextCompactD.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextCompactE.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextCompactF.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});

            // Navigation vents
            textViewBackToAlgorithms.setOnClickListener(view -> {
                if(tabFragmentAlgorithms != null) {
                    // Go back to the algorithms main menu
                    FragmentAlgorithms fragmentAlgorithms = (FragmentAlgorithms) tabFragmentAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentAlgorithms");
                    tabFragmentAlgorithms.setFragment(fragmentAlgorithms);
                }
            });
            textViewInputToggle.setOnClickListener(view -> {
                int inputToggleValue = UserSettings.getBQFInputToggle(requireContext());
                switch (inputToggleValue) {
                    case 0: // fa_0
                        inputToggleValue = 4;
                        textViewInputToggle.setText(requireContext().getText(R.string.fa_4));
                        break;
                    case 4: // fa_4
                        inputToggleValue = 6;
                        textViewInputToggle.setText(requireContext().getText(R.string.fa_6));
                        break;
                    case 6: // fa_6
                        inputToggleValue = 0;
                        textViewInputToggle.setText(requireContext().getText(R.string.fa_0));
                        break;
                    default:
                        inputToggleValue = UserSettings.BQF_INPUT_TOGGLE_DEFAULT_VALUE;
                        textViewInputToggle.setText(requireContext().getText(R.string.fa_4));
                        break;
                }
                UserSettings.setBQFInputToggle(requireContext(), inputToggleValue);
                refreshInputToggle();
            });

            // Expanded input events
            editTextA.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextA.get()) return; // editTextA is locked
                    // Other work
                    String labelText = "a" + UIHelper.getNrOfDigits(s.toString());
                    textViewLabelA.setText(labelText);
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextCompactA
                    isUpdatingEditTextCompactA.set(true); // Lock editTextCompactA
                    try {
                        editTextCompactA.setText(s.toString());
                        // editTextCompactA.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextCompactA.set(false); // Unlock editTextCompactA
                    }
                }
            });
            editTextB.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextB.get()) return; // editTextB is locked
                    // Other work
                    String labelText = "b" + UIHelper.getNrOfDigits(s.toString());
                    textViewLabelB.setText(labelText);
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextCompactB
                    isUpdatingEditTextCompactB.set(true); // Lock editTextCompactB
                    try {
                        editTextCompactB.setText(s.toString());
                        // editTextCompactB.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextCompactB.set(false); // Unlock editTextCompactB
                    }
                }
            });
            editTextC.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextC.get()) return; // editTextC is locked
                    // Other work
                    String labelText = "c" + UIHelper.getNrOfDigits(s.toString());
                    textViewLabelC.setText(labelText);
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextCompactC
                    isUpdatingEditTextCompactC.set(true); // Lock editTextCompactC
                    try {
                        editTextCompactC.setText(s.toString());
                        // editTextCompactC.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextCompactC.set(false); // Unlock editTextCompactC
                    }
                }
            });
            editTextD.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextD.get()) return; // editTextD is locked
                    // Other work
                    String labelText = "d" + UIHelper.getNrOfDigits(s.toString());
                    textViewLabelD.setText(labelText);
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextCompactD
                    isUpdatingEditTextCompactD.set(true); // Lock editTextCompactD
                    try {
                        editTextCompactD.setText(s.toString());
                        // editTextCompactD.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextCompactD.set(false); // Unlock editTextCompactD
                    }
                }
            });
            editTextE.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextE.get()) return; // editTextE is locked
                    // Other work
                    String labelText = "e" + UIHelper.getNrOfDigits(s.toString());
                    textViewLabelE.setText(labelText);
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextCompactF
                    isUpdatingEditTextCompactE.set(true); // Lock editTextCompactE
                    try {
                        editTextCompactE.setText(s.toString());
                        // editTextCompactE.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextCompactE.set(false); // Unlock editTextCompactE
                    }
                }
            });
            editTextF.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextF.get()) return; // editTextF is locked
                    // Other work
                    String labelText = "f" + UIHelper.getNrOfDigits(s.toString());
                    textViewLabelF.setText(labelText);
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextCompactF
                    isUpdatingEditTextCompactF.set(true); // Lock editTextCompactF
                    try {
                        editTextCompactF.setText(s.toString());
                        // editTextCompactF.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextCompactF.set(false); // Unlock editTextCompactF
                    }
                }
            });


            // Compact input events
            editTextCompactA.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextCompactA.get()) return; // editTextCompactA is locked
                    // Other work
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextA
                    isUpdatingEditTextA.set(true); // Lock editTextA
                    try {
                        editTextA.setText(s.toString());
                        // editTextA.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextA.set(false); // unlock editTextA
                    }
                }
            });
            editTextCompactB.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextCompactB.get()) return; // editTextCompactB is locked
                    // Other work
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextB
                    isUpdatingEditTextB.set(true); // Lock editTextB
                    try {
                        editTextB.setText(s.toString());
                        // editTextB.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextB.set(false); // unlock editTextB
                    }
                }
            });
            editTextCompactC.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextCompactC.get()) return; // editTextCompactC is locked
                    // Other work
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextC
                    isUpdatingEditTextC.set(true); // Lock editTextC
                    try {
                        editTextC.setText(s.toString());
                        // editTextC.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextC.set(false); // unlock editTextC
                    }
                }
            });
            editTextCompactD.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextCompactD.get()) return; // editTextCompactD is locked
                    // Other work
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextD
                    isUpdatingEditTextD.set(true); // Lock editTextD
                    try {
                        editTextD.setText(s.toString());
                        // editTextD.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextD.set(false); // unlock editTextD
                    }
                }
            });
            editTextCompactE.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextCompactE.get()) return; // editTextCompactE is locked
                    // Other work
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextE
                    isUpdatingEditTextE.set(true); // Lock editTextE
                    try {
                        editTextE.setText(s.toString());
                        // editTextE.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextE.set(false); // unlock editTextE
                    }
                }
            });
            editTextCompactF.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextCompactF.get()) return; // editTextCompactF is locked
                    // Other work
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextF
                    isUpdatingEditTextF.set(true); // Lock editTextF
                    try {
                        editTextF.setText(s.toString());
                        // editTextF.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextF.set(false); // unlock editTextF
                    }
                }
            });


            // Expanded input a clipboard button events
            textViewMinusA.setOnClickListener(v -> {
                decreaseByOne(editTextA);
                resetAllAndSelectTheLastButtonClicked(textViewMinusA);
            });
            textViewPlusA.setOnClickListener(v -> {
                increaseByOne(editTextA);
                resetAllAndSelectTheLastButtonClicked(textViewPlusA);
            });
            textViewCopyA.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextA);
                resetAllAndSelectTheLastButtonClicked(textViewCopyA);
            });
            textViewPasteA.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextA);
                resetAllAndSelectTheLastButtonClicked(textViewPasteA);
            });
            textViewClearA.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextA);
                resetAllAndSelectTheLastButtonClicked(textViewClearA);
            });

            // Expanded input b clipboard button events
            textViewMinusB.setOnClickListener(v -> {
                decreaseByOne(editTextB);
                resetAllAndSelectTheLastButtonClicked(textViewMinusB);
            });
            textViewPlusB.setOnClickListener(v -> {
                increaseByOne(editTextB);
                resetAllAndSelectTheLastButtonClicked(textViewPlusB);
            });
            textViewCopyB.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextB);
                resetAllAndSelectTheLastButtonClicked(textViewCopyB);
            });
            textViewPasteB.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextB);
                resetAllAndSelectTheLastButtonClicked(textViewPasteB);
            });
            textViewClearB.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextB);
                resetAllAndSelectTheLastButtonClicked(textViewClearB);
            });

            // Expanded input c clipboard button events
            textViewMinusC.setOnClickListener(v -> {
                decreaseByOne(editTextC);
                resetAllAndSelectTheLastButtonClicked(textViewMinusC);
            });
            textViewPlusC.setOnClickListener(v -> {
                increaseByOne(editTextC);
                resetAllAndSelectTheLastButtonClicked(textViewPlusC);
            });
            textViewCopyC.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextC);
                resetAllAndSelectTheLastButtonClicked(textViewCopyC);
            });
            textViewPasteC.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextC);
                resetAllAndSelectTheLastButtonClicked(textViewPasteC);
            });
            textViewClearC.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextC);
                resetAllAndSelectTheLastButtonClicked(textViewClearC);
            });

            // Expanded input d clipboard button events
            textViewMinusD.setOnClickListener(v -> {
                decreaseByOne(editTextD);
                resetAllAndSelectTheLastButtonClicked(textViewMinusD);
            });
            textViewPlusD.setOnClickListener(v -> {
                increaseByOne(editTextD);
                resetAllAndSelectTheLastButtonClicked(textViewPlusD);
            });
            textViewCopyD.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextD);
                resetAllAndSelectTheLastButtonClicked(textViewCopyD);
            });
            textViewPasteD.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextD);
                resetAllAndSelectTheLastButtonClicked(textViewPasteD);
            });
            textViewClearD.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextD);
                resetAllAndSelectTheLastButtonClicked(textViewClearD);
            });

            // Expanded input e clipboard button events
            textViewMinusE.setOnClickListener(v -> {
                decreaseByOne(editTextE);
                resetAllAndSelectTheLastButtonClicked(textViewMinusE);
            });
            textViewPlusE.setOnClickListener(v -> {
                increaseByOne(editTextE);
                resetAllAndSelectTheLastButtonClicked(textViewPlusE);
            });
            textViewCopyE.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextE);
                resetAllAndSelectTheLastButtonClicked(textViewCopyE);
            });
            textViewPasteE.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextE);
                resetAllAndSelectTheLastButtonClicked(textViewPasteE);
            });
            textViewClearE.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextE);
                resetAllAndSelectTheLastButtonClicked(textViewClearE);
            });

            // Expanded input f clipboard button events
            textViewMinusF.setOnClickListener(v -> {
                decreaseByOne(editTextF);
                resetAllAndSelectTheLastButtonClicked(textViewMinusF);
            });
            textViewPlusF.setOnClickListener(v -> {
                increaseByOne(editTextF);
                resetAllAndSelectTheLastButtonClicked(textViewPlusF);
            });
            textViewCopyF.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextF);
                resetAllAndSelectTheLastButtonClicked(textViewCopyF);
            });
            textViewPasteF.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextF);
                resetAllAndSelectTheLastButtonClicked(textViewPasteF);
            });
            textViewClearF.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextF);
                resetAllAndSelectTheLastButtonClicked(textViewClearF);
            });


            // Compact input a clipboard button events
            textViewMinusCompactA.setOnClickListener(v -> {
                decreaseByOne(editTextCompactA);
                resetAllAndSelectTheLastButtonClicked(textViewMinusCompactA);
            });
            textViewPlusCompactA.setOnClickListener(v -> {
                increaseByOne(editTextCompactA);
                resetAllAndSelectTheLastButtonClicked(textViewPlusCompactA);
            });
            textViewCopyCompactA.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextCompactA);
                resetAllAndSelectTheLastButtonClicked(textViewCopyCompactA);
            });
            textViewPasteCompactA.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextCompactA);
                resetAllAndSelectTheLastButtonClicked(textViewPasteCompactA);
            });
            textViewClearCompactA.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextCompactA);
                resetAllAndSelectTheLastButtonClicked(textViewClearCompactA);
            });

            // Compact input b clipboard button events
            textViewMinusCompactB.setOnClickListener(v -> {
                decreaseByOne(editTextCompactB);
                resetAllAndSelectTheLastButtonClicked(textViewMinusCompactB);
            });
            textViewPlusCompactB.setOnClickListener(v -> {
                increaseByOne(editTextCompactB);
                resetAllAndSelectTheLastButtonClicked(textViewPlusCompactB);
            });
            textViewCopyCompactB.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextCompactB);
                resetAllAndSelectTheLastButtonClicked(textViewCopyCompactB);
            });
            textViewPasteCompactB.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextCompactB);
                resetAllAndSelectTheLastButtonClicked(textViewPasteCompactB);
            });
            textViewClearCompactB.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextCompactB);
                resetAllAndSelectTheLastButtonClicked(textViewClearCompactB);
            });

            // Compact input c clipboard button events
            textViewMinusCompactC.setOnClickListener(v -> {
                decreaseByOne(editTextCompactC);
                resetAllAndSelectTheLastButtonClicked(textViewMinusCompactC);
            });
            textViewPlusCompactC.setOnClickListener(v -> {
                increaseByOne(editTextCompactC);
                resetAllAndSelectTheLastButtonClicked(textViewPlusCompactC);
            });
            textViewCopyCompactC.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextCompactC);
                resetAllAndSelectTheLastButtonClicked(textViewCopyCompactC);
            });
            textViewPasteCompactC.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextCompactC);
                resetAllAndSelectTheLastButtonClicked(textViewPasteCompactC);
            });
            textViewClearCompactC.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextCompactC);
                resetAllAndSelectTheLastButtonClicked(textViewClearCompactC);
            });

            // Compact input d clipboard button events
            textViewMinusCompactD.setOnClickListener(v -> {
                decreaseByOne(editTextCompactD);
                resetAllAndSelectTheLastButtonClicked(textViewMinusCompactD);
            });
            textViewPlusCompactD.setOnClickListener(v -> {
                increaseByOne(editTextCompactD);
                resetAllAndSelectTheLastButtonClicked(textViewPlusCompactD);
            });
            textViewCopyCompactD.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextCompactD);
                resetAllAndSelectTheLastButtonClicked(textViewCopyCompactD);
            });
            textViewPasteCompactD.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextCompactD);
                resetAllAndSelectTheLastButtonClicked(textViewPasteCompactD);
            });
            textViewClearCompactD.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextCompactD);
                resetAllAndSelectTheLastButtonClicked(textViewClearCompactD);
            });

            // Compact input e clipboard button events
            textViewMinusCompactE.setOnClickListener(v -> {
                decreaseByOne(editTextCompactE);
                resetAllAndSelectTheLastButtonClicked(textViewMinusCompactE);
            });
            textViewPlusCompactE.setOnClickListener(v -> {
                increaseByOne(editTextCompactE);
                resetAllAndSelectTheLastButtonClicked(textViewPlusCompactE);
            });
            textViewCopyCompactE.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextCompactE);
                resetAllAndSelectTheLastButtonClicked(textViewCopyCompactE);
            });
            textViewPasteCompactE.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextCompactE);
                resetAllAndSelectTheLastButtonClicked(textViewPasteCompactE);
            });
            textViewClearCompactE.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextCompactE);
                resetAllAndSelectTheLastButtonClicked(textViewClearCompactE);
            });

            // Compact input f clipboard button events
            textViewMinusCompactF.setOnClickListener(v -> {
                decreaseByOne(editTextCompactF);
                resetAllAndSelectTheLastButtonClicked(textViewMinusCompactF);
            });
            textViewPlusCompactF.setOnClickListener(v -> {
                increaseByOne(editTextCompactF);
                resetAllAndSelectTheLastButtonClicked(textViewPlusCompactF);
            });
            textViewCopyCompactF.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextCompactF);
                resetAllAndSelectTheLastButtonClicked(textViewCopyCompactF);
            });
            textViewPasteCompactF.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextCompactF);
                resetAllAndSelectTheLastButtonClicked(textViewPasteCompactF);
            });
            textViewClearCompactF.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextCompactF);
                resetAllAndSelectTheLastButtonClicked(textViewClearCompactF);
            });

            // Run button events
            this.buttonRunExampleToggle.setOnClickListener(v -> onButtonRunExampleToggle(container));
            this.buttonRun.setOnClickListener(v -> onButtonRun(container, false, false));
            this.buttonRun1.setOnClickListener(v -> onButtonRun1(container, false));
            this.buttonRun2.setOnClickListener(v -> onButtonRun2(container, false));

            // Run button events
            buttonMMinus.setOnClickListener(v -> {
                String mText = buttonM.getText().toString();
                if (!mText.isEmpty()) {
                    int m = Integer.parseInt(mText);
                    if (m <= 1) { // m value should not be 0 otherwise we get "modulus not positive" or "m.signum() <= 0" exceptions when doing m.mod(r)
                        buttonM.setText("");
                    } else {
                        m = m - 1;
                        buttonM.setText(String.valueOf(m));
                    }
                }
                calculateFModM();
            });
            buttonMPlus.setOnClickListener(v -> {
                String mText = buttonM.getText().toString();
                if (mText.isEmpty()) {
                    buttonM.setText("1"); // m value should not be 0 otherwise we get "modulus not positive" or "m.signum() <= 0" exceptions when doing m.mod(r)
                } else {
                    int m = Integer.parseInt(mText);
                    m = m + 1;
                    buttonM.setText(String.valueOf(m));
                }
                calculateFModM();
            });
            buttonRMinus.setOnClickListener(v -> {
                String rText = buttonR.getText().toString();
                if (!rText.isEmpty()) {
                    int r = Integer.parseInt(rText);
                    if (r <= 0) {
                        buttonR.setText("");
                    } else {
                        r -= 1;
                        buttonR.setText(String.valueOf(r));
                    }
                }
                calculateFModM();
            });
            buttonRPlus.setOnClickListener(v -> {
                String rText = buttonR.getText().toString();
                if (rText.isEmpty()) {
                    buttonR.setText("0");
                } else {
                    String mText = buttonM.getText().toString();
                    if (mText.isEmpty()) {
                        buttonR.setText("");
                    } else {
                        int m = Integer.parseInt(mText);
                        int r = Integer.parseInt(rText);
                        r += 1;
                        if (r < m) {
                            buttonR.setText(String.valueOf(r));
                        }
                    }
                }
                calculateFModM();
            });

            // Result clipboard button events
            textViewExpandResult.setOnClickListener(v -> {
                expandResult();
                resetAllAndSelectTheLastButtonClicked(textViewExpandResult);
            });
            textViewCopyResult.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextResult);
                resetAllAndSelectTheLastButtonClicked(textViewCopyResult);
            });
            textViewClearResult.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextResult);
                resetResult(false);
                resetAllAndSelectTheLastButtonClicked(textViewClearResult);
            });

            // Result events
            initDoubleTapDetector(editTextResult);
            editTextResult.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    if (s == null || s.length() == 0){
                        textViewExpandResult.setVisibility(View.GONE);
                    } else {
                        textViewExpandResult.setVisibility(View.VISIBLE);
                    }
                }
            });

            listViewResult2.setOnScrollListener(syncScrollListener);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }

        return inflater;
    }
    //endregion CREATE


    AbsListView.OnScrollListener syncScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            // Not needed
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            View firstView = view.getChildAt(0);
            int topOffset = (firstView == null) ? 0 : firstView.getTop();
            listViewRowHeaderResult2.setSelectionFromTop(firstVisibleItem, topOffset);
        }
    };


    @Override
    protected void fireOnDoubleTap(View view) {
        if (view == editTextResult){
            expandResult();
            resetAllAndSelectTheLastButtonClicked(textViewExpandResult);
        }
    }
    private void expandResult() {
        if (linearLayoutResultContainer.getVisibility() == View.VISIBLE) {
            PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewTitle.getText().toString(), editTextResult.getText());
            popupResult.show();
        } else if (linearLayoutResultGridContainer1.getVisibility() == View.VISIBLE) {
            PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewTitle.getText().toString(), linearLayoutStaticColumnHeader1, listViewResult1);
            popupResult.show();
        } else if (linearLayoutResultGridContainer2.getVisibility() == View.VISIBLE) {
            PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewTitle.getText().toString(), linearLayoutStaticColumnHeader2, listViewResult2);
            popupResult.show();
        }
    }


    // region MENU
    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        try {
            menuInflater.inflate(R.menu.menu_fragment_binary_quadratic_form, menu);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
        this.menuItemIncludeTrivialSolutions = menu.findItem(R.id.quadratic_form_menu_include_trivial_solutions);
        this.menuItemIncludeOnlyPositiveSolutions = menu.findItem(R.id.quadratic_form_menu_include_only_positive_solutions);
        this.menuItemIncludeOnlyNegativeSolutions = menu.findItem(R.id.quadratic_form_menu_include_only_negative_solutions);
        // Get the value from shared preferences.
        boolean includeTrivialSolutions = UserSettings.getBQFIncludeTrivialSolutions(requireContext());
        menuItemIncludeTrivialSolutions.setChecked(includeTrivialSolutions);
        boolean includeOnlyPositiveSolutions = UserSettings.getBQFIncludeOnlyPositiveSolutions(requireContext());
        menuItemIncludeOnlyPositiveSolutions.setChecked(includeOnlyPositiveSolutions);
        boolean includeOnlyNegativeSolutions = UserSettings.getBQFIncludeOnlyNegativeSolutions(requireContext());
        menuItemIncludeOnlyNegativeSolutions.setChecked(includeOnlyNegativeSolutions);
    }

    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        try {
            // Handle menu item clicks here based on their ID.
            int id = menuItem.getItemId();
            if (id == R.id.quadratic_form_menu_include_trivial_solutions) {
                boolean isChecked = !menuItemIncludeTrivialSolutions.isChecked();
                // Store the value to shared preferences.
                UserSettings.setBQFIncludeTrivialSolutions(requireContext(), isChecked);
                menuItemIncludeTrivialSolutions.setChecked(isChecked);
                resetResult(false);
                return true;
            }
            if (id == R.id.quadratic_form_menu_include_only_positive_solutions) {
                boolean isChecked = !menuItemIncludeOnlyPositiveSolutions.isChecked();
                // Store the value to shared preferences.
                UserSettings.setBQFIncludeOnlyPositiveSolutions(requireContext(), isChecked);
                menuItemIncludeOnlyPositiveSolutions.setChecked(isChecked);
                resetResult(false);
                return true;
            }
            if (id == R.id.quadratic_form_menu_include_only_negative_solutions) {
                boolean isChecked = !menuItemIncludeOnlyNegativeSolutions.isChecked();
                // Store the value to shared preferences.
                UserSettings.setBQFIncludeOnlyNegativeSolutions(requireContext(), isChecked);
                menuItemIncludeOnlyNegativeSolutions.setChecked(isChecked);
                resetResult(false);
                return true;
            }
            if (id == R.id.quadratic_form_menu_example_1) {
                setExample1();
                runExample(null);
                return true;
            }
            if (id == R.id.quadratic_form_menu_example_2) {
                setExample2();
                runExample(null);
                return true;
            }
            if (id == R.id.quadratic_form_menu_example_3) {
                setExample3();
                runExample(null);
                return true;
            }
            if (id == R.id.quadratic_form_menu_example_4) {
                setExample4();
                runExample(null);
                return true;
            }
            if (id == R.id.quadratic_form_menu_example_5) {
                setExample5();
                runExample(null);
                return true;
            }
            if (id == R.id.menu_documentation) {
                DialogFragmentPdfViewer.newInstance(DialogFragmentPdfViewer.BINARY_QUADRATIC_FORM_PDF).show(getParentFragmentManager(), "BINARY_QUADRATIC_FORM_PDF");
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
        refreshInputToggle();
        refreshInputViewMode();
        refreshShowInputDecreaseIncreaseButtons();
        refreshControlsDisplay();
        refreshHideExampleButtons();
        refreshResultDisplay();
        refreshRun();
    }


    //region Refresh UI
    private void refreshInputToggle() {
        try {
            int inputToggleValue = UserSettings.getBQFInputToggle(requireContext());
            switch (inputToggleValue) {
                case 0:
                    textViewInputToggle.setText(requireContext().getText(R.string.fa_0));
                    linearLayoutInputInputView.setVisibility(View.GONE);
                    linearLayoutExpandedInputA.setVisibility(View.GONE);
                    linearLayoutExpandedInputB.setVisibility(View.GONE);
                    linearLayoutExpandedInputC.setVisibility(View.GONE);
                    linearLayoutExpandedInputD.setVisibility(View.GONE);
                    linearLayoutExpandedInputE.setVisibility(View.GONE);
                    linearLayoutExpandedInputF.setVisibility(View.GONE);
                    linearLayoutCompactInputA.setVisibility(View.GONE);
                    linearLayoutCompactInputB.setVisibility(View.GONE);
                    linearLayoutCompactInputC.setVisibility(View.GONE);
                    linearLayoutCompactInputD.setVisibility(View.GONE);
                    linearLayoutCompactInputE.setVisibility(View.GONE);
                    linearLayoutCompactInputF.setVisibility(View.GONE);
                    break;
                case 4:
                    textViewInputToggle.setText(requireContext().getText(R.string.fa_4));
                    linearLayoutInputInputView.setVisibility(View.VISIBLE);
                    linearLayoutExpandedInputA.setVisibility(View.GONE);
                    linearLayoutExpandedInputB.setVisibility(View.VISIBLE);
                    linearLayoutExpandedInputC.setVisibility(View.GONE);
                    linearLayoutExpandedInputD.setVisibility(View.VISIBLE);
                    linearLayoutExpandedInputE.setVisibility(View.VISIBLE);
                    linearLayoutExpandedInputF.setVisibility(View.VISIBLE);
                    linearLayoutCompactInputA.setVisibility(View.GONE);
                    linearLayoutCompactInputB.setVisibility(View.VISIBLE);
                    linearLayoutCompactInputC.setVisibility(View.GONE);
                    linearLayoutCompactInputD.setVisibility(View.VISIBLE);
                    linearLayoutCompactInputE.setVisibility(View.VISIBLE);
                    linearLayoutCompactInputF.setVisibility(View.VISIBLE);
                    break;
                case 6:
                    textViewInputToggle.setText(requireContext().getText(R.string.fa_6));
                    linearLayoutInputInputView.setVisibility(View.VISIBLE);
                    linearLayoutExpandedInputA.setVisibility(View.VISIBLE);
                    linearLayoutExpandedInputB.setVisibility(View.VISIBLE);
                    linearLayoutExpandedInputC.setVisibility(View.VISIBLE);
                    linearLayoutExpandedInputD.setVisibility(View.VISIBLE);
                    linearLayoutExpandedInputE.setVisibility(View.VISIBLE);
                    linearLayoutExpandedInputF.setVisibility(View.VISIBLE);
                    linearLayoutCompactInputA.setVisibility(View.VISIBLE);
                    linearLayoutCompactInputB.setVisibility(View.VISIBLE);
                    linearLayoutCompactInputC.setVisibility(View.VISIBLE);
                    linearLayoutCompactInputD.setVisibility(View.VISIBLE);
                    linearLayoutCompactInputE.setVisibility(View.VISIBLE);
                    linearLayoutCompactInputF.setVisibility(View.VISIBLE);
                    break;
                default:
                    inputToggleValue = UserSettings.BQF_INPUT_TOGGLE_DEFAULT_VALUE;
                    UserSettings.setBQFInputToggle(requireContext(), inputToggleValue);
                    textViewInputToggle.setText(requireContext().getText(R.string.fa_4));
                    linearLayoutInputInputView.setVisibility(View.VISIBLE);
                    linearLayoutExpandedInputA.setVisibility(View.GONE);
                    linearLayoutExpandedInputB.setVisibility(View.VISIBLE);
                    linearLayoutExpandedInputC.setVisibility(View.GONE);
                    linearLayoutExpandedInputD.setVisibility(View.VISIBLE);
                    linearLayoutExpandedInputE.setVisibility(View.VISIBLE);
                    linearLayoutExpandedInputF.setVisibility(View.VISIBLE);
                    linearLayoutCompactInputA.setVisibility(View.GONE);
                    linearLayoutCompactInputB.setVisibility(View.VISIBLE);
                    linearLayoutCompactInputC.setVisibility(View.GONE);
                    linearLayoutCompactInputD.setVisibility(View.VISIBLE);
                    linearLayoutCompactInputE.setVisibility(View.VISIBLE);
                    linearLayoutCompactInputF.setVisibility(View.VISIBLE);
                    break;
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void refreshInputViewMode() {
        try {
            this.isCompactInputView = UserSettings.getCompactInputView(requireContext());
            if(isCompactInputView){
                linearLayoutExpandedInputView.setVisibility(View.GONE);
                linearLayoutCompactInputView.setVisibility(View.VISIBLE);
            } else {
                linearLayoutExpandedInputView.setVisibility(View.VISIBLE);
                linearLayoutCompactInputView.setVisibility(View.GONE);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void refreshShowInputDecreaseIncreaseButtons() {
        try {
            boolean showInputDecreaseIncreaseButtons = UserSettings.getShowInputDecreaseIncreaseButtons(requireContext());
            if (showInputDecreaseIncreaseButtons) {
                textViewMinusA.setVisibility(View.VISIBLE);
                textViewPlusA.setVisibility(View.VISIBLE);
                textViewMinusCompactA.setVisibility(View.VISIBLE);
                textViewPlusCompactA.setVisibility(View.VISIBLE);
                textViewMinusB.setVisibility(View.VISIBLE);
                textViewPlusB.setVisibility(View.VISIBLE);
                textViewMinusCompactB.setVisibility(View.VISIBLE);
                textViewPlusCompactB.setVisibility(View.VISIBLE);
                textViewMinusC.setVisibility(View.VISIBLE);
                textViewPlusC.setVisibility(View.VISIBLE);
                textViewMinusCompactC.setVisibility(View.VISIBLE);
                textViewPlusCompactC.setVisibility(View.VISIBLE);
                textViewMinusD.setVisibility(View.VISIBLE);
                textViewPlusD.setVisibility(View.VISIBLE);
                textViewMinusCompactD.setVisibility(View.VISIBLE);
                textViewPlusCompactD.setVisibility(View.VISIBLE);
                textViewMinusE.setVisibility(View.VISIBLE);
                textViewPlusE.setVisibility(View.VISIBLE);
                textViewMinusCompactE.setVisibility(View.VISIBLE);
                textViewPlusCompactE.setVisibility(View.VISIBLE);
                textViewMinusF.setVisibility(View.VISIBLE);
                textViewPlusF.setVisibility(View.VISIBLE);
                textViewMinusCompactF.setVisibility(View.VISIBLE);
                textViewPlusCompactF.setVisibility(View.VISIBLE);
            } else {
                textViewMinusA.setVisibility(View.GONE);
                textViewPlusA.setVisibility(View.GONE);
                textViewMinusCompactA.setVisibility(View.GONE);
                textViewPlusCompactA.setVisibility(View.GONE);
                textViewMinusB.setVisibility(View.GONE);
                textViewPlusB.setVisibility(View.GONE);
                textViewMinusCompactB.setVisibility(View.GONE);
                textViewPlusCompactB.setVisibility(View.GONE);
                textViewMinusC.setVisibility(View.GONE);
                textViewPlusC.setVisibility(View.GONE);
                textViewMinusCompactC.setVisibility(View.GONE);
                textViewPlusCompactC.setVisibility(View.GONE);
                textViewMinusD.setVisibility(View.GONE);
                textViewPlusD.setVisibility(View.GONE);
                textViewMinusCompactD.setVisibility(View.GONE);
                textViewPlusCompactD.setVisibility(View.GONE);
                textViewMinusE.setVisibility(View.GONE);
                textViewPlusE.setVisibility(View.GONE);
                textViewMinusCompactE.setVisibility(View.GONE);
                textViewPlusCompactE.setVisibility(View.GONE);
                textViewMinusF.setVisibility(View.GONE);
                textViewPlusF.setVisibility(View.GONE);
                textViewMinusCompactF.setVisibility(View.GONE);
                textViewPlusCompactF.setVisibility(View.GONE);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void refreshHideExampleButtons() {
        if (!isAdded()) return; // Ensure Fragment is attached before accessing context or views.

        try {
            boolean hideExampleButtons = UserSettings.getHideExampleButtons(requireContext());
            buttonRunExampleToggle.setVisibility(hideExampleButtons ? View.GONE : View.VISIBLE);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void refreshControlsDisplay() {
        try {
            boolean biggerControls = UserSettings.getBiggerControls(requireContext());
            // Clipboard input buttons
            ControlDisplay.setClipboardButtonFontSize(textViewMinusA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusC, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusC, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyC, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteC, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearC, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusD, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusD, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyD, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteD, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearD, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusE, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusE, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyE, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteE, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearE, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusF, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusF, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyF, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteF, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearF, biggerControls);
            //
            ControlDisplay.setClipboardButtonFontSize(textViewMinusCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusCompactC, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusCompactC, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyCompactC, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteCompactC, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearCompactC, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusCompactD, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusCompactD, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyCompactD, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteCompactD, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearCompactD, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusCompactE, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusCompactE, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyCompactE, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteCompactE, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearCompactE, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusCompactF, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusCompactF, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyCompactF, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteCompactF, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearCompactF, biggerControls);
            // Clipboard output buttons
            ControlDisplay.setClipboardButtonFontSize(textViewExpandResult, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyResult, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearResult, biggerControls);
            // Expanded input controls
            ControlDisplay.setInputLabelFontSize(textViewLabelA, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelElasticA, biggerControls);
            ControlDisplay.setInputFontSize(editTextA, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelB, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelElasticB, biggerControls);
            ControlDisplay.setInputFontSize(editTextB, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelC, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelElasticC, biggerControls);
            ControlDisplay.setInputFontSize(editTextC, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelD, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelElasticD, biggerControls);
            ControlDisplay.setInputFontSize(editTextD, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelE, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelElasticE, biggerControls);
            ControlDisplay.setInputFontSize(editTextE, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelF, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelElasticF, biggerControls);
            ControlDisplay.setInputFontSize(editTextF, biggerControls);
            // Compact input controls
            ControlDisplay.setInputLabelFontSize(textViewLabelCompactA, biggerControls);
            ControlDisplay.setInputFontSize(editTextCompactA, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelCompactB, biggerControls);
            ControlDisplay.setInputFontSize(editTextCompactB, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelCompactC, biggerControls);
            ControlDisplay.setInputFontSize(editTextCompactC, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelCompactD, biggerControls);
            ControlDisplay.setInputFontSize(editTextCompactD, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelCompactE, biggerControls);
            ControlDisplay.setInputFontSize(editTextCompactE, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelCompactF, biggerControls);
            ControlDisplay.setInputFontSize(editTextCompactF, biggerControls);
            // Run buttons
            ControlDisplay.setButtonFontSize(buttonRunExampleToggle, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRun, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRun1, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRun2, biggerControls);
            // Run Buttons
            ControlDisplay.setButtonFontSize(buttonMMinus, biggerControls);
            ControlDisplay.setButtonFontSize(buttonM, biggerControls);
            ControlDisplay.setButtonFontSize(buttonMPlus, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRMinus, biggerControls);
            ControlDisplay.setButtonFontSize(buttonR, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRPlus, biggerControls);
            // Output result
            ControlDisplay.setInputLabelFontSize(this.textViewLabelResult, biggerControls);
            ControlDisplay.setInputLabelFontSize(this.textViewLabelElasticResult, biggerControls);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void refreshResultDisplay() {
        try {
            boolean biggerControls = UserSettings.getBiggerResultDisplay(requireContext());
            // Output result
            ControlDisplay.setOutputFontSize(editTextResult, biggerControls);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void refreshRun() {
        boolean inputAIsEmpty = getInputGroupA().isInputEmpty();
        boolean inputBIsEmpty = getInputGroupB().isInputEmpty();
        boolean inputCIsEmpty = getInputGroupC().isInputEmpty();
        boolean inputDIsEmpty = getInputGroupD().isInputEmpty();
        boolean inputEIsEmpty = getInputGroupE().isInputEmpty();
        boolean inputFIsEmpty = getInputGroupF().isInputEmpty();

        if (inputAIsEmpty || inputBIsEmpty || inputCIsEmpty || inputDIsEmpty || inputEIsEmpty || inputFIsEmpty) {
            return;
        }

        switch (lastRun) {
            case RUN:
                onButtonRun(null, true, false);
                break;
            case RUN1:
                onButtonRun1(null, false);
                break;
            case RUN2:
                onButtonRun2(null, false);
                break;
            default:
                onButtonRun(null, true, false);
                break;
        }
    }
    //endregion Refresh UI


    //region Callback
    @Override
    public void callbackResult(AlgorithmName algorithmName, Object result, ProgressStatus progressStatus) {
        Activity activity = getActivity();
        if (activity == null || !this.isAdded()) {
            return;
        }
        if (algorithmName == AlgorithmName.BINARY_QUADRATIC_FORM) {
            if (progressStatus == ProgressStatus.CANCELED) {
                String resultCanceledText = requireContext().getResources().getString(R.string.canceled);
                editTextResult.setText(resultCanceledText);
            } else {
                String resultAsString = (String)result;
                CharSequence resultFromHtml = Html.fromHtml(resultAsString, Html.FROM_HTML_MODE_LEGACY);
                editTextResult.setText(resultFromHtml);
            }
        }

        if (algorithmName == AlgorithmName.BINARY_QUADRATIC_FORM_1) {
            if (progressStatus == ProgressStatus.CANCELED) {
                cancelShowResult(listViewResult1);
            } else {
                @SuppressWarnings("unchecked")
                Grid gridResult1 = (Grid) result;
                showResult1(gridResult1);
            }
        }

        if (algorithmName == AlgorithmName.BINARY_QUADRATIC_FORM_2) {
            if (progressStatus == ProgressStatus.CANCELED) {
                cancelShowResult(listViewResult2);
            } else {
                @SuppressWarnings("unchecked")
                Grid gridResult2 = (Grid) result;
                showResult2(gridResult2);
            }
        }
    }

    private void cancelShowResult(ListView listView) {
        ArrayList<String> listItems=new ArrayList<>();
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(requireContext(), R.layout.cell_canceled, R.id.CellCanceled, listItems);
        setListViewAdapter(listView, adapter);
        listItems.add(requireContext().getResources().getString(R.string.canceled));
        adapter.notifyDataSetChanged();
    }

    private void showResult1(Grid grid) {
        try {
            List<List<Cell>> corner = grid.getCorner();
            List<List<Cell>> columnHeaders = grid.getColumnHeaders();
            List<List<Cell>> rowHeaders = grid.getRowHeaders(); // TODO
            List<List<Cell>> rows = grid.getRows();

            // TODO calculate width when creating the data
            List<Cell> columnHeaderRow = columnHeaders.getFirst();
            // Get max text length per each column in rows.
            List<String> columnsMaxText = new ArrayList<>();
            // Populate with the column header cell values.
            for(int c = 0; c < columnHeaderRow.size(); c++) {
                Cell cell = columnHeaderRow.get(c);
                columnsMaxText.add(cell.getValue());
            }
            for(int r = 0; r < rows.size(); r++) {
                List<Cell> row = rows.get(r);
                for(int c = 0; c < row.size(); c++) {
                    Cell cell = row.get(c);
                    String existingMaxText = columnsMaxText.get(c);
                    if (cell.getValue().length() > existingMaxText.length()) {
                        columnsMaxText.set(c, cell.getValue());
                    }
                }
            }

            // Get the value from shared preferences.
            boolean biggerResultDisplay = UserSettings.getBiggerResultDisplay(requireContext());

            // TODO calculate width when creating the data
            // Get and set the max row item width per each column.
            List<Integer> cellWidths = new ArrayList<>();
            LayoutInflater layoutInflater = LayoutInflater.from(requireContext());
            int cellResource = biggerResultDisplay ? R.layout.cell_big : R.layout.cell_small;
            int extraPadding = (int) UIHelper.convertDpToPixel(10f, requireContext());
            for (int c = 0; c < columnsMaxText.size(); c++) {
                // Start the pre-calculate
                TextView textViewTemp = (TextView) layoutInflater.inflate(cellResource, null, false);
                String maxText = columnsMaxText.get(c);
                textViewTemp.setText(maxText);
                // Must call measure!
                textViewTemp.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                // Get width
                int maxColumnWidth = textViewTemp.getMeasuredWidth();
                cellWidths.add(maxColumnWidth + extraPadding);
            }

            int cellWidthDefault = LinearLayout.LayoutParams.WRAP_CONTENT;
            int cellHeightDefault = LinearLayout.LayoutParams.WRAP_CONTENT;

            // Set the listview row space
            float dividerDp = biggerResultDisplay ? 4f : 1f;
            int dividerPx = (int) UIHelper.convertDpToPixel(dividerDp, requireContext());
            listViewResult1.setDividerHeight(dividerPx);

            // Set column headers.
            CellUI cellUI = new CellUI(requireContext(), cellWidthDefault, cellWidths, cellHeightDefault, null, biggerResultDisplay);
            Grid.setColumnHeaders(cellUI, columnHeaders, linearLayoutStaticColumnHeader1);

            // Create and set the adapter.
            GridAdapter adapter = new GridAdapter(requireContext(), rows, cellWidthDefault, cellWidths, cellHeightDefault, null, biggerResultDisplay);
            setListViewAdapter(listViewResult1, adapter);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }

    private void showResult2(Grid grid) {
        try {
            List<List<Cell>> corner = grid.getCorner();
            List<List<Cell>> columnHeaders = grid.getColumnHeaders();
            List<List<Cell>> rowHeaders = grid.getRowHeaders();
            List<List<Cell>> rows = grid.getRows();

            // Get the values from shared preferences.
            boolean biggerResultDisplay = UserSettings.getBiggerResultDisplay(requireContext());
            boolean squareResultDisplay = UserSettings.getSquareResultDisplay(requireContext());

            BigInteger m = null;
            BigInteger r = null;
            String mText = this.buttonM.getText().toString();
            if (!mText.isEmpty()) {
                m = new BigInteger(mText);
            }
            String rText = this.buttonR.getText().toString();
            if (!rText.isEmpty()) {
                r = new BigInteger(rText);
            }

            // TODO calculate width when creating the data
            // Get the last item in the last row
            List<Cell> lastRow = rows.get(rows.size()-1);
            String lastRowLastValue = lastRow.get(lastRow.size()-1).getValue();
            int maxTextLength = lastRowLastValue.length();
            maxTextLength = maxTextLength + 1; // Add 1 for easy reading.
            // Construct the maxText. if maxTextLength = 6 the maxText = "000000"
            StringBuilder maxText = new StringBuilder(maxTextLength);
            for(int i = 0; i < maxTextLength; i++) {
                maxText.append("0");
            }
            LayoutInflater layoutInflater = (LayoutInflater) requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            TextView textViewTemp;
            int cellResource = biggerResultDisplay ? R.layout.cell_big : R.layout.cell_small;
            textViewTemp = (TextView) layoutInflater.inflate(cellResource, null);
            textViewTemp.setText(maxText.toString());
            textViewTemp.measure(0, 0); //must call measure!
            int cellWidthDefault = textViewTemp.getMeasuredWidth();
            int cellHeightDefault = squareResultDisplay ? cellWidthDefault : LinearLayout.LayoutParams.WRAP_CONTENT;

            // Set the listview row space.
            float dividerDp = biggerResultDisplay ? 4f : 1f;
            int dividerPx = (int) UIHelper.convertDpToPixel(dividerDp, requireContext());
            listViewResult2.setDividerHeight(dividerPx);
            listViewRowHeaderResult2.setDividerHeight(dividerPx);

            // Set column headers.
            CellUI cellUI = new CellUI(requireContext(), cellWidthDefault, null, cellHeightDefault, null, biggerResultDisplay);
            Grid.setColumnHeaders(cellUI, corner, linearLayoutStaticColumnHeaderOrigin);
            Grid.setColumnHeaders(cellUI, columnHeaders, linearLayoutStaticColumnHeader2);

            // Manually set the row headers width as per the cellWidthDefault.
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linearLayoutStaticRowHeader2.getLayoutParams();
            params.width = cellWidthDefault + dividerPx;
            linearLayoutStaticRowHeader2.setLayoutParams(params);

            // Create and set the adapter.
            GridAdapter adapter = new GridAdapter(requireContext(), rows, cellWidthDefault, null, cellHeightDefault, null, biggerResultDisplay);
            showResultFModM2(adapter, m, r);
            setListViewAdapter(listViewResult2, adapter);

            //
            GridAdapter gridAdapterRowHeaders = new GridAdapter(requireContext(), rowHeaders, cellWidthDefault, null, cellHeightDefault, null, biggerResultDisplay);
            setListViewAdapter(listViewRowHeaderResult2, gridAdapterRowHeaders);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }

    private void setListViewAdapter(ListView listView, ListAdapter listAdapter) {
        listView.setAdapter(listAdapter);
        if (listAdapter == null) {
            textViewExpandResult.setVisibility(View.GONE);
        } else {
            textViewExpandResult.setVisibility(View.VISIBLE);
        }
    }
    //endregion Callback


    //region BUTTON ACTIONS
    private void onButtonRunExampleToggle(ViewGroup container) {
        try {
            String currentExample = buttonRunExampleToggle.getText().toString();
            if (currentExample.equals(getString(R.string.run_example))) {
                setExample1();
            } else if (currentExample.equals(getString(R.string.run_example_1))) {
                setExample2();
            } else if (currentExample.equals(getString(R.string.run_example_2))) {
                setExample3();
            } else if (currentExample.equals(getString(R.string.run_example_3))) {
                setExample4();
            } else if (currentExample.equals(getString(R.string.run_example_4))) {
                setExample5();
            } else {
                setExample1();
            }
            runExample(container);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void runExample(ViewGroup container) {
        switch (lastRun) {
            case RUN:
                onButtonRun(container, true, true);
                break;
            case RUN1:
                onButtonRun1(container, true);
                break;
            case RUN2:
                onButtonRun2(container, true);
                break;
            default:
                onButtonRun(container, true, true);
                break;
        }
    }
    private void setExample1() {
        try {
            String a = "0";
            String b = "8";
            String c = "0";
            String d = "3";
            String e = "3";
            String f = "88";
            String m = "8";
            String r = "0";
            this.editTextA.setText(a);
            this.editTextB.setText(b);
            this.editTextC.setText(c);
            this.editTextD.setText(d);
            this.editTextE.setText(e);
            this.editTextF.setText(f);
            this.buttonM.setText(m);
            this.buttonR.setText(r);
            this.buttonRunExampleToggle.setText(R.string.run_example_1);
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_1));
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void setExample2() {
        try {
            String a = "0";
            String b = "16";
            String c = "0";
            String d = "7";
            String e = "3";
            String f = "113";
            String m = "16";
            String r = "1";
            this.editTextA.setText(a);
            this.editTextB.setText(b);
            this.editTextC.setText(c);
            this.editTextD.setText(d);
            this.editTextE.setText(e);
            this.editTextF.setText(f);
            this.buttonM.setText(m);
            this.buttonR.setText(r);
            this.buttonRunExampleToggle.setText(R.string.run_example_2);
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_2));
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void setExample3() {
        try {
            String a = "0";
            String b = "8";
            String c = "0";
            String d = "7";
            String e = "7";
            String f = "83";
            String m = "8";
            String r = "3";
            this.editTextA.setText(a);
            this.editTextB.setText(b);
            this.editTextC.setText(c);
            this.editTextD.setText(d);
            this.editTextE.setText(e);
            this.editTextF.setText(f);
            this.buttonM.setText(m);
            this.buttonR.setText(r);
            this.buttonRunExampleToggle.setText(R.string.run_example_3);
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_3));
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void setExample4() {
        try {
            String a = "0";
            String b = "16";
            String c = "0";
            String d = "15";
            String e = "11";
            String f = "104";
            String m = "16";
            String r = "8";
            this.editTextA.setText(a);
            this.editTextB.setText(b);
            this.editTextC.setText(c);
            this.editTextD.setText(d);
            this.editTextE.setText(e);
            this.editTextF.setText(f);
            this.buttonM.setText(m);
            this.buttonR.setText(r);
            this.buttonRunExampleToggle.setText(R.string.run_example_4);
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_4));
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void setExample5() {
        try {
            String a = "0";
            String b = "8";
            String c = "0";
            String d = "3";
            String e = "5";
            String f = "14";
            String m = "8";
            String r = "6";
            this.editTextA.setText(a);
            this.editTextB.setText(b);
            this.editTextC.setText(c);
            this.editTextD.setText(d);
            this.editTextE.setText(e);
            this.editTextF.setText(f);
            this.buttonM.setText(m);
            this.buttonR.setText(r);
            this.buttonRunExampleToggle.setText(R.string.run_example_5);
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_5));
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private InputGroup getInputGroupA() {
        return new InputGroup.Builder()
                .setIsCompactInputView(isCompactInputView)
                .setExpandedControls(textViewLabelA, "a", textViewLabelElasticA, editTextA)
                .setCompactControls(textViewLabelCompactA, editTextCompactA)
                .build();
    }
    private InputGroup getInputGroupB() {
        return new InputGroup.Builder()
                .setIsCompactInputView(isCompactInputView)
                .setExpandedControls(textViewLabelB, "b", textViewLabelElasticB, editTextB)
                .setCompactControls(textViewLabelCompactB, editTextCompactB)
                .build();
    }
    private InputGroup getInputGroupC() {
        return new InputGroup.Builder()
                .setIsCompactInputView(isCompactInputView)
                .setExpandedControls(textViewLabelC, "c", textViewLabelElasticC, editTextC)
                .setCompactControls(textViewLabelCompactC, editTextCompactC)
                .build();
    }
    private InputGroup getInputGroupD() {
        return new InputGroup.Builder()
                .setIsCompactInputView(isCompactInputView)
                .setExpandedControls(textViewLabelD, "d", textViewLabelElasticD, editTextD)
                .setCompactControls(textViewLabelCompactD, editTextCompactD)
                .build();
    }
    private InputGroup getInputGroupE() {
        return new InputGroup.Builder()
                .setIsCompactInputView(isCompactInputView)
                .setExpandedControls(textViewLabelE, "e", textViewLabelElasticE, editTextE)
                .setCompactControls(textViewLabelCompactE, editTextCompactE)
                .build();
    }
    private InputGroup getInputGroupF() {
        return new InputGroup.Builder()
                .setIsCompactInputView(isCompactInputView)
                .setExpandedControls(textViewLabelF, "f", textViewLabelElasticF, editTextF)
                .setCompactControls(textViewLabelCompactF, editTextCompactF)
                .build();
    }


    private void onButtonRun(ViewGroup container, boolean skipLabelResult, boolean runFromExample) {
        try {
            lastRun = LastRun.RUN;
            resetResult(skipLabelResult);

            InputGroup inputGroupA = getInputGroupA();
            InputGroup inputGroupB = getInputGroupB();
            InputGroup inputGroupC = getInputGroupC();
            InputGroup inputGroupD = getInputGroupD();
            InputGroup inputGroupE = getInputGroupE();
            InputGroup inputGroupF = getInputGroupF();

            // Check.
            //if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupA)) {
            //    return;
            //}
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupB)) {
                return;
            }
            //if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupC)) {
            //    return;
            //}
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupD)) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupE)) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupF)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextA.getText().toString());
            BigInteger b = new BigInteger(editTextB.getText().toString());
            BigInteger c = new BigInteger(editTextC.getText().toString());
            BigInteger d = new BigInteger(editTextD.getText().toString());
            BigInteger e = new BigInteger(editTextE.getText().toString());
            BigInteger f = new BigInteger(editTextF.getText().toString());

            // Check a != 0
            if (a.compareTo(BigInteger.ZERO) != 0) {
                UIHelper.showCustomToastLight(requireContext(), "The value of 'a' not yet supported, hence ignored");
            }
            // Check b ≠ 0
            if (b.compareTo(BigInteger.ZERO) == 0) {
                UIHelper.showCustomToastLight(requireContext(), "The value of 'b' must be other than 0");
                return;
            }
            // Check c != 0
            if (c.compareTo(BigInteger.ZERO) != 0) {
                UIHelper.showCustomToastLight(requireContext(), "The value of 'c' not yet supported, hence ignored");
            }

            setResultVisibilityFromButtonRun(skipLabelResult);

            // Before action performing.
            if(runFromExample) {
                beforeActionPerforming(buttonRunExampleToggle);
            } else {
                beforeActionPerforming(buttonRun);
            }

            boolean includeTrivialSolutions = UserSettings.getBQFIncludeTrivialSolutions(requireContext());
            boolean includeOnlyPositiveSolutions = UserSettings.getBQFIncludeOnlyPositiveSolutions(requireContext());
            boolean includeOnlyNegativeSolutions = UserSettings.getBQFIncludeOnlyNegativeSolutions(requireContext());

            // Perform the quadratic form
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.BINARY_QUADRATIC_FORM, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            algorithmParameters.setInput3(c);
            algorithmParameters.setInput4(d);
            algorithmParameters.setInput5(e);
            algorithmParameters.setInput6(f);
            algorithmParameters.setIncludeTrivialSolutions(includeTrivialSolutions);
            algorithmParameters.setIncludeOnlyPositiveSolutions(includeOnlyPositiveSolutions);
            algorithmParameters.setIncludeOnlyNegativeSolutions(includeOnlyNegativeSolutions);

            // To prevent flickering for small time calculations do not show the progress dialog.
            if (f.compareTo(BigInteger.valueOf(500L)) <= 0) {
                progressManager.startWork(container, algorithmParameters,false);
            } else {
                progressManager.startWork(container, algorithmParameters);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void onButtonRun1(ViewGroup container, boolean runFromExample) {
        try {
            lastRun = LastRun.RUN1;
            resetResult(false);

            InputGroup inputGroupA = getInputGroupA();
            InputGroup inputGroupB = getInputGroupB();
            InputGroup inputGroupC = getInputGroupC();
            InputGroup inputGroupD = getInputGroupD();
            InputGroup inputGroupE = getInputGroupE();
            InputGroup inputGroupF = getInputGroupF();

            // Check inputs.
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), inputGroupA, ZERO)) {
                return;
            }
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), inputGroupB, ZERO)) {
                return;
            }
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), inputGroupC, ZERO)) {
                return;
            }
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), inputGroupD, ZERO)) {
                return;
            }
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), inputGroupE, ZERO)) {
                return;
            }
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), inputGroupF, ZERO)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextA.getText().toString());
            BigInteger b = new BigInteger(editTextB.getText().toString());
            BigInteger c = new BigInteger(editTextC.getText().toString());
            BigInteger d = new BigInteger(editTextD.getText().toString());
            BigInteger e = new BigInteger(editTextE.getText().toString());
            BigInteger f = new BigInteger(editTextF.getText().toString());

            // Check inputs.
            if (a.compareTo(ZERO) == 0 && b.compareTo(ZERO) == 0 && c.compareTo(ZERO) == 0 && d.compareTo(ZERO) == 0 && e.compareTo(ZERO) == 0) {
                UIHelper.displayTheErrorMessage(requireContext(), "Too many inputs are zero.");
                return;
            }

            setResultVisibilityFromButtonRun1();

            // Before action performing.
            if(runFromExample) {
                beforeActionPerforming(buttonRunExampleToggle);
            } else {
                beforeActionPerforming(buttonRun1);
            }

            // Perform the quadratic form
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.BINARY_QUADRATIC_FORM_1, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            algorithmParameters.setInput3(c);
            algorithmParameters.setInput4(d);
            algorithmParameters.setInput5(e);
            algorithmParameters.setInput6(f);

            // To prevent flickering for small time calculations do not show the progress dialog.
            if (f.compareTo(BigInteger.valueOf(500L)) <= 0) {
                progressManager.startWork(container, algorithmParameters,false);
            } else {
                progressManager.startWork(container, algorithmParameters);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void onButtonRun2(ViewGroup container, boolean runFromExample) {
        try {
            lastRun = LastRun.RUN2;
            resetResult(false);

            // Check.
            InputGroup inputGroupA = getInputGroupA();
            InputGroup inputGroupB = getInputGroupB();
            InputGroup inputGroupC = getInputGroupC();
            InputGroup inputGroupD = getInputGroupD();
            InputGroup inputGroupE = getInputGroupE();
            InputGroup inputGroupF = getInputGroupF();
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupA)) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupB)) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupC)) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupD)) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupE)) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupF)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextA.getText().toString());
            BigInteger b = new BigInteger(editTextB.getText().toString());
            BigInteger c = new BigInteger(editTextC.getText().toString());
            BigInteger d = new BigInteger(editTextD.getText().toString());
            BigInteger e = new BigInteger(editTextE.getText().toString());
            BigInteger f = new BigInteger(editTextF.getText().toString());

            setResultVisibilityFromButtonRun2();

            // Before action performing.
            if(runFromExample) {
                beforeActionPerforming(buttonRunExampleToggle);
            } else {
                beforeActionPerforming(buttonRun2);
            }

            // Perform the quadratic form
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.BINARY_QUADRATIC_FORM_2, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            algorithmParameters.setInput3(c);
            algorithmParameters.setInput4(d);
            algorithmParameters.setInput5(e);
            algorithmParameters.setInput6(f);

            // To prevent flickering for small time calculations do not show the progress dialog.
            if (f.compareTo(BigInteger.valueOf(500L)) <= 0) {
                progressManager.startWork(container, algorithmParameters,false);
            } else {
                progressManager.startWork(container, algorithmParameters);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void calculateFModM() {
        if (this.listViewResult2.getAdapter() != null) {
            GridAdapter gridAdapter = (GridAdapter) this.listViewResult2.getAdapter();

            BigInteger m = null;
            BigInteger r = null;

            String mText = this.buttonM.getText().toString();
            String rText = this.buttonR.getText().toString();

            if (!mText.isEmpty()) {
                m = new BigInteger(mText);
            }
            if (!rText.isEmpty()) {
                r = new BigInteger(rText);
            }

            showResultFModM2(gridAdapter, m, r);
        }
    }
    public static void showResultFModM2(GridAdapter gridAdapter, BigInteger m, BigInteger r) {
        try {
            if (gridAdapter == null) {
                return;
            }

            if (m != null && r == null) {
                // Display f (mod m) and reset if highlighted.
                for(int rIndex = 0; rIndex < gridAdapter.getCount(); rIndex++) {
                    List<Cell> row = gridAdapter.getItem(rIndex);
                    for(int iIndex = 0; iIndex < row.size(); iIndex++) {
                        Cell item = row.get(iIndex);
                        if (!item.getIsHeader()) {
                            String value1 = item.getValue1();
                            BigInteger f = new BigInteger(value1);
                            BigInteger fmodm1 = f.mod(m);
                            String value2 = fmodm1.toString();
                            item.setValue2(value2);
                            item.setValueToDisplay(Cell.ValueToDisplay.VALUE_2);
                            if (item.getIsSelected()) {
                                item.setValueStyle(Cell.ValueStyle.ORANGE);
                            } else {
                                // Reset if highlighted.
                                item.setValueStyle(Cell.ValueStyle.DEFAULT);
                            }
                        }
                    }
                }
            } else if (m != null && r != null) {
                // Display f and highlight f (mod m) = r
                for(int rIndex = 0; rIndex < gridAdapter.getCount(); rIndex++) {
                    List<Cell> row = gridAdapter.getItem(rIndex);
                    for(int iIndex = 0; iIndex < row.size(); iIndex++) {
                        Cell item = row.get(iIndex);
                        if (!item.getIsHeader()) {
                            if (item.getValueToDisplay() != Cell.ValueToDisplay.VALUE_1) {
                                item.setValueToDisplay(Cell.ValueToDisplay.VALUE_1);
                            }
                            String value1 = item.getValue1();
                            BigInteger f = new BigInteger(value1);
                            BigInteger fmodm1 = f.mod(m);
                            if (fmodm1.compareTo(r) == 0) {
                                // Highlight.
                                if (item.getIsSelected()) {
                                    item.setValueStyle(Cell.ValueStyle.ORANGE_STRESSED);
                                } else {
                                    item.setValueStyle(Cell.ValueStyle.YELLOW_STRESSED);
                                }
                            } else {
                                // Reset if highlighted.
                                if (item.getIsSelected()) {
                                    item.setValueStyle(Cell.ValueStyle.ORANGE);
                                } else {
                                    if (item.getQuadrant() == 1) {
                                        item.setValueStyle(Cell.ValueStyle.WHITE);
                                    } else if(item.getQuadrant() == 3){
                                        item.setValueStyle(Cell.ValueStyle.BLUE);
                                    } else {
                                        item.setValueStyle(Cell.ValueStyle.DEFAULT);
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                // Display f and reset if highlighted.
                for(int rIndex = 0; rIndex < gridAdapter.getCount(); rIndex++) {
                    List<Cell> row = gridAdapter.getItem(rIndex);
                    for(int iIndex = 0; iIndex < row.size(); iIndex++) {
                        Cell item = row.get(iIndex);
                        if (!item.getIsHeader()) {
                            if (item.getValueToDisplay() != Cell.ValueToDisplay.VALUE_1) {
                                item.setValueToDisplay(Cell.ValueToDisplay.VALUE_1);
                            }
                            if (item.getIsSelected()) {
                                item.setValueStyle(Cell.ValueStyle.ORANGE);
                            } else {
                                // Reset if highlighted.
                                item.setValueStyle(Cell.ValueStyle.DEFAULT);
                            }
                        }
                    }
                }
            }
            gridAdapter.refresh();
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    //endregion BUTTON ACTIONS


    //region RESULT
    private void beforeActionPerforming(Button button) {
        // Hide the keyboard.
        UIHelper.hideSoftKeyBoard(requireActivity());
        // Clear the focus.
        editTextA.clearFocus();
        editTextB.clearFocus();
        editTextC.clearFocus();
        editTextD.clearFocus();
        editTextE.clearFocus();
        editTextF.clearFocus();
        editTextCompactA.clearFocus();
        editTextCompactB.clearFocus();
        editTextCompactC.clearFocus();
        editTextCompactD.clearFocus();
        editTextCompactE.clearFocus();
        editTextCompactF.clearFocus();
        // Select the last button clicked.
        resetAllAndSelectTheLastButtonClicked(button);
    }
    private void resetAllAndSelectTheLastButtonClicked() {
        resetAllAndSelectTheLastButtonClicked(null);
    }
    private void resetAllAndSelectTheLastButtonClicked(View view) {
        //
        textViewMinusA.setSelected(false);
        textViewPlusA.setSelected(false);
        textViewCopyA.setSelected(false);
        textViewPasteA.setSelected(false);
        textViewClearA.setSelected(false);
        textViewMinusB.setSelected(false);
        textViewPlusB.setSelected(false);
        textViewCopyB.setSelected(false);
        textViewPasteB.setSelected(false);
        textViewClearB.setSelected(false);
        textViewMinusC.setSelected(false);
        textViewPlusC.setSelected(false);
        textViewCopyC.setSelected(false);
        textViewPasteC.setSelected(false);
        textViewClearC.setSelected(false);
        textViewMinusD.setSelected(false);
        textViewPlusD.setSelected(false);
        textViewCopyD.setSelected(false);
        textViewPasteD.setSelected(false);
        textViewClearD.setSelected(false);
        textViewMinusE.setSelected(false);
        textViewPlusE.setSelected(false);
        textViewCopyE.setSelected(false);
        textViewPasteE.setSelected(false);
        textViewClearE.setSelected(false);
        textViewMinusF.setSelected(false);
        textViewPlusF.setSelected(false);
        textViewCopyF.setSelected(false);
        textViewPasteF.setSelected(false);
        textViewClearF.setSelected(false);
        //
        textViewMinusCompactA.setSelected(false);
        textViewPlusCompactA.setSelected(false);
        textViewCopyCompactA.setSelected(false);
        textViewPasteCompactA.setSelected(false);
        textViewClearCompactA.setSelected(false);
        textViewMinusCompactB.setSelected(false);
        textViewPlusCompactB.setSelected(false);
        textViewCopyCompactB.setSelected(false);
        textViewPasteCompactB.setSelected(false);
        textViewClearCompactB.setSelected(false);
        textViewMinusCompactC.setSelected(false);
        textViewPlusCompactC.setSelected(false);
        textViewCopyCompactC.setSelected(false);
        textViewPasteCompactC.setSelected(false);
        textViewClearCompactC.setSelected(false);
        textViewMinusCompactD.setSelected(false);
        textViewPlusCompactD.setSelected(false);
        textViewCopyCompactD.setSelected(false);
        textViewPasteCompactD.setSelected(false);
        textViewClearCompactD.setSelected(false);
        textViewMinusCompactE.setSelected(false);
        textViewPlusCompactE.setSelected(false);
        textViewCopyCompactE.setSelected(false);
        textViewPasteCompactE.setSelected(false);
        textViewClearCompactE.setSelected(false);
        textViewMinusCompactF.setSelected(false);
        textViewPlusCompactF.setSelected(false);
        textViewCopyCompactF.setSelected(false);
        textViewPasteCompactF.setSelected(false);
        textViewClearCompactF.setSelected(false);
        //
        buttonRunExampleToggle.setSelected(false);
        buttonRun.setSelected(false);
        buttonRun1.setSelected(false);
        buttonRun2.setSelected(false);
        //
        textViewExpandResult.setSelected(false);
        textViewCopyResult.setSelected(false);
        textViewClearResult.setSelected(false);
        // Select the last button clicked.
        if (view != null) {
            UIHelper.vibrateOnButtonTap(requireContext());
            view.setSelected(true);
        }
    }
    private void resetResult(boolean skipLabelResult) {
        resetAllAndSelectTheLastButtonClicked();
        //
        if(!skipLabelResult) {
            textViewLabelResult.setText(requireContext().getText(R.string.result));
        }
        this.editTextResult.setText("");
        this.linearLayoutStaticColumnHeader1.removeAllViews();
        this.linearLayoutStaticColumnHeader2.removeAllViews();
        setListViewAdapter(listViewResult1, null);
        setListViewAdapter(listViewResult2, null);
    }
    private void setResultVisibilityFromButtonRun(boolean skipLabelResult) {
        if(!skipLabelResult) {
            textViewLabelResult.setText(requireContext().getText(R.string.result));
        }
        this.linearLayoutFModMContainer.setVisibility(View.GONE);
        this.textViewCopyResult.setVisibility(View.VISIBLE);
        this.textViewClearResult.setVisibility(View.VISIBLE);
        this.linearLayoutResultContainer.setVisibility(View.VISIBLE);
        this.linearLayoutResultGridContainer1.setVisibility(View.GONE);
        this.linearLayoutResultGridContainer2.setVisibility(View.GONE);
    }
    private void setResultVisibilityFromButtonRun1() {
        textViewLabelResult.setText(requireContext().getText(R.string.binary_quadratic_form_result_fxy));
        this.linearLayoutFModMContainer.setVisibility(View.GONE);
        this.textViewCopyResult.setVisibility(View.GONE);
        this.textViewClearResult.setVisibility(View.VISIBLE);
        this.linearLayoutResultContainer.setVisibility(View.GONE);
        this.linearLayoutResultGridContainer1.setVisibility(View.VISIBLE);
        this.linearLayoutResultGridContainer2.setVisibility(View.GONE);
    }
    private void setResultVisibilityFromButtonRun2() {
        textViewLabelResult.setText(requireContext().getText(R.string.binary_quadratic_form_result_fxy_f_mod_m_r));
        this.linearLayoutFModMContainer.setVisibility(View.VISIBLE);
        this.textViewCopyResult.setVisibility(View.GONE);
        this.textViewClearResult.setVisibility(View.VISIBLE);
        this.linearLayoutResultContainer.setVisibility(View.GONE);
        this.linearLayoutResultGridContainer1.setVisibility(View.GONE);
        this.linearLayoutResultGridContainer2.setVisibility(View.VISIBLE);
    }
    //endregion RESULT
}