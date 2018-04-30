package com.example.saint.aukg.ui.details;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saint.aukg.R;
import com.example.saint.aukg.data.models.VacancyModel;
import com.example.saint.aukg.ui.BaseActivity;

import java.util.ArrayList;

public class DetailsActivity extends BaseActivity implements View.OnClickListener {

    private TextView textViewHeader, textViewProfile, textViewDate, textViewSalary, textViewSiteAddress, textViewTelephone, textViewBody;
    private LinearLayout buttonPrevious, buttonNext;
    private RelativeLayout buttonCall;
    private VacancyModel vacancyModel;
    private ArrayList<VacancyModel> vacancyModels;
    private int position;

    @Override
    protected int getViewLayout() {
        return R.layout.activity_details;
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewLayout());

        getToolbar(getResources().getString(R.string.vacancies), true);

        textViewHeader = findViewById(R.id.textViewHeader);
        textViewProfile = findViewById(R.id.textViewProfile);
        textViewDate = findViewById(R.id.textViewDate);
        textViewSalary = findViewById(R.id.textViewSalary);
        textViewSiteAddress = findViewById(R.id.textViewSiteAddress);
        textViewTelephone = findViewById(R.id.textViewTelephone);
        textViewBody = findViewById(R.id.textViewBody);

        buttonPrevious = findViewById(R.id.buttonPrevious);
        buttonNext = findViewById(R.id.buttonNext);
        buttonCall = findViewById(R.id.buttonCall);

        buttonPrevious.setOnClickListener(this);
        buttonNext.setOnClickListener(this);
        buttonCall.setOnClickListener(this);

        if(getIntent() != null){

            vacancyModels = getIntent().getParcelableArrayListExtra("vacancy_models");
            position = getIntent().getIntExtra("position", 0);
            updateData();
        }

        showButtons();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.buttonPrevious:

                previousVacancy();

                break;
            case R.id.buttonNext:

                nextVacancy();

                break;
            case R.id.buttonCall:


                break;
        }

    }

    @SuppressLint("ResourceAsColor")
    private void showButtons(){
        if(position == 0){
//            buttonPrevious.setVisibility(View.INVISIBLE);
            buttonPrevious.setClickable(false);
            Toast.makeText(getApplicationContext(), "position is 0", Toast.LENGTH_LONG).show();
            return;
        }
        if(position == vacancyModels.size() - 1){
//            buttonNext.setVisibility(View.INVISIBLE);
            buttonNext.setClickable(false);
            Toast.makeText(getApplicationContext(), "position is -1", Toast.LENGTH_LONG).show();
        }
        else{
            buttonPrevious.setVisibility(View.VISIBLE);
            buttonNext.setVisibility(View.VISIBLE);
            buttonPrevious.setClickable(true);
            buttonNext.setClickable(true);
            Toast.makeText(getApplicationContext(), "position is neither 0 nor -1", Toast.LENGTH_LONG).show();
        }

    }

    private void updateData(){
        vacancyModel = vacancyModels.get(position);
        textViewHeader.setText(vacancyModel.getHeader());
        textViewProfile.setText(vacancyModel.getProfile());
        textViewDate.setText(vacancyModel.getData());
        textViewSalary.setText(vacancyModel.getSalary());
        textViewSiteAddress.setText(vacancyModel.getSiteAddress());
        textViewTelephone.setText(vacancyModel.getTelephone());
        textViewBody.setText(vacancyModel.getBody());
    }

    private void previousVacancy(){
        position--;
        showButtons();
        updateData();
    }

    private void nextVacancy(){
        position++;
        showButtons();
        updateData();
    }
}
