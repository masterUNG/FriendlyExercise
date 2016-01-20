package appewtc.masterung.friendlyexercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_score);

        //Show Score
        showScore();

    }   // Main Method

    private void showScore() {
        int intScore = getIntent().getIntExtra("Score", 0);
        TextView showScoreTextView = (TextView) findViewById(R.id.textView19);
        showScoreTextView.setText(Integer.toString(intScore));
    }   // showScore

}   // Main Class
