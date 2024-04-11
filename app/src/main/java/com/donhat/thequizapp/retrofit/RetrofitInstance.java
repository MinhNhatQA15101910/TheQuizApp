package com.donhat.thequizapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private String _baseUrl = "http://192.168.1.11/quiz/";

    public Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(_baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
