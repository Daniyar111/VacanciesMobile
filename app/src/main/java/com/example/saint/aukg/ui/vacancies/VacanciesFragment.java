package com.example.saint.aukg.ui.vacancies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.saint.aukg.AuApplication;
import com.example.saint.aukg.R;
import com.example.saint.aukg.data.RetrofitService;
import com.example.saint.aukg.models.VacanciesList;
import com.example.saint.aukg.models.VacancyModel;
import com.example.saint.aukg.ui.BaseFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by saint on 15.04.2018.
 */

public class VacanciesFragment extends BaseFragment {

    private RetrofitService service;
    private RecyclerView recyclerView;
    private MainVacanciesAdapter adapter;
    private VacancyModel vacancyModel;
    private VacanciesList vacanciesList;

    @Override
    protected int getViewLayout() {
        return R.layout.fragment_vacancies;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void getVacancies(final View view){

        service = AuApplication.get(getContext()).getService();

        service.postVacancies("au", "get_all_vacancies", "20", "20")
                .enqueue(new Callback<VacancyModel>() {
                    @Override
                    public void onResponse(@NonNull Call<VacancyModel> call, @NonNull Response<VacancyModel> response) {

                        if(response.isSuccessful() && response.body() != null){

                            // i don't know how to response vacancies list

                            vacancyModel = response.body();

                            recyclerView = view.findViewById(R.id.recyclerView);

                            adapter = new MainVacanciesAdapter(vacanciesList.getList());
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<VacancyModel> call, @NonNull Throwable t) {

                    }
                });
    }
}
