package com.example.saint.vacancies_mobile.ui;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.saint.vacancies_mobile.StartApplication;
import com.example.saint.vacancies_mobile.R;
import com.example.saint.vacancies_mobile.data.db.SQLiteHelper;
import com.example.saint.vacancies_mobile.data.models.VacancyModel;
import com.example.saint.vacancies_mobile.ui.vacancies.VacanciesAdapterCallback;
import com.example.saint.vacancies_mobile.utils.AndroidUtils;

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
    private boolean mIsWatched;
    private Set<Integer> mCheckBoxSet = new HashSet<>();
    private Set<Integer> mWatchedSet = new HashSet<>();

    public MainVacanciesAdapter(Context context, ArrayList<VacancyModel> arrayList, VacanciesAdapterCallback callback, boolean isWatched){
        mContext = context;
        mArrayList = arrayList;
        mCallback = callback;
        mIsWatched = isWatched;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_vacancy, parent, false);
        mSQLiteHelper = StartApplication.get(parent.getContext()).getSQLiteHelper();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        mVacancyModel = mArrayList.get(position);

        updateData(holder, mVacancyModel);

        checkBoxController(holder, position);

        holder.mCheckBoxElected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.mCheckBoxElected.isChecked()){
                    mSQLiteHelper.saveElectedVacancy(mArrayList.get(position),true);
                    AndroidUtils.showLongToast(mContext, mContext.getResources().getString(R.string.added_to_elected));
                }
                else{
                    mSQLiteHelper.deleteElectedVacancy(mArrayList.get(position).getPid());
                    AndroidUtils.showLongToast(mContext, mContext.getResources().getString(R.string.deleted_from_elected));
                }
            }
        });

        if(mIsWatched){
            relativeWatchedController(holder, position);
        }

        holder.mCardViewVacancy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onVacancyClicked(mArrayList, position);
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

    private void updateData(ViewHolder holder, VacancyModel vacancyModel){
        setTextViewProfession(holder);
        holder.mTextViewDate.setText(formatTextViewDate(vacancyModel.getData()));
        holder.mTextViewHeader.setText(vacancyModel.getHeader());
        setTextViewSalary(holder);
    }

    private void checkBoxController(ViewHolder holder, final int position){
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
    }

    private void relativeWatchedController(ViewHolder holder, final int position){
        holder.mRelativeWatched.setOnFocusChangeListener(null);
        holder.mRelativeWatched.setVisibility(mWatchedSet.contains(position) ? View.VISIBLE : View.GONE);
        if(mSQLiteHelper.isWatchedVacancy(mArrayList.get(position).getPid())){

            holder.mRelativeWatched.setVisibility(View.VISIBLE);
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
