package org.iptime.proncan.touchstudyremake;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by 호창 on 2017-07-04.
 */

public class CreditsActivity extends Activity implements View.OnClickListener {
    Button btn_gotoMain = (Button) findViewById(R.id.btn_gotoMain);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_gotoMain :
                finish();
                break;
        }
    }
}
