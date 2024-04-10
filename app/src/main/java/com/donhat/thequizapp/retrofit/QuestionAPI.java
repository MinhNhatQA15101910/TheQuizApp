package com.donhat.thequizapp.retrofit;

import com.donhat.thequizapp.models.Question;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuestionAPI {
    @GET("myquizapi.php")
    Call<ArrayList<Question>> getQuestions();
}
