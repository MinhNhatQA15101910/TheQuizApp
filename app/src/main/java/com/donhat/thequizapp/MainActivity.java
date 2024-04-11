package com.donhat.thequizapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.donhat.thequizapp.databinding.ActivityMainBinding;
import com.donhat.thequizapp.models.Question;
import com.donhat.thequizapp.viewmodels.QuizViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding _activityMainBinding;
    private QuizViewModel _quizViewModel;
    private List<Question> _questionList;

    static int result = 0;
    static int totalQuestions = 0;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Data Binding
        _activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Resetting the Scores
        result = 0;
        totalQuestions = 0;

        // Creating an instance of QuizViewModel
        _quizViewModel = new ViewModelProvider(this).get(QuizViewModel.class);
    }

    private void displayFirstQuestion() {
        _quizViewModel.getQuestionListLiveData().observe(this, questions -> {
            _questionList = questions;

            _activityMainBinding.questionTextView.setText("Question 1: " + questions.get(0).getQuestion());
            _activityMainBinding.radioButton1.setText(questions.get(0).getOption1());
            _activityMainBinding.radioButton2.setText(questions.get(0).getOption2());
            _activityMainBinding.radioButton3.setText(questions.get(0).getOption3());
            _activityMainBinding.radioButton4.setText(questions.get(0).getOption4());
        });
    }
}