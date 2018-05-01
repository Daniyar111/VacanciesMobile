package com.example.saint.aukg.ui.details;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.saint.aukg.R;

import java.util.ArrayList;

public class DialogTelephoneFragment extends DialogFragment implements AdapterView.OnItemClickListener {

    private ListView listViewTelephone;
    private TelephoneAdapter telephoneAdapter;
    private ArrayList<String> telephones = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_fragment_telephone, container, false);

        if(getDialog().getWindow() != null){
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }

        Bundle bundle = this.getArguments();
        if(bundle != null){
            telephones = bundle.getStringArrayList("telephones");
        }

        listViewTelephone = view.findViewById(R.id.listViewTelephone);
        telephoneAdapter = new TelephoneAdapter(getContext(), telephones);
        listViewTelephone.setAdapter(telephoneAdapter);

        listViewTelephone.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String telephone = (String) parent.getItemAtPosition(position);

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + telephone));
        startActivity(intent);
    }
}
