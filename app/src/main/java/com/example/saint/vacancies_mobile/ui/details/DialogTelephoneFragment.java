package com.example.saint.vacancies_mobile.ui.details;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.saint.vacancies_mobile.R;
import com.example.saint.vacancies_mobile.ui.BaseDialogFragment;

import java.util.ArrayList;

public class DialogTelephoneFragment extends BaseDialogFragment implements AdapterView.OnItemClickListener {

    private ListView mListViewTelephone;
    private TelephoneAdapter mTelephoneAdapter;
    private ArrayList<String> mTelephones = new ArrayList<>();
    private String mTelephone;
    private Intent mIntentDial;

    @Override
    protected int getViewLayout() {
        return R.layout.dialog_fragment_telephone;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        removeDialogToolbarAndSetAnimation();
        initialize(view);
    }

    private void initialize(View view){
        mListViewTelephone = view.findViewById(R.id.listViewTelephone);
        mTelephoneAdapter = new TelephoneAdapter(getContext(), bundleTelephone());
        mListViewTelephone.setAdapter(mTelephoneAdapter);
        mListViewTelephone.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        mTelephone = (String) parent.getItemAtPosition(position);
        dialPhone(mTelephone);
    }

    private ArrayList<String> bundleTelephone(){
        Bundle bundle = this.getArguments();
        if(bundle != null){
            mTelephones = bundle.getStringArrayList("telephones");
        }
        return mTelephones;
    }

    private void dialPhone(String tel){
        mIntentDial = new Intent(Intent.ACTION_DIAL);
        mIntentDial.setData(Uri.parse("tel:" + tel));
        startActivity(mIntentDial);
    }
}
