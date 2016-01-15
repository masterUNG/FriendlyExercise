package appewtc.masterung.friendlyexercise;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

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

        //Synchronize JSON to SQLite
        synJSONtoSQLite();

    }   // Main Method

    private void synJSONtoSQLite() {

        //Setup Policy
        StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(myPolicy);

        int intTimes = 1;
        while (intTimes <= 2) {

            //1. Create InputStream
            InputStream objInputStream = null;
            String strURLuser = "http://swiftcodingthai.com/pop/php_get_data_master.php";
            String strURLsubject = "http://swiftcodingthai.com/pop/php_get_subject_master.php";
            HttpPost objHttpPost = null;

            try {

                HttpClient objHttpClient = new DefaultHttpClient();
                switch (intTimes) {
                    case 1:
                        objHttpPost = new HttpPost(strURLuser);
                        break;
                    case 2:
                        objHttpPost = new HttpPost(strURLsubject);
                        break;
                }   //switch

                HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
                HttpEntity objHttpEntity = objHttpResponse.getEntity();
                objInputStream = objHttpEntity.getContent();

            } catch (Exception e) {
                Log.d("friend", "InputStream ==> " + e.toString());
            }


            //2. Create JSON String
            String strJSON = null;
            String strLine = null;

            try {

                BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objInputStream, "UTF-8"));
                StringBuilder objStringBuilder = new StringBuilder();

                while ((strLine = objBufferedReader.readLine()) != null) {
                    objStringBuilder.append(strLine);
                }   // while

                objInputStream.close();
                strJSON = objStringBuilder.toString();

            } catch (Exception e) {
                Log.d("friend", "JSON String ==> " + e.toString());
            }


            //3. Update to SQLite
            try {

                JSONArray objJsonArray = new JSONArray(strJSON);

                for (int i = 0; i < objJsonArray.length(); i++) {

                    JSONObject object = objJsonArray.getJSONObject(i);
                    switch (intTimes) {
                        case 1:

                            //userTABLE
                            String strUser = object.getString(ManageTABLE.COLUMN_USER);
                            String strPassword = object.getString(ManageTABLE.COLUMN_PASSWORD);
                            String strStatus = object.getString(ManageTABLE.COLUMN_STATUS);
                            String strName = object.getString(ManageTABLE.COLUMN_NAME);
                            String strSurname = object.getString(ManageTABLE.COLUMN_SURNAME);
                            String strSub1 = object.getString(ManageTABLE.COLUMN_SUBJECT1);
                            String strDateSub1 = object.getString(ManageTABLE.COLUMN_DATESUB1);
                            String strSub2 = object.getString(ManageTABLE.COLUMN_SUBJECT2);
                            String strDateSub2 = object.getString(ManageTABLE.COLUMN_DATESUB2);
                            String strSub3 = object.getString(ManageTABLE.COLUMN_SUBJECT3);
                            String strDateSub3 = object.getString(ManageTABLE.COLUMN_DATESUB3);
                            String strSub4 = object.getString(ManageTABLE.COLUMN_SUBJECT4);
                            String strDateSub4 = object.getString(ManageTABLE.COLUMN_DATESUB4);

                            objManageTABLE.addNewValueToUser(strUser, strPassword, strStatus,
                                    strName, strSurname, strSub1, strDateSub1, strSub2, strDateSub2,
                                    strSub3, strDateSub3, strSub4, strDateSub4);

                            break;
                        case 2:

                            //subjectTABLE
                            String strSubject = object.getString(ManageTABLE.COLUMN_Subject);
                            String strQuestion = object.getString(ManageTABLE.COLUMN_Question);
                            String strImage = object.getString(ManageTABLE.COLUMN_Image);
                            String strChoice1 = object.getString(ManageTABLE.COLUMN_Choice1);
                            String strChoice2 = object.getString(ManageTABLE.COLUMN_Choice2);
                            String strChoice3 = object.getString(ManageTABLE.COLUMN_Choice3);
                            String strChoice4 = object.getString(ManageTABLE.COLUMN_Choice4);
                            String strAnswer = object.getString(ManageTABLE.COLUMN_Answer);

                            objManageTABLE.addNewValueToSubject(strSubject, strQuestion, strImage,
                                    strChoice1, strChoice2, strChoice3, strChoice4, strAnswer);

                            break;
                    }   // switch

                }   // for

            } catch (Exception e) {
                Log.d("friend", "Update SQLite ==> " + e.toString());
            }

            intTimes += 1;
        }   // while


    }   // synJSONtoSQLite

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
