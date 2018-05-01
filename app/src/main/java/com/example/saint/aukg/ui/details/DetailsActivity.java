package com.example.saint.aukg.ui.details;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saint.aukg.R;
import com.example.saint.aukg.data.models.VacancyModel;
import com.example.saint.aukg.ui.BaseActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class DetailsActivity extends BaseActivity implements View.OnClickListener {

    private TextView textViewHeader, textViewProfile, textViewDate, textViewSalary, textViewSiteAddress, textViewTelephone, textViewBody;
    private LinearLayout buttonPrevious, buttonNext;
    private RelativeLayout buttonCall;
    private FrameLayout frameLayoutPrevious, frameLayoutNext;
    private TextView textViewPrevious, textViewNext;
    private VacancyModel vacancyModel;
    private ArrayList<VacancyModel> vacancyModels;
    private DialogTelephoneFragment dialogTelephoneFragment;
    private int position;
    private ArrayList<String> telephones;
    private String telephone, vacancyTelephone;


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

        frameLayoutPrevious = findViewById(R.id.frameLayoutPrevious);
        frameLayoutNext = findViewById(R.id.frameLayoutNext);
        textViewPrevious = findViewById(R.id.textViewPrevious);
        textViewNext = findViewById(R.id.textViewNext);

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
                pushTelephone();
                break;
        }
    }

    private void showButtons(){
        if(position == 0){
            frameLayoutPrevious.setVisibility(View.INVISIBLE);
            textViewPrevious.setVisibility(View.INVISIBLE);
            buttonPrevious.setClickable(false);
            return;
        }
        if(position == vacancyModels.size() - 1){
            frameLayoutNext.setVisibility(View.INVISIBLE);
            textViewNext.setVisibility(View.INVISIBLE);
            buttonNext.setClickable(false);
        }
        else{
            frameLayoutPrevious.setVisibility(View.VISIBLE);
            textViewPrevious.setVisibility(View.VISIBLE);
            frameLayoutNext.setVisibility(View.VISIBLE);
            textViewNext.setVisibility(View.VISIBLE);
            buttonPrevious.setClickable(true);
            buttonNext.setClickable(true);
        }

    }

    private void updateData(){
        vacancyModel = vacancyModels.get(position);
        textViewHeader.setText(vacancyModel.getHeader());
        textViewProfile.setText(vacancyModel.getProfile());
        setTextViewDate();
        setTextViewSalary();
        textViewSiteAddress.setText(vacancyModel.getSiteAddress());
        setTextViewTelephone();
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

    private void setTextViewSalary(){
        if(vacancyModel.getSalary().equals("")){
            textViewSalary.setText(R.string.treaty);
        }
        else {
            textViewSalary.setText(vacancyModel.getSalary());
        }
    }

    private void setTextViewDate(){

        String fullDate = vacancyModel.getData();
        String[] splittingFullDate = fullDate.split(" ");
        String[] splittingTime = splittingFullDate[1].split(":");
        String[] splittingDate = splittingFullDate[0].split("-");
        String newTime = splittingTime[0] + ":" + splittingTime[1];
        String newDate = splittingDate[2] + " " + transformMonth(splittingDate[1]) + " " + splittingDate[0];

        String currentFullDate = newTime + " " + newDate;
        textViewDate.setText(currentFullDate);
    }

    private String transformMonth(String month){

        String normalMonth = "";
        switch (month){
            case "01":
                normalMonth = "Янв";
                break;
            case "02":
                normalMonth = "Фев";
                break;
            case "03":
                normalMonth = "Мар";
                break;
            case "04":
                normalMonth = "Апр";
                break;
            case "05":
                normalMonth = "Май";
                break;
            case "06":
                normalMonth = "Июн";
                break;
            case "07":
                normalMonth = "Июл";
                break;
            case "08":
                normalMonth = "Авг";
                break;
            case "09":
                normalMonth = "Сен";
                break;
            case "10":
                normalMonth = "Окт";
                break;
            case "11":
                normalMonth = "Ноя";
                break;
            case "12":
                normalMonth = "Дек";
                break;
        }
        return normalMonth;
    }

    private void setTextViewTelephone(){
        vacancyTelephone = vacancyModel.getTelephone().trim();
        if(vacancyTelephone.equals("")){
            textViewTelephone.setText(R.string.undefined);
            buttonCall.setVisibility(View.INVISIBLE);
        }
        else if(vacancyTelephone.contains("@") && !vacancyTelephone.contains(" ") && !vacancyTelephone.contains(";")){
            buttonCall.setVisibility(View.INVISIBLE);
            textViewTelephone.setText(vacancyModel.getTelephone());
        }
        else{
            textViewTelephone.setText(vacancyModel.getTelephone());
            buttonCall.setVisibility(View.VISIBLE);
        }
    }

    public void pushTelephone(){

        telephone = vacancyModel.getTelephone();
        telephones = new ArrayList<>();

        // if (0551440114; 0708440114) || (0551440114; 0708440114; 0778440114)
        // if (0551440114; 0708440114 dani.fdgfwj312232@gmail.com)

        if(telephone.contains("; ")){
            telephones = getListTelephone();
            dialogTelephoneFragment = new DialogTelephoneFragment();

            Bundle bundle = new Bundle();
            bundle.putStringArrayList("telephones", telephones);
            dialogTelephoneFragment.setArguments(bundle);
            dialogTelephoneFragment.show(getSupportFragmentManager(), "telephone");
        }

        // if (0551440114 dani.changylov42123@gmail.com) (dani.changylov431@gmail.com 0551440114)
        else if(telephone.contains("@") && telephone.contains(" ") && !telephone.contains(";")){
            telephone = divideTelAndMail();
            parseIntent(telephone);
        }
        else{
            buttonCall.setVisibility(View.VISIBLE);
            parseIntent(telephone);
        }
    }

    private void parseIntent(String tel){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + tel));
        startActivity(intent);
    }

    private ArrayList<String> getListTelephone(){

        // if (0551440114; 0708440114) || (0551440114; 0708440114; 0778440114)
        // if (0551440114; 0708440114 dani.fdgfwj312232@gmail.com; 0551423232)

        String[] splittingTelephone = telephone.split("; ");
        String[] splittingTelAndMail = new String[]{};
        ArrayList<String> telephonesList = new ArrayList<>();

        for (int i = 0; i < splittingTelephone.length; i++) {
            if(splittingTelephone[i].contains(" ")){
                splittingTelAndMail = splittingTelephone[i].split(" ");
            }
            else if(!splittingTelephone[i].contains("@")){
                telephonesList.add(splittingTelephone[i]);
            }
        }
        for (int i = 0; i < splittingTelAndMail.length; i++) {
            if(!splittingTelAndMail[i].contains("@")){
                telephonesList.add(splittingTelAndMail[i]);
            }
        }
        return telephonesList;
    }

    private String divideTelAndMail(){

        // if (0551440114 dani.changylov42123@gmail.com) (dani.changylov431@gmail.com 0551440114)
        String normalTelephone = "";
        String[] splitMail = telephone.split(" ");

        for (int i = 0; i < splitMail.length; i++) {
            if(!splitMail[i].contains("@")){
                normalTelephone = splitMail[i];
            }
        }
        return normalTelephone;
    }

}
