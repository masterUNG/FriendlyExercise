package appewtc.masterung.friendlyexercise;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MakeExercise extends AppCompatActivity {

    //Explicit
    private String subjectString, questtionString, choice1String, choice2String,
            choice3String, choice4String;
    private EditText questionEditText, choice1EditText, choice2EditText,
            choice3EditText, choice4EditText;
    private RadioGroup answerRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_exercise);

        //Bind Widget
        bindWidget();

        //Show Subject
        showSubject();

    }   // Main Method

    private void bindWidget() {

        questionEditText = (EditText) findViewById(R.id.editText7);
        choice1EditText = (EditText) findViewById(R.id.editText8);
        choice2EditText = (EditText) findViewById(R.id.editText9);
        choice3EditText = (EditText) findViewById(R.id.editText10);
        choice4EditText = (EditText) findViewById(R.id.editText11);

    }   // bindWidget

    public void clickSaveData(View view) {

        questtionString = questionEditText.getText().toString().trim();
        choice1String = choice1EditText.getText().toString().trim();
        choice2String = choice2EditText.getText().toString().trim();
        choice3String = choice3EditText.getText().toString().trim();
        choice4String = choice4EditText.getText().toString().trim();

        if (checkSpace()) {
            //Have Space
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.errorDialog(MakeExercise.this, "Have Space", "Please Fill All Every Blank");
        } else {
            //No Space
        }


    }   // clickSaveData

    private boolean checkSpace() {
        return questtionString.equals("") ||
                choice1String.equals("") ||
                choice2String.equals("") ||
                choice3String.equals("") ||
                choice4String.equals("");
    }

    private void showSubject() {
        subjectString = getIntent().getStringExtra("Subject");
        TextView subjectTextView = (TextView) findViewById(R.id.txtTitleMake);
        subjectTextView.setText(subjectString);
    }
}   // Main Class
