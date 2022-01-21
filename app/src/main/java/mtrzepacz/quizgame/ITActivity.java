package mtrzepacz.quizgame;

import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import mtrzepacz.quizgame.database.DbAdapter;
import mtrzepacz.quizgame.database.HistoryTableConstants;
import mtrzepacz.quizgame.database.QuestionDbo;

public class ITActivity extends AppCompatActivity {

    Button answer1, answer2, answer3, answer4;

    TextView questionView, score, timer, BestScoreView;

    private IT mQuestions = new IT();

    Set<Integer> powtorki = new HashSet<>();
    private int mScore = 0;
    private String mAnswer;
    private int Counter = 60;
    private int mQuestionslenght = mQuestions.contentQuestionsIT.length;
    Integer highScoreINT;
    int QuestionNumber;

    MediaPlayer mplayerGood;
    MediaPlayer mplayerWrong;
    Random r;

    //db setup
    private DbAdapter dbAdapter;
    private Cursor ITcursor;
    private List<QuestionDbo> questions = new ArrayList<>();

    CountDownTimer CDTimer = new CountDownTimer(90000, 1000) {
        public void onTick(long milisUntilFinished) {
            if (Counter > -1)
                timer.setText(String.valueOf(Counter));
            Counter--;
            if (Counter == 0) {
                onFinish();
            }
        }

        public void onFinish() {
            timer.setText(" KONIEC CZASU ");
            cancel();
            gameOver();
        }
    };


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_it);
            LayoutControl();
            onClickListeners();
        } else {
            setContentView(R.layout.activity_it);
            LayoutControl();
            onClickListeners();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it);
        setTitle("Informatyka");
        loadmusic();
        loadQuestionData();
        r = new Random();
        QuestionNumber = r.nextInt(mQuestionslenght);
        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);

        questionView = (TextView) findViewById(R.id.QuestionView);
        BestScoreView = (TextView) findViewById(R.id.BestScoreView);
        score = (TextView) findViewById(R.id.score);
        score.setText("Wynik : " + mScore);
        updateQuestion(QuestionNumber);

        timer = (TextView) findViewById(R.id.timerView);

        checkHighScore();
        CDTimer.start();
        timer.setText(String.valueOf(Counter));

        saveQuestion();
        onClickListeners();
    }

    private void updateQuestion(int num) {
        questionView.setText(questions.get(num).getQuestion());
        answer1.setText(questions.get(num).getAnswer1());
        answer2.setText(questions.get(num).getAnswer2());
        answer3.setText(questions.get(num).getAnswer3());
        answer4.setText(questions.get(num).getAnswer4());

        mAnswer = questions.get(num).getCorrectAnswer();
    }

    private void checkHighScore() {
        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        highScoreINT = settings.getInt("HIGH_SCORE_INF", 0);
        if (mScore > highScoreINT) {
            BestScoreView.setText("Najlepszy wynik : " + mScore);

            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE_INF", mScore);
            editor.commit();
        } else {
            BestScoreView.setText("Najlepszy wynik : " + highScoreINT);
        }

    }

    private void gameOver() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ITActivity.this);
        alertDialogBuilder
                .setMessage("Błędna odpowiedź! Twój wynik to " + mScore + " punktów.")
                .setCancelable(false)
                .setPositiveButton("NEW GAME",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(getApplicationContext(), Start.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                finish();
                                System.gc();
                            }
                        })
                .setNegativeButton("EXIT",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(getApplicationContext(), Start.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra("EXIT", true);
                                finish();
                                startActivity(intent);
                            }
                        });
        // playWrongSound();
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        Counter = -1;
        timer.setText("KONIEC GRY");
    }

    private void playCorrectSound() {
        mplayerGood.start();
    }

    private void playWrongSound() {
        mplayerWrong.start();
    }

    public void LayoutControl() {
        SharedPreferences settings2 = getSharedPreferences("GAME_DATA2", Context.MODE_PRIVATE);

        int help = settings2.getInt("NUM_PYT", 0);

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
        timer.setText(String.valueOf(Counter));

    }

    private void loadmusic() {
        mplayerGood = new MediaPlayer().create(getApplication(), R.raw.goodanswer);
        mplayerWrong = new MediaPlayer().create(getApplication(), R.raw.wronganswer);
    }

    public void saveQuestion() {
        SharedPreferences settings2 = getSharedPreferences("GAME_DATA2", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings2.edit();
        editor.putInt("NUM_PYT", QuestionNumber);
        editor.commit();
    }

    public void onClickListeners() {
        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (equalsIgnoreCase(answer1.getText(), mAnswer)) {
                    goodanswer();
                } else {
                    playWrongSound();
                    gameOver();
                }
            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (equalsIgnoreCase(answer2.getText(), mAnswer)) {
                    goodanswer();
                } else {
                    playWrongSound();
                    gameOver();
                }
            }
        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (equalsIgnoreCase(answer3.getText(), mAnswer)) {
                    goodanswer();
                } else {
                    playWrongSound();
                    gameOver();
                }
            }
        });

        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (equalsIgnoreCase(answer4.getText(), mAnswer)) {
                    goodanswer();
                } else {
                    playWrongSound();
                    gameOver();
                }
            }
        });
    }

    private void goodanswer() {
        mScore = mScore + Counter;
        checkHighScore();
        playCorrectSound();
        score.setText("Wynik : " + mScore);
        Counter = 60;
        QuestionNumber = r.nextInt(mQuestionslenght);
        while (powtorki.contains(QuestionNumber)) {
            QuestionNumber = r.nextInt(mQuestionslenght);
        }
        updateQuestion(QuestionNumber);
        powtorki.add(QuestionNumber);
        saveQuestion();
        if (powtorki.size() == mQuestions.contentQuestionsIT.length) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ITActivity.this);
            alertDialogBuilder
                    .setMessage("Koniec pytań, twój końcowy wynik to " + mScore + " punktów, gratulacje!")
                    .setCancelable(false)
                    .setPositiveButton("NEW GAME",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                    Intent intent = new Intent(getApplicationContext(), Start.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                }
                            })
                    .setNegativeButton("EXIT",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    finish();
                                    Intent intent = new Intent(getApplicationContext(), Start.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.putExtra("EXIT", true);
                                    startActivity(intent);

                                }
                            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

            Counter = -1;
            timer.setText("KONIEC GRY");
        }
    }

    private void loadQuestionData() {
        dbAdapter = new DbAdapter(getApplicationContext());
        dbAdapter.open();
        getAllItQuestions();
    }

    private void getAllItQuestions() {
        questions = new ArrayList<QuestionDbo>();
        ITcursor = getAllITQuestionsFromDb();
        //mapping questions
        if (ITcursor != null && ITcursor.moveToFirst()) {
            do {
                long id = ITcursor.getLong(HistoryTableConstants.ID_COLUMN);
                String question = ITcursor.getString(HistoryTableConstants.QUESTION_COLUMN);
                String answer1 = ITcursor.getString(HistoryTableConstants.ANSWER_1_COLUMN);
                String answer2 = ITcursor.getString(HistoryTableConstants.ANSWER_2_COLUMN);
                String answer3 = ITcursor.getString(HistoryTableConstants.ANSWER_3_COLUMN);
                String answer4 = ITcursor.getString(HistoryTableConstants.ANSWER_4_COLUMN);
                String correctAnswer = ITcursor.getString(HistoryTableConstants.KEY_CORRECT_COLUMN);
                questions.add(new QuestionDbo(id, question, answer1, answer2, answer3, answer4, correctAnswer));
            } while (ITcursor.moveToNext());
        }
    }

    private Cursor getAllITQuestionsFromDb() {
        ITcursor = dbAdapter.getAllITQuestions();
        if (ITcursor != null) {
            startManagingCursor(ITcursor);
            ITcursor.moveToFirst();
        }
        return ITcursor;
    }
}

