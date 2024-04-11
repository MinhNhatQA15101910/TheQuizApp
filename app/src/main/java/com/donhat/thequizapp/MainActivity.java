package com.donhat.thequizapp;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.donhat.thequizapp.databinding.ActivityMainBinding;
import com.donhat.thequizapp.models.Question;
import com.donhat.thequizapp.viewmodels.QuizViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding _activityMainBinding;
    private QuizViewModel _quizViewModel;
    private List<Question> _questionList;

    private static int _result = 0;
    private static int _totalQuestions = 0;
    private int _i = 0;

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
        _result = 0;
        _totalQuestions = 0;

        // Creating an instance of QuizViewModel
        _quizViewModel = new ViewModelProvider(this).get(QuizViewModel.class);

        displayFirstQuestion();

        _activityMainBinding.nextBtn.setOnClickListener(v -> displayNextQuestion());
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

    private void displayNextQuestion() {
        int selectedOption = _activityMainBinding.optionsRadioGroup.getCheckedRadioButtonId();
        if (selectedOption != -1) {
            RadioButton radioButton = findViewById(selectedOption);

            // More questions to display
            if (_questionList.size() - _i > 0) {
                // Getting the number of questions
                _totalQuestions = _questionList.size();

                // Check if the chosen option is correct
                if (radioButton.getText().toString().equals(
                        _questionList.get(_i).getCorrectOption())
                ) {
                    _result++;
                    _activityMainBinding.resultTextView.setText("Correct Answer: " + _result);
                }

                if (_i == 0) {
                    _i++;
                }

                _activityMainBinding.questionTextView.setText("Question " + (_i + 1) + " : " + _questionList.get(_i).getQuestion());
                _activityMainBinding.radioButton1.setText(_questionList.get(_i).getOption1());
                _activityMainBinding.radioButton2.setText(_questionList.get(_i).getOption2());
                _activityMainBinding.radioButton3.setText(_questionList.get(_i).getOption3());
                _activityMainBinding.radioButton4.setText(_questionList.get(_i).getOption4());

                // Check if it is the last question
                if (_i == _questionList.size() - 1) {
                    _activityMainBinding.nextBtn.setText("Finish");
                }

                _activityMainBinding.optionsRadioGroup.clearCheck();
                _i++;
            } else if (radioButton.getText().toString().equals(
                    _questionList.get(_i - 1).getCorrectOption()
            )) {
                _activityMainBinding.resultTextView.setText("Correct Answer: " + _result);
            }
        } else {
            Toast.makeText(
                    this,
                    "You need to make a selection!",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}