package com.example.saint.aukg.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class VacanciesList implements Parcelable {

    private List<VacancyModel> list;

    public List<VacancyModel> getList() {
        return list;
    }

    public void setList(List<VacancyModel> list) {
        this.list = list;
    }

    protected VacanciesList(Parcel in) {
        if (in.readByte() == 0x01) {
            list = new ArrayList<>();
            in.readList(list, VacancyModel.class.getClassLoader());
        } else {
            list = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (list == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(list);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<VacanciesList> CREATOR = new Parcelable.Creator<VacanciesList>() {
        @Override
        public VacanciesList createFromParcel(Parcel in) {
            return new VacanciesList(in);
        }

        @Override
        public VacanciesList[] newArray(int size) {
            return new VacanciesList[size];
        }
    };
}