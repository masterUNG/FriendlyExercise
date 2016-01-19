package appewtc.masterung.friendlyexercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TestExercise extends AppCompatActivity {

    //Explicit
    private TextView titleTextView;
    private String subjectString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_exercise);

        //Bind Widget
        bindWidget();

        //Show Title
        showTitle();

    }   // Main Method

    private void showTitle() {
        subjectString = getIntent().getStringExtra("Subject");
        titleTextView.setText(subjectString);
    }

    private void bindWidget() {
        titleTextView = (TextView) findViewById(R.id.txtTitleTest);
    }

}   // Main Class
