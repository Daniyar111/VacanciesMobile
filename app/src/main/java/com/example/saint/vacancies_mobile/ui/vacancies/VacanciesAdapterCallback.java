package com.example.saint.vacancies_mobile.ui.vacancies;

import com.example.saint.vacancies_mobile.data.models.VacancyModel;

import java.util.ArrayList;

public interface VacanciesAdapterCallback {

    void onVacancyClicked(ArrayList<VacancyModel> vacancyModels, int position);
}
