package mtrzepacz.quizgame.database;

public class StartingTopicsConstants {

    public static final String DB_TOPIC_TABLE = "Topic";
    public static final String TOPIC_KEY_ID = "_id";
    public static final String TOPIC_KEY_ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final int ID_COLUMN = 0;

    public static final String KEY_TOPIC_NAME = "name";
    public static final String TOPIC_NAME_OPTIONS = "TEXT NOT NULL";
    public static final int TOPIC_NAME_COLUMN = 1;

    public static final String DB_CREATE_TOPIC_TABLE =
            "CREATE TABLE " + DB_TOPIC_TABLE + "( " +
                    TOPIC_KEY_ID + " " + TOPIC_KEY_ID_OPTIONS + ", " +
                    KEY_TOPIC_NAME + " " + TOPIC_NAME_OPTIONS +
                    ");";

    public static final String DROP_TOPIC_TABLE =
            "DROP TABLE IF EXISTS " + DB_TOPIC_TABLE;
}
