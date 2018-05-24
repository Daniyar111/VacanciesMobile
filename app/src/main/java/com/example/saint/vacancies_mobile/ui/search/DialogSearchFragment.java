package com.example.saint.vacancies_mobile.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.saint.vacancies_mobile.AuApplication;
import com.example.saint.vacancies_mobile.R;
import com.example.saint.vacancies_mobile.data.db.SQLiteHelper;
import com.example.saint.vacancies_mobile.data.models.SearchButtonsModel;
import com.example.saint.vacancies_mobile.ui.BaseDialogFragment;

public class DialogSearchFragment extends BaseDialogFragment implements View.OnClickListener{

    private RadioGroup mRadioGroupRegimeFirst, mRadioGroupRegimeSecond, mRadioGroupSalaryFirst, mRadioGroupSalarySecond;
    private RadioButton mRadioButtonRegimeAny, mRadioButtonSalaryAny, mCheckedRadioButtonRegime, mCheckedRadioButtonSalary;
    private Button mButtonReset, mButtonSearch;
    private SearchButtonsModel mSearchButtonsModel = new SearchButtonsModel();
    private SQLiteHelper mSQLiteHelper;
    private boolean mFlagRegime = true;
    private boolean mFlagSalary = true;

    @Override
    protected int getViewLayout() {
        return R.layout.dialog_fragment_search;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSQLiteHelper = AuApplication.get(view.getContext()).getSQLiteHelper();
        if(mSQLiteHelper != null){
            mSearchButtonsModel = mSQLiteHelper.getRadioButtons();
        }

        removeDialogToolbarAndSetAnimation();
        initialize(view);

        mRadioButtonRegimeAny.setChecked(true);
        mRadioButtonSalaryAny.setChecked(true);
    }

    private void initialize(View view){
        mRadioGroupRegimeFirst = view.findViewById(R.id.radioGroupRegimeFirst);
        mRadioGroupRegimeSecond = view.findViewById(R.id.radioGroupRegimeSecond);
        mRadioGroupSalaryFirst = view.findViewById(R.id.radioGroupSalaryFirst);
        mRadioGroupSalarySecond = view.findViewById(R.id.radioGroupSalarySecond);

        mRadioButtonRegimeAny = view.findViewById(R.id.radioButtonRegimeAny);
        mRadioButtonSalaryAny = view.findViewById(R.id.radioButtonSalaryAny);

        mButtonReset = view.findViewById(R.id.buttonReset);
        mButtonSearch = view.findViewById(R.id.buttonSearch);

        mRadioGroupRegimeFirst.setOnCheckedChangeListener(radioGroupRegimeFirstListener);
        mRadioGroupRegimeSecond.setOnCheckedChangeListener(radioGroupRegimeSecondListener);
        mRadioGroupSalaryFirst.setOnCheckedChangeListener(radioGroupSalaryFirstListener);
        mRadioGroupSalarySecond.setOnCheckedChangeListener(radioGroupSalarySecondListener);

        mButtonReset.setOnClickListener(this);
        mButtonSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.buttonReset:
                resetButtons();
                break;

            case R.id.buttonSearch:
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
                dismiss();
                break;
        }
    }

    private void resetButtons(){
        mRadioGroupRegimeFirst.clearCheck();
        mRadioGroupRegimeSecond.clearCheck();
        mRadioGroupSalaryFirst.clearCheck();
        mRadioGroupSalarySecond.clearCheck();
        mRadioButtonRegimeAny.setChecked(true);
        mRadioButtonSalaryAny.setChecked(true);
    }

    RadioGroup.OnCheckedChangeListener radioGroupRegimeFirstListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            if((group.getCheckedRadioButtonId() != -1) && mFlagRegime){
                mFlagRegime = false;
                mRadioGroupRegimeSecond.clearCheck();
                mCheckedRadioButtonRegime = group.findViewById(checkedId);
            }
            else{
                mFlagRegime = true;
            }
        }
    };

    RadioGroup.OnCheckedChangeListener radioGroupRegimeSecondListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            if((group.getCheckedRadioButtonId() != -1) && mFlagRegime){
                mFlagRegime = false;
                mRadioGroupRegimeFirst.clearCheck();
                mCheckedRadioButtonRegime = group.findViewById(checkedId);
            }
            else{
                mFlagRegime = true;
            }
        }
    };

    RadioGroup.OnCheckedChangeListener radioGroupSalaryFirstListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            if((group.getCheckedRadioButtonId() != -1) && mFlagSalary){
                mFlagSalary = false;
                mRadioGroupSalarySecond.clearCheck();
                mCheckedRadioButtonSalary = group.findViewById(checkedId);
            }
            else {
                mFlagSalary = true;
            }
        }
    };

    RadioGroup.OnCheckedChangeListener radioGroupSalarySecondListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            if((group.getCheckedRadioButtonId() != -1) && mFlagSalary){
                mFlagSalary = false;
                mRadioGroupSalaryFirst.clearCheck();
                mCheckedRadioButtonSalary = group.findViewById(checkedId);
            }
            else {
                mFlagSalary = true;
            }
        }
    };

    @Override
    public void onPause() {
        super.onPause();

        mSearchButtonsModel.setRegime(mCheckedRadioButtonRegime.getText().toString());
        mSearchButtonsModel.setSalary(mCheckedRadioButtonSalary.getText().toString());
        mSQLiteHelper.saveRadioButtons(mSearchButtonsModel);
    }
}
