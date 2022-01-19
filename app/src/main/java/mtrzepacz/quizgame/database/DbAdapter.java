package mtrzepacz.quizgame.database;

import static mtrzepacz.quizgame.database.HistoryTableConstants.DB_CREATE_HISTORY_TABLE;
import static mtrzepacz.quizgame.database.HistoryTableConstants.DB_HISTORY_TABLE;
import static mtrzepacz.quizgame.database.HistoryTableConstants.DROP_HISTORY_TABLE;
import static mtrzepacz.quizgame.database.HistoryTableConstants.HISTORY_KEY_ID;
import static mtrzepacz.quizgame.database.HistoryTableConstants.KEY_ANSWER_1;
import static mtrzepacz.quizgame.database.HistoryTableConstants.KEY_ANSWER_2;
import static mtrzepacz.quizgame.database.HistoryTableConstants.KEY_ANSWER_3;
import static mtrzepacz.quizgame.database.HistoryTableConstants.KEY_ANSWER_4;
import static mtrzepacz.quizgame.database.HistoryTableConstants.KEY_CORRECT_ANSWER;
import static mtrzepacz.quizgame.database.HistoryTableConstants.KEY_QUESTION;
import static mtrzepacz.quizgame.database.ItTableConstants.DB_CREATE_IT_TABLE;
import static mtrzepacz.quizgame.database.ItTableConstants.DROP_IT_TABLE;
import static mtrzepacz.quizgame.database.MathTableConstants.DB_CREATE_MATH_TABLE;
import static mtrzepacz.quizgame.database.MathTableConstants.DROP_MATH_TABLE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Arrays;

import mtrzepacz.quizgame.History;
import mtrzepacz.quizgame.IT;
import mtrzepacz.quizgame.MathQuestions;

public class DbAdapter {
    private static final String DEBUG_TAG = "SqLiteTodoManager";
    private static final int DB_VERSION = 7;
    private static final String DB_NAME = "database.db";

    private SQLiteDatabase db;
    private Context context;
    private DatabaseHelper dbHelper;

    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE_HISTORY_TABLE);
            db.execSQL(DB_CREATE_IT_TABLE);
            db.execSQL(DB_CREATE_MATH_TABLE);
            populateHistoryQuestions(db);
            populateItQuestions(db);
            populateMathQusetions(db);
            Log.d(DEBUG_TAG, "Database creating...");
            Log.d(DEBUG_TAG, "Table " + DB_HISTORY_TABLE + " ver." + DB_VERSION + " created");
        }

        private void populateMathQusetions(SQLiteDatabase db) {
            MathQuestions math = new MathQuestions();
            for (int i = 0; i < 30; i++) {
                ContentValues values = new ContentValues();
                values.put(ItTableConstants.KEY_QUESTION, math.contentQuestionsMath[i]);
                values.put(ItTableConstants.KEY_ANSWER_1, math.getChoice1(i));
                values.put(ItTableConstants.KEY_ANSWER_2, math.getChoice2(i));
                values.put(ItTableConstants.KEY_ANSWER_3, math.getChoice3(i));
                values.put(ItTableConstants.KEY_ANSWER_4, math.getChoice4(i));
                values.put(ItTableConstants.KEY_CORRECT_ANSWER, math.getCorrectAnswer(i));
                db.insert(MathTableConstants.DB_MATH_TABLE, null, values);
            }
        }

        private void populateItQuestions(SQLiteDatabase db) {
            IT it = new IT();
            for (int i = 0; i < 30; i++) {
                ContentValues values = new ContentValues();
                values.put(ItTableConstants.KEY_QUESTION, it.contentQuestionsIT[i]);
                values.put(ItTableConstants.KEY_ANSWER_1, it.getChoice1(i));
                values.put(ItTableConstants.KEY_ANSWER_2, it.getChoice2(i));
                values.put(ItTableConstants.KEY_ANSWER_3, it.getChoice3(i));
                values.put(ItTableConstants.KEY_ANSWER_4, it.getChoice4(i));
                values.put(ItTableConstants.KEY_CORRECT_ANSWER, it.getCorrectAnswer(i));
                db.insert(ItTableConstants.DB_IT_TABLE, null, values);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_HISTORY_TABLE);
            db.execSQL(DROP_IT_TABLE);
            db.execSQL(DROP_MATH_TABLE);
            Log.d(DEBUG_TAG, "Database updating...");
            Log.d(DEBUG_TAG, "Table " + DB_HISTORY_TABLE + " updated from ver." + oldVersion + " to ver." + newVersion);
            Log.d(DEBUG_TAG, "All data is lost.");

            onCreate(db);
        }

        public void populateHistoryQuestions(SQLiteDatabase db) {
            History history = new History();
            for (int i = 0; i < 30; i++) {
                ContentValues values = new ContentValues();
                values.put(KEY_QUESTION, history.contentQuestionsHIS[i]);
                values.put(KEY_ANSWER_1, history.getChoice1(i));
                values.put(KEY_ANSWER_2, history.getChoice2(i));
                values.put(KEY_ANSWER_3, history.getChoice3(i));
                values.put(KEY_ANSWER_4, history.getChoice4(i));
                values.put(KEY_CORRECT_ANSWER, history.getCorrectAnswer(i));
                db.insert(DB_HISTORY_TABLE, null, values);
            }
        }
    }

    public DbAdapter(Context context) {
        this.context = context;
    }

    public DbAdapter open() {
        dbHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            db = dbHelper.getReadableDatabase();
        }
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public Cursor getAllHistoryQuestions() {
        String[] columns = {HISTORY_KEY_ID, KEY_QUESTION,KEY_ANSWER_1 ,KEY_ANSWER_2,KEY_ANSWER_3,KEY_ANSWER_4,KEY_CORRECT_ANSWER};
        return db.query(DB_HISTORY_TABLE, columns, null, null, null, null, null);
    }

    public Cursor getAllITQuestions() {
        String[] columns = {ItTableConstants.IT_KEY_ID, KEY_QUESTION,KEY_ANSWER_1 ,KEY_ANSWER_2,KEY_ANSWER_3,KEY_ANSWER_4,KEY_CORRECT_ANSWER};
        return db.query(ItTableConstants.DB_IT_TABLE, columns, null, null, null, null, null);
    }

    public Cursor getAllMathQuestions() {
        String[] columns = {MathTableConstants.MATH_KEY_ID, KEY_QUESTION,KEY_ANSWER_1 ,KEY_ANSWER_2,KEY_ANSWER_3,KEY_ANSWER_4,KEY_CORRECT_ANSWER};
        return db.query(MathTableConstants.DB_MATH_TABLE, columns, null, null, null, null, null);
    }
}