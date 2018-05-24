package com.example.saint.vacancies_mobile.ui.vacancies;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.example.saint.vacancies_mobile.StartApplication;
import com.example.saint.vacancies_mobile.R;
import com.example.saint.vacancies_mobile.data.RetrofitService;
import com.example.saint.vacancies_mobile.data.db.SQLiteHelper;
import com.example.saint.vacancies_mobile.data.models.VacancyModel;
import com.example.saint.vacancies_mobile.ui.BaseFragment;
import com.example.saint.vacancies_mobile.ui.MainVacanciesAdapter;
import com.example.saint.vacancies_mobile.ui.details.DetailsActivity;
import com.example.saint.vacancies_mobile.utils.AndroidUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by saint on 15.04.2018.
 */

public class VacanciesFragment extends BaseFragment implements VacanciesAdapterCallback, SwipeRefreshLayout.OnRefreshListener {

    private RetrofitService mService;
    private RecyclerView mRecyclerView;
    private MainVacanciesAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Context mContext;
    private ArrayList<VacancyModel> mVacancyModels = new ArrayList<>();
    private int mPage = 1;
    private FrameLayout mProgressBar;
    private SQLiteHelper mSQLiteHelper;

    @Override
    protected int getViewLayout() {
        return R.layout.fragment_vacancies;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getActivity() != null){
            mContext = getActivity().getApplicationContext();
        }
        mProgressBar = view.findViewById(R.id.progressBar);

        mSQLiteHelper = StartApplication.get(mContext).getSQLiteHelper();

        getRecyclerView(view);
        setSwipyRefreshLayout(view);
        internetChecking();
    }

    private void getRecyclerView(View view){

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setSwipyRefreshLayout(View view){

        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    private void getVacancies(){

        mProgressBar.setVisibility(View.VISIBLE);

        mService = StartApplication.get(mContext).getService();
        mService.postVacancies("daniyar", "get", "20", String.valueOf(mPage))
                .enqueue(new Callback<ArrayList<VacancyModel>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<VacancyModel>> call, @NonNull Response<ArrayList<VacancyModel>> response) {

                        if(response.isSuccessful() && response.body() != null){

                            if(mPage == 1){
                                mSQLiteHelper.saveListWithoutInternet(response.body());
                            }

                            mVacancyModels.addAll(response.body());
                            mAdapter = new MainVacanciesAdapter(mContext, mVacancyModels, VacanciesFragment.this, true);
                            mRecyclerView.setAdapter(mAdapter);
                        }
                        mProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArrayList<VacancyModel>> call, @NonNull Throwable t) {

                        AndroidUtils.showLongToast(mContext, t.getMessage());
                        mProgressBar.setVisibility(View.GONE);
                    }
                });
    }

    private void internetChecking(){
        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(manager != null){
            NetworkInfo info = manager.getActiveNetworkInfo();
            if(info != null && info.isConnected()){
                getVacancies();

            }
            else {
                if(mVacancyModels != null && mSQLiteHelper.getListWithoutInternet() != null){
                    mVacancyModels = mSQLiteHelper.getListWithoutInternet();
                    mAdapter = new MainVacanciesAdapter(mContext, mVacancyModels, VacanciesFragment.this, true);
                    mRecyclerView.setAdapter(mAdapter);
                }
                AndroidUtils.showLongToast(mContext, getResources().getString(R.string.no_internet));
            }
        }
    }

    @Override
    public void onRefresh() {
        mPage++;
        getVacancies();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mAdapter != null){
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onVacancyClicked(ArrayList<VacancyModel> vacancyModels, int position) {
        Intent intent = new Intent(mContext, DetailsActivity.class);
        intent.putParcelableArrayListExtra("vacancy_models", vacancyModels);
        intent.putExtra("position", position);

        startActivity(intent);
    }
}
