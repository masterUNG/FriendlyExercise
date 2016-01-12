package appewtc.masterung.friendlyexercise;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by masterUNG on 1/12/16 AD.
 */
public class MyOpenHelper extends SQLiteOpenHelper{

    //Explicit
    public static final String tag = "friend";
    public static final String DATABASE_NAME = "Friend.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_USER_TABLE = "create table userTABLE (" +
            "_id integer primary key, " +
            "User text, " +
            "Password text, " +
            "Status text, " +
            "Name text, " +
            "Surname text, " +
            "Subject1 text, " +
            "DateSubject1 text, " +
            "Subject2 text, " +
            "DateSubject2 text, " +
            "Subject3 text, " +
            "DateSubject3 text, " +
            "Subject4 text, " +
            "DateSubject4 text);";

    private static final String CREATE_SUBJECT_TABLE = "create table subjectTABLE (" +
            "_id integer primary key, " +
            "Subject text, " +
            "Question text, " +
            "Image text, " +
            "Choice1 text, " +
            "Choice2 text, " +
            "Choice3 text, " +
            "Choice4 text, " +
            "Answer text);";

    public MyOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }   // Constructor

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(CREATE_SUBJECT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}   // Main Class
