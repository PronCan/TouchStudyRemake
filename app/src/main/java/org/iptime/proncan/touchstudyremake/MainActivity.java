package org.iptime.proncan.touchstudyremake;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity implements View.OnClickListener {

    private ImageButton startServiceButton;
    private ImageButton stopServiceButton;
    private ImageButton listQuestions, listMistakeQuestions, credits;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startServiceButton = (ImageButton) findViewById(R.id.btn_start);
        stopServiceButton = (ImageButton) findViewById(R.id.btn_stop);
        listQuestions = (ImageButton) findViewById(R.id.btn_questionList);
        listMistakeQuestions = (ImageButton) findViewById(R.id.btn_mistakesList);
        credits = (ImageButton) findViewById(R.id.btn_credits);

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
                creditsDialog();
                break;
        }
    }

    public void creditsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("앱을 만든 사람들");
        builder.setMessage("선린인터넷고등학교\n김수진 : 메인 개발\n이은진 : 서브 개발, DB\n장민정 : 메인 디자인\n장소정 : 서브 개발, 프론트\n최은아 : 기획, 서브 디자인");
        builder.setCancelable(false);        // 뒤로 버튼 클릭시 취소 가능 설정
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
            // 확인 버튼 클릭시 설정
            public void onClick(DialogInterface dialog, int whichButton){
                //finish();

            }
        });
        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기
    }
}
