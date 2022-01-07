package mtrzepacz.quizgame;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.CountDownTimer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class MathActivity extends AppCompatActivity {


    Button answer1,answer2,answer3,answer4;

    TextView questionView, score,timer, BestScoreView;

    private MathQuestions mQuestions = new MathQuestions();


  //  Set<Integer> powtorki = new HashSet<>();
    private int mScore = 0;
    private String mAnswer;
    private int Counter = 60;
    private int mQuestionslenght = mQuestions.contentQuestionsMath.length;
    Integer highScoreINT;
    int QuestionNumber;


    MediaPlayer mplayerGood;
    MediaPlayer mplayerWrong;

    Random r;

    CountDownTimer CDTimer =   new CountDownTimer (90000,1000){
        public void onTick(long milisUnitilFinished)
        {
            if(Counter > -1)
                timer.setText(String.valueOf(Counter));
            Counter--;
            if(Counter == -1) {
                onFinish();
            }

        }
        public void onFinish()
        {


            cancel();
            //playWrongSound();
            gameOver();

        }


    };


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            setContentView(R.layout.activity_math);
            LayoutControl();
            onClickListeners();


        } else {

            setContentView(R.layout.activity_math);
            LayoutControl();
            onClickListeners();

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);
        setTitle("Math Questions");





        r = new Random();
        loadmusic();
        QuestionNumber = r.nextInt(mQuestionslenght);
        //powtorki.add(QuestionNumber);
        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);

        questionView = (TextView) findViewById(R.id.QuestionView);
        score = (TextView) findViewById(R.id.score);
        BestScoreView = (TextView) findViewById(R.id.BestScoreView);
        score.setText("Wynik : " + mScore);
        updateQuestion(QuestionNumber);


        timer = (TextView) findViewById(R.id.timerView);

        checkHighScore();
        CDTimer.start();
        timer.setText(String.valueOf(Counter));

        saveQuestion();
        onClickListeners();







    }
    private void updateQuestion(int num)
    {
        questionView.setText(mQuestions.getQuestionMath(num));
        answer1.setText(mQuestions.getChoice1(num));
        answer2.setText(mQuestions.getChoice2(num));
        answer3.setText(mQuestions.getChoice3(num));
        answer4.setText(mQuestions.getChoice4(num));


        mAnswer = mQuestions.getCorrectAnswer(num);
    }
    private void checkHighScore()
    {
        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        highScoreINT = settings.getInt("HIGH_SCORE_MATH",0);
        if(mScore > highScoreINT)
        {
            BestScoreView.setText("Najlepszy wynik : " + mScore);

            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE_MATH",mScore);
            editor.commit();
        }
        else
        {
            BestScoreView.setText("Najlepszy wynik : " + highScoreINT );
        }

    }

    private void gameOver()
    {


        AlertDialog.Builder alertDialogBuilder = new    AlertDialog.Builder(MathActivity.this);
        alertDialogBuilder
                .setMessage("Błędna odpowiedź! Twój wynik to " + mScore + " punktów.")
                .setCancelable(false)
                .setPositiveButton("NEW GAME",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i )
                            {
                                finish();
                                Intent intent = new Intent(getApplicationContext(), Start.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                //  startActivity(new Intent(getApplicationContext(),Start.class));
                            }
                        })
                .setNegativeButton("EXIT",
                        new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {

                                finish();
                                Intent intent = new Intent(getApplicationContext(), Start.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra("EXIT", true);
                                startActivity(intent);
                                Counter = -1;

                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        Counter = -1;
        timer.setText("KONIEC GRY");






    }


    private void playCorrectSound()
    {

        mplayerGood.start();
    }

    private void playWrongSound()
    {

        mplayerWrong.start();

    }
    private void loadmusic()
    {
        mplayerGood = new MediaPlayer().create(getApplication(),R.raw.goodanswer);
        mplayerWrong = new MediaPlayer().create(getApplication(),R.raw.wronganswer);
    }
    public void LayoutControl()
    {
        SharedPreferences settings2 = getSharedPreferences("GAME_DATA2", Context.MODE_PRIVATE);

        int help = settings2.getInt("NUM_PYT",0);


        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);

        questionView = (TextView) findViewById(R.id.QuestionView);
        score = (TextView) findViewById(R.id.score);
        BestScoreView = (TextView) findViewById(R.id.BestScoreView);
        score.setText("Wynik : " + mScore);
        updateQuestion(help);

        timer = (TextView) findViewById(R.id.timerView);

        checkHighScore();
      //  CDTimer.start();

    }
    public void onClickListeners()
    {
        answer1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(answer1.getText() == mAnswer)
                {
                    goodanswer();
                }
                else
                {
                    playWrongSound();
                    gameOver();
                }
            }
        });

        answer2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(answer2.getText() == mAnswer)
                {
                    goodanswer();
                }
                else
                {
                    playWrongSound();
                    gameOver();
                }
            }
        });

        answer3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(answer3.getText() == mAnswer)
                {

                    goodanswer();
                }
                else
                {
                    playWrongSound();
                    gameOver();
                }
            }
        });

        answer4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(answer4.getText() == mAnswer)
                {

                   goodanswer();
                }
                else
                {
                    playWrongSound();
                    gameOver();
                }
            }
        });
    }
    public void saveQuestion()
    {
        SharedPreferences settings2 = getSharedPreferences("GAME_DATA2", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings2.edit();
        editor.putInt("NUM_PYT",QuestionNumber);
        editor.commit();
    }

    private void goodanswer()
    {
        mScore=mScore+Counter;
        checkHighScore();
        playCorrectSound();
        score.setText("Wynik : " + mScore);
        Counter = 60;
        QuestionNumber = r.nextInt(mQuestionslenght);
      /*  while (powtorki.contains(QuestionNumber))
        {
            QuestionNumber = r.nextInt(mQuestionslenght);
        } */
        updateQuestion(QuestionNumber);
     //   powtorki.add(QuestionNumber);
        saveQuestion();
      /*  if(powtorki.size() == mQuestions.contentQuestionsMath.length)
        {
            AlertDialog.Builder alertDialogBuilder = new    AlertDialog.Builder(MathActivity.this);
            alertDialogBuilder
                    .setMessage("Koniec pytań, twój końcowy wynik to " + mScore + " punktów, gratulacje!")
                    .setCancelable(false)
                    .setPositiveButton("NEW GAME",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i )
                                {

                                    Intent intent = new Intent(getApplicationContext(), Start.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();

                                }
                            })
                    .setNegativeButton("EXIT",
                            new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i)
                                {


                                    Intent intent = new Intent(getApplicationContext(), Start.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.putExtra("EXIT", true);
                                    startActivity(intent);
                                    finish();
                                }
                            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

            Counter = -1;
            timer.setText("KONIEC GRY");
        } */
    }

}



