package appewtc.masterung.friendlyexercise;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private ManageTABLE objManageTABLE;
    private EditText userEditText, passwordEditText;
    private RadioGroup positionRadioGroup;
    private RadioButton studentRadioButton, teacherRadioButton;
    private String userString, passwordString, positionString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        bindWidget();

        //Connected
        objManageTABLE = new ManageTABLE(this);

        //Test Add Value
        //testAddValue();

        //Delete All SQLite
        deleteAllSQLite();

    }   // Main Method

    public void clickSignIn(View view) {

        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //Check Space
        if (userString.equals("") || passwordString.equals("")) {
            //Have Space
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.errorDialog(MainActivity.this,
                    "Have Space", "Please Fill All Every Blank");
        } else {

            //No Space

        }   // if

    }   // clickSignIn

    private void bindWidget() {
        userEditText = (EditText) findViewById(R.id.editText);
        passwordEditText = (EditText) findViewById(R.id.editText2);
        positionRadioGroup = (RadioGroup) findViewById(R.id.ragPosition);
        studentRadioButton = (RadioButton) findViewById(R.id.radioButton);
        teacherRadioButton = (RadioButton) findViewById(R.id.radioButton2);
    }

    public void clickSignUp(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }

    private void deleteAllSQLite() {
        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME, MODE_PRIVATE, null);
        objSqLiteDatabase.delete(ManageTABLE.TABLE_USR, null, null);
        objSqLiteDatabase.delete(ManageTABLE.TABLE_SUBJECT, null, null);
    }

    private void testAddValue() {
        String strTest = "Test";
        objManageTABLE.addNewValueToUser(strTest, strTest, strTest, strTest, strTest,
                strTest, strTest, strTest, strTest, strTest, strTest, strTest, strTest);
        objManageTABLE.addNewValueToSubject(strTest, strTest, strTest, strTest,
                strTest, strTest, strTest, strTest);
    }

}   // Main Class
