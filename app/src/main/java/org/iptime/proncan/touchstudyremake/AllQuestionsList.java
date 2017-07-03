package org.iptime.proncan.touchstudyremake;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by 호창 on 2017-07-03.
 */

public class AllQuestionsList extends Activity {

    ArrayList<Question> questions;
    ArrayAdapter<String> mAdapter;
    DBHelper mDBHelper;
    ListView allQuestionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_questions);

        mAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_questions_detail, R.id.tv_listQuestion);
        allQuestionsList = (ListView) findViewById(R.id.listView_questionsList);
        allQuestionsList.setAdapter(mAdapter);
        allQuestionsList.setOnItemClickListener(onItemClickListener);

        mDBHelper = new DBHelper(this);

        try {
            questions = mDBHelper.getAllQuestions();
            for(int i=0; i<questions.size(); i++) {
                mAdapter.add(questions.get(i).question);
            }
        } catch (Exception e) {
            Log.d("%%%TAG_All%%%", e.getMessage().toString());
        }


    }


    void detailQuestionShow(final int id) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 제목 설정
        builder.setTitle(questions.get(id).question);

        if(questions.get(id).answerNum == 1)
            builder.setMessage("[정답] "+questions.get(id).answer1+"\n[해설]\n"+questions.get(id).comment);
        else if(questions.get(id).answerNum == 2)
            builder.setMessage("[정답] "+questions.get(id).answer2+"\n[해설]\n"+questions.get(id).comment);
        else if(questions.get(id).answerNum == 3)
            builder.setMessage("[정답] "+questions.get(id).answer3+"\n[해설]\n"+questions.get(id).comment);
        else if(questions.get(id).answerNum == 4)
            builder.setMessage("[정답] "+questions.get(id).answer4+"\n[해설]\n"+questions.get(id).comment);

        builder.setCancelable(false);        // 뒤로 버튼 클릭시 취소 가능 설정

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
            // 확인 버튼 클릭시 설정
            public void onClick(DialogInterface dialog, int whichButton){

            }
        });

        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기

    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            detailQuestionShow(i);

        }
    };

}
