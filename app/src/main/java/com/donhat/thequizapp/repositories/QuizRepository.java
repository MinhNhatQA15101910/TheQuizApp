package com.donhat.thequizapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.donhat.thequizapp.models.Question;
import com.donhat.thequizapp.retrofit.QuestionAPI;
import com.donhat.thequizapp.retrofit.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizRepository {
    private QuestionAPI _questionAPI;

    public QuizRepository() {
        _questionAPI = new RetrofitInstance().getRetrofitInstance().create(QuestionAPI.class);
    }

    public LiveData<ArrayList<Question>> getQuestionsFromAPI() {
        MutableLiveData<ArrayList<Question>> data = new MutableLiveData<>();

        Call<ArrayList<Question>> response = _questionAPI.getQuestions();

        response.enqueue(new Callback<ArrayList<Question>>() {
            @Override
            public void onResponse(Call<ArrayList<Question>> call, Response<ArrayList<Question>> response) {
                ArrayList<Question> list = response.body();
                data.setValue(list);
            }

            @Override
            public void onFailure(Call<ArrayList<Question>> call, Throwable throwable) {

            }
        });

        return data;
    }
}
