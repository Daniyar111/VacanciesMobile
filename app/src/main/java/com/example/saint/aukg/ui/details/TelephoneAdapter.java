package com.example.saint.aukg.ui.details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.saint.aukg.R;

import java.util.ArrayList;

public class TelephoneAdapter extends BaseAdapter {

    private ArrayList<String> mArrayList;
    private Context mContext;

    public TelephoneAdapter(Context context, ArrayList<String> arrayList){
        this.mContext = context;
        this.mArrayList = arrayList;
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_telephone, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String telephone = (String) getItem(position);
        viewHolder.mTextViewDialogTelephone.setText(telephone);

        return convertView;
    }

    private class ViewHolder{
        private TextView mTextViewDialogTelephone;
        public ViewHolder(View view){
            mTextViewDialogTelephone = view.findViewById(R.id.textViewDialogTelephone);
        }
    }
}
