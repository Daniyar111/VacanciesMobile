package com.example.saint.aukg.ui.details;

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

import com.example.saint.aukg.AuApplication;
import com.example.saint.aukg.R;
import com.example.saint.aukg.data.db.SQLiteHelper;
import com.example.saint.aukg.data.models.VacancyModel;
import com.example.saint.aukg.ui.BaseActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DetailsActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTextViewHeader, mTextViewProfile, mTextViewDate, mTextViewSalary, mTextViewSiteAddress, mTextViewTelephone, mTextViewBody;
    private LinearLayout mButtonPrevious, mButtonNext;
    private RelativeLayout mButtonCall;
    private FrameLayout mFrameLayoutPrevious, mFrameLayoutNext;
    private TextView mTextViewPrevious, mTextViewNext;
    private VacancyModel mVacancyModel;
    private ArrayList<VacancyModel> mVacancyModels;
    private DialogTelephoneFragment mDialogTelephoneFragment;
    private int mPosition;
    private ArrayList<String> mTelephones;
    private String mTelephone, mVacancyTelephone;


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

        initialize();

        if(getIntent() != null){

            mVacancyModels = getIntent().getParcelableArrayListExtra("vacancy_models");
            mPosition = getIntent().getIntExtra("position", 0);

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

    private void initialize(){
        mTextViewHeader = findViewById(R.id.textViewHeader);
        mTextViewProfile = findViewById(R.id.textViewProfile);
        mTextViewDate = findViewById(R.id.textViewDate);
        mTextViewSalary = findViewById(R.id.textViewSalary);
        mTextViewSiteAddress = findViewById(R.id.textViewSiteAddress);
        mTextViewTelephone = findViewById(R.id.textViewTelephone);
        mTextViewBody = findViewById(R.id.textViewBody);

        mFrameLayoutPrevious = findViewById(R.id.frameLayoutPrevious);
        mFrameLayoutNext = findViewById(R.id.frameLayoutNext);
        mTextViewPrevious = findViewById(R.id.textViewPrevious);
        mTextViewNext = findViewById(R.id.textViewNext);

        mButtonPrevious = findViewById(R.id.buttonPrevious);
        mButtonNext = findViewById(R.id.buttonNext);
        mButtonCall = findViewById(R.id.buttonCall);

        mButtonPrevious.setOnClickListener(this);
        mButtonNext.setOnClickListener(this);
        mButtonCall.setOnClickListener(this);
    }

    private void showButtons(){
        if(mPosition == 0){
            mFrameLayoutPrevious.setVisibility(View.INVISIBLE);
            mTextViewPrevious.setVisibility(View.INVISIBLE);
            mButtonPrevious.setClickable(false);
            return;
        }
        if(mPosition == mVacancyModels.size() - 1){
            mFrameLayoutNext.setVisibility(View.INVISIBLE);
            mTextViewNext.setVisibility(View.INVISIBLE);
            mButtonNext.setClickable(false);
        }
        else{
            mFrameLayoutPrevious.setVisibility(View.VISIBLE);
            mTextViewPrevious.setVisibility(View.VISIBLE);
            mFrameLayoutNext.setVisibility(View.VISIBLE);
            mTextViewNext.setVisibility(View.VISIBLE);
            mButtonPrevious.setClickable(true);
            mButtonNext.setClickable(true);
        }

    }

    private void updateData(){
        mVacancyModel = mVacancyModels.get(mPosition);
        mTextViewHeader.setText(mVacancyModel.getHeader());
        mTextViewProfile.setText(mVacancyModel.getProfile());
        mTextViewDate.setText(formatTextViewDate(mVacancyModel.getData()));
        setTextViewSalary();
        mTextViewSiteAddress.setText(mVacancyModel.getSiteAddress());
        setTextViewTelephone();
        mTextViewBody.setText(mVacancyModel.getBody());
    }

    private void previousVacancy(){
        mPosition--;
        showButtons();
        updateData();
    }

    private void nextVacancy(){
        mPosition++;
        showButtons();
        updateData();
    }

    private void setTextViewSalary(){
        if(mVacancyModel.getSalary().equals("")){
            mTextViewSalary.setText(R.string.treaty);
        }
        else {
            mTextViewSalary.setText(mVacancyModel.getSalary());
        }
    }

    private String formatTextViewDate(String textDate) {
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "HH:mm dd MMM yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.getDefault());
        Date date;
        String output = null;
        try {
            date = inputFormat.parse(textDate);
            output = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return output;
    }

    private void setTextViewTelephone(){
        mVacancyTelephone = mVacancyModel.getTelephone().trim();
        if(mVacancyTelephone.equals("")){
            mTextViewTelephone.setText(R.string.undefined);
            mButtonCall.setVisibility(View.INVISIBLE);
        }
        else if(mVacancyTelephone.contains("@") && !mVacancyTelephone.contains(" ") && !mVacancyTelephone.contains(";")){
            mButtonCall.setVisibility(View.INVISIBLE);
            mTextViewTelephone.setText(mVacancyModel.getTelephone());
        }
        else{
            mTextViewTelephone.setText(mVacancyModel.getTelephone());
            mButtonCall.setVisibility(View.VISIBLE);
        }
    }

    public void pushTelephone(){

        mTelephone = mVacancyModel.getTelephone();
        mTelephones = new ArrayList<>();

        // if (0551440114; 0708440114) || (0551440114; 0708440114; 0778440114)
        // if (0551440114; 0708440114 dani.fdgfwj312232@gmail.com)

        if(mTelephone.contains("; ")){
            mTelephones = getListTelephone();
            mDialogTelephoneFragment = new DialogTelephoneFragment();

            Bundle bundle = new Bundle();
            bundle.putStringArrayList("telephones", mTelephones);
            mDialogTelephoneFragment.setArguments(bundle);
            mDialogTelephoneFragment.show(getSupportFragmentManager(), "telephone");
        }

        // if (0551440114 dani.changylov42123@gmail.com) (dani.changylov431@gmail.com 0551440114)
        else if(mTelephone.contains("@") && mTelephone.contains(" ") && !mTelephone.contains(";")){
            mTelephone = divideTelAndMail();
            parseIntent(mTelephone);
        }
        else{
            mButtonCall.setVisibility(View.VISIBLE);
            parseIntent(mTelephone);
        }
    }

    private void parseIntent(String tel){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + tel));
        startActivity(intent);
    }

    private ArrayList<String> getListTelephone(){

        // if (0551440114; 0708440114) || (0551440114; 0708440114; 0778440114)
        // if (0551440114; 0708440114 dani.fdgfwj312232@gmail.com)

        String[] splittingTelephone = mTelephone.split("; ");
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
        String[] splitMail = mTelephone.split(" ");

        for (int i = 0; i < splitMail.length; i++) {
            if(!splitMail[i].contains("@")){
                normalTelephone = splitMail[i];
            }
        }
        return normalTelephone;
    }

}
