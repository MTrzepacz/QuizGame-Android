package mtrzepacz.quizgame;


import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PlayOrAddNewQuestionActivity extends AppCompatActivity {


    TextView categoryTextView;
    Button playButton, addQuestionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_or_add_new_question);
        categoryTextView = findViewById(R.id.categoryTextView);
        playButton = findViewById(R.id.playButton);
        addQuestionButton = findViewById(R.id.addNewQuestionButton);
        categoryTextView.setText(getIntent().getStringExtra("categoryName"));
        onClickListeners();
    }

    private void onClickListeners() {
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent categoryIntent = getIntent();
                String categoryName = categoryIntent.getStringExtra("categoryName");
                pickPlayCategory(categoryName);
            }
        });
        addQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent categoryIntent = getIntent();
                Intent intent = new Intent(getApplicationContext(), AddNewQuestionActivity.class);
                intent.putExtra("categoryName", categoryIntent.getStringExtra("categoryName"));
                startActivity(intent);
            }
        });
    }

    private void pickPlayCategory(String categoryName) {
        if(equalsIgnoreCase(categoryName, "Matematyka")){
            Intent intent = new Intent(getApplicationContext(), MathActivity.class);
            startActivity(intent);
        } else if (equalsIgnoreCase(categoryName, "Historia")) {
            Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
            startActivity(intent);
        } else if (equalsIgnoreCase(categoryName, "Informatyka")){
            Intent intent = new Intent(getApplicationContext(), ITActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getApplicationContext(), CustomCategoryActivity.class);
            intent.putExtra("categoryName",categoryName);
            startActivity(intent);
        }
    }
}
