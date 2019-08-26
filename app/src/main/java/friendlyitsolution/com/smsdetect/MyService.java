package friendlyitsolution.com.smsdetect;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class MyService extends Service {
    MyReceiver mSMSreceiver;
    IntentFilter mIntentFilter;

    public MyService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();

        mSMSreceiver = new MyReceiver();
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");


        registerReceiver(mSMSreceiver, mIntentFilter);
        Toast.makeText(MyApp.con,"Starting......",Toast.LENGTH_SHORT).show();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        Toast.makeText(MyApp.con,"Start service......",Toast.LENGTH_SHORT).show();
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        unregisterReceiver(mSMSreceiver);
        Toast.makeText(MyApp.con,"End service......",Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
