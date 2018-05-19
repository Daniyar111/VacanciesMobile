package com.example.saint.aukg.ui.search;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.saint.aukg.AuApplication;
import com.example.saint.aukg.R;
import com.example.saint.aukg.data.RetrofitService;
import com.example.saint.aukg.data.db.SQLiteHelper;
import com.example.saint.aukg.data.models.VacancyModel;
import com.example.saint.aukg.ui.BaseFragment;
import com.example.saint.aukg.ui.details.DetailsActivity;
import com.example.saint.aukg.ui.vacancies.MainVacanciesAdapter;
import com.example.saint.aukg.ui.vacancies.VacanciesAdapterCallback;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchListFragment extends BaseFragment implements VacanciesAdapterCallback, SwipyRefreshLayout.OnRefreshListener{

    private RetrofitService mService;
    private RecyclerView mRecyclerView;
    private MainVacanciesAdapter mAdapter;
    private SwipyRefreshLayout mSwipyRefreshLayout;
    private Context mContext;
    private ArrayList<VacancyModel> mVacancyModels = new ArrayList<>();
    private int mPage = 1;
    private FrameLayout mProgressBar;
    private SQLiteHelper mSQLiteHelper;
    private String mSalary, mTerm;

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

        mSQLiteHelper = AuApplication.get(mContext).getSQLiteHelper();

        getRecyclerView(view);
        setSwipyRefreshLayout(view);
        getVacancies();
    }

    private void getRecyclerView(View view){

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setSwipyRefreshLayout(View view){

        mSwipyRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        mSwipyRefreshLayout.setOnRefreshListener(this);
    }

    private void getVacancies(){

        mProgressBar.setVisibility(View.VISIBLE);

        mSalary = mSQLiteHelper.getRadioButtons().getSalary();
        mTerm = mSQLiteHelper.getRadioButtons().getRegime();

        mService = AuApplication.get(mContext).getService();
        mService.postSearchingVacancies("au", "get_post_by_filter", "30", String.valueOf(mPage), mSalary, mTerm)
                .enqueue(new Callback<ArrayList<VacancyModel>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<VacancyModel>> call, @NonNull final Response<ArrayList<VacancyModel>> response) {

                        if(response.isSuccessful() && response.body() != null){

                            mVacancyModels.addAll(response.body());
                            mAdapter = new MainVacanciesAdapter(mContext, mVacancyModels, SearchListFragment.this);
                            mRecyclerView.setAdapter(mAdapter);
                        }
                        mProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArrayList<VacancyModel>> call, @NonNull Throwable t) {

                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                        mProgressBar.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        mPage++;
        getVacancies();
        mSwipyRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onVacancyClicked(final ArrayList<VacancyModel> vacancyModels, final int position) {

        Intent intent = new Intent(mContext, DetailsActivity.class);

        intent.putParcelableArrayListExtra("vacancy_models", vacancyModels);
        intent.putExtra("position", position);

        startActivity(intent);
    }
}

