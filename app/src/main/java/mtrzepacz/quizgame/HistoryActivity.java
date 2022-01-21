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
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mtrzepacz.quizgame.database.DbAdapter;
import mtrzepacz.quizgame.database.HistoryTableConstants;
import mtrzepacz.quizgame.database.QuestionDbo;

public class HistoryActivity extends AppCompatActivity {

    Button answer1, answer2, answer3, answer4;

    TextView questionView, score, timer, BestScoreView;
    MediaPlayer mplayerGood, mplayerWrong;
    private History mQuestions = new History();

    //Set<Integer> powtorki = new HashSet<>();
    private int currentScore = 0;
    private String correctAnswer;
    private int Counter = 60;
    private int mQuestionslenght = mQuestions.contentQuestionsHIS.length;
    Integer highScoreINT;
    int questionNumber;

    Random r;

    //db setup
    private DbAdapter dbAdapter;
    private Cursor historyCursor;
    private List<QuestionDbo> questions = new ArrayList<>();

    CountDownTimer CDTimer = new CountDownTimer(90000, 1000) {
        public void onTick(long milisUnitilFinished) {
            if (Counter > -1)
                timer.setText(String.valueOf(Counter));
            Counter--;
            if (Counter == 0) {
                onFinish();
            }
        }

        public void onFinish() {
            cancel();
            gameOver();
        }
    };

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_history);
            LayoutControl();
            onClickListeners();
        } else {
            setContentView(R.layout.activity_history);
            LayoutControl();
            onClickListeners();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setTitle("Historia");
        loadQuestionData();
        r = new Random();
        questionNumber = r.nextInt(mQuestionslenght);
        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);

        questionView = (TextView) findViewById(R.id.QuestionView);
        BestScoreView = (TextView) findViewById(R.id.BestScoreView);
        score = (TextView) findViewById(R.id.score);
        score.setText("Wynik : " + currentScore);
        updateQuestion(questionNumber);

        timer = (TextView) findViewById(R.id.timerView);

        checkHighScore();
        CDTimer.start();
        timer.setText(String.valueOf(Counter));
        loadmusic();
        saveQuestion();
        onClickListeners();
    }

    @Override
    protected void onDestroy(){
        if(dbAdapter != null){
            dbAdapter.close();
        }
        super.onDestroy();
    }
    private void updateQuestion(int num) {
        questionView.setText(questions.get(num).getQuestion());
        answer1.setText(questions.get(num).getAnswer1());
        answer2.setText(questions.get(num).getAnswer2());
        answer3.setText(questions.get(num).getAnswer3());
        answer4.setText(questions.get(num).getAnswer4());

        correctAnswer = questions.get(num).getCorrectAnswer();
    }

    private void checkHighScore() {
        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        highScoreINT = settings.getInt("HIGH_SCORE_HIS", 0);
        if (currentScore > highScoreINT) {
            BestScoreView.setText("Najlepszy wynik : " + currentScore);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE_HIS", currentScore);
            editor.commit();
        } else {
            BestScoreView.setText("Najlepszy  wynik : " + highScoreINT);
        }

    }

    private void gameOver() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HistoryActivity.this);
        alertDialogBuilder
                .setMessage("Błędna odpowiedź! Twój wynik to " + currentScore + " punktów.")
                .setCancelable(false)
                .setPositiveButton("NEW GAME",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(getApplicationContext(), Start.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                                // startActivity(new Intent(getApplicationContext(),Start.class));
                            }
                        })
                .setNegativeButton("EXIT",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
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
    }

    private void playCorrectSound() {
        mplayerGood.start();
    }

    private void playWrongSound() {
        mplayerWrong.start();
    }

    private void loadmusic() {
        mplayerGood = new MediaPlayer().create(getApplication(), R.raw.goodanswer);
        mplayerWrong = new MediaPlayer().create(getApplication(), R.raw.wronganswer);
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
        score.setText("Wynik : " + currentScore);
        updateQuestion(help);

        timer = (TextView) findViewById(R.id.timerView);

        checkHighScore();
    }

    public void saveQuestion() {
        SharedPreferences settings2 = getSharedPreferences("GAME_DATA2", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings2.edit();
        editor.putInt("NUM_PYT", questionNumber);
        editor.commit();
    }

    public void onClickListeners() {
        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (equalsIgnoreCase(answer1.getText(), correctAnswer)) {
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
                if (equalsIgnoreCase(answer2.getText(), correctAnswer)) {
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
                if (equalsIgnoreCase(answer3.getText(), correctAnswer)) {
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
                if (equalsIgnoreCase(answer4.getText(), correctAnswer)) {
                    goodanswer();
                } else {
                    playWrongSound();
                    gameOver();
                }
            }
        });
    }

    private void goodanswer() {
        currentScore = currentScore + Counter;
        checkHighScore();
        playCorrectSound();
        score.setText("Wynik " + currentScore);
        Counter = 60;
        questionNumber = r.nextInt(mQuestionslenght);
        updateQuestion(questionNumber);
        saveQuestion();
    }

    private void loadQuestionData() {
        dbAdapter = new DbAdapter(getApplicationContext());
        dbAdapter.open();
        getAllHistoryQuestions();
    }

    private void getAllHistoryQuestions() {
        questions = new ArrayList<QuestionDbo>();
        historyCursor = getAllHistoryQuestionsFromDb();
        //mapping questions
        if (historyCursor != null && historyCursor.moveToFirst()) {
            do {
                long id = historyCursor.getLong(HistoryTableConstants.ID_COLUMN);
                String question = historyCursor.getString(HistoryTableConstants.QUESTION_COLUMN);
                String answer1 = historyCursor.getString(HistoryTableConstants.ANSWER_1_COLUMN);
                String answer2 = historyCursor.getString(HistoryTableConstants.ANSWER_2_COLUMN);
                String answer3 = historyCursor.getString(HistoryTableConstants.ANSWER_3_COLUMN);
                String answer4 = historyCursor.getString(HistoryTableConstants.ANSWER_4_COLUMN);
                String correctAnswer = historyCursor.getString(HistoryTableConstants.KEY_CORRECT_COLUMN);
                questions.add(new QuestionDbo(id, question, answer1, answer2, answer3, answer4, correctAnswer));
            } while (historyCursor.moveToNext());
        }
    }

    private Cursor getAllHistoryQuestionsFromDb() {
        historyCursor = dbAdapter.getAllHistoryQuestions();
        if (historyCursor != null) {
            startManagingCursor(historyCursor);
            historyCursor.moveToFirst();
        }
        return historyCursor;
    }
}


