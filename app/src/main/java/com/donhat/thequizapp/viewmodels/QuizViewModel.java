package com.donhat.thequizapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.donhat.thequizapp.models.Question;
import com.donhat.thequizapp.repositories.QuizRepository;

import java.util.ArrayList;

public class QuizViewModel extends ViewModel {
    private QuizRepository _quizRepository = new QuizRepository();
    private LiveData<ArrayList<Question>> _questionListLiveData;

    public QuizViewModel() {
        _questionListLiveData = _quizRepository.getQuestionsFromAPI();
    }

    public LiveData<ArrayList<Question>> getQuestionListLiveData() {
        return _questionListLiveData;
    }
}
