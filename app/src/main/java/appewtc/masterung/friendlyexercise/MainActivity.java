package appewtc.masterung.friendlyexercise;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private ManageTABLE objManageTABLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connected
        objManageTABLE = new ManageTABLE(this);

        //Test Add Value
        //testAddValue();

        //Delete All SQLite
        deleteAllSQLite();

    }   // Main Method

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
