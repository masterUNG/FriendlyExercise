package appewtc.masterung.friendlyexercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private ImageView sub1ImageView, sub2ImageView, sub3ImageView, sub4ImageView;
    private String positionString, idString, subjectString;
    private int positionAnInt, idAnInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        //Bind Widget
        bindWidget();

        //Receive from Intent
        receiveFromIntent();

        //Image Controller
        imageController();

    }   // Main Method

    private void imageController() {
        sub1ImageView.setOnClickListener(this);
        sub2ImageView.setOnClickListener(this);
        sub3ImageView.setOnClickListener(this);
        sub4ImageView.setOnClickListener(this);
    }

    private void bindWidget() {
        sub1ImageView = (ImageView) findViewById(R.id.imageView2);
        sub2ImageView = (ImageView) findViewById(R.id.imageView3);
        sub3ImageView = (ImageView) findViewById(R.id.imageView4);
        sub4ImageView = (ImageView) findViewById(R.id.imageView5);
    }

    private void receiveFromIntent() {
        positionString = getIntent().getStringExtra("Position");
        idString = getIntent().getStringExtra("id");

        positionAnInt = Integer.parseInt(positionString);
        idAnInt = Integer.parseInt(idString);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.imageView2:
                subjectString = getResources().getString(R.string.subject1);
                break;
            case R.id.imageView3:
                subjectString = getResources().getString(R.string.subject2);
                break;
            case R.id.imageView4:
                subjectString = getResources().getString(R.string.subject3);
                break;
            case R.id.imageView5:
                subjectString = getResources().getString(R.string.subject4);
                break;
        }   // switch

        switch (positionAnInt) {
            case 0:
                Intent studentIntent = new Intent(ServiceActivity.this, TestExercise.class);
                studentIntent.putExtra("Subject", subjectString);
                studentIntent.putExtra("id", idAnInt);
                startActivity(studentIntent);
                break;
            case 1:
                Intent teacherIntent = new Intent(ServiceActivity.this, MakeExercise.class);
                teacherIntent.putExtra("Subject", subjectString);
                teacherIntent.putExtra("id", idAnInt);
                startActivity(teacherIntent);
                break;
        }   // switch



    }   // onClick
}   // Main Class
