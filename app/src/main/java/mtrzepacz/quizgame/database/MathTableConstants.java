package mtrzepacz.quizgame.database;

public class MathTableConstants {

    public static final String DB_MATH_TABLE = "Math";
    public static final String MATH_KEY_ID = "_id";
    public static final String MATH_KEY_ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final int ID_COLUMN = 0;

    public static final String KEY_QUESTION = "question";
    public static final String QUESTION_OPTIONS = "TEXT NOT NULL";
    public static final int QUESTION_COLUMN = 1;

    public static final String KEY_ANSWER_1 = "answer1";
    public static final String ANSWER_1_OPTIONS = "TEXT NOT NULL";
    public static final int ANSWER_1_COLUMN = 2;

    public static final String KEY_ANSWER_2 = "answer2";
    public static final String ANSWER_2_OPTIONS = "TEXT NOT NULL";
    public static final int ANSWER_2_COLUMN = 3;

    public static final String KEY_ANSWER_3 = "answer3";
    public static final String ANSWER_3_OPTIONS = "TEXT NOT NULL";
    public static final int ANSWER_3_COLUMN = 4;

    public static final String KEY_ANSWER_4 = "answer4";
    public static final String ANSWER_4_OPTIONS = "TEXT NOT NULL";
    public static final int ANSWER_4_COLUMN = 5;

    public static final String KEY_CORRECT_ANSWER = "correctAnswer";
    public static final String KEY_CORRECT_ANSWER_OPTIONS = "TEXT NOT NULL";
    public static final int KEY_CORRECT_COLUMN = 6;

    public static final String DB_CREATE_MATH_TABLE =
            "CREATE TABLE " + DB_MATH_TABLE + "( " +
                    MATH_KEY_ID + " " + MATH_KEY_ID_OPTIONS + ", " +
                    KEY_QUESTION + " " + QUESTION_OPTIONS + ", " +
                    KEY_ANSWER_1 + " " + ANSWER_1_OPTIONS + ", " +
                    KEY_ANSWER_2 + " " + ANSWER_2_OPTIONS + ", " +
                    KEY_ANSWER_3 + " " + ANSWER_3_OPTIONS + ", " +
                    KEY_ANSWER_4 + " " + ANSWER_4_OPTIONS + ", " +
                    KEY_CORRECT_ANSWER + " " + KEY_CORRECT_ANSWER_OPTIONS +
                    ");";
    public static final String DROP_MATH_TABLE =
            "DROP TABLE IF EXISTS " + DB_MATH_TABLE;
}
