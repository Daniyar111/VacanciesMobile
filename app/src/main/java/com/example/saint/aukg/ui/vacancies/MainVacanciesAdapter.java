package com.example.saint.aukg.ui.vacancies;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saint.aukg.R;
import com.example.saint.aukg.data.models.VacancyModel;
import com.example.saint.aukg.ui.details.DetailsActivity;

import java.util.ArrayList;

/**
 * Created by saint on 17.04.2018.
 */

public class MainVacanciesAdapter extends RecyclerView.Adapter<MainVacanciesAdapter.ViewHolder>{

    private ArrayList<VacancyModel> arrayList;
    private VacancyModel vacancyModel;

    public MainVacanciesAdapter(ArrayList<VacancyModel> arrayList){
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_vacancy, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        vacancyModel = arrayList.get(position);

        setTextViewProfession(holder);
        setTextViewDate(holder);
        holder.textViewHeader.setText(vacancyModel.getHeader());
        setTextViewSalary(holder);

        holder.cardViewVacancy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                intent.putParcelableArrayListExtra("vacancy_models", arrayList);
                intent.putExtra("position", position);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView textViewProfession, textViewDate, textViewHeader, textViewSalary;
        private CheckBox checkBoxElected;
        private RelativeLayout relativeWatched;
        private CardView cardViewVacancy;

        public ViewHolder(View view) {
            super(view);
            textViewProfession = view.findViewById(R.id.textViewProfession);
            textViewDate = view.findViewById(R.id.textViewDate);
            textViewHeader = view.findViewById(R.id.textViewHeader);
            textViewSalary = view.findViewById(R.id.textViewSalary);
            checkBoxElected = view.findViewById(R.id.checkBoxElected);
            relativeWatched = view.findViewById(R.id.relativeWatched);
            cardViewVacancy = view.findViewById(R.id.cardViewVacancy);
        }
    }

    private void setTextViewProfession(ViewHolder viewHolder){
        if(vacancyModel.getProfession().equals("Не определено")){
            viewHolder.textViewProfession.setText(vacancyModel.getHeader());
        }
        else{
            viewHolder.textViewProfession.setText(vacancyModel.getProfession());
        }
    }

    private void setTextViewSalary(ViewHolder viewHolder){
        if(vacancyModel.getSalary().equals("")){
            viewHolder.textViewSalary.setText(R.string.treaty);
        }
        else {
            viewHolder.textViewSalary.setText(vacancyModel.getSalary());
        }
    }

    private void setTextViewDate(ViewHolder viewHolder){

        String fullDate = vacancyModel.getData();
        String[] splittingFullDate = fullDate.split(" ");
        String[] splittingTime = splittingFullDate[1].split(":");
        String[] splittingDate = splittingFullDate[0].split("-");
        String newTime = splittingTime[0] + ":" + splittingTime[1];
        String newDate = splittingDate[2] + " " + transformMonth(splittingDate[1]) + " " + splittingDate[0];

        String currentFullDate = newTime + " " + newDate;
        viewHolder.textViewDate.setText(currentFullDate);
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

}
