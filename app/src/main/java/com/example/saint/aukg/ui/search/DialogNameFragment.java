package com.example.saint.aukg.ui.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.saint.aukg.R;
import com.example.saint.aukg.ui.BaseFragment;

public class DialogNameFragment extends DialogFragment implements View.OnClickListener{

    private RadioGroup radioGroupRegimeFirst, radioGroupRegimeSecond, radioGroupSalaryFirst, radioGroupSalarySecond;
    private RadioButton radioButtonRegimeAny, radioButtonFull, radioButtonFlexible, radioButtonRemotely, radioButtonNight, radioButtonSalaryAny, radioButtonFiveMore, radioButtonTenMore, radioButtonThirtyMore;
    private Button buttonReset, buttonSearch;
    private boolean flagRegime = true;
    private boolean flagSalary = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dialog, container, false);

        if(getDialog().getWindow() != null){
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }

        radioGroupRegimeFirst = view.findViewById(R.id.radioGroupRegimeFirst);
        radioGroupRegimeSecond = view.findViewById(R.id.radioGroupRegimeSecond);
        radioGroupSalaryFirst = view.findViewById(R.id.radioGroupSalaryFirst);
        radioGroupSalarySecond = view.findViewById(R.id.radioGroupSalarySecond);

        radioButtonRegimeAny = view.findViewById(R.id.radioButtonRegimeAny);
        radioButtonFull = view.findViewById(R.id.radioButtonFull);
        radioButtonFlexible = view.findViewById(R.id.radioButtonFlexible);
        radioButtonRemotely = view.findViewById(R.id.radioButtonRemotely);
        radioButtonNight = view.findViewById(R.id.radioButtonNight);
        radioButtonSalaryAny = view.findViewById(R.id.radioButtonSalaryAny);
        radioButtonFiveMore = view.findViewById(R.id.radioButtonFiveMore);
        radioButtonTenMore = view.findViewById(R.id.radioButtonTenMore);
        radioButtonThirtyMore = view.findViewById(R.id.radioButtonThirtyMore);

        buttonReset = view.findViewById(R.id.buttonReset);
        buttonSearch = view.findViewById(R.id.buttonSearch);

        radioButtonRegimeAny.setChecked(true);
        radioButtonSalaryAny.setChecked(true);

        radioGroupRegimeFirst.setOnCheckedChangeListener(radioGroupRegimeFirstListener);
        radioGroupRegimeSecond.setOnCheckedChangeListener(radioGroupRegimeSecondListener);
        radioGroupSalaryFirst.setOnCheckedChangeListener(radioGroupSalaryFirstListener);
        radioGroupSalarySecond.setOnCheckedChangeListener(radioGroupSalarySecondListener);

        buttonReset.setOnClickListener(this);
        buttonSearch.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.buttonReset:

                radioGroupRegimeFirst.clearCheck();
                radioGroupRegimeSecond.clearCheck();
                radioGroupSalaryFirst.clearCheck();
                radioGroupSalarySecond.clearCheck();
                radioButtonRegimeAny.setChecked(true);
                radioButtonSalaryAny.setChecked(true);
                break;

            case R.id.buttonSearch:

                break;
        }

    }

    RadioGroup.OnCheckedChangeListener radioGroupRegimeFirstListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            if((group.getCheckedRadioButtonId() != -1) && flagRegime){
                flagRegime = false;
                radioGroupRegimeSecond.clearCheck();
            }
            else{
                flagRegime = true;
            }
        }
    };

    RadioGroup.OnCheckedChangeListener radioGroupRegimeSecondListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            if((group.getCheckedRadioButtonId() != -1) && flagRegime){
                flagRegime = false;
                radioGroupRegimeFirst.clearCheck();
            }
            else{
                flagRegime = true;
            }
        }
    };

    RadioGroup.OnCheckedChangeListener radioGroupSalaryFirstListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            if((group.getCheckedRadioButtonId() != -1) && flagSalary){
                flagSalary = false;
                radioGroupSalarySecond.clearCheck();
            }
            else {
                flagSalary = true;
            }
        }
    };

    RadioGroup.OnCheckedChangeListener radioGroupSalarySecondListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            if((group.getCheckedRadioButtonId() != -1) && flagSalary){
                flagSalary = false;
                radioGroupSalaryFirst.clearCheck();
            }
            else {
                flagSalary = true;
            }
        }
    };
}
