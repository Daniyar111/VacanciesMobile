package com.example.saint.aukg.ui.elected;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.saint.aukg.R;
import com.example.saint.aukg.ui.BaseFragment;

public class ElectedFragment extends BaseFragment {

    private Context mContext;
    private RecyclerView mRecyclerView;

    @Override
    protected int getViewLayout() {
        return R.layout.fragment_elected;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getActivity() != null){
            mContext = getActivity().getApplicationContext();
        }

        getRecyclerView(view);
    }

    private void getRecyclerView(View view){

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


}
