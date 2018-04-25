package com.example.saint.aukg.data;

import com.example.saint.aukg.models.VacancyModel;
import com.example.saint.aukg.utils.Constants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface RetrofitService {

    @POST(Constants.MOBILE_API)
    Call<VacancyModel> postVacancies(@Field("login") String login,
                                     @Field("f") String form,
                                     @Field("limit") String limit,
                                     @Field("page") String page);
}
