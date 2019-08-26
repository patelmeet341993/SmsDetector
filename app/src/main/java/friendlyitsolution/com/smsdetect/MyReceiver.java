package friendlyitsolution.com.smsdetect;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.util.Random;

import static android.content.Context.NOTIFICATION_SERVICE;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        SmsMessage[] smsMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        for (SmsMessage message : smsMessages) {

            Toast.makeText(MyApp.con,"Detect : "+message.getDisplayMessageBody(),Toast.LENGTH_LONG).show();
            // showNotification(message.getOriginatingAddress(),message.getDisplayMessageBody());
           NotificationHelper n=new NotificationHelper(MyApp.con);
           n.createNotification(message.getOriginatingAddress(),message.getDisplayMessageBody());


        }
    }





    void showNotificationType2(String title,String msg)
    {

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+title));
        PendingIntent pendingIntent = PendingIntent.getActivity(MyApp.con, 0, callIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent callIntent1 = new Intent(MyApp.con,MainActivity.class);
        callIntent1.putExtra("number",title);
        PendingIntent pendingIntent1 = PendingIntent.getActivity(MyApp.con, 1, callIntent1, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification noti = new Notification.Builder(MyApp.con)
                .setContentTitle(title)
                .setContentText(msg).setSmallIcon(R.drawable.logo)
                //   .setContentIntent(pIntent)
                .setPriority(Notification.PRIORITY_MAX)
                .setWhen(0)
                .addAction(R.drawable.ic_call_black_24dp, "Call", pendingIntent)
                .addAction(R.drawable.logo, "show", pendingIntent1).build();
        NotificationManager notificationManager = (NotificationManager) MyApp.con.getSystemService(NOTIFICATION_SERVICE);
        // Hide the notification after its selected
        Random r=new Random();
        notificationManager.notify(r.nextInt(), noti);
    }

}
