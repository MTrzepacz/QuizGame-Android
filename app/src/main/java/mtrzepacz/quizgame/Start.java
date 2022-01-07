package mtrzepacz.quizgame;

import android.content.Intent;
import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

    }
    public void chooseMath(View view)
    {
        Intent intent = new Intent(this, MathActivity.class);
        startActivity(intent);
    }

    public void chooseIT(View view)
    {
        Intent intent = new Intent(this, ITActivity.class);
        startActivity(intent);
    }

    public void chooseHistory(View view)
    {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }
}
