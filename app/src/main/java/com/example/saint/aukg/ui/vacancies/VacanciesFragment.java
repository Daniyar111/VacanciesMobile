package com.example.saint.aukg.ui.vacancies;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.saint.aukg.AuApplication;
import com.example.saint.aukg.R;
import com.example.saint.aukg.data.RetrofitService;
import com.example.saint.aukg.data.models.VacancyModel;
import com.example.saint.aukg.ui.BaseFragment;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by saint on 15.04.2018.
 */

public class VacanciesFragment extends BaseFragment implements SwipyRefreshLayout.OnRefreshListener{

    private RetrofitService service;
    private RecyclerView recyclerView;
    private MainVacanciesAdapter adapter;
    private SwipyRefreshLayout swipeRefreshLayout;
    private int swipe = 20;
    private Context context;

    @Override
    protected int getViewLayout() {
        return R.layout.fragment_vacancies;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getActivity() != null){
            context = getActivity().getApplicationContext();
        }
        getRecyclerView(view);
        setSwipeRefreshLayout(view);
        getVacancies();
    }

    private void getRecyclerView(View view){

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setSwipeRefreshLayout(View view){

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void getVacancies(){

        service = AuApplication.get(context).getService();

        service.postVacancies("au", "get_all_vacancies", String.valueOf(swipe), "1")
                .enqueue(new Callback<ArrayList<VacancyModel>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<VacancyModel>> call, @NonNull Response<ArrayList<VacancyModel>> response) {

                        if(response.isSuccessful() && response.body() != null){

                            adapter = new MainVacanciesAdapter(response.body());
                            recyclerView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArrayList<VacancyModel>> call, @NonNull Throwable t) {

                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        swipe += 20;
        getVacancies();
        swipeRefreshLayout.setRefreshing(false);
    }

}
