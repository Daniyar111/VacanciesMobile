package com.example.saint.aukg.ui.elected;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.saint.aukg.R;
import com.example.saint.aukg.ui.BaseFragment;

public class EmptyFragment extends BaseFragment {
    @Override
    protected int getViewLayout() {
        return R.layout.fragment_empty;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
