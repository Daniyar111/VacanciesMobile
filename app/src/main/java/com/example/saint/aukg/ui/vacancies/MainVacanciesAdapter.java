package com.example.saint.aukg.ui.vacancies;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.saint.aukg.R;

import java.util.ArrayList;

/**
 * Created by saint on 17.04.2018.
 */

public class MainVacanciesAdapter extends RecyclerView.Adapter<MainVacanciesAdapter.ViewHolder> {

    public ArrayList<String> arrayList;

    public MainVacanciesAdapter(ArrayList<String> arrayList){
        this.arrayList = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_vacancy, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewCard.setText(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewCard;
        public ViewHolder(View view) {
            super(view);
            textViewCard = view.findViewById(R.id.textViewCard);
        }
    }
}
