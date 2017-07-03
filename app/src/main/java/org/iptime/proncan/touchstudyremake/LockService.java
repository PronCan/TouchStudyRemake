package org.iptime.proncan.touchstudyremake;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by 호창 on 2017-07-03.
 */

public class LockService extends Service {

    private KeyguardManager km = null;
    private KeyguardManager.KeyguardLock keylock = null;

    private BroadcastReceiver mReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals("android.intent.action.SCREEN_OFF")){
                Intent i = new Intent(context, LockScreen.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        km=(KeyguardManager) this.getSystemService(Activity.KEYGUARD_SERVICE);
        if(km!=null){
            keylock = km.newKeyguardLock("test");
            keylock.disableKeyguard();
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Toast.makeText(this, "잠금화면을 시작합니다", Toast.LENGTH_SHORT).show();
        IntentFilter filter = new IntentFilter("Yes.Alive");
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mReceiver, filter);
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy(){
        if(keylock!=null){
            keylock.reenableKeyguard();
        }

        if(mReceiver != null)
            unregisterReceiver(mReceiver);
        Toast.makeText(this, "잠금화면을 종료합니다", Toast.LENGTH_SHORT).show();
    }
}

