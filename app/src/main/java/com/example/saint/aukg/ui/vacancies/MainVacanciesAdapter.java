package com.example.saint.aukg.ui.vacancies;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.saint.aukg.R;
import com.example.saint.aukg.models.VacancyModel;

import java.util.ArrayList;
import java.util.List;

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
        holder.textViewProfession.setText(vacancyModel.getProfession());
        holder.textViewDate.setText(vacancyModel.getData());
        holder.textViewBody.setText(vacancyModel.getBody());
        holder.textViewSalary.setText(vacancyModel.getSalary());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewProfession, textViewDate, textViewBody, textViewSalary;
        private CheckBox checkBoxElected;
        private RelativeLayout relativeWatched;

        public ViewHolder(View view) {
            super(view);
            textViewProfession = view.findViewById(R.id.textViewProfession);
            textViewDate = view.findViewById(R.id.textViewDate);
            textViewBody = view.findViewById(R.id.textViewBody);
            textViewSalary = view.findViewById(R.id.textViewSalary);
            checkBoxElected = view.findViewById(R.id.checkBoxElected);
            relativeWatched = view.findViewById(R.id.relativeWatched);
        }
    }
}
