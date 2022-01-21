package mtrzepacz.quizgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import org.apache.commons.lang3.StringUtils;

import mtrzepacz.quizgame.database.DbAdapter;
import mtrzepacz.quizgame.database.QuestionDbo;

public class AddNewQuestionActivity extends AppCompatActivity {

    EditText question, answer1, answer2, answer3, answer4, correctAnswer;
    Button resetButton, addNewQuestionButton;

    DbAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_question);

        dbAdapter = new DbAdapter(getApplicationContext());
        dbAdapter.open();

        question = findViewById(R.id.questionEditText);
        answer1 = findViewById(R.id.odp1EditText);
        answer2 = findViewById(R.id.odp2EditText);
        answer3 = findViewById(R.id.odp3EditText);
        answer4 = findViewById(R.id.odp4EditText);
        correctAnswer = findViewById(R.id.correctAnswerEditText);

        resetButton = findViewById(R.id.resetButton);
        addNewQuestionButton = findViewById(R.id.addQuestionButton);
        onClickListeners();
    }

    private void onClickListeners() {
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                question.setText("Question");
                answer1.setText("answer no1");
                answer2.setText("answer no2");
                answer3.setText("answer no3");
                answer4.setText("answer no4");
                correctAnswer.setText("correct answer - must be equal to one of above!");
            }
        });
        addNewQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionDbo dbo = new QuestionDbo(
                        question.getText().toString(),
                        answer1.getText().toString(),
                        answer2.getText().toString(),
                        answer3.getText().toString(),
                        answer4.getText().toString(),
                        StringUtils.stripEnd(correctAnswer.getText().toString(), ""));

                Intent contentIntent = getIntent();

                dbAdapter.addCustomQuestion(contentIntent.getStringExtra("categoryName"), dbo);

                Toast.makeText(getApplicationContext(), "Pytanie dodane", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
