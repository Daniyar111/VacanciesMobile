package com.example.saint.aukg.ui.vacancies;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.saint.aukg.R;
import com.example.saint.aukg.data.models.VacancyModel;

import java.util.ArrayList;

/**
 * Created by saint on 17.04.2018.
 */

public class MainVacanciesAdapter extends RecyclerView.Adapter<MainVacanciesAdapter.ViewHolder> {

    public ArrayList<VacancyModel> arrayList;

    public MainVacanciesAdapter(ArrayList<VacancyModel> arrayList){
        this.arrayList = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_vacancy, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        VacancyModel vacancyModel = arrayList.get(position);

        if(vacancyModel.getProfession().equals("Не определено")){
            holder.textViewProfession.setText(vacancyModel.getHeader());
        }
        else{
            holder.textViewProfession.setText(vacancyModel.getProfession());
        }

        holder.textViewDate.setText(vacancyModel.getData());
        holder.textViewHeader.setText(vacancyModel.getHeader());

        if(vacancyModel.getSalary().equals("")){
            holder.textViewSalary.setText(R.string.treaty);
        }
        else {
            holder.textViewSalary.setText(vacancyModel.getSalary());
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewProfession, textViewDate, textViewHeader, textViewSalary;
        private CheckBox checkBoxElected;
        private RelativeLayout relativeWatched;

        public ViewHolder(View view) {
            super(view);
            textViewProfession = view.findViewById(R.id.textViewProfession);
            textViewDate = view.findViewById(R.id.textViewDate);
            textViewHeader = view.findViewById(R.id.textViewHeader);
            textViewSalary = view.findViewById(R.id.textViewSalary);
            checkBoxElected = view.findViewById(R.id.checkBoxElected);
            relativeWatched = view.findViewById(R.id.relativeWatched);
        }
    }
}
