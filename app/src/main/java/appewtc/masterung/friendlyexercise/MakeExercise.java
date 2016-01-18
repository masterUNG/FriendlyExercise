package appewtc.masterung.friendlyexercise;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MakeExercise extends AppCompatActivity {

    //Explicit
    private String subjectString, questtionString, choice1String, choice2String,
            choice3String, choice4String, answerString;
    private EditText questionEditText, choice1EditText, choice2EditText,
            choice3EditText, choice4EditText;
    private RadioGroup answerRadioGroup;
    private boolean answerABoolean = true; //Not Choose

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_exercise);

        //Bind Widget
        bindWidget();

        //Show Subject
        showSubject();

        //RadioGroup Controller
        radioController();

    }   // Main Method

    private void radioController() {
        answerRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                answerABoolean = false;

                switch (i) {
                    case R.id.radioButton3:
                        answerString = "1";
                        break;
                    case R.id.radioButton4:
                        answerString = "2";
                        break;
                    case R.id.radioButton5:
                        answerString = "3";
                        break;
                    case R.id.radioButton6:
                        answerString = "4";
                        break;
                }   // switch

            }   // event
        });
    }   // radioController

    private void bindWidget() {

        questionEditText = (EditText) findViewById(R.id.editText7);
        choice1EditText = (EditText) findViewById(R.id.editText8);
        choice2EditText = (EditText) findViewById(R.id.editText9);
        choice3EditText = (EditText) findViewById(R.id.editText10);
        choice4EditText = (EditText) findViewById(R.id.editText11);
        answerRadioGroup = (RadioGroup) findViewById(R.id.ragAnswer);

    }   // bindWidget

    public void clickSaveData(View view) {

        questtionString = questionEditText.getText().toString().trim();
        choice1String = choice1EditText.getText().toString().trim();
        choice2String = choice2EditText.getText().toString().trim();
        choice3String = choice3EditText.getText().toString().trim();
        choice4String = choice4EditText.getText().toString().trim();

        if (checkSpace() || answerABoolean) {
            //Have Space
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.errorDialog(MakeExercise.this, "Have Space", "Please Fill All Every Blank and Choose True Answer");
        } else {
            //No Space
            confirmValue();
        }   // if


    }   // clickSaveData

    private void confirmValue() {

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.icon_myaccount);
        objBuilder.setTitle(subjectString);
        objBuilder.setMessage("คำถาม = " + questtionString + "\n" +
        "ก = " + choice1String + "\n" +
        "ข = " + choice2String + "\n" +
        "ค = " + choice3String + "\n" +
        "ง = " + choice4String + "\n" +
        "เฉลย ข้อ " + answerString);
        objBuilder.setCancelable(false);
        objBuilder.setPositiveButton("Save & Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                upLoadToMySQL();
                dialogInterface.dismiss();
                finish();
            }
        });
        objBuilder.setNeutralButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                upLoadToMySQL();
                dialogInterface.dismiss();
            }
        });
        objBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        objBuilder.show();

    }   // confirmValue

    private void upLoadToMySQL() {

    }   // upLoadToMySQL

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
