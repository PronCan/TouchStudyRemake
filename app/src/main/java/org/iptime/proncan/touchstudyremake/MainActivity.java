package org.iptime.proncan.touchstudyremake;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import static org.iptime.proncan.touchstudyremake.R.id.imageButton;

public class MainActivity extends Activity implements View.OnClickListener {

    private ImageView startServiceButton;
    private ImageView stopServiceButton;
    private ImageView listQuestions, listMistakeQuestions, credits;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startServiceButton = (ImageView) findViewById(R.id.btn_start);
        stopServiceButton = (ImageView) findViewById(R.id.btn_stop);
        listQuestions = (ImageView) findViewById(R.id.btn_questionList);
        listMistakeQuestions = (ImageView) findViewById(R.id.btn_mistakesList);
        credits = (ImageView) findViewById(R.id.btn_credits);

        startServiceButton.setOnClickListener(this);
        stopServiceButton.setOnClickListener(this);
        listQuestions.setOnClickListener(this);
        listMistakeQuestions.setOnClickListener(this);
        credits.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start :
                startService(new Intent(this, LockService.class));
                break;
            case R.id.btn_stop :
                stopService(new Intent(this, LockService.class));
                break;
            case R.id.btn_questionList :
                intent = new Intent(this, AllQuestionsList.class);
                startActivity(intent);
                break;
            case R.id.btn_mistakesList :
                intent = new Intent(this, MistakeQuestionsList.class);
                startActivity(intent);
                break;
            case R.id.btn_credits :
                intent = new Intent(this, CreditsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
