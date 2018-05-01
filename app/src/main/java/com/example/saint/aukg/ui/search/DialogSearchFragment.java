package com.example.saint.aukg.ui.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.saint.aukg.R;
import com.example.saint.aukg.ui.BaseDialogFragment;

public class DialogSearchFragment extends BaseDialogFragment implements View.OnClickListener{

    private RadioGroup radioGroupRegimeFirst, radioGroupRegimeSecond, radioGroupSalaryFirst, radioGroupSalarySecond;
    private RadioButton radioButtonRegimeAny, radioButtonFull, radioButtonFlexible, radioButtonRemotely, radioButtonNight, radioButtonSalaryAny, radioButtonFiveMore, radioButtonTenMore, radioButtonThirtyMore;
    private Button buttonReset, buttonSearch;
    private boolean flagRegime = true;
    private boolean flagSalary = true;

    @Override
    protected int getViewLayout() {
        return R.layout.dialog_fragment_search;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        removeDialogToolbar();
        initialize(view);
    }

    private void initialize(View view){
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
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.buttonReset:

                resetButtons();
                break;

            case R.id.buttonSearch:

                break;
        }

    }

    private void resetButtons(){
        radioGroupRegimeFirst.clearCheck();
        radioGroupRegimeSecond.clearCheck();
        radioGroupSalaryFirst.clearCheck();
        radioGroupSalarySecond.clearCheck();
        radioButtonRegimeAny.setChecked(true);
        radioButtonSalaryAny.setChecked(true);
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
