package appewtc.masterung.friendlyexercise;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {

    //Explicit
    private EditText userEditText, passwordEditText, nameEditText, surnameEditText;
    private static final String blankSTRING = "na";
    private String userString, passwordString, nameString, surnameString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Bind Widget
        bindWidget();

    }   // Main Method

    private void bindWidget() {
        userEditText = (EditText) findViewById(R.id.editText3);
        passwordEditText = (EditText) findViewById(R.id.editText4);
        nameEditText = (EditText) findViewById(R.id.editText5);
        surnameEditText = (EditText) findViewById(R.id.editText6);
    }

    public void clickSaveData(View view) {

        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();
        nameString = nameEditText.getText().toString().trim();
        surnameString = surnameEditText.getText().toString().trim();

        //Check Space
        if (userString.equals("") ||
                passwordString.equals("") ||
                nameString.equals("") ||
                surnameString.equals("")) {

            //Have Space
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.errorDialog(SignUpActivity.this, "Have Space", "Please Fill All Every Blank");

        } else {

            //No Space
            updateNewStudent();

        } // if

    }   // clickSaveData

    private void updateNewStudent() {

        //Setup New Policy
        StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(myPolicy);

        //Update mySQL
        try {

            ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
            objNameValuePairs.add(new BasicNameValuePair("isAdd", "true"));
            objNameValuePairs.add(new BasicNameValuePair("User", userString));
            objNameValuePairs.add(new BasicNameValuePair("Password", passwordString));
            objNameValuePairs.add(new BasicNameValuePair("Name", nameString));
            objNameValuePairs.add(new BasicNameValuePair("Surname", surnameString));

            String strURL = "http://swiftcodingthai.com/pop/php_add_data_master.php";
            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost(strURL);
            objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
            objHttpClient.execute(objHttpPost);


        } catch (Exception e) {
            Toast.makeText(SignUpActivity.this, "Cannot Update New Value to Server", Toast.LENGTH_SHORT).show();
        }



    }   // updateNewStudent

}   // Main Class
