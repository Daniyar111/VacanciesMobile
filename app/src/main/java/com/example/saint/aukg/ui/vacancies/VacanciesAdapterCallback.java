package com.example.saint.aukg.ui.vacancies;

import com.example.saint.aukg.data.models.VacancyModel;

import java.util.ArrayList;

public interface VacanciesAdapterCallback {

    void onVacancyClicked(ArrayList<VacancyModel> vacancyModels, int position);
}
