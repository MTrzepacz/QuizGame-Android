package mtrzepacz.quizgame;

import android.content.Intent;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import mtrzepacz.quizgame.database.DbAdapter;

public class Start extends AppCompatActivity {

    DbAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        dbAdapter = new DbAdapter(getApplicationContext());
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
    }

    public void chooseMath(View view) {
        Intent intent = new Intent(this, MathActivity.class);
        startActivity(intent);
    }

    public void chooseIT(View view) {
        Intent intent = new Intent(this, ITActivity.class);
        startActivity(intent);
    }

    public void chooseHistory(View view) {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    public void chooseTopicList(View view) {
        Intent intent = new Intent(this, TopicList.class);
        startActivity(intent);
    }
}
