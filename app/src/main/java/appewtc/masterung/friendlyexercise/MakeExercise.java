package appewtc.masterung.friendlyexercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MakeExercise extends AppCompatActivity {

    //Explicit
    private String subjectString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_exercise);

        //Show Subject
        showSubject();

    }   // Main Method

    private void showSubject() {
        subjectString = getIntent().getStringExtra("Subject");
        TextView subjectTextView = (TextView) findViewById(R.id.txtTitleMake);
        subjectTextView.setText(subjectString);
    }
}   // Main Class
