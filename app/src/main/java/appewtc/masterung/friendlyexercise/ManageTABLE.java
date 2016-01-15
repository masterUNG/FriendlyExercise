package appewtc.masterung.friendlyexercise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterUNG on 1/12/16 AD.
 */
public class ManageTABLE {

    //Explicit
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USER = "User";
    public static final String COLUMN_PASSWORD = "Password";
    public static final String COLUMN_STATUS = "Status";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_SURNAME = "Surname";
    public static final String COLUMN_SUBJECT1 = "Subject1";
    public static final String COLUMN_DATESUB1 = "DateSubject1";
    public static final String COLUMN_SUBJECT2 = "Subject2";
    public static final String COLUMN_DATESUB2 = "DateSubject2";
    public static final String COLUMN_SUBJECT3 = "Subject3";
    public static final String COLUMN_DATESUB3 = "DateSubject3";
    public static final String COLUMN_SUBJECT4 = "Subject4";
    public static final String COLUMN_DATESUB4 = "DateSubject4";
    public static final String TABLE_USR = "userTABLE";

    public static final String TABLE_SUBJECT = "subjectTABLE";
    public static final String COLUMN_Subject = "Subject";
    public static final String COLUMN_Question = "Question";
    public static final String COLUMN_Image = "Image";
    public static final String COLUMN_Choice1 = "Choice1";
    public static final String COLUMN_Choice2 = "Choice2";
    public static final String COLUMN_Choice3 = "Choice3";
    public static final String COLUMN_Choice4 = "Choice4";
    public static final String COLUMN_Answer = "Answer";

    public ManageTABLE(Context context) {

        //Create & Connected
        objMyOpenHelper = new MyOpenHelper(context);
        writeSqLiteDatabase = objMyOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMyOpenHelper.getReadableDatabase();

    }   // Constructor

    public String[] searchUser(String strUser) {

        try {

            String[] resultStrings = null;
            Cursor objCursor = readSqLiteDatabase.query(TABLE_USR,
                    new String[]{COLUMN_ID, COLUMN_USER, COLUMN_PASSWORD, COLUMN_STATUS,
                    COLUMN_NAME, COLUMN_SURNAME, COLUMN_SUBJECT1, COLUMN_DATESUB1,
                    COLUMN_SUBJECT2, COLUMN_DATESUB2, COLUMN_SUBJECT3, COLUMN_DATESUB3,
                    COLUMN_SUBJECT4, COLUMN_DATESUB4},
                    COLUMN_USER + "=?",
                    new String[]{String.valueOf(strUser)},
                    null, null, null, null);

            if (objCursor != null) {
                if (objCursor.moveToFirst()) {
                    resultStrings = new String[objCursor.getColumnCount()];
                    for (int i=0;i<objCursor.getColumnCount();i++) {
                        resultStrings[i] = objCursor.getString(i);
                    }   // for
                }   //if
            }   // if
            objCursor.close();
            return resultStrings;

        } catch (Exception e) {
            return null;
        }
    }


    public long addNewValueToSubject(String strSubject,
                                     String strQuestion,
                                     String strImage,
                                     String strChoice1,
                                     String strChoice2,
                                     String strChoice3,
                                     String strChoice4,
                                     String strAnswer) {

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_Subject, strSubject);
        objContentValues.put(COLUMN_Question, strQuestion);
        objContentValues.put(COLUMN_Image, strImage);
        objContentValues.put(COLUMN_Choice1, strChoice1);
        objContentValues.put(COLUMN_Choice2, strChoice2);
        objContentValues.put(COLUMN_Choice3, strChoice3);
        objContentValues.put(COLUMN_Choice4, strChoice4);
        objContentValues.put(COLUMN_Answer, strAnswer);

        return writeSqLiteDatabase.insert(TABLE_SUBJECT, null, objContentValues);
    }

    public long addNewValueToUser(String strUser,
                                  String strPassword,
                                  String strStatus,
                                  String strName,
                                  String strSurname,
                                  String strSub1,
                                  String strDateSub1,
                                  String strSub2,
                                  String strDateSub2,
                                  String strSub3,
                                  String strDateSub3,
                                  String strSub4,
                                  String strDateSub4) {

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_USER, strUser);
        objContentValues.put(COLUMN_PASSWORD, strPassword);
        objContentValues.put(COLUMN_STATUS, strStatus);
        objContentValues.put(COLUMN_NAME, strName);
        objContentValues.put(COLUMN_SURNAME, strSurname);
        objContentValues.put(COLUMN_SUBJECT1, strSub1);
        objContentValues.put(COLUMN_DATESUB1, strDateSub1);
        objContentValues.put(COLUMN_SUBJECT2, strSub2);
        objContentValues.put(COLUMN_DATESUB2, strDateSub2);
        objContentValues.put(COLUMN_SUBJECT3, strSub3);
        objContentValues.put(COLUMN_DATESUB3, strDateSub3);
        objContentValues.put(COLUMN_SUBJECT4, strSub4);
        objContentValues.put(COLUMN_DATESUB4, strDateSub4);

        return writeSqLiteDatabase.insert(TABLE_USR, null, objContentValues);
    }


}   // Main Class
