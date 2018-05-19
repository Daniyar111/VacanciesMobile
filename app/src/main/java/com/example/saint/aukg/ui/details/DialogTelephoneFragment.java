package com.example.saint.aukg.ui.details;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.saint.aukg.R;
import com.example.saint.aukg.ui.BaseDialogFragment;

import java.util.ArrayList;

public class DialogTelephoneFragment extends BaseDialogFragment implements AdapterView.OnItemClickListener {

    private ListView listViewTelephone;
    private TelephoneAdapter telephoneAdapter;
    private ArrayList<String> telephones = new ArrayList<>();
    private String telephone;

    @Override
    protected int getViewLayout() {
        return R.layout.dialog_fragment_telephone;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        removeDialogToolbar();
        initialize(view);
    }

    private void initialize(View view){
        listViewTelephone = view.findViewById(R.id.listViewTelephone);
        telephoneAdapter = new TelephoneAdapter(getContext(), bundleTelephone());
        listViewTelephone.setAdapter(telephoneAdapter);
        listViewTelephone.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        telephone = (String) parent.getItemAtPosition(position);
        parseIntent(telephone);
    }

    private ArrayList<String> bundleTelephone(){
        Bundle bundle = this.getArguments();
        if(bundle != null){
            telephones = bundle.getStringArrayList("telephones");
        }
        return telephones;
    }

    private void parseIntent(String tel){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + tel));
        startActivity(intent);
    }
}
