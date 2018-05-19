package com.example.saint.aukg.ui.vacancies;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saint.aukg.AuApplication;
import com.example.saint.aukg.R;
import com.example.saint.aukg.data.db.SQLiteHelper;
import com.example.saint.aukg.data.models.VacancyModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Created by saint on 17.04.2018.
 */

public class MainVacanciesAdapter extends RecyclerView.Adapter<MainVacanciesAdapter.ViewHolder>{

    private ArrayList<VacancyModel> mArrayList;
    private VacancyModel mVacancyModel;
    private VacanciesAdapterCallback mCallback;
    private SQLiteHelper mSQLiteHelper;
    private Context mContext;
    private ArrayList<VacancyModel> mVacancyModels;

    private Set<Integer> mCheckBoxSet = new HashSet<>();
    private Set<Integer> mWatchedSet = new HashSet<>();

    public MainVacanciesAdapter(Context context, ArrayList<VacancyModel> arrayList, VacanciesAdapterCallback callback){
        mContext = context;
        mArrayList = arrayList;
        mCallback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_vacancy, parent, false);
        mSQLiteHelper = AuApplication.get(parent.getContext()).getSQLiteHelper();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        mVacancyModel = mArrayList.get(position);

        setTextViewProfession(holder);
        holder.mTextViewDate.setText(formatTextViewDate(mVacancyModel.getData()));
        holder.mTextViewHeader.setText(mVacancyModel.getHeader());
        setTextViewSalary(holder);

        holder.mCheckBoxElected.setOnCheckedChangeListener(null);

        holder.mCheckBoxElected.setChecked(mCheckBoxSet.contains(position));
        holder.mCheckBoxElected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mCheckBoxSet.add(position);
                } else {
                    mCheckBoxSet.remove(position);
                }
            }
        });

        if(mSQLiteHelper.getElectedVacancy(mVacancyModel.getPid())){

            holder.mCheckBoxElected.setChecked(true);
        }

        holder.mCheckBoxElected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.mCheckBoxElected.isChecked()){
                    mSQLiteHelper.saveElectedVacancy(mVacancyModel.getPid(),true);
                    Toast.makeText(mContext, "Added to db " + mVacancyModel.getProfession(), Toast.LENGTH_LONG).show();
                }
                else{
                    mSQLiteHelper.deleteElectedVacancy(mVacancyModel.getPid());
                    Toast.makeText(mContext, "Deleted from db " + mVacancyModel.getProfession(), Toast.LENGTH_LONG).show();
                }
            }
        });

        holder.mRelativeWatched.setOnFocusChangeListener(null);

        holder.mRelativeWatched.setVisibility(mWatchedSet.contains(position) ? View.VISIBLE : View.GONE);
        if(mSQLiteHelper.getWatchedVacancy(mVacancyModel.getPid())){

            holder.mRelativeWatched.setVisibility(View.VISIBLE);
            Log.d("CAME", "CAME");
            Toast.makeText(mContext, "adapter is " + mVacancyModel.getProfession(), Toast.LENGTH_LONG).show();
        }

        holder.mRelativeWatched.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    mWatchedSet.add(position);
                }
                else{
                    mWatchedSet.remove(position);
                }
            }
        });

        holder.mCardViewVacancy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCallback.onVacancyClicked(mArrayList, position);

                new Thread(new Runnable() {
                    public void run() {
                        mSQLiteHelper.saveWatchedVacancy(mVacancyModel.getPid(), true);
                    }
                }).start();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewProfession, mTextViewDate, mTextViewHeader, mTextViewSalary;
        private CheckBox mCheckBoxElected;
        private RelativeLayout mRelativeWatched;
        private CardView mCardViewVacancy;

        public ViewHolder(View view) {
            super(view);
            mTextViewProfession = view.findViewById(R.id.textViewProfession);
            mTextViewDate = view.findViewById(R.id.textViewDate);
            mTextViewHeader = view.findViewById(R.id.textViewHeader);
            mTextViewSalary = view.findViewById(R.id.textViewSalary);
            mCheckBoxElected = view.findViewById(R.id.checkBoxElected);
            mRelativeWatched = view.findViewById(R.id.relativeWatched);
            mCardViewVacancy = view.findViewById(R.id.cardViewVacancy);
        }
    }

    private void setTextViewProfession(ViewHolder viewHolder){
        if(mVacancyModel.getProfession().equals("Не определено")){
            viewHolder.mTextViewProfession.setText(mVacancyModel.getHeader());
        }
        else{
            viewHolder.mTextViewProfession.setText(mVacancyModel.getProfession());
        }
    }

    private void setTextViewSalary(ViewHolder viewHolder){
        if(mVacancyModel.getSalary().equals("")){
            viewHolder.mTextViewSalary.setText(R.string.treaty);
        }
        else {
            viewHolder.mTextViewSalary.setText(mVacancyModel.getSalary());
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

}
