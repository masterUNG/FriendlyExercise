package appewtc.masterung.friendlyexercise;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TestExercise extends AppCompatActivity {

    //Explicit
    private TextView titleTextView, questionTextView, showTimesTextView;
    private String subjectString;
    private String[] questionStrings, choice1Strings, choice2Strings, choice3Strings, choice4Strings;
    private boolean statusABoolean = true;
    private RadioGroup answerRadioGroup;
    private RadioButton choice1RadioButton, choice2RadioButton, choice3RadioButton, choice4RadioButton;
    private int timesAnInt, currentTimesAnInt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_exercise);

        //Bind Widget
        bindWidget();

        //Show Title
        showTitle();

        //Get Exercise
        getExercise();

    }   // Main Method

    public void clickAnswerTest(View view) {

        if (statusABoolean) {
            //Have data
            changeView();

        } else {
            Toast.makeText(TestExercise.this, "ยังไม่ได้ออกข้อสอบ", Toast.LENGTH_SHORT).show();
        }

    }   // clickAnswer

    private void changeView() {

        String tag = "changeView";
        currentTimesAnInt += 1;
        Log.d(tag, "currentTime = " + currentTimesAnInt);

        if (currentTimesAnInt < timesAnInt) {
            //Continue
            showView(questionStrings[currentTimesAnInt],
                    choice1Strings[currentTimesAnInt],
                    choice2Strings[currentTimesAnInt],
                    choice3Strings[currentTimesAnInt],
                    choice4Strings[currentTimesAnInt]
                    );

        } else {
            //Stop
            Intent objIntent = new Intent(TestExercise.this, ShowScoreActivity.class);
            startActivity(objIntent);
        }


    }   // changeView

    private void getExercise() {

        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME, MODE_PRIVATE, null);
        Cursor objCursor = objSqLiteDatabase.rawQuery("SELECT * FROM subjectTABLE WHERE Subject = " + "'" + subjectString + "'",
                null);
        if (objCursor.getCount() != 0) {
            objCursor.moveToFirst();
            questionStrings = new String[objCursor.getCount()];
            choice1Strings = new String[objCursor.getCount()];
            choice2Strings = new String[objCursor.getCount()];
            choice3Strings = new String[objCursor.getCount()];
            choice4Strings = new String[objCursor.getCount()];
            timesAnInt = objCursor.getCount();

            for (int i = 0; i < objCursor.getCount(); i++) {

                questionStrings[i] = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Question));
                choice1Strings[i] = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Choice1));
                choice2Strings[i] = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Choice2));
                choice3Strings[i] = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Choice3));
                choice4Strings[i] = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Choice4));

                objCursor.moveToNext();
            }   // for
            objCursor.close();

            //ShowView
            showView(questionStrings[0], choice1Strings[0], choice2Strings[0],choice3Strings[0],choice4Strings[0]);



        } else {
            statusABoolean = false;
        } //if

    }   // getExercise

    private void showView(String questionString, String choice1String, String choice2String, String choice3String, String choice4String) {

        questionTextView.setText(questionString);
        choice1RadioButton.setText(choice1String);
        choice2RadioButton.setText(choice2String);
        choice3RadioButton.setText(choice3String);
        choice4RadioButton.setText(choice4String);

        showTimesTextView.setText("แบบผึกหัด มีทั้งหมด " + Integer.toString(timesAnInt) + " ข้อ");

    }   // showView

    private void showTitle() {
        subjectString = getIntent().getStringExtra("Subject");
        titleTextView.setText(subjectString);
    }

    private void bindWidget() {
        titleTextView = (TextView) findViewById(R.id.txtTitleTest);
        questionTextView = (TextView) findViewById(R.id.textView17);
        answerRadioGroup = (RadioGroup) findViewById(R.id.ragAnswer);
        choice1RadioButton = (RadioButton) findViewById(R.id.radioButton7);
        choice2RadioButton = (RadioButton) findViewById(R.id.radioButton8);
        choice3RadioButton = (RadioButton) findViewById(R.id.radioButton9);
        choice4RadioButton = (RadioButton) findViewById(R.id.radioButton10);
        showTimesTextView = (TextView) findViewById(R.id.txtShowTime);

    }

}   // Main Class
