package mtrzepacz.quizgame;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

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
